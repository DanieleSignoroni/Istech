/*
 * @(#)YPersDatiModulaPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 09/02/2024 at 15:13:32
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 09/02/2024    Wizard     Codice generato da Wizard
 *
 */
package it.istech.thip.base.modula;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.generale.Serie;
import it.thera.thip.magazzino.documenti.CausaleDocumentoTrasf;
import it.thera.thip.magazzino.documenti.CausaleDocumentoGen;
import it.thera.thip.magazzino.documenti.CausaleRigaDocGen;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YPersDatiModulaPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static YPersDatiModula cInstance;

  /**
   * Attributo iRelseriedoctrasf
   */
  protected Proxy iRelseriedoctrasf = new Proxy(it.thera.thip.base.generale.Serie.class);

  /**
   * Attributo iRelcaudoctrasf
   */
  protected Proxy iRelcaudoctrasf = new Proxy(it.thera.thip.magazzino.documenti.CausaleDocumentoTrasf.class);

  /**
   * Attributo iRelseriedocgen
   */
  protected Proxy iRelseriedocgen = new Proxy(it.thera.thip.base.generale.Serie.class);

  /**
   * Attributo iRelcaudocgen
   */
  protected Proxy iRelcaudocgen = new Proxy(it.thera.thip.magazzino.documenti.CausaleDocumentoGen.class);

  /**
   * Attributo iRelcaudocrigvers
   */
  protected Proxy iRelcaudocrigvers = new Proxy(it.thera.thip.magazzino.documenti.CausaleRigaDocGen.class);

  /**
   * Attributo iRelcaudocrigprel
   */
  protected Proxy iRelcaudocrigprel = new Proxy(it.thera.thip.magazzino.documenti.CausaleRigaDocGen.class);

  /**
   * Attributo iRelseriedocriall
   */
  protected Proxy iRelseriedocriall = new Proxy(it.thera.thip.base.generale.Serie.class);

  /**
   * Attributo iRelcaudocriall
   */
  protected Proxy iRelcaudocriall = new Proxy(it.thera.thip.magazzino.documenti.CausaleDocumentoGen.class);

  /**
   * Attributo iRelcaudocrigriallretneg
   */
  protected Proxy iRelcaudocrigriallretneg = new Proxy(it.thera.thip.magazzino.documenti.CausaleRigaDocGen.class);

  /**
   * Attributo iRelcaudocrigriallpos
   */
  protected Proxy iRelcaudocrigriallpos = new Proxy(it.thera.thip.magazzino.documenti.CausaleRigaDocGen.class);


  @SuppressWarnings("rawtypes")
public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YPersDatiModula)Factory.createObject(YPersDatiModula.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  public static YPersDatiModula elementWithKey(String key, int lockType) throws SQLException {
    return (YPersDatiModula)PersistentObject.elementWithKey(YPersDatiModula.class, key, lockType);
  }

  public YPersDatiModulaPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  public void setRelseriedoctrasf(Serie relseriedoctrasf) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relseriedoctrasf != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relseriedoctrasf.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelseriedoctrasf.setObject(relseriedoctrasf);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public Serie getRelseriedoctrasf() {
    return (Serie)iRelseriedoctrasf.getObject();
  }

  public void setRelseriedoctrasfKey(String key) {
    String oldObjectKey = getKey();
    iRelseriedoctrasf.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelseriedoctrasfKey() {
    return iRelseriedoctrasf.getKey();
  }

  public void setRSerieDocTra(String rSerieDocTra) {
    String key = iRelseriedoctrasf.getKey();
    iRelseriedoctrasf.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rSerieDocTra));
    setDirty();
  }

  public String getRSerieDocTra() {
    String key = iRelseriedoctrasf.getKey();
    String objRSerieDocTra = KeyHelper.getTokenObjectKey(key,2);
    return objRSerieDocTra;
    
  }

  public void setRNumeratoreDocTra(String rNumeratoreDocTra) {
    String key = iRelseriedoctrasf.getKey();
    iRelseriedoctrasf.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeratoreDocTra));
    setDirty();
  }

  public String getRNumeratoreDocTra() {
    String key = iRelseriedoctrasf.getKey();
    String objRNumeratoreDocTra = KeyHelper.getTokenObjectKey(key,3);
    return objRNumeratoreDocTra;
  }

  public void setRelcaudoctrasf(CausaleDocumentoTrasf relcaudoctrasf) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudoctrasf != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudoctrasf.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudoctrasf.setObject(relcaudoctrasf);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleDocumentoTrasf getRelcaudoctrasf() {
    return (CausaleDocumentoTrasf)iRelcaudoctrasf.getObject();
  }

  public void setRelcaudoctrasfKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudoctrasf.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelcaudoctrasfKey() {
    return iRelcaudoctrasf.getKey();
  }

  public void setRCauTesDocTra(String rCauTesDocTra) {
    String key = iRelcaudoctrasf.getKey();
    iRelcaudoctrasf.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauTesDocTra));
    setDirty();
  }

  public String getRCauTesDocTra() {
    String key = iRelcaudoctrasf.getKey();
    String objRCauTesDocTra = KeyHelper.getTokenObjectKey(key,2);
    return objRCauTesDocTra;
  }

  public void setRelseriedocgen(Serie relseriedocgen) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relseriedocgen != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relseriedocgen.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelseriedocgen.setObject(relseriedocgen);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public Serie getRelseriedocgen() {
    return (Serie)iRelseriedocgen.getObject();
  }

  public void setRelseriedocgenKey(String key) {
    String oldObjectKey = getKey();
    iRelseriedocgen.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelseriedocgenKey() {
    return iRelseriedocgen.getKey();
  }

  public void setRSerieDocGen(String rSerieDocGen) {
    String key = iRelseriedocgen.getKey();
    iRelseriedocgen.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rSerieDocGen));
    setDirty();
  }

  public String getRSerieDocGen() {
    String key = iRelseriedocgen.getKey();
    String objRSerieDocGen = KeyHelper.getTokenObjectKey(key,2);
    return objRSerieDocGen;
    
  }

  public void setRNumeratoreDocGen(String rNumeratoreDocGen) {
    String key = iRelseriedocgen.getKey();
    iRelseriedocgen.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeratoreDocGen));
    setDirty();
  }

  public String getRNumeratoreDocGen() {
    String key = iRelseriedocgen.getKey();
    String objRNumeratoreDocGen = KeyHelper.getTokenObjectKey(key,3);
    return objRNumeratoreDocGen;
  }

  public void setRelcaudocgen(CausaleDocumentoGen relcaudocgen) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocgen != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocgen.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocgen.setObject(relcaudocgen);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleDocumentoGen getRelcaudocgen() {
    return (CausaleDocumentoGen)iRelcaudocgen.getObject();
  }

  public void setRelcaudocgenKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocgen.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelcaudocgenKey() {
    return iRelcaudocgen.getKey();
  }

  public void setRCauDocGen(String rCauDocGen) {
    String key = iRelcaudocgen.getKey();
    iRelcaudocgen.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocGen));
    setDirty();
  }

  public String getRCauDocGen() {
    String key = iRelcaudocgen.getKey();
    String objRCauDocGen = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocGen;
  }

  public void setRelcaudocrigvers(CausaleRigaDocGen relcaudocrigvers) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocrigvers != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocrigvers.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocrigvers.setObject(relcaudocrigvers);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleRigaDocGen getRelcaudocrigvers() {
    return (CausaleRigaDocGen)iRelcaudocrigvers.getObject();
  }

  public void setRelcaudocrigversKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocrigvers.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelcaudocrigversKey() {
    return iRelcaudocrigvers.getKey();
  }

  public void setRCauDocGenRigVers(String rCauDocGenRigVers) {
    String key = iRelcaudocrigvers.getKey();
    iRelcaudocrigvers.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocGenRigVers));
    setDirty();
  }

  public String getRCauDocGenRigVers() {
    String key = iRelcaudocrigvers.getKey();
    String objRCauDocGenRigVers = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocGenRigVers;
  }

  public void setRelcaudocrigprel(CausaleRigaDocGen relcaudocrigprel) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocrigprel != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocrigprel.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocrigprel.setObject(relcaudocrigprel);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleRigaDocGen getRelcaudocrigprel() {
    return (CausaleRigaDocGen)iRelcaudocrigprel.getObject();
  }

  public void setRelcaudocrigprelKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocrigprel.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelcaudocrigprelKey() {
    return iRelcaudocrigprel.getKey();
  }

  public void setRCauDocGenRigPrel(String rCauDocGenRigPrel) {
    String key = iRelcaudocrigprel.getKey();
    iRelcaudocrigprel.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocGenRigPrel));
    setDirty();
  }
  
  public String getRCauDocGenRigPrel() {
    String key = iRelcaudocrigprel.getKey();
    String objRCauDocGenRigPrel = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocGenRigPrel;
  }

  public void setRelseriedocriall(Serie relseriedocriall) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relseriedocriall != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relseriedocriall.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelseriedocriall.setObject(relseriedocriall);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public Serie getRelseriedocriall() {
    return (Serie)iRelseriedocriall.getObject();
  }

  public void setRelseriedocriallKey(String key) {
    String oldObjectKey = getKey();
    iRelseriedocriall.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelseriedocriallKey() {
    return iRelseriedocriall.getKey();
  }

  public void setRSerieDocRiall(String rSerieDocRiall) {
    String key = iRelseriedocriall.getKey();
    iRelseriedocriall.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rSerieDocRiall));
    setDirty();
  }

  public String getRSerieDocRiall() {
    String key = iRelseriedocriall.getKey();
    String objRSerieDocRiall = KeyHelper.getTokenObjectKey(key,2);
    return objRSerieDocRiall;
    
  }

  public void setRNumeratoreDocRiall(String rNumeratoreDocRiall) {
    String key = iRelseriedocriall.getKey();
    iRelseriedocriall.setKey(KeyHelper.replaceTokenObjectKey(key , 3, rNumeratoreDocRiall));
    setDirty();
  }

  public String getRNumeratoreDocRiall() {
    String key = iRelseriedocriall.getKey();
    String objRNumeratoreDocRiall = KeyHelper.getTokenObjectKey(key,3);
    return objRNumeratoreDocRiall;
  }

  public void setRelcaudocriall(CausaleDocumentoGen relcaudocriall) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocriall != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocriall.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocriall.setObject(relcaudocriall);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleDocumentoGen getRelcaudocriall() {
    return (CausaleDocumentoGen)iRelcaudocriall.getObject();
  }
  
  public void setRelcaudocriallKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocriall.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }


  public String getRelcaudocriallKey() {
    return iRelcaudocriall.getKey();
  }

  public void setRCauDocRiall(String rCauDocRiall) {
    String key = iRelcaudocriall.getKey();
    iRelcaudocriall.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocRiall));
    setDirty();
  }

  public String getRCauDocRiall() {
    String key = iRelcaudocriall.getKey();
    String objRCauDocRiall = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocRiall;
  }

  public void setRelcaudocrigriallretneg(CausaleRigaDocGen relcaudocrigriallretneg) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocrigriallretneg != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocrigriallretneg.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocrigriallretneg.setObject(relcaudocrigriallretneg);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleRigaDocGen getRelcaudocrigriallretneg() {
    return (CausaleRigaDocGen)iRelcaudocrigriallretneg.getObject();
  }

  public void setRelcaudocrigriallretnegKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocrigriallretneg.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }
  
  public String getRelcaudocrigriallretnegKey() {
    return iRelcaudocrigriallretneg.getKey();
  }

  public void setRCauDocRiallRigRetneg(String rCauDocRiallRigRetneg) {
    String key = iRelcaudocrigriallretneg.getKey();
    iRelcaudocrigriallretneg.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocRiallRigRetneg));
    setDirty();
  }

  public String getRCauDocRiallRigRetneg() {
    String key = iRelcaudocrigriallretneg.getKey();
    String objRCauDocRiallRigRetneg = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocRiallRigRetneg;
  }

  public void setRelcaudocrigriallpos(CausaleRigaDocGen relcaudocrigriallpos) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcaudocrigriallpos != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcaudocrigriallpos.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcaudocrigriallpos.setObject(relcaudocrigriallpos);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public CausaleRigaDocGen getRelcaudocrigriallpos() {
    return (CausaleRigaDocGen)iRelcaudocrigriallpos.getObject();
  }

  public void setRelcaudocrigriallposKey(String key) {
    String oldObjectKey = getKey();
    iRelcaudocrigriallpos.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelcaudocrigriallposKey() {
    return iRelcaudocrigriallpos.getKey();
  }

  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  public String getIdAzienda() {
    String key = iRelseriedoctrasf.getKey();
    String objIdAzienda = KeyHelper.getTokenObjectKey(key,1);
    return objIdAzienda;
    
  }

  public void setRCauDocRiallRigRetpos(String rCauDocRiallRigRetpos) {
    String key = iRelcaudocrigriallpos.getKey();
    iRelcaudocrigriallpos.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rCauDocRiallRigRetpos));
    setDirty();
  }

  public String getRCauDocRiallRigRetpos() {
    String key = iRelcaudocrigriallpos.getKey();
    String objRCauDocRiallRigRetpos = KeyHelper.getTokenObjectKey(key,2);
    return objRCauDocRiallRigRetpos;
  }

  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YPersDatiModulaPO yPersDatiModulaPO = (YPersDatiModulaPO)obj;
    iRelseriedoctrasf.setEqual(yPersDatiModulaPO.iRelseriedoctrasf);
    iRelcaudoctrasf.setEqual(yPersDatiModulaPO.iRelcaudoctrasf);
    iRelseriedocgen.setEqual(yPersDatiModulaPO.iRelseriedocgen);
    iRelcaudocgen.setEqual(yPersDatiModulaPO.iRelcaudocgen);
    iRelcaudocrigvers.setEqual(yPersDatiModulaPO.iRelcaudocrigvers);
    iRelcaudocrigprel.setEqual(yPersDatiModulaPO.iRelcaudocrigprel);
    iRelseriedocriall.setEqual(yPersDatiModulaPO.iRelseriedocriall);
    iRelcaudocriall.setEqual(yPersDatiModulaPO.iRelcaudocriall);
    iRelcaudocrigriallretneg.setEqual(yPersDatiModulaPO.iRelcaudocrigriallretneg);
    iRelcaudocrigriallpos.setEqual(yPersDatiModulaPO.iRelcaudocrigriallpos);
  }

  @SuppressWarnings("rawtypes")
public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  public void setKey(String key) {
    setIdAzienda(key);
  }

  public String getKey() {
    return getIdAzienda();
  }
  
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  protected TableManager getTableManager() throws SQLException {
    return YPersDatiModulaTM.getInstance();
  }

  protected void setIdAziendaInternal(String idAzienda) {
    String key1 = iRelseriedoctrasf.getKey();
    iRelseriedoctrasf.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
    String key2 = iRelcaudoctrasf.getKey();
    iRelcaudoctrasf.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iRelseriedocgen.getKey();
    iRelseriedocgen.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
    String key4 = iRelcaudocgen.getKey();
    iRelcaudocgen.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
    String key5 = iRelcaudocrigvers.getKey();
    iRelcaudocrigvers.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
    String key6 = iRelcaudocrigprel.getKey();
    iRelcaudocrigprel.setKey(KeyHelper.replaceTokenObjectKey(key6, 1, idAzienda));
    String key7 = iRelseriedocriall.getKey();
    iRelseriedocriall.setKey(KeyHelper.replaceTokenObjectKey(key7, 1, idAzienda));
    String key8 = iRelcaudocriall.getKey();
    iRelcaudocriall.setKey(KeyHelper.replaceTokenObjectKey(key8, 1, idAzienda));
    String key9 = iRelcaudocrigriallretneg.getKey();
    iRelcaudocrigriallretneg.setKey(KeyHelper.replaceTokenObjectKey(key9, 1, idAzienda));
    String key10 = iRelcaudocrigriallpos.getKey();
    iRelcaudocrigriallpos.setKey(KeyHelper.replaceTokenObjectKey(key10, 1, idAzienda));
  }

}

