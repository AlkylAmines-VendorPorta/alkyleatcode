package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Component
public class UserComponent {

	@Autowired
	private UserService userService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String getEmailByBpartnerId(Long partnerId){
		return userService.findDto("getuserByBPartner", AbstractContextServiceImpl.getParamMap("BPartnerId", partnerId)).getEmail();
	}
	
}
