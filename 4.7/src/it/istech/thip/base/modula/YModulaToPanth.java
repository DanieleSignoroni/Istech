package it.istech.thip.base.modula;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.Factory;

import it.istech.thip.base.modula.importazione.YExpOrdini;
import it.istech.thip.base.modula.importazione.YExpOrdiniRighe;
import it.thera.thip.base.azienda.Azienda;

public class YModulaToPanth extends YModulaToPanthPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	@Override
	public int save() throws SQLException {
		if(!this.isOnDB()) {
			CachedStatement cs = null;
			ResultSet rs = null;
			try {
				String stmt = "SELECT COALESCE(MAX(ID)+1,1) AS MASSIMO "
						+ "FROM THIPPERS.YMODULA_TO_PANTH ytp ";
				cs = new CachedStatement(stmt);
				rs = cs.executeQuery();
				if(rs.next())
					this.setId(rs.getInt(1));
			}catch(SQLException e) {
				e.printStackTrace();
				e.printStackTrace(Trace.excStream);
			}finally {
				if(rs != null) rs.close();
			}
		}
		int rc = super.save();
		return rc;

	}
	
	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Si occupa della scrittura di un record nella tabella {@value YModulaToPanthTM#TABLE_NAME}.<br>
	 * </p>
	 * @param testata della tabella {@value YExpOrdini#TABLE_NAME}
	 * @param riga della tabella {@value YExpOrdiniRighe#TABLE_NAME}
	 * @param tipoDoc uno tra quelli dell'enumerato {@link TipoDocumentoModula}
	 * @return un interno che rappresenta l'rc del salvataggio
	 * @throws SQLException in caso di exc nella save di PO
	 */
	public static int scriviModulaToPanth(YExpOrdini testata, YExpOrdiniRighe riga, char tipoDoc) throws SQLException {
		YModulaToPanth modulaToPanth = (YModulaToPanth) Factory.createObject(YModulaToPanth.class);
		modulaToPanth.setIdAzienda(Azienda.getAziendaCorrente());
		modulaToPanth.setOrdine(testata.getOrd_ordine());
		modulaToPanth.setArticolo(riga.getRig_articolo());
		modulaToPanth.setQtaEvasaUmPrm(riga.getRig_qtaE());
		modulaToPanth.setTipoMov(testata.getOrd_tipoop().charAt(0));
		modulaToPanth.setTipoDoc(tipoDoc);
		return modulaToPanth.save();
	}

}