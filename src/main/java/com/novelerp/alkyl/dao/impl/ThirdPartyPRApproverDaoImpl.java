package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.ThirdPartyPRApproverDao;
import com.novelerp.alkyl.dto.ThirdPartyPRApproverDto;
import com.novelerp.alkyl.entity.ThirdPartyPRApprover;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ThirdPartyPRApproverDaoImpl extends AbstractJpaDAO<ThirdPartyPRApprover, ThirdPartyPRApproverDto>
implements ThirdPartyPRApproverDao {

	@PostConstruct
	public void postConstruct() {

		setClazz(ThirdPartyPRApprover.class, ThirdPartyPRApproverDto.class);
	}
	
	
	public String findThirdPartyById(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM ThirdPartyPRApprover A ");
		jpql.append(" LEFT JOIN FETCH A.pr p ");
		jpql.append(" LEFT JOIN FETCH A.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH A.updatedBy ub ");
		jpql.append(" LEFT JOIN FETCH A.partner bp ");
		jpql.append(" WHERE A.thirdPartyPRApproverId=:thirdPartyPRApproverId and A.pr.isActive='Y' ");
		return jpql.toString();
	}
	
	
	public String getThirdPartyPRByPrId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM ThirdPartyPRApprover A ");
		jpql.append(" INNER JOIN FETCH A.pr p ");
		jpql.append(" WHERE A.pr.prId=:prId and A.pr.isActive='Y' ");
		return jpql.toString();
	}
	
}
