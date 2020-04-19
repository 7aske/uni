package servlet.admin;

import config.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Properties props = Config.getProperties();
		String confUsername = props.getProperty("blog-username");
		String confPassword = props.getProperty("blog-password");
		int validity = Integer.parseInt(props.getProperty("session-validity"));

		if (confUsername.equals(username) && confPassword.equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(validity);
			response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		}

	}
}
