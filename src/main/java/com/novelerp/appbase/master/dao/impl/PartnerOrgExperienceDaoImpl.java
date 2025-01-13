package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgExperienceDao;
import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.entity.PartnerOrgExperience;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman/ Vivek Birdi
 */
@Repository
public class PartnerOrgExperienceDaoImpl extends AbstractJpaDAO<PartnerOrgExperience, PartnerOrgExperienceDto> implements PartnerOrgExperienceDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgExperience.class, PartnerOrgExperienceDto.class);
	}

	public String getOrgExperienceQuery(){
		/* Vivek Birdi . */
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e from PartnerOrgExperience e")
		.append(" LEFT OUTER JOIN e.createdBy c")
		.append(" LEFT OUTER JOIN e.updatedBy u")
		.append(" LEFT OUTER JOIN e.partnerOrg o")
		.append(" LEFT OUTER JOIN e.partner p")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");	
		return query.toString();
	}
	public String getTradingExperienceQuery(){
		StringBuilder jpql=new StringBuilder("SELECT e from PartnerOrgExperience e ")
		.append(" LEFT JOIN FETCH e.partner p ")
		.append(" LEFT JOIN FETCH e.partnerOrg po ")
		.append(" where e.partner.bPartnerId=:partnerId and e.partnerOrg.partnerOrgId is null ");
		return jpql.toString();
	}
	
}
