package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgRegistrationDao;
import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 * @author Vivek Birdi
 */
@Repository
public class PartnerOrgRegistrationDaoImpl extends AbstractJpaDAO<PartnerOrgRegistration, PartnerOrgRegistrationDto> implements PartnerOrgRegistrationDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgRegistration.class, PartnerOrgRegistrationDto.class);
	}
	
	public String getPartnerOrgRegQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerOrgRegistration e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.partnerOrg po")
		.append(" LEFT JOIN FETCH e.registrationCopy rc")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
	public String getPartnerOrgRegByType(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerOrgRegistration e")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId")
		.append(" and e.registrationType = :registrationType");
		return query.toString();
	
	}
	public String getPartnerOrgRegQueryForProduct(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerOrgRegistration e")
		.append(" WHERE e.isApplicable='N' AND e.partnerOrg.partnerOrgId = :partnerOrgId  ");
		return query.toString();
	}
	public String getOrgRegQuery(){
		StringBuilder query =  new StringBuilder("SELECT e FROM PartnerOrgRegistration e")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId  AND e.partner.bPartnerId=:partnerId ");
		return query.toString();
	}
}
