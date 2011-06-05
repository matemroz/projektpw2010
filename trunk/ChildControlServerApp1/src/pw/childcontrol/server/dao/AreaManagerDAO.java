/**
 * 
 */
package pw.childcontrol.server.dao;

import java.util.List;

import pw.childcontrol.server.dao.domain.Area;
import pw.childcontrol.server.dao.domain.AreaPoint;

public interface AreaManagerDAO {
	//### AREA ###
	/**
	 * Pobiera listę niebezpiecznych miejsc zdefiniowanych dla konkretnego dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @return lista niebezpiecznych miejsc
	 */
	public List<Area> getDangerousAreas(int idChild);
	
	/**
	 * Dodaje niebezpieczne miejsca dla konkretnego dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @param area niebezpieczne miejsce
	 * @return prawda - jeśli ope racja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public int addDangerousArea(int idChild, Area area);
	
	/**
	 * Usuwa niebezpieczne miejsce zdefiniowane dla konkretnego dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @param area niebezpieczne miejsce
	 * @return id utworzonego obszaru, jesli 0 - wystapil blad
	 */
	public boolean removeDangerousArea(int idChild, Area area);
	//###
	
	//### AREA POINT ###
	/**
	 * Sprawdza czy punkt geograficzny należy do obszaru zdefiniowanego jako niebezpieczny
	 * dla konkretnego dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @param point punkt geograficzny
	 * @return prawda - jeśli punkt jest niebezpieczny, fałsz - jesli punkt jest bezpieczny
	 */
	public boolean idDangerousArea(int idChild, AreaPoint point);
	//###
}
