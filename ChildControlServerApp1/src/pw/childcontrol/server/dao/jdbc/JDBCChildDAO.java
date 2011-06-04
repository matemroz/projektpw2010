package pw.childcontrol.server.dao.jdbc;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pw.childcontrol.server.dao.ChildDAO;
import pw.childcontrol.server.dao.domain.AreaPoint;
import pw.childcontrol.utils.db.DatabaseUtils;
import pw.childcontrol.utils.security.SecurityUtils;

public class JDBCChildDAO implements ChildDAO {

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
		String value = "'" + Float.toString(stepTime) + "'";
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
		String value = "'" + name + "'";
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

	/**
	 * Rejestruje numer IMEI konkretnego dziecka i zwraca wygenerowany, unikalny
	 * klucz potrzebny do rejestracji.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param imei
	 *            numer IMEI
	 * @return napis zawierający unikalny klucz lub null w wypadku niepowodzenia
	 */
	@Override
	public String registerImei(int idChild, String imei) {
		String tableName = "CHILDREN";
		String columnName = "IMEI";
		String value = "'" + imei + "'";
		String condition = "IDCHILD = '" + idChild + "'";
		String key = "";

		DatabaseUtils.updateCommand(tableName, columnName, value, condition);

		try {
			key = SecurityUtils.generateMD5Hash(imei);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (key != "")
			setKey(idChild, "'" + key + "'");

		return key;
	}

	// ### KEY ###
	/**
	 * Ustawia unikalny klucz dziecka
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param key
	 *            unikalny klucz
	 * @return prawda - jeśli operacja się udała, fałsz - jeśli zakończono
	 *         niepowodzeniem
	 */
	public boolean setKey(int idChild, String key) {
		String tableName = "CHILDREN";
		String columnName = "KEY";
		String value = "'" + key + "'";
		String condition = "IDCHILD = '" + idChild + "'";
		boolean success = false;

		if (DatabaseUtils
				.updateCommand(tableName, columnName, value, condition) == true)
			success = true;

		return success;
	}

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
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		String value = "'" + sdf.format(date) + "'";
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

		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnName, condition);

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				dateOfBirth = rs.getString("DATEOFBIRTH");
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting DATEOFBIRTH from database.");
		}

		df = new SimpleDateFormat("dd-mm-yyyy");
		try {
			dob = (Date) df.parse(dateOfBirth);
			System.out.println(dob.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dob;
	}

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
		String condition = "IDCHILD = '"
				+ idChild
				+ "' "
				+ "AND IDMARKER = (SELECT MAX(IDMARKER) FROM MARKER WHERE IDCHILD = '"
				+ idChild + "')";
		String columnNames = " X, Y ";
		String tableName = "MARKER";
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnNames,
				condition);

		double x = 0, y = 0;

		AreaPoint areaPoint = null;

		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				x = Double.parseDouble(rs.getString("X"));
				y = Double.parseDouble(rs.getString("Y"));
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting KEY from database.");
		}

		areaPoint = new AreaPoint(x, y);

		return areaPoint;
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
		String tableName = "MARKER";
		String date = "CURRENT_TIMESTAMP";
		boolean success = false;
		List<String> columnNames = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		columnNames.add("IDCHILD");
		columnNames.add("X");
		columnNames.add("Y");
		columnNames.add("DATETIME");

		values.add("'" + idChild + "'");
		values.add("'" + Double.toString(point.getX()) + "'");
		values.add("'" + Double.toString(point.getY()) + "'");
		values.add(date);

		if (DatabaseUtils.insertCommand(tableName, columnNames, values) == true)
			success = true;

		return success;
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
	 * @return lista punktów geograficznych w któych przebywało dziecko lub null jeśli brak
	 */
	@Override
	public List<AreaPoint> getLocationHistory(int idChild, int historyLimit) {
		String condition = "IDCHILD = '" + idChild + "' AND ROWNUM <= " + historyLimit + " order by IDMARKER desc";
		String columnNames = " X, Y ";
		String tableName = "MARKER";
		List<AreaPoint> areaPoints = new ArrayList<AreaPoint>();
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnNames,
				condition);		
		
		if (rs == null) {
			return null;
		}

		try {
			while (rs.next()) {
				AreaPoint areaPoint = null;
				double x = 0, y = 0;
				x = Double.parseDouble(rs.getString("X"));
				y = Double.parseDouble(rs.getString("Y"));
				areaPoint = new AreaPoint(x,y);
				areaPoints.add(areaPoint);
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting KEY from database.");
		}

		return areaPoints;
	}

	/**
	 * Pobiera listę punktów geograficznych w których ostatnio przebywało
	 * dziecko. Metoda pozwala określić zakres czasowy.
	 * 
	 * @param idChild
	 *            indiwydualny numer dziecka
	 * @param dateLimit
	 *            data od której sprawdzono historię położeń geograficznych. Format daty, np. '11/05/22 19:19:34'.
	 * @return lista punktów geograficznych w któych przebywało dziecko
	 */
	//@Override
	public List<AreaPoint> getLocationHistory(int idChild, Date dateLimit) {
		String condition = "IDCHILD = '" + idChild + "'" + 
		"AND TO_CHAR(DATETIME,'YY/MM/DD HH24:MI:SS') >= '" + dateLimit + "' ORDER BY IDMARKER DESC";
		String columnNames = " X, Y ";
		String tableName = "MARKER";
		List<AreaPoint> areaPoints = new ArrayList<AreaPoint>();
		ResultSet rs = DatabaseUtils.queryCommand(tableName, columnNames,
				condition);		
		
		if (rs.equals(null)) {
			return null;
		}

		try {
			while (rs.next()) {
				AreaPoint areaPoint = null;
				double x = 0, y = 0;
				x = Double.parseDouble(rs.getString("X"));
				y = Double.parseDouble(rs.getString("Y"));
				areaPoint = new AreaPoint(x,y);
				areaPoints.add(areaPoint);
			}
		} catch (SQLException ex) {
			System.err.println("Problem, while getting KEY from database.");
		}

		return areaPoints;
	}
	
	public static void main(String[] args){
		Date dob = null;
		DateFormat df;
		String dateOfBirth = "01-01-2000";


		df = new SimpleDateFormat("dd-mm-yyyy");
		try {
			dob = (Date) df.parse(dateOfBirth);
			System.out.println(dob.toString());
		} catch(ParseException e){
			e.toString();
		}
	}

}
