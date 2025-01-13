package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.PartnerInfraItemApprovalDao;
import com.novelerp.eat.dto.PartnerInfraItemApprovalDto;
import com.novelerp.eat.entity.PartnerInfraItemApproval;

@Repository
public class PartnerInfraItemApprovalDaoImpl extends AbstractJpaDAO<PartnerInfraItemApproval, PartnerInfraItemApprovalDto> implements PartnerInfraItemApprovalDao {

	@PostConstruct
	private void init() {
		setClazz(PartnerInfraItemApproval.class, PartnerInfraItemApprovalDto.class);
	}
	
	public String getInfraApprovalLinePartnerAndInfraItem(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ia from PartnerInfraItemApproval ia ")
		.append(" INNER JOIN FETCH ia.infraApprovalLevel al ")
		.append(" INNER JOIN FETCH ia.partnerInfraItem ii ")
		.append(" INNER JOIN FETCH ii.material m ")
		.append(" LEFT JOIN FETCH ii.attachment a ")
		.append(" WHERE ia.partner.bPartnerId=:partnerId ")
		.append(" AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
	
	public String getInfraApprovalLineByApprovalLevel(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ia from PartnerInfraItemApproval ia ")
		.append(" INNER JOIN FETCH ia.infraApprovalLevel al ")
		.append(" INNER JOIN FETCH ia.partnerInfraItem ii ")
		.append(" LEFT JOIN FETCH ii.attachment a ")
		.append(" INNER JOIN FETCH ii.material m ")
		.append(" INNER JOIN FETCH ia.partner p ")
		.append(" WHERE ia.partner.bPartnerId=:partnerId ")
		.append(" AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId AND ii.levelNo=al.level");
		return jpql.toString();
	}
	
	public String getInfraApprovalLine(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ia from PartnerInfraItemApproval ia ")
		.append(" INNER JOIN FETCH ia.infraApprovalLevel al ")
		.append(" INNER JOIN FETCH al.role alr ")
		.append(" INNER JOIN FETCH ia.partnerInfraItem ii ")
		.append(" LEFT JOIN FETCH ii.attachment a ")
		.append(" INNER JOIN FETCH ii.material m ")
		.append(" WHERE ia.partner.bPartnerId=:partnerId ")
		.append(" AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId AND alr.value=:roleName ");
		return jpql.toString();
	}
	
	public String getPrevoiusInfraApprovalLine(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select ia from PartnerInfraItemApproval ia ")
		.append(" INNER JOIN FETCH ia.infraApprovalLevel al ")
		.append(" INNER JOIN FETCH al.role alr ")
		.append(" INNER JOIN FETCH ia.partnerInfraItem ii ")
		.append(" LEFT JOIN FETCH ii.attachment a ")
		.append(" WHERE ia.partner.bPartnerId=:partnerId ")
		.append(" AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId AND al.level_no = (SELECT il.level +1 FROM InfraApprovalLevel il WHERE il.role.value=:roleName)");
		return jpql.toString();
	}
	
	@Override
	public int saveInfraApprovalLine(String status,String comment,Long infraItemId,Long partnerId,String roleName){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerInfraItemApproval ia SET ia.isApproved=:isApproved,ia.comment=:comment ");
		jpql.append(" WHERE ia.partner.bPartnerId=:partnerId AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId"
				+ " AND ia.infraApprovalLevel.infraApprovalLevelId=(SELECT il.infraApprovalLevelId FROM InfraApprovalLevel il where il.role.value=:roleName) ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", infraItemId);
		query.setParameter("partnerId", partnerId);
		query.setParameter("roleName", roleName);
		query.setParameter("isApproved", status);
		query.setParameter("comment", comment);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public int resetInfraApprovalLine(Long infraItemId,Long partnerId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerInfraItemApproval ia SET ia.isApproved=null,ia.comment=null ");
		jpql.append(" WHERE ia.partner.bPartnerId=:partnerId AND ia.partnerInfraItem.partnerInfraItemId=:infraItemId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", infraItemId);
		query.setParameter("partnerId", partnerId);
		int count= query.executeUpdate();
		return count;
	}
}
