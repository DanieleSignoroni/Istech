package it.istech.thip.base.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;

import it.thera.thip.base.articolo.Articolo;
import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 01/03/2024
 * <br><br>
 * <b></b>
 * <p>Prima stesura.<br>
 * Aggiungere flag per gestire l'esportazione verso Modula dell'anagrafica.
 * </p>
 */

public class YArticolo extends Articolo {

	public static final String STMT_UPDATE_STATO_EXP_MODULA_ART = ""
			+ " UPDATE "+YArticoloTM.TABLE_NAME_EXT+" SET "+YArticoloTM.ESPORTATO_MODULA+" = ? "
			+ " WHERE "+YArticoloTM.ID_AZIENDA+" = ? AND "+YArticoloTM.ID_ARTICOLO+" = ? ";

	public static final String STMT_INSERT_ANAGRAFICA_MODULA = "INSERT INTO [dbo].[IMP_ARTICOLI] ([ART_ARTICOLO],[ART_DES],[ART_UMI]) "
			+ "	VALUES (?,?,?) ";

	protected boolean iEsportatoModula;

	public boolean isEsportatoModula() {
		return iEsportatoModula;
	}

	public void setEsportatoModula(boolean iEsportatoModula) {
		this.iEsportatoModula = iEsportatoModula;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Metodo per l'aggiornamento del flag {@link #iEsportatoModula}.<br>
	 * <b>NB</b> E' A CURA DELL'UTENTE LA COMMIT O LA ROLLBACK.
	 * </p>
	 * @param idArticolo
	 * @param esportato un booleano che indica se l'update dev'essere Y : N
	 * @return
	 */
	public static int aggiornaStatoEsportazioneModulaArticolo(String idArticolo, boolean esportato) {
		try {
			Database db = ConnectionManager.getCurrentDatabase();
			PreparedStatement ps = ConnectionManager.getCurrentConnection().prepareStatement(STMT_UPDATE_STATO_EXP_MODULA_ART);
			db.setString(ps,2, Azienda.getAziendaCorrente());
			db.setString(ps,3, idArticolo);
			if(esportato) {
				db.setString(ps,1, "Y");
			}else {
				db.setString(ps,1, "N");
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return 0;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Si occupa di effettuare l'insert di un articolo nella tabella HOST_IMPEXP.dbo.IMP_ARTICOLI.<br>
	 * <b>NB:</b> LA COMMIT E' A CURA DELL'UTENTE.
	 * </p>
	 * @param connection rappresenta la connessione verso il db di Modula
	 * @param articolo oggetto da cui verrano presi i dati.
	 * @return -999 se la connessione e' chiusa, altrimenti un intero che rappresenta il risultato dell'update
	 */
	public static int esportaArticoloVersoModula(Connection connection, YArticolo articolo) {
		try {
			if(connection.isClosed()) {
				return -999;
			}
			PreparedStatement ps = connection.prepareStatement(STMT_INSERT_ANAGRAFICA_MODULA);
			ps.setString(1, articolo.getIdArticolo());
			ps.setString(2, getDescrizioneArticoloPerModula(articolo));
			ps.setString(3, articolo.getArticoloDatiMagaz().getIdUMPrmMag());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return 0;
	}


	/**
	 * @author Daniele Signoroni
	 * <p>
	 * La colonna dbo.IMP_ARTICOLI.ART_DES e' nvarchar(100), quindi la descr va sizzata di conseguenza.
	 * </p>
	 * @param articolo
	 * @return
	 */
	public static String getDescrizioneArticoloPerModula(YArticolo articolo) {
		if(articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa() != null) {
			if(articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa().length() > 100) {
				return articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa().substring(0,100);
			}else {
				return articolo.getDescrizioneArticoloNLS().getDescrizioneEstesa();
			}
		}else {
			return articolo.getDescrizioneArticoloNLS().getDescrizione();
		}
	}
}
