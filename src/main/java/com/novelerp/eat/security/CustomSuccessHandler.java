package com.novelerp.eat.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.controller.LoginController;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private UserSessionService userSessionService;
	@Autowired
	private BPartnerService partnerService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		
		/* String targetUrl = determineTargetUrl(authentication); */
		/*String username = authentication.getName();*/
		HttpSession session = request.getSession(true);
		String remoteIP = null;
		String sessionId = request.getRequestedSessionId();

		UserDto userDto =    (UserDto) authentication.getPrincipal();/* loginService.getUserByUserName(username);*/
		String targetUrl="";
		if("Y".equals(userDto.getIsPasswordUpdated())){
			String url="dashboard4";/*partnerService.checkPartnerValidity(userDto,userDto.getRoles().iterator().next());*/
			targetUrl = "/"+url;/*userDto.getRoles().iterator().next().getViewName();*/
		}else{
			targetUrl="/changePassword";
		}
		session.setAttribute("UserSession", userDto);
		ResponseDto responseDto = new ResponseDto(false, "Logged In");
		userDto.setResponse(responseDto);
		contextService.contextInitializer(userDto);
		UserSessionDto userSesssion = new UserSessionDto();
		userSesssion.setSessionId(sessionId);
		userSesssion.setIsActive("Y");
		if (request != null) {
			remoteIP = request.getHeader("X-FORWARDED-FOR");
			if (remoteIP == null || "".equals(remoteIP)) {
				remoteIP = request.getRemoteAddr();
			}
		}
		userSesssion.setRemoteIp(remoteIP);
		userSesssion.setLoginTime(new Date());
		userSesssion.setUserName(userDto.getEmail());
		userSessionService.save(userSesssion);

		if (response.isCommitted()) {
			log.error("Can't redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
