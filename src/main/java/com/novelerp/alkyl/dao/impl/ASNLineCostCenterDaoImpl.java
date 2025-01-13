package com.novelerp.alkyl.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.ASNLineCostCenterDao;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.entity.ASNLineCostCenter;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class ASNLineCostCenterDaoImpl extends AbstractJpaDAO<ASNLineCostCenter, ASNLineCostCenterDto> implements ASNLineCostCenterDao{

	@Override
	public void postConstruct() {
		setClazz(ASNLineCostCenter.class, ASNLineCostCenterDto.class);
	}
	
	public String getasnLineCostCenter(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM ASNLineCostCenter A ")
				//.append(" INNER JOIN FETCH A.partner B ")
				//.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" INNER JOIN FETCH A.asnLine B")
				.append(" INNER JOIN FETCH B.advanceshipmentnotice C")
				.append(" INNER JOIN FETCH C.po F")
				.append(" INNER JOIN FETCH B.partner D ")
				.append(" INNER JOIN FETCH B.poLine E ")
				.append(" INNER JOIN FETCH C.createdBy G ")
				.append(" INNER JOIN FETCH G.userDetails H ")
				.append(" WHERE A.isActive = 'Y' and B.advanceShipmentNoticeLineId=:advanceShipmentNoticeLineId ");
		return jpql.toString();
	}
	
	
	
	@Override
	public int DeleteasnLineCostCenter(Long advanceShipmentNoticeLineId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" DELETE FROM ASNLineCostCenter b ");
		jpql.append( " where b.asnLine.advanceShipmentNoticeLineId=:advanceShipmentNoticeLineId AND b.isActive='Y'");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("advanceShipmentNoticeLineId", advanceShipmentNoticeLineId);
		int count= query.executeUpdate();
		return count;
	}
	
//	public String getasnLineCostCenter(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM ASNLineCostCenter A ")
//				//.append(" INNER JOIN FETCH A.partner B ")
//				//.append(" LEFT JOIN FETCH A.poAtt C ")
//				.append(" INNER JOIN FETCH A.asnLine B")
//				.append(" INNER JOIN FETCH B.advanceshipmentnotice C")
//				.append(" INNER JOIN FETCH C.po F")
//				.append(" INNER JOIN FETCH B.partner D ")
//				.append(" INNER JOIN FETCH B.poLine E ")
//				.append(" INNER JOIN FETCH C.createdBy G ")
//				.append(" INNER JOIN FETCH G.userDetails H ")
//				.append(" WHERE A.isActive = 'Y' and B.advanceShipmentNoticeLineId=:advanceShipmentNoticeLineId and (A.storageLocation=:storageLocation and A.quantity=:quantity)");
//		return jpql.toString();
//	}
	
}
