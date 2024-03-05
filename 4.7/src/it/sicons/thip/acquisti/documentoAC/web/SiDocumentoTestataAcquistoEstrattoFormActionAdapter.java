package it.sicons.thip.acquisti.documentoAC.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;

import it.istech.thip.base.modula.YDocAcqToModula;
import it.thera.thip.acquisti.documentoAC.web.DocumentoTestataAcquistoEstrattoFormActionAdapter;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class SiDocumentoTestataAcquistoEstrattoFormActionAdapter extends DocumentoTestataAcquistoEstrattoFormActionAdapter {

	private static final long serialVersionUID = 1L;

	public static final String SI_GENERA_LISTA = "SI_GENERA_LISTA";

	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		super.otherActions(cadc, se);
		String azione = this.getAzione(se);
		if (azione.equals(SI_GENERA_LISTA)) {
			String key = getStringParameter(se.getRequest(), "thKey");
			if (key != null && !key.equals("")) {
				if(generaListaModula(key)) {
					String[] c = KeyHelper.unpackObjectKey(key);
					String filtri = 
							"IdAzienda=" + c[0] + ";" +
									"RAnnoDocAcq=" + c[1]+ ";" +
									"RNumeroDocAcq=" + c[2] + ";";
					String urlGriglia = 
							se.getServletPath() + "com.thera.thermfw.web.servlet.ShowGrid" + 
									"?thGridType=list" +
									"&thRestrictConditions=" + URLEncoder.encode(filtri, "UTF-8") +
									"&ClassName=YDocAcqToModula";

					se.sendRequest(this.getServletContext(), urlGriglia, true);
				}else {

				}
			}
		}

	}

	protected boolean generaListaModula(String key) throws ServletException, IOException {
		try {
			YDocAcqToModula.cancellaRigheDocumentoAcquisto(key); //svuoto le righe legate al mio ordine di vendita
			YDocAcqToModula.creaRighePerDocumentoAcquisto(key);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return true;

	}

	//   protected void lanciaGeneraLista(ServletEnvironment se) throws ServletException, IOException {
	//      //String action = getStringParameter(se.getRequest(), "thAction");
	//      String key = getStringParameter(se.getRequest(), "thKey");
	//      if (key != null && !key.equals("")) {
	//         try {
	//            DocumentoAcquisto docAcq = DocumentoAcquisto.elementWithKey(key, 0);
	//            if (docAcq.getTipoBolla() == '1' && docAcq.getStatoAvanzamento() == '2') {
	//               se.sendRequest(this.getServletContext(), "it/sicons/thip/acquisti/SiGenerazioneListaBatch.jsp?DocKey=" + key, true);
	//            }
	//         } catch (SQLException var5) {
	//            var5.printStackTrace(Trace.excStream);
	//         }
	//      }
	//
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
}