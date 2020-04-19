package servlet.admin.tag.delete;

import database.dao.BlogPostDAO;
import database.dao.TagDAO;
import util.UrlUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tag/delete/*")
public class DeleteTagServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idTagString = UrlUtil.getUrlBase(request.getRequestURL().toString());
		long idTag;
		TagDAO tagDAO = new TagDAO();
		System.out.println(idTagString);
		try {
			idTag = Long.parseLong(idTagString);
			tagDAO.removeById(idTag);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/tag/tags.jsp");
	}
}
