package pw.childcontrol.server.database.jdbc.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pw.childcontrol.server.database.jdbc.AreaPoint;
import pw.childcontrol.server.familymanager.IChild;
import pw.childcontrol.server.database.jdbc.utils.DatabaseUtils;

public class ChildDAO implements IChild {

	// ### LOCATION ###
	/**
	 * Znajduje aktualne położenie geograficzne dziecka.
	 * 
	 * @param idChild
	 *            indywidualny numer dziecka
	 * @return aktualne położenie geograficzne
	 */
	@Override
	public AreaPoint getLocation(int idChild) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ustawia położenie geograficzne dziecka.
	 * 
	 * @param idChild
	 *            indywidualny numer dziecka
	 * @param point
	 *            punkt z danymi o położeniu geograficznym
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	@Override
	public boolean setLocation(int idChild, AreaPoint point) {
		// TODO Auto-generated method stub
		return false;
	}

	// ### STEP TIME ###
	/**
	 * Ustawia czas odstępu między sprawdzaniem położenia geograficznego dla
	 * konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indywidualny numer dziecka
	 * @param stepTime
	 *            odstęp czasu między sprawdzaniem położenia
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	@Override
	public boolean setStepTime(int idChild, float stepTime) {
		String tableName = "CHILDREN";
		String columnName = "STEPTIME";
		String value = Float.toString(stepTime);
		String condition = "IDCHILD = '" + idChild + "'";
		boolean success = false;

		if (DatabaseUtils
				.updateCommand(tableName, columnName, value, condition) == true)
			success = true;

		return success;
	}

	/**
	 * Pobiera czas odstępu między sprawdzaniem położenia geograficznego dla
	 * konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indywidualny numer dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	@Override
	public float getStepTime(int idChild) {
		String tableName = "CHILDREN";
		String columnName = "STEPTIME";
		String condition = "IDCHILD = '" + idChild + "'";
		float stepTime = 0;
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);

		if (rs.equals(null)) {
			return -1;
		}

		try {
			while (rs.next()) {
				stepTime = rs.getFloat("STEPTIME");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting STEPTIME from database.");
		}

		return stepTime;
	}

	// ### NAME ###
	/**
	 * Ustawia nowe imię dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param name
	 *            imię dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	@Override
	public boolean setName(int idChild, String name) {
		String tableName = "CHILDREN";
		String columnName = "CHILDNAME";
		String value = name;
		String condition = "IDCHILD = '" + idChild + "'";
		boolean success = false;

		if (DatabaseUtils
				.updateCommand(tableName, columnName, value, condition) == true)
			success = true;

		return success;
	}

	/**
	 * Pobiera imię konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @return napis zawierjący imię dziecka lub null w wypadku niepowodzenia
	 */
	@Override
	public String getName(int idChild) {
		String tableName = "CHILDREN";
		String columnName = "CHILDNAME";
		String condition = "IDCHILD = '" + idChild + "'";
		String name = "";
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				name = rs.getString("CHILDNAME");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting CHILDNAME from database.");
		}

		return name;
	}

	// ### IMEI ###
	/**
	 * Pobiera numer IMEI konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @return napis zawierający numer IMEI
	 */
	@Override
	public String getImei(int idChild) {
		String tableName = "CHILDREN";
		String columnName = "IMEI";
		String condition = "IDCHILD = '" + idChild + "'";
		String imei = "";
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				imei = rs.getString("IMEI");
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting IMEI from database.");
		}

		return imei;
	}

	//### 
	//### TODO ###
	//###
	/**
	 * Rejestruje numer IMEI konkretnego dziecka i zwraca wygenerowany, unikalny
	 * klucz potrzebny do rejestracji.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param imei
	 *            numer IMEI
	 * @return napis zawierający unikalny klucz
	 */
	@Override
	public String registerImei(int idChild, String imei) {
		String tableName = "CHILDREN";
		String columnName = "CHILDNAME";
		String value = imei;
		String condition = "IDCHILD = '" + idChild + "'";
		boolean success = false;

		if (DatabaseUtils
				.updateCommand(tableName, columnName, value, condition) == true)
			success = true;

		return "";
	}

	// ### KEY ###
	/**
	 * Pobiera unikalny klucz dla konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @return napis zawierający unikalny klucz
	 */
	@Override
	public String getKey(int idChild) {
		String tableName = "CHILDREN";
		String columnName = "KEY";
		String condition = "IDCHILD = '" + idChild + "'";
		String key = "";
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				key = rs.getString("KEY");
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting KEY from database.");
		}

		return key;
	}

	// ### BIRTH DATE###
	/**
	 * Ustawia datę urodzenia konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param date
	 *            data urodzenia dziecka
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	@Override
	public boolean setDateOfBirth(int idChild, Date date) {
		String tableName = "CHILDREN";
		String columnName = "DATEOFBIRTH";
		String condition = "IDCHILD = '" + idChild + "'";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		String value = sdf.format(date);
		boolean success = false;

		if (DatabaseUtils
				.updateCommand(tableName, columnName, value, condition) == true)
			success = true;

		return success;
	}

	/**
	 * Pobiera datę urodzenia konkretnego dziecka.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @return data urodzenia dziecka
	 */
	@Override
	public Date getDateOfBirth(int idChild) {
		String tableName = "CHILDREN";
		String columnName = "DATEOFBIRTH";
		String condition = "IDCHILD = '" + idChild + "'";
		String dateOfBirth = "";
		Date dob = null;
		DateFormat df;

		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName,
				condition);

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				dateOfBirth = rs.getString("DATEOFBIRTH");
			}
		} catch (SQLException ex) {
			System.err
					.println("Problem, while getting DATEOFBIRTH from database.");
		}

		df = new SimpleDateFormat("dd-mm-yyyy");
		try {
			dob = (Date) df.parse(dateOfBirth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dob;
	}

	// ### LOCATION HISTORY ###
	/**
	 * Pobiera listę punktów geograficznych w których ostatnio przebywało
	 * dziecko. Metoda pozwala określić limit ilości punktów.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param historyLimit
	 *            limit ilości zwracanych punktów
	 * @return lista punktów geograficznych w któych przebywało dziecko
	 */
	@Override
	public List<AreaPoint> getLocationHistory(int idChild, int historyLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Pobiera listę punktów geograficznych w których ostatnio przebywało
	 * dziecko. Metoda pozwala określić zakres czasowy.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param dateLimit
	 *            data od której sprawdzono historię położeń geograficznych
	 * @return lista punktów geograficznych w któych przebywało dziecko
	 */
	@Override
	public List<AreaPoint> getLocationHistory(int idChild, Date dateLimit) {
		// TODO Auto-generated method stub
		return null;
	}
}
