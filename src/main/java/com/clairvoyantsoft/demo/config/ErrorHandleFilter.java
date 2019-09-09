package com.clairvoyantsoft.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ErrorHandleFilter implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, 
               ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		try {
			chain.doFilter(request, response);
		} catch (Exception ex) {
			request.setAttribute("errorMessage", ex);
			request.getRequestDispatcher("/WEB-INF/JSP/Error.jsp")
                               .forward(request, response);
		}

	}

}
