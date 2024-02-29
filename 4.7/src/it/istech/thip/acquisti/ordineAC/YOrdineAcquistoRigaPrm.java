package it.istech.thip.acquisti.ordineAC;

import java.math.BigDecimal;

import it.thera.thip.acquisti.ordineAC.OrdineAcquistoRigaPrm;
import it.thera.thip.base.comuniVenAcq.TipoRiga;

public class YOrdineAcquistoRigaPrm extends OrdineAcquistoRigaPrm{
	
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
		return this.isRigaMerceValore();
	}

	/**
	 * Controllo che esista della qta residua
	 * @return true se residuo > 0
	 */
	public boolean checkResiduoPresente() {
	    BigDecimal qtaResidua = this.getQuantitaResiduo().getQuantitaInUMRif();
	    return qtaResidua != null && qtaResidua.compareTo(BigDecimal.ZERO) == 1;
	}
}
