package com.novelerp.alkyl.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.controller.PurchaseOrderController;
import com.novelerp.alkyl.dao.PurchaseOrderDao;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.PurchaseOrder;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.TenderCommitteeDto;

@Repository
public class PurchaseOrderDaoImpl extends AbstractJpaDAO<PurchaseOrder, PurchaseOrderDto> implements PurchaseOrderDao {

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void postConstruct(){
		
		setClazz(PurchaseOrder.class, PurchaseOrderDto.class);
		
	}
	
	public String getPurchaseOrder(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" WHERE A.isActive = 'Y' ORDER BY A.purchaseOrderNumber DESC ");
		return jpql.toString();
	}
	
	public String getPurchaseOrderbyPartner(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and B.bPartnerId =:partnerId ORDER BY A.purchaseOrderNumber DESC ");
		return jpql.toString();
	}
	
	public String getPurchaseOrderByStatus(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.status = '"+AppBaseConstant.PO_STATUS_ACCEPTED+"'");
		return jpql.toString();
	}
	
	public String getPurchaseOrderByPONo(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.status = '"+AppBaseConstant.PO_STATUS_ACCEPTED+"'");
		return jpql.toString();
	}
	
//	public String getPObyNumber(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
//				.append(" INNER JOIN FETCH A.partner B ")
//				.append(" LEFT JOIN FETCH A.poAtt C ")
//				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderNumber=:poNumber ");
//		return jpql.toString();
//	}
	
	
	public String getPObyNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" LEFT JOIN FETCH A.partner B ")
			//	.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderNumber=:poNumber ");
		return jpql.toString();
	}
	
	
	public Long getAcceptedPobyPartnerId(Long partnerId){
		StringBuilder query = new StringBuilder(" SELECT COUNT(A) FROM PurchaseOrder A ")
				.append(" INNER JOIN  A.partner B ")
				.append(" WHERE A.partner.bPartnerId =:partnerId and A.isActive = 'Y' and A.status = '"+AppBaseConstant.PO_STATUS_ACCEPTED+"' ");
		Query q=em.createQuery(query.toString());
		q.setParameter("partnerId",partnerId);
		Long x=(Long) q.getSingleResult();
		 return x;
	}
	
	public Long getRejectedPobyPartnerId(Long partnerId){
		StringBuilder query = new StringBuilder(" SELECT COUNT(A) FROM PurchaseOrder A ")
				.append(" INNER JOIN  A.partner B ")
				.append(" WHERE A.partner.bPartnerId = :partnerId and A.isActive = 'Y' and A.status = '"+AppBaseConstant.PO_STATUS_REJECTED+"' ");
		Query q=em.createQuery(query.toString());
		q.setParameter("partnerId",partnerId);
		Long x=(Long) q.getSingleResult();
		 return x;
	}
	public Long getReleasedPobyPartnerId(Long partnerId){
		StringBuilder query = new StringBuilder(" SELECT COUNT(A) FROM PurchaseOrder A ")
				.append(" INNER JOIN  A.partner B ")
				.append(" WHERE A.partner.bPartnerId = :partnerId and A.isActive = 'Y' and A.status = '"+AppBaseConstant.PO_STATUS_RELEASED+"'" );
		Query q=em.createQuery(query.toString());
		q.setParameter("partnerId",partnerId);
		Long x=(Long) q.getSingleResult();
		 return x;
	}
	
	public Long getPobyPartnerId(Long partnerId){
		StringBuilder query = new StringBuilder(" SELECT COUNT(A) FROM PurchaseOrder A ")
				.append(" INNER JOIN  A.partner B ")
				.append(" WHERE A.partner.bPartnerId = :partnerId   and A.isActive = 'Y'");
		Query q=em.createQuery(query.toString());
		q.setParameter("partnerId",partnerId);
		Long x=(Long) q.getSingleResult();
		 return x;
	}
	
	public String getPurchaseOrderbyPoId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderId =:purchaseOrderId ");
		return jpql.toString();
	}
	public String getPurchaseOrderbyStatus(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and B.bPartnerId =:partnerId and A.status=:status");
		return jpql.toString();
	}
	public String geOpentPurchaseOrder(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and B.bPartnerId =:partnerId and A.status !=:status");
		return jpql.toString();
	}
	public String getPOByPONO(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderNumber=:poNumber ");
		return jpql.toString();
	}
	
	public String getPurchaseOrderbyPoNoFilter(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderNumber BETWEEN :from AND :to ");
		return jpql.toString();
	}
	public String getPurchaseOrderbyPoDateFilter(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.date BETWEEN :from AND :to ");
		return jpql.toString();
	}
	public String getPurchaseOrderbyVendorFilter(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.vendorCode BETWEEN :from AND :to ");
		return jpql.toString();
	}
	public String getPurchaseOrderbyEmployeeFilter(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.reqby D ")
				.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.reqby.userName =:id ");
		return jpql.toString();
	}
	public String getPObyPONumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				.append(" LEFT JOIN FETCH A.reqby B ")
				.append(" WHERE A.isActive = 'Y' and A.purchaseOrderNumber=:poNumber ");
		return jpql.toString();
	}
	
	public String getPObyoutdeliveryNo(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ")
				
				.append(" WHERE A.isActive = 'Y' and A.outboundDeliveryNo=:poNumber ");
		return jpql.toString();
	}

	@Override
	public String getPOByFilter(POReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PurchaseOrder A ");
				jpql.append(" LEFT JOIN FETCH A.partner B ");
				jpql.append(" LEFT JOIN FETCH A.reqby D ");
				jpql.append(" LEFT JOIN FETCH A.poAtt C ");
				String where =  getWhereClause(dto);
		   		jpql.append(where);
		return jpql.toString();
	}
 	private String getWhereClause(POReadDto dto){
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' ");
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
   			where.append(" AND A.purchaseOrderNumber BETWEEN :fromNo AND :toNo ");
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			where.append(" AND A.purchaseOrderNumber =:fromNo ");
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			where.append(" AND A.purchaseOrderNumber =:toNo ");
   		}
   		if(dto.getPoDateFrom()!=null && dto.getPoDateTo()!=null){
   			where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
   		}
   		if(dto.getPoDateFrom()!=null && dto.getPoDateTo()==null){
   			where.append(" AND A.date =:fromDate ");
   		}
   		if(dto.getPoDateFrom()==null && dto.getPoDateTo()!=null){
   			where.append(" AND A.date =:toDate ");
   		}
   		if(dto.getEmpCode()!=null && dto.getVendorCode()!=null){
   			where.append(" AND A.reqby.userName =:id AND A.vendorCode =:vendorCode ");
   		}
   		if(dto.getEmpCode()!=null && dto.getVendorCode()==null){   			
   			where.append(" AND A.reqby.userName =:id ");
   		}
   		if(dto.getEmpCode()==null && dto.getVendorCode()!=null){   			
   			where.append("  AND A.vendorCode =:vendorCode ");
   		}
   		if(dto.getIsVendorFilter()){
   			where.append(" AND B.bPartnerId =:bid ");
   		}
   		if(dto.getOutboundDeliveryNo() != null){
   			where.append(" AND A.outboundDeliveryNo =:outboundDeliveryNo ");
   			//params.put("outboundDeliveryNo", dto.getOutboundDeliveryNo());
   		}
   		return where.toString();
 	}
}
