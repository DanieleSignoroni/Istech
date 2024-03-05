package it.istech.thip.base.modula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;

import it.istech.thip.base.modula.YOrdVenToModula;
import it.thera.thip.cs.web.AziendaGridActionAdapter;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YOrdVenToModulaGridActionAdapter extends AziendaGridActionAdapter{

	private static final long serialVersionUID = 1L;
	public static final String GENERA = "GENERA";
	public static final String RES_ORD = "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig";

	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		WebToolBarButton btn1 = new WebToolBarButton("INVIA_MODULA", "action_submit", "infoArea", "no", "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig", "Genera", "it/sicons/thip/modula/images/Modula.ico", "INVIA_MODULA", "multipleSelSingleWindow", false);
		toolBar.addButton(btn1);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = se.getRequest().getParameter(ACTION);
		if("INVIA_MODULA".equals(azione)) {
			try {
				String[] objectKeys = se.getRequest().getParameterValues(OBJECT_KEY);
				ErrorMessage em = YOrdVenToModula.inviaAModulaMultple(objectKeys);
				if(em == null) {
					String[] keyParts = KeyHelper.unpackObjectKey(objectKeys[0]);
					String key = KeyHelper.buildObjectKey(new String[] {
							keyParts[0],
							keyParts[1],
							keyParts[2]
					});
					String url = "/" + se.getServletPath() + "/it.thera.thip.vendite.ordineVE.web.OrdineVenditaGridActionAdapter?thClassName=OrdineVendita&ObjectKey="+URLEncoder.encode(key)+"&thTarget=NEW&thAction=UPDATE_RIGHE";
					//se.sendRequest(getServletContext(), url, false);
					executeJSOpenAction(se, url);
				}else {
					se.addErrorMessage(em);
					se.sendRequest(getServletContext(), "com/thera/thermfw/common/InfoAreaHandler.jsp", false);
				}
			}catch(Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}else {
			super.otherActions(cadc, se);
		}
	}
	
	public void executeJSOpenAction(ServletEnvironment se, String url) {
		try {
			PrintWriter out = se.getResponse().getWriter();
			out.println("  <script language=\'JavaScript1.2\'>");
			String initialActionAdapter = getStringParameter(se.getRequest(), "thInitialActionAdapter");
			if(initialActionAdapter != null) {
				out.println("    var errViewObj = window.parent.eval(window.parent.errorsViewName);");
				out.println("    errViewObj.setMessage(null);");
				out.println("    parent.enableFormActions();");
			}
			else {
				out.println("window.parent.ErVwinfoarea.clearDisplay();");
				out.println("window.parent.enableGridActions();");
			}
			if (url.startsWith("/"))
				url = url.substring(1);
			out.println("    var url = '" + se.getWebApplicationPath() + url + "'");
			out.println(getWinFeatures(url));           
			out.println("    var winName = '" + String.valueOf(System.currentTimeMillis()) + "';");
			out.println("    var winrUrl = parent.window.open(url,'_self');");
			out.println("  </script>");
		}
		catch (Exception ex) {
			ex.printStackTrace(Trace.excStream);
		}
	}

	public String getWinFeatures(String url) {
		return "var winFeature = 'width=1366, height=768, resizable=yes';";	  
	}

	@Override
	public String getWebFormModifierExtended() {
		return "it.sicons.thip.vendite.ordineVE.web.YOrdVenToModulaEditFormModifier";
	}

	@Override
	public String getWebGridStylistName() {
		return "it.sicons.thip.vendite.ordineVE.web.YOrdVenToModulaStylist";
	}
}