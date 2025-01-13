package com.novelerp.appcontext.filter;

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

public class CaptchaFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String path = ((HttpServletRequest) request).getRequestURI();

		res.addHeader("Server", "xyz");

		res.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
		res.addHeader("Pragma", "no-cache");
		res.addDateHeader("Expires", 0);
		if (path.contains("/eatApp/register")) {
			HttpSession session1 = httpReq.getSession(true);
			String storedCaptcha = "";
			storedCaptcha = (String) session1.getAttribute("CAPTCHA");
			session1.setAttribute("CAPTCHA", "");
			if (storedCaptcha.equals(request.getParameter("UserCaptchaCode")))// if(storedCaptcha.equals(request.getParameter("j_captcha")))
			{
				chain.doFilter(httpReq, response);
				res.addHeader("X-Powered-By", "");
			} else {
				session1.invalidate();
				res.sendRedirect("/registration");
			}
		} else {

			chain.doFilter(httpReq, response);
			res.addHeader("X-Powered-By", "");

		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
