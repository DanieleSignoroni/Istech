package it.istech.thip.base.modula;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public abstract class YModulaToPanthPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YModulaToPanth cInstance;

	protected Integer iId;

	protected String iOrdine;

	protected String iArticolo;

	protected char iTipoMov = 'V';

	protected char iTipoDoc = 'V';

	protected BigDecimal iQtaEvasaUmPrm;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YModulaToPanth) Factory.createObject(YModulaToPanth.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YModulaToPanth elementWithKey(String key, int lockType) throws SQLException {
		return (YModulaToPanth) PersistentObject.elementWithKey(YModulaToPanth.class, key, lockType);
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
		Object[] keyParts = { idAzienda, id };
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