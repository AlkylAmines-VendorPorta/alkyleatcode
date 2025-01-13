package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.service.SecurityPOHeaderService;

@Component
public class SecurityPOHeaderComponent {
//	
	@Autowired
	private SecurityPOHeaderService securityService;
	
//	@Autowired
//	private SecurityPOHeaderDao securityPOHeaderDao;
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public Integer getNewASNNumber(String plant) {
		return securityService.getNewASNNumber(plant);
	}



}
