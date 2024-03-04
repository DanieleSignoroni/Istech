package it.sicons.thip.produzione.ordese.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;
import it.sicons.thip.modula.tabelle.SiLists;
import it.sicons.thip.modula.tabelle.SiModulaCausali;
import it.thera.thip.cs.PersistentObjectDCE;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;
import it.thera.thip.produzione.ordese.web.OrdineEsecutivoFormActionAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;

public class SiOrdineEsecutivoFormActionAdapter extends OrdineEsecutivoFormActionAdapter {
   public static final String MAT_A_MOD = "MAT_A_MOD";
   public static final String MAT_NON_MOD = "MAT_NON_MOD";
   public static final String SRES = "it.sicons.thip.modula.resources.SiModula";

   protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
      super.otherActions(cadc, se);
      String azione = this.getAzione(se);
      if (azione.equals("MAT_A_MOD")) {
         this.materialiAModula(cadc, se);
      } else if (azione.equals("MAT_NON_MOD")) {
         this.materialiNonModula(cadc, se);
      }

   }

   public void modifyToolBar(WebToolBar toolBar) {
      super.modifyToolBar(toolBar);
      WebToolBarButton addLinkButton = new WebToolBarButton("MaterialiModula", "action_submit", "new", "no", "it.sicons.thip.modula.resources.SiModula", "MaterialiModula", "it/thera/thip/produzione/ordese/images/Materiale.gif", "MAT_A_MOD", "single", false);
      toolBar.addSeparator();
      toolBar.addButton(addLinkButton);
      WebToolBarButton addLinkButton2 = new WebToolBarButton("MaterialiNonModula", "action_submit", "new", "no", "it.sicons.thip.modula.resources.SiModula", "MaterialiNonModula", "it/thera/thip/produzione/ordese/images/Materiale.gif", "MAT_NON_MOD", "single", false);
      toolBar.addSeparator();
      toolBar.addButton(addLinkButton2);
   }

   protected void materialiAModula(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
      List materialiInOrdine = new ArrayList();
      boolean continua = true;

      try {
         String key = getStringParameter(se.getRequest(), "thKey");
         OrdineEsecutivo ordEsec = (OrdineEsecutivo)OrdineEsecutivo.elementWithKey(OrdineEsecutivo.class, key, 1);
         String message;
         if (ordEsec != null) {
            message = "OP".concat("_").concat(ordEsec.getIdNumeroOrdine().substring(0, 2));
            String keyCau = KeyHelper.buildObjectKey(new String[]{ordEsec.getIdAzienda(), message.trim()});
            SiModulaCausali cauModu = (SiModulaCausali)PersistentObject.elementWithKey(SiModulaCausali.class, keyCau, 0);
            System.out.println("---> CAUSALE " + keyCau);
            if (cauModu != null) {
               System.out.println("---> CAUSALE NOT NULL " + keyCau);
               materialiInOrdine = this.getMateriali(ordEsec);
               continua = true;
            } else {
               continua = false;
            }
         }

         if (continua) {
            ConnectionManager.pushConnection();
            int elaborati = 0;
            Iterator materialiIter = ((List)materialiInOrdine).iterator();

            while(materialiIter.hasNext()) {
               AttivitaEsecMateriale materiale = (AttivitaEsecMateriale)materialiIter.next();
               int ret = this.aggiornaLists(materiale);
               if (ret >= 0) {
                  ++elaborati;
               }
            }

            String restrictCondition;
            if (elaborati > 0) {
               ConnectionManager.commit();
               restrictCondition = "annoOrdine=" + ordEsec.getIdAnnoOrdine();
               restrictCondition = restrictCondition + ";numeroOrdine=" + ordEsec.getIdNumeroOrdine();
               String restrictConditions = "&thRestrictConditions=" + URLEncoder.encode(restrictCondition);
               String urlDo = "&thSpecificDOList=it.sicons.thip.modula.tabelle.web.SiListsDOList";
               String servletName = "com.thera.thermfw.web.servlet.ShowGrid?ClassName=SiLists&thGridType=list" + restrictConditions + urlDo;
               se.sendRequest(this.getServletContext(), se.getServletPath() + servletName, true);
            } else {
               ConnectionManager.rollback();
               restrictCondition = "Nessun Materiale elaborato";
               PrintWriter out = se.getResponse().getWriter();
               out.println("<script language=\"JavaScript1.2\">");
               out.println("alert(\"" + restrictCondition + "\");");
               out.println("window.close()");
               out.println("</script>");
            }
         } else {
            message = "Attività non prevista per la serie";
            PrintWriter out = se.getResponse().getWriter();
            out.println("<script language=\"JavaScript1.2\">");
            out.println("alert(\"" + message + "\");");
            out.println("window.close()");
            out.println("</script>");
         }
      } catch (SQLException var17) {
         var17.printStackTrace();
      } finally {
         ConnectionManager.popConnection();
      }

   }

   public List getMateriali(OrdineEsecutivo ordineEsecutivo) {
      List listaMateriali = new Vector();
      Iterator attivitaEsecutive = ordineEsecutivo.getAttivitaEsecutive().iterator();

      while(true) {
         AttivitaEsecutiva attivita;
         do {
            do {
               if (!attivitaEsecutive.hasNext()) {
                  return listaMateriali;
               }

               attivita = (AttivitaEsecutiva)attivitaEsecutive.next();
            } while(!this.isValido(attivita));
         } while(!this.isAttivitaAperta(attivita));

         Iterator materiali = attivita.getMateriali().iterator();

         while(materiali.hasNext()) {
            AttivitaEsecMateriale materiale = (AttivitaEsecMateriale)materiali.next();
            if (materiale.getArticolo().getTipoParte() != '7' && materiale.getArticolo().getIdClasseA() != null && materiale.getArticolo().getIdClasseA().equals("MO")) {
               listaMateriali.add(materiale);
            }
         }
      }
   }

   public boolean isValido(PersistentObjectDCE oggetto) {
      return oggetto.getDatiComuniEstesi().getStato() == 'V';
   }

   public boolean isAttivitaAperta(AttivitaEsecutiva attivita) {
      return attivita.getStatoAvanzamento() != '4' && attivita.getStatoAvanzamento() != '9';
   }

   public int aggiornaLists(AttivitaEsecMateriale materiale) {
      int ret = -1;

      try {
         String whereLists = " ID_AZIENDA = '" + materiale.getIdAzienda() + "'  AND " + " ID_ANNO_ORD = '" + materiale.getIdAnnoOrdine() + "' AND " + " ID_NUMERO_ORD = '" + materiale.getIdNumeroOrdine() + "' AND " + " ID_RIGA_ATTIVITA = " + materiale.getIdRigaAttivita() + " AND  " + " ID_RIGA_MATERIALE = " + materiale.getIdRigaMateriale() + " ";
         System.out.println("---> WHERE " + whereLists);
         boolean listNew = true;
         BigDecimal qtaInModula = new BigDecimal("0");
         Vector listsEsistenti = SiLists.retrieveList(whereLists, "", false);
         Iterator iterLists = listsEsistenti.iterator();

         SiLists lists;
         while(iterLists.hasNext()) {
            lists = (SiLists)iterLists.next();
            BigDecimal qtaListaInModula = lists.getQuantitaListaMod();
            if (qtaListaInModula == null) {
               qtaListaInModula = new BigDecimal("0");
            }

            if (lists.getListNumber() == null) {
               lists.delete();
            } else {
               listNew = false;
               qtaInModula = qtaInModula.add(qtaListaInModula);
            }
         }

         if (listNew || qtaInModula.compareTo(new BigDecimal("0")) > 0) {
            System.out.println("---> LISTNEW " + listNew);
            System.out.println("---> QTAINMODULA  " + qtaInModula);
            System.out.println("---> QTARESIDUA " + materiale.getQtaResiduaUMPrm());
            System.out.println("---> CLASSEA  " + materiale.getArticolo().getIdClasseA());
            System.out.println("---> MAGPRE  " + materiale.getIdMagazzinoPrl());
            if (materiale.getQtaResiduaUMPrm() != null && materiale.getArticolo().getIdClasseA() != null && materiale.getQtaResiduaUMPrm().compareTo(qtaInModula) > 0 && materiale.getArticolo().getIdClasseA().equals("MO") && materiale.getIdMagazzinoPrl().equals("001")) {
               lists = (SiLists)Factory.createObject(SiLists.class);
               lists.setIdAzienda(materiale.getIdAzienda());
               lists.setIdRigaNumerator();
               lists.setIdAnnoOrdine(materiale.getIdAnnoOrdine());
               lists.setIdNumeroOrdine(materiale.getIdNumeroOrdine());
               lists.setIdRigaAttivita(materiale.getIdRigaAttivita());
               lists.setIdRigaMateriale(materiale.getIdRigaMateriale());
               lists.setIdArticolo(materiale.getIdArticolo());
               lists.setDesArticolo(materiale.getArticolo().getDescrizioneArticoloNLS().getDescrizione());
               lists.setIdMagazzino(materiale.getIdMagazzinoPrl());
               if (materiale.getArticolo().getIdClasseA() != null) {
                  lists.setIdSubFamiglia(materiale.getArticolo().getIdClasseA());
                  lists.setPlantId(Integer.parseInt(materiale.getArticolo().getClasseA().getDescrizione().getDescrizioneRidotta().trim()));
               }

               lists.setIdUniMis(materiale.getIdUMPrmMag());
               lists.setQtaRichiestaUMPrm(materiale.getQtaResiduaUMPrm());
               lists.setQtaRichiestaUMSec(materiale.getQtaResiduaUMSec());
               lists.setQtaPrelevataUMPrm(qtaInModula);
               lists.setListNumber((String)null);
               lists.setLineNumber((String)null);
               lists.setQuantitaListaMod(new BigDecimal("0"));
               lists.setCoeffImpiego(materiale.getCoeffImpiego());
               Vector saldiMag = SaldoMag.retrieveList(" ID_AZIENDA  = '" + materiale.getIdAzienda() + "'   AND " + " ID_MAGAZZINO = '001' " + "  AND ID_ARTICOLO = '" + materiale.getIdArticolo() + "' ", "", false);
               Iterator iterSaldi = saldiMag.iterator();
               if (iterSaldi.hasNext()) {
                  SaldoMag saldo = (SaldoMag)iterSaldi.next();
                  lists.setQuantitaGiacPth(saldo.getDatiSaldo().getQtaGiacenzaUMPrim());
               }

               ret = lists.save();
               System.out.println("---> SAVELISTS  " + ret);
            }
         }
      } catch (Exception var12) {
         var12.printStackTrace(Trace.excStream);
      }

      return ret;
   }

   protected void materialiNonModula(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
      new ArrayList();
      boolean continua = true;
      String restrictConditions = "&thRestrictConditions=";
      String restrictCondition = "";

      try {
         String key = getStringParameter(se.getRequest(), "thKey");
         OrdineEsecutivo ordEsec = (OrdineEsecutivo)OrdineEsecutivo.elementWithKey(OrdineEsecutivo.class, key, 1);
         if (ordEsec != null) {
            String chiave = "OP".concat("_").concat(ordEsec.getIdNumeroOrdine().substring(0, 2));
            String keyCau = KeyHelper.buildObjectKey(new String[]{ordEsec.getIdAzienda(), chiave.trim()});
            SiModulaCausali cauModu = (SiModulaCausali)PersistentObject.elementWithKey(SiModulaCausali.class, keyCau, 0);
            if (cauModu != null) {
               restrictCondition = "annoOrdine=" + ordEsec.getIdAnnoOrdine();
               restrictCondition = restrictCondition + ";numeroOrdine=" + ordEsec.getIdNumeroOrdine();
               restrictConditions = "&thRestrictConditions=" + URLEncoder.encode(restrictCondition);
               String doList2 = "&thSpecificDOList=it.sicons.thip.modula.tabelle.web.SiAttivitaMaterialiDOList";
               String servletName = "com.thera.thermfw.web.servlet.ShowGrid?ClassName=AttivitaEsecMateriale&thGridType=list" + doList2 + restrictConditions;
               se.sendRequest(this.getServletContext(), se.getServletPath() + servletName, true);
            }
         }
      } catch (SQLException var17) {
         var17.printStackTrace();
      } finally {
         ConnectionManager.popConnection();
      }

   }
}