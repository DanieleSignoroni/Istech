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

import it.istech.thip.acquisti.ordineAC.YOrdineAcquistoRigaPrm;
import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.modula.esportazione.YGestoreEsportazioneModula;
import it.sicons.ag.produzione.mancanti.ParametriUtils;
import it.thera.thip.acquisti.ordineAC.OrdineAcquisto;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRiga;
import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaSec;
import it.thera.thip.base.comuniVenAcq.TipoRiga;
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

public class YOrdAcqToModula extends YOrdAcqToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YOrdAcqToModula() {
		super();
	}

	public YOrdAcqToModula(OrdineAcquistoRiga riga) throws SQLException {
		YOrdAcqToModula vTm = (YOrdAcqToModula) Factory.createObject(YOrdAcqToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoOrdAcq(riga.getAnnoDocumento());
		vTm.setRNumeroOrdAcq(riga.getNumeroDocumento());
		vTm.setRRigaOrd(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaOrd(riga.getDettaglioRigaDocumento());
		vTm.setRArticolo(riga.getIdArticolo());

		vTm.setQtaOriginale(riga.getQtaInUMPrmMag());

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
		pTm.setTipoDoc(TipoDocumentoModula.ORDINE_ACQUISTO);
		pTm.setIdAnnoDoc(this.getRAnnoOrdAcq());
		pTm.setIdNumeroDoc(this.getRNumeroOrdAcq());
		pTm.setIdRigaDoc(this.getRRigaOrd());
		pTm.setIdDetRigaDoc(this.getRDetRigaOrd());
		if(pTm.retrieve()) {
			qta = pTm.getQtaEvasaUmPrm();
		}
		return qta;
	}

	
	@SuppressWarnings("unchecked")
	public static void creaRighePerOrdineAcquisto(String keyOrdAcq) throws SQLException {
		OrdineAcquisto ordAcq = (OrdineAcquisto) OrdineAcquisto.elementWithKey(OrdineAcquisto.class, keyOrdAcq, 0);
		if(ordAcq != null && ordAcq.getCausale().isLavEsterna()) { //solo se e' Conto Lavoro
			List<YOrdAcqToModula> righeOrdAcqToModula = new ArrayList<YOrdAcqToModula>();
			List<YOrdineAcquistoRigaPrm> righe = ordAcq.getRighe();
			for(YOrdineAcquistoRigaPrm riga : righe) {
				if (riga.checkMagazzinoModula() 
						&& !riga.checkIsRigaMerceAValore() 
						&& riga.checkIsMerceOrOmaggio() 
						&& riga.checkIsClasseAMO()
						&& riga.checkResiduoPresente()
						&& (riga.getTipoRiga() == TipoRiga.MERCE || riga.getTipoRiga() == TipoRiga.OMAGGIO)) {

					List<OrdineAcquistoRigaSec> righeSec = riga.getRigheSecondarie();
					for(OrdineAcquistoRigaSec rigaSec : righeSec) {
						YOrdAcqToModula rigaOrdAcqToModula = new YOrdAcqToModula(rigaSec);
						righeOrdAcqToModula.add(rigaOrdAcqToModula);
					}
				}
			}
			if(!righeOrdAcqToModula.isEmpty())
				ConnectionManager.commit(); //committo le righe che ho salvato nei costruttori
		}
	}

	
	@SuppressWarnings("unchecked")
	public static int cancellaRigheOrdineAcquisto(String keyOrdAcq) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyOrdAcq.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YOrdAcqToModulaTM.R_ANNO_ORD_ACQ+" = '"+c[1]+"' AND "+YOrdAcqToModulaTM.R_NUMERO_ORD_ACQ+" = '"+c[2]+"' ";
		List<YOrdAcqToModula> records = YOrdAcqToModula.retrieveList(YOrdAcqToModula.class,where, "", false);
		for(YOrdAcqToModula record : records) {
			record.delete();
			recordCancellati++;
		}
		if(recordCancellati > 0)
			ConnectionManager.commit();
		return recordCancellati;
	}

	
	public static ErrorMessage inviaAModulaMultple(String[] objectKeys) {
		ErrorMessage em = null;
		Connection connection = null;
		try {
			List<YOrdAcqToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0));
					if(em == null) {
						for (YOrdAcqToModula riga : rows) {
							em = riga.inviaAModula(numeroListaModula, connection);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YOrdAcqToModula.cancellaRigheOrdineAcquisto(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoOrdAcq(),rows.get(0).getRNumeroOrdAcq()}));
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

	public static List<YOrdAcqToModula> getListaRigheDaInviare(String[] keys){
		List<YOrdAcqToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YOrdAcqToModula riga = YOrdAcqToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YOrdAcqToModula vTm) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.ORDINE_ACQUISTO);
		pTm.setIdAnnoDoc(this.getRAnnoOrdAcq());
		pTm.setIdNumeroDoc(this.getRNumeroOrdAcq());
		pTm.setIdRigaDoc(this.getRRigaOrd());
		pTm.setIdDetRigaDoc(this.getRDetRigaOrd());
		boolean exists = pTm.retrieve();
		if(!exists) {
			pTm.setQtaEvasaUmPrm(BigDecimal.ZERO);
		}
		pTm.setTipoMov(TipoMovimentoModula.PRELIEVO);
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
		String lineNumber = this.getRRigaOrd().toString().concat("#").concat(this.getRDetRigaOrd().toString());
		String idArticolo = this.getRArticolo();
		BigDecimal qta = this.getQtaDaEvadere();
		int ris = 0;
		try {
			if(getRelarticolo() instanceof YArticolo
					&& !((YArticolo)getRelarticolo()).isEsportatoModula()) {
				ris = YArticolo.esportaArticoloVersoModula(connection, (YArticolo) this.getRelarticolo());
				if(ris > 0)
					ris += YArticolo.aggiornaStatoEsportazioneModulaArticolo(idArticolo, true);
				if(ris <= 0) {
					return new ErrorMessage("YSOF3_001","Impossibile esportare il nuovo articolo verso modula");
				}
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

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YOrdAcqToModula vTm){
		ErrorMessage em = null;
		OrdineAcquisto testata = getOrdineAcquistoDB(vTm.getIdAzienda(),vTm.getRAnnoOrdAcq(),vTm.getRNumeroOrdAcq());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getFornitore().getIdFornitore();
		String descrizioneLista = "["+String.valueOf(TipoMovimentoModula.PRELIEVO)+"]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
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

	protected static OrdineAcquisto getOrdineAcquistoDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (OrdineAcquisto) OrdineAcquisto.elementWithKey(OrdineAcquisto.class,
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

