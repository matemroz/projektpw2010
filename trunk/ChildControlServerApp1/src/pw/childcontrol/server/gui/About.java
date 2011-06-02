package pw.childcontrol.server.gui;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import pw.childcontrol.server.dao.domain.AreaPoint;
import pw.childcontrol.server.dao.jdbc.JDBCChildDAO;
import pw.childcontrol.server.dao.jdbc.db.ConnectionManager;

public class About extends HttpServlet {
	
	
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	  String message = request.getParameter("message");
	  
	  //Connection con = ConnectionManager.getDatabaseConnection();
	JDBCChildDAO child = new JDBCChildDAO();
	String imei = child.getImei(0);
	List<AreaPoint> list = child.getLocationHistory(0, 10);
	
	PrintWriter out = response.getWriter();
	out.println("Hello World " + String.valueOf(imei.length()));
	
	
	out.println("<areaPointList>");
	if(list != null){
		for (AreaPoint areaPoint : list) {
			out.println("<areaPoint>" + areaPoint + "</areaPoint>");
		}
	}
	out.println("</areaPointList>");
	
	out.println("<message>" + message + "</message>");
	
  }
}
