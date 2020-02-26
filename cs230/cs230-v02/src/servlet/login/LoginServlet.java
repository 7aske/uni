package servlet.login;

import auth.Authentication;
import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<title>Login Form</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div>");
		out.println("<form action=\"/RegistrationServletDemo/login\" method=\"post\">");
		out.println("<label>");
		out.println("Username");
		out.println("<input name=\"username\" type=\"text\">");
		out.println("</label>");
		out.println("<label>");
		out.println("Password");
		out.println("<input name=\"password\" type=\"password\">");
		out.println("</label>");
		out.println("<input  type=\"submit\">");
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

		User user = Authentication.getAuthentication().getLoggedInUser(username, password);
		if (user != null) {
			request.getSession(true).setAttribute("user", user);
			response.sendRedirect("/RegistrationServletDemo/dashboard");
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
			out.println("Invalid username or password");
		}
	}
}