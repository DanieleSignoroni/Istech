package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.articolo.Articolo;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YDocTraToModulaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {


	/**
	 *  instance
	 */
	private static YDocTraToModula cInstance;

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
	 * Attributo iRRigaDoc
	 */
	protected Integer iRRigaDoc;

	/**
	 * Attributo iRDetRigaDoc
	 */
	protected Integer iRDetRigaDoc;

	/**
	 * Attributo iRAnnoDocTra
	 */
	protected String iRAnnoDocTra;

	/**
	 * Attributo iRNumeroDocTra
	 */
	protected String iRNumeroDocTra;

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
	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YDocTraToModula)Factory.createObject(YDocTraToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	/**
	 *  elementWithKey
	 * @param key
	 * @param lockType
	 * @return YDocTraToModula
	 * @throws SQLException
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    CodeGen     Codice generato da CodeGenerator
	 *
	 */
	public static YDocTraToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YDocTraToModula)PersistentObject.elementWithKey(YDocTraToModula.class, key, lockType);
	}

	/**
	 * YDocTraToModulaPO
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    Wizard     Codice generato da Wizard
	 *
	 */
	public YDocTraToModulaPO() {
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
	 * @param rRigaDoc
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRRigaDoc(Integer rRigaDoc) {
		this.iRRigaDoc = rRigaDoc;
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
	public Integer getRRigaDoc() {
		return iRRigaDoc;
	}

	/**
	 * Valorizza l'attributo. 
	 * @param rDetRigaDoc
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRDetRigaDoc(Integer rDetRigaDoc) {
		this.iRDetRigaDoc = rDetRigaDoc;
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
	public Integer getRDetRigaDoc() {
		return iRDetRigaDoc;
	}

	/**
	 * Valorizza l'attributo. 
	 * @param rAnnoDocTra
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRAnnoDocTra(String rAnnoDocTra) {
		this.iRAnnoDocTra = rAnnoDocTra;
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
	public String getRAnnoDocTra() {
		return iRAnnoDocTra;
	}

	/**
	 * Valorizza l'attributo. 
	 * @param rNumeroDocTra
	 */
	/*
	 * Revisions:
	 * Date          Owner      Description
	 * 04/03/2024    Wizard     Codice generato da Wizard
	 *
	 */
	public void setRNumeroDocTra(String rNumeroDocTra) {
		this.iRNumeroDocTra = rNumeroDocTra;
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
	public String getRNumeroDocTra() {
		return iRNumeroDocTra;
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
		YDocTraToModulaPO yDocTraToModulaPO = (YDocTraToModulaPO)obj;
		iRelarticolo.setEqual(yDocTraToModulaPO.iRelarticolo);
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
	@SuppressWarnings("rawtypes")
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
		setRAnnoDocTra(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroDocTra(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRDetRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
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
		String rAnnoDocTra = getRAnnoDocTra();
		String rNumeroDocTra = getRNumeroDocTra();
		Integer rRigaDoc = getRRigaDoc();
		Integer rDetRigaDoc = getRDetRigaDoc();
		Object[] keyParts = {idAzienda, rAnnoDocTra, rNumeroDocTra, rRigaDoc, rDetRigaDoc};
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
		return YDocTraToModulaTM.getInstance();
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

