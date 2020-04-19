package servlet.admin.post.delete;

import database.dao.BlogPostDAO;
import util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/post/delete/*")
public class DeletePostServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idBlogPostString = UrlUtil.getUrlBase(request.getRequestURL().toString());
		long idBlogPost;
		BlogPostDAO blogPostDAO = new BlogPostDAO();
		System.out.println(idBlogPostString);
		try {
			idBlogPost = Long.parseLong(idBlogPostString);
			blogPostDAO.removeById(idBlogPost);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
	}
}
