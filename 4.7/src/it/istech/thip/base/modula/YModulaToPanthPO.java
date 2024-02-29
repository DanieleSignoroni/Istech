package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import java.math.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YModulaToPanthPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	/**
	 *  instance
	 */
	private static YModulaToPanth cInstance;

	/**
	 * Attributo iId
	 */
	protected Integer iId;

	/**
	 * Attributo iOrdine
	 */
	protected String iOrdine;

	/**
	 * Attributo iArticolo
	 */
	protected String iArticolo;

	/**
	 * Attributo iTipoMov
	 */
	protected char iTipoMov = 'V';

	/**
	 * Attributo iTipoDoc
	 */
	protected char iTipoDoc = 'V';

	/**
	 * Attributo iQtaEvasaUmPrm
	 */
	protected BigDecimal iQtaEvasaUmPrm;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YModulaToPanth)Factory.createObject(YModulaToPanth.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YModulaToPanth elementWithKey(String key, int lockType) throws SQLException {
		return (YModulaToPanth)PersistentObject.elementWithKey(YModulaToPanth.class, key, lockType);
	}

	public YModulaToPanthPO() {
		setTipoMov('V');
		setTipoDoc('V');
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setId(Integer id) {
		this.iId = id;
		setDirty();
		setOnDB(false);
	}

	public Integer getId() {
		return iId;
	}

	public void setOrdine(String ordine) {
		this.iOrdine = ordine;
		setDirty();
	}

	public String getOrdine() {
		return iOrdine;
	}

	public void setArticolo(String articolo) {
		this.iArticolo = articolo;
		setDirty();
	}

	public String getArticolo() {
		return iArticolo;
	}

	public void setTipoMov(char tipoMov) {
		this.iTipoMov = tipoMov;
		setDirty();
	}

	public char getTipoMov() {
		return iTipoMov;
	}

	public void setTipoDoc(char tipoDoc) {
		this.iTipoDoc = tipoDoc;
		setDirty();
	}

	public char getTipoDoc() {
		return iTipoDoc;
	}

	public void setQtaEvasaUmPrm(BigDecimal qtaEvasaUmPrm) {
		this.iQtaEvasaUmPrm = qtaEvasaUmPrm;
		setDirty();
	}

	public BigDecimal getQtaEvasaUmPrm() {
		return iQtaEvasaUmPrm;
	}

	public void setIdAzienda(String idAzienda) {
		iAzienda.setKey(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Integer id = getId();
		Object[] keyParts = {idAzienda, id};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YModulaToPanthTM.getInstance();
	}

}