package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PRAttachmentDao;
import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.entity.PRAttachment;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class PRAttachmentDaoImpl extends AbstractJpaDAO<PRAttachment, PRAttachmentDto> implements PRAttachmentDao{

	@PostConstruct
	public void postconstruct(){
		
		setClazz(PRAttachment.class, PRAttachmentDto.class);
	}

	
	public String findPRAttById(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRAttachment A ");
		jpql.append(" LEFT JOIN FETCH A.attachment att ");
		jpql.append(" LEFT JOIN FETCH A.pr p ");
		jpql.append(" LEFT JOIN FETCH A.createdBy cb ");
		jpql.append(" LEFT JOIN FETCH A.updatedBy ub ");
		jpql.append(" LEFT JOIN FETCH A.partner bp ");
		jpql.append(" WHERE A.prAttachmentId=:prAttachmentId and A.pr.isActive='Y' ");
		return jpql.toString();
	}
	
	
	public String getPRAttByPrId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRAttachment A ");
		jpql.append(" INNER JOIN FETCH A.attachment att ");
		jpql.append(" INNER JOIN FETCH A.pr p ");
		jpql.append(" WHERE A.pr.prId=:prId and A.pr.isActive='Y' and A.isActive='Y' ");
		return jpql.toString();
	}
	
	public String getPRAttByAttId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRAttachment A ");
		jpql.append(" INNER JOIN FETCH A.attachment att ");
		jpql.append(" INNER JOIN FETCH A.pr p ");
		jpql.append(" WHERE att.fileName=:fileName and A.pr.isActive='Y' and A.isActive='Y' ");
		return jpql.toString();
	}
	
}
