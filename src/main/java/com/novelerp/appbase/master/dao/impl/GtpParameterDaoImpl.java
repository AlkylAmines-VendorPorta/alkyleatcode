package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.GtpParameterDao;
import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class GtpParameterDaoImpl extends AbstractJpaDAO<GtpParameter, GtpParameterDto> implements GtpParameterDao{

private static final String ENTITY_NAME="M_GTPPARAMETER";

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(GtpParameter.class, GtpParameterDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<GtpParameter> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),GtpParameter.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM ")
		.append(ENTITY_NAME)
		.append(" u LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	
	public String getBasicOrderBy(){
		return " order by u.updated desc ";
	}
	
	public String getActiveClause(){
		return " u.isActive= 'Y'";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GtpParameter> getGtpParameterList() {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.material ");
		Query q=em.createQuery(sb.toString(),GtpParameter.class);
		List<GtpParameter> gtpParameterlist= q.getResultList();
		return gtpParameterlist;
	}
	
	@Override
	public String getAllGtpParameterQuery(String searchColumn, String searchValue) {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.partner bp");
		sb.append(" LEFT JOIN FETCH gp.material m ");
		sb.append(" LEFT JOIN FETCH m.hsnCode h ");
		sb.append(" where gp.isActive='Y' AND bp.bPartnerId=:bPartnerId ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(gp."+searchColumn+") LIKE :searchValue");
		}
		return sb.toString();
	}
	
	@Override
	public String getAllGtpParameterCountQuery(String searchColumn, String searchValue) {
		StringBuilder sb=new StringBuilder(" c.partner.bPartnerId=:bPartnerId AND c.isActive='Y' ");
		if(!"none".equalsIgnoreCase(searchValue)){
			sb.append(" AND UPPER(c."+searchColumn+") LIKE :searchValue");
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GtpParameter> getGtpParameterById(Long id) {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.material m ");
		sb.append(" LEFT JOIN FETCH m.hsnCode where gp.gtpParameterId=:id");
		Query q=em.createQuery(sb.toString(),GtpParameter.class);
		q.setParameter("id", id);
		List<GtpParameter> gtpParameterLIst= q.getResultList();
		return gtpParameterLIst;
	}

	@Override
	public String getGtpParameterListByMaterial() {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.material m ");
		sb.append(" WHERE m.materialId=:materialId ");
		return sb.toString();
	}
	

	public String getCopiedGtpParameterListByMaterial() {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.material m ");
		sb.append(" WHERE m.materialId=:materialId AND gp.isCopied='Y' ");
		return sb.toString();
	}
	
	public String getNonCopiedGtpParameterListByMaterial() {
		StringBuilder sb=new StringBuilder("select gp from GtpParameter gp ");
		sb.append(" LEFT JOIN FETCH gp.gtpParameterType ud");
		sb.append(" LEFT JOIN FETCH gp.material m ");
		sb.append(" WHERE m.materialId=:materialId AND gp.isCopied IS NULL ");
		return sb.toString();
	}
	
	@Override
	public int deleteCopiedGTP(Long materialId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" DELETE FROM GtpParameter b ");
		jpql.append( " where b.material.materialId=:materialId AND b.isCopied='Y'");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("materialId", materialId);
		int count= query.executeUpdate();
		return count;
	}
}

