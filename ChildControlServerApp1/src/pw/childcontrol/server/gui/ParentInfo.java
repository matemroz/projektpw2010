package pw.childcontrol.server.gui;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import pw.childcontrol.server.dao.domain.AreaPoint;
import pw.childcontrol.server.dao.jdbc.JDBCAreaManagerDAO;
import pw.childcontrol.server.dao.jdbc.JDBCChildDAO;
import pw.childcontrol.server.dao.jdbc.JDBCParentDAO;
import pw.childcontrol.server.dao.jdbc.db.ConnectionManager;

public class ParentInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = response.getWriter();

		try {
			if (id != null) {
				JDBCParentDAO parent = new JDBCParentDAO();
				int idParent = Integer.valueOf(id);

				if (true){//!parent.getPassword(idParent).equals("")) {
					
					sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					sb.append("<parentInfo>");
					sb.append("<isParent>true</isParent>");
					sb.append("<parentEmail>" + parent.getEmail(idParent)
							+ "</parentEmail>");
					sb.append("<parentName>" + parent.getName(idParent)
							+ "</parentName>");
					sb.append("<parentPassword>"
							+ parent.getPassword(idParent)
							+ "</parentPassword>");
					// List<Integer> parentChildList =
					// parent.getChildsList(idParent);
					List<Integer> parentChildList = new ArrayList<Integer>();
					parentChildList.add(45);
					parentChildList.add(46);

					if (!parentChildList.isEmpty()) {
						sb.append("<nochild>false</nochild>");
						int childNumber = 1;
						for (Integer IDchild : parentChildList) {
							sb.append("<child number=\""
									+ String.valueOf(childNumber) + "\">"
									+ IDchild + "</child>");
							childNumber++;
						}
					} else {
						sb.append("<nochild>true</nochild>");
					}
					sb.append("</parentInfo>");
				}
			} else {
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				sb.append("<isParent>false</isParent>");
			}
			
			out.println(sb.toString());
		} catch (NumberFormatException e) {
			sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<error>NumberFormatException</error>");
			out.println(sb.toString());
			e.printStackTrace();
		}
	}
}

