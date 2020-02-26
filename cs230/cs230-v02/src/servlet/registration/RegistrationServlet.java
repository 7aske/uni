package servlet.registration;

import auth.Authentication;
import entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 3352669373264309920L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>Registration Form</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div>");
		out.println("<form action=\"/RegistrationServletDemo/register\" method=\"post\">");
		out.println("<label>");
		out.println("Username");
		out.println("<input name=\"username\" type=\"text\">");
		out.println("</label>");
		out.println("<label>");
		out.println("Password");
		out.println("<input name=\"password\" type=\"password\">");
		out.println("</label>");
		out.println("<input type=\"submit\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == null || username.equals("")) {
			out.println("Invalid field 'username'");
			return;
		}
		if (password == null || password.equals("")) {
			out.println("Invalid field 'password'");
			return;
		}

		HttpSession session = request.getSession(true);
		User user = new User(username, password);
		session.setAttribute("user", user);
		Authentication.getAuthentication().saveUser(user);

		response.setContentType("text/html;charset=utf8");
		out.println(String.format("Successfully registered '%s'<br><br><a href=\"/RegistrationServletDemo/dashboard\">Dashboard</a>", ((User) session.getAttribute("user")).getUsername()));
	}
}