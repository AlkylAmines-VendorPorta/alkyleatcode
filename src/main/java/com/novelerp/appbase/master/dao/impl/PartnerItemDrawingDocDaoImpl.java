package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerItemDrawingDocDao;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.entity.PartnerItemDrawingDoc;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class PartnerItemDrawingDocDaoImpl extends AbstractJpaDAO<PartnerItemDrawingDoc, PartnerItemDrawingDocDto> implements PartnerItemDrawingDocDao{
    @PostConstruct
	private void init(){
		setClazz(PartnerItemDrawingDoc.class,PartnerItemDrawingDocDto.class);
	}
    
    public String getInfraDrawingDocsByPartnerAndInfraItem(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select dd from PartnerItemDrawingDoc dd ")
		.append(" INNER JOIN FETCH dd.partnerInfraItem ii ")
		.append(" INNER JOIN FETCH ii.material ii ")
		.append(" INNER JOIN FETCH dd.materialDrawingDoc mdd")
		.append(" WHERE dd.partner.bPartnerId=:partnerId ")
		.append(" AND dd.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
    
    public int updateInfraTypeTest(String status,String comment,Long partnerItemDrawingDocId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerItemDrawingDoc dd SET dd.status=:status,dd.remark=:comment ");
		jpql.append(" WHERE dd.partnerItemDrawingDocId=:partnerItemDrawingDocId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("partnerItemDrawingDocId", partnerItemDrawingDocId);
		query.setParameter("status", status);
		query.setParameter("comment", comment);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertDrawingDocByMaterial(PartnerItemDrawingDoc itemDoc){
		StringBuilder jpql=new StringBuilder(" insert into PartnerItemDrawingDoc ")
		.append(" (materialDrawingDoc,partnerInfraItem,isActive,created,updated,createdBy,updatedBy,partner,createdSessionId,updatedSessionId) ")
		.append(" select md,:partnerInfraItem,'Y',sysdate,sysdate,:user,:user,:partner,:sessionId,:sessionId ")
		.append(" from MaterialDrawingDocument md ")
	    .append(" where md.material.materialId=:materialId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("partnerInfraItem", itemDoc.getPartnerInfraItem());
		query.setParameter("user", itemDoc.getCreatedBy());
		query.setParameter("partner",itemDoc.getPartner());
		query.setParameter("sessionId",itemDoc.getCreatedSessionId());
		query.setParameter("materialId",itemDoc.getPartnerInfraItem().getMaterial().getMaterialId());
		return query.executeUpdate();
	}
	
	@Override
	public int resetInfraDrawingDocs(Long infraItemId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerItemDrawingDoc ia SET ia.status=null,ia.remark=null, ")
		.append(" ia.isEEApproved=null,ia.eeComment=null, ")
		.append(" ia.isSEApproved=null,ia.seComment=null, ")
		.append(" ia.isCEApproved=null,ia.ceComment=null, ")
		.append(" ia.isEDApproved=null,ia.edComment=null, ")
		.append(" ia.isDIRApproved=null,ia.dirComment=null ");
		jpql.append(" WHERE ia.partner.bPartnerId=:partnerId AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", infraItemId);
		query.setParameter("partnerId", partnerId);
		int count= query.executeUpdate();
		return count;
	}   
	public String getInfraDrawingDocById(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select dd from PartnerItemDrawingDoc dd ")
		.append(" LEFT JOIN FETCH dd.partner ptr ")
		.append(" LEFT JOIN FETCH dd.partnerInfraItem ii ")
		.append(" LEFT JOIN FETCH ii.material ii ")
		.append(" LEFT JOIN FETCH dd.materialDrawingDoc mdd")
		.append(" WHERE  dd.partnerItemDrawingDocId=:docId ");
		return jpql.toString();
	}
	public String getInfraDocsForApproval(){
		StringBuilder jpql=new StringBuilder(" Select d from PartnerItemDrawingDoc d ")
				.append(" LEFT JOIN FETCH d.materialDrawingDoc mdd ")
				/*.append(" t.:isApproved='N' OR t.:isApproved is null ")*/
				.append(" where d.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
}
