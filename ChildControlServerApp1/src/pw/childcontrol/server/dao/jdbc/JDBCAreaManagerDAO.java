package pw.childcontrol.server.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import pw.childcontrol.server.dao.AreaManagerDAO;
import pw.childcontrol.server.dao.domain.Area;
import pw.childcontrol.server.dao.domain.AreaPoint;
import pw.childcontrol.server.dao.jdbc.db.ConnectionManager;
import pw.childcontrol.utils.db.DatabaseUtils;

public class JDBCAreaManagerDAO implements AreaManagerDAO{

	@Override
	public List<Area> getDangerousAreas(int idChild) {
		List<Area> areaList = new LinkedList<Area>();
		double x = 0.0;
		double y = 0.0;
		double r = 0.0;
		int idArea = 0;
		
		List<String> columnNames = new LinkedList<String>();
		List<String> tableNames = new LinkedList<String>();
		List<String> columnRelations = new LinkedList<String>();
		String condition = "IDCHILD = '" + idChild + "'";
		columnNames.add("X");
		columnNames.add("Y");
		columnNames.add("R");
		columnNames.add("AREAPOINT.IDAREA");
		tableNames.add("AREA");
		tableNames.add("AREAPOINT");
		columnRelations.add("IDAREA");
		
		ResultSet rs = DatabaseUtils.queryCommand(columnNames, tableNames, columnRelations, condition);
		
		try {
			if (rs == null) {
				return null;
			}
			while(rs.next()){
				x = rs.getDouble("X");
				y = rs.getDouble("Y");
				r = rs.getDouble("R");
				idArea = rs.getInt("IDAREA");
				areaList.add(new Area(new AreaPoint(x,y),r));
				
				/*
				System.out.print("X = " + rs.getDouble("X"));
				System.out.print(" Y = " + rs.getDouble("Y"));
				System.out.print(" R = " + rs.getDouble("R"));
				System.out.print(" IDAREA = " + rs.getInt("IDAREA"));
				System.out.println("");
				*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaList;
	}

	@Override
	public int addDangerousArea(int idChild, Area area) {
		int idArea = 0;
		/*Pobranie wspolrzednych z klasy Area*/
		AreaPoint ap = area.getAreaPoint();
		Double r = area.getRadius();
		Double x = ap.getX();
		Double y = ap.getY();
		
		/*Przygotowanie kwerendy dodajacej niezbezpieczny obszar do tabeli AREA*/
		String tableName = "AREA";
		String columnNames = "R,IDCHILD";
		String values = Double.toString(r) + "," + idChild;
		String insertQuery = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + values + ")";
		System.out.println(insertQuery);
		
		try {
			/* wykonanie zapytania zwracającego id dodanego wiersza */
			String generatedId[] = {"IDAREA"};
			PreparedStatement pstmt = ConnectionManager.getDatabaseConnection().prepareStatement(insertQuery, generatedId);
			
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				/* numer dodanego id */
				idArea = rs.getInt(1);
				System.out.println(idArea);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*Przygotwanie kwerendy dodajacej wspolrzedne srodka obszaru do tabeli AREAPOINT */
		tableName = "AREAPOINT";
		List<String> columnList = new LinkedList<String>();
		List<String> valueList = new LinkedList<String>();
		columnList.add("IDAREA");
		columnList.add("X");
		columnList.add("Y");
		valueList.add(String.valueOf(idArea));
		valueList.add(Double.toString(x));
		valueList.add(Double.toString(y));
		
		DatabaseUtils.insertCommand(tableName, columnList, valueList);
		
		return idArea;
	}

	@Override
	public boolean removeDangerousArea(int idChild, Area area) {
		int idArea = 0;
		boolean result = false;
		
		/*Pobranie wspolrzednych z klasy Area*/
		AreaPoint ap = area.getAreaPoint();
		double r = area.getRadius();
		String x = Double.toString(ap.getX());
		x = x.replace('.', ',');
		String y = Double.toString(ap.getY());
		y = y.replace(".", ",");
		
		
		
		/*Przygotowanie kwerendy wyszukujacej id podanego obszaru*/
		List<String> columnNames = new LinkedList<String>();
		List<String> tableNames = new LinkedList<String>();
		List<String> columnRelations = new LinkedList<String>();
		String condition = "IDCHILD = '" + idChild + "' AND X = '" + x + "' AND Y = '" + y + "'";
		columnNames.add("AREA.IDAREA");
		tableNames.add("AREA");
		tableNames.add("AREAPOINT");
		columnRelations.add("IDAREA");
		
		ResultSet rs = DatabaseUtils.queryCommand(columnNames, tableNames, columnRelations, condition);
		
		try {
			if (rs == null) {
				return false;
			}
			while(rs.next()) {
				idArea = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*Przygotowanie kwerendy usuwajacej centralny punkt obszaru z tabeli AREAPOINT*/
		condition = "IDAREA = " + idArea + " AND X = '" + x + "' AND Y = '" + y + "'";
		String tableName = "AREAPOINT";
		
		result = DatabaseUtils.deleteCommand(tableName, condition);
		
		/*Przygotowanie kwerendy usuwajacej obszar z tabeli AREA*/
		condition = "IDAREA = " + idArea + "";
		tableName = "AREA";
		
		result = DatabaseUtils.deleteCommand(tableName, condition);
		
		return result;
	}

	@Override
	public boolean idDangerousArea(int idChild, AreaPoint point) {
		boolean result = false;
		List<Area> areaList = new LinkedList<Area>();
		double r = 0;
		double x = point.getX();
		double y = point.getY();
		
		areaList = getDangerousAreas(idChild);
		
		/*Sprawdzanie czy punkt nalezy do ktoregos z obszarow*/
		Iterator<Area> iter = areaList.iterator();
		Area area;
		AreaPoint ap;
		
		while(iter.hasNext()) {
			area = iter.next();
			r = area.getRadius();
			ap = area.getAreaPoint();
			
			/*Obliczanie odleglosci punktu od srodka obszaru*/
			double distance = Math.sqrt( Math.pow((ap.getX() - x),2) + Math.pow((ap.getY() - y),2) );
			System.out.println("Distance :" + distance);
			
			/*Porownanie obliczonej odleglosci z promieniem obszaru*/
			if (r > distance) {
				return true;
			}
		}
		
		return result;
	}
	
	/*
	public static void main(String [ ] args) {
		JDBCAreaManagerDAO instance = new JDBCAreaManagerDAO();
		instance.addDangerousArea(1, new Area(new AreaPoint(1.11,2.21),2.31));
		List<Area> list = instance.getDangerousAreas(1);
		System.out.println(list.toString());
		instance.removeDangerousArea(1, new Area(new AreaPoint(1.11,2.21),2.31));
		System.out.println(instance.idDangerousArea(1, new AreaPoint(1.3,1.4)));
		System.out.println(instance.idDangerousArea(1, new AreaPoint(9.3,-1.4)));
		System.out.println(instance.idDangerousArea(1, new AreaPoint(-9.3,-0.4)));
	}
	*/
}
