package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgBISDao;
import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgBISDaoImpl extends AbstractJpaDAO<PartnerOrgBIS, PartnerOrgBISDto> implements PartnerOrgBISDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgBIS.class, PartnerOrgBISDto.class);
	}
	public String getOrgBISQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e from PartnerOrgBIS e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.partnerOrg o")
		.append(" LEFT JOIN FETCH e.bisLicenceCertificate bc")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
	public String getPartnerOrgBISQuery()
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT e from PartnerOrgBIS e")
		.append(" WHERE e.isNotApplicable='N' AND e.partnerOrg.partnerOrgId = :partnerOrgId");
		return query.toString();
	}
	public String getPartnerOrgBIS()
	{
		StringBuilder query = new StringBuilder(" SELECT e from PartnerOrgBIS e ")
		.append(" WHERE e.partnerOrg.partnerOrgId=:partnerOrgId AND e.partner.bPartnerId=:partnerId ");
		return query.toString();
	}
	
}