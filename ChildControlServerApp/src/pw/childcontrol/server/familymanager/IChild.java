/**
 * 
 */
package pw.childcontrol.server.familymanager;

import java.util.Date;
import java.util.List;
import pw.childcontrol.server.database.jdbc.AreaPoint;

public interface IChild {
	//### LOCATION ###
	/**
	 * Znajduje aktualne położenie geograficzne dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @return aktualne położenie geograficzne
	 */
	public AreaPoint getLocation(int idChild);
	
	/**
	 * Ustawia położenie geograficzne dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @param point punkt z danymi o położeniu geograficznym
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setLocation(int idChild, AreaPoint point);
	//###
	
	//### STEP TIME ###
	/**
	 * Ustawia czas odstępu między sprawdzaniem położenia geograficznego
	 * dla konkretnego dziecka.
	 *  
	 * @param idChild indywidualny numer dziecka
	 * @param stepTime odstęp czasu między sprawdzaniem położenia
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setStepTime(int idChild, float stepTime);
	
	/**
	 * Pobiera czas odstępu między sprawdzaniem położenia geograficznego
	 * dla konkretnego dziecka.
	 * 
	 * @param idChild indywidualny numer dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public float getStepTime(int idChild);
	//###
	
	//### NAME ###
	/**
	 * Ustawia nowe imię dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @param name imię dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem 
	 */
	public boolean setName(int idChild, String name);
	
	/**
	 * Pobiera imię konkretnego dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @return napis zawierjący imię dziecka lub null w wypadku niepowodzenia
	 */
	public String getName(int idChild);
	//###
	
	//### IMEI ###
	/**
	 * Pobiera numer IMEI konkretnego dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @return napis zawierający numer IMEI
	 */
	public String getImei(int idChild);
	
	/**
	 * Rejestruje numer IMEI konkretnego dziecka i zwraca wygenerowany,
	 * unikalny klucz potrzebny do rejestracji.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @param imei numer IMEI
	 * @return zwraca unikalny klucz
	 */
	public String registerImei(int idChild, String imei);
	//###
	
	//### KEY ###
	/**
	 * Pobiera unikalny klucz dla konkretnego dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @return napis zawierający unikalny klucz
	 */
	public String getKey(int idChild);
	//###
	
	//### BIRTH DATE###
	/**
	 * Ustawia datę urodzenia konkretnego dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @param date data urodzenia dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setDateOfBirth(int idChild, Date date);
	
	/**
	 * Pobiera datę urodzenia konkretnego dziecka.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @return data urodzenia dziecka
	 */
	public Date getDateOfBirth(int idChild);
	//###
	
	//### LOCATION HISTORY ###
	/**
	 * Pobiera listę punktów geograficznych w których ostatnio przebywało dziecko.
	 * Metoda pozwala określić limit ilości punktów.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @param historyLimit limit ilości zwracanych punktów
	 * @return lista punktów geograficznych w któych przebywało dziecko
	 */
	public List<AreaPoint> getLocationHistory( int idChild, int historyLimit);
	
	/**
	 * Pobiera listę punktów geograficznych w których ostatnio przebywało dziecko.
	 * Metoda pozwala określić zakres czasowy.
	 * 
	 * @param idChild indiwydualny numer dziecka
	 * @param dateLimit data od której sprawdzono historię położeń geograficznych
	 * @return lista punktów geograficznych w któych przebywało dziecko
	 */
	public List<AreaPoint> getLocationHistory( int idChild, Date dateLimit);
	//###
}
