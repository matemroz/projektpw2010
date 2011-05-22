package pw.childcontrol.server.dao.jdbc;

import pw.childcontrol.server.dao.ParentDAO;

public class JDBCParentDAO implements ParentDAO{

	@Override
	public int[] getChildsList(int idParent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean relocateChilds(int idParent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addChild(int idParent) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeChild(int idParent, int idChild) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setEmail(int idParent, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getEmail(int idParent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(int idParent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setName(int idParent, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPassword(int idParent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPassword(int idParent, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
