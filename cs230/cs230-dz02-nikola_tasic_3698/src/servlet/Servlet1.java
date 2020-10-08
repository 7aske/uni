package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet1 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		String username = req.getParameter("username");

		if (username == null) {
			resp.setStatus(400);
			out.write("400 Bad Request");
			return;
		}
		req.getSession(true).setAttribute("username", username);
		resp.setContentType("text/html;charset=utf8;");
		out.write(String.format("Dobrodosli <span>%s</span><br>", username));
		out.write("<a href=\"/cs230_dz02_nikola_tasic_3698_war_exploded/step2\">Dalje</a><br>");
	}
}
