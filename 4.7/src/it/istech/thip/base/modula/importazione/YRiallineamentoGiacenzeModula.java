package it.istech.thip.base.modula.importazione;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Authorizable;

import it.istech.thip.base.modula.YModulaConnection;
import it.istech.thip.base.modula.YPersDatiModula;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documenti.CausaleRiga;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.magazzino.comuniMag.web.DatiWebArticolo;
import it.thera.thip.magazzino.documenti.DocMagGenerico;
import it.thera.thip.magazzino.documenti.DocMagGenericoRiga;
import it.thera.thip.magazzino.documenti.DocMagGenericoTM;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.magazzino.saldi.SaldoMagTM;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 29/02/2024
 * <br><br>
 * <b></b>
 * <p>Prima stesura.<br>
 * Si occupa del riallineamento delle giacenze tra Panthera e Modula.<br>
 * Legge le tabelle {@value YExpGiacenze#TABLE_NAME},{@value SaldoMagTM#TABLE_NAME}.<br></br>
 * Confronta le giacenze e crea un documento generico contenente tante righe quante le giacenze da rettificare.<br></br>
 * Se:
 * <ul>
 *  <li>Non e' presente un saldo per l'articolo, il saldo viene creato tramite il Documento Generico.</li>
 *  <li>La giacenza di modula e' > della giacenza di Panthera, viene effettuata una rettifica positiva in Panthera.</li>
 *  <li>La giacenza di modula e' < della giacenza di Panthera, viene effettuata una rettifica negativa in Panthera.</li>
 *  <li>La giacenza di modula e' == alla giacenza di Panthera, non viene effettuata nessuna rettifica.</li>
 * </ul>
 * <b>NB: </b>Gli articoli devono avere la ClasseA = 'MO'.<br>
 * Vengono creati Documenti Generici solo su magazzino 'MOD'.
 * </p>
 */

public class YRiallineamentoGiacenzeModula extends BatchRunnable implements Authorizable{

	private static final char RETTIFICA_POSITIVA = 'P';

	private static final char RETTIFICA_NEGATIVA = 'N';

	private YPersDatiModula persDatiModula = null;

	public static final String STMT_SELECT_GIACENZA = " SELECT * "
			+ " FROM "+YExpGiacenze.TABLE_NAME+" ";

	@Override
	protected boolean run() {
		boolean isOk = true;
		output.println(" *** INIZIO RICEZIONE MOVIMENTI DA MODULA  *** ");
		persDatiModula = YPersDatiModula.getCurrenPersDatiModula();
		if(persDatiModula != null) {
			isOk = checkPersDatiModula();
			if(isOk) {
				isOk = riallineamentoGiacenzeModula();
			}else {
				isOk = false;
				output.println(" checkPersDatiModula fallita!, termino il lavoro...");
			}
		}else {
			output.println("Non e' stata definita nessuna personalizzazione dati modula per l'azienda : "+Azienda.getAziendaCorrente());
			isOk = false;
		}
		output.println(" *** FINE RICEZIONE MOVIMENTI DA MODULA  *** ");
		return isOk;
	}

	@SuppressWarnings("unchecked")
	protected boolean riallineamentoGiacenzeModula() {
		HashMap<String, Object[]> giacDaRiallineare = recuperaGiacenzeDaRiallineare();
		if(giacDaRiallineare.size() > 0) {
			int rcTestata = 0;
			try {
				DocMagGenerico docMagGen = null;
				docMagGen = ricercaDocumentoGenericoModulaOdierno();
				if(docMagGen == null) {
					docMagGen = creaDocumentoDiMagazzinoGenerico();
					docMagGen.setAlfanumRiservatoUtente1(TimeUtils.getCurrentDate().toString());
					docMagGen.setAlfanumRiservatoUtente2("DOC_RIA_GIAC_MODULA");
					rcTestata = docMagGen.save();
				}
				if(rcTestata >= BODataCollector.OK) {
					for (Map.Entry<String, Object[]> entry : giacDaRiallineare.entrySet()) {
						String idArticolo = entry.getKey();
						Object[] valori = entry.getValue();
						CausaleRiga causale = getCausaleRigaDocumentoGenericoDaTipologiaMovimento((char)valori[0]);
						if(causale != null) {
							DocMagGenericoRiga docMagGenRig = creaRigaDocumentoDiMagazzinoGenerico(
									docMagGen,
									causale,
									idArticolo,
									(BigDecimal) valori[1]);
							if(docMagGenRig != null) {
								docMagGen.getRighe().add(docMagGenRig);
							}
						}
					}
					if(docMagGen.getRighe().size() > 0) {
						docMagGen.setSalvaRighe(true);
						int rc = 0;
						try {
							rc = docMagGen.save();
							if(rc >= BODataCollector.OK) {
								ConnectionManager.commit();
							}else {
								ConnectionManager.rollback();
								output.println("Impossibile salvare il documento di trasferimento, errore: \n"
										+CreaMessaggioErrore.daRcAErrorMessage(rc, null));
							}
						}catch (SQLException e) {
							output.println("Impossibile salvare il documento di trasferimento, errore: \n"
									+CreaMessaggioErrore.daRcAErrorMessage(rc, e));
							e.printStackTrace(Trace.excStream);
						}
					}else {
						ConnectionManager.rollback();
					}
				}else {

				}
			}catch (SQLException e) {
				if(rcTestata != 0) {
					output.println("Impossibile salvare il documento generico, errore: \n"
							+CreaMessaggioErrore.daRcAErrorMessage(rcTestata, e));
				}
				e.printStackTrace(Trace.excStream);
			}
		}else {
			//non e' stata trovata nessuna riga da riallineare
		}
		return false;
	}

	protected DocMagGenerico ricercaDocumentoGenericoModulaOdierno() {
		ResultSet rs = null;
		CachedStatement cs = null;
		String stmt = null;
		try {
			stmt = "SELECT "+DocMagGenericoTM.ID_AZIENDA+","+DocMagGenericoTM.ID_ANNO_DOC+","+DocMagGenericoTM.ID_NUMERO_DOC+" "
					+ "FROM "+DocMagGenericoTM.TABLE_NAME+" "
					+ "WHERE "+DocMagGenericoTM.ID_AZIENDA+"= '"+Azienda.getAziendaCorrente()+"' "
					+ "AND "+DocMagGenericoTM.STRINGA_RIS_UTE_1+" = '"+TimeUtils.getCurrentDate().toString()+"' "
					+ "AND "+DocMagGenericoTM.STRINGA_RIS_UTE_2+" = 'DOC_RIA_GIAC_MODULA' ";
			cs = new CachedStatement(stmt);
			rs = cs.executeQuery();
			if(rs.next()) {
				return (DocMagGenerico) DocMagGenerico.elementWithKey(
						DocMagGenerico.class,
						KeyHelper.buildObjectKey(new String[] {
								rs.getString(DocMagGenericoTM.ID_AZIENDA),
								rs.getString(DocMagGenericoTM.ID_ANNO_DOC),
								rs.getString(DocMagGenericoTM.ID_NUMERO_DOC)
						}),
						PersistentObject.NO_LOCK);
			}else {
				return null;
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	protected CausaleRiga getCausaleRigaDocumentoGenericoDaTipologiaMovimento(char tipologia) {
		CausaleRiga causale = null;
		switch (tipologia) {
		case RETTIFICA_POSITIVA:
			causale = persDatiModula.getRelcaudocrigriallpos();
			break;
		case RETTIFICA_NEGATIVA:
			causale = persDatiModula.getRelcaudocrigriallretneg();
			break;
		default:
			break;
		}
		return causale;
	}

	protected DocMagGenerico creaDocumentoDiMagazzinoGenerico() {
		DocMagGenerico docGen = (DocMagGenerico) Factory.createObject(DocMagGenerico.class);
		docGen.setIdAzienda(Azienda.getAziendaCorrente());
		docGen.setCausale(persDatiModula.getRelcaudocriall());
		docGen.getNumeratoreHandler().setIdAzienda(Azienda.getAziendaCorrente());
		docGen.getNumeratoreHandler().setDataDocumento(TimeUtils.getCurrentDate());
		docGen.getNumeratoreHandler().setIdSerie(persDatiModula.getRelseriedocriall().getIdSerie());
		docGen.setIdMagazzino("MOD");
		docGen.setStatoAvanzamento(StatoAvanzamento.DEFINITIVO);
		return docGen;
	}

	protected DocMagGenericoRiga creaRigaDocumentoDiMagazzinoGenerico(DocMagGenerico testata, CausaleRiga causaleRiga,String idArticolo,BigDecimal quantita) throws SQLException {
		DocMagGenericoRiga riga = (DocMagGenericoRiga) Factory.createObject(DocMagGenericoRiga.class);
		riga.setTestata(testata);
		riga.setIdAzienda(testata.getIdAzienda());
		riga.setDataRegistrazione(testata.getDataDocumento());			
		riga.setCausaleRiga(causaleRiga);
		riga.setIdMagazzino(testata.getIdMagazzino());
		riga.setIdArticolo(idArticolo);
		DatiWebArticolo dati = null;
		String articoloKey = KeyHelper.buildObjectKey(new String [] {testata.getIdAzienda(), riga.getIdArticolo()});
		Articolo articolo = Articolo.elementWithKey(articoloKey, PersistentObject.NO_LOCK);
		if (articolo != null) {
			dati = new DatiWebArticolo(articolo);
			riga.setIdUMPrm(dati.getIdUMPrim());
			riga.setIdUMSec(dati.getIdUMSec());
			riga.setFattConverUMPrimSec(dati.getFattoreConversione());
			riga.setOperatoreConversioneUM(dati.getOperatoreConversione());
			riga.setDescrizioneArticolo(dati.getDescrizione());
			//		if(docJson.getIdConfigurazione() != null && !docJson.getIdConfigurazione().isEmpty())
			//			riga.setIdConfigurazione(Integer.parseInt(docJson.getIdConfigurazione()));
			riga.completaBO();
			riga.getQuantita().setQuantitaInUMPrm(quantita);		
			riga.setStatoAvanzamento(StatoAvanzamento.DEFINITIVO);
			if(articolo.isArticLotto()) {
				//x futura gestione lotti in caso ci saranno
				//				DocMagGenRigaLotto rigaLotto = (DocMagGenRigaLotto) Factory.createObject(DocMagGenRigaLotto.class);
				//				rigaLotto.setFather(riga);
				//				rigaLotto.setIdArticolo(riga.getIdArticolo());
				//				rigaLotto.setIdLotto(docJson.getIdLotto());
				//				rigaLotto.getQuantita().setQuantitaInUMPrm(docJson.getQuantita());
				//				riga.getRigheLotto().add(rigaLotto);
			}

		}
		return riga;
	}

	protected HashMap<String, Object[]> recuperaGiacenzeDaRiallineare() {
		List<YExpGiacenze> lst = getListaExpGiacenze();
		HashMap<String, Object[]> risultato = new HashMap<String, Object[]>();
		for (YExpGiacenze giacenza : lst) {
			Articolo articolo = checkArticolo(giacenza);
			if(articolo != null
					&& (articolo.getClasseA() != null && articolo.getIdClasseA().equals("MO")) ) {
				SaldoMag saldoBase = getSaldoBaseMagazzino(Azienda.getAziendaCorrente(),
						"MOD", articolo.getIdArticolo(), null, null, null);
				if(saldoBase == null) {
					//non esiste il saldo quindi va fatto comunque un doc gen per sistemare
					if(giacenza.getGia_Giac().compareTo(BigDecimal.ZERO) != 0) {
						//o va fatta rettifica positiva o negativa
						if(giacenza.getGia_Giac().compareTo(BigDecimal.ZERO) > 0) {
							//positiva di tutto
							risultato.put(articolo.getIdArticolo(), new Object[] {RETTIFICA_POSITIVA,giacenza.getGia_Giac()});
						}else {
							//negativa di tutto
							risultato.put(articolo.getIdArticolo(), new Object[] {RETTIFICA_NEGATIVA,giacenza.getGia_Giac()});
						}
					}
				}else {
					BigDecimal qtaGiac = saldoBase != null ? saldoBase.getDatiSaldo().getQtaGiacenzaUMPrim() : BigDecimal.ZERO;
					if(qtaGiac.compareTo(giacenza.getGia_Giac()) != 0) {
						BigDecimal discreapanza = BigDecimal.ZERO;
						if(giacenza.getGia_Giac().compareTo(qtaGiac) > 0) {
							//positiva solo della discreapanza
							discreapanza = giacenza.getGia_Giac().subtract(qtaGiac);
							risultato.put(articolo.getIdArticolo(), new Object[] {RETTIFICA_POSITIVA,discreapanza});
						}else {
							//negativa solo della discrepanza
							discreapanza = qtaGiac.subtract(giacenza.getGia_Giac());
							risultato.put(articolo.getIdArticolo(), new Object[] {RETTIFICA_NEGATIVA,discreapanza});
						}
					}else {
						//le giacenze sono allineate
					}
				}
			}else {
				output.println("L'articolo {"+(Azienda.getAziendaCorrente()+"/"+giacenza.getGia_Articolo()+"} non esiste in Panthera"));
			}
		}
		return risultato;
	}

	protected Articolo checkArticolo(YExpGiacenze riga) {
		try {
			return (Articolo) Articolo.elementWithKey(Articolo.class, KeyHelper.buildObjectKey(new String[] {
					Azienda.getAziendaCorrente(),
					riga.getGia_Articolo()
			}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	protected static SaldoMag getSaldoBaseMagazzino(String idAzienda,String idMagazzino,String idArticolo,String idVersione,String idConfigurazione,String idOperazione) {
		try {
			return (SaldoMag) SaldoMag.elementWithKey(
					SaldoMag.class,
					KeyHelper.buildObjectKey(new String[] {
							idAzienda,
							idMagazzino,
							idArticolo,
							idVersione != null ? idVersione : "1",
									idConfigurazione != null ? idConfigurazione : "0",
											idOperazione != null ? idOperazione : "DUMMY"
					}),
					PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	protected List<YExpGiacenze> getListaExpGiacenze(){
		YResultSetIteratorExpGiacenze iterator = null;
		List<YExpGiacenze> lst = new ArrayList<YExpGiacenze>();
		try {
			Connection connection = YModulaConnection.getModulaConnection();
			ResultSet rs = null;
			try (
					Connection conn = connection;
					PreparedStatement ps = conn.prepareStatement(STMT_SELECT_GIACENZA);
					) {
				rs = ps.executeQuery();
				iterator = new YResultSetIteratorExpGiacenze(rs);
				while(iterator.hasNext()) {
					lst.add((YExpGiacenze) iterator.next());
				}

			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			if(iterator != null) {
				try {
					iterator.closeCursor();
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}
		return lst;
	}

	protected boolean checkPersDatiModula() {
		if(persDatiModula.getRelseriedocriall() == null) {
			output.println("Non e' stata definita una serie per il Documento Generico \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		if(persDatiModula.getRelcaudocriall() == null) {
			output.println("Non e' stata definita una causale per il Documento Generico \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		if(persDatiModula.getRelcaudocrigriallpos() == null) {
			output.println("Non e' stata definita una causale di rettifica positiva per il Documento Generico \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		if(persDatiModula.getRelcaudocrigriallretneg() == null) {
			output.println("Non e' stata definita una causale di rettifica negativa per il Documento Generico \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		return true;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YRiaGiacMODULA";
	}


}
