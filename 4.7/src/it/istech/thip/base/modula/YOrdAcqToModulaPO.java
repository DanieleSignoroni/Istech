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

public abstract class YOrdAcqToModulaPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YOrdAcqToModula cInstance;

	protected Integer iRRigaOrd;

	protected Integer iRDetRigaOrd;

	protected BigDecimal iQtaOriginale;

	protected BigDecimal iQtaEvasa;

	protected BigDecimal iQtaResidua;

	protected BigDecimal iGiacenza;

	protected BigDecimal iQtaDaEvadere;

	protected String iRAnnoOrdAcq;

	protected String iRNumeroOrdAcq;

	protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YOrdAcqToModula) Factory.createObject(YOrdAcqToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YOrdAcqToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YOrdAcqToModula) PersistentObject.elementWithKey(YOrdAcqToModula.class, key, lockType);
	}

	public YOrdAcqToModulaPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
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

	public void setRAnnoOrdAcq(String rAnnoOrdAcq) {
		this.iRAnnoOrdAcq = rAnnoOrdAcq;
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoOrdAcq() {
		return iRAnnoOrdAcq;
	}

	public void setRNumeroOrdAcq(String rNumeroOrdAcq) {
		this.iRNumeroOrdAcq = rNumeroOrdAcq;
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroOrdAcq() {
		return iRNumeroOrdAcq;
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
		YOrdAcqToModulaPO yOrdAcqToModulaPO = (YOrdAcqToModulaPO) obj;
		iRelarticolo.setEqual(yOrdAcqToModulaPO.iRelarticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoOrdAcq(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOrdAcq(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaOrd(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRDetRigaOrd(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoOrdAcq = getRAnnoOrdAcq();
		String rNumeroOrdAcq = getRNumeroOrdAcq();
		Integer rRigaOrd = getRRigaOrd();
		Integer rDetRigaOrd = getRDetRigaOrd();
		Object[] keyParts = { idAzienda, rAnnoOrdAcq, rNumeroOrdAcq, rRigaOrd, rDetRigaOrd };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YOrdAcqToModulaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}
