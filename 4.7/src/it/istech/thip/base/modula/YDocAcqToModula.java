package it.istech.thip.base.modula;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.istech.thip.acquisti.documentoAC.YDocumentoAcqRigaPrm;
import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.modula.esportazione.YGestoreEsportazioneModula;
import it.sicons.ag.produzione.mancanti.ParametriUtils;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquistoRiga;
import it.thera.thip.acquisti.generaleAC.TipoDocumentoAcq;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YDocAcqToModula extends YDocAcqToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YDocAcqToModula() {
		super();
	}

	public YDocAcqToModula(DocumentoAcquistoRiga riga) throws SQLException {
		YDocAcqToModula vTm = (YDocAcqToModula) Factory.createObject(YDocAcqToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoDocAcq(riga.getAnnoDocumento());
		vTm.setRNumeroDocAcq(riga.getNumeroDocumento());
		vTm.setRRigaDoc(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaDoc(riga.getDettaglioRigaDocumento());
		vTm.setRArticolo(riga.getIdArticolo());

		//Se acquisto allora la quantita' e' la ricevuta
		//Se e' reso fornitore la quantita' e' QTA_ACQ_UM_PRM
		if(((DocumentoAcquisto)riga.getTestata()).getTipoDocumento() == TipoDocumentoAcq.ACQUISTO)
			vTm.setQtaOriginale(riga.getQtaRicevuta().getQuantitaInUMRif());
		else if(((DocumentoAcquisto)riga.getTestata()).getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE) {
			vTm.setQtaOriginale(riga.getQtaRicevuta().getQuantitaInUMRif());
		}

		BigDecimal qtaGiaEvasa = vTm.cercaQtaGiaEvasa();
		vTm.setQtaEvasa(qtaGiaEvasa); //cercare qta evasa in YPANTH_TO_MODULA

		vTm.setQtaResidua(vTm.getQtaOriginale().subtract(vTm.getQtaEvasa()));
		vTm.setQtaDaEvadere(vTm.getQtaResidua());

		String[] keySaldo = {riga.getIdAzienda(),"001",riga.getIdArticolo(),Integer.toString(riga.getIdVersioneRcs()), Integer.toString(riga.getIdConfigurazione() != null ? riga.getIdConfigurazione() : 0), riga.getIdOperazione() != null ? riga.getIdOperazione() : "DUMMY"};
		SaldoMag saldo = SaldoMag.elementWithKey(KeyHelper.buildObjectKey(keySaldo), 0);
		if(saldo != null)
			vTm.setGiacenza(saldo.giacenzaNetta().getQuantitaInUMPrm());
		vTm.save();
	}

	protected BigDecimal cercaQtaGiaEvasa() throws SQLException {
		BigDecimal qta = BigDecimal.ZERO;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_ACQUISTO);
		pTm.setIdAnnoDoc(this.getRAnnoDocAcq());
		pTm.setIdNumeroDoc(this.getRNumeroDocAcq());
		pTm.setIdRigaDoc(this.getRRigaDoc());
		pTm.setIdDetRigaDoc(this.getRDetRigaDoc());
		if(pTm.retrieve()) {
			qta = pTm.getQtaEvasaUmPrm();
		}
		return qta;
	}

	/**
	 * 
	 * @param keyOrdAcq = chiave dell'ordine di vendita di cui vanno create le righe che poi si vorranno passare a modula
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public static void creaRighePerDocumentoAcquisto(String keyDocAcq) throws SQLException {
		DocumentoAcquisto docAcq = (DocumentoAcquisto) DocumentoAcquisto.elementWithKey(DocumentoAcquisto.class, keyDocAcq, 0);
		if(docAcq != null
				&& (docAcq.getTipoDocumento() == TipoDocumentoAcq.ACQUISTO || docAcq.getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE) ) {
			List<YDocAcqToModula> righeOrdAcqToModula = new ArrayList<YDocAcqToModula>();
			List<YDocumentoAcqRigaPrm> righe = docAcq.getRighe();
			for(YDocumentoAcqRigaPrm riga : righe) {
				if (riga.checkMagazzinoModula() 
						&& !riga.checkIsRigaMerceAValore() 
						&& riga.checkIsMerceOrOmaggio() 
						&& riga.checkIsClasseAMO()
						&& !riga.isGestioneCaliTriangulazione()) {
					YDocAcqToModula rigaDocAcqToModula = new YDocAcqToModula(riga);
					righeOrdAcqToModula.add(rigaDocAcqToModula);
				}
			}
			if(!righeOrdAcqToModula.isEmpty())
				ConnectionManager.commit(); //committo le righe che ho salvato nei costruttori
		}
	}

	/**
	 * 
	 * @param keyOrdVen = chiave dell'ordine di vendita di cui verranno cancellate tutte le righe in THIPPERS.YORD_VEN_TO_MODULA
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static int cancellaRigheDocumentoAcquisto(String keyDocAcq) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyDocAcq.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YDocAcqToModulaTM.R_ANNO_DOC_ACQ+" = '"+c[1]+"' AND "+YDocAcqToModulaTM.R_NUMERO_DOC_ACQ+" = '"+c[2]+"' ";
		List<YDocAcqToModula> records = YDocAcqToModula.retrieveList(YDocAcqToModula.class,where, "", false);
		for(YDocAcqToModula record : records) {
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
	 * L'utente una volta selezionati N {@link YOrdVenToModula} record dalla griglia, vuole invarli a modula.<br>
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
			List<YDocAcqToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String[] keyParts = KeyHelper.unpackObjectKey(objectKeys[0]);
				DocumentoAcquisto docAcq = getDocumentoAcquistoDB(keyParts[0], keyParts[1], keyParts[2]);
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0), docAcq);
					if(em == null) {
						for (YDocAcqToModula riga : rows) {
							em = riga.inviaAModula(numeroListaModula, connection,docAcq);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YOrdVenToModula.cancellaRigheOrdineVendita(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoDocAcq(),rows.get(0).getRNumeroDocAcq()}));
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

	public static List<YDocAcqToModula> getListaRigheDaInviare(String[] keys){
		List<YDocAcqToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YDocAcqToModula riga = YDocAcqToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YDocAcqToModula vTm,DocumentoAcquisto docAcq) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm,docAcq);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection, DocumentoAcquisto docAcq) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_ACQUISTO);
		pTm.setIdAnnoDoc(this.getRAnnoDocAcq());
		pTm.setIdNumeroDoc(this.getRNumeroDocAcq());
		pTm.setIdRigaDoc(this.getRRigaDoc());
		pTm.setIdDetRigaDoc(this.getRDetRigaDoc());
		boolean exists = pTm.retrieve();
		if(!exists) {
			pTm.setQtaEvasaUmPrm(BigDecimal.ZERO);
		}
		if(docAcq.getTipoDocumento() == TipoDocumentoAcq.ACQUISTO) {
			pTm.setTipoMov(TipoMovimentoModula.VERSAMENTO); //carico dopo BEM
		}else if(docAcq.getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE) {
			pTm.setTipoMov(TipoMovimentoModula.PRELIEVO); //prelievo perch'e mando merce indietro
		}
		pTm.setQtaEvasaUmPrm(pTm.getQtaEvasaUmPrm().add(this.getQtaDaEvadere()));
		int rc = pTm.save();
		if(rc < 0)
			em = CreaMessaggioErrore.daRcAErrorMessage(rc, null);
		else {
			try {
				em = scriviRigaDBModula(numeroListaModula, connection);
				if(em != null) {
					return em;
				}
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;		
	}

	protected ErrorMessage scriviRigaDBModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		String lineNumber = this.getRRigaDoc().toString().concat("#").concat(this.getRDetRigaDoc().toString());
		String idArticolo = this.getRArticolo();
		BigDecimal qta = this.getQtaDaEvadere();
		int ris = 0;
		try {
			if(getRelarticolo() instanceof YArticolo
					&& !((YArticolo)getRelarticolo()).isEsportatoModula()) {
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

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YDocAcqToModula vTm, DocumentoAcquisto docAcq){
		ErrorMessage em = null;
		DocumentoAcquisto testata = getDocumentoAcquistoDB(vTm.getIdAzienda(),vTm.getRAnnoDocAcq(),vTm.getRNumeroDocAcq());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getFornitore().getIdFornitore();
		String descrizioneLista = null;
		if(docAcq.getTipoDocumento() == TipoDocumentoAcq.ACQUISTO) {
			descrizioneLista = "["+String.valueOf(TipoMovimentoModula.VERSAMENTO)+"]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
		}else if(docAcq.getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE) {
			descrizioneLista = "["+String.valueOf(TipoMovimentoModula.PRELIEVO)+"]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
		}
		int ris = 0;
		try {
			if(docAcq.getTipoDocumento() == TipoDocumentoAcq.ACQUISTO) {
				ris = YGestoreEsportazioneModula.esportaTestataOrdine(connection, numeroListaModula, descrizioneLista, TipoMovimentoModula.VERSAMENTO, null);
			}else if(docAcq.getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE) {
				ris = YGestoreEsportazioneModula.esportaTestataOrdine(connection, numeroListaModula, descrizioneLista, TipoMovimentoModula.PRELIEVO, null);
			}
			if(ris <= 0) {
				em = new ErrorMessage("");
			}
		} catch (SQLException e) {
			em = CreaMessaggioErrore.daRcAErrorMessage(-9999, (SQLException) e);
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	protected static DocumentoAcquisto getDocumentoAcquistoDB(String idAzienda,String idAnnoDoc,String idNumeroDoc) {
		try {
			return (DocumentoAcquisto) DocumentoAcquisto.elementWithKey(DocumentoAcquisto.class,
					KeyHelper.buildObjectKey(new String[] {
							idAzienda,
							idAnnoDoc,
							idNumeroDoc
					}), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}
}

