package servlet.post;

import util.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/*")
public class PostServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String slug = UrlUtil.getUrlBase(request.getRequestURL().toString());
		request.setAttribute("postSlug", slug);
		request.getRequestDispatcher("/post.jsp").forward(request, response);
	}
}
