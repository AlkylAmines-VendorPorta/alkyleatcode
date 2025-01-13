/**
v * @author Ankush
 */
package com.novelerp.appcontext.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionListenerController implements HttpSessionListener{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Autowired
	private HttpSessionListener sessionListener;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("created");
		sessionListener.sessionCreated(se);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("destroyed");
		sessionListener.sessionDestroyed(se);
	}
	
}
