package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.common.ErrorMessage;

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
