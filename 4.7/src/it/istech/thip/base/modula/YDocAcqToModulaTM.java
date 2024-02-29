package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class YDocAcqToModulaTM extends TableManager {

  
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
   * Attributo R_RIGA_DOC
   */
  public static final String R_RIGA_DOC = "R_RIGA_DOC";

  /**
   * Attributo R_DET_RIGA_DOC
   */
  public static final String R_DET_RIGA_DOC = "R_DET_RIGA_DOC";

  /**
   * Attributo R_ANNO_DOC_ACQ
   */
  public static final String R_ANNO_DOC_ACQ = "R_ANNO_DOC_ACQ";

  /**
   * Attributo R_NUMERO_DOC_ACQ
   */
  public static final String R_NUMERO_DOC_ACQ = "R_NUMERO_DOC_ACQ";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YDOC_ACQ_TO_MODULA";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.istech.thip.base.modula.YDocAcqToModula.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 29/02/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YDocAcqToModulaTM.class);
    }
    return cInstance;
  }

  /**
   *  YDocAcqToModulaTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 29/02/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public YDocAcqToModulaTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 29/02/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  /**
   *  initializeRelation
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 29/02/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("QtaOriginale", QTA_ORIGINALE);
    addAttribute("QtaEvasa", QTA_EVASA);
    addAttribute("QtaResidua", QTA_RESIDUA);
    addAttribute("Giacenza", GIACENZA);
    addAttribute("QtaDaEvadere", QTA_DA_EVADERE);
    addAttribute("RRigaDoc", R_RIGA_DOC, "getIntegerObject");
    addAttribute("RDetRigaDoc", R_DET_RIGA_DOC, "getIntegerObject");
    addAttribute("RAnnoDocAcq", R_ANNO_DOC_ACQ);
    addAttribute("RNumeroDocAcq", R_NUMERO_DOC_ACQ);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("RArticolo", R_ARTICOLO);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + R_ANNO_DOC_ACQ + "," + R_NUMERO_DOC_ACQ + "," + R_RIGA_DOC + "," + R_DET_RIGA_DOC);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 29/02/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure();
  }

}

