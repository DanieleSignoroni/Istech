package it.istech.thip.vendite.documentoVE.web;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.web.WebJSTypeList;

import it.thera.thip.base.documenti.StatoAttivita;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.cs.DatiComuniEstesi;
import it.thera.thip.vendite.documentoVE.DocumentoVendita;
import it.thera.thip.vendite.documentoVE.web.DocumentoVenditaEstrattoFormModifier;
import it.thera.thip.vendite.generaleVE.TipoDocumento;

public class YDocumentoVenditaEstrattoFormModifier extends DocumentoVenditaEstrattoFormModifier{
	
	@Override
	public void writeHeadElements(JspWriter out) throws IOException {
		super.writeHeadElements(out);
		out.println(WebJSTypeList.getImportForJSLibrary("it/istech/thip/vendite/documentoVE/YDocumentoVenditaEstrattoPers.js", getServletEnvironment().getRequest()));
	}
	
	public void writePulsantiBarraAzioniStandard(JspWriter out) throws IOException {
		super.writePulsantiBarraAzioniStandard(out);
		BODataCollector boDC = this.getBODataCollector();
		DocumentoVendita doc = (DocumentoVendita)boDC.getBo();
		if (doc.getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO 
				&& doc.getDatiComuniEstesi().getStato() == DatiComuniEstesi.VALIDO
				&& doc.getCollegatoAMagazzino() == StatoAttivita.ESEGUITO
				&& doc.getTipoDocumento() == TipoDocumento.NOTA_ACCREDITO) {
			out.println("<td  nowrap=\"true\" height=\"0\">");
			out.println("<button name=\"thgenera\" id=\"thgenera\" onclick=\"genera()\" style=\"width:30px;height:30px;\" type=\"button\" title=\"" + ResourceLoader.getString("it/sicons/thip/base/comuniVenAcq/resources/Documento", "Genera") + "\">");
			out.println("<img border=\"0\" width=\"24px\" height=\"24px\" src=\"it/sicons/thip/modula/images/Modula.ico\" >");
			out.println("</button>");
			out.println("</td>\n");
		}

	}
}
