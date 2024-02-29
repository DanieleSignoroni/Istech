package it.sicons.thip.vendite.ordineVE.web;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.collector.BODataCollector;
import it.thera.thip.vendite.ordineVE.OrdineVendita;
import it.thera.thip.vendite.ordineVE.web.OrdineVenditaEstrattoFormModifier;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;

public class SiOrdineVenditaEstrattoFormModifier extends OrdineVenditaEstrattoFormModifier {
	
	public static final String SIRESDOC = "it/sicons/thip/base/comuniVenAcq/resources/Documento";

	public void writePulsantiBarraAzioniStandard(JspWriter out) throws IOException {
		super.writePulsantiBarraAzioniStandard(out);
		//DocumentoDatiSessione datiSessione = DocumentoDatiSessione.getDocumentoDatiSessione(this.getServletEnvironment());
		//String azione = datiSessione.getModoIniziale();
		BODataCollector boDC = this.getBODataCollector();
		OrdineVendita doc = (OrdineVendita)boDC.getBo();
		if (doc.getStatoAvanzamento() == '2' && doc.getFlagRiservatoUtente2() == '-' && doc.getDatiComuniEstesi().getStato() == 'V') {
			out.println("<td  nowrap=\"true\" height=\"0\">");
			out.println("<button name=\"thgenera\" id=\"thgenera\" onclick=\"genera()\" style=\"width:30px;height:30px;\" type=\"button\" title=\"" + ResourceLoader.getString("it/sicons/thip/base/comuniVenAcq/resources/Documento", "Genera") + "\">");
			out.println("<img border=\"0\" width=\"24px\" height=\"24px\" src=\"it/sicons/thip/modula/images/Modula.ico\" >");
			out.println("</button>");
			out.println("</td>\n");
		}

	}
}