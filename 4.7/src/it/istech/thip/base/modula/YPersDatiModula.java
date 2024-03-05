package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

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