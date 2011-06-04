package pw.childcontrol.server.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pw.childcontrol.server.dao.jdbc.JDBCChildDAO;
import pw.childcontrol.server.dao.jdbc.JDBCParentDAO;

public class ChildInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String limit = request.getParameter("limit");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = response.getWriter();

		try {
			if (id != null) {
				JDBCChildDAO child = new JDBCChildDAO();
				int idChild = Integer.valueOf(id);
				int historyLimit = Integer.valueOf(limit);

				if (child.getImei(idChild) != null){//!parent.getPassword(idParent).equals("")) {
					
					sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					sb.append("<childInfo>");
					sb.append("<childIMEI>" + child.getImei(idChild)
							+ "</childIMEI>");
					sb.append("<childName>"
							+ child.getName(idChild)
							+ "</childName>");

					sb.append("<childLocation>" + child.getLocation(idChild)
							+ "</childLocation>");
					sb.append("<childStepTime>" + child.getStepTime(idChild)
							+ "</childStepTime>");
					sb.append("<childLocationHistory>" + child.getLocationHistory(idChild, historyLimit)
							+ "</childLocationHistory>");
					

					sb.append("</childInfo>");
				}
			} else {
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				sb.append("<isChild>false</isChild>");
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

