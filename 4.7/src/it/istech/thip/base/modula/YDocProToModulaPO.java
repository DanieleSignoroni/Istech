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

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public abstract class YDocProToModulaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YDocProToModula cInstance;

	protected BigDecimal iQtaOriginale;

	protected BigDecimal iQtaEvasa;

	protected BigDecimal iQtaResidua;

	protected BigDecimal iGiacenza;

	protected BigDecimal iQtaDaEvadere;

	protected Integer iRRigaDoc;

	protected Integer iRDetRigaDoc;

	protected String iRAnnoDocPro;

	protected String iRNumeroDocPro;

	protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YDocProToModula)Factory.createObject(YDocProToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YDocProToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YDocProToModula)PersistentObject.elementWithKey(YDocProToModula.class, key, lockType);
	}

	public YDocProToModulaPO() {
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

	public void setRAnnoDocPro(String rAnnoDocPro) {
		this.iRAnnoDocPro = rAnnoDocPro;
		setDirty();
		setOnDB(false);
	}

	public String getRAnnoDocPro() {
		return iRAnnoDocPro;
	}

	public void setRNumeroDocPro(String rNumeroDocPro) {
		this.iRNumeroDocPro = rNumeroDocPro;
		setDirty();
		setOnDB(false);
	}

	public String getRNumeroDocPro() {
		return iRNumeroDocPro;
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
		YDocProToModulaPO yDocProToModulaPO = (YDocProToModulaPO)obj;
		iRelarticolo.setEqual(yDocProToModulaPO.iRelarticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoDocPro(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroDocPro(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRDetRigaDoc(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoDocPro = getRAnnoDocPro();
		String rNumeroDocPro = getRNumeroDocPro();
		Integer rRigaDoc = getRRigaDoc();
		Integer rDetRigaDoc = getRDetRigaDoc();
		Object[] keyParts = {idAzienda, rAnnoDocPro, rNumeroDocPro, rRigaDoc, rDetRigaDoc};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YDocProToModulaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

