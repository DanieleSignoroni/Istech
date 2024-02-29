 package it.sicons.thip.acquisti.documentoAC.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.web.ServletEnvironment;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.documentoAC.web.DocumentoTestataAcquistoEstrattoFormActionAdapter;
import it.thera.thip.base.generale.ParametroPsn;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class SiDocumentoTestataAcquistoEstrattoFormActionAdapter extends DocumentoTestataAcquistoEstrattoFormActionAdapter {
  
	private static final long serialVersionUID = 1L;

	public static final String SI_GENERA_LISTA = "SI_GENERA_LISTA";

   protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
      super.otherActions(cadc, se);
      this.getDatiSessione(se);
      String azione = this.getAzione(se);
      if (azione.equals("SI_GENERA_LISTA")) {
         this.lanciaGeneraLista(se);
      }

   }

   protected void lanciaGeneraLista(ServletEnvironment se) throws ServletException, IOException {
      //String action = getStringParameter(se.getRequest(), "thAction");
      String key = getStringParameter(se.getRequest(), "thKey");
      if (key != null && !key.equals("")) {
         try {
            DocumentoAcquisto docAcq = DocumentoAcquisto.elementWithKey(key, 0);
            if (docAcq.getTipoBolla() == '1' && docAcq.getStatoAvanzamento() == '2') {
               se.sendRequest(this.getServletContext(), "it/sicons/thip/acquisti/SiGenerazioneListaBatch.jsp?DocKey=" + key, true);
            }
         } catch (SQLException var5) {
            var5.printStackTrace(Trace.excStream);
         }
      }

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
}