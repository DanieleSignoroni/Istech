package it.istech.thip.base.modula;

import java.sql.Connection;
import java.sql.SQLException;

import com.thera.thermfw.persist.ConnectionDescriptor;
import com.thera.thermfw.persist.SQLServerJTDSNoUnicodeDatabase;

import it.thera.thip.base.generale.ParametroPsn;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 05/03/2024
 * <br><br>
 * <b>71453	DSSOF3 05/03/2024</b>
 * <p>Prima stesura</p>
 */

public class YModulaConnection {

	//private static String classDriver = "net.sourceforge.jtds.jdbc.Driver";
	private static String host = null;
	private static String port = "";
	private static String dbname = null;
	//private static String instance = "";
	private static String userName = null;
	private static String password = null;
	
	
	public static Connection getModulaConnection() throws ModulaConnectionException {
		if(host == null || dbname == null || userName == null || password == null)
			retrieveConnectionValues();
		Connection connection = null;
		ConnectionDescriptor cnd = new ConnectionDescriptor(dbname,userName, password, new SQLServerJTDSNoUnicodeDatabase(host, port));
		try {
			cnd.openConnection();
			connection = cnd.getConnection();
		} catch (SQLException e) {
			throw new ModulaConnectionException(e.getMessage());
		}
		return connection;
	}

	public static void retrieveConnectionValues() {
		ParametroPsn hostP = ParametroPsn.getParametroPsn("SiModula", "Host");
		ParametroPsn portP = ParametroPsn.getParametroPsn("SiModula", "Port");
		ParametroPsn dbnameP = ParametroPsn.getParametroPsn("SiModula", "DBname");
		ParametroPsn instanceP = ParametroPsn.getParametroPsn("SiModula", "Instance");
		ParametroPsn userNameP = ParametroPsn.getParametroPsn("SiModula", "UserName");
		ParametroPsn passwordP = ParametroPsn.getParametroPsn("SiModula", "Password");
		if (hostP != null && hostP.getValore() != null) {
			host = hostP.getValore();
		}

		if (portP != null && portP.getValore() != null) {
			port = portP.getValore();
		}

		if (dbnameP != null && dbnameP.getValore() != null) {
			dbname = dbnameP.getValore();
		}

		if (instanceP != null && instanceP.getValore() != null) {
			//instance = instanceP.getValore();
		}

		if (userNameP != null && userNameP.getValore() != null) {
			userName = userNameP.getValore();
		}

		if (passwordP != null && passwordP.getValore() != null) {
			password = passwordP.getValore();
		}

	}
}
