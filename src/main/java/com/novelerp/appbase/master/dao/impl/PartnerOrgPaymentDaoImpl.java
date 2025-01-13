package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgPaymentDao;
import com.novelerp.appbase.master.dto.PartnerOrgPaymentDto;
import com.novelerp.appbase.master.entity.PartnerOrgPayment;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgPaymentDaoImpl extends AbstractJpaDAO<PartnerOrgPayment, PartnerOrgPaymentDto> implements PartnerOrgPaymentDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgPayment.class, PartnerOrgPaymentDto.class);
	}
}

