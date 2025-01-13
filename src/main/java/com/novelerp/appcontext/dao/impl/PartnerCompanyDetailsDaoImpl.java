package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.PartnerCompanyDetailsDao;
import com.novelerp.appcontext.dto.PartnerCompanyDetailsDto;
import com.novelerp.appcontext.entity.PartnerCompanyDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerCompanyDetailsDaoImpl extends AbstractJpaDAO<PartnerCompanyDetails, PartnerCompanyDetailsDto> implements PartnerCompanyDetailsDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerCompanyDetails.class, PartnerCompanyDetailsDto.class);
	}
}
