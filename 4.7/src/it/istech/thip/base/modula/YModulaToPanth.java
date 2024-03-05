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

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

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