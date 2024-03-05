package it.sicons.thip.acquisti.documentoAC.web;

import com.thera.thermfw.base.ResourceLoader;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.security.Security;
import it.thera.thip.acquisti.documentoAC.DocumentoAcquisto;
import it.thera.thip.acquisti.documentoAC.web.DocumentoAcquistoEstrattoFormModifier;
import it.thera.thip.acquisti.generaleAC.TipoDocumentoAcq;
import it.thera.thip.base.documenti.StatoAttivita;
import it.thera.thip.base.documenti.StatoAvanzamento;
import it.thera.thip.base.documenti.web.DocumentoDatiSessione;
import it.thera.thip.base.generale.ParametroPsn;
import it.thera.thip.cs.DatiComuniEstesi;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class SiDocumentoAcquistoEstrattoFormModifier extends DocumentoAcquistoEstrattoFormModifier {
	
	public void writePulsantiBarraAzioniStandard(JspWriter out) throws IOException {
		super.writePulsantiBarraAzioniStandard(out);
		boolean regredisco = false;
		boolean confermaRegredisco = false;
		DocumentoDatiSessione datiSessione = DocumentoDatiSessione.getDocumentoDatiSessione(this.getServletEnvironment());
		String azione = datiSessione.getModoIniziale();
		BODataCollector boDC = this.getBODataCollector();
		DocumentoAcquisto doc = (DocumentoAcquisto)boDC.getBo();
		if (doc.getStatoAvanzamento() == StatoAvanzamento.DEFINITIVO 
				&& doc.getDatiComuniEstesi().getStato() == DatiComuniEstesi.VALIDO
				&& doc.getCollegatoAMagazzino() == StatoAttivita.ESEGUITO
				&& (doc.getTipoDocumento() == TipoDocumentoAcq.ACQUISTO || doc.getTipoDocumento() == TipoDocumentoAcq.RESO_FORNITORE)) {
			out.println("<td  nowrap=\"true\" height=\"0\">");
			out.println("<button name=\"thGeneraListe\" id=\"thGeneraListe\" onclick=\"generaLista()\"  style=\"width:30px;height:30px;\" type=\"button\" title=\"" + ResourceLoader.getString("it.sicons.thip.modula.resources.SiModula", "InvModula") + "\">");
			out.println("<img border=\"0\" width=\"24px\" height=\"24px\" src=\"it/sicons/thip/modula/images/Modula.ico\" >");
			out.println("</button>");
			out.println("</td>");
		}

		if ((doc.getCollegatoAMagazzino() == '2' || doc.getCollegatoAMagazzino() == '3' || doc.getCollegatoAMagazzino() == '1') && doc.getStatoAvanzamento() == '2' && (doc.getCollegatoAMagazzino() == '2' || doc.isConRighe() && !doc.isTutteLeRigheRegredite())) {
			regredisco = true;
			confermaRegredisco = true;
			if (doc.getFlagRiservatoUtente1() != '-') {
				String utente = Security.getCurrentUser().getId();
				confermaRegredisco = this.ricercaCodiceInParmPersonalizzazione("SiModula", "Utenti", utente);
			}
		}

		if (azione.equals("UPDATE") && regredisco && !confermaRegredisco) {
			out.println("<script language='JavaScript1.2'>");
			out.println("document.getElementById('thregressione').style.display='none';");
			out.println("</script>");
		}

	}

	public boolean ricercaCodiceInParmPersonalizzazione(String funzione, String paramentro, String codice) {
		boolean trovato = false;
		String list = null;
		ParametroPsn paramPers = null;
		paramPers = ParametroPsn.getParametroPsn(funzione, paramentro);
		if (paramPers != null && paramPers.getValore() != null) {
			list = paramPers.getValore();
		} else {
			trovato = true;
		}

		if (!trovato) {
			String listaCodiciDaIncludere = list;
			int pos = list.indexOf(45);
			byte posIni = 0;

			while(pos > 0) {
				String azienda = listaCodiciDaIncludere.substring(posIni, pos).toUpperCase();
				if (azienda.trim().equals(codice)) {
					trovato = true;
					pos = -1;
				} else {
					listaCodiciDaIncludere = listaCodiciDaIncludere.substring(pos + 1, listaCodiciDaIncludere.length());
					pos = listaCodiciDaIncludere.indexOf(45);
				}
			}
		}

		return trovato;
	}
}
