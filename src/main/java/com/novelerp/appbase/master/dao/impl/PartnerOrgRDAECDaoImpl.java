package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgRDAECDao;
import com.novelerp.appbase.master.dto.PartnerOrgRDAECDto;
import com.novelerp.appbase.master.entity.PartnerOrgRDAEC;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgRDAECDaoImpl extends AbstractJpaDAO<PartnerOrgRDAEC, PartnerOrgRDAECDto> implements PartnerOrgRDAECDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgRDAEC.class, PartnerOrgRDAECDto.class);
	}
	
	public String getOrgRDAECQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerOrgRDAEC e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.partnerOrg po")
		.append(" LEFT JOIN FETCH e.eligibilityCertificate ec")
		.append(" WHERE e.isActive= 'Y' AND e.partnerOrg.partnerOrgId= :partnerOrgId");
		return query.toString();
	}
}