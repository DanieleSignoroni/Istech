package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.azienda.Azienda;

public class YPersDatiModula extends YPersDatiModulaPO {

	public ErrorMessage checkDelete() {
		return null;
	}
	
	public static YPersDatiModula getCurrenPersDatiModula() {
		try {
			return (YPersDatiModula) YPersDatiModula.elementWithKey(YPersDatiModula.class, Azienda.getAziendaCorrente(), PersistentObject.NO_LOCK);
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}

}