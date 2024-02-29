package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YOrdVenToModulaTM extends TableManager {

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
	 * Attributo R_ANNO_ORD_VEN
	 */
	public static final String R_ANNO_ORD_VEN = "R_ANNO_ORD_VEN";

	/**
	 * Attributo R_NUMERO_ORD_VEN
	 */
	public static final String R_NUMERO_ORD_VEN = "R_NUMERO_ORD_VEN";

	/**
	 * Attributo R_RIGA_ORD
	 */
	public static final String R_RIGA_ORD = "R_RIGA_ORD";

	/**
	 * Attributo R_DET_RIGA_ORD
	 */
	public static final String R_DET_RIGA_ORD = "R_DET_RIGA_ORD";

	/**
	 * Attributo R_ARTICOLO
	 */
	public static final String R_ARTICOLO = "R_ARTICOLO";

	/**
	 * Attributo QTA_ORIGINALE
	 */
	public static final String QTA_ORIGINALE = "QTA_ORIGINALE";

	/**
	 * Attributo QTA_EVASA
	 */
	public static final String QTA_EVASA = "QTA_EVASA";

	/**
	 * Attributo QTA_RESIDUA
	 */
	public static final String QTA_RESIDUA = "QTA_RESIDUA";

	/**
	 * Attributo GIACENZA
	 */
	public static final String GIACENZA = "GIACENZA";

	/**
	 * Attributo QTA_DA_EVADERE
	 */
	public static final String QTA_DA_EVADERE = "QTA_DA_EVADERE";

	/**
	 *  TABLE_NAME
	 */
	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YORD_VEN_TO_MODULA";

	/**
	 *  instance
	 */
	private static TableManager cInstance;

	/**
	 *  CLASS_NAME
	 */
	private static final String CLASS_NAME = it.istech.thip.base.modula.YOrdVenToModula.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager)Factory.createObject(YOrdVenToModulaTM.class);
		}
		return cInstance;
	}

	public YOrdVenToModulaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("RAnnoOrdVen", R_ANNO_ORD_VEN);
		addAttribute("RNumeroOrdVen", R_NUMERO_ORD_VEN);
		addAttribute("RRigaOrd", R_RIGA_ORD, "getIntegerObject");
		addAttribute("RDetRigaOrd", R_DET_RIGA_ORD, "getIntegerObject");
		addAttribute("QtaOriginale", QTA_ORIGINALE);
		addAttribute("QtaEvasa", QTA_EVASA);
		addAttribute("QtaResidua", QTA_RESIDUA);
		addAttribute("Giacenza", GIACENZA);
		addAttribute("QtaDaEvadere", QTA_DA_EVADERE);
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("RArticolo", R_ARTICOLO);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_ANNO_ORD_VEN + "," + R_NUMERO_ORD_VEN + "," + R_RIGA_ORD + "," + R_DET_RIGA_ORD);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}