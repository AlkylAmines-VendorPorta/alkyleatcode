package com.novelerp.appcontext.filter;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novelerp.appcontext.service.UserSessionService;

public class SessionListener implements HttpSessionListener{

	private Logger log = LoggerFactory.getLogger(SessionListener.class);
	@Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("session created");
        /*event.getSession().setMaxInactiveInterval(15*60);*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
       System.out.println("session destroyed");
       UserSessionService userSessionService = null;
       HttpSession session  = event.getSession();
       if(session == null){
    	   log.error(" Session is Null ");
    	   return;
       }
       
       WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
       userSessionService = (UserSessionService)context.getBean(UserSessionService.class);
       String sessionId= session.getId();
       if(userSessionService == null){
    	   log.error(" UserSessionService is Null ");
    	   return;
       }
       if(sessionId==null){
    	   log.error(" Session Id is Null ");
    	   return;
       }
       
       userSessionService.updateUserSession(sessionId);
    }


}