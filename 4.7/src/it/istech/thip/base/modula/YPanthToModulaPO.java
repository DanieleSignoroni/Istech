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

public abstract class YPanthToModulaPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YPanthToModula cInstance;

	protected char iTipoDoc = 'V';

	protected String iIdAnnoDoc;

	protected String iIdNumeroDoc;

	protected Integer iIdRigaDoc;

	protected Integer iIdDetRigaDoc;

	protected char iTipoMov = 'V';

	protected BigDecimal iQtaEvasaUmPrm;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YPanthToModula) Factory.createObject(YPanthToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YPanthToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YPanthToModula) PersistentObject.elementWithKey(YPanthToModula.class, key, lockType);
	}

	public YPanthToModulaPO() {
		setTipoDoc('V');
		setTipoMov('V');
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setTipoDoc(char tipoDoc) {
		this.iTipoDoc = tipoDoc;
		setDirty();
		setOnDB(false);
	}

	public char getTipoDoc() {
		return iTipoDoc;
	}

	public void setIdAnnoDoc(String idAnnoDoc) {
		this.iIdAnnoDoc = idAnnoDoc;
		setDirty();
		setOnDB(false);
	}

	public String getIdAnnoDoc() {
		return iIdAnnoDoc;
	}

	public void setIdNumeroDoc(String idNumeroDoc) {
		this.iIdNumeroDoc = idNumeroDoc;
		setDirty();
		setOnDB(false);
	}

	public String getIdNumeroDoc() {
		return iIdNumeroDoc;
	}

	public void setIdRigaDoc(Integer idRigaDoc) {
		this.iIdRigaDoc = idRigaDoc;
		setDirty();
		setOnDB(false);
	}

	public Integer getIdRigaDoc() {
		return iIdRigaDoc;
	}

	public void setIdDetRigaDoc(Integer idDetRigaDoc) {
		this.iIdDetRigaDoc = idDetRigaDoc;
		setDirty();
		setOnDB(false);
	}

	public Integer getIdDetRigaDoc() {
		return iIdDetRigaDoc;
	}

	public void setTipoMov(char tipoMov) {
		this.iTipoMov = tipoMov;
		setDirty();
	}

	public char getTipoMov() {
		return iTipoMov;
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
		setTipoDoc(KeyHelper.stringToChar(KeyHelper.getTokenObjectKey(key, 2)));
		setIdAnnoDoc(KeyHelper.getTokenObjectKey(key, 3));
		setIdNumeroDoc(KeyHelper.getTokenObjectKey(key, 4));
		setIdRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
		setIdDetRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 6)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Character tipoDoc = new Character(getTipoDoc());
		String idAnnoDoc = getIdAnnoDoc();
		String idNumeroDoc = getIdNumeroDoc();
		Integer idRigaDoc = getIdRigaDoc();
		Integer idDetRigaDoc = getIdDetRigaDoc();
		Object[] keyParts = { idAzienda, tipoDoc, idAnnoDoc, idNumeroDoc, idRigaDoc, idDetRigaDoc };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YPanthToModulaTM.getInstance();
	}

}