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

public class YPanthToModulaTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String TIPO_DOC = "TIPO_DOC";

	public static final String ID_ANNO_DOC = "ID_ANNO_DOC";

	public static final String ID_NUMERO_DOC = "ID_NUMERO_DOC";

	public static final String ID_RIGA_DOC = "ID_RIGA_DOC";

	public static final String ID_DET_RIGA_DOC = "ID_DET_RIGA_DOC";

	public static final String TIPO_MOV = "TIPO_MOV";

	public static final String QTA_EVASA_UM_PRM = "QTA_EVASA_UM_PRM";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPANTH_TO_MODULA";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.istech.thip.base.modula.YPanthToModula.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YPanthToModulaTM.class);
		}
		return cInstance;
	}

	public YPanthToModulaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("TipoDoc", TIPO_DOC);
		addAttribute("IdAnnoDoc", ID_ANNO_DOC);
		addAttribute("IdNumeroDoc", ID_NUMERO_DOC);
		addAttribute("IdRigaDoc", ID_RIGA_DOC, "getIntegerObject");
		addAttribute("IdDetRigaDoc", ID_DET_RIGA_DOC, "getIntegerObject");
		addAttribute("TipoMov", TIPO_MOV);
		addAttribute("QtaEvasaUmPrm", QTA_EVASA_UM_PRM);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + TIPO_DOC + "," + ID_ANNO_DOC + "," + ID_NUMERO_DOC + "," + ID_RIGA_DOC + ","
				+ ID_DET_RIGA_DOC);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(TIPO_DOC + ", " + ID_ANNO_DOC + ", " + ID_NUMERO_DOC + ", " + ID_RIGA_DOC + ", " + ID_DET_RIGA_DOC
				+ ", " + TIPO_MOV + ", " + QTA_EVASA_UM_PRM + ", " + ID_AZIENDA + ", " + STATO + ", " + R_UTENTE_CRZ
				+ ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
	}

}