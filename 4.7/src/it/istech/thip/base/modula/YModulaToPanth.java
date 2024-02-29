package it.istech.thip.base.modula;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;

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
				if(cs != null) cs.free();
				if(rs != null) rs.close();
			}
		}
		int rc = super.save();
		return rc;

	}

}