/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database;

import java.sql.*;

public class DatabaseManager {
	
	private Statement st;
	
	/**
	  * DatabaseManager class constructor. Creates an instance of Statement object.
	*/
	public DatabaseManager(){
		try {
			st = ConnectionManager.getDatabaseConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method creates tables in the database if there are no tables with the same name.
	 * @throws SQLException  If an sql exception occured.
	 */
	public void createTables() throws SQLException{ 
	     String crTab = "DECLARE"
	    	 + " parent_tb_name VARCHAR2(50) := upper('Parent');"
	    	 + " family_tb_name VARCHAR2(50) := upper('Family');"
	    	 + " children_tb_name VARCHAR2(50) := upper('Children');"
	    	 + " marker_tb_name VARCHAR2(50) := upper('Marker');"
	    	 + " parentsettings VARCHAR2(50) := upper('ParentSettings');"
	    	 + " areapoint VARCHAR2(50) := upper('AreaPoint');"
	    	 + " area VARCHAR2(50) := upper('Area');"
	    	 + " v_table_name VARCHAR2(50);"
	         + " CURSOR c_1 IS"
	         	+ " SELECT table_name"
	         		+ " FROM user_tables"
	         		+ " WHERE table_name = parent_tb_name OR"
	         			+ " table_name = family_tb_name OR"
	         			+ " table_name = children_tb_name OR"
	         			+ " table_name = marker_tb_name OR"
	         			+ " table_name = parentsettings OR"
	         			+ " table_name = areapoint OR"
	         			+ " table_name = area;"
	         + " BEGIN"
	         + " OPEN c_1;"
	         + " FETCH c_1 INTO v_table_name;"
	         + " IF c_1%NOTFOUND THEN"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE Parent ("
	         		+ " idParent NUMBER(10)" 
	         			+ " CONSTRAINT parent_pk PRIMARY KEY"
	         			+ " CONSTRAINT idparent_nn not null," 
	         		+ " shortedPassword VARCHAR2(50) not null,"
	         		+ " email VARCHAR2(100) not null,"
	         		+ " name VARCHAR2(50) not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE Family ("
	         		+ " idFamily NUMBER(10)"
	         			+ " CONSTRAINT family_pk PRIMARY KEY"
	         			+ " CONSTRAINT idfamily_nn not null,"
	         		+ " idParent NUMBER(10) not null,"
	         		+ " idChild NUMBER(10)"
	         		+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE Children ("
	         		+ " idChild NUMBER(10)" 
	         			+ " CONSTRAINT children_pk PRIMARY KEY"
	         			+ " CONSTRAINT idchild_nn not null,"
	         		+ " idArea NUMBER(10) not null,"
	         		+ " imei VARCHAR2(50) not null,"
	         		+ " name VARCHAR2(50) not null,"
	         		+ " dateOfBirth DATE,"
	         		+ " key VARCHAR(50) not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE Marker ("
	         		+ " idMarker NUMBER(10) "
	         			+ " CONSTRAINT marker_pk PRIMARY KEY"
	         			+ " CONSTRAINT idmarker_nn not null,"
	         		+ " idChild NUMBER(10) not null,"
	         		+ " x BINARY_FLOAT not null,"
	         		+ " y BINARY_FLOAT not null,"
	         		+ " datetime DATE not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE ParentSettings ("
	         		+ " idParentSettings NUMBER(10)" 
	         			+ " CONSTRAINT parentsettings_pk PRIMARY KEY"
	         			+ " CONSTRAINT idparentsettings_nn not null,"
	         		+ " idParent NUMBER(10) not null,"
	         		+ " idChild NUMBER(10) not null,"
	         		+ " stepTime BINARY_FLOAT not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE AreaPoint ("
	         		+ " idAreaPoint NUMBER(10)" 
	         			+ " CONSTRAINT areapoint_pk PRIMARY KEY"
	         			+ " CONSTRAINT idareapoint_nn not null,"
	         		+ " idArea NUMBER(10) not null,"
	         		+ " x BINARY_FLOAT not null,"
	         		+ " y BINARY_FLOAT not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE '"
	         	+ " CREATE TABLE Area ("
	         		+ " idArea NUMBER(10)" 
	         			+ " CONSTRAINT area_pk PRIMARY KEY"
	         			+ " CONSTRAINT idarea_nn not null,"
	         		+ " idAreaPoint NUMBER(10) not null,"
	         		+ " idChild NUMBER(10) not null"
	         	+ ")';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Family ADD"
	         	+ " (CONSTRAINT family_idparent_fk FOREIGN KEY (idParent) REFERENCES Parent)';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Family ADD"
	         	+ " (CONSTRAINT family_idchild_fk FOREIGN KEY (idChild) REFERENCES Children)';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Marker ADD"
	         	+ " (CONSTRAINT marker_idchild_fk FOREIGN KEY (idChild) REFERENCES Children)';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Children ADD"
	         	+ " (CONSTRAINT children_idchild_fk FOREIGN KEY (idChild) REFERENCES Area)';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Parent ADD"
	         	+ " (CONSTRAINT parent_idparent_fk FOREIGN KEY (idParent) REFERENCES ParentSettings)';"
	         + " EXECUTE IMMEDIATE 'ALTER TABLE Area ADD"
	         	+ " (CONSTRAINT area_idarea_fk FOREIGN KEY (idArea) REFERENCES AreaPoint)';"
	         + " END IF;"
	         + " CLOSE c_1;"
	         + " END;";
	     
			st.execute(crTab);
	        	System.out.println("Created tables");
	}
}
