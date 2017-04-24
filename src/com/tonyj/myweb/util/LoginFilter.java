package com.tonyj.myweb.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.Log4jConfigListener;

import com.tonyj.myweb.po.BsUser;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		BsUser bsUser = (BsUser) session.getAttribute("LOGININFO");
		// 登陆页面无需过滤
        if(path.indexOf("login.jsp") > -1 || path.indexOf(".js") > -1 || path.indexOf(".css") > -1
        			|| path.indexOf(".png") > -1 || path.indexOf(".jgp") > -1) {
             chain.doFilter(servletRequest, servletResponse);
             return;
        }
		if(bsUser !=null){
			chain.doFilter(servletRequest, servletResponse);
		}else{
			servletResponse.sendRedirect("/webtest/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
