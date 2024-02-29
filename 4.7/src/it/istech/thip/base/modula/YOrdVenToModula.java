package it.istech.thip.base.modula;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.sicons.ag.produzione.mancanti.ParametriUtils;
import it.sicons.thip.venditeIstech.ordineVE.SiOrdineVenditaRigaPrm;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.articolo.ArticoloDatiIdent;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRiga;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaSec;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

public class YOrdVenToModula extends YOrdVenToModulaPO {

	public static String STMT_INSERT_IMP_ORDINI = "INSERT INTO [dbo].[IMP_ORDINI] ([ORD_ORDINE],[ORD_DES],[ORD_TIPOOP],[ORD_ERRORE]) "
			+ " VALUES (?,?,?,?) ";

	public static CachedStatement cs_insert_imp_ordini = new CachedStatement(STMT_INSERT_IMP_ORDINI);

	public static String STMT_INSERT_IMP_ORDINI_RIG = "INSERT INTO [dbo].[IMP_ORDINI_RIGHE] ([RIG_ORDINE],[RIG_ARTICOLO],[RIG_HOSTINF],[RIG_QTAR],[RIG_ATTR1],[RIG_ERRORE]) "
			+ " VALUES (?,?,?,?,?,?) ";

	public static CachedStatement cs_insert_imp_ordini_rig = new CachedStatement(STMT_INSERT_IMP_ORDINI_RIG);

	public ErrorMessage checkDelete() {
		return null;
	}

	public YOrdVenToModula() {
		super();
	}

	public YOrdVenToModula(OrdineVenditaRiga riga) throws SQLException {
		YOrdVenToModula vTm = (YOrdVenToModula) Factory.createObject(YOrdVenToModula.class); //vTm venToModula
		vTm.setIdAzienda(riga.getIdAzienda());
		vTm.setRAnnoOrdVen(riga.getAnnoDocumento());
		vTm.setRNumeroOrdVen(riga.getNumeroDocumento());
		vTm.setRRigaOrd(riga.getNumeroRigaDocumento());
		vTm.setRDetRigaOrd(riga.getDettaglioRigaDocumento());
		vTm.setRArticolo(riga.getIdArticolo());

		vTm.setQtaOriginale(riga.getQuantitaOrdinata().getQuantitaInUMRif());

		BigDecimal qtaGiaEvasa = vTm.cercaQtaGiaEvasa();
		vTm.setQtaEvasa(qtaGiaEvasa); //cercare qta evasa in YPANTH_TO_MODULA


		vTm.setQtaResidua(vTm.getQtaOriginale().subtract(vTm.getQtaEvasa()));
		vTm.setQtaDaEvadere(vTm.getQtaResidua());

		String[] keySaldo = {riga.getIdAzienda(),"MOD",riga.getIdArticolo(),Integer.toString(riga.getIdVersioneRcs()), Integer.toString(riga.getIdConfigurazione() != null ? riga.getIdConfigurazione() : 0), riga.getIdOperazione() != null ? riga.getIdOperazione() : "DUMMY"};
		SaldoMag saldo = SaldoMag.elementWithKey(KeyHelper.buildObjectKey(keySaldo), 0);
		if(saldo != null)
			vTm.setGiacenza(saldo.giacenzaNetta().getQuantitaInUMPrm());
		vTm.save();
	}

	protected BigDecimal cercaQtaGiaEvasa() throws SQLException {
		BigDecimal qta = BigDecimal.ZERO;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc('V');
		pTm.setIdAnnoDoc(this.getRAnnoOrdVen());
		pTm.setIdNumeroDoc(this.getRNumeroOrdVen());
		pTm.setIdRigaDoc(this.getRRigaOrd());
		pTm.setIdDetRigaDoc(this.getRDetRigaOrd());
		if(pTm.retrieve()) {
			qta = pTm.getQtaEvasaUmPrm();
		}
		return qta;
	}

	/**
	 * 
	 * @param keyOrdVen = chiave dell'ordine di vendita di cui vanno create le righe che poi si vorranno passare a modula
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public static void creaRighePerOrdineVendita(String keyOrdVen) throws SQLException {
		OrdineVendita ordVen = (OrdineVendita) OrdineVendita.elementWithKey(OrdineVendita.class, keyOrdVen, 0);
		if(ordVen != null) {
			List<YOrdVenToModula> righeOrdVenToModula = new ArrayList<YOrdVenToModula>();
			List<SiOrdineVenditaRigaPrm> righe = ordVen.getRighe();
			for(SiOrdineVenditaRigaPrm riga : righe) {
				if (riga.checkMagazzinoModula() 
						&& !riga.checkIsRigaMerceAValore() 
						&& riga.checkIsMerceOrOmaggio() 
						&& riga.checkIsClasseAMO()
						&& riga.checkResiduoPresente()) {

					Articolo art = riga.getArticolo();
					switch(art.getTipoParte()) {
					case ArticoloDatiIdent.KIT_NON_GEST:
						/*
						 Se un riga primaria è intestata ad un articolo che ha tipo parte “kit non gestito a
						 magazzino” allora la riga primaria non viene mostrata. Vengono mostrate però le
						 relative righe secondarie
						 */
						List<OrdineVenditaRigaSec> righeSec = riga.getRigheSecondarie();
						for(OrdineVenditaRigaSec rigaSec : righeSec) {
							YOrdVenToModula rigaOrdVenToModula = new YOrdVenToModula(rigaSec);
							righeOrdVenToModula.add(rigaOrdVenToModula);
						}
						break;					
					case ArticoloDatiIdent.KIT_GEST:
						/*
						Se un riga primaria è intestata ad un articolo che ha tipo parte “kit gestito a magazzino”
						allora la riga primaria viene mostrata. Vengono però omesse le relative righe
						secondarie.
						 */
						YOrdVenToModula rigaOrdVenToModula = new YOrdVenToModula(riga);
						righeOrdVenToModula.add(rigaOrdVenToModula);
						break;
					default:
						break;
					}
				}
			}
			if(!righeOrdVenToModula.isEmpty())
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
	public static int cancellaRigheOrdineVendita(String keyOrdVen) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int recordCancellati = 0;
		String[] c = keyOrdVen.split(KeyHelper.KEY_SEPARATOR);
		String where = " ID_AZIENDA = '"+c[0]+"' AND R_ANNO_ORD_VEN = '"+c[1]+"' AND R_NUMERO_ORD_VEN = '"+c[2]+"' ";
		List<YOrdVenToModula> records = YOrdVenToModula.retrieveList(YOrdVenToModula.class,where, "", false);
		for(YOrdVenToModula record : records) {
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
			connection = YModulaConnection.getModulaConnection();
			if(connection != null) {
				YOrdVenToModula vTm = null;
				String numeroListaModula = null;
				if(objectKeys.length > 0) {
					vTm = YOrdVenToModula.elementWithKey(objectKeys[0], 0);
					numeroListaModula = ParametriUtils.getNextNumeratorLista();
					em = inviaTestataAModula(numeroListaModula,connection,vTm);
				}
				if(em == null) {
					for(String key : objectKeys) {
						YOrdVenToModula riga = YOrdVenToModula.elementWithKey(key, 0);
						if(riga.getQtaDaEvadere().compareTo(BigDecimal.ZERO) == 1) {
							em = riga.inviaAModula(numeroListaModula, connection);
							if(em != null) {
								return em;
							}
						}
					}
				}else {
					return em;
				}
				YOrdVenToModula.cancellaRigheOrdineVendita(KeyHelper.buildObjectKey(new String[] {vTm.getIdAzienda(),vTm.getRAnnoOrdVen(),vTm.getRNumeroOrdVen()}));
			}else {
				//errore di connessione
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

	public static ErrorMessage inviaTestataAModula(String numeroListaModula, Connection connection, YOrdVenToModula vTm) {
		ErrorMessage em = null;
		em = scriviTestataDBModula(numeroListaModula, connection,vTm);
		return em;
	}

	public ErrorMessage inviaAModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		YPanthToModula pTm = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		pTm.setIdAzienda(this.getIdAzienda());
		pTm.setTipoDoc('V');
		pTm.setIdAnnoDoc(this.getRAnnoOrdVen());
		pTm.setIdNumeroDoc(this.getRNumeroOrdVen());
		pTm.setIdRigaDoc(this.getRRigaOrd());
		pTm.setIdDetRigaDoc(this.getRDetRigaOrd());
		boolean exists = pTm.retrieve();
		if(!exists) {
			pTm.setQtaEvasaUmPrm(BigDecimal.ZERO);
		}
		pTm.setTipoMov('P');
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
			ConnectionManager.commit();
		}
		return null;		
	}

	protected ErrorMessage scriviRigaDBModula(String numeroListaModula,Connection connection) throws SQLException {
		ErrorMessage em = null;
		String lineNumber = this.getRRigaOrd().toString().concat("#").concat(this.getRDetRigaOrd().toString());
		String idArticolo = this.getRArticolo();
		BigDecimal qta = this.getQtaDaEvadere();
		try (
				Connection conn = connection;
				PreparedStatement ps = cs_insert_imp_ordini_rig.getStatement()
				) {
			ps.setString(1, numeroListaModula);
			ps.setString(2, idArticolo);
			ps.setString(3, null);
			ps.setBigDecimal(4, qta);
			ps.setString(5, lineNumber);
			ps.setString(6, null);
			int ris = ps.executeUpdate();
			if(ris > 0) {
				//connection.commit();
			}
		}
		return em;
	}

	protected static ErrorMessage scriviTestataDBModula(String numeroListaModula,Connection connection, YOrdVenToModula vTm){
		ErrorMessage em = null;
		OrdineVendita testata = getOrdineVenditaDB(vTm.getIdAzienda(),vTm.getRAnnoOrdVen(),vTm.getRNumeroOrdVen());
		if(testata == null) {
			//new error msg testata non trovata
		}
		String ragSoc = testata.getCliente().getIdCliente();
		String descrizioneLista = "[V]" + ragSoc.trim().concat(",").concat(testata.getAnnoDocumento().trim()).concat(",").concat(testata.getNumeroDocumento().trim());
		try (
				Connection conn = connection;
				PreparedStatement ps = cs_insert_imp_ordini.getStatement()
				) {
			ps.setString(1, numeroListaModula);
			ps.setString(2, descrizioneLista);
			ps.setString(3, "P");
			ps.setString(4, null);
			int ris = ps.executeUpdate();
			if(ris > 0) {
				//connection.commit();
			}
		} catch (SQLException e) {
			em = CreaMessaggioErrore.daRcAErrorMessage(9999, e);
			e.printStackTrace(Trace.excStream);
		}
		return em;
	}

	protected static YModulaConnection getNewModulaConnection() {
		try {
			return new YModulaConnection();
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

	protected static OrdineVendita getOrdineVenditaDB(String idAzienda,String idAnnoOrd,String idNumeroOrd) {
		try {
			return (OrdineVendita) OrdineVendita.elementWithKey(OrdineVendita.class,
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