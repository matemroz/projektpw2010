package pw.childcontrol.server.database.jdbc.dao;

import java.util.List;

import pw.childcontrol.server.database.hb.entity.Area;
import pw.childcontrol.server.database.hb.entity.AreaPoint;
import pw.childcontrol.server.familymanager.IAreaManager;

public class AreaManagerDAO implements IAreaManager{

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
