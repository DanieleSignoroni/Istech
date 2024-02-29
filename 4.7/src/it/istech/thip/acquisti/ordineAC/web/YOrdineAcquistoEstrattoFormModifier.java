package it.istech.thip.acquisti.ordineAC.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.WebJSTypeList;

import it.thera.thip.acquisti.ordineAC.OrdineAcquisto;
import it.thera.thip.acquisti.ordineAC.web.OrdineAcquistoEstrattoFormModifier;
import it.thera.thip.base.documenti.StatoAvanzamento;

public class YOrdineAcquistoEstrattoFormModifier extends OrdineAcquistoEstrattoFormModifier{
	
	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/istech/thip/acquisti/ordineAC/YOrdineAcquistoEstrattoPers.js", getServletEnvironment().getRequest()));
	}
	
	public void writePulsantiBarraAzioniStandard(JspWriter out) throws IOException {
		super.writePulsantiBarraAzioniStandard(out);
		BODataCollector boDC = this.getBODataCollector();
		OrdineAcquisto doc = (OrdineAcquisto)boDC.getBo();
		if (doc.getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO && doc.getFlagRiservatoUtente2() == '-' && doc.getDatiComuniEstesi().getStato() == 'V') {
			out.println("<td  nowrap=\"true\" height=\"0\">");
			out.println("<button name=\"thgenera\" id=\"thgenera\" onclick=\"genera()\" style=\"width:30px;height:30px;\" type=\"button\" title=\"" + ResourceLoader.getString("it/sicons/thip/base/comuniVenAcq/resources/Documento", "Genera") + "\">");
			out.println("<img border=\"0\" width=\"24px\" height=\"24px\" src=\"it/sicons/thip/modula/images/Modula.ico\" >");
			out.println("</button>");
			out.println("</td>\n");
		}

	}
}
