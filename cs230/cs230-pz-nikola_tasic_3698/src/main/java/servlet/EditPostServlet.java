package servlet;

import util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit/*")
public class EditPostServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idBlogPost = UrlUtil.getUrlBase(request.getRequestURL().toString());
		request.setAttribute("idBlogPost", idBlogPost);
		request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
	}
}
