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

public class YResultSetIteratoExpOrdini extends ResultSetIterator{

	public YResultSetIteratoExpOrdini(ResultSet rs) {
		super(rs);
	}

	@Override
	protected Object createObject() throws SQLException {
		YExpOrdini ordine = (YExpOrdini) Factory.createObject(YExpOrdini.class);
		ordine.setOrd_ordine(cursor.getString(YExpOrdini.ORD_ORDINE));
		ordine.setOrd_des(cursor.getString(YExpOrdini.ORD_DES));
		ordine.setOrd_tipoop(cursor.getString(YExpOrdini.ORD_TIPOOP));
		ordine.setOrd_stato(cursor.getString(YExpOrdini.ORD_STATO));
		return ordine;
	}

}
