package filter;

import config.Config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebFilter("/admin/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if (request.getServletPath().equals("/admin/login.jsp") || request.getServletPath().equals("/admin/login")) {
			chain.doFilter(request, response);
			return;
		}

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		} else {
			String username = (String) session.getAttribute("username");
			Properties props = Config.getProperties();
			String confUsername = props.getProperty("blog-username");
			if (!confUsername.equals(username)) {
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
			} else {
				System.out.println(session.getAttribute("username"));
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {

	}
}
