package pw.childcontrol.server.session;

import pw.childcontrol.server.dao.domain.Parent;
import pw.childcontrol.server.dao.jdbc.JDBCParentDAO;

public class ParentSession {
	
	private static Parent parent;
	
	public ParentSession(int idParent){
		parent = new Parent(idParent);
		parent.setId(idParent);
		parent.setEmail(new JDBCParentDAO().getEmail(idParent));
		parent.setName(new JDBCParentDAO().getName(idParent));
	}
	
	public static Parent getParent(){
		if(parent == null)
			return null;
		return parent;
	}
}
