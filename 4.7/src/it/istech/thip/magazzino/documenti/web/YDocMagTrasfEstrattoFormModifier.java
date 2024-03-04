package it.istech.thip.magazzino.documenti.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.WebJSTypeList;

import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.base.documenti.web.DocumentoEstrattoFormModifier;
import it.thera.thip.magazzino.documenti.DocMagTrasferimento;

public class YDocMagTrasfEstrattoFormModifier extends DocumentoEstrattoFormModifier{
	
	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/istech/thip/magazzino/documenti/YDocMagTrasfEstrattoPers.js", getServletEnvironment().getRequest()));
	}
	
	public void writePulsantiBarraAzioniStandard(JspWriter out) throws IOException {
		super.writePulsantiBarraAzioniStandard(out);
		BODataCollector boDC = this.getBODataCollector();
		DocMagTrasferimento doc = (DocMagTrasferimento)boDC.getBo();
		if (doc.getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO && doc.getFlagRiservatoUtente2() == '-' && doc.getDatiComuniEstesi().getStato() == 'V') {
			out.println("<td  nowrap=\"true\" height=\"0\">");
			out.println("<button name=\"thgenera\" id=\"thgenera\" onclick=\"genera()\" style=\"width:30px;height:30px;\" type=\"button\" title=\"" + ResourceLoader.getString("it/sicons/thip/base/comuniVenAcq/resources/Documento", "Genera") + "\">");
			out.println("<img border=\"0\" width=\"24px\" height=\"24px\" src=\"it/sicons/thip/modula/images/Modula.ico\" >");
			out.println("</button>");
			out.println("</td>\n");
		}

	}
}
