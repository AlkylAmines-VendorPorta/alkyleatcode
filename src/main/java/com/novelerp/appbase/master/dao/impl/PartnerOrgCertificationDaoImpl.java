package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgCertificationDao;
import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman /Vivek Birdi
 */
@Repository
public class PartnerOrgCertificationDaoImpl extends AbstractJpaDAO<PartnerOrgCertification, PartnerOrgCertificationDto> implements PartnerOrgCertificationDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgCertification.class, PartnerOrgCertificationDto.class);
	}
	
	public String getOrgCertificateQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e from PartnerOrgCertification e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.partnerOrg o")
		.append(" LEFT JOIN FETCH e.certificateCopy cc ")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
	public String getPartnerOrgCertification()
	{
		StringBuilder query = new StringBuilder(" SELECT e from PartnerOrgCertification e ")
		.append(" WHERE e.partnerOrg.partnerOrgId=:partnerOrgId AND e.partner.bPartnerId=:partnerId ");
		return query.toString();
	}
}
