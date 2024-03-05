package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YDocVenToModulaTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_ARTICOLO = "R_ARTICOLO";

	public static final String QTA_ORIGINALE = "QTA_ORIGINALE";

	public static final String QTA_EVASA = "QTA_EVASA";

	public static final String QTA_RESIDUA = "QTA_RESIDUA";

	public static final String GIACENZA = "GIACENZA";

	public static final String QTA_DA_EVADERE = "QTA_DA_EVADERE";

	public static final String R_ANNO_DOC_VEN = "R_ANNO_DOC_VEN";

	public static final String R_NUMERO_DOC_VEN = "R_NUMERO_DOC_VEN";

	public static final String R_RIGA_DOC = "R_RIGA_DOC";

	public static final String R_DET_RIGA_DOC = "R_DET_RIGA_DOC";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YDOC_VEN_TO_MODULA";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.istech.thip.base.modula.YDocVenToModula.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YDocVenToModulaTM.class);
		}
		return cInstance;
	}

	public YDocVenToModulaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("QtaOriginale", QTA_ORIGINALE);
		addAttribute("QtaEvasa", QTA_EVASA);
		addAttribute("QtaResidua", QTA_RESIDUA);
		addAttribute("Giacenza", GIACENZA);
		addAttribute("QtaDaEvadere", QTA_DA_EVADERE);
		addAttribute("RAnnoDocVen", R_ANNO_DOC_VEN);
		addAttribute("RNumeroDocVen", R_NUMERO_DOC_VEN);
		addAttribute("RRigaDoc", R_RIGA_DOC, "getIntegerObject");
		addAttribute("RDetRigaDoc", R_DET_RIGA_DOC, "getIntegerObject");
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("RArticolo", R_ARTICOLO);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_ANNO_DOC_VEN + "," + R_NUMERO_DOC_VEN + "," + R_RIGA_DOC + "," + R_DET_RIGA_DOC);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}
