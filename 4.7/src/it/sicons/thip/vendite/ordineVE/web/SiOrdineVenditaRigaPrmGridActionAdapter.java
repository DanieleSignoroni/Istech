package it.sicons.thip.vendite.ordineVE.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.WebToolBar;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documenti.web.DocumentoDatiSessione;
import it.thera.thip.vendite.ordineVE.web.OrdineVenditaRigaPrmGridActionAdapter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;

/**
 * 
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Andrea Gatta 12/02/2024
 * <br><br>
 * <b>71XXX	AGSOF3	12/02/2024</b>    <p>Rimosso bottone MODULA dal gridactionadapter</p>
 */

public class SiOrdineVenditaRigaPrmGridActionAdapter extends OrdineVenditaRigaPrmGridActionAdapter {
	private static final long serialVersionUID = 1L;
	public static final String GENERA = "GENERA";
	public static final String RES_ORD = "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig";

	public void modifyToolBar(WebToolBar toolBar) {
		super.modifyToolBar(toolBar);
		//AGOSOF3 REMMATA, NELLA PERS. INTEGRAZIONE MODULA DI SOFTRE E' STATO RICHIESTO DI RIMUOVERE IL BOTTONE DA QUI
		//      WebToolBarButton btn1 = new WebToolBarButton("Genera", "action_submit", "new", "no", "it.sicons.thip.vendite.ordineVE.resources.SiOrdVenRig", "Genera", "it/sicons/thip/modula/images/Modula.ico", "GENERA", "multipleSelSingleWindow", false);
		//      toolBar.addButton(btn1);
	}

	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		DocumentoDatiSessione datiSessione = this.getDatiSessione(se);
		String azione = this.getAzione(se);
		if (azione.equals("GENERA")) {
			try {
				this.generaListaModula(se, datiSessione, azione, cadc);
			} catch (NoSuchElementException var6) {
				var6.printStackTrace();
			}
		} else {
			super.otherActions(cadc, se);
		}

	}

	@SuppressWarnings("rawtypes")
	protected void generaListaModula(ServletEnvironment se, DocumentoDatiSessione datiSessione, String action, ClassADCollection cadc) throws ServletException, IOException {
		String[] chiaviR = se.getRequest().getParameterValues("ObjectKey");
		String[] chiavi = datiSessione.getValoriChiaviDocumento();
		String objectKey = URLEncoder.encode(KeyHelper.buildObjectKey(chiaviR),"UTF-8");
		List lstChiaviSelected = this.getChiaviSelected(cadc, se);
		System.out.println("LISTA CHIAVI SELEZIONATE " + lstChiaviSelected);

		try {
			se.getRequest().setAttribute("lstChiaviSelected", lstChiaviSelected);
		} catch (Exception var13) {
			var13.printStackTrace(Trace.excStream);
		}

		String idAzienda = chiavi[0];
		String annoDocumento = chiavi[1];
		String numeroDocumento = chiavi[2];
		String key = KeyHelper.buildObjectKey(new String[]{idAzienda, annoDocumento, numeroDocumento});
		se.sendRequest(this.getServletContext(), "it/sicons/thip/vendite/ordineVE/SiGenerazioneListaBatchOrdVen.jsp?DocKey=" + key + "&ObjectKeys=" + objectKey, true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getChiaviSelected(ClassADCollection cadc, ServletEnvironment se) {
		ArrayList listChiavi = new ArrayList();
		String idObj2 = null;
		String idObj3 = null;
		String idObj = null;
		String idAzienda = Azienda.getAziendaCorrente();
		String[] keys = se.getRequest().getParameterValues("ObjectKey");
		if (keys != null) {
			for(int i = 0; i < keys.length; ++i) {
				String selectedKey = keys[i];
				new StringTokenizer(selectedKey, KeyHelper.KEY_SEPARATOR);
				String key = null;
				idAzienda = KeyHelper.getTokenObjectKey(selectedKey, 1);
				idObj = KeyHelper.getTokenObjectKey(selectedKey, 2);
				idObj2 = KeyHelper.getTokenObjectKey(selectedKey, 3);
				idObj3 = KeyHelper.getTokenObjectKey(selectedKey, 4);
				key = KeyHelper.buildObjectKey(new String[]{idAzienda, idObj, idObj2, idObj3});
				if (idObj != null && idObj2 != null && idObj3 != null && !isExist(key, listChiavi)) {
					listChiavi.add(key);
				}
			}
		}

		return listChiavi;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isExist(String element, List lst) {
		if (!lst.isEmpty()) {
			for(int i = 0; i < lst.size(); ++i) {
				if (element.equals(lst.get(i))) {
					return true;
				}
			}
		}

		return false;
	}
}
