package com.example.cms.servlet.admin;

import com.example.cms.config.Config;
import com.example.cms.database.dao.UserDAO;
import com.example.cms.database.entity.User;
import com.example.cms.security.Security;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		List<String> errors = new ArrayList<>();

		User user = new UserDAO().findByUsername(username);

		if (user == null) {
			errors.add("User doesn't exists");
		} else if (!user.getPassword().equals(Security.hash(password))) {
			errors.add("Invalid password");
		}

		if (errors.size() != 0) {
			request.setAttribute("errors", Iterables.toArray(errors, String.class));
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		} else {
			assert user != null;
			Properties props = Config.getProperties();
			int validity = Integer.parseInt(props.getProperty("session-validity"));
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("idUser", user.getIdUser());
			session.setAttribute("roles", user.getRoles());
			session.setMaxInactiveInterval(validity);
			response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
		}

	}
}
