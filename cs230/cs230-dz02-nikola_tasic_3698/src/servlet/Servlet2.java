package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		String username = (String) req.getSession(true).getAttribute("username");

		if (username == null) {
			resp.setStatus(400);
			out.write("400 Bad Request");
			return;
		}

		resp.setContentType("text/html;charset=utf8;");
		out.write(String.format("Bravo <span>%s</span>! Uspesno ste savladali osnove servleta!", username));
	}
}
