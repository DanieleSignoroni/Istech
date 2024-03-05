package it.istech.thip.base.modula.importazione;

import java.math.BigDecimal;

import it.thera.thip.cs.ResultSetIterator;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 29/02/2024
 * <br><br>
 * <b>71453	DSSOF3	29/02/2024</b>    
 * <p>Rappresenta la tabella HOST_IMPEXP.dbo.EXP_ORDINI_RIGHE.<br>
 * All'interno della classe sono presenti i suoi attributi e i suoi nomi colonne.<br>
 * Verrano poi utilizzate in un {@link ResultSetIterator} per leggere i dati.
 * </p>
 */

public class YExpOrdiniRighe {
	
	public static final String RIG_ORDINE = "RIG_ORDINE";
	
	public static final String RIG_ARTICOLO = "RIG_ARTICOLO";
	
	public static final String RIG_SUB1 = "RIG_SUB1";
	
	public static final String RIG_HOSTINF = "RIG_HOSTINF";
	
	public static final String RIG_QTAR = "RIG_QTAR";
	
	public static final String RIG_QTAE = "RIG_QTAE";
	
	public static final String RIG_STARIORD = "RIG_STARIORD";
	
	public static final String TABLE_NAME = "dbo.EXP_ORDINI_RIGHE";
	
	protected String rig_ordine = null;
	
	protected String rig_articolo = null;
	
	protected String rig_sub1 = null;
	
	protected String rig_hostinf = null;
	
	protected BigDecimal rig_qtaR = null;
	
	protected BigDecimal rig_qtaE = null;
	
	protected String rig_stariord = null;

	public String getRig_ordine() {
		return rig_ordine;
	}

	public void setRig_ordine(String rig_ordine) {
		this.rig_ordine = rig_ordine;
	}

	public String getRig_articolo() {
		return rig_articolo;
	}

	public void setRig_articolo(String rig_articolo) {
		this.rig_articolo = rig_articolo;
	}

	public String getRig_sub1() {
		return rig_sub1;
	}

	public void setRig_sub1(String rig_sub1) {
		this.rig_sub1 = rig_sub1;
	}

	public String getRig_hostinf() {
		return rig_hostinf;
	}

	public void setRig_hostinf(String rig_hostinf) {
		this.rig_hostinf = rig_hostinf;
	}

	public BigDecimal getRig_qtaR() {
		return rig_qtaR;
	}

	public void setRig_qtaR(BigDecimal rig_qtaR) {
		this.rig_qtaR = rig_qtaR;
	}

	public BigDecimal getRig_qtaE() {
		return rig_qtaE;
	}

	public void setRig_qtaE(BigDecimal rig_qtaE) {
		this.rig_qtaE = rig_qtaE;
	}

	public String getRig_stariord() {
		return rig_stariord;
	}

	public void setRig_stariord(String rig_stariord) {
		this.rig_stariord = rig_stariord;
	}

	public static String getRigOrdine() {
		return RIG_ORDINE;
	}

	public static String getRigArticolo() {
		return RIG_ARTICOLO;
	}

	public static String getRigSub1() {
		return RIG_SUB1;
	}

	public static String getRigHostinf() {
		return RIG_HOSTINF;
	}

	public static String getRigQtar() {
		return RIG_QTAR;
	}

	public static String getRigQtae() {
		return RIG_QTAE;
	}

	public static String getRigStariord() {
		return RIG_STARIORD;
	}
	
	
}
