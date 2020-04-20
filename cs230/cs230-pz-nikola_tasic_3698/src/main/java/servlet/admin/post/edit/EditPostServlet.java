package servlet.admin.post.edit;

import config.Config;
import database.dao.BlogPostDAO;
import database.dao.TagDAO;
import database.entity.BlogPost;
import database.entity.Tag;
import util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/post/edit/*")
public class EditPostServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idBlogPost = UrlUtil.getUrlBase(request.getRequestURL().toString());
		request.setAttribute("idBlogPost", idBlogPost);
		request.getRequestDispatcher("/admin/post/edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idBlogPostString = request.getParameter("idBlogPost");
		long idBlogPost;
		String title = request.getParameter("title");
		String slug = request.getParameter("slug");
		String preview = request.getParameter("preview");
		String body = request.getParameter("body");
		boolean published = false;
		if (request.getParameter("published") != null && request.getParameter("published").equals("on")) {
			published = true;
		}
		TagDAO tagDAO = new TagDAO();
		Set<Tag> tagList = new HashSet<>();
		String[] tags = request.getParameter("tags").split(",");
		for (String tagName : tags) {
			Tag tag = tagDAO.findByName(tagName);
			if (tag != null) {
				tagList.add(tag);
			}
		}

		BlogPostDAO blogPostDAO = new BlogPostDAO();
		try {
			idBlogPost = Long.parseLong(idBlogPostString);
			BlogPost blogPost = blogPostDAO.find(idBlogPost);
			blogPost.setTitle(title);
			blogPost.setSlug(slug);
			blogPost.setBody(body);
			blogPost.setPreview(preview);
			blogPost.setPublished(published);
			blogPost.setTags(tagList);
			blogPostDAO.update(blogPost);
		} catch (NumberFormatException ex) {
			BlogPost blogPost = new BlogPost();
			blogPost.setTitle(title);
			blogPost.setSlug(slug);
			blogPost.setBody(body);
			blogPost.setPreview(preview);
			blogPost.setPublished(published);
			blogPost.setDatePosted(LocalDate.now());
			blogPost.setTags(tagList);
			blogPost.setAuthor((String) Config.getProperties().get("author"));
			blogPostDAO.create(blogPost);
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
	}
}
