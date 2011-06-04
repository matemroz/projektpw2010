package pw.childcontrol.server.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import pw.childcontrol.server.dao.ParentDAO;
import pw.childcontrol.server.dao.jdbc.db.ConnectionManager;
import pw.childcontrol.utils.db.DatabaseUtils;

public class JDBCParentDAO implements ParentDAO{
	//### RODZIC dodawanie/usuwanie ###
	/**
	 * Dodaje rodzica do bazy danych.
	 * 
	 * @param email adres e-mail rodzica
	 * @param name imie rodzica
	 * @param password haslo rodzica
	 * @return id nowego rodzica
	 */
	@Override
	public int addParent(String email, String name, String password){
		/*String tableName = "PARENT";
		List<String> columnList = new LinkedList<String>();
		List<String> valueList = new LinkedList<String>();
		columnList.add("EMAIL");
		columnList.add("NAME");
		columnList.add("SHORTEDPASSWORD");
		valueList.add(String.valueOf(email));
		valueList.add(String.valueOf(name));
		valueList.add(String.valueOf(password));
		return DatabaseUtils.insertCommand(tableName, columnList, valueList);*/
		int idParent = 0;
		String tableName = "PARENT";
		String columnNames = "EMAIL,PARENTNAME,SHORTEDPASSWORD";
		String values = "'" + email + "','" + name + "','" + password + "'";
		String insertQuery = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + values + ")";
		System.out.println(insertQuery);
		
		try {
			/* przygotowanie zapytania zwracającego id dodanego wiersza */
			String generatedId[] = {"IDPARENT"};
			
			System.out.println(insertQuery);
			PreparedStatement pstmt = ConnectionManager.getDatabaseConnection().prepareStatement(insertQuery, generatedId);
			
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				idParent = rs.getInt(1);
				System.out.println(idParent);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idParent;
	}
	/**
	 * Usuwa rodzica z bazy danych.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean removeParent(int idParent){
		return false;
	}

	//### CHILDREN LIST###
	/**
	 * Pobiera listę dzieci przypisanych do konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return tablica indywidualnych numerów dzieci
	 */
	@Override
	public List<Integer> getChildsList(int idParent) {
		
		List<Integer> childsList = new LinkedList<Integer>();
		String tableName = "FAMILY";
		String columnName = "IDCHILD";
		String condition = "IDPARENT = '" + idParent + "'";
		
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);
		
		
		
		if (rs.equals(null)) {
			return childsList;
		}
		
		try {
			while (rs.next()) {
				childsList.add(rs.getInt(columnName));
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting childs list from database.");
		}

		return childsList;
	}

	/**
	 * PROTOTYP - NIE WIADOMO CZY BEDZIE DZIAŁAŁ
	 * Pozwala na zaktualizowanie lokacji wszystkich dzieci.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean relocateChilds(int idParent) {
		return false;
	}

	//### CHILD ###
	/**
	 * Przypisuje nowe dziecko do konkretnego rodzica i zwraca jego numer z bazy danych.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return indywidualny numer dziecka
	 */
	@Override
	public boolean addChild(int idParent, int idChild) {
		// TODO Auto-generated method stub
		return false;
	}
	
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
	@Override
	public int addChild(int idParent, int idArea, String imei, String name, Date dateOfBirth, String key) {
		/*FAZA 1: Dodawnie dziecka do bazy danych*/
		int idChild = 0;
		String tableName = "CHILDREN";
		String columnNames = "IDAREA, IMEI, CHILDNAME, KEY";
		String values = idArea + ",'" + imei + "','" + name + "','" + key + "'";
		String insertQuery = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + values + ")";
		System.out.println(insertQuery);
		
		try {
			/* przygotowanie zapytania zwracającego id dodanego wiersza */
			String generatedId[] = {"IDCHILD"};
			PreparedStatement pstmt = ConnectionManager.getDatabaseConnection().prepareStatement(insertQuery, generatedId);
			
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				idChild = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*FAZA 2: Przypisanie nowego dziecka do rodzica*/
		tableName = "FAMILY";
		List<String> columnList = new LinkedList<String>();
		List<String> valueList = new LinkedList<String>();
		columnList.add("IDPARENT");
		columnList.add("IDCHILD");
		valueList.add(String.valueOf(idParent));
		valueList.add(String.valueOf(idChild));
		
		
		boolean result = DatabaseUtils.insertCommand(tableName, columnList, valueList);
		return idChild;
	}

	/**
	 * Usuwa powiązanie dziecka z rodzicem.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param idChild indywidualny numer dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean removeChild(int idParent, int idChild) {
		String tableName = "FAMILY";
		String condition = "IDPARENT = '" + idParent + "' AND IDCHILD = '" + idChild + "'";
		
		return DatabaseUtils.deleteCommand(tableName, condition);
	}

	//### EMAIL ###
	/**
	 * Ustawia nowy e-mail konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param email napis zawierający adres e-mail
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean setEmail(int idParent, String email) {
		String tableName = "PARENT";
		String columnName = "EMAIL";
		String value = "'" + email + "'";
		String condition = "IDPARENT = '" + idParent + "'";
		
		return DatabaseUtils.updateCommand(tableName, columnName, value, condition);
	}

	/**
	 * Pobiera e-mail konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający adres e-mail
	 */
	@Override
	public String getEmail(int idParent) {
		String email = "";
		String tableName = "PARENT";
		String columnName = "EMAIL";
		String condition = "IDPARENT = '" + idParent + "'";
		
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName, condition);
		
		if (rs.equals(null)) {
			return "";
		}
		
		try {
			while (rs.next()) {
				email = rs.getString("EMAIL");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting EMAIL from database.");
		}
		return email;
	}

	//### NAME ###
	/**
	 * Pobiera imię konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający imię rodzica
	 */
	@Override
	public String getName(int idParent) {
		String name = "";
		String tableName = "PARENT";
		String columnName = "NAME";
		String condition = "IDPARENT = '" + idParent + "'";
		
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName, condition);
		
		if (rs.equals(null)) {
			return "";
		}
		
		try {
			while (rs.next()) {
				name = rs.getString("NAME");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting NAME from database.");
		}
		return name;
	}
	
	/**
	 * Ustawia nowe imię konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param name napis zawierający imię rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean setName(int idParent, String name) {
		String tableName = "PARENT";
		String columnName = "PARENTNAME";
		String value = "'" + name + "'";
		String condition = "IDPARENT = '" + idParent + "'";
		
		return DatabaseUtils.updateCommand(tableName, columnName, value, condition);
	}

	//### PASSWORD ###
	/**
	 * Pobiera hasło dostępu konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @return napis zawierający hasło dostępu rodzica
	 */
	@Override
	public String getPassword(int idParent) {
		String pass = "";
		String tableName = "PARENT";
		String columnName = "SHORTEDPASSWORD";
		String condition = "IDPARENT = '" + idParent + "'";
		
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName, condition);
		
		if (rs.equals(null)) {
			return "";
		}
		
		try {
			while (rs.next()) {
				pass = rs.getString("SHORTEDPASSWORD");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting SHORTEDPASSWORD from database.");
		}
		return pass;
	}

	/**
	 * Ustawia nowe hasło dostępu konkretnego rodzica.
	 * 
	 * @param idParent indywidaulny numer rodzica
	 * @param password napis zawierający hasło dostępu rodzica
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono niepowodzeniem
	 */
	@Override
	public boolean setPassword(int idParent, String password) {
		String tableName = "PARENT";
		String columnName = "SHORTEDPASSWORD";
		String value = "'" + password + "'";
		String condition = "IDPARENT = '" + idParent + "'";
		
		return DatabaseUtils.updateCommand(tableName, columnName, value, condition);
	}

	
	public static void main(String [ ] args){
		JDBCParentDAO instance = new JDBCParentDAO();
		int idParent = 1;
		int idChild = 1;
		String email = "2testowy@test.pl";
		String name = "Zulugula";
		String password = "haslo";
		//instance.getChildsList(idParent);
		instance.addParent(email,name,password);
		//instance.addChild(idParent,1,"imei","Robert",new Date(2011,04,07),"kluczyk");
		//System.out.println(instance.getEmail(idParent));
		//System.out.println(instance.getName(idParent));
		//System.out.println(instance.getPassword(idParent));
		//instance.removeChild(idParent, idChild);
		//instance.setEmail(idParent, email);
		//instance.setName(idParent, name);
		//instance.setPassword(idParent, password);
	}
	
	

	
	/* TODO:
	 * removeParent
	 * addChild: jaki format dla Daty? ciezko znalesc prawidlowy format do inserta, jak bedzie wiadomo jaki format to dodac date do zapytania
	 */
}
