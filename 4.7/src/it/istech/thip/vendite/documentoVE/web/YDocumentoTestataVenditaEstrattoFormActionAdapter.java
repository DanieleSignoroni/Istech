package it.istech.thip.vendite.documentoVE.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;

import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;

import it.istech.thip.base.modula.YDocVenToModula;
import it.thera.thip.vendite.documentoVE.web.DocumentoTestataVenditaEstrattoFormActionAdapter;

public class YDocumentoTestataVenditaEstrattoFormActionAdapter extends DocumentoTestataVenditaEstrattoFormActionAdapter{

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
									"RAnnoDocVen=" + c[1]+ ";" +
									"RNumeroDocVen=" + c[2] + ";";
					String urlGriglia = 
							se.getServletPath() + "com.thera.thermfw.web.servlet.ShowGrid" + 
									"?thGridType=list" +
									"&thRestrictConditions=" + URLEncoder.encode(filtri, "UTF-8") +
									"&ClassName=YDocVenToModula";

					se.sendRequest(this.getServletContext(), urlGriglia, true);
				}else {

				}
			}
		}

	}

	protected boolean generaListaModula(String key) throws ServletException, IOException {
		try {
			YDocVenToModula.cancellaRigheDocumentoVendita(key); //svuoto le righe legate al mio ordine di vendita
			YDocVenToModula.creaRighePerDocumentoVendita(key);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return true;

	}
}
