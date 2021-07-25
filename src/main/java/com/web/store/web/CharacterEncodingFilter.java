package com.web.store.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class CharacterEncodingFilter implements Filter {

	private String encoding;
	
    public void init(FilterConfig Config) throws ServletException {
    	
    	encoding = Config.getInitParameter("encoding");
	}
    
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		if (encoding != null) {
			req.setCharacterEncoding(encoding);
		}
		chain.doFilter(req, resp);
	}
	
	public void destroy() {
		
	}
}
