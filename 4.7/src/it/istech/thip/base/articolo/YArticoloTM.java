package it.istech.thip.base.articolo;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;

import it.thera.thip.base.articolo.ArticoloTM;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 01/03/2024
 * <br><br>
 * <b></b>
 * <p>Prima stesura.<br>
 * Aggiungere flag per gestire l'esportazione verso Modula dell'anagrafica.
 * </p>
 */

public class YArticoloTM extends ArticoloTM{

	public static final String ESPORTATO_MODULA = "ESPORTATO_MODULA";

	public static final String TABLE_NAME_EXT = SystemParam.getSchema("THIPPERS") + "YARTICOLI";

	private static final String CLASS_NAME = it.istech.thip.base.articolo.YArticolo.class.getName();

	public YArticoloTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		
		super.initialize();
		setObjClassName(CLASS_NAME);
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		linkTable(TABLE_NAME_EXT);
		addAttributeOnTable("EsportatoModula", ESPORTATO_MODULA, TABLE_NAME_EXT);
	}
}
