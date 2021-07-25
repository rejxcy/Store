package com.web.store.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.store.domain.Customer;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"*.jsp","/controller"})
public class LoginCheckFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Customer customer =(Customer) request.getSession().getAttribute("customer");
		
		String action = request.getParameter("action");
		//除 登入與註冊外 皆導去登入頁面
		if (customer == null && !"login".equals(action) && !"reg_init".equals(action)) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		chain.doFilter(req, resp);
	} 
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
