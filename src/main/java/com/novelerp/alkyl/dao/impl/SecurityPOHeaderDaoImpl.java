package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.SecurityPOHeaderDao;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.entity.SecurityPOHeader;
import com.novelerp.appbase.master.entity.PartnerItemDrawingDoc;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class SecurityPOHeaderDaoImpl extends AbstractJpaDAO<SecurityPOHeader, SecurityPOHeaderDto> implements SecurityPOHeaderDao {

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void postConstruct(){
		
		setClazz(SecurityPOHeader.class, SecurityPOHeaderDto.class);
		
	}
	
	@Override
	public Integer getASNNumber(String plant) {
		StringBuilder query = new StringBuilder(" SELECT MAX(A.asnNumber) FROM SecurityPOHeader A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.asnNumber,'') like '" + plant + "%' ");
		Query q = em.createQuery(query.toString());
		Integer x = (Integer) q.getSingleResult();
		if (null == x) {
			x = Integer.parseInt(((String) plant + "000000"));
		}
		return new Integer(x + 1);
	}
	
	public String getASNBYStatusforCommercial() {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM SecurityPOHeader A ")
				.append(" INNER JOIN FETCH A.po B ")
//				.append(" INNER JOIN FETCH A.partner C ")
				.append(" INNER JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ")
				//append(" WHERE A.isActive = 'Y' and D.plant=:plant and A.status IN (:status)");
		.append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
		return jpql.toString();
	}
	
	public String getASNByASNIdforCommercial() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM SecurityPOHeader A ")
				.append(" INNER JOIN FETCH A.po B ")
//				.append(" INNER JOIN FETCH A.partner C ")
//				.append(" INNER JOIN FETCH A.createdBy E")
				.append(" WHERE A.isActive = 'Y' and A.asnHeaderId = :asnHeaderId ");
		return jpql.toString();
	}
	
	public String getASNByASNNoforCommercial() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM SecurityPOHeader A ")
				.append(" INNER JOIN FETCH A.reportedBy B ")
				.append(" INNER JOIN FETCH A.gateinBy C ")
				.append(" INNER JOIN FETCH A.partner D")
				.append(" INNER JOIN FETCH A.createdBy E ")
				.append(" INNER JOIN FETCH E.userDetails F ")
				.append(" WHERE A.isActive = 'Y' and A.asnNumber = :asnNumber ");
		return jpql.toString();
		
	}
	


	

//	@Transactional(propagation=Propagation.REQUIRED)
//	public int insertintoheadertable(SecurityPOHeader itemDoc){
//		StringBuilder jpql=new StringBuilder(" insert into SecurityPOHeader ")
//		.append(" (advanceShipmentNoticeNo,status,isActive,created,updated,createdBy,updatedBy,partner,createdSessionId,updatedSessionId) ");
////		.append(" select md,:partnerInfraItem,'Y',sysdate,sysdate,:user,:user,:partner,:sessionId,:sessionId ")
////		.append(" from MaterialDrawingDocument md ")
////	    .append(" where md.material.materialId=:materialId ");
//		Query query=getEntityManager().createQuery(jpql.toString());
//		query.setParameter("user", itemDoc.getCreatedBy());
//		query.setParameter("user", itemDoc.getCreatedBy());
//		query.setParameter("partner",itemDoc.getPartner());
//		query.setParameter("sessionId",itemDoc.getCreatedSessionId());
//		
//		return query.executeUpdate();
//	}

	
}
