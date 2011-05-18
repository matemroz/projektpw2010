package pw.childcontrol.server.database.jdbc.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import pw.childcontrol.server.database.jdbc.ConnectionManager;
import pw.childcontrol.server.database.jdbc.dao.ChildDAO;

public class DatabaseUtils {

	/**
	 * INSERT into database Oracle
	 * 
	 * @param tableName
	 *            table name
	 * @param columns
	 *            columns list
	 * @param values
	 *            values list
	 * @return true if succes of false if not
	 */
	public static boolean insertCommand(String tableName, List<String> columns,
			List<String> values) {

		boolean success = false;
		Iterator<String> itColumns = columns.iterator();
		Iterator<String> itValues = values.iterator();
		StringBuilder sql = new StringBuilder("INSERT INTO ");

		sql.append(tableName).append(" (");

		while (itColumns.hasNext()) {
			sql.append(itColumns.next()).append(", ");
		}

		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(") VALUES (");

		while (itValues.hasNext()) {
			String value = itValues.next().toString();
			sql.append("'").append(value).append("'").append(", ");
		}

		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" );");

		try {
			PreparedStatement st = ConnectionManager.getDatabaseConnection().prepareStatement(sql.toString());
			System.out.println(sql.toString());
			st.setQueryTimeout(10);
			st.setQueryTimeout(10);
			st.execute(sql.toString());
			st.close();
			success = true;
		} catch (SQLException ex) {
			System.err.println("Error while inserting data into database. \n"
					+ ex.getSQLState() + "\n" + ex.getErrorCode());
			success = false;
		}
		return success;
	}

	/**
	 * UPDATE in Oracle database
	 * 
	 * @param tableName
	 *            table name
	 * @param columnName
	 *            column name which will be updated
	 * @param condition
	 *            condition after WHERE
	 * @return true if succes of false if not
	 */

	public static boolean updateCommand(String tableName, String columnName,
			String value, String condition) {

		boolean success = false;
		int retValue = 0;
		StringBuilder sql = new StringBuilder("UPDATE ");
		sql.append(tableName).append(" SET ").append(columnName).append(" = ")
				.append(value).append(" ");
		sql.append("WHERE ").append(condition);

		try {
			PreparedStatement st = ConnectionManager.getDatabaseConnection().prepareStatement(sql.toString());
			System.out.println(sql.toString());
			st.setQueryTimeout(10);
			retValue = st.executeUpdate();
			st.close();
			
		} catch (SQLException ex) {
			System.err.println("Error while updating data in database. \n"
					+ ex.getSQLState() + "\n" + ex.getErrorCode());
		}
		System.out.println("retValue: " + retValue);
		if (retValue <= 0)
			success = false;
		else
			success = true;

		return success;
	}

	/**
	 * DELETE data from Oracle database
	 * 
	 * @param tableName
	 *            table name
	 * @param columnName
	 *            column name which will be updated
	 * @param condition
	 *            condition after WHERE
	 * @return true if succes of false if not
	 */

	public static boolean deleteCommand(String tableName, String condition) {

		boolean success = false;
		StringBuilder sql = new StringBuilder("DELETE FROM ");

		if (!condition.equals("") || !condition.equals(null)) {
			sql.append(tableName).append(" WHERE ").append(condition);
		}

		try {
			PreparedStatement st = ConnectionManager.getDatabaseConnection().prepareStatement(sql.toString());
			System.out.println(sql.toString());
			st.setQueryTimeout(10);
			success = st.execute(sql.toString());
			st.close();
		} catch (SQLException ex) {
			System.err.println("Error while deleting data from database. \n"
					+ ex.getSQLState() + "\n" + ex.getErrorCode());
		}
		return success;
	}

	/**
	 * Query command for getting data from Oracle database.
	 * 
	 * @param tableName
	 *            table name
	 * @param columnName
	 *            querying column
	 * @param condition
	 *            condition after WHERE
	 * @return ResultSet object that contains the data produced by the given
	 *         query; never null
	 */

	public static ResultSet queryCommand(String tableName, String columnName,
			String condition) {

		ResultSet rs = null;
		StringBuilder sql = null;

		sql = new StringBuilder("SELECT ");

		if (condition.equals("")) {
			sql.append(columnName).append(" FROM ").append(tableName);
		} else {
			sql.append(columnName).append(" FROM ").append(tableName)
					.append(" WHERE ").append(condition);
		}

		try {
			Statement st = ConnectionManager.getDatabaseConnection()
					.createStatement();
			System.out.println(sql.toString());
			st.setQueryTimeout(15);
			rs = st.executeQuery(sql.toString());
		} catch (SQLException ex) {
			System.err.println("Error while querying data from database.\n"
					+ ex.getSQLState() + "\n" + ex.getErrorCode());
		}
		return rs;
	}

	/**
	 * Query command for getting data from Oracle database. Tables are in
	 * relation.
	 * 
	 * @param columnNames
	 *            list of querying column names
	 * @param tableNames
	 *            table names
	 * @param columnRelations
	 *            column names which join table in relation
	 * @param condition
	 *            condition after WHERE
	 * @return ResultSet object that contains the data produced by the given
	 *         query; never null
	 */
	public static ResultSet queryCommand(List<String> columnNames,
			List<String> tableNames, List<String> columnRelations,
			String condition) {

		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("SELECT ");
		Iterator<String> itColNames = columnNames.iterator();
		Iterator<String> itTableNames = tableNames.iterator();
		Iterator<String> itTableRelations = columnRelations.iterator();
		String tabName = "";
		String prevTabName = "";
		String tableRelation = "";
		int i = 0;

		while (itColNames.hasNext()) {
			sql.append(itColNames.next().toString());
			sql.append(", ");
		}

		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append(" FROM ");

		while (itTableNames.hasNext()) {
			tabName = itTableNames.next().toString();
			tableRelation = "";
			sql.append(tabName);

			if (i > 0 && itTableRelations.hasNext()) {
				sql.append(" ON ");
				tableRelation = itTableRelations.next().toString();
				sql.append(prevTabName).append(".").append(tableRelation);
				sql.append(" = ");
				sql.append(tabName).append(".").append(tableRelation);
			}
			prevTabName = tabName;
			sql.append(" INNER JOIN ");
			i++;
		}

		sql.delete(sql.toString().length() - " INNER JOIN ".length(), sql
				.toString().length());
		sql.append(" WHERE ");
		sql.append(condition);

		System.out.println(sql.toString());

		try {
			Statement st = ConnectionManager.getDatabaseConnection()
					.createStatement();
			st.setQueryTimeout(15);
			rs = st.executeQuery(sql.toString());
		} catch (SQLException ex) {
			System.err.println("Error while querying data from database. \n"
					+ ex.getSQLState() + "\n" + ex.getErrorCode());
		}
		return rs;
	}
	
}
