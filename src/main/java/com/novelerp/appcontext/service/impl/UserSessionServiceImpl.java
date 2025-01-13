package com.novelerp.appcontext.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.UserSessionDao;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.entity.UserSession;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.core.dto.ResponseDto;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

@Service
public class UserSessionServiceImpl extends AbstractContextServiceImpl<UserSession, UserSessionDto> implements UserSessionService{

	@Autowired
	private UserSessionDao userSessionDao;
	
	@PostConstruct
	public void init(){
		super.init(UserSessionServiceImpl.class, userSessionDao, UserSession.class, UserSessionDto.class);
		setByPassProxy(true);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserSessionDto updateUserSession(String sessionId) {
		UserSessionDto sessionDto=new UserSessionDto();				
		try{
		    int count=userSessionDao.updateUserSession(sessionId);
		    if(count>0)
		    {
		    	sessionDto.setResponse(new ResponseDto(false, "Record updated"));
		    	return sessionDto;
		    }
		}
		catch(Exception e)
		{
			sessionDto.setResponse(new ResponseDto(true, "Error in update"));
			log.error(e.toString());
		}
		
		return sessionDto;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserSessionDto saveUserSession(HttpServletRequest request,UserDto userDto) {
		String sessionId=request.getRequestedSessionId() == null ? request.getSession().getId() : request.getRequestedSessionId();
		String remoteIP=null;
		 UserSessionDto userSesssion=new UserSessionDto();
		  userSesssion.setSessionId(sessionId);
		  userSesssion.setIsActive("Y");
		  userSesssion.setAuthToken(userDto.getAuthToken());
		  userSesssion.setPartner(userDto.getPartner());
		 /* if (request != null) {
			  remoteIP = request.getHeader("X-FORWARDED-FOR");
			  if (remoteIP == null || "".equals(remoteIP)) {
				  remoteIP = request.getRemoteAddr();
			  }
		  }*/
		    remoteIP = request.getHeader("x-forwarded-for"); 
		    if(remoteIP == null || remoteIP.length() == 0 || "unknown".equalsIgnoreCase(remoteIP)) { 
		    	remoteIP = request.getHeader("Proxy-Client-IP"); 
		    } 
		    if(remoteIP == null || remoteIP.length() == 0 || "unknown".equalsIgnoreCase(remoteIP)) { 
		    	remoteIP = request.getHeader("WL-Proxy-Client-IP"); 
		    } 
		    if(remoteIP == null || remoteIP.length() == 0 || "unknown".equalsIgnoreCase(remoteIP)) { 
		    	remoteIP = request.getRemoteAddr(); 
		    } 
		    
	      userSesssion.setRemoteIp(remoteIP);
	      String macAddress=getClientMACAddress(remoteIP);
	      userSesssion.setMacAddress(macAddress);
		  userSesssion.setLoginTime(new Date());
		  userSesssion.setUserName(userDto.getEmail());
		  UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	      OperatingSystem agent = userAgent.getOperatingSystem();
	      userSesssion.setDeviceType(agent.getName()+" "+agent.getDeviceType().getName());
	      HttpSession session=ContextServiceImpl.getCurrentSession();
			userSesssion.setUpdatedBy(userDto);
			userSesssion.setCreatedBy(userDto);
			userSesssion.setCreatedSessionId(session.getId());
			userSesssion.setUpdatedSessionId(session.getId());
			userSesssion.setPartner(userDto.getPartner());
       return save(userSesssion);
	}
	public static String getClientMACAddress(String clientIp){ 
	     String str = ""; 
	     String macAddress = ""; 
	     try { 
	    	    /*InetAddress ip=InetAddress.getLocalHost();
	    		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
	    		byte[] mac = network.getHardwareAddress();
	    			
	    		System.out.print("Current MAC address : ");
	    			
	    		StringBuilder sb = new StringBuilder();
	    		for (int i = 0; i < mac.length; i++) {
	    			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
	    		}
	    		System.out.println(sb.toString());*/
	    		
	          Process p = Runtime.getRuntime().exec("nbtstat -A " + clientIp); 
	          InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
	          LineNumberReader input = new LineNumberReader(ir); 
	          for (int i = 1; i <100; i++) { 
	               str = input.readLine(); 
	               if (str != null) { 
	                    if (str.indexOf("MAC Address") > 1) { 
	                         macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
	                         break; 
	                    } 
	               } 
	          }
	     } catch (IOException e) { 
	         /*log.error(e.toString());*/
	     } 
	     return macAddress; 
	}
	public static void main(String[] a){
		
		getClientMACAddress("172.20.20.234");
	}
	@Override
	public long getSessionAuditsQueryCount(String searchColumn, String searchValue) {
		long totalCount;
		if (!"none".equalsIgnoreCase(searchColumn)) {
			Map<String, Object> params = null;
			String queryString = userSessionDao.getSessionAuditCountQry(searchColumn);
			params = AbstractContextServiceImpl.getParamMap("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, params);
		}else{
			totalCount = getRecordCount();
		}
		return totalCount;
	}
	@Override
	public List<UserSessionDto> getSessionAuditList(int pageNumber, int pageSize, String searchColumn, String searchValue) {
		List<UserSessionDto> sessionList = new ArrayList<>();
		Map<String, Object> params = null;
		String queryString = userSessionDao.getSessionListQuery(searchColumn, searchValue);
		if (!"none".equalsIgnoreCase(searchValue)) {
			params = AbstractContextServiceImpl.getParamMap("searchValue",
					"%" + searchValue.toUpperCase() + "%");
		} 
		sessionList = findDtosByQuery(queryString, params, pageNumber, pageSize);
		return sessionList;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserSessionDto updateUserAuthTokenSession(String authToken) {
		UserSessionDto sessionDto=new UserSessionDto();				
		try{
		    int count=userSessionDao.updateUserAuthTokenSession(authToken);
		    if(count>0)
		    {
		    	sessionDto.setResponse(new ResponseDto(false, "Record updated"));
		    	return sessionDto;
		    }
		}
		catch(Exception e)
		{
			sessionDto.setResponse(new ResponseDto(true, "Error in update"));
			log.error(e.toString());
		}
		
		return sessionDto;
	}
	
}
