package it.sicons.thip.vendite.ordineVE.web;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;

import it.istech.thip.base.modula.YOrdVenToModula;
import it.thera.thip.vendite.ordineVE.web.OrdineTestataVenditaEstrattoFormActionAdapter;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;

public class SiOrdineTestataVenditaEstrattoFormActionAdapter extends OrdineTestataVenditaEstrattoFormActionAdapter {

	private static final long serialVersionUID = 1L;

	public static final String GENERA = "GENERA";

	protected void otherActions(ClassADCollection cadc, ServletEnvironment se) throws ServletException, IOException {
		super.otherActions(cadc, se);
		this.getDatiSessione(se);
		String azione = this.getAzione(se);
		if (azione.equals(GENERA)) {
			String key = getStringParameter(se.getRequest(), "thKey");
			if (key != null && !key.equals("")) {
				if(generaListaModula(key)) {
					String[] c = KeyHelper.unpackObjectKey(key);
					String filtri = 
							"IdAzienda=" + c[0] + ";" +
									"RAnnoOrdVen=" + c[1]+ ";" +
									"RNumeroOrdVen=" + c[2] + ";";
					String urlGriglia = 
							se.getServletPath() + "com.thera.thermfw.web.servlet.ShowGrid" + 
									"?thGridType=list" +
									"&thRestrictConditions=" + URLEncoder.encode(filtri, "UTF-8") +
									"&ClassName=YOrdVenToModula";

					se.sendRequest(this.getServletContext(), urlGriglia, true);
				}else {

				}
			}
		}

	}

	protected boolean generaListaModula(String key) throws ServletException, IOException {
		try {
			YOrdVenToModula.cancellaRigheOrdineVendita(key); //svuoto le righe legate al mio ordine di vendita
			YOrdVenToModula.creaRighePerOrdineVendita(key);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return true;

	}
}