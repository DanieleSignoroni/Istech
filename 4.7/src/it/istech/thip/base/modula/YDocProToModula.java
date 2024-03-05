package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.documento.DocumentoProduzione;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;
import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.modula.esportazione.YGestoreEsportazioneModula;
import it.sicons.ag.produzione.mancanti.ParametriUtils;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YDocProToModula extends YDocProToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YDocProToModula() {
		super();
	}

	public YDocProToModula(DocumentoPrdRigaVersamento riga) throws SQLException {
		YDocProToModula vTm = (YDocProToModula) Factory.createObject(YDocProToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoDocPro(riga.getAnnoDocumento());
		vTm.setRNumeroDocPro(riga.getNumeroDocumento());
		vTm.setRRigaDoc(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaDoc(riga.getDettaglioRigaDocumento() != null ? riga.getDettaglioRigaDocumento() : 0);
		vTm.setRArticolo(riga.getRArticolo());

		vTm.setQtaOriginale(riga.getQtaVersataUMPrmPrd());

		BigDecimal qtaGiaEvasa = vTm.cercaQtaGiaEvasa();
		vTm.setQtaEvasa(qtaGiaEvasa); //cercare qta evasa in YPANTH_TO_MODULA


		vTm.setQtaResidua(vTm.getQtaOriginale().subtract(vTm.getQtaEvasa()));
		vTm.setQtaDaEvadere(vTm.getQtaResidua());
		
		String[] keySaldo = {riga.getIdAzienda(),"MOD",riga.getRArticolo(),Integer.toString(riga.getIdVersioneRcs()), Integer.toString(riga.getIdConfigurazione() != null ? riga.getIdConfigurazione() : 0), "DUMMY"};
		SaldoMag saldo = SaldoMag.elementWithKey(KeyHelper.buildObjectKey(keySaldo), 0);
		if(saldo != null)
			vTm.setGiacenza(saldo.giacenzaNetta().getQuantitaInUMPrm());
		vTm.save();
	}

	protected BigDecimal cercaQtaGiaEvasa() throws SQLException {
		BigDecimal qta = BigDecimal.ZERO;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_PRODUZIONE);
		pTm.setIdAnnoDoc(this.getRAnnoDocPro());
		pTm.setIdNumeroDoc(this.getRNumeroDocPro());
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
	public static void creaRighePerDocumentoProduzione(String keyDocVen) throws SQLException {
		DocumentoProduzione docProd = (DocumentoProduzione) DocumentoProduzione.elementWithKey(DocumentoProduzione.class, keyDocVen, 0);
		if(docProd != null) {
			List<YDocProToModula> righeDocVenToModula = new ArrayList<YDocProToModula>();
			List<DocumentoPrdRigaVersamento> righe = docProd.getVersamentiColl();
			for(DocumentoPrdRigaVersamento riga : righe) {
				if ("MO".equals(riga.getArticolo().getIdClasseA())) {

					YDocProToModula rigaDocVenToModula = new YDocProToModula(riga);
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
	public static int cancellaRigheDocumentoProduzione(String keyDocVen) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyDocVen.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YDocProToModulaTM.R_ANNO_DOC_PRO+" = '"+c[1]+"' AND "+YDocProToModulaTM.R_NUMERO_DOC_PRO+" = '"+c[2]+"' ";
		List<YDocProToModula> records = YDocProToModula.retrieveList(YDocProToModula.class,where, "", false);
		for(YDocProToModula record : records) {
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
	 * L'utente una volta selezionati N {@link YDocProToModula} record dalla griglia, vuole invarli a modula.<br>
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
			List<YDocProToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0));
					if(em == null) {
						for (YDocProToModula riga : rows) {
							em = riga.inviaAModula(numeroListaModula, connection);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YDocProToModula.cancellaRigheDocumentoProduzione(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoDocPro(),rows.get(0).getRNumeroDocPro()}));
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

	public static List<YDocProToModula> getListaRigheDaInviare(String[] keys){
		List<YDocProToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YDocProToModula riga = YDocProToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YDocProToModula vTm) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_PRODUZIONE);
		pTm.setIdAnnoDoc(this.getRAnnoDocPro());
		pTm.setIdNumeroDoc(this.getRNumeroDocPro());
		pTm.setIdRigaDoc(this.getRRigaDoc());
		pTm.setIdDetRigaDoc(this.getRDetRigaDoc());
		boolean exists = pTm.retrieve();
		if(!exists) {
			pTm.setQtaEvasaUmPrm(BigDecimal.ZERO);
		}
		pTm.setTipoMov(TipoMovimentoModula.VERSAMENTO);
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

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YDocProToModula vTm){
		ErrorMessage em = null;
		DocumentoProduzione testata = getDocumentoProduzioneDB(vTm.getIdAzienda(),vTm.getRAnnoDocPro(),vTm.getRNumeroDocPro());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getKey();
		String descrizioneLista = "["+String.valueOf(TipoMovimentoModula.VERSAMENTO)+"]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
		int ris;
		try {
			ris = YGestoreEsportazioneModula.esportaTestataOrdine(connection, numeroListaModula, descrizioneLista, TipoMovimentoModula.VERSAMENTO, null);
			if(ris <= 0) {
				em = new ErrorMessage("");
			}
		} catch (SQLException e) {
			em = CreaMessaggioErrore.daRcAErrorMessage(-9999, (SQLException) e);
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	protected static DocumentoProduzione getDocumentoProduzioneDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (DocumentoProduzione) DocumentoProduzione.elementWithKey(DocumentoProduzione.class,
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

}

