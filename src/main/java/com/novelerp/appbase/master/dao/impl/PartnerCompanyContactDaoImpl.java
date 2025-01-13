package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerCompanyContactDao;
import com.novelerp.appbase.master.dto.PartnerCompanyContactDto;
import com.novelerp.appbase.master.entity.PartnerCompanyContact;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerCompanyContactDaoImpl extends AbstractJpaDAO<PartnerCompanyContact, PartnerCompanyContactDto> implements PartnerCompanyContactDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerCompanyContact.class, PartnerCompanyContactDto.class);
	}

}
