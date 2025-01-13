package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerInfraItemDao;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.entity.PartnerInfraItem;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class PartnerInfraItemDaoImpl extends AbstractJpaDAO<PartnerInfraItem, PartnerInfraItemDto> implements PartnerInfraItemDao{
	@PostConstruct
	private void init() {
		setClazz(PartnerInfraItem.class, PartnerInfraItemDto.class);
	}
	
	public String getInfraItemQuery(){
		StringBuilder jpql=new StringBuilder(" Select pi from PartnerInfraItem pi ")
		.append(" LEFT JOIN FETCH pi.partner p ")
		.append(" LEFT JOIN FETCH pi.material m ")
		.append(" LEFT JOIN FETCH m.uom u ")
		.append(" LEFT JOIN FETCH m.hsnCode hc ")
		.append(" LEFT JOIN FETCH pi.attachment at ")
		.append(" where pi.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	@Override
	public int uploadClarification(Long infraItemId,Long partnerId,Long attachmentId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PartnerInfraItem b SET b.attachment.attachmentId=:attachmentId,b.status=:status ")
		.append(" ,b.levelNo=(select min(al.level) from InfraApprovalLevel al ) ");
		jpql.append( " where b.partnerInfraItemId=:infraItemId AND b.partner.bPartnerId=:partnerId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", infraItemId);
		query.setParameter("partnerId", partnerId);
		query.setParameter("attachmentId", attachmentId);
		query.setParameter("status",AppBaseConstant.INFRA_IN_PROGRESS);
		int count= query.executeUpdate();
		return count;
	}
	public String getInfraItemById(){
		StringBuilder jpql=new StringBuilder(" Select i from PartnerInfraItem i ")
				.append(" INNER JOIN FETCH i.material m ")
				.append(" WHERE i.partnerInfraItemId=:infraItemId ")
				.append(" AND EXISTS (SELECT md FROM MaterialDrawingDocument md WHERE md.material.materialId=m.materialId) ");
		return jpql.toString();
	}
	public String getQueryForInfraItemById(){
		StringBuilder jpql=new StringBuilder(" Select pi from PartnerInfraItem pi ")
		.append(" LEFT JOIN FETCH pi.partner p ")
		.append(" LEFT JOIN FETCH pi.material m ")
		.append(" LEFT JOIN FETCH m.uom u ")
		.append(" LEFT JOIN FETCH m.hsnCode hc ")
		.append(" LEFT JOIN FETCH pi.attachment at ")
		.append(" where pi.partnerInfraItemId=:infraItemId ");
		return jpql.toString();
	}
	public String getInfraItemForApproval(){
		StringBuilder jpql=new StringBuilder(" Select pi from PartnerInfraItem pi ")
		.append(" LEFT JOIN FETCH pi.partner p ")
		.append(" LEFT JOIN FETCH pi.material m ")
		.append(" LEFT JOIN FETCH m.uom u ")
		.append(" LEFT JOIN FETCH m.hsnCode hc ")
		.append(" LEFT JOIN FETCH pi.attachment at ")
		.append(" where pi.partner.bPartnerId=:partnerId AND pi.levelNo=:level AND pi.status=:status");
		return jpql.toString();
	}

	@Override
	public int updateInfraItemLevel(PartnerInfraItemDto dto, RoleDto role) {
		StringBuilder jpql=new StringBuilder(" Update PartnerInfraItem pi set ")
		.append(" pi.status=:status, ")
		.append(" pi.levelNo=(select min(al.level) from InfraApprovalLevel al where al.level>pi.levelNo) ")
		.append(" where pi.partnerInfraItemId=:infraItemId AND pi.partner.bPartnerId=:partnerId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("infraItemId", dto.getPartnerInfraItemId());
		query.setParameter("partnerId", dto.getPartner().getbPartnerId());
		query.setParameter("status",dto.getStatus());
		int count= query.executeUpdate();
		return count;
	}

	@Override
	public int updateInfraItemStaus(PartnerInfraItemDto dto, RoleDto role) {
		StringBuilder jpql=new StringBuilder(" Update PartnerInfraItem pi set ")
				.append(" pi.status=:status ")
				.append(" where pi.partnerInfraItemId=:infraItemId AND pi.partner.bPartnerId=:partnerId ");
				Query query=getEntityManager().createQuery(jpql.toString());
				query.setParameter("infraItemId", dto.getPartnerInfraItemId());
				query.setParameter("partnerId", dto.getPartner().getbPartnerId());
				query.setParameter("status",dto.getStatus());
				int count= query.executeUpdate();
	   return count;
	}
	public String getInfraQueryForDuplicateCheck(){
		StringBuilder jpql=new StringBuilder(" select i from PartnerInfraItem i ")
		.append(" where i.material.materialId=:materialId and i.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
}
