package it.istech.thip.base.modula.importazione;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.persist.Factory;

import it.thera.thip.cs.ResultSetIterator;

public class YResultSetIteratoExpOrdiniRighe extends ResultSetIterator{

	public YResultSetIteratoExpOrdiniRighe(ResultSet rs) {
		super(rs);
	}

	@Override
	protected Object createObject() throws SQLException {
		YExpOrdiniRighe riga = (YExpOrdiniRighe) Factory.createObject(YExpOrdiniRighe.class);
		riga.setRig_ordine(cursor.getString(YExpOrdiniRighe.RIG_ORDINE));
		riga.setRig_articolo(cursor.getString(YExpOrdiniRighe.RIG_ARTICOLO));
		riga.setRig_sub1(cursor.getString(YExpOrdiniRighe.RIG_SUB1));
		riga.setRig_hostinf(cursor.getString(YExpOrdiniRighe.RIG_HOSTINF));
		riga.setRig_qtaR(cursor.getBigDecimal(YExpOrdiniRighe.RIG_QTAR));
		riga.setRig_qtaE(cursor.getBigDecimal(YExpOrdiniRighe.RIG_QTAE));
		riga.setRig_stariord(cursor.getString(YExpOrdiniRighe.RIG_STARIORD));
		return riga;
	}
}