package servlet.login;

import auth.Authentication;
import entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

	/**
	 * Rendering a simple login 'page'
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<title>Login Form</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div>");
		out.print("<h3>Login</h3>");
		// Print out the error message there is one
		out.print(request.getParameter("error") != null ? request.getParameter("error") : "");
		out.print("<form action=\"/RegistrationServletDemo/login\" method=\"post\">");
		out.print("<label>");
		out.print("Username");
		out.print("<input name=\"username\" type=\"text\">");
		out.print("</label>");
		out.print("<label>");
		out.print("Password");
		out.print("<input name=\"password\" type=\"password\">");
		out.print("</label>");
		out.print("<input  type=\"submit\">");
		out.print("</form>");
		out.println("<a href=\"/RegistrationServletDemo/register\">Register</a>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * Process login form post data
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Attempt to verify user credentials
		User user = Authentication.getAuthentication().getLoggedInUser(username, password);
		if (user != null) {
			// Set user object to session and redirect to dashboard
			request.getSession(true).setAttribute("user", user);
			response.sendRedirect("/RegistrationServletDemo/dashboard");
		} else {
			// Redirect user back to login with error message
			response.sendRedirect("/RegistrationServletDemo/login?error=Invalid%20username%20or%20password");
		}
	}
}