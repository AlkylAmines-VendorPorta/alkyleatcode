package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.service.PartnerDirectorDetailsService;
import com.novelerp.appcontext.dao.UserDetailsDao;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * 
 * @author Aman
 * @author Vivek Birdi
 */
@Service
public class PartnerDirectorDetailsServiceImpl extends AbstractContextServiceImpl<UserDetails, UserDetailsDto> implements PartnerDirectorDetailsService{


	@Autowired
	private UserDetailsDao  partnerDirectorDetailsDao;
	
	
	@PostConstruct
	public void init(){
		super.init(PartnerDirectorDetailsServiceImpl.class, partnerDirectorDetailsDao, UserDetails.class, UserDetailsDto.class);
		/*setObjectConverter(partnerDirectorDetailsConverter);*/
		setByPassProxy(true);
	}	

}
