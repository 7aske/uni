package com.example.cms.servlet.admin.post.delete;

import com.example.cms.database.dao.PostDAO;
import com.example.cms.util.UrlUtil;

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
		String idPostString = UrlUtil.getUrlBase(request.getRequestURL().toString());
		long idPost;
		PostDAO blogPostDAO = new PostDAO();
		System.out.println(idPostString);
		try {
			idPost = Long.parseLong(idPostString);
			blogPostDAO.removeById(idPost);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/admin.jsp");
	}
}
