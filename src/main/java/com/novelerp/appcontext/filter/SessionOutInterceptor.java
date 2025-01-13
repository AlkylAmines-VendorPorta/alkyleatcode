package com.novelerp.appcontext.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionOutInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionOutInterceptor.class);

	private static List<String> filterUrlList = new ArrayList<>();

	public SessionOutInterceptor() {
		filterUrlList.add("/errors");
		filterUrlList.add("/createCaptcha");
		filterUrlList.add("/login");
		filterUrlList.add("/");
		filterUrlList.add("/pu");
		filterUrlList.add("/rest");
		filterUrlList.add("/testReact");
		filterUrlList.add("/api/auth/signin");
		filterUrlList.add("/home");
		filterUrlList.add("/latestAuctionReverse");
		filterUrlList.add("/latestAuctionForward");
		filterUrlList.add("/latestTendersProcurement");
		filterUrlList.add("/blacklistedVendors");
		filterUrlList.add("/registeredVendors");
		filterUrlList.add("/blacklistedCustomer");
		filterUrlList.add("/registeredCustomer");
		filterUrlList.add("/getRegisteredVendorList");
		filterUrlList.add("/register");
		filterUrlList.add("/getTenderSearchDetail");
		filterUrlList.add("/advanceSearch");
		filterUrlList.add("/getSearchList");
		filterUrlList.add("/termsnCondition");
		filterUrlList.add("/notification");
		filterUrlList.add("/forgotPassword");
		filterUrlList.add("/contactUs");
		filterUrlList.add("/calendar");
		filterUrlList.add("/disclaimer");
		filterUrlList.add("/getHomePublicNoticeList");
		filterUrlList.add("/publicNotices");
		filterUrlList.add("/registration");
		filterUrlList.add("/download");
		filterUrlList.add("/getLatestTahdrTypeCode/");
		filterUrlList.add("/getTahdrTypeCode/");
		filterUrlList.add("/latestTendersWorks");
		filterUrlList.add("/latestAnnouncements");
		filterUrlList.add("/getRegisteredCustomerList/");
		filterUrlList.add("/login");
		filterUrlList.add("/getTAHDRDetailsById/");
		filterUrlList.add("/reloadContext");
		filterUrlList.add("/reloadProperties");
		filterUrlList.add("/reloadServletContext");
		filterUrlList.add("/changePlainPassword");
		filterUrlList.add("/credits");
		filterUrlList.add("/faq");
		filterUrlList.add("/sessionOut");
		filterUrlList.add("/Access_Denied");
		filterUrlList.add("/interpoleResponse");
		filterUrlList.add("/getApprovedVendorsList");
		filterUrlList.add("/getAdvanceSearchCount");
		filterUrlList.add("/partner/**");
		}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		logger.info("in interceptor" + request.getServletPath());

		HttpSession session = request.getSession(false);
		String url = request.getServletPath();

		if (isXMLHttpRequest(request) && !(isFilteredUrl(url))
				&& (null == session || session.getAttribute("UserSession") == null)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // status code is 401
			return false;
		} else if (!(isFilteredUrl(url)) && (null == session || session.getAttribute("UserSession") == null)) {
			try {
				response.sendRedirect(request.getContextPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			return true;
		}
	}

	private boolean isXMLHttpRequest(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equals("XMLHttpRequest");
	}

	private boolean isFilteredUrl(String requestUrl) {

		if (requestUrl.equals("/")) {
			return true;
		} else {
			for (String url : filterUrlList) {
				if (!"/".equalsIgnoreCase(url))
					if (requestUrl.contains(url)) {
						return true;
					}
			}
		}

		return false;
	}
}