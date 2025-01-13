package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgOEDao;
import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgOEDaoImpl extends AbstractJpaDAO<PartnerOrgOE, PartnerOrgOEDto> implements PartnerOrgOEDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgOE.class, PartnerOrgOEDto.class);
	}
	
	public String getOrgOEDetailQuery(){
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerOrgOE e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.partnerOrg po")
		.append(" LEFT JOIN FETCH e.eligibilityCertificte ec ")
		.append(" WHERE e.partnerOrg.partnerOrgId = :partnerOrgId order by e.oeType");
		
		return query.toString();
	}
	public String getOEQuery(){
		StringBuilder query = new StringBuilder("SELECT e FROM PartnerOrgOE e ")
				.append(" where e.partnerOrg.partnerOrgId = :partnerOrgId and e.partner.bPartnerId=:partnerId ");
		return query.toString();
	}
}
