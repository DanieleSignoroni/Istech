package it.istech.thip.base.modula.esportazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.security.Authorizable;

import it.istech.thip.base.articolo.YArticolo;
import it.istech.thip.base.articolo.YArticoloTM;
import it.istech.thip.base.modula.ModulaConnectionException;
import it.istech.thip.base.modula.YModulaConnection;
import it.thera.thip.base.articolo.ArticoloTM;
import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 04/03/2024
 * <br><br>
 * <b></b>
 * <p>
 * Prima stesura.<br>
 * Lavoro per l'esportazione dell'anagrafica articoli verso il Modula.<br>
 * Recupera tutti gli articoli con ClasseA = 'MO' e che non sono stati ancora processati.<br>
 * Per ognuno di questi viene fatta la insert nella tabella HOST_IMPEXP.dbo.IMP_ARTICOLI e viene flaggato come importato.<br>
 * <b>NB:</b> Si considerano commit singolari.
 * </p>
 */

public class YEsportazioneArticoliModula extends BatchRunnable implements Authorizable {

	@Override
	protected boolean run() {
		boolean isOk = true;
		output.println("** INIZIO ESPORTAZIONE ANAGRAFICA ARTICOLI VERSO MODULA  **");
		isOk = runEsportazione();
		output.println("** TERMINE ESPORTAZIONE ANAGRAFICA ARTICOLI VERSO MODULA  **");
		return isOk;
	}

	protected boolean runEsportazione() {
		List<YArticolo> lista = listaArticoloDaEsportareVersoModula();
		if(lista.size() > 0) {
			int counter_esportati = 0;
			int count_falliti = 0;
			output.println(" --> Ho trovato: "+lista.size()+" articoli da esportare verso Modula");
			Connection modulaConnection = null;
			try {
				modulaConnection = YModulaConnection.getModulaConnection();
				for (YArticolo articolo : lista) {
					try {
						int ris = YArticolo.esportaArticoloVersoModula(modulaConnection, articolo);
						if(ris > 0) {
							int risArt = YArticolo.aggiornaStatoEsportazioneModulaArticolo(articolo.getIdArticolo(), true);
							if(risArt > 0) {
								modulaConnection.commit();
								ConnectionManager.commit();
								counter_esportati++;
							}else {
								output.println(" --> Articolo: "+articolo.getIdArticolo()+", qualcosa e' andato storto nell'aggiornamento del \n "
										+ "flag processato, rc update = "+risArt);
								ConnectionManager.rollback();
								modulaConnection.rollback();
								count_falliti++;
							}
						}else {
							if(ris == -999) {
								output.println(" --> La connessione a Modula e' chiusa, l'update fallisce...");
							}
							lista.remove(articolo);
						}
					} catch (SQLException e) {
						e.printStackTrace(Trace.excStream);
					}

				}
			} catch (ModulaConnectionException e) {
				e.printStackTrace(Trace.excStream);
			}finally {
				try {
					if(modulaConnection != null) {
						modulaConnection.close();
					}
				}catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
			output.println(" --> Ho esportato correttamente: "+counter_esportati+" articoli");
			if(count_falliti > 0) {
				output.println(" --> Ho fallito l'esportazione di: "+count_falliti+" articoli");
			}
		}else {
			output.println(" --> Non e' presente nessuna articolo da esportare, termino...");
			return true;
		}
		return true;
	}

	/**
	 * @author Daniele Signoroni
	 * <p>
	 * Recupera la lista degli articoli da esportare verso il Modula.<br>
	 * Sono tutti quegli articoli che hanno come ClasseA = 'MO' <br> e il flag {@link YArticolo#isEsportatoModula()} = false.<br>
	 * </p>
	 * @return una lista di PersistentObj YArticolo
	 */
	@SuppressWarnings("unchecked")
	public static List<YArticolo> listaArticoloDaEsportareVersoModula(){
		List<YArticolo> list = new ArrayList<>();
		try {
			String where = " "+YArticoloTM.ID_AZIENDA+" = '"+Azienda.getAziendaCorrente()+"' "
					+ " AND "+ArticoloTM.ID_CLASSE_A+" = 'MO'"
					+ " AND EXISTS ( "
					+ "		SELECT "
					+ "			1 "
					+ "		FROM "
					+ "			"+YArticoloTM.TABLE_NAME_EXT+" "
					+ "		WHERE "
					+ "			"+YArticoloTM.ID_AZIENDA+" = "+ArticoloTM.TABLE_NAME+"."+ArticoloTM.ID_AZIENDA+" "
					+ "AND "+YArticoloTM.ID_ARTICOLO+" = "+ArticoloTM.TABLE_NAME+"."+ArticoloTM.ID_ARTICOLO+" "
					+ "AND "+YArticoloTM.ESPORTATO_MODULA+" = 'N')";
			list = YArticolo.retrieveList(YArticolo.class, where, "", false);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return list;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YExpArtMODULA";
	}

}
