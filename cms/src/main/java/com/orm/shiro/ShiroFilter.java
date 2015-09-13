package com.orm.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.BeansException;

import com.cako.basic.platform.user.entity.User;
import com.orm.config.InitEnvironment;

public class ShiroFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			InitEnvironment environment = InitEnvironment.getInitEnvironmentInstance();
			System.out.println(environment);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(true);
			String url = httpRequest.getRequestURL().toString();
			User user = (User) session.getAttribute("user");
			if (user != null) {
				chain.doFilter(request, response);
			} else {
				if (url.contains("/tribune/user")) {

				}
				httpRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
