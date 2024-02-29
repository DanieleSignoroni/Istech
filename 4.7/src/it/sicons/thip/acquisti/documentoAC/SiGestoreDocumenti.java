package it.sicons.thip.acquisti.documentoAC;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.KeyHelper;
import it.sicons.thip.modula.SiModulaConnessione;
import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.comuniVenAcq.DocumentoTestata;
import it.thera.thip.base.comuniVenAcq.GestoreDocumenti;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.qualita.controllo.DocumentoCollaudoTestataTM;
import it.thera.thip.qualita.controllo.DocumentoTRFTM;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class SiGestoreDocumenti extends GestoreDocumenti {

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Buttare gestione SiConsulting.<br>
	 * </p>
	 */
	@SuppressWarnings("rawtypes")
	public List convalida(DocumentoTestata testata) {
		List list = super.convalida(testata);
		//
		//      try {
		//         if (!this.isCondizioneErrore(list) && testata instanceof DocumentoAcquisto && this.isModula((DocumentoAcquisto)testata)) {
		//            SiModulaConnessione conMod = new SiModulaConnessione();
		//            int succes = 0;
		//            System.out.println("---> OK Modula  ");
		//
		//            try {
		//               conMod.aperturaConnessioneModula();
		//               ConnectionManager.pushConnection();
		//               succes = this.scritturaListaModula((DocumentoAcquisto)testata, conMod.con, conMod.newDataRiferimento);
		//               System.out.println("---> SCRITTURA LISTA  " + succes);
		//            } catch (Exception var10) {
		//               var10.printStackTrace();
		//            } finally {
		//               if (succes < 0) {
		//                  ConnectionManager.rollback();
		//               } else {
		//                  ConnectionManager.commit();
		//               }
		//
		//               System.out.println("---> FINE DO  " + succes);
		//               conMod.closeConnection();
		//               ConnectionManager.popConnection();
		//            }
		//         }
		//      } catch (Exception var12) {
		//         var12.printStackTrace();
		//      }

		return list;
	}

	public boolean isModula(DocumentoAcquisto testata) {
		boolean modula = false;
		if (testata.getFlagRiservatoUtente1() == '-') {
			String idAzienda = testata.getIdAzienda();
			modula = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Aziende", idAzienda);
			System.out.println("---> AZIENDA   " + idAzienda + "--->   " + modula);
			if (modula) {
				String idCausale = testata.getIdCau();
				modula = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Causali", idCausale);
				System.out.println("---> CAUSALE  " + idCausale + "--->   " + modula);
				if (modula) {
					int artPresenti = 0;
					int magPresenti = 0;
					List righeDocumento = testata.getRighe();
					Iterator r = righeDocumento.iterator();

					while(r.hasNext()) {
						DocumentoAcqRigaPrm rigaPrm = (DocumentoAcqRigaPrm)r.next();
						String idMagazzino = rigaPrm.getIdMagazzino();
						boolean incluso = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Magazzini", idMagazzino);
						System.out.println("---> MAGAZZINO   " + idMagazzino + "--->   " + incluso);
						if (incluso) {
							++magPresenti;
						}

						Articolo articolo = rigaPrm.getArticolo();
						String classeA = articolo.getIdClasseA();
						System.out.println("---> CLASSEA   " + classeA);
						if (articolo.getIdClasseA() != null) {
							++artPresenti;
						}
					}

					System.out.println("---> MAGAZZINIPRESENTI   " + magPresenti);
					System.out.println("---> ARTPRESENTI   " + artPresenti);
					if (magPresenti > 0 && artPresenti > 0) {
						modula = true;
					} else {
						modula = false;
					}
				}
			}
		}

		return modula;
	}

	public boolean ricercaCodiceInParmPersonalizzazione(String funzione, String paramentro, String codice) {
		boolean trovato = false;
		String list = null;
		ParametroPsn paramPers = null;
		paramPers = ParametroPsn.getParametroPsn(funzione, paramentro);
		if (paramPers != null && paramPers.getValore() != null) {
			list = paramPers.getValore();
		} else {
			trovato = true;
		}

		if (!trovato) {
			String listaCodiciDaIncludere = list;
			int pos = list.indexOf(45);
			byte posIni = 0;

			while(pos > 0) {
				String azienda = listaCodiciDaIncludere.substring(posIni, pos).toUpperCase();
				if (azienda.trim().equals(codice)) {
					trovato = true;
					pos = -1;
				} else {
					listaCodiciDaIncludere = listaCodiciDaIncludere.substring(pos + 1, listaCodiciDaIncludere.length());
					pos = listaCodiciDaIncludere.indexOf(45);
				}
			}
		}

		return trovato;
	}

	public int scritturaListaModula(DocumentoAcquisto testata, Connection con, Timestamp newDataRiferimento) throws Exception {
		int succes = 0;
		String listNumber = "DO#".concat(testata.getNumeroDocumentoFormattato());
		System.out.println("---> LIST NUMBER  " + listNumber);
		String ragSoc = testata.getFornitore().getIdFornitore();
		String descrizioneLista = ragSoc + testata.getNumeroRifIntestatario();
		List righeDocumento = testata.getRighe();
		Iterator r = righeDocumento.iterator();

		while(r.hasNext()) {
			DocumentoAcqRigaPrm rigaPrm = (DocumentoAcqRigaPrm)r.next();
			String idMagazzino = rigaPrm.getIdMagazzino();
			boolean incluso = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Magazzini", idMagazzino);
			if (incluso && rigaPrm.getArticolo().getIdClasseA() != null && !this.isRigaInCollaudo(rigaPrm)) {
				String plantIdStr = rigaPrm.getArticolo().getClasseA().getDescrizione().getDescrizioneRidotta().trim();
				int plantId = Integer.parseInt(plantIdStr);
				listNumber.concat("_" + rigaPrm.getArticolo().getIdClasseA());
				succes = this.rigaModula(testata, listNumber, rigaPrm, con);
				if (succes < 0) {
					return succes;
				}
			}
		}

		if (succes >= 0) {
			succes = this.testataModula(con, listNumber, descrizioneLista);
			if (succes >= 0) {
				testata.setFlagRiservatoUtente1('1');
				succes = testata.save();
				System.out.println("---> SAVE TESTATA  " + listNumber + succes);
			}
		}

		return succes;
	}

	public int rigaModula(DocumentoAcquisto testata, String listNumber, DocumentoAcqRigaPrm rigaPrm, Connection con) throws Exception {
		String lineNumber = "DO#".concat(testata.getNumeroDocumentoFormattato()).concat("_" + rigaPrm.getArticolo().getIdClasseA());
		String lineNumberNr = rigaPrm.getNumeroRigaDocumento().toString();
		String idArticolo = rigaPrm.getIdArticolo();
		BigDecimal quantita = rigaPrm.getQtaRicevuta().getQuantitaInUMPrm();
		String insertList = " INSERT INTO [dbo].[IMP_ORDINI_RIGHE]  ([RIG_ORDINE],[RIG_ARTICOLO],[RIG_HOSTINF],[RIG_QTAR],[RIG_ATTR1],[RIG_ERRORE]) VALUES(   '" + listNumber + "'" + " , '" + idArticolo + "'" + " , '" + lineNumberNr + "'" + " , " + quantita + " , '" + lineNumber + "'" + " , '" + "' )";

		try {
			Statement st = con.createStatement();
			int succes = st.executeUpdate(insertList);
			System.out.println("---> INSERT LIST NUMBER  " + listNumber + succes);
			return succes;
		} catch (SQLException var12) {
			var12.printStackTrace(Trace.excStream);
			return -1;
		}
	}

	public int testataModula(Connection con, String listNumber, String descrizioneLista) throws Exception {
		String insertList = " INSERT INTO [dbo].[IMP_ORDINI] ([ORD_ORDINE],[ORD_DES],[ORD_TIPOOP],[ORD_ERRORE]) VALUES (  '" + listNumber + "'" + " , '" + descrizioneLista + "'" + " , '" + "V" + "'" + " , '" + "'" + ")";

		try {
			Statement st = con.createStatement();
			int succes = st.executeUpdate(insertList);
			System.out.println("---> INSERT LIST NUMBER  " + listNumber + succes);
			return succes;
		} catch (SQLException var7) {
			var7.printStackTrace(Trace.excStream);
			return -1;
		}
	}

	public boolean isRigaInCollaudo(DocumentoAcqRigaPrm rigaPrm) throws Exception {
		boolean collaudo = false;
		String tipoDocCol = ((DocumentoAcquisto)rigaPrm.getTestata()).getIdTipoDocumentoCollaudo();
		String keyDocCol = KeyHelper.buildObjectKey(new Object[]{rigaPrm.getIdAzienda(), tipoDocCol, rigaPrm.getAnnoDocumento(), rigaPrm.getNumeroDocumento(), rigaPrm.getNumeroRigaDocumento()});
		if (existDocumentoCollaudoEsitoDoc("A%", rigaPrm.getIdAzienda(), rigaPrm.getAnnoDocumento(), rigaPrm.getNumeroDocumento(), rigaPrm.getNumeroRigaDocumento(), rigaPrm.getDettaglioRigaDocumento())) {
			System.out.println("--->test collaudo   esiste doc collaudo " + keyDocCol);
			collaudo = true;
		} else {
			collaudo = false;
		}

		System.out.println("--->test collaudo is collaudo " + collaudo);
		return collaudo;
	}

	public static synchronized boolean existDocumentoCollaudoEsitoDoc(String tipoDoc, String idAzienda, String idAnnoDoc, String idNumeroDoc, Integer idRigaDoc, Integer idDetRigaDoc) {
		String SQL_EXIST_DC = "SELECT * FROM " + DocumentoCollaudoTestataTM.TABLE_NAME + " DCT " + " LEFT OUTER JOIN " + DocumentoTRFTM.TABLE_NAME + " TRF " + " ON DCT." + "ID_AZIENDA" + "= TRF." + "R_AZIENDA" + " AND DCT." + "ID_TIPO_DOCPRV" + "= TRF." + "R_TIPO_DOCPRV" + " AND DCT." + "ID_ANNO_DOC" + "= TRF." + "R_ANNO_DOC_TES" + " AND DCT." + "ID_NUMERO_DOC" + "= TRF." + "R_NUM_DOC_TES" + " AND DCT." + "ID_RIGA" + "= TRF." + "R_RIGA_TES" + " WHERE DCT." + "ID_TIPO_DOCPRV" + " LIKE ?" + " AND DCT." + "ID_AZIENDA" + "= ?";
		String SQL_EXIST_DC_DOC = SQL_EXIST_DC + " AND TRF." + "R_ANNO_DOC" + "= ?" + " AND TRF." + "R_NUMERO_DOC" + "= ?" + " AND TRF." + "R_RIGA_DOC" + "= ?" + " AND TRF." + "R_DET_RIGA_DOC" + "= ?";
		CachedStatement existDCDocCahStmt = new CachedStatement(SQL_EXIST_DC_DOC);

		try {
			ResultSet rs = null;
			PreparedStatement ps = existDCDocCahStmt.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, tipoDoc);
			db.setString(ps, 2, idAzienda);
			db.setString(ps, 3, idAnnoDoc);
			db.setString(ps, 4, idNumeroDoc);
			ps.setInt(5, idRigaDoc);
			ps.setInt(6, idDetRigaDoc);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException var12) {
			var12.printStackTrace(Trace.excStream);
		}

		return false;
	}
}