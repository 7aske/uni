package com.example.cms.servlet.admin.tag.delete;

import com.example.cms.database.dao.TagDAO;
import com.example.cms.util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tag/delete/*")
public class DeleteTagServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String idTagString = UrlUtil.getUrlBase(request.getRequestURL().toString());
		long idTag;
		TagDAO tagDAO = new TagDAO();
		System.out.println(idTagString);
		try {
			idTag = Long.parseLong(idTagString);
			tagDAO.removeById(idTag);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			String[] errors = new String[]{
					ex.getMessage()
			};
			request.setAttribute("errors", errors);
		}
		// response.sendRedirect(request.getContextPath() + "/admin/tag/tags.jsp");
		request.getRequestDispatcher("/admin/tag/tags.jsp").forward(request, response);
		request.setAttribute("errors", null);
	}
}
