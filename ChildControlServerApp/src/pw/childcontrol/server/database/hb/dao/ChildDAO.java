package pw.childcontrol.server.database.hb.dao;

import pw.childcontrol.server.database.hb.entity.Children;

public class ChildDAO extends GenericDAO {
	public Children findChildById(int idChild) {
		return (Children)findUnique("... where idChild=" + idChild);
	}
}
