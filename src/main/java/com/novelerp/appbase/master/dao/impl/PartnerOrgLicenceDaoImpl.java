package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgLicenseDao;
import com.novelerp.appbase.master.dto.PartnerOrgLicenseDto;
import com.novelerp.appbase.master.entity.PartnerOrgLicense;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgLicenceDaoImpl extends AbstractJpaDAO<PartnerOrgLicense, PartnerOrgLicenseDto> implements PartnerOrgLicenseDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgLicense.class, PartnerOrgLicenseDto.class);
	}
	
	public String getPartnerOrgLicenseQuery(){
		StringBuilder query = new StringBuilder("SELECT e FROM PartnerOrgLicense e");
		query.append(" INNER JOIN FETCH e.partnerOrg o")
		.append(" INNER JOIN FETCH e.createdBy c")
		.append(" INNER JOIN FETCH e.updatedBy u")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId");
		
		return query.toString();
	}
}