package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PublicNoticeDao;
import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.entity.PublicNotice;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class PublicNoticeDaoImpl extends AbstractJpaDAO<PublicNotice, PublicNoticeDto> implements PublicNoticeDao{

private static final String ENTITY_NAME="M_PUBLICNOTICE";
	
	@PersistenceContext
	private EntityManager em;


	@PostConstruct
	private void init() {
		setClazz(PublicNotice.class, PublicNoticeDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<PublicNotice> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),PublicNotice.class).getResultList();
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
	public List<PublicNotice> getPublicNoticeList() {
		StringBuilder sb=new StringBuilder("select pn from PublicNotice pn ");
		sb.append(" LEFT JOIN FETCH pn.attachment a");
		/*sb.append(" LEFT JOIN FETCH pn.tenderDetails td");*/
		Query q=em.createQuery(sb.toString(),PublicNotice.class);
		List<PublicNotice> publicNoticeList= q.getResultList();
		return publicNoticeList;
	}
	
	public String getPublicNoticesList() {
		StringBuilder sb=new StringBuilder("select pn from PublicNotice pn ");
		sb.append(" LEFT JOIN FETCH pn.attachment a");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PublicNotice> getPublicNoticeListById(Long id) {
		StringBuilder sb=new StringBuilder("select pn from PublicNotice pn ");
		sb.append(" LEFT JOIN FETCH pn.attachment a");
		/*sb.append(" LEFT JOIN FETCH pn.tenderDetails td");*/
		sb.append("  where pn.id=:id");
		Query q=em.createQuery(sb.toString(),PublicNotice.class);
		System.err.println("id: "+id);
		q.setParameter("id", id);
		List<PublicNotice> publicNotice= q.getResultList();
		return publicNotice;
	}
	
	public String getPublicNoticebyId() {
		StringBuilder sb=new StringBuilder("select pn from PublicNotice pn ");
		sb.append(" LEFT JOIN FETCH pn.attachment a");
		sb.append(" WHERE pn.publicNoticeId=:publicNoticeId");
		return sb.toString();
	}
	
	public String getHomePublicNoticeLists(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT p FROM PublicNotice p")
		.append(" LEFT JOIN FETCH p.tahdr td")
		.append(" LEFT JOIN FETCH td.tahdrDetail tahdrdet")
		.append(" LEFT JOIN FETCH p.attachment attachment")
		.append(" WHERE SYSDATE<=tahdrdet.purchaseToDate");
		return query.toString();
	
	}
}

