package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerItemManufacturerDao;
import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Aman
 */
@Repository
public class PartnerItemManufacturerDaoImpl extends AbstractJpaDAO<PartnerItemManufacturer, PartnerItemManufacturerDto> implements PartnerItemManufacturerDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerItemManufacturer.class, PartnerItemManufacturerDto.class);
	}
	
	public String getPartnerItemManufacturerQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT distinct(e) FROM PartnerItemManufacturer e")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH p.paymentDetails pd")
		/*.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")*/
		.append(" LEFT JOIN FETCH e.location l")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d");
		return query.toString();
	}

	@Override
	public String getQueryForPartnerItemManufacturerQuery(RoleDto role) {
		StringBuilder jpql=new StringBuilder(getPartnerItemManufacturerQuery());
		jpql.append(getWhereClause(role));
		return jpql.toString();
	}
	private String getWhereClause(RoleDto role)
	{
		StringBuilder where = new StringBuilder();
		where.append("  WHERE e.partner.bPartnerId=:partnerId  ");
		if(role==null || role.getValue()==null){
			return where.toString();
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			where.append(" AND pd.vendorTypePayment='TP' AND pd.isFAApproved=:isFAApproved AND EXISTS (select itm from PartnerItemManufacturer itm INNER JOIN itm.partner ptr INNER JOIN itm.partnerOrg  io where itm.isEEApproved=null OR ptr.isEEApproved=null OR ptr.isCEApproved='C' OR io.isEEApproved=null )");
		 }
		if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		{
			where.append(" AND pd.vendorTypePayment='TP' AND e.isEEApproved='Y' AND pd.isFAApproved=:isFAApproved AND EXISTS (select itm from PartnerItemManufacturer itm INNER JOIN itm.partner ptr INNER JOIN itm.partnerOrg  io where itm.isCEApproved=null OR ptr.isCEApproved=null OR io.isCEApproved=null )");
		}
		
		return where.toString();
	}
}
