package servlet.users;

import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			out.println("UNAUTHORIZED");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		response.setContentType("text/html;charset=utf8");
		out.println(String.format("Welcome <span style=\"font-weight:bold;\">%s</span>", user.getUsername()));
	}
}
