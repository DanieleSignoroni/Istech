package it.istech.thip.acquisti.documentoAC;

import it.thera.thip.acquisti.documentoAC.DocumentoAcqRigaPrm;
import it.thera.thip.acquisti.generaleAC.CausaleDocumentoRigaAcq;
import it.thera.thip.base.comuniVenAcq.TipoRiga;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Andrea Gatta 05/03/2024
 * <br><br>
 * <b>71453	AGSOF3 05/03/2024</b>
 * <p>
 * </p>
 */

public class YDocumentoAcqRigaPrm extends DocumentoAcqRigaPrm {
	
	/**
	 * Controllo che il magazzino di riga sia corretto per il passaggio dei dati a modula
	 * @return true se magazzino = 001
	 */
	public boolean checkMagazzinoModula() {
		return "001".equals(this.getIdMagazzino());
	}

	/**
	 * Controllo che la riga sia tipo riga MERCE oppure OMAGGIO	
	 * @return true se la riga è MERCE o OMAGGIO
	 */
	public boolean checkIsMerceOrOmaggio() {
		return this.getTipoRiga() == TipoRiga.MERCE || this.getTipoRiga() == TipoRiga.OMAGGIO;
	}

	/**
	 * Controllo che la classe A dell'articolo sia = MO
	 * @return true se la classe A dell'articolo = MO
	 */
	public boolean checkIsClasseAMO() {
		return "MO".equals(this.getArticolo().getIdClasseA());
	}

	/**
	 * rendo pubblico il metodo isRigaMerceValore
	 * @return isRigaMerceAValore()
	 */
	public boolean checkIsRigaMerceAValore() {
		return ((CausaleDocumentoRigaAcq)this.getCausaleRiga()).isRigaMerceAValore();
	}

}
