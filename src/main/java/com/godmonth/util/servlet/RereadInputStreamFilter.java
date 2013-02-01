package com.godmonth.util.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author shenyue
 */
public class RereadInputStreamFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (!(request instanceof BodyRequestWrapper)
				&& ("POST".equalsIgnoreCase(httpServletRequest.getMethod()) || "PUT"
						.equalsIgnoreCase(httpServletRequest.getMethod()))) {
			BodyRequestWrapper pbrw = new BodyRequestWrapper(httpServletRequest);
			chain.doFilter(pbrw, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
