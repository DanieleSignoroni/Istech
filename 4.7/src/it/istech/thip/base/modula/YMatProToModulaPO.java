/*
 * @(#)YMatProToModulaPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/03/2024 at 12:51:48
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/03/2024    Wizard     Codice generato da Wizard
 *
 */
package it.istech.thip.base.modula;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.articolo.Articolo;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YMatProToModulaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YMatProToModula cInstance;

  /**
   * Attributo iQtaOriginale
   */
  protected BigDecimal iQtaOriginale;

  /**
   * Attributo iQtaEvasa
   */
  protected BigDecimal iQtaEvasa;

  /**
   * Attributo iQtaResidua
   */
  protected BigDecimal iQtaResidua;

  /**
   * Attributo iGiacenza
   */
  protected BigDecimal iGiacenza;

  /**
   * Attributo iQtaDaEvadere
   */
  protected BigDecimal iQtaDaEvadere;

  /**
   * Attributo iRAnnoOrd
   */
  protected String iRAnnoOrd;

  /**
   * Attributo iRNumeroOrd
   */
  protected String iRNumeroOrd;

  /**
   * Attributo iRRigaAttivita
   */
  protected Integer iRRigaAttivita;

  /**
   * Attributo iRRigaMateriale
   */
  protected Integer iRRigaMateriale;

  /**
   * Attributo iRelarticolo
   */
  protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

  
  /**
   *  retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YMatProToModula)Factory.createObject(YMatProToModula.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return YMatProToModula
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static YMatProToModula elementWithKey(String key, int lockType) throws SQLException {
    return (YMatProToModula)PersistentObject.elementWithKey(YMatProToModula.class, key, lockType);
  }

  /**
   * YMatProToModulaPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public YMatProToModulaPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param qtaOriginale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQtaOriginale(BigDecimal qtaOriginale) {
    this.iQtaOriginale = qtaOriginale;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQtaOriginale() {
    return iQtaOriginale;
  }

  /**
   * Valorizza l'attributo. 
   * @param qtaEvasa
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQtaEvasa(BigDecimal qtaEvasa) {
    this.iQtaEvasa = qtaEvasa;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQtaEvasa() {
    return iQtaEvasa;
  }

  /**
   * Valorizza l'attributo. 
   * @param qtaResidua
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQtaResidua(BigDecimal qtaResidua) {
    this.iQtaResidua = qtaResidua;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQtaResidua() {
    return iQtaResidua;
  }

  /**
   * Valorizza l'attributo. 
   * @param giacenza
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setGiacenza(BigDecimal giacenza) {
    this.iGiacenza = giacenza;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getGiacenza() {
    return iGiacenza;
  }

  /**
   * Valorizza l'attributo. 
   * @param qtaDaEvadere
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQtaDaEvadere(BigDecimal qtaDaEvadere) {
    this.iQtaDaEvadere = qtaDaEvadere;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQtaDaEvadere() {
    return iQtaDaEvadere;
  }

  /**
   * Valorizza l'attributo. 
   * @param rAnnoOrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRAnnoOrd(String rAnnoOrd) {
    this.iRAnnoOrd = rAnnoOrd;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRAnnoOrd() {
    return iRAnnoOrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param rNumeroOrd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRNumeroOrd(String rNumeroOrd) {
    this.iRNumeroOrd = rNumeroOrd;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRNumeroOrd() {
    return iRNumeroOrd;
  }

  /**
   * Valorizza l'attributo. 
   * @param rRigaAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRRigaAttivita(Integer rRigaAttivita) {
    this.iRRigaAttivita = rRigaAttivita;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getRRigaAttivita() {
    return iRRigaAttivita;
  }

  /**
   * Valorizza l'attributo. 
   * @param rRigaMateriale
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRRigaMateriale(Integer rRigaMateriale) {
    this.iRRigaMateriale = rRigaMateriale;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getRRigaMateriale() {
    return iRRigaMateriale;
  }

  /**
   * Valorizza l'attributo. 
   * @param relarticolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRelarticolo(Articolo relarticolo) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relarticolo != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relarticolo.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelarticolo.setObject(relarticolo);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return Articolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Articolo getRelarticolo() {
    return (Articolo)iRelarticolo.getObject();
  }

  /**
   * setRelarticoloKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRelarticoloKey(String key) {
    String oldObjectKey = getKey();
    iRelarticolo.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getRelarticoloKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRelarticoloKey() {
    return iRelarticolo.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param rArticolo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRArticolo(String rArticolo) {
    String key = iRelarticolo.getKey();
    iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRArticolo() {
    String key = iRelarticolo.getKey();
    String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
    return objRArticolo;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YMatProToModulaPO yMatProToModulaPO = (YMatProToModulaPO)obj;
    iRelarticolo.setEqual(yMatProToModulaPO.iRelarticolo);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  /**
   *  setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setRAnnoOrd(KeyHelper.getTokenObjectKey(key, 2));
    setRNumeroOrd(KeyHelper.getTokenObjectKey(key, 3));
    setRRigaAttivita(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
    setRRigaMateriale(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String rAnnoOrd = getRAnnoOrd();
    String rNumeroOrd = getRNumeroOrd();
    Integer rRigaAttivita = getRRigaAttivita();
    Integer rRigaMateriale = getRRigaMateriale();
    Object[] keyParts = {idAzienda, rAnnoOrd, rNumeroOrd, rRigaAttivita, rRigaMateriale};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   *  getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return YMatProToModulaTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/03/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iRelarticolo.getKey();
    iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}

