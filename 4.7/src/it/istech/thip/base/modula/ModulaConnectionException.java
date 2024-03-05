package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.common.ErrorMessage;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class ModulaConnectionException extends SQLException{

	private static final long serialVersionUID = 1L;

	public ModulaConnectionException() {
		super();
	}

	public ModulaConnectionException(String reason) {
		super(reason);
	}

	public ModulaConnectionException(ErrorMessage em) {
		super(em.getText());
	}
}
