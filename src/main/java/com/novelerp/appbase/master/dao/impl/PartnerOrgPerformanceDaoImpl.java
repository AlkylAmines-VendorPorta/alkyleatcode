package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgPerformanceDao;
import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgPerformanceDaoImpl extends AbstractJpaDAO<PartnerOrgPerformance, PartnerOrgPerformanceDto> implements PartnerOrgPerformanceDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrgPerformance.class, PartnerOrgPerformanceDto.class);
	}
	
	public String getOrgPerformanceQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerOrgPerformance e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.material m")
		.append(" LEFT JOIN FETCH m.uom u ")
		.append(" LEFT JOIN FETCH e.partnerOrg po")
		.append(" LEFT JOIN FETCH e.certificateAward ca")
		.append(" WHERE e.isActive = 'Y' AND e.partnerOrg.partnerOrgId = :partnerOrgId" );
		
		return query.toString();
	}
	public String getTraderPerformanceQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerOrgPerformance e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.material m")
		.append(" LEFT JOIN FETCH m.uom m")
		.append(" LEFT JOIN FETCH e.partnerOrg po")
		.append(" LEFT JOIN FETCH e.certificateAward ca")
		.append(" WHERE e.isActive = 'Y' AND e.partner.bPartnerId=:partnerId and e.partnerOrg.partnerOrgId is null " );
		return query.toString();
	}

	@Override
	public String checkForDuplicatePerformance(PartnerOrgPerformanceDto dto) {
		StringBuilder jpql=new StringBuilder(" SELECT p FROM PartnerOrgPerformance p ")
		.append(" LEFT JOIN FETCH p.partner bp ")
		.append(" LEFT JOIN FETCH p.material m ")
		.append(" LEFT JOIN FETCH p.partnerOrg o ")
		/*.append(" WHERE upper(p.firmName) like upper(:firmName) ")*/
		.append(" WHERE  p.material.materialId=:material ")
		.append(" AND p.poNumber=:poNumber ");
		/*.append(" AND trunc(p.orderStartDate)=trunc(:startDate) ")
		.append(" AND p.quantitySupplied=:quantity ");
		*/if(dto.getPartnerOrg()!=null && dto.getPartnerOrg().getPartnerOrgId()!=null){
			jpql.append(" AND p.partnerOrg.partnerOrgId=:partnerOrgId");
		}else if(dto.getPartner()!=null && dto.getPartner().getbPartnerId()!=null){
			jpql.append(" AND p.partner.bPartnerId=:partnerId AND p.partnerOrg.partnerOrgId IS NULL ");
		}
		return jpql.toString();
	}
}