package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.PartnerApprovalDao;
import com.novelerp.appcontext.dto.BpartnerApprovalDto;
import com.novelerp.appcontext.entity.BpartnerApproval;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class PartnerApprovalDaoImpl  extends AbstractJpaDAO<BpartnerApproval, BpartnerApprovalDto> implements PartnerApprovalDao{
	@PostConstruct
	private void init() {
		setClazz(BpartnerApproval.class, BpartnerApprovalDto.class);
	}
    public String getQueryForPartnerApprovalByPartnerId()
    {
    	StringBuilder jpql=new StringBuilder();
    	jpql.append("select ba from BpartnerApproval ba");
    	jpql.append(" INNER JOIN FETCH ba.approvalPartner ap ");
    	jpql.append(" where ba.isActive='Y' AND ap.isActive='Y' ");
    	jpql.append(" AND ap.bPartnerId=:bpartnerId ");
    	return jpql.toString();
    }
    
   
}
