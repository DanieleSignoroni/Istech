package it.istech.thip.base.modula.importazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.TimeUtils;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Authorizable;

import it.istech.thip.base.modula.TipoDocumentoModula;
import it.istech.thip.base.modula.TipoMovimentoModula;
import it.istech.thip.base.modula.YModulaConnection;
import it.istech.thip.base.modula.YModulaToPanth;
import it.istech.thip.base.modula.YPersDatiModula;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.magazzino.documenti.DocMagTrasferimento;
import it.thera.thip.magazzino.documenti.DocMagTrasferimentoRiga;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 29/02/2024
 * <br><br>
 * <b>71453	DSSOF3	29/02/2024</b>
 * <p>Prima stesura.<br>
 * Si occupa della ricezione dei movimenti da Modula.<br>
 * Legge le tabelle {@value YExpOrdini#TABLE_NAME},{@value YExpOrdiniRighe#TABLE_NAME} e crea 1 documento con N righe per ogni record.<br></br>
 * Se:
 * <ul>
 * 		<li> Tipo movimento == {@link TipoMovimentoModula#PRELIEVO} allora viene generato<br> un documento 
 * 			 di trasferimento che ha come magazzino di partenza MOD --> e di arrivo 001.<br>
 * 		 </li>
 * 		<li> Tipo movimento == {@link TipoMovimentoModula#VERSAMENTO} allora viene generato<br> un documento 
 * 			 di trasferimento che ha come magazzino di partenza 001 --> e di arrivo MOD.<br>
 * 		 </li>
 * </ul>
 * Una volta creato il documento di trasferimento, vengono puliti i record nelle due tabelle del Modula.<br>
 * Se questa pulizia non va a buon fine il documento viene rollbacckato e verra' re-importato nei successivi lanci.<br>
 * </p>
 */

public class YRicezioneMovimentiModula extends BatchRunnable implements Authorizable{

	private YPersDatiModula persDatiModula = null;

	public static final String STMT_SELECT_ORDINI = " SELECT "
			+ ""+YExpOrdini.ORD_ORDINE+","
			+ ""+YExpOrdini.ORD_DES+","
			+ ""+YExpOrdini.ORD_TIPOOP+","
			+ ""+YExpOrdini.ORD_STATO+" "
			+ " FROM "+YExpOrdini.TABLE_NAME+" ";

	public static final String STMT_DELETE_ORDINE = " DELETE "
			+ " FROM "+YExpOrdini.TABLE_NAME+" "
			+ " WHERE "+YExpOrdini.ORD_ORDINE+" = ? ";

	public static final String STMT_SELECT_ORDINI_RIGHE = " SELECT "
			+ ""+YExpOrdiniRighe.RIG_ORDINE+","
			+ ""+YExpOrdiniRighe.RIG_ARTICOLO+","
			+ ""+YExpOrdiniRighe.RIG_SUB1+","
			+ ""+YExpOrdiniRighe.RIG_HOSTINF+","
			+ ""+YExpOrdiniRighe.RIG_QTAR+","
			+ ""+YExpOrdiniRighe.RIG_QTAE+","
			+ ""+YExpOrdiniRighe.RIG_STARIORD+" "
			+ " FROM "+YExpOrdiniRighe.TABLE_NAME+" WHERE "+YExpOrdiniRighe.RIG_ORDINE+" = ? ";

	public static final String STMT_DELETE_ORDINI_RIGHE = " DELETE "
			+ " FROM "+YExpOrdiniRighe.TABLE_NAME+" "
			+ " WHERE "+YExpOrdiniRighe.RIG_ORDINE+" = ? ";

	@Override
	protected boolean run() {
		boolean isOk = true;
		output.println(" *** INIZIO RICEZIONE MOVIMENTI DA MODULA  *** ");
		persDatiModula = YPersDatiModula.getCurrenPersDatiModula();
		if(persDatiModula != null) {
			isOk = checkPersDatiModula();
			if(isOk) {
				isOk = ricezioneMovimentiDaModula();
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

	@SuppressWarnings("rawtypes")
	protected boolean ricezioneMovimentiDaModula() {
		List<YExpOrdini> lst = getListaExpOrdini();
		boolean isOk = true;
		if(lst.size() > 0) {
			for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
				YExpOrdini yExpOrdini = (YExpOrdini) iterator.next();
				List<YExpOrdiniRighe> rows = getListaExpOrdiniRighe(yExpOrdini.getOrd_ordine());
				yExpOrdini.getRighe().addAll(rows);
			}
			isOk = generaDocumentiDiTrasferimento(lst);
		}else {
			output.println("Non ci sono movimenti da importare...termino");
		}
		return isOk;
	}

	@SuppressWarnings("unchecked")
	protected boolean generaDocumentiDiTrasferimento(List<YExpOrdini> lst) {
		for (Iterator<YExpOrdini> iterator = lst.iterator(); iterator.hasNext();) {
			YExpOrdini movimento = (YExpOrdini) iterator.next();
			String idMagazzinoPartenza = null;
			String idMagazzinoArrivo = null;
			switch (movimento.getOrd_tipoop().charAt(0)) {
			case TipoMovimentoModula.PRELIEVO:
				idMagazzinoArrivo = "MOD";
				idMagazzinoPartenza = "001";
				break;
			case TipoMovimentoModula.VERSAMENTO:
				idMagazzinoArrivo = "001";
				idMagazzinoPartenza = "MOD";
				break;

			default:
				break;
			}
			if(idMagazzinoArrivo != null && idMagazzinoPartenza != null) {
				int rc = 0;
				try {
					DocMagTrasferimento docTrasf = creaDocumentoTrasferimento(idMagazzinoPartenza, idMagazzinoArrivo);
					String msg = movimento.getOrd_tipoop().charAt(0) == TipoMovimentoModula.PRELIEVO ? "prelievo" : "versamento";
					docTrasf.setNota("Movimento Modula di tipo : "+msg+" derivante da ordine {"+movimento.getOrd_ordine()+"}");
					docTrasf.setAlfanumRiservatoUtente1(movimento.getOrd_ordine());
					docTrasf.setAlfanumRiservatoUtente2(msg);
					if(docTrasf.save() >= BODataCollector.OK) {
						//generiamo ora le righe
						Iterator<YExpOrdiniRighe> iterRows = movimento.getRighe().iterator();
						while (iterRows.hasNext()) {
							YExpOrdiniRighe riga = (YExpOrdiniRighe) iterRows.next();
							Articolo articolo = checkArticolo(riga);
							if(articolo != null) {
								DocMagTrasferimentoRiga docTrasfRiga = creaDocumentoTrasferimentoRiga(docTrasf, riga, articolo);
								docTrasf.getRighe().add(docTrasfRiga);
							}else {
								output.println("L'articolo {"+(Azienda.getAziendaCorrente()+"/"+riga.getRig_articolo()+"} non esiste in Panthera"));
							}
							rc += YModulaToPanth.scriviModulaToPanth(movimento, riga, TipoDocumentoModula.DOCUMENTO_TRASFERIMENTO);
						}
						if(docTrasf.getRighe().size() > 0) {
							docTrasf.setSalvaRighe(true);
							try {
								rc = docTrasf.save();
								if(rc >= BODataCollector.OK) {
									boolean isOk = puliziaTabelleModula(movimento.getOrd_ordine());
									//Committo solo se ho pulito anche le tabelle di modula
									if(!isOk) {
										ConnectionManager.rollback();
									}else {
										ConnectionManager.commit();
									}
								}else {
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
						if(docTrasf.getException() != null) {
							output.println("Impossibile salvare il documento di trasferimento, errore : \n "
									+ ""+docTrasf.getException().toString());
						}
					}
				}catch (SQLException e) {
					output.println("ERRORE in creazione documento di trasferimento "+ (e.getMessage()  != null ? e.getMessage() : e.getSQLState()));
					e.printStackTrace(Trace.excStream);
				}
			}else {
				output.println("Il record con chiave {"+movimento.getOrd_ordine()+"} ha un tipo di movimento non congruo, tipo movimento = "+movimento.getOrd_tipoop());
				continue;
			}
		}
		return true;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Si occupa di pulire le tabelle {@value YExpOrdini#TABLE_NAME}, {@value YExpOrdiniRighe#TABLE_NAME}.<br>
	 * Se entrambe le update vanno a buon fine committa, altrimenti rollbaccka.
	 * </p>
	 * @param ord_ordine (la chiave della tabella).
	 * @return true se ha pulito entrambe le tabelle, false se qualcosa e' andato storto
	 */
	protected boolean puliziaTabelleModula(String ord_ordine) {
		Connection connection = null;
		try {
			connection = YModulaConnection.getModulaConnection();
			try (
					Connection conn = connection;
					PreparedStatement ps1 = conn.prepareStatement(STMT_DELETE_ORDINE);
					PreparedStatement ps2 = conn.prepareStatement(STMT_DELETE_ORDINI_RIGHE);
					) {
				conn.setAutoCommit(false); 

				ps1.setString(1, ord_ordine);
				int ris1 = ps1.executeUpdate();

				ps2.setString(1, ord_ordine);
				int ris2 = ps2.executeUpdate();

				if (ris1 > 0 && ris2 > 0) {
					conn.commit();
					return true;
				} else {
					conn.rollback(); 
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return false;
	}

	protected Articolo checkArticolo(YExpOrdiniRighe riga) {
		try {
			return (Articolo) Articolo.elementWithKey(Articolo.class, KeyHelper.buildObjectKey(new String[] {
					Azienda.getAziendaCorrente(),
					riga.getRig_articolo()
			}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	protected DocMagTrasferimento creaDocumentoTrasferimento(String idMagazzinoPartenza,String idMagazzinoArrivo) {
		DocMagTrasferimento docTrasf = (DocMagTrasferimento) Factory.createObject(DocMagTrasferimento.class);
		docTrasf.setIdAzienda(Azienda.getAziendaCorrente());
		docTrasf.setIdCau(persDatiModula.getRelcaudoctrasf().getIdCausale()); //da pers dati
		docTrasf.getNumeratoreHandler().setIdNumeratore(persDatiModula.getRelseriedoctrasf().getIdNumeratore()); //da pers dati
		docTrasf.getNumeratoreHandler().setIdSerie(persDatiModula.getRelseriedoctrasf().getIdSerie()); //da pers dati
		docTrasf.setDataDocRiferimento(TimeUtils.getCurrentDate());
		docTrasf.setIdMagazzino(idMagazzinoPartenza); //partenza
		docTrasf.setCodiceMagazzinoArrivo(idMagazzinoArrivo); //arrivo
		return docTrasf;
	}

	protected DocMagTrasferimentoRiga creaDocumentoTrasferimentoRiga(DocMagTrasferimento docTrasf, YExpOrdiniRighe riga, Articolo articolo) {
		DocMagTrasferimentoRiga docTrasfRiga = (DocMagTrasferimentoRiga) Factory.createObject(DocMagTrasferimentoRiga.class);
		docTrasfRiga.setIdAzienda(Azienda.getAziendaCorrente());
		docTrasfRiga.setTestata(docTrasf);
		docTrasfRiga.setCausaleRiga(docTrasf.getCausale().getCausaleRiga());
		docTrasfRiga.setArticolo(articolo);
		docTrasfRiga.setDataDocRiferimento(TimeUtils.getCurrentDate());
		docTrasfRiga.setDataRegistrazione(TimeUtils.getCurrentDate());
		docTrasfRiga.completaBO();
		docTrasfRiga.getQuantita().setQuantitaInUMPrm(riga.getRig_qtaE());
		docTrasfRiga.setUMPrm(articolo.getUMPrmMag());
		docTrasfRiga.setOperatoreConversioneUM(articolo.getOperConverUM());
		docTrasfRiga.setStatoAvanzamento(StatoAvanzamento.DEFINITIVO);
		//gestioneRigheLotto(docTrasfRiga,docTrasf);
		return docTrasfRiga;
	}

	//	protected void gestioneRigheLotto(DocMagTrasferimentoRiga docTrasfRiga, DocMagTrasferimento docTrasf) {
	//		DocMagTrasfRigaLotto docTrasfRigLotto = (DocMagTrasfRigaLotto) Factory.createObject(DocMagTrasfRigaLotto.class);
	//		docTrasfRigLotto.setIdAzienda(Azienda.getAziendaCorrente());
	//		docTrasfRigLotto.setDocumentoBaseRiga(docTrasfRiga);
	//		//docTrasfRigLotto.setIdLotto(rigaLotto.getIdLotto());
	//		//docTrasfRigLotto.setIdArticolo(riga.getIdArticolo());
	//		//docTrasfRigLotto.setQuantitaInUMPrm(rigaLotto.getQtaRicevuta().getQuantitaInUMPrm());
	//		//docTrasfRiga.getRigheLotto().add(docTrasfRigLotto);
	//	}
	//	

	protected List<YExpOrdini> getListaExpOrdini(){
		YResultSetIteratoExpOrdini iterator = null;
		List<YExpOrdini> lst = new ArrayList<YExpOrdini>();
		try {
			Connection connection = YModulaConnection.getModulaConnection();
			ResultSet rs = null;
			try (
					Connection conn = connection;
					PreparedStatement ps = conn.prepareStatement(STMT_SELECT_ORDINI);
					) {
				rs = ps.executeQuery();
				iterator = new YResultSetIteratoExpOrdini(rs);
				while(iterator.hasNext()) {
					lst.add((YExpOrdini) iterator.next());
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

	protected List<YExpOrdiniRighe> getListaExpOrdiniRighe(String idNumeroOrdine){
		YResultSetIteratoExpOrdiniRighe iterator = null;
		List<YExpOrdiniRighe> lst = new ArrayList<YExpOrdiniRighe>();
		try {
			Connection connection = YModulaConnection.getModulaConnection();
			ResultSet rs = null;
			try (
					Connection conn = connection;
					PreparedStatement ps = conn.prepareStatement(STMT_SELECT_ORDINI_RIGHE)
					) {
				ps.setString(1, idNumeroOrdine);
				rs = ps.executeQuery();
				iterator = new YResultSetIteratoExpOrdiniRighe(rs);
				while(iterator.hasNext()) {
					lst.add((YExpOrdiniRighe) iterator.next());
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
		if(persDatiModula.getRelseriedoctrasf() == null) {
			output.println("Non e' stata definita una serie per il Documento di Trasferimento \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		if(persDatiModula.getRelcaudoctrasf() == null) {
			output.println("Non e' stata definita una causale per il Documento di Trasferimento \n "
					+ " e' necessaria definirne una prima di lanciare il lavoro! ");
			return false;
		}
		return true;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YRicMovMODULA";
	}

}
