package it.istech.thip.base.modula;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.articolo.Articolo;
import java.math.*;
import it.thera.thip.cs.*;
import it.thera.thip.produzione.ordese.AttivitaEsecMateriale;
import it.thera.thip.produzione.ordese.AttivitaEsecutiva;
import it.thera.thip.produzione.ordese.OrdineEsecutivo;

import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class YMatProToModulaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static YMatProToModula cInstance;

	protected BigDecimal iQtaOriginale;

	protected BigDecimal iQtaEvasa;

	protected BigDecimal iQtaResidua;

	protected BigDecimal iGiacenza;

	protected BigDecimal iQtaDaEvadere;

	protected String iRAnnoOrd;

	protected String iRNumeroOrd;

	protected Integer iRRigaAttivita;

	protected Integer iRRigaMateriale;

	protected Proxy iRelarticolo = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	protected Proxy iOrdineEsecutivo = new Proxy(it.thera.thip.produzione.ordese.OrdineEsecutivo.class);

	protected Proxy iAttivitaEsecutiva = new Proxy(it.thera.thip.produzione.ordese.AttivitaEsecutiva.class);

	protected Proxy iAttivitaEsecMateriale = new Proxy(it.thera.thip.produzione.ordese.AttivitaEsecMateriale.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (YMatProToModula)Factory.createObject(YMatProToModula.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static YMatProToModula elementWithKey(String key, int lockType) throws SQLException {
		return (YMatProToModula)PersistentObject.elementWithKey(YMatProToModula.class, key, lockType);
	}

	public YMatProToModulaPO() {
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

	public void setOrdineEsecutivo(OrdineEsecutivo ordineEsecutivo)
	{
		this.iOrdineEsecutivo.setObject(ordineEsecutivo);
		setDirty();
	}
	public OrdineEsecutivo getOrdineEsecutivo() {
		return (OrdineEsecutivo)iOrdineEsecutivo.getObject();
	}

	public void setOrdineEsecutivoKey(String key) {
		iOrdineEsecutivo.setKey(key);
		setDirty();
	}
	public String getOrdineEsecutivoKey() {
		return iOrdineEsecutivo.getKey();
	}

	public AttivitaEsecutiva getAttivitaEsecutiva() {
		return (AttivitaEsecutiva)iAttivitaEsecutiva.getObject();
	}

	public void setAttivitaEsecutivaKey(String key) {
		String oldObjectKey = getKey();
		iAttivitaEsecutiva.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idAnnoOrdine = KeyHelper.getTokenObjectKey(key, 2);
		setIdAnnoOrdineInternal(idAnnoOrdine);
		String idNumeroOrdine = KeyHelper.getTokenObjectKey(key, 3);
		setIdNumeroOrdineInternal(idNumeroOrdine);
		String idRigaAttivita = KeyHelper.getTokenObjectKey(key, 4);
		setIdRigaAttivitaInternal(idRigaAttivita);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getAttivitaEsecutivaKey() {
		return iAttivitaEsecutiva.getKey();
	}

	public void setAttivitaEsecMateriale(AttivitaEsecMateriale attivitaEsecMateriale) {
		String oldObjectKey = getKey();
		String idAzienda = null;
		if (attivitaEsecMateriale != null) {
			idAzienda = KeyHelper.getTokenObjectKey(attivitaEsecMateriale.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idAnnoOrdine = null;
		if (attivitaEsecMateriale != null) {
			idAnnoOrdine = KeyHelper.getTokenObjectKey(attivitaEsecMateriale.getKey(), 2);
		}
		setIdAnnoOrdineInternal(idAnnoOrdine);
		String idNumeroOrdine = null;
		if (attivitaEsecMateriale != null) {
			idNumeroOrdine = KeyHelper.getTokenObjectKey(attivitaEsecMateriale.getKey(), 3);
		}
		setIdNumeroOrdineInternal(idNumeroOrdine);
		String idRigaAttivita = null;
		if (attivitaEsecMateriale != null) {
			idRigaAttivita = KeyHelper.getTokenObjectKey(attivitaEsecMateriale.getKey(), 4);
		}
		setIdRigaAttivitaInternal(idRigaAttivita);
		this.iAttivitaEsecMateriale.setObject(attivitaEsecMateriale);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public AttivitaEsecMateriale getAttivitaEsecMateriale() {
		return (AttivitaEsecMateriale)iAttivitaEsecMateriale.getObject();
	}

	public void setAttivitaEsecMaterialeKey(String key) {
		String oldObjectKey = getKey();
		iAttivitaEsecMateriale.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idAnnoOrdine = KeyHelper.getTokenObjectKey(key, 2);
		setIdAnnoOrdineInternal(idAnnoOrdine);
		String idNumeroOrdine = KeyHelper.getTokenObjectKey(key, 3);
		setIdNumeroOrdineInternal(idNumeroOrdine);
		String idRigaAttivita = KeyHelper.getTokenObjectKey(key, 4);
		setIdRigaAttivitaInternal(idRigaAttivita);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	protected void setIdAnnoOrdineInternal(String idAnnoOrdine) {
		String key1 = iOrdineEsecutivo.getKey();
		iOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idAnnoOrdine));
		String key2 = iAttivitaEsecutiva.getKey();
		iAttivitaEsecutiva.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idAnnoOrdine));
		String key3 = iAttivitaEsecMateriale.getKey();
		iAttivitaEsecMateriale.setKey(KeyHelper.replaceTokenObjectKey(key3, 2, idAnnoOrdine));
	}

	protected void setIdNumeroOrdineInternal(String idNumeroOrdine) {
		String key1 = iOrdineEsecutivo.getKey();
		iOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key1, 3, idNumeroOrdine));
		String key2 = iAttivitaEsecutiva.getKey();
		iAttivitaEsecutiva.setKey(KeyHelper.replaceTokenObjectKey(key2, 3, idNumeroOrdine));
		String key3 = iAttivitaEsecMateriale.getKey();
		iAttivitaEsecMateriale.setKey(KeyHelper.replaceTokenObjectKey(key3, 3, idNumeroOrdine));
	}

	protected void setIdRigaAttivitaInternal(String idRigaAttivita) {
		String key1 = iAttivitaEsecutiva.getKey();
		iAttivitaEsecutiva.setKey(KeyHelper.replaceTokenObjectKey(key1, 4, idRigaAttivita));
		String key2 = iAttivitaEsecMateriale.getKey();
		iAttivitaEsecMateriale.setKey(KeyHelper.replaceTokenObjectKey(key2, 4, idRigaAttivita));
	}


	protected void setIdRigaAttivitaMatInternal(String idRigaAtvMatInternal) {
		String key1 = iAttivitaEsecMateriale.getKey();
		iAttivitaEsecMateriale.setKey(KeyHelper.replaceTokenObjectKey(key1, 5, idRigaAtvMatInternal));
	}

	public String getAttivitaEsecMaterialeKey() {
		return iAttivitaEsecMateriale.getKey();
	}

	public void setRAnnoOrd(String idAnnoOrdine)
	{
		String key = iOrdineEsecutivo.getKey();
		iOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idAnnoOrdine));
		key = iAttivitaEsecutiva.getKey();
		iAttivitaEsecutiva.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idAnnoOrdine));
		setDirty();
	}
	
	public String getRAnnoOrd()
	{
		String key = iOrdineEsecutivo.getKey();
		String objIdAnnoOrdine = KeyHelper.getTokenObjectKey(key, 2);
		return objIdAnnoOrdine;
	}
	
	public void setRNumeroOrd(String idNumeroOrdine)
	{
		String key = iOrdineEsecutivo.getKey();
		iOrdineEsecutivo.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idNumeroOrdine));
		key = iAttivitaEsecutiva.getKey();
		iAttivitaEsecutiva.setKey(KeyHelper.replaceTokenObjectKey(key, 3, idNumeroOrdine));
		setDirty();
	}
	
	public String getRNumeroOrd()
	{
		String key = iOrdineEsecutivo.getKey();
		String objIdNumeroOrdine = KeyHelper.getTokenObjectKey(key, 3);
		return objIdNumeroOrdine;
	}

	public void setRRigaAttivita(Integer rRigaAttivita) {
		this.iRRigaAttivita = rRigaAttivita;
		setDirty();
		setOnDB(false);
	}

	public Integer getRRigaAttivita() {
		return iRRigaAttivita;
	}

	public void setRRigaMateriale(Integer rRigaMateriale) {
		this.iRRigaMateriale = rRigaMateriale;
		setDirty();
		setOnDB(false);
	}

	public Integer getRRigaMateriale() {
		return iRRigaMateriale;
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
		YMatProToModulaPO yMatProToModulaPO = (YMatProToModulaPO)obj;
		iRelarticolo.setEqual(yMatProToModulaPO.iRelarticolo);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setRAnnoOrd(KeyHelper.getTokenObjectKey(key, 2));
		setRNumeroOrd(KeyHelper.getTokenObjectKey(key, 3));
		setRRigaAttivita(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 4)));
		setRRigaMateriale(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 5)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String rAnnoOrd = getRAnnoOrd();
		String rNumeroOrd = getRNumeroOrd();
		Integer rRigaAttivita = getRRigaAttivita();
		Integer rRigaMateriale = getRRigaMateriale();
		Object[] keyParts = {idAzienda, rAnnoOrd, rNumeroOrd, rRigaAttivita, rRigaMateriale};
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return YMatProToModulaTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iRelarticolo.getKey();
		iRelarticolo.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

