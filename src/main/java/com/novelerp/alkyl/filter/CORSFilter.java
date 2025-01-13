//package com.novelerp.alkyl.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CORSFilter extends HttpFilter{
//
//	@Override
//	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		/*if(!"OPTIONS".equals(request.getMethod())){
//			super.doFilter(request, response, chain);
//		}else{
//			response.setStatus(HttpServletResponse.SC_OK);
//		}*/
//		super.doFilter(request, response, chain);
//		
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//}