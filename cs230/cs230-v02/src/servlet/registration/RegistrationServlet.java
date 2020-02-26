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

	/**
	 * Render simple registration 'page'
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<title>Registration Form</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div>");
		out.print("<h3>Register</h3>");
		// Print out the error message there is one
		out.print(request.getParameter("error") != null ? request.getParameter("error") : "");
		out.print("<form action=\"/RegistrationServletDemo/register\" method=\"post\">");
		out.print("<label>");
		out.print("Username");
		out.print("<input name=\"username\" type=\"text\">");
		out.print("</label>");
		out.print("<label>");
		out.print("Password");
		out.print("<input name=\"password\" type=\"password\">");
		out.print("</label>");
		out.print("<input type=\"submit\">");
		out.print("</form>");
		out.print("<a href=\"/RegistrationServletDemo/login\">Login</a>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
	}

	/**
	 * Process registration form post data
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// if the field is invalid send the error back to the user using HTTP redirect
		if (username == null || username.equals("") || username.contains(" ")) {
			response.sendRedirect("/RegistrationServletDemo/register?error=Invalid%20username");
			return;
		}
		if (password == null || password.equals("")) {
			response.sendRedirect("/RegistrationServletDemo/register?error=Invalid%20password");
			return;
		}

		if (Authentication.getAuthentication().userExists(username)) {
			response.sendRedirect(String.format("/RegistrationServletDemo/register?error=Username%%20'%s'%%20already%%20taken", username));
			return;
		}

		// if the registration data is valid, save user to the file and set it to session
		HttpSession session = request.getSession(true);
		User user = new User(username, password);
		session.setAttribute("user", user);
		Authentication.getAuthentication().saveUser(user);

		response.setContentType("text/html;charset=utf8");
		out.print(String.format("Successfully registered '%s'<br><br><a href=\"/RegistrationServletDemo/dashboard\">Dashboard</a>", ((User) session.getAttribute("user")).getUsername()));
	}
}