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
import com.thera.thermfw.web.servlet.GridActionAdapter;

import it.istech.thip.base.modula.YMatProToModula;

public class YMatProToModulaGridActionAdapter extends GridActionAdapter{

	private static final long serialVersionUID = 1L;

	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		WebToolBarButton btn1 = new WebToolBarButton("INVIA_MODULA", "action_submit", "infoArea", "no", "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig", "Genera", "it/sicons/thip/modula/images/Modula.ico", "INVIA_MODULA", "multipleSelSingleWindow", false);
		toolBar.addButton(btn1);
	}

	@Override
	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		String azione = se.getRequest().getParameter(ACTION);
		if("INVIA_MODULA".equals(azione)) {
			try {
				String[] objectKeys = se.getRequest().getParameterValues(OBJECT_KEY);
				ErrorMessage em = YMatProToModula.inviaAModulaMultple(objectKeys);
				if(em == null) {
					String[] keyParts = KeyHelper.unpackObjectKey(objectKeys[0]);
					String key = KeyHelper.buildObjectKey(new String[] {
							keyParts[0],
							keyParts[1],
							keyParts[2]
					});
					String url = "/it/thera/thip/produzione/ordese/OrdineEsecutivo.jsp?Mode=UPDATE&Key="+URLEncoder.encode(key,"UTF-8")+"&InitialActionAdapter=it.thera.thip.produzione.ordese.web.OrdineEsecutivoGridActionAdapter";
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
		return "it.istech.thip.base.modula.web.YMatProToModulaEditFormModifier";
	}

	@Override
	public String getWebGridStylistName() {
		return "it.istech.thip.base.modula.web.YMatProToModulaStylist";
	}
}