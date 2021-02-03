package com.example.cms.servlet.admin.tag;

import com.example.cms.database.dao.TagDAO;
import com.example.cms.database.entity.Tag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tag")
public class TagServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagName = request.getParameter("name");
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		tag.setName(tagName);
		tagDAO.create(tag);
		response.sendRedirect(request.getContextPath() + "/admin/tag/tags.jsp");
	}
}
