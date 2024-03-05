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

import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.modula.esportazione.YGestoreEsportazioneModula;
import it.sicons.ag.produzione.mancanti.ParametriUtils;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloDatiIdent;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaPrm;
import it.thera.thip.vendite.documentoVE.DocumentoVenRigaSec;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.DocumentoVenditaRiga;
import it.thera.thip.vendite.generaleVE.TipoDocumento;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

public class YDocVenToModula extends YDocVenToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YDocVenToModula() {
		super();
	}

	public YDocVenToModula(DocumentoVenditaRiga riga) throws SQLException {
		YDocVenToModula vTm = (YDocVenToModula) Factory.createObject(YDocVenToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoDocVen(riga.getAnnoDocumento());
		vTm.setRNumeroDocVen(riga.getNumeroDocumento());
		vTm.setRRigaDoc(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaDoc(riga.getDettaglioRigaDocumento());
		vTm.setRArticolo(riga.getIdArticolo());

		vTm.setQtaOriginale(riga.getQtaSpedita().getQuantitaInUMRif());

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
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_VENDITA);
		pTm.setIdAnnoDoc(this.getRAnnoDocVen());
		pTm.setIdNumeroDoc(this.getRNumeroDocVen());
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
	public static void creaRighePerDocumentoVendita(String keyDocVen) throws SQLException {
		DocumentoVendita DocVen = (DocumentoVendita) DocumentoVendita.elementWithKey(DocumentoVendita.class, keyDocVen, 0);
		if(DocVen != null && DocVen.getTipoDocumento() == TipoDocumento.NOTA_ACCREDITO) {
			List<YDocVenToModula> righeDocVenToModula = new ArrayList<YDocVenToModula>();
			List<DocumentoVenRigaPrm> righe = DocVen.getRighe();
			for(DocumentoVenRigaPrm riga : righe) {
				if ("001".equals(riga.getIdMagazzino())
						&& !riga.getCausaleRiga().isRigaMerceAValore() 
						&& (riga.getTipoRiga() == TipoRiga.MERCE || riga.getTipoRiga() == TipoRiga.OMAGGIO)
						&& "MO".equals(riga.getArticolo().getIdClasseA())) {

					Articolo art = riga.getArticolo();
					switch(art.getTipoParte()) {
					case ArticoloDatiIdent.KIT_NON_GEST:
						/*
						 Se un riga primaria è intestata ad un articolo che ha tipo parte “kit non gestito a
						 magazzino” allora la riga primaria non viene mostrata. Vengono mostrate però le
						 relative righe secondarie
						 */
						List<DocumentoVenRigaSec> righeSec = riga.getRigheSecondarie();
						for(DocumentoVenRigaSec rigaSec : righeSec) {
							YDocVenToModula rigaDocVenToModula = new YDocVenToModula(rigaSec);
							righeDocVenToModula.add(rigaDocVenToModula);
						}
						break;					
					case ArticoloDatiIdent.KIT_GEST:
						/*
						Se un riga primaria è intestata ad un articolo che ha tipo parte “kit gestito a magazzino”
						allora la riga primaria viene mostrata. Vengono però omesse le relative righe
						secondarie.
						 */
						YDocVenToModula rigaDocVenToModula = new YDocVenToModula(riga);
						righeDocVenToModula.add(rigaDocVenToModula);
						break;
					default:
						rigaDocVenToModula = new YDocVenToModula(riga);
						righeDocVenToModula.add(rigaDocVenToModula);
						break;
					}
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
	public static int cancellaRigheDocumentoVendita(String keyDocVen) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyDocVen.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YDocVenToModulaTM.R_ANNO_DOC_VEN+" = '"+c[1]+"' AND "+YDocVenToModulaTM.R_NUMERO_DOC_VEN+" = '"+c[2]+"' ";
		List<YDocVenToModula> records = YDocVenToModula.retrieveList(YDocVenToModula.class,where, "", false);
		for(YDocVenToModula record : records) {
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
	 * L'utente una volta selezionati N {@link YDocVenToModula} record dalla griglia, vuole invarli a modula.<br>
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
			List<YDocVenToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0));
					if(em == null) {
						for (YDocVenToModula riga : rows) {
							em = riga.inviaAModula(numeroListaModula, connection);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YDocVenToModula.cancellaRigheDocumentoVendita(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoDocVen(),rows.get(0).getRNumeroDocVen()}));
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

	public static List<YDocVenToModula> getListaRigheDaInviare(String[] keys){
		List<YDocVenToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YDocVenToModula riga = YDocVenToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YDocVenToModula vTm) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.DOCUMENTO_VENDITA);
		pTm.setIdAnnoDoc(this.getRAnnoDocVen());
		pTm.setIdNumeroDoc(this.getRNumeroDocVen());
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

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YDocVenToModula vTm){
		ErrorMessage em = null;
		DocumentoVendita testata = getDocumentoVenditaDB(vTm.getIdAzienda(),vTm.getRAnnoDocVen(),vTm.getRNumeroDocVen());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getCliente().getIdCliente();
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

	protected static DocumentoVendita getDocumentoVenditaDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (DocumentoVendita) DocumentoVendita.elementWithKey(DocumentoVendita.class,
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

