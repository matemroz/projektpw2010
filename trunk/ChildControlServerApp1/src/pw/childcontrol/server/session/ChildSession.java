package pw.childcontrol.server.session;

import pw.childcontrol.server.dao.domain.Child;
import pw.childcontrol.server.dao.jdbc.JDBCChildDAO;

public class ChildSession {
	private static Child child;
	
	public ChildSession(int idChild){
		child = new Child(idChild);
		child.setIdChild(idChild);
		child.setChildName(new JDBCChildDAO().getName(idChild));
		child.setDateOfBirth(new JDBCChildDAO().getDateOfBirth(idChild));
		child.setImei(new JDBCChildDAO().getImei(idChild));
		child.setIdArea(new JDBCChildDAO().getLocation(idChild));
	}
	
	public static Child getChild(){
		if(child== null)
			return null;
		return child;
	}
}
