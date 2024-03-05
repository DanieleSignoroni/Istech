package it.istech.thip.acquisti.ordineAC.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;

import it.istech.thip.base.modula.YOrdAcqToModula;
import it.thera.thip.acquisti.ordineAC.web.OrdineTestataAcquistoEstrattoFormActionAdapter;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YOrdineTestataAcquistoEstrattoFormActionAdapter extends OrdineTestataAcquistoEstrattoFormActionAdapter{

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
									"RAnnoOrdAcq=" + c[1]+ ";" +
									"RNumeroOrdAcq=" + c[2] + ";";
					String urlGriglia = 
							se.getServletPath() + "com.thera.thermfw.web.servlet.ShowGrid" + 
									"?thGridType=list" +
									"&thRestrictConditions=" + URLEncoder.encode(filtri, "UTF-8") +
									"&ClassName=YOrdAcqToModula";

					se.sendRequest(this.getServletContext(), urlGriglia, true);
				}else {

				}
			}
		}

	}

	protected boolean generaListaModula(String key) throws ServletException, IOException {
		try {
			YOrdAcqToModula.cancellaRigheOrdineAcquisto(key); //svuoto le righe legate al mio ordine di vendita
			YOrdAcqToModula.creaRighePerOrdineAcquisto(key);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return true;

	}
}
