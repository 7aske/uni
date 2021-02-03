package com.example.cms.servlet.admin;

import com.example.cms.database.RoleNames;
import com.example.cms.database.dao.RoleDAO;
import com.example.cms.database.dao.UserDAO;
import com.example.cms.database.entity.Role;
import com.example.cms.database.entity.User;
import com.example.cms.security.Security;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/create")
public class AdminCreateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<String> errors = new ArrayList<>();
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String displayName = req.getParameter("display_name");
		String password = req.getParameter("password");
		String password_confirm = req.getParameter("password_confirm");

		Set<Role> roles = new HashSet<>();

		UserDAO userDAO = new UserDAO();
		RoleDAO roleDAO = new RoleDAO();

		if (!password.equals(password_confirm)) {
			errors.add("Passwords do not match");
		}

		if (userDAO.findByUsername(username) != null) {
			errors.add("User already exists");
		}

		if (errors.size() == 0) {
			User user = new User();

			roles.add(roleDAO.findByName(RoleNames.ADMIN));
			roles.add(roleDAO.findByName(RoleNames.AUTHOR));
			user.setEmail(email);
			user.setUsername(username);
			user.setDisplayName(displayName);
			user.setPassword(Security.hash(password));
			user.setRoles(roles);
			user.setActive(true);
			userDAO.create(user);

			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/admin/setup.jsp");
		}
	}
}
