package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YModulaToPanthTM extends TableManager {

	/**
	 * Attributo ID_AZIENDA
	 */
	public static final String ID_AZIENDA = "ID_AZIENDA";

	/**
	 * Attributo STATO
	 */
	public static final String STATO = "STATO";

	/**
	 * Attributo R_UTENTE_CRZ
	 */
	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	/**
	 * Attributo TIMESTAMP_CRZ
	 */
	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	/**
	 * Attributo R_UTENTE_AGG
	 */
	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	/**
	 * Attributo TIMESTAMP_AGG
	 */
	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	/**
	 * Attributo ID
	 */
	public static final String ID = "ID";

	/**
	 * Attributo ORDINE
	 */
	public static final String ORDINE = "ORDINE";

	/**
	 * Attributo ARTICOLO
	 */
	public static final String ARTICOLO = "ARTICOLO";

	/**
	 * Attributo TIPO_MOV
	 */
	public static final String TIPO_MOV = "TIPO_MOV";

	/**
	 * Attributo TIPO_DOC
	 */
	public static final String TIPO_DOC = "TIPO_DOC";

	/**
	 * Attributo QTA_EVASA_UM_PRM
	 */
	public static final String QTA_EVASA_UM_PRM = "QTA_EVASA_UM_PRM";

	/**
	 *  TABLE_NAME
	 */
	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YMODULA_TO_PANTH";

	/**
	 *  instance
	 */
	private static TableManager cInstance;

	/**
	 *  CLASS_NAME
	 */
	private static final String CLASS_NAME = it.istech.thip.base.modula.YModulaToPanth.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YModulaToPanthTM.class);
		}
		return cInstance;
	}

	public YModulaToPanthTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Id", ID, "getIntegerObject");
		addAttribute("Ordine", ORDINE);
		addAttribute("Articolo", ARTICOLO);
		addAttribute("TipoMov", TIPO_MOV);
		addAttribute("TipoDoc", TIPO_DOC);
		addAttribute("QtaEvasaUmPrm", QTA_EVASA_UM_PRM);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + ID);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(ID + ", " + ORDINE + ", " + ARTICOLO + ", " + TIPO_MOV
				+ ", " + TIPO_DOC + ", " + QTA_EVASA_UM_PRM + ", " + ID_AZIENDA + ", " + STATO
				+ ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
				);
	}

}