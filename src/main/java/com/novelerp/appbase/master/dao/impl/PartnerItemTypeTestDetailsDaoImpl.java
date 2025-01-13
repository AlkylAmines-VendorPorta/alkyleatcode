package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerItemTypeTestDetailsDao;
import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.PartnerItemTypeTestDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class PartnerItemTypeTestDetailsDaoImpl extends AbstractJpaDAO<PartnerItemTypeTestDetails,PartnerItemTypeTestDetailsDto> implements PartnerItemTypeTestDetailsDao{
	@PostConstruct
	private void init() {
		setClazz(PartnerItemTypeTestDetails.class, PartnerItemTypeTestDetailsDto.class);
	}
	
	public String getInfraTypeTestByPartnerAndInfraItem(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select tt from PartnerItemTypeTestDetails tt ")
		.append(" INNER JOIN FETCH tt.partnerInfraItem ii ")
		.append(" INNER JOIN FETCH ii.material m ")
		.append(" INNER JOIN FETCH tt.materialTypeTestDetails ")
		.append(" WHERE tt.partner.bPartnerId=:partnerId ")
		.append(" AND tt.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
	
	public int updateInfraTypeTest(String status,String comment,Long partnerItemTypeTestId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerItemTypeTestDetails tt SET tt.status=:status,tt.remark=:comment ");
		jpql.append(" WHERE tt.partnerItemTypeTestId=:partnerItemTypeTestId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("partnerItemTypeTestId", partnerItemTypeTestId);
		query.setParameter("status", status);
		query.setParameter("comment", comment);
		int count= query.executeUpdate();
		return count;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertDrawingDocByMaterial(PartnerItemTypeTestDetails typeTest) {
		StringBuilder jpql=new StringBuilder(" insert into PartnerItemTypeTestDetails ")
				.append(" (materialTypeTestDetails,partnerInfraItem,isActive,created,updated,createdBy,updatedBy,partner,createdSessionId,updatedSessionId) ")
				.append(" select mt,:partnerInfraItem,'Y',sysdate,sysdate,:user,:user,:partner,:sessionId,:sessionId ")
				.append(" from MaterialTypeTestDetails mt ")
			    .append(" where mt.material.materialId=:materialId ");
				Query query=getEntityManager().createQuery(jpql.toString());
				query.setParameter("partnerInfraItem", typeTest.getPartnerInfraItem());
				query.setParameter("user", typeTest.getCreatedBy());
				query.setParameter("partner",typeTest.getPartner());
				query.setParameter("sessionId",typeTest.getCreatedSessionId());
				query.setParameter("materialId",typeTest.getPartnerInfraItem().getMaterial().getMaterialId());
				return query.executeUpdate();
		}
	
	public String getInfraTypeTestById()
	{
		StringBuilder jpql=new StringBuilder(" Select tt from PartnerItemTypeTestDetails tt ")
		    .append(" LEFT JOIN FETCH tt.partner ptr ")
			.append(" LEFT JOIN FETCH tt.materialTypeTestDetails mt ")
			.append(" LEFT JOIN FETCH tt.partnerInfraItem ii ")
			.append(" LEFT JOIN FETCH ii.material ii ")
			.append(" where tt.partnerItemTypeTestId=:typeTestId ");
		return jpql.toString();
	}
	
	@Override
	public int resetInfraTypeTest(Long infraItemId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerItemTypeTestDetails ia SET ia.status=null,ia.remark=null, ")
		.append(" ia.isEEApproved=null,ia.eeComment=null, ")
		.append(" ia.isSEApproved=null,ia.seComment=null, ")
		.append(" ia.isCEApproved=null,ia.ceComment=null, ")
		.append(" ia.isEDApproved=null,ia.edComment=null, ")
		.append(" ia.isDIRApproved=null,ia.dirComment=null ")
		.append(" WHERE ia.partner.bPartnerId=:partnerId AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", infraItemId);
		query.setParameter("partnerId", partnerId);
		int count= query.executeUpdate();
		return count;
	}
	public String getInfraTypeTestForApproval(){
		StringBuilder jpql=new StringBuilder(" Select t from PartnerItemTypeTestDetails t ")
				.append(" LEFT JOIN FETCH t.materialTypeTestDetails mtt ")
				/*.append(" t.:isApproved='N' OR t.:isApproved is null ")*/
				.append(" where t.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
}
