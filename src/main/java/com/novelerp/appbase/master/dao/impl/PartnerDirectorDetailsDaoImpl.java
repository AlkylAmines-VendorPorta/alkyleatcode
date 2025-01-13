package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerDirectorDetailsDao;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerDirectorDetailsDaoImpl extends AbstractJpaDAO<UserDetails, UserDetailsDto> implements PartnerDirectorDetailsDao{

	@PostConstruct
	private void init() {
		setClazz(UserDetails.class, UserDetailsDto.class);
	}

	
	
}
