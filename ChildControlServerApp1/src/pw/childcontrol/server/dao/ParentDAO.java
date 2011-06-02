/**
 * 
 */
package pw.childcontrol.server.dao;

import java.util.Date;
import java.util.List;

public interface ParentDAO {
	//### RODZIC dodawanie/usuwanie ###
	/**
	 * Dodaje rodzica do bazy danych.
	 * 
	 * @param email adres e-mail rodzica
	 * @param name imie rodzica
	 * @param password haslo rodzica
	 * @return id nowego rodzica
	 */
	public int addParent(String email, String name, String password);
	/**
	 * Usuwa rodzica z bazy danych.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean removeParent(int idParent);
	//###
	
	//### CHILDREN LIST ###
	/**
	 * Pobiera listę dzieci przypisanych do konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return tablica indywidualnych numerów dzieci
	 */
	public List<Integer> getChildsList(int idParent);
	
	/**
	 * PROTOTYP - NIE WIADOMO CZY BEDZIE DZIAŁAŁ
	 * Pozwala na zaktualizowanie lokacji wszystkich dzieci.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean relocateChilds(int idParent);
	//###
	
	//### CHILD ###
	/**
	 * Przypisuje ISTNIEJĄCE dziecko do konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean addChild(int idParent, int idChild);
	
	/**
	 * Tworzy nowe dziecko i przypisuje do konkretnego rodzica i zwraca jego numer z bazy danych.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param idArea id zagrozonej strefy dla dziecka
	 * @param imei IMEI komorki dziecka
	 * @param name imię dziecka
	 * @param dateOfBirth data urodzenia dziecka
	 * @param key indywiadualny klucz dziecka, potrzebny przy rejestracji
	 * @return indywidualny numer dziecka
	 */
	int addChild(int idParent, int idArea, String imei, String name,
			Date dateOfBirth, String key);
	
	/**
	 * Usuwa powiązanie dziecka z rodzicem.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param idChild indywidualny numer dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean removeChild(int idParent, int idChild);
	//###
	
	//### EMAIL ###
	/**
	 * Ustawia nowy e-mail konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param email napis zawierający adres e-mail
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setEmail(int idParent, String email);
	
	/**
	 * Pobiera e-mail konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający adres e-mail
	 */
	public String getEmail(int idParent);
	//###
	
	//### NAME ###
	/**
	 * Pobiera imię konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający imię rodzica
	 */
	public String getName(int idParent);
	
	/**
	 * Ustawia nowe imię konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param name napis zawierający imię rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setName(int idParent, String name);
	//###
	
	//### PASSWORD ###
	/**
	 * Pobiera hasło dostępu konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający hasło dostępu rodzica
	 */
	public String getPassword(int idParent);
	
	/**
	 * Ustawia nowe hasło dostępu konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param password napis zawierający hasło dostępu rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	public boolean setPassword(int idParent, String password);
	//###
	

}
