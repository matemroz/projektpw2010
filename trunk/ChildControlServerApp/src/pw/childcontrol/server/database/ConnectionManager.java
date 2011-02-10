/**
@author      Mateusz Mróz
@version	 1.0
*/
package pw.childcontrol.server.database;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class ConnectionManager {

	private static final ConnectionManager cm = new ConnectionManager();
	private Connection conn;
	private OracleDataSource ods;
	
    /**
	  * Class constructor which creates the connection to database.
	*/
	private ConnectionManager(){
		try {
			ods = new OracleDataSource();
			String connectionString = "jdbc:oracle:thin:student/student@localhost:1521/XE";
			ods.setURL(connectionString);
			conn = ods.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Method responsible for getting connection to database.
	 * @return object Connection type
	 * @throws SQLException  If an sql exception occured.
	 */
	public static Connection getDatabaseConnection() throws SQLException{
		if(cm.conn == null || cm == null)
			new ConnectionManager();
		return cm.conn;
	}
	
	/**
	 * Method responsible for closing connection from database.
	 * @throws SQLException  If an sql exception occured.
	 */
	public void disconnectFromDatabase() throws SQLException{
		conn.close();
	}
}
