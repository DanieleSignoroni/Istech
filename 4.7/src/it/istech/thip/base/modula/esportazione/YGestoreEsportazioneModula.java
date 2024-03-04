package it.istech.thip.base.modula.esportazione;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.thera.thermfw.persist.CachedStatement;

public class YGestoreEsportazioneModula {

	public static final String STMT_INSERT_IMP_ORDINI = "INSERT INTO [dbo].[IMP_ORDINI] ([ORD_ORDINE],[ORD_DES],[ORD_TIPOOP],[ORD_ERRORE]) "
			+ " VALUES (?,?,?,?) ";

	public static final CachedStatement cs_insert_imp_ordini = new CachedStatement(STMT_INSERT_IMP_ORDINI);

	public static final String STMT_INSERT_IMP_ORDINI_RIG = "INSERT INTO [dbo].[IMP_ORDINI_RIGHE] ([RIG_ORDINE],[RIG_ARTICOLO],[RIG_HOSTINF],[RIG_QTAR],[RIG_ATTR1],[RIG_ERRORE]) "
			+ " VALUES (?,?,?,?,?,?) ";

	public static final CachedStatement cs_insert_imp_ordini_rig = new CachedStatement(STMT_INSERT_IMP_ORDINI_RIG);

	public static int esportaTestataOrdine(Connection conn, String ordOrdine, String ordDes,char prelievo,String ordErrore) throws SQLException {
		if(conn.isClosed()) {
			return -999;
		}
		if(ordOrdine == null) {
			return -998;
		}
		PreparedStatement ps = conn.prepareStatement(STMT_INSERT_IMP_ORDINI);
		ps.setString(1, ordOrdine);
		ps.setString(2, ordDes);
		ps.setString(3, String.valueOf(prelievo));
		ps.setString(4, ordErrore != null ? ordErrore : " ");
		return ps.executeUpdate();
	}

	public static int esportaRigaOrdine(Connection conn, String rigOrdine, String rigArticolo,String hostinf,BigDecimal quantita,String attr1,String errore) throws SQLException {
		if(conn.isClosed()) {
			return -999;
		}
		if(rigOrdine == null) {
			return -998;
		}
		PreparedStatement ps = conn.prepareStatement(STMT_INSERT_IMP_ORDINI_RIG);
		ps.setString(1, rigOrdine);
		ps.setString(2, rigArticolo);
		ps.setString(3, hostinf != null ? hostinf : " ");
		ps.setBigDecimal(4, quantita);
		ps.setString(5, attr1 != null ? attr1 : " ");
		ps.setString(6, errore != null ? errore : " ");
		return ps.executeUpdate();
	}
}
