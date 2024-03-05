package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.thera.thip.magazzino.documenti.DocMagTrasferimento;
import it.thera.thip.magazzino.documenti.DocMagTrasferimentoRiga;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;
import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.modula.esportazione.YGestoreEsportazioneModula;
import it.sicons.ag.produzione.mancanti.ParametriUtils;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;

public class YDocTraToModula extends YDocTraToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YDocTraToModula() {
		super();
	}

	public YDocTraToModula(DocMagTrasferimentoRiga riga) throws SQLException {
		YDocTraToModula vTm = (YDocTraToModula) Factory.createObject(YDocTraToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoDocTra(riga.getAnnoDocumento());
		vTm.setRNumeroDocTra(riga.getNumeroDocumento());
		vTm.setRRigaDoc(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaDoc(riga.getDettaglioRigaDocumento());
		vTm.setRArticolo(riga.getIdArticolo());

		if("MOD".equals(riga.getIdMagazzino()) && "001".equals(riga.getCodiceMagazzinoArrivo())) {
			vTm.setQtaOriginale(riga.getQuantita().getQuantitaInUMPrm());
		}else if("001".equals(riga.getIdMagazzino()) && "MOD".equals(riga.getCodiceMagazzinoArrivo())){
			vTm.setQtaOriginale(riga.getQuantita().getQuantitaInUMPrm()); //non ce' la rif
		}

		BigDecimal qtaGiaEvasa = vTm.cercaQtaGiaEvasa();
		vTm.setQtaEvasa(qtaGiaEvasa); //cercare qta evasa in YPANTH_TO_MODULA


		vTm.setQtaResidua(vTm.getQtaOriginale().subtract(vTm.getQtaEvasa()));
		vTm.setQtaDaEvadere(vTm.getQtaResidua());
		String magazzinoSaldo = null;
		if("MOD".equals(riga.getIdMagazzino()) && "001".equals(riga.getCodiceMagazzinoArrivo())) {
			magazzinoSaldo = "001";
		}else if("001".equals(riga.getIdMagazzino()) && "MOD".equals(riga.getCodiceMagazzinoArrivo())){
			magazzinoSaldo = "MOD";
		}
		String[] keySaldo = {
				riga.getIdAzienda(),
				magazzinoSaldo,
				riga.getIdArticolo(),
				Integer.toString(riga.getIdVersioneRcs() != null ? riga.getIdVersioneRcs() : 1),
				Integer.toString(riga.getIdConfigurazione() != null ? riga.getIdConfigurazione() : 0),
				riga.getIdOperazione() != null ? riga.getIdOperazione() : "DUMMY"};
		SaldoMag saldo = SaldoMag.elementWithKey(KeyHelper.buildObjectKey(keySaldo), 0);
		if(saldo != null)
			vTm.setGiacenza(saldo.giacenzaNetta().getQuantitaInUMPrm());
		vTm.save();
	}

	protected BigDecimal cercaQtaGiaEvasa() throws SQLException {
		BigDecimal qta = BigDecimal.ZERO;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_TRASFERIMENTO);
		pTm.setIdAnnoDoc(this.getRAnnoDocTra());
		pTm.setIdNumeroDoc(this.getRNumeroDocTra());
		pTm.setIdRigaDoc(this.getRRigaDoc());
		pTm.setIdDetRigaDoc(this.getRDetRigaDoc());
		if(pTm.retrieve()) {
			qta = pTm.getQtaEvasaUmPrm();
		}
		return qta;
	}

	/**
	 * 
	 * @param keyDocVen = chiave dell'ordine di vendita di cui vanno create le righe che poi si vorranno passare a modula
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public static void creaRighePerDocumentoTrasferimento(String keyDocVen) throws SQLException {
		DocMagTrasferimento docTrasf = (DocMagTrasferimento) DocMagTrasferimento.elementWithKey(DocMagTrasferimento.class, keyDocVen, 0);
		if(docTrasf != null) {
			List<YDocTraToModula> righeDocVenToModula = new ArrayList<YDocTraToModula>();
			List<DocMagTrasferimentoRiga> righe = docTrasf.getRighe();
			for(DocMagTrasferimentoRiga riga : righe) {
				if ("MO".equals(riga.getArticolo().getIdClasseA())
						&& ("MOD".equals(riga.getIdMagazzino()) && "001".equals(riga.getCodiceMagazzinoArrivo()))
						 || "001".equals(riga.getIdMagazzino()) && "MOD".equals(riga.getCodiceMagazzinoArrivo())) {

					YDocTraToModula rigaDocVenToModula = new YDocTraToModula(riga);
					righeDocVenToModula.add(rigaDocVenToModula);
				}
			}
			if(!righeDocVenToModula.isEmpty())
				ConnectionManager.commit(); //committo le righe che ho salvato nei costruttori
		}
	}

	/**
	 * 
	 * @param keyDocVen = chiave dell'ordine di vendita di cui verranno cancellate tutte le righe in THIPPERS.YORD_VEN_TO_MODULA
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static int cancellaRigheDocumentoTrasferimento(String keyDocVen) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyDocVen.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YDocTraToModulaTM.R_ANNO_DOC_TRA+" = '"+c[1]+"' AND "+YDocTraToModulaTM.R_NUMERO_DOC_TRA+" = '"+c[2]+"' ";
		List<YDocTraToModula> records = YDocTraToModula.retrieveList(YDocTraToModula.class,where, "", false);
		for(YDocTraToModula record : records) {
			record.delete();
			recordCancellati++;
		}
		if(recordCancellati > 0)
			ConnectionManager.commit();
		return recordCancellati;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>Prima stesura:<br>
	 * L'utente una volta selezionati N {@link YDocTraToModula} record dalla griglia, vuole invarli a modula.<br>
	 * Viene quindi inserito un record per la testata tramite {@link #STMT_INSERT_IMP_ORDINI}.<br>
	 * Per ogni riga selezionata invece viene inserito un record in riga {@link #STMT_INSERT_IMP_ORDINI_RIG}.<br>
	 * </p>
	 * @param objectKeys
	 * @return
	 */
	public static ErrorMessage inviaAModulaMultple(String[] objectKeys) {
		ErrorMessage em = null;
		Connection connection = null;
		try {
			List<YDocTraToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0));
					if(em == null) {
						for (YDocTraToModula riga : rows) {
							DocMagTrasferimentoRiga docTrasfRig = getDocumentoTrasferimentoRigaDB(
									riga.getIdAzienda(), 
									riga.getRAnnoDocTra(), 
									riga.getRNumeroDocTra(), riga.getRRigaDoc().toString(),
									riga.getRDetRigaDoc().toString());
							char tipoMovim = TipoMovimentoModula.PRELIEVO;
							if("MOD".equals(docTrasfRig.getIdMagazzino()) && "001".equals(docTrasfRig.getCodiceMagazzinoArrivo())) {
								tipoMovim = TipoMovimentoModula.PRELIEVO;
							}else if("001".equals(docTrasfRig.getIdMagazzino()) && "MOD".equals(docTrasfRig.getCodiceMagazzinoArrivo())){
								tipoMovim = TipoMovimentoModula.VERSAMENTO;
							}
							em = riga.inviaAModula(numeroListaModula, connection,tipoMovim);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YDocTraToModula.cancellaRigheDocumentoTrasferimento(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoDocTra(),rows.get(0).getRNumeroDocTra()}));
					}else {
						return em;
					}	
				}
			}else {
				em = new ErrorMessage("YSOF3_001","Non e' presente nessuna riga da esportare, le quantita' sono gia' state evase completamente!");
				return em;
			}
		}catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			if(e instanceof ModulaConnectionException)
				em = new ErrorMessage("BAS0000036",e.getMessage());
			if(em == null && e instanceof SQLException)
				em = CreaMessaggioErrore.daRcAErrorMessage(-9999, (SQLException) e);
			e.printStackTrace(Trace.excStream);
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}
		return em;
	}

	public static List<YDocTraToModula> getListaRigheDaInviare(String[] keys){
		List<YDocTraToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YDocTraToModula riga = YDocTraToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YDocTraToModula vTm) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection, char tipoMovimento) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_TRASFERIMENTO);
		pTm.setIdAnnoDoc(this.getRAnnoDocTra());
		pTm.setIdNumeroDoc(this.getRNumeroDocTra());
		pTm.setIdRigaDoc(this.getRRigaDoc());
		pTm.setIdDetRigaDoc(this.getRDetRigaDoc());
		boolean exists = pTm.retrieve();
		if(!exists) {
			pTm.setQtaEvasaUmPrm(BigDecimal.ZERO);
		}
		pTm.setTipoMov(tipoMovimento);
		pTm.setQtaEvasaUmPrm(pTm.getQtaEvasaUmPrm().add(this.getQtaDaEvadere()));
		int rc = pTm.save();
		if(rc < 0)
			em = CreaMessaggioErrore.daRcAErrorMessage(rc, null);
		else {
			try {
				boolean esportaArticolo;
				switch (tipoMovimento) {
				case TipoMovimentoModula.PRELIEVO:
					esportaArticolo = false;
					break;
				case TipoMovimentoModula.VERSAMENTO:
					esportaArticolo = true;
					break;
				default:
					esportaArticolo = false;
					break;
				}
				em = scriviRigaDBModula(numeroListaModula, connection,esportaArticolo);
				if(em != null) {
					return em;
				}
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;		
	}

	protected ErrorMessage scriviRigaDBModula(String numeroListaModula,Connection connection, boolean esportaArticolo) throws SQLException {
		ErrorMessage em = null;
		String lineNumber = this.getRRigaDoc().toString().concat("#").concat(this.getRDetRigaDoc().toString());
		String idArticolo = this.getRArticolo();
		BigDecimal qta = this.getQtaDaEvadere();
		int ris = 0;
		try {
			if(getRelarticolo() instanceof YArticolo
					&& !((YArticolo)getRelarticolo()).isEsportatoModula()
					&& esportaArticolo) {
				ris = YArticolo.esportaArticoloVersoModula(connection, (YArticolo) this.getRelarticolo());
				if(ris > 0)
					ris += YArticolo.aggiornaStatoEsportazioneModulaArticolo(idArticolo, true);
			}
			if(ris <= 0) {
				return new ErrorMessage("YSOF3_001","Impossibile esportare il nuovo articolo verso modula");
			}
			ris = YGestoreEsportazioneModula.esportaRigaOrdine(connection, numeroListaModula, idArticolo, null, qta, lineNumber, null);
			if(ris <= 0) {
				em = new ErrorMessage("");
			}
		} catch (SQLException e) {
			em = CreaMessaggioErrore.daRcAErrorMessage(-9999, (SQLException) e);
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YDocTraToModula vTm){
		ErrorMessage em = null;
		DocMagTrasferimento testata = getDocumentoTrasferimentoDB(vTm.getIdAzienda(),vTm.getRAnnoDocTra(),vTm.getRNumeroDocTra());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = "DocTrasfGen";
		String descrizioneLista = "[V]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
		int ris;
		try {
			ris = YGestoreEsportazioneModula.esportaTestataOrdine(connection, numeroListaModula, descrizioneLista, TipoMovimentoModula.PRELIEVO, null);
			if(ris <= 0) {
				em = new ErrorMessage("");
			}
		} catch (SQLException e) {
			em = CreaMessaggioErrore.daRcAErrorMessage(-9999, (SQLException) e);
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	protected static DocMagTrasferimento getDocumentoTrasferimentoDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (DocMagTrasferimento) DocMagTrasferimento.elementWithKey(DocMagTrasferimento.class,
					KeyHelper.buildObjectKey(new String[] {
							idAzienda,
							idAnnoOrd,
							idNumeroOrd
					}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}
	
	protected static DocMagTrasferimentoRiga getDocumentoTrasferimentoRigaDB(String idAzienda,String idAnnoOrd,String idNumeroOrd,String idRiga,String idDetRiga) {
		try {
			return (DocMagTrasferimentoRiga) DocMagTrasferimentoRiga.elementWithKey(DocMagTrasferimentoRiga.class,
					KeyHelper.buildObjectKey(new String[] {
							idAzienda,
							idAnnoOrd,
							idNumeroOrd,
							idRiga,
							idDetRiga
					}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}


}

