package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
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

public class YMatProToModula extends YMatProToModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public YMatProToModula() {
		super();
	}

	public YMatProToModula(AttivitaEsecMateriale riga) throws SQLException {
		YMatProToModula vTm = (YMatProToModula) Factory.createObject(YMatProToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoOrd(riga.getIdAnnoOrdine());
		vTm.setRNumeroOrd(riga.getIdNumeroOrdine());
		vTm.setRRigaAttivita(riga.getIdRigaAttivita());
		vTm.setRRigaMateriale(riga.getIdRigaMateriale());
		vTm.setRArticolo(riga.getIdArticolo());

		vTm.setQtaOriginale(riga.getQtaRichiestaUMPrm());

		BigDecimal qtaGiaEvasa = vTm.cercaQtaGiaEvasa();
		vTm.setQtaEvasa(qtaGiaEvasa); //cercare qta evasa in YPANTH_TO_MODULA


		vTm.setQtaResidua(vTm.getQtaOriginale().subtract(vTm.getQtaEvasa()));
		vTm.setQtaDaEvadere(vTm.getQtaResidua());

		String[] keySaldo = {riga.getIdAzienda(),"MOD",riga.getIdArticolo(),Integer.toString(riga.getIdVersione()), Integer.toString(riga.getIdConfigurazione() != null ? riga.getIdConfigurazione() : 0), "DUMMY"};
		SaldoMag saldo = SaldoMag.elementWithKey(KeyHelper.buildObjectKey(keySaldo), 0);
		if(saldo != null)
			vTm.setGiacenza(saldo.giacenzaNetta().getQuantitaInUMPrm());
		vTm.save();
	}

	protected BigDecimal cercaQtaGiaEvasa() throws SQLException {
		BigDecimal qta = BigDecimal.ZERO;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.ORDINE_ESECUTIVO);
		pTm.setIdAnnoDoc(this.getRAnnoOrd());
		pTm.setIdNumeroDoc(this.getRNumeroOrd());
		pTm.setIdRigaDoc(this.getRRigaAttivita());
		pTm.setIdDetRigaDoc(this.getRRigaMateriale());
		if(pTm.retrieve()) {
			qta = pTm.getQtaEvasaUmPrm();
		}
		return qta;
	}

	
	public static void creaRighePerMaterialiOrdineEsecutivo(String keyOrdVen) throws SQLException {
		OrdineEsecutivo ordEsec = (OrdineEsecutivo) OrdineVendita.elementWithKey(OrdineEsecutivo.class, keyOrdVen, 0);
		if(ordEsec != null) {
			List<YMatProToModula> righeOrdVenToModula = new ArrayList<YMatProToModula>();
			List<AttivitaEsecMateriale> righe = getMateriali(ordEsec);
					for(AttivitaEsecMateriale riga : righe) {
						if("MO".equals(riga.getArticolo().getIdClasseA())
								&& riga.getQtaResiduaUMPrm().compareTo(BigDecimal.ZERO) > 0
								&& riga.getModalitaPrelievo() == AttivitaEsecMateriale.NORMALE
								&& (riga.getIdMagazzinoPrl() != null ? "001".equals(riga.getIdMagazzinoPrl()) : "001".equals(ordEsec.getIdMagazzinoPrl()))){
							YMatProToModula matProdToModula = new YMatProToModula(riga);
							righeOrdVenToModula.add(matProdToModula);
						}
					}
			if(!righeOrdVenToModula.isEmpty())
				ConnectionManager.commit(); //committo le righe che ho salvato nei costruttori
		}
	}

	@SuppressWarnings("unchecked")
	public static List<AttivitaEsecMateriale> getMateriali(OrdineEsecutivo ordEsec) {
		List<AttivitaEsecMateriale> materiali = new ArrayList<>();
		List<AttivitaEsecutiva> attivitaList = ordEsec.getAttivitaEsecutive();
		for (AttivitaEsecutiva attivitaEsecutiva : attivitaList) {
			materiali.addAll(attivitaEsecutiva.getMateriali());
		}
		return materiali;
	}

	
	@SuppressWarnings("unchecked")
	public static int cancellaRigheMaterialiOrdineEsecutivo(String keyOrdVen) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyOrdVen.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND "+YMatProToModulaTM.R_ANNO_ORD+" = '"+c[1]+"' AND "+YMatProToModulaTM.R_NUMERO_ORD+" = '"+c[2]+"' ";
		List<YMatProToModula> records = YMatProToModula.retrieveList(YMatProToModula.class,where, "", false);
		for(YMatProToModula record : records) {
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
			List<YMatProToModula> rows = getListaRigheDaInviare(objectKeys);
			if(rows.size() > 0) {
				String numeroListaModula = null;
				connection = YModulaConnection.getModulaConnection();
				if(connection != null) {
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,rows.get(0));
					if(em == null) {
						for (YMatProToModula riga : rows) {
							em = riga.inviaAModula(numeroListaModula, connection);
							if(em != null) {
								return em;
							}
						}
						if(em == null) {
							ConnectionManager.commit();
							connection.commit();
						}
						YMatProToModula.cancellaRigheMaterialiOrdineEsecutivo(KeyHelper.buildObjectKey(new String[] {rows.get(0).getIdAzienda(),rows.get(0).getRAnnoOrd(),rows.get(0).getRNumeroOrd()}));
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

	public static List<YMatProToModula> getListaRigheDaInviare(String[] keys){
		List<YMatProToModula> list = new ArrayList<>();
		for(String key : keys) {
			try {
				YMatProToModula riga = YMatProToModula.elementWithKey(key, 0);
				if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
					list.add(riga);
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return list;
	}

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YMatProToModula vTm) {
		return scriviTestataDBModula(numeroListaModula, connection,vTm);
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc(TipoDocumentoModula.ORDINE_ESECUTIVO);
		pTm.setIdAnnoDoc(this.getRAnnoOrd());
		pTm.setIdNumeroDoc(this.getRNumeroOrd());
		pTm.setIdRigaDoc(this.getRRigaAttivita());
		pTm.setIdDetRigaDoc(this.getRRigaMateriale());
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
		String lineNumber = this.getRRigaAttivita().toString().concat("#").concat(this.getRRigaMateriale().toString());
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

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YMatProToModula vTm){
		ErrorMessage em = null;
		OrdineEsecutivo testata = getOrdineEsecutivoDB(vTm.getIdAzienda(),vTm.getRAnnoOrd(),vTm.getRNumeroOrd());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getCliente().getIdCliente();
		String descrizioneLista = "["+String.valueOf(TipoMovimentoModula.PRELIEVO)+"]" + ragSoc.trim().concat(",").concat(testata.getIdAnnoOrdine().trim()).concat(",").concat(testata.getIdNumeroOrdine().trim());
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

	protected static OrdineEsecutivo getOrdineEsecutivoDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (OrdineEsecutivo) OrdineEsecutivo.elementWithKey(OrdineEsecutivo.class,
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

