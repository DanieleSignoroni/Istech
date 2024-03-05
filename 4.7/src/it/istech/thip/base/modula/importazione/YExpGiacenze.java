package it.istech.thip.base.modula.importazione;

import java.math.BigDecimal;

import it.thera.thip.cs.ResultSetIterator;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 29/02/2024
 * <br><br>
 * <b>71453	DSSOF3	29/02/2024</b>    
 * <p>Rappresenta la tabella HOST_IMPEXP.dbo.EXP_GIACENZE.<br>
 * All'interno della classe sono presenti i suoi attributi e i suoi nomi colonne.<br>
 * Verrano poi utilizzate in un {@link ResultSetIterator} per leggere i dati.
 * </p>
 */

public class YExpGiacenze {
	
	public static final String GIA_DATAORAS1 = "GIA_DATAORAS1";
	public static final String GIA_DATAORAS2 = "GIA_DATAORAS2";
	public static final String GIA_ARTICOLO = "GIA_ARTICOLO";
	public static final String GIA_SUB1 = "GIA_SUB1";
	public static final String GIA_SUB2 = "GIA_SUB2";
	public static final String GIA_STAMATE = "GIA_STAMATE";
	public static final String GIA_TIPOCONF = "GIA_TIPOCONF";
	public static final String GIA_DSCAD1 = "GIA_DSCAD1";
	public static final String GIA_DSCAD2 = "GIA_DSCAD2";
	public static final String GIA_GIAC = "GIA_GIAC";
	public static final String GIA_VER = "GIA_VER";
	public static final String GIA_PRE = "GIA_PRE";
	public static final String GIA_ARTICOLO_DES = "GIA_ARTICOLO_DES";
	public static final String GIA_ARTICOLO_NOTE = "GIA_ARTICOLO_NOTE";
	public static final String GIA_ARTICOLO_DCREA1 = "GIA_ARTICOLO_DCREA1";
	public static final String GIA_ARTICOLO_DCREA2 = "GIA_ARTICOLO_DCREA2";
	public static final String GIA_ARTICOLO_UMI = "GIA_ARTICOLO_UMI";
	public static final String GIA_ARTICOLO_PMU = "GIA_ARTICOLO_PMU";
	public static final String GIA_ARTICOLO_SOTTOSCO = "GIA_ARTICOLO_SOTTOSCO";
	
	public static final String TABLE_NAME = "dbo.EXP_GIACENZE";
	
	protected String Gia_DataOraS1 = null;
	protected String Gia_DataOraS2 = null;
	protected String Gia_Articolo = null;
	protected String Gia_Sub1 = null;
	protected String Gia_Sub2 = null;
	protected String Gia_Stamate = null;
	protected String Gia_TipoConf = null;
	protected String Gia_Dscad1 = null;
	protected String Gia_Dscad2 = null;
	protected BigDecimal Gia_Giac = null;
	protected BigDecimal Gia_Ver = null;
	protected BigDecimal Gia_Pre = null;
	protected String Gia_Articolo_Des = null;
	protected String Gia_Articolo_Note = null;
	protected String Gia_Articolo_Dcrea1 = null;
	protected String Gia_Articolo_Dcrea2 = null;
	protected String Gia_Articolo_UMI = null;
	protected String Gia_Articolo_PMU = null;
	protected String Gia_Articolo_Sottosco = null;
	
	public String getGia_DataOraS1() {
		return Gia_DataOraS1;
	}
	public void setGia_DataOraS1(String gia_DataOraS1) {
		Gia_DataOraS1 = gia_DataOraS1;
	}
	public String getGia_DataOraS2() {
		return Gia_DataOraS2;
	}
	public void setGia_DataOraS2(String gia_DataOraS2) {
		Gia_DataOraS2 = gia_DataOraS2;
	}
	public String getGia_Articolo() {
		return Gia_Articolo;
	}
	public void setGia_Articolo(String gia_Articolo) {
		Gia_Articolo = gia_Articolo;
	}
	public String getGia_Sub1() {
		return Gia_Sub1;
	}
	public void setGia_Sub1(String gia_Sub1) {
		Gia_Sub1 = gia_Sub1;
	}
	public String getGia_Sub2() {
		return Gia_Sub2;
	}
	public void setGia_Sub2(String gia_Sub2) {
		Gia_Sub2 = gia_Sub2;
	}
	public String getGia_Stamate() {
		return Gia_Stamate;
	}
	public void setGia_Stamate(String gia_Stamate) {
		Gia_Stamate = gia_Stamate;
	}
	public String getGia_TipoConf() {
		return Gia_TipoConf;
	}
	public void setGia_TipoConf(String gia_TipoConf) {
		Gia_TipoConf = gia_TipoConf;
	}
	public String getGia_Dscad1() {
		return Gia_Dscad1;
	}
	public void setGia_Dscad1(String gia_Dscad1) {
		Gia_Dscad1 = gia_Dscad1;
	}
	public String getGia_Dscad2() {
		return Gia_Dscad2;
	}
	public void setGia_Dscad2(String gia_Dscad2) {
		Gia_Dscad2 = gia_Dscad2;
	}
	public BigDecimal getGia_Giac() {
		return Gia_Giac;
	}
	public void setGia_Giac(BigDecimal gia_Giac) {
		Gia_Giac = gia_Giac;
	}
	public BigDecimal getGia_Ver() {
		return Gia_Ver;
	}
	public void setGia_Ver(BigDecimal gia_Ver) {
		Gia_Ver = gia_Ver;
	}
	public BigDecimal getGia_Pre() {
		return Gia_Pre;
	}
	public void setGia_Pre(BigDecimal gia_Pre) {
		Gia_Pre = gia_Pre;
	}
	public String getGia_Articolo_Des() {
		return Gia_Articolo_Des;
	}
	public void setGia_Articolo_Des(String gia_Articolo_Des) {
		Gia_Articolo_Des = gia_Articolo_Des;
	}
	public String getGia_Articolo_Note() {
		return Gia_Articolo_Note;
	}
	public void setGia_Articolo_Note(String gia_Articolo_Note) {
		Gia_Articolo_Note = gia_Articolo_Note;
	}
	public String getGia_Articolo_Dcrea1() {
		return Gia_Articolo_Dcrea1;
	}
	public void setGia_Articolo_Dcrea1(String gia_Articolo_Dcrea1) {
		Gia_Articolo_Dcrea1 = gia_Articolo_Dcrea1;
	}
	public String getGia_Articolo_Dcrea2() {
		return Gia_Articolo_Dcrea2;
	}
	public void setGia_Articolo_Dcrea2(String gia_Articolo_Dcrea2) {
		Gia_Articolo_Dcrea2 = gia_Articolo_Dcrea2;
	}
	public String getGia_Articolo_UMI() {
		return Gia_Articolo_UMI;
	}
	public void setGia_Articolo_UMI(String gia_Articolo_UMI) {
		Gia_Articolo_UMI = gia_Articolo_UMI;
	}
	public String getGia_Articolo_PMU() {
		return Gia_Articolo_PMU;
	}
	public void setGia_Articolo_PMU(String gia_Articolo_PMU) {
		Gia_Articolo_PMU = gia_Articolo_PMU;
	}
	public String getGia_Articolo_Sottosco() {
		return Gia_Articolo_Sottosco;
	}
	public void setGia_Articolo_Sottosco(String gia_Articolo_Sottosco) {
		Gia_Articolo_Sottosco = gia_Articolo_Sottosco;
	}
	
	
}
