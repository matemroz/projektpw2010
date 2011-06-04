/**
@author      Mateusz Mróz
@version	 1.0
*/
package pw.childcontrol.server.dao.jdbc.db;

import java.io.PrintWriter;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;
import pw.childcontrol.server.dao.jdbc.JDBCParentDAO;

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
			String connectionString = "jdbc:oracle:thin:student/student@localhost:1521:XE";
			ods.setURL(connectionString);
			ods.setLoginTimeout(1);
			conn = ods.getConnection();
			if(conn != null ){
				System.out.println("Oracle database connection established successfully");
			}
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
	 * @return 
	 * @throws SQLException  If an sql exception occured.
	 */	
	public static void disconnectFromDatabase() throws SQLException{
		cm.conn.close();
	}
	
	public static void main(String[] argv){
		try {
			ConnectionManager.getDatabaseConnection();
			JDBCParentDAO parent = new JDBCParentDAO();
			int idParent = 1;
			//idParent = parent.addParent("test@gmail.com", "Jan Nowak", "h@slo");
			
			
			//JDBCAreaManagerDAO area = new JDBCAreaManagerDAO();
			
			System.out.println("Hello World! id " + idParent);// + String.valueOf(imei.length()));
			System.out.println("Rodzic: " + parent.getEmail(idParent) + " " + parent.getName(idParent) + " " + parent.getPassword(idParent));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
