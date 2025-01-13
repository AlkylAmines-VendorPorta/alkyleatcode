package com.novelerp.alkyl.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.AnnexureDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.entity.Annexure;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AnnexureDaoImpl extends AbstractJpaDAO<Annexure, AnnexureDto> implements AnnexureDao {

	@Override
	public void postConstruct() {
		setClazz(Annexure.class, AnnexureDto.class);
	}
	
	@PersistenceContext
	private EntityManager em;
	
	public String getAnnexureByPrId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM Annexure A ")
	/*	.append(" INNER JOIN FETCH A.pr C ");*/
				.append(" INNER JOIN FETCH A.enquiry C ");
		jpql.append(" WHERE C.enquiryId=:prId and A.isActive='Y' ");
		return jpql.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Annexure> getPrForQcf(String where){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM Annexure A ")
		/*.append(" INNER JOIN FETCH A.pr C ");*/
		.append(" INNER JOIN FETCH A.enquiry C ")
		.append(" INNER JOIN FETCH C.createdBy D ");
		jpql.append(" WHERE ");
		jpql.append(where);
		jpql.append(" and A.isActive='Y' ");
		Query q = em.createQuery(jpql.toString());
		return q.getResultList();
	}
	
	public String getAnnexureById(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM Annexure A ")
		.append(" INNER JOIN FETCH A.enquiry C ")
		.append(" LEFT JOIN FETCH A.rejectedby D");
		jpql.append(" WHERE A.annexureId=:Id  ");
		return jpql.toString();
	}
}
