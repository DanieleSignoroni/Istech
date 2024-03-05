package it.sicons.thip.produzione.documento;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;
import it.sicons.thip.modula.SiModulaConnessione;
import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.produzione.documento.DocumentoPrdRigaVersamento;
import it.thera.thip.produzione.documento.DocumentoProduzione;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura.<br>
 * Buttare gestione SiConsulting.
 * </p>
 */

public class SiDocumentoProduzione extends DocumentoProduzione {
	// $FF: synthetic field
	static Class class$0;

	public int save() throws SQLException {
		int rc = super.save();
		//      if (rc >= 0) {
		//         try {
		//            if (this.isModula(this)) {
		//               SiModulaConnessione conMod = new SiModulaConnessione();
		//               int succes = 0;
		//               System.out.println("---> OK MODULA  ");
		//
		//               try {
		//                  conMod.aperturaConnessioneModula();
		//                  ConnectionManager.pushConnection();
		//                  succes = this.scritturaListaModula(this, conMod.con, conMod.newDataRiferimento);
		//                  System.out.println("---> SCRITTURA LISTA  " + succes);
		//               } catch (Exception var9) {
		//                  var9.printStackTrace();
		//               } finally {
		//                  if (succes < 0) {
		//                     ConnectionManager.rollback();
		//                  } else {
		//                     ConnectionManager.commit();
		//                  }
		//
		//                  System.out.println("---> FINE DO  " + succes);
		//                  conMod.closeConnection();
		//                  ConnectionManager.popConnection();
		//               }
		//            }
		//         } catch (Exception var11) {
		//            var11.printStackTrace();
		//         }
		//      }
		return rc;
	}

	//   public boolean isModula(DocumentoProduzione testata) {
	//      boolean modula = false;
	//      if (testata.getCausale().getAbilitaVersamenti() != '0') {
	//         modula = true;
	//      } else {
	//         modula = false;
	//      }
	//
	//      boolean generataLista = false;
	//      if (modula) {
	//         modula = false;
	//         SiDocPrd siDocPrd = null;
	//
	//         try {
	//            Class var10000 = class$0;
	//            if (var10000 == null) {
	//               try {
	//                  var10000 = Class.forName("it.sicons.thip.produzione.documento.SiDocPrd");
	//               } catch (ClassNotFoundException var16) {
	//                  throw new NoClassDefFoundError(var16.getMessage());
	//               }
	//
	//               class$0 = var10000;
	//            }
	//
	//            siDocPrd = (SiDocPrd)PersistentObject.elementWithKey(var10000, testata.getKey(), 0);
	//            if (siDocPrd != null && siDocPrd.retrieve()) {
	//               if (siDocPrd.getSiModula() == '-') {
	//                  generataLista = true;
	//               }
	//            } else {
	//               generataLista = true;
	//            }
	//         } catch (SQLException var17) {
	//            var17.printStackTrace();
	//         }
	//
	//         if (generataLista) {
	//            String idAzienda = testata.getIdAzienda();
	//            modula = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Aziende", idAzienda);
	//            System.out.println("---> AZIENDA   " + idAzienda + "--->   " + modula);
	//            if (modula) {
	//               String idCausale = testata.getRCauDocPrd();
	//               modula = this.ricercaCodiceInParmPersonalizzazione("SiModula", "CausaliPrd", idCausale);
	//               System.out.println("---> CAUSALE  " + idCausale + "--->   " + modula);
	//               if (modula) {
	//                  int artPresenti = 0;
	//                  int magPresenti = 0;
	//                  List righeDocumento = testata.getVersamentiColl();
	//                  Iterator r = righeDocumento.iterator();
	//
	//                  while(r.hasNext()) {
	//                     DocumentoPrdRigaVersamento rigaPrm = (DocumentoPrdRigaVersamento)r.next();
	//                     String idMagazzino = rigaPrm.getIdMagazzino();
	//                     boolean incluso = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Magazzini", idMagazzino);
	//                     System.out.println("---> MAGAZZINO   " + idMagazzino + "--->   " + incluso);
	//                     if (incluso) {
	//                        ++magPresenti;
	//                     }
	//
	//                     Articolo articolo = rigaPrm.getArticolo();
	//                     String classeA = articolo.getIdClasseA();
	//                     System.out.println("---> CLASSEA   " + classeA);
	//                     if (articolo.getIdClasseA() != null) {
	//                        ++artPresenti;
	//                     }
	//                  }
	//
	//                  System.out.println("---> MAGAZZINIPRESENTI   " + magPresenti);
	//                  System.out.println("---> ARTPRESENTI   " + artPresenti);
	//                  if (magPresenti > 0 && artPresenti > 0) {
	//                     modula = true;
	//                  } else {
	//                     modula = false;
	//                  }
	//               }
	//            }
	//         }
	//      }
	//
	//      return modula;
	//   }
	//
	//   public boolean ricercaCodiceInParmPersonalizzazione(String funzione, String paramentro, String codice) {
	//      boolean trovato = false;
	//      String list = null;
	//      ParametroPsn paramPers = null;
	//      paramPers = ParametroPsn.getParametroPsn(funzione, paramentro);
	//      if (paramPers != null && paramPers.getValore() != null) {
	//         list = paramPers.getValore();
	//      } else {
	//         trovato = true;
	//      }
	//
	//      if (!trovato) {
	//         String listaCodiciDaIncludere = list;
	//         int pos = list.indexOf(45);
	//         byte posIni = 0;
	//
	//         while(pos > 0) {
	//            String azienda = listaCodiciDaIncludere.substring(posIni, pos).toUpperCase();
	//            if (azienda.trim().equals(codice)) {
	//               trovato = true;
	//               pos = -1;
	//            } else {
	//               listaCodiciDaIncludere = listaCodiciDaIncludere.substring(pos + 1, listaCodiciDaIncludere.length());
	//               pos = listaCodiciDaIncludere.indexOf(45);
	//            }
	//         }
	//      }
	//
	//      return trovato;
	//   }
	//
	//   public int scritturaListaModula(DocumentoProduzione testata, Connection con, Timestamp newDataRiferimento) throws Exception {
	//      int succes = 0;
	//      String listNumber = "DP#".concat(testata.getNumeroDocumentoFormattato());
	//      System.out.println("---> LIST NUMBER  " + listNumber);
	//      String descrizioneLista = "Doc. di versamento produzione " + listNumber;
	//      List righeDocumento = testata.getVersamentiColl();
	//      Iterator siDocPrd = righeDocumento.iterator();
	//
	//      while(siDocPrd.hasNext()) {
	//         DocumentoPrdRigaVersamento rigaPrm = (DocumentoPrdRigaVersamento)siDocPrd.next();
	//         String idMagazzino = rigaPrm.getIdMagazzino();
	//         boolean incluso = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Magazzini", idMagazzino);
	//         if (incluso && rigaPrm.getArticolo().getIdClasseA() != null && rigaPrm.getArticolo().getTipoParte() != '7') {
	//            String plantIdStr = rigaPrm.getArticolo().getClasseA().getDescrizione().getDescrizioneRidotta().trim();
	//            int plantId = Integer.parseInt(plantIdStr);
	//            listNumber.concat("_" + rigaPrm.getArticolo().getIdClasseA());
	//            succes = this.rigaModula(testata, listNumber, rigaPrm, con);
	//            if (succes < 0) {
	//               return succes;
	//            }
	//         }
	//      }
	//
	//      if (succes >= 0) {
	//         succes = this.testataModula(con, listNumber, descrizioneLista);
	//         if (succes >= 0) {
	//            siDocPrd = null;
	//            Class var10000 = class$0;
	//            if (var10000 == null) {
	//               try {
	//                  var10000 = Class.forName("it.sicons.thip.produzione.documento.SiDocPrd");
	//               } catch (ClassNotFoundException var15) {
	//                  throw new NoClassDefFoundError(var15.getMessage());
	//               }
	//
	//               class$0 = var10000;
	//            }
	//
	//            SiDocPrd docPrd = (SiDocPrd)Factory.createObject(var10000);
	//            docPrd.setKey(testata.getKey());
	//            if (docPrd == null || !docPrd.retrieve()) {
	//            	docPrd.setKey(testata.getKey());
	//            	docPrd.setIdAzienda(testata.getIdAzienda());
	//            	docPrd.setIdAnnoDoc(testata.getIdAnnoDoc());
	//            	docPrd.setIdNumeroDoc(testata.getIdNumeroDoc());
	//            }
	//
	//            docPrd.setSiModula('1');
	//            succes = docPrd.save();
	//            System.out.println("---> SAVE SIDOCPRD  " + listNumber + succes);
	//         }
	//      }
	//
	//      return succes;
	//   }
	//
	//   public int rigaModula(DocumentoProduzione testata, String listNumber, DocumentoPrdRigaVersamento rigaPrm, Connection con) throws Exception {
	//      String lineNumber = "DO#".concat(testata.getNumeroDocumentoFormattato()).concat("_" + rigaPrm.getArticolo().getIdClasseA());
	//      String lineNumberNr = rigaPrm.getNumeroRigaDocumento().toString();
	//      String idArticolo = rigaPrm.getRArticolo();
	//      BigDecimal quantita = rigaPrm.getQuantitaUmPrm();
	//      String insertList = " INSERT INTO [dbo].[IMP_ORDINI_RIGHE]  ([RIG_ORDINE],[RIG_ARTICOLO],[RIG_HOSTINF],[RIG_QTAR],[RIG_ATTR1],[RIG_ERRORE]) VALUES(   '" + listNumber + "'" + " , '" + idArticolo + "'" + " , '" + lineNumberNr + "'" + " , " + quantita + " , '" + lineNumber + "'" + " , '" + "' )";
	//
	//      try {
	//         Statement st = con.createStatement();
	//         int succes = st.executeUpdate(insertList);
	//         System.out.println("---> INSERT LIST NUMBER  " + listNumber + succes);
	//         return succes;
	//      } catch (SQLException var12) {
	//         var12.printStackTrace(Trace.excStream);
	//         return -1;
	//      }
	//   }
	//
	//   public int testataModula(Connection con, String listNumber, String descrizioneLista) throws Exception {
	//      String insertList = " INSERT INTO [dbo].[IMP_ORDINI] ([ORD_ORDINE],[ORD_DES],[ORD_TIPOOP],[ORD_ERRORE]) VALUES (  '" + listNumber + "'" + " , '" + descrizioneLista + "'" + " , '" + "V" + "'" + " , '" + "ORD_ERRORE" + "'" + ")";
	//
	//      try {
	//         Statement st = con.createStatement();
	//         int succes = st.executeUpdate(insertList);
	//         System.out.println("---> INSERT LIST NUMBER  " + listNumber + succes);
	//         return succes;
	//      } catch (SQLException var7) {
	//         var7.printStackTrace(Trace.excStream);
	//         return -1;
	//      }
	//   }
}