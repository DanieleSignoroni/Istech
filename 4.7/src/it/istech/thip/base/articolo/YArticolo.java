package it.istech.thip.base.articolo;

import it.thera.thip.base.articolo.Articolo;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 01/03/2024
 * <br><br>
 * <b></b>
 * <p>Prima stesura.<br>
 * Aggiungere flag per gestire l'esportazione verso Modula dell'anagrafica.
 * </p>
 */

public class YArticolo extends Articolo{
	
	protected boolean iEsportatoModula;

	public boolean isEsportatoModula() {
		return iEsportatoModula;
	}

	public void setEsportatoModula(boolean iEsportatoModula) {
		this.iEsportatoModula = iEsportatoModula;
	}
	
}
