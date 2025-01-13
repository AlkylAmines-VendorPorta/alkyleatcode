package com.novelerp.appcontext.service.impl;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;

public  abstract class AbstractUserConextService {

	public abstract ContextDto getContext();

	protected HttpServletRequest getRequest(){
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest();
	}
    		
	public Set<RoleDto> getRoles(){
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getRoles();
	}


	public UserDto getUser() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getUserDto();
	}


	public BPartnerDto getPartner() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getPartnerDto();
	}


	public UserDetailsDto getUserDetails() {
		ContextDto context = getContext();
		if(context == null){
			return null;
		}
		return context.getUserDetailsDto();
	}

	public RoleDto getDefaultRole(){
		Set<RoleDto> roles =  getRoles();
		if(!CommonUtil.isCollectionEmpty(roles)){
			return roles.iterator().next();
		}
		return null;
	}

}
