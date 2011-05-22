/**
 * 
 */
package pw.childcontrol.server.dao;

public interface ParentDAO {
	//### CHILDREN LIST###
	/**
	 * Pobiera listę dzieci przypisanych do konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return tablica indywidualnych numerów dzieci
	 */
	public int[] getChildsList(int idParent);
	
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
	 * Przypisuje nowe dziecko do konkretnego rodzica i zwraca jego numer z bazy danych.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return indywidualny numer dziecka
	 */
	public int addChild(int idParent);
	
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
