package pw.childcontrol.server.dao.jdbc;

import java.util.List;

import pw.childcontrol.server.dao.AreaManagerDAO;
import pw.childcontrol.server.dao.domain.Area;
import pw.childcontrol.server.dao.domain.AreaPoint;

public class JDBCAreaManagerDAO implements AreaManagerDAO{

	@Override
	public List<Area> getDangerousAreas(int idChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addDangerousArea(int idChild, Area area) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeDangerousArea(int idChild, Area area) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean idDangerousArea(int idChild, AreaPoint point) {
		// TODO Auto-generated method stub
		return false;
	}

}
