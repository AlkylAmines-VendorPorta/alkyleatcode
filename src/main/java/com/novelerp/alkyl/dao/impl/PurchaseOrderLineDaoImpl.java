package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PurchaserOrderLineDao;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PurchaseOrderLine;
import com.novelerp.core.dao.impl.AbstractJpaDAO;


@Repository
public class PurchaseOrderLineDaoImpl extends AbstractJpaDAO<PurchaseOrderLine, PurchaseOrderLineDto> implements PurchaserOrderLineDao {

	
	@PostConstruct
	public void postConstruct(){
		setClazz(PurchaseOrderLine.class, PurchaseOrderLineDto.class);
	}
	
	public String getPOLineByPOId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH B.partner D")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE B.purchaseOrderId = :poId AND C.purchaseOrderLineId is null and A.isActive='Y' AND A.deleted!= 'X'")
				.append(" ORDER BY A.lineItemNumber ");
		return jpql.toString();
	}
	
	
	
	
	public String getPOLineByPOLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
//				.append(" LEFT JOIN FETCH B.partner C ")
				.append(" WHERE A.purchaseOrderLineId = :poLineId ");
		return jpql.toString();
	}
	
	public String getPOLineForASNLine(){
		StringBuilder jpql = new StringBuilder("SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y' and ")
				.append(" NOT EXISTS ( SELECT P FROM AdvanceShipmentNoticeLine P ")
				.append(" INNER JOIN P.poLine Q ")
				.append(" WHERE Q.purchaseOrderLineId = A.purchaseOrderLineId ) and C.purchaseOrderLineId is null ")
				.append(" ORDER BY A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getPOLineForASNLineCommercial(){
		StringBuilder jpql = new StringBuilder("SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
//				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y' and ")
				.append(" NOT EXISTS ( SELECT P FROM AdvanceShipmentNoticeLine P ")
				.append(" INNER JOIN P.poLine Q ")
				.append(" WHERE Q.purchaseOrderLineId = A.purchaseOrderLineId ) ")
				.append(" ORDER BY A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServiceForASNLine(){
		StringBuilder jpql = new StringBuilder("SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
				.append(" WHERE C.purchaseOrderLineId = :poLineId and A.isActive='Y' and ")
				.append(" NOT EXISTS ( SELECT P FROM AdvanceShipmentNoticeLine P ")
				.append(" INNER JOIN P.poLine Q ")
				.append(" INNER JOIN P.parentASNLine R ")
				.append(" WHERE Q.purchaseOrderLineId = A.purchaseOrderLineId and R.advanceShipmentNoticeLineId= :asnLineId ) ")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServicesByPOLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
				.append(" WHERE C.purchaseOrderLineId = :poLineId and A.isActive='Y' ")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getoldServiceLineByPOLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
				.append(" WHERE C.purchaseOrderLineId = :poLineId and C.isActive='Y'")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	
	public String getPOLineByPOandMaterialCode(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE A.code = :materialCode and B.purchaseOrderNumber=:poNumber and C.purchaseOrderLineId is null ")
				.append(" ORDER BY A.lineItemNumber ");
		return jpql.toString();
	}
	
//	public String getPOLineByPOandLineItemNumber(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
//				.append(" INNER JOIN FETCH A.purchaseOrder B ")
//				.append(" LEFT JOIN FETCH A.parentPOLine C ")
//				.append(" WHERE A.lineItemNumber = :lineItemNumber and B.purchaseOrderNumber=:poNumber and C.purchaseOrderLineId is null ");
//		return jpql.toString();
//	}
	
	public String getPOLineByPOandLineItemNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH B.partner D ")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE A.lineItemNumber = :lineItemNumber and B.purchaseOrderNumber=:poNumber and C.purchaseOrderLineId is null ");
		return jpql.toString();
	}
	
//	public String getServiceLineByPOandLineItemNumber(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
//				.append(" INNER JOIN FETCH A.purchaseOrder B ")
//				.append(" LEFT JOIN FETCH A.parentPOLine C ")
//				.append(" WHERE C.lineItemNumber = :lineItemNumber and A.lineItemNumber = :serviceItemNumber and B.purchaseOrderNumber=:poNumber and C.purchaseOrderLineId is not null ")
//				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
//		return jpql.toString();
//	}
	/*14-09-2023*/
	public String getServiceLineByPOandLineItemNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH B.partner D ")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE C.lineItemNumber = :lineItemNumber and A.lineItemNumber = :serviceItemNumber and B.purchaseOrderNumber=:poNumber and C.purchaseOrderLineId is not null ")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServiceLineByPOandServiceCode(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
				.append(" WHERE A.code = :materialCode and B.purchaseOrderNumber=:poNumber ")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServicesByPOId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH B.partner D ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
//				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y'")
				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y'  AND A.deleted!= 'X'")
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServiceListByPOIdASNId(){
		StringBuilder jpql = new StringBuilder("SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" INNER JOIN FETCH A.parentPOLine C ")
//				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y' and ")
				.append(" WHERE B.purchaseOrderId = :poId and A.isActive='Y' and A.deleted!= 'X' and ")
				.append(" NOT EXISTS ( SELECT P FROM AdvanceShipmentNoticeLine P ")
				.append(" INNER JOIN P.poLine Q ")
				.append(" INNER JOIN P.advanceshipmentnotice R ")
				.append(" WHERE Q.purchaseOrderLineId = A.purchaseOrderLineId and R.advanceShipmentNoticeId=:asnId) ") 
				.append(" ORDER BY C.lineItemNumber, A.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getPOLineByPONumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrderLine A ")
				.append(" INNER JOIN FETCH A.purchaseOrder B ")
				.append(" LEFT JOIN FETCH B.partner D ")
				.append(" LEFT JOIN FETCH A.parentPOLine C ")
				.append(" WHERE B.purchaseOrderId = :poId and C.purchaseOrderLineId is null ");
		return jpql.toString();
	}

	@Override
	public int DeletepoLine(Long purchaseOrderId){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" DELETE FROM PurchaseOrderLine A ");
		jpql.append( " where A.purchaseOrder.purchaseOrderId = :purchaseOrderId AND A.isActive='Y'");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("purchaseOrderId", purchaseOrderId);
		int count= query.executeUpdate();
		return count;
	}
	

}
