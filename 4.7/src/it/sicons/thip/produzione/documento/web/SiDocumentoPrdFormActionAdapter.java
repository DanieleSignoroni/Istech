package it.sicons.thip.produzione.documento.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Security;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;

import it.istech.thip.base.modula.YDocProToModula;
import it.sicons.thip.produzione.documento.SiDocPrd;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.produzione.documento.web.DocumentoPrdFormActionAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class SiDocumentoPrdFormActionAdapter extends DocumentoPrdFormActionAdapter {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	static Class class$0;

	public static final String GENERA = "GENERA";

	public static final String RES_ORD = "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig";

	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		WebToolBarButton btn1 = new WebToolBarButton(GENERA, "action_submit", "same", "no", "it.sicons.thip.modula.resources.SiModula", "MaterialiModula", "it/sicons/thip/modula/images/Modula.ico", GENERA, "multipleSelSingleWindow", false);
		toolBar.addButton(btn1);
	}

	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = getStringParameter(se.getRequest(), "thAction").toUpperCase();
		if (azione.equals("RIAPRI_MODIFICA")) {
			this.azioneRiapriModifica(cadc, se);
		}else if(azione.equals(GENERA)) {
			String key = getStringParameter(se.getRequest(), "thKey");
			if (key != null && !key.equals("")) {
				if(generaListaModula(key)) {
					String[] c = KeyHelper.unpackObjectKey(key);
					String filtri = 
							"IdAzienda=" + c[0] + ";" +
									"RAnnoDocPro=" + c[1]+ ";" +
									"RNumeroDocPro=" + c[2] + ";";
					String urlGriglia = 
							se.getServletPath() + "com.thera.thermfw.web.servlet.ShowGrid" + 
									"?thGridType=list" +
									"&thRestrictConditions=" + URLEncoder.encode(filtri, "UTF-8") +
									"&ClassName=YDocProToModula";

					se.sendRequest(this.getServletContext(), urlGriglia, true);
				}else {

				}
			}
		} else {
			super.otherActions(cadc, se);
		}

	}
	
	protected boolean generaListaModula(String key) throws ServletException, IOException {
		try {
			YDocProToModula.cancellaRigheDocumentoProduzione(key); //svuoto le righe legate al mio ordine di vendita
			YDocProToModula.creaRighePerDocumentoProduzione(key);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return true;

	}

	protected void save(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = getStringParameter(se.getRequest(), "thAction").toUpperCase();
		if (azione.equals("SAVE")) {
			se.getRequest().setAttribute("CambiaStato", "N");
		} else {
			se.getRequest().setAttribute("CambiaStato", "Y");
		}

		se.sendRequest(this.getServletContext(), se.getServletPath() + "it.thera.thip.produzione.documento.web.DocumentoProduzioneSave", true);
	}

	protected void azioneConfermaModifica(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		se.getRequest().setAttribute("NuoveRighe", "Y");
		se.sendRequest(this.getServletContext(), se.getServletPath() + "it.thera.thip.produzione.documento.web.RiempiRigheDocumento", true);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	protected void azioneRiapriModifica(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String action = getStringParameter(se.getRequest(), "thAction");
		String key = getStringParameter(se.getRequest(), "thKey");
		boolean incluso = true;
		String message = "Documento inviato a Modula. Utente non abilitato alla riapertura. ";
		boolean generataLista = false;
		SiDocPrd siDocPrd = null;

		try {
			Class var10000 = class$0;
			if (var10000 == null) {
				try {
					var10000 = Class.forName("it.sicons.thip.produzione.documento.SiDocPrd");
				} catch (ClassNotFoundException var11) {
					throw new NoClassDefFoundError(var11.getMessage());
				}

				class$0 = var10000;
			}

			siDocPrd = (SiDocPrd)PersistentObject.elementWithKey(var10000, key, 0);
		} catch (SQLException var12) {
			var12.printStackTrace();
		}

		if (siDocPrd == null) {
			generataLista = false;
		} else if (siDocPrd.getSiModula() != '-') {
			generataLista = true;
		}

		if (generataLista) {
			String utente = Security.getCurrentUser().getId();
			incluso = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Utenti", utente);
		}

		if (!incluso) {
			try {
				PrintWriter out = se.getResponse().getWriter();
				out.println("<script language=\"JavaScript1.2\">");
				out.println("alert(\"" + message + "\");");
				out.println("</script>");
			} catch (IOException var10) {
				var10.printStackTrace();
			}

			se.getRequest().setAttribute("CambiaStato", "N");
			se.sendRequest(this.getServletContext(), se.getServletPath() + "it.thera.thip.produzione.documento.web.DocumentoProduzioneSave", true);
		} else {
			super.azioneRiapriModifica(cadc, se);
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
