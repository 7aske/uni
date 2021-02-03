package com.example.cms.servlet.admin.post.edit;

import com.example.cms.config.Config;
import com.example.cms.database.dao.PostDAO;
import com.example.cms.database.dao.TagDAO;
import com.example.cms.database.dao.UserDAO;
import com.example.cms.database.entity.Post;
import com.example.cms.database.entity.Tag;
import com.example.cms.database.entity.User;
import com.example.cms.util.TagUtil;
import com.example.cms.util.UrlUtil;
import com.google.common.collect.Iterables;
import com.mysql.cj.protocol.x.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/post/edit/*")
public class EditPostServlet extends HttpServlet {
	// TODO: all these idPost should be changed to blog post
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPost = UrlUtil.getUrlBase(request.getRequestURL().toString());
		request.setAttribute("idPost", idPost);
		request.getRequestDispatcher("/admin/post/edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String locale = (String) request.getSession().getAttribute("lang");

		String idPostString = request.getParameter("id_post");
		String idUserString = request.getParameter("id_user");
		long idPost;
		long idUser;
		boolean published = false;

		String title = request.getParameter("title");
		String slug = request.getParameter("slug");
		String excerpt = request.getParameter("excerpt");
		String body = request.getParameter("body");


		List<String> errros = new ArrayList<>();

		if (idUserString == null || idUserString.equals("")) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.user"));
		}

		if (title == null || title.equals("")) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.title"));
		}

		if (slug == null || slug.equals("")) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.slug"));
		}

		if (excerpt == null || excerpt.equals("")) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.excerpt"));
		}

		if (body == null || body.equals("")) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.body"));
		}


		if (request.getParameter("published") != null && request.getParameter("published").equals("on")) {
			published = true;
		}

		Set<Tag> tagList = TagUtil.parseTags(request);

		if (errros.size() != 0) {
			request.setAttribute("idPost", idPostString);
			request.setAttribute("errors", Iterables.toArray(errros, String.class));
			request.getRequestDispatcher("/admin/post/edit.jsp").forward(request, response);
			return;
		}

		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			idUser = Long.parseLong(idUserString);
			user = userDAO.find(idUser);
		} catch (NumberFormatException ignored) {
			errros.add(Config.getBundle(locale).getString("errors.post.edit.user"));
		}

		PostDAO postDAO = new PostDAO();
		Post post;
		if (idPostString == null || idPostString.equals("")) {
			post = new Post();
			post.setTitle(title);
			post.setSlug(UrlUtil.encodeValue(slug));
			post.setBody(body);
			post.setExcerpt(excerpt);
			post.setPublished(published);
			post.setDatePosted(LocalDate.now().plusDays(1));
			post.setTags(tagList);
			post.setIdUser(user);
			postDAO.create(post);
			response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
		} else {
			try {
				idPost = Long.parseLong(idPostString);
				post = postDAO.find(idPost);
				post.setTitle(title);
				post.setSlug(UrlUtil.encodeValue(slug));
				post.setBody(body);
				post.setExcerpt(excerpt);
				post.setDatePosted(post.getDatePosted().plusDays(1));
				post.setPublished(published);
				post.setTags(tagList);
				postDAO.update(post);
				response.sendRedirect(request.getContextPath() + "/admin/post/edit/" + idPostString);
			} catch (NumberFormatException ignored) {
				errros.add(Config.getBundle(locale).getString("errors.post.edit.post"));
				request.setAttribute("idPost", idPostString);
				request.setAttribute("errors", Iterables.toArray(errros, String.class));
				request.getRequestDispatcher("/admin/post/edit.jsp").forward(request, response);
			}
		}
	}
}
