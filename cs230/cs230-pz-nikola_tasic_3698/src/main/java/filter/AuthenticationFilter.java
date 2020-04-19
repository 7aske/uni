package filter;

import config.Config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
			new HashSet<>(
					Arrays.asList("", "/admin/login", "/admin/logout", "/admin/login.jsp")
			));
	Properties props;

	@Override
	public void init(FilterConfig filterConfig) {
		props = Config.getProperties();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
		boolean allowedPath = ALLOWED_PATHS.contains(path);
		boolean loggedIn = (session != null && session.getAttribute("username") != null && session.getAttribute("username").equals(props.getProperty("blog-username")));

		if (allowedPath || loggedIn) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		}
	}

	@Override
	public void destroy() {

	}
}
