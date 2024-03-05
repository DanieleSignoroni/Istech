package it.istech.thip.base.modula.importazione;

import java.util.ArrayList;
import java.util.List;

import it.thera.thip.cs.ResultSetIterator;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 29/02/2024
 * <br><br>
 * <b>71453	DSSOF3	29/02/2024</b>    
 * <p>Rappresenta la tabella HOST_IMPEXP.dbo.EXP_ORDINI.<br>
 * All'interno della classe sono presenti i suoi attributi e i suoi nomi colonne.<br>
 * Verrano poi utilizzate in un {@link ResultSetIterator} per leggere i dati.
 * </p>
 */

public class YExpOrdini {

	public static final String ORD_ORDINE = "ORD_ORDINE";

	public static final String ORD_DES = "ORD_DES";

	public static final String ORD_TIPOOP = "ORD_TIPOOP";

	public static final String ORD_STATO = "ORD_STATO";
	
	public static final String TABLE_NAME = "dbo.EXP_ORDINI";

	protected String ord_ordine = null;

	protected String ord_des = null;

	protected String ord_tipoop = null;

	protected String ord_stato = null;

	protected List<YExpOrdiniRighe> righe = null;
	
	public YExpOrdini() {
		initializeRighe();
	}

	public String getOrd_ordine() {
		return ord_ordine;
	}
	public void setOrd_ordine(String ord_ordine) {
		this.ord_ordine = ord_ordine;
	}
	public String getOrd_des() {
		return ord_des;
	}
	public void setOrd_des(String ord_des) {
		this.ord_des = ord_des;
	}
	public String getOrd_tipoop() {
		return ord_tipoop;
	}
	public void setOrd_tipoop(String ord_tipoop) {
		this.ord_tipoop = ord_tipoop;
	}
	public String getOrd_stato() {
		return ord_stato;
	}
	public void setOrd_stato(String ord_stato) {
		this.ord_stato = ord_stato;
	}
	
	public List<YExpOrdiniRighe> getRighe() {
		return righe;
	}

	public void setRighe(List<YExpOrdiniRighe> righe) {
		this.righe = righe;
	}

	protected void initializeRighe() {
		righe = new ArrayList<YExpOrdiniRighe>();
	}
}
