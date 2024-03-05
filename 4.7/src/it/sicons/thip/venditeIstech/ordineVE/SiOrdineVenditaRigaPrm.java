package it.sicons.thip.venditeIstech.ordineVE;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.KeyHelper;

import it.thera.thip.base.comuniVenAcq.TipoRiga;
import it.thera.thip.magazzino.saldi.DatiSaldo;
import it.thera.thip.magazzino.saldi.SaldoMag;
import it.thera.thip.vendite.ordineVE.OrdineVenditaRigaPrm;
import java.math.BigDecimal;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class SiOrdineVenditaRigaPrm extends OrdineVenditaRigaPrm {
	private static BigDecimal ZERO = new BigDecimal("0.0");

	public ErrorMessage checkSiDisponibilita() {
		ErrorMessage em = null;
		String messaggio = this.getDisponibilitaIniziale();
		if (!messaggio.equals("")) {
			em = new ErrorMessage("SI_0000001", messaggio);
		}

		return em;
	}

	public String getDisponibilitaIniziale() {
		String messaggioErrore = "";
		SaldoMag saldoMag = this.getSaldiBase(this.getIdAzienda(), this.getIdMagazzino(), this.getIdArticolo(), this.getIdVersioneRcs(), this.getIdConfigurazione());
		if (saldoMag == null) {
			return " Per l'articolo " + this.getIdArticolo() + " la giacenza è " + ZERO.toString() + ", la disponibilità " + ZERO.toString() + ". La quantità richiesta è " + this.getQtaInUMPrmMag().toString() + " " + this.getIdUMPrm();
		} else {
			DatiSaldo datiSaldo = saldoMag.getDatiSaldo();
			if (datiSaldo == null) {
				return " Per l'articolo " + this.getIdArticolo() + " la giacenza è " + ZERO.toString() + ", la disponibilità " + ZERO.toString() + ". La quantità richiesta è " + this.getQtaInUMPrmMag().toString() + " " + this.getIdUMPrm();
			} else {
				BigDecimal entrata = this.sum(datiSaldo.getQtaPropostaEntUMPrim(), datiSaldo.getQtaAttesaEntUMPrim());
				BigDecimal uscita = this.sum(datiSaldo.getQtaPropostaUscitaUMPrim(), datiSaldo.getQtaAttesaUscitaUMPrim());
				BigDecimal disponibilitaIniziale = this.sum(datiSaldo.getQtaGiacenzaUMPrim(), entrata);
				disponibilitaIniziale = this.substract(disponibilitaIniziale, uscita);
				if (disponibilitaIniziale.compareTo(ZERO) != 0 && disponibilitaIniziale.compareTo(this.getQtaInUMPrmMag()) >= 0) {
					messaggioErrore = "";
				} else {
					messaggioErrore = " Per l'articolo " + this.getIdArticolo() + " la giacenza è " + datiSaldo.getQtaGiacenzaUMPrim().toString() + ", la disponibilità " + disponibilitaIniziale.toString() + ". La quantità richiesta è " + this.getQtaInUMPrmMag().toString() + " " + this.getIdUMPrm();
				}

				return messaggioErrore;
			}
		}
	}

	public SaldoMag getSaldiBase(String idAzienda, String idMagazzino, String idArticolo, Integer idVersione, Integer idConfigurazione) {
		String saldoMagKey = KeyHelper.buildObjectKey(new Object[]{idAzienda, idMagazzino, idArticolo, idVersione, idConfigurazione, "DUMMY"});

		try {
			return SaldoMag.elementWithKey(saldoMagKey, 0);
		} catch (Exception var8) {
			var8.printStackTrace(Trace.excStream);
			return null;
		}
	}

	public BigDecimal sum(BigDecimal val1, BigDecimal val2) {
		if (val1 == null && val2 == null) {
			return null;
		} else if (val1 == null) {
			return val2.add(ZERO);
		} else {
			return val2 == null ? val1.add(ZERO) : val1.add(val2);
		}
	}

	public BigDecimal substract(BigDecimal val1, BigDecimal val2) {
		if (val1 == null && val2 == null) {
			return null;
		} else if (val1 == null) {
			return val2.negate();
		} else {
			return val2 == null ? val1.subtract(ZERO) : val1.subtract(val2);
		}
	}

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