package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PraposedReasonDao;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.entity.PraposedReason;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class PraposedReasonDaoImpl extends AbstractJpaDAO<PraposedReason, PraposedReasonDto> implements PraposedReasonDao{
	
	@Override
	public void postConstruct() {
		setClazz(PraposedReason.class, PraposedReasonDto.class);
	}
	
	public String getProposedReasonByAnnexureId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PraposedReason A ")
		.append(" INNER JOIN FETCH A.annexure C ");
		jpql.append(" WHERE A.annexure.annexureId=:annexureId and A.isActive='Y' ");
		return jpql.toString();
	}

}
