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

public abstract class YOrdVenToModulaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	/**
	 *  instance
	 */
	private static YOrdVenToModula cInstance;

	/**
	 * Attributo iRAnnoOrdVen
	 */
	protected String iRAnnoOrdVen;

	/**
	 * Attributo iRNumeroOrdVen
	 */
	protected String iRNumeroOrdVen;

	/**
	 * Attributo iRRigaOrd
	 */
	protected Integer iRRigaOrd;

	/**
	 * Attributo iRDetRigaOrd
	 */
	protected Integer iRDetRigaOrd;

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
	 * Attributo iRelarticolo
	 */
	protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YOrdVenToModula)Factory.createObject(YOrdVenToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YOrdVenToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YOrdVenToModula)PersistentObject.elementWithKey(YOrdVenToModula.class, key, lockType);
	}

	public YOrdVenToModulaPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setRAnnoOrdVen(String rAnnoOrdVen) {
		this.iRAnnoOrdVen = rAnnoOrdVen;
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoOrdVen() {
		return iRAnnoOrdVen;
	}

	public void setRNumeroOrdVen(String rNumeroOrdVen) {
		this.iRNumeroOrdVen = rNumeroOrdVen;
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroOrdVen() {
		return iRNumeroOrdVen;
	}

	public void setRRigaOrd(Integer rRigaOrd) {
		this.iRRigaOrd = rRigaOrd;
		setDirty();
		setOnDB(false);
	}

	public Integer getRRigaOrd() {
		return iRRigaOrd;
	}

	public void setRDetRigaOrd(Integer rDetRigaOrd) {
		this.iRDetRigaOrd = rDetRigaOrd;
		setDirty();
		setOnDB(false);
	}

	public Integer getRDetRigaOrd() {
		return iRDetRigaOrd;
	}

	public void setQtaOriginale(BigDecimal qtaOriginale) {
		this.iQtaOriginale = qtaOriginale;
		setDirty();
	}

	public BigDecimal getQtaOriginale() {
		return iQtaOriginale;
	}

	public void setQtaEvasa(BigDecimal qtaEvasa) {
		this.iQtaEvasa = qtaEvasa;
		setDirty();
	}

	public BigDecimal getQtaEvasa() {
		return iQtaEvasa;
	}

	public void setQtaResidua(BigDecimal qtaResidua) {
		this.iQtaResidua = qtaResidua;
		setDirty();
	}

	public BigDecimal getQtaResidua() {
		return iQtaResidua;
	}

	public void setGiacenza(BigDecimal giacenza) {
		this.iGiacenza = giacenza;
		setDirty();
	}

	public BigDecimal getGiacenza() {
		return iGiacenza;
	}

	public void setQtaDaEvadere(BigDecimal qtaDaEvadere) {
		this.iQtaDaEvadere = qtaDaEvadere;
		setDirty();
	}

	public BigDecimal getQtaDaEvadere() {
		return iQtaDaEvadere;
	}

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

	public Articolo getRelarticolo() {
		return (Articolo)iRelarticolo.getObject();
	}

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

	public String getRelarticoloKey() {
		return iRelarticolo.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setRArticolo(String rArticolo) {
		String key = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key , 2, rArticolo));
		setDirty();
	}

	public String getRArticolo() {
		String key = iRelarticolo.getKey();
		String objRArticolo = KeyHelper.getTokenObjectKey(key,2);
		return objRArticolo;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YOrdVenToModulaPO yOrdVenToModulaPO = (YOrdVenToModulaPO)obj;
		iRelarticolo.setEqual(yOrdVenToModulaPO.iRelarticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoOrdVen(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOrdVen(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaOrd(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRDetRigaOrd(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoOrdVen = getRAnnoOrdVen();
		String rNumeroOrdVen = getRNumeroOrdVen();
		Integer rRigaOrd = getRRigaOrd();
		Integer rDetRigaOrd = getRDetRigaOrd();
		Object[] keyParts = {idAzienda, rAnnoOrdVen, rNumeroOrdVen, rRigaOrd, rDetRigaOrd};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YOrdVenToModulaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}