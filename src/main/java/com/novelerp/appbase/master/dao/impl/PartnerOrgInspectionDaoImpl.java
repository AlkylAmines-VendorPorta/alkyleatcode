package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgInspectionDao;
import com.novelerp.appbase.master.dto.PartnerOrgInspectionDto;
import com.novelerp.appbase.master.entity.PartnerOrgInspection;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class PartnerOrgInspectionDaoImpl extends AbstractJpaDAO<PartnerOrgInspection, PartnerOrgInspectionDto> implements PartnerOrgInspectionDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgInspection.class, PartnerOrgInspectionDto.class);
	}
	
	public String getPartnerOrgInspection()
	{
		StringBuilder jpql=new StringBuilder(" select i from PartnerOrgInspection i ");
		jpql.append(" INNER JOIN FETCH i.partnerOrg po ");
		jpql.append(" where i.partnerOrg.partnerOrgId=:partnerOrgId");
		return jpql.toString();
	}
}
