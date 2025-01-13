package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;


import com.novelerp.alkyl.dao.SecurityPOItemDao;

import com.novelerp.alkyl.dto.SecurityPOItemDto;

import com.novelerp.alkyl.entity.SecurityPOItem;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class SecurityPOItemDaoImpl extends AbstractJpaDAO<SecurityPOItem, SecurityPOItemDto> implements SecurityPOItemDao {

	@PostConstruct
	public void postconstruct(){
		
		setClazz(SecurityPOItem.class, SecurityPOItemDto.class);
	}
	
	public String getASNLinesByASNIdforSecurity(){
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM SecurityPOItem A ")
				.append(" INNER JOIN FETCH A.securityPOHeaderDto B ")
				.append(" INNER JOIN FETCH B.po po  ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
//				.append(" LEFT JOIN FETCH A.parentASNLine E ")
//				.append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
				.append(" INNER JOIN FETCH B.createdBy G ")
				.append(" INNER JOIN FETCH G.userDetails H ")				
				.append(" WHERE  A.isActive = 'Y' and B.asnHeaderId =:asnHeaderId")
//				.append(" WHERE  A.isActive = 'Y' and B.asnHeaderId =:asnHeaderId and A.deliveryQuantity>0  ")
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
}
