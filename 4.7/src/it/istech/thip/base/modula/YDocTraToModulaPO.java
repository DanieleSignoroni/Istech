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
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.thera.thip.base.articolo.Articolo;
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

public abstract class YDocTraToModulaPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YDocTraToModula cInstance;

	protected BigDecimal iQtaOriginale;

	protected BigDecimal iQtaEvasa;

	protected BigDecimal iQtaResidua;

	protected BigDecimal iGiacenza;

	protected BigDecimal iQtaDaEvadere;

	protected Integer iRRigaDoc;

	protected Integer iRDetRigaDoc;

	protected String iRAnnoDocTra;

	protected String iRNumeroDocTra;

	protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YDocTraToModula) Factory.createObject(YDocTraToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YDocTraToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YDocTraToModula) PersistentObject.elementWithKey(YDocTraToModula.class, key, lockType);
	}

	public YDocTraToModulaPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
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

	public void setRRigaDoc(Integer rRigaDoc) {
		this.iRRigaDoc = rRigaDoc;
		setDirty();
		setOnDB(false);
	}

	public Integer getRRigaDoc() {
		return iRRigaDoc;
	}

	public void setRDetRigaDoc(Integer rDetRigaDoc) {
		this.iRDetRigaDoc = rDetRigaDoc;
		setDirty();
		setOnDB(false);
	}

	public Integer getRDetRigaDoc() {
		return iRDetRigaDoc;
	}

	public void setRAnnoDocTra(String rAnnoDocTra) {
		this.iRAnnoDocTra = rAnnoDocTra;
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoDocTra() {
		return iRAnnoDocTra;
	}

	public void setRNumeroDocTra(String rNumeroDocTra) {
		this.iRNumeroDocTra = rNumeroDocTra;
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroDocTra() {
		return iRNumeroDocTra;
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
		return (Articolo) iRelarticolo.getObject();
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
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key, 2, rArticolo));
		setDirty();
	}

	public String getRArticolo() {
		String key = iRelarticolo.getKey();
		String objRArticolo = KeyHelper.getTokenObjectKey(key, 2);
		return objRArticolo;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YDocTraToModulaPO yDocTraToModulaPO = (YDocTraToModulaPO) obj;
		iRelarticolo.setEqual(yDocTraToModulaPO.iRelarticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoDocTra(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroDocTra(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRDetRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoDocTra = getRAnnoDocTra();
		String rNumeroDocTra = getRNumeroDocTra();
		Integer rRigaDoc = getRRigaDoc();
		Integer rDetRigaDoc = getRDetRigaDoc();
		Object[] keyParts = { idAzienda, rAnnoDocTra, rNumeroDocTra, rRigaDoc, rDetRigaDoc };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YDocTraToModulaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}
