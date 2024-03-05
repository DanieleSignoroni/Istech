package it.istech.thip.base.modula.importazione;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.persist.Factory;

import it.thera.thip.cs.ResultSetIterator;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YResultSetIteratorExpGiacenze extends ResultSetIterator{

	public YResultSetIteratorExpGiacenze(ResultSet rs) {
		super(rs);
	}

	@Override
	protected Object createObject() throws SQLException {
		YExpGiacenze giacenza = (YExpGiacenze) Factory.createObject(YExpGiacenze.class);
		giacenza.setGia_DataOraS1(cursor.getString(YExpGiacenze.GIA_DATAORAS1));
		giacenza.setGia_DataOraS2(cursor.getString(YExpGiacenze.GIA_DATAORAS2));
		giacenza.setGia_Articolo(cursor.getString(YExpGiacenze.GIA_ARTICOLO));
		giacenza.setGia_Sub1(cursor.getString(YExpGiacenze.GIA_SUB1));
		giacenza.setGia_Sub2(cursor.getString(YExpGiacenze.GIA_SUB2));
		giacenza.setGia_Stamate(cursor.getString(YExpGiacenze.GIA_STAMATE));
		giacenza.setGia_TipoConf(cursor.getString(YExpGiacenze.GIA_TIPOCONF));
		giacenza.setGia_Dscad1(cursor.getString(YExpGiacenze.GIA_DSCAD1));
		giacenza.setGia_Dscad2(cursor.getString(YExpGiacenze.GIA_DSCAD2));
		giacenza.setGia_Giac(cursor.getBigDecimal(YExpGiacenze.GIA_GIAC));
		giacenza.setGia_Ver(cursor.getBigDecimal(YExpGiacenze.GIA_VER));
		giacenza.setGia_Pre(cursor.getBigDecimal(YExpGiacenze.GIA_PRE));
		giacenza.setGia_Articolo_Des(cursor.getString(YExpGiacenze.GIA_ARTICOLO_DES));
		giacenza.setGia_Articolo_Note(cursor.getString(YExpGiacenze.GIA_ARTICOLO_NOTE));
		giacenza.setGia_Articolo_Dcrea1(cursor.getString(YExpGiacenze.GIA_ARTICOLO_DCREA1));
		giacenza.setGia_Articolo_Dcrea2(cursor.getString(YExpGiacenze.GIA_ARTICOLO_DCREA2));
		giacenza.setGia_Articolo_UMI(cursor.getString(YExpGiacenze.GIA_ARTICOLO_UMI));
		giacenza.setGia_Articolo_PMU(cursor.getString(YExpGiacenze.GIA_ARTICOLO_PMU));
		giacenza.setGia_Articolo_Sottosco(cursor.getString(YExpGiacenze.GIA_ARTICOLO_SOTTOSCO));
		return giacenza;
	}

	
}
