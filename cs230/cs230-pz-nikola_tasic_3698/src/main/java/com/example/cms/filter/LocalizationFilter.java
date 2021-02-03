package com.example.cms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LocalizationFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getParameter("lang") != null) {
			req.getSession().setAttribute("lang", req.getParameter("lang"));
		} else {
			if (req.getSession().getAttribute("lang") == null) {
				req.getSession().setAttribute("lang", "en");
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}