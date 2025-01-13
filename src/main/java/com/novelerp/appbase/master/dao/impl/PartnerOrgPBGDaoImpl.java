package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgPBGDao;
import com.novelerp.appbase.master.dto.PartnerOrgPBGDto;
import com.novelerp.appbase.master.entity.PartnerOrgPBG;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Vivek Birdi
 */
@Repository
public class PartnerOrgPBGDaoImpl extends AbstractJpaDAO<PartnerOrgPBG, PartnerOrgPBGDto> implements PartnerOrgPBGDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgPBG.class, PartnerOrgPBGDto.class);
	}
	
	/**
	 * get the query for Permanent bank guarantee details of the Partner's Org.
	 * @return query string for partner-org's Permanent bank Guarantee
	 */
	public String getPBGQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT pbg FROM PartnerOrgPBG pbg")
		.append(" LEFT JOIN FETCH pbg.partnerOrg o")
		.append(" LEFT JOIN FETCH pbg.createdBy c")
		.append(" LEFT JOIN FETCH pbg.updatedBy u")
		.append(" LEFT JOIN FETCH pbg.partner p")
		.append(" LEFT JOIN FETCH pbg.bankGauranteeCopy bgc")
		/*.append(" WHERE pbg.isActive='Y' AND pbg.partnerOrg.par0itnerOrgId = :partnerOrgId");*/
		.append(" WHERE pbg.partner.bPartnerId = :partnerId");
		return query.toString();
	}

}