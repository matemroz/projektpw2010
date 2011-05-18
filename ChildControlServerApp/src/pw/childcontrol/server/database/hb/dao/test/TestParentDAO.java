package pw.childcontrol.server.database.hb.dao.test;

import pw.childcontrol.server.database.hb.entity.Parent;
import pw.childcontrol.server.database.hb.dao.*;

public class TestParentDAO {
	public static void main(String[] args) {
		Parent parent = new Parent();
		parent.setName("Gniewosz");
		parent.setEmail("gniewasz@dupa.com");
     	ParentDAO parentDAO = new ParentDAO();
     	System.out.println(parent.toString());
     	parentDAO.store(parent);
	}
}
