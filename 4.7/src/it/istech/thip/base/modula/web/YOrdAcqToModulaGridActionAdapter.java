package it.istech.thip.base.modula.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;
import com.thera.thermfw.web.WebToolBarButton;

import it.istech.thip.base.modula.YOrdVenToModula;
import it.thera.thip.cs.web.AziendaGridActionAdapter;

public class YOrdAcqToModulaGridActionAdapter extends AziendaGridActionAdapter{

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
				String ordVenKey = null;
				//ErrorMessage em = YOrdVenToModula.inviaAModulaMultple(objectKeys);
//				if(em == null) {
//					String url = "/" + se.getServletPath() + "/it.thera.thip.vendite.ordineVE.web.OrdineVenditaGridActionAdapter?thClassName=OrdineVendita&ObjectKey="+URLEncoder.encode(ordVenKey)+"&thTarget=NEW&thAction=UPDATE_RIGHE";
//					se.sendRequest(getServletContext(), url, false);
//				}else {
//					se.addErrorMessage(em);
//					se.sendRequest(getServletContext(), "com/thera/thermfw/common/InfoAreaHandler.jsp", false);
//				}
			}catch(Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}else {
			super.otherActions(cadc, se);
		}
	}

	@Override
	public String getWebFormModifierExtended() {
		return "it.istech.thip.base.modula.web.YOrdAcqToModulaEditFormModifier";
	}

	@Override
	public String getWebGridStylistName() {
		return "it.istech.thip.base.modula.web.YOrdAcqToModulaStylist";
	}
}