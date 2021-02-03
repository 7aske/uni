package com.example.cms.servlet.admin.user.edit;

import com.example.cms.config.Config;
import com.example.cms.database.RoleNames;
import com.example.cms.database.dao.RoleDAO;
import com.example.cms.database.dao.UserDAO;
import com.example.cms.database.entity.Role;
import com.example.cms.database.entity.User;
import com.example.cms.security.Security;
import com.example.cms.util.UrlUtil;
import com.google.common.collect.Iterables;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/user/edit/*")
public class EditUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUserString = UrlUtil.getUrlBase(request.getRequestURL().toString());
		long idUser;
		try {
			idUser = Long.parseLong(idUserString);
		} catch (NumberFormatException ignored) {
			response.sendRedirect(request.getContextPath() + "/admin/user/edit.jsp");
			return;
		}
		User user = new UserDAO().find(idUser);
		if (user != null) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/admin/user/edit.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user/edit.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String locale = (String) request.getSession().getAttribute("lang");

		String idUser = request.getParameter("idUser");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String displayName = request.getParameter("display_name");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");

		List<String> errors = new ArrayList<>();

		UserDAO userDAO = new UserDAO();
		RoleDAO roleDAO = new RoleDAO();

		Set<Role> roles = new HashSet<>();
		roles.add(roleDAO.findByName(RoleNames.AUTHOR));

		User user = userDAO.findByUsername(username);

		if (username == null || username.equals("")) {
			errors.add(Config.getBundle(locale).getString("errors.user.edit.username"));
		}

		if (email == null || email.equals("")) {
			errors.add(Config.getBundle(locale).getString("errors.user.edit.email"));
		}

		if (password == null || password.equals("")) {
			errors.add(Config.getBundle(locale).getString("errors.user.edit.password"));
		}

		if (displayName == null || displayName.equals("")) {
			errors.add(Config.getBundle(locale).getString("errors.user.edit.display_name"));
		}

		if (errors.size() != 0) {
			request.setAttribute("errors", Iterables.toArray(errors, String.class));
			if (request.getParameter("setup") != null && request.getParameter("setup").equals("on")) {
				request.getRequestDispatcher("/setup.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/user/edit.jsp").forward(request, response);
			}
			return;
		}

		assert password != null;

		//FIXME: bad bad
		if (request.getParameter("roles") != null) {
			roles.add(roleDAO.findByName(request.getParameter("roles")));
		}

		if (!password.equals(password_confirm)) {
			errors.add(Config.getBundle(locale).getString("errors.user.edit.password_not_match"));
		}

		if (user == null && !idUser.equals("")) { // update
			errors.add(Config.getBundle(locale).getString("errors.user.edit.user_not_exists"));
		} else if (user != null && idUser.equals("")) {// create
			errors.add(Config.getBundle(locale).getString("errors.user.edit.user_exists"));
		} else if (user == null)  {
			user = new User();
		}

		if (errors.size() == 0) {
			assert user != null;
			user.setEmail(email);
			user.setUsername(username);
			user.setDisplayName(displayName);
			user.setPassword(Security.hash(password));
			user.setRoles(roles);
			user.setActive(true);
			user = userDAO.update(user);
		} else {
			request.setAttribute("errors", Iterables.toArray(errors, String.class));
		}
		request.setAttribute("user", user);

		if (request.getParameter("setup") != null && request.getParameter("setup").equals("on")){
			request.getRequestDispatcher("/setup.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/admin/user/edit.jsp").forward(request, response);
		}
	}
}
