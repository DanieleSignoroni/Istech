package it.istech.thip.base.modula;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YPersDatiModulaTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String R_SERIE_DOC_TRA = "R_SERIE_DOC_TRA";

	public static final String R_CAU_TES_DOC_TRA = "R_CAU_TES_DOC_TRA";

	public static final String R_NUMERATORE_DOC_TRA = "R_NUMERATORE_DOC_TRA";

	public static final String R_SERIE_DOC_GEN = "R_SERIE_DOC_GEN";

	public static final String R_NUMERATORE_DOC_GEN = "R_NUMERATORE_DOC_GEN";

	public static final String R_CAU_DOC_GEN = "R_CAU_DOC_GEN";

	public static final String R_CAU_DOC_GEN_RIG_VERS = "R_CAU_DOC_GEN_RIG_VERS";

	public static final String R_CAU_DOC_GEN_RIG_PREL = "R_CAU_DOC_GEN_RIG_PREL";

	public static final String R_SERIE_DOC_RIALL = "R_SERIE_DOC_RIALL";

	public static final String R_NUMERATORE_DOC_RIALL = "R_NUMERATORE_DOC_RIALL";

	public static final String R_CAU_DOC_RIALL = "R_CAU_DOC_RIALL";

	public static final String R_CAU_DOC_RIALL_RIG_RETPOS = "R_CAU_DOC_RIALL_RIG_RETPOS";

	public static final String R_CAU_DOC_RIALL_RIG_RETNEG = "R_CAU_DOC_RIALL_RIG_RETNEG";

	public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YPERS_DATI_MODULA";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.istech.thip.base.modula.YPersDatiModula.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(YPersDatiModulaTM.class);
		}
		return cInstance;
	}

	public YPersDatiModulaTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("RSerieDocTra", R_SERIE_DOC_TRA);
		addAttribute("RNumeratoreDocTra", R_NUMERATORE_DOC_TRA);
		addAttribute("RCauTesDocTra", R_CAU_TES_DOC_TRA);
		addAttribute("RSerieDocGen", R_SERIE_DOC_GEN);
		addAttribute("RNumeratoreDocGen", R_NUMERATORE_DOC_GEN);
		addAttribute("RCauDocGen", R_CAU_DOC_GEN);
		addAttribute("RCauDocGenRigVers", R_CAU_DOC_GEN_RIG_VERS);
		addAttribute("RCauDocGenRigPrel", R_CAU_DOC_GEN_RIG_PREL);
		addAttribute("RSerieDocRiall", R_SERIE_DOC_RIALL);
		addAttribute("RNumeratoreDocRiall", R_NUMERATORE_DOC_RIALL);
		addAttribute("RCauDocRiall", R_CAU_DOC_RIALL);
		addAttribute("RCauDocRiallRigRetneg", R_CAU_DOC_RIALL_RIG_RETNEG);
		addAttribute("RCauDocRiallRigRetpos", R_CAU_DOC_RIALL_RIG_RETPOS);

		setKeys(ID_AZIENDA);
	}

	private void init() throws SQLException {
		configure(ID_AZIENDA + ", " + R_SERIE_DOC_TRA + ", " + R_NUMERATORE_DOC_TRA + ", " + R_CAU_TES_DOC_TRA + ", "
				+ R_SERIE_DOC_GEN + ", " + R_NUMERATORE_DOC_GEN + ", " + R_CAU_DOC_GEN + ", " + R_CAU_DOC_GEN_RIG_VERS
				+ ", " + R_CAU_DOC_GEN_RIG_PREL + ", " + R_SERIE_DOC_RIALL + ", " + R_NUMERATORE_DOC_RIALL + ", "
				+ R_CAU_DOC_RIALL + ", " + R_CAU_DOC_RIALL_RIG_RETNEG + ", " + R_CAU_DOC_RIALL_RIG_RETPOS);
	}

}
