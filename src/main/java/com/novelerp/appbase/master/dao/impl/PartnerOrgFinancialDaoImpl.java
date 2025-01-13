package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgFinancialDao;
import com.novelerp.appbase.master.dto.PartnerOrgFinancialDto;
import com.novelerp.appbase.master.entity.PartnerOrgFinancial;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgFinancialDaoImpl extends AbstractJpaDAO<PartnerOrgFinancial, PartnerOrgFinancialDto> implements PartnerOrgFinancialDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgFinancial.class, PartnerOrgFinancialDto.class);
	}
}

