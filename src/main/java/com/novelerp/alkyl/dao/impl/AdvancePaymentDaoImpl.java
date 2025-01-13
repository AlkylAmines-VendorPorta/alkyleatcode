package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.AdvancePaymentDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.PaymentReadDto;
import com.novelerp.alkyl.entity.AdvancePayment;

import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AdvancePaymentDaoImpl extends AbstractJpaDAO<AdvancePayment, AdvancePaymentDto>
implements AdvancePaymentDao{
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void postConstruct() {

		setClazz(AdvancePayment.class, AdvancePaymentDto.class);
	}

	
	public String getpaymentbydocNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
				.append(" LEFT JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.partner C ")
			//	.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.documentNumber=:docNumber ");
		return jpql.toString();
	}


//	@Override
//	public String getAllpaymentdetails() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	public String getAllpayment(){
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
//				.append(" LEFT JOIN FETCH A.createdBy B ")
//				.append(" INNER JOIN FETCH A.partner C ")
//			//	.append(" LEFT JOIN FETCH A.poAtt C ")
//				.append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
//		return jpql.toString();
//	}
	
	@Override
	public String getAllpaymentdetails(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
				.append(" LEFT JOIN FETCH A.createdBy B ")
				.append(" LEFT JOIN FETCH A.partner C ")
			//	.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
		return jpql.toString();
	}
	
	
	public String getAllpaymentbyPartner(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
				.append(" INNER JOIN FETCH A.partner B ")
				
				.append(" WHERE A.isActive = 'Y' and B.bPartnerId =:partnerId and A.status IN (:status)");
		return jpql.toString();
	}
	
	
	public String getPaymentReportlist(PaymentReadDto dto) {
		         StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvancePayment A ")
				    .append(" LEFT JOIN FETCH A.partner p")
	                .append(" LEFT JOIN FETCH A.createdBy B ")
		            .append(" LEFT JOIN FETCH B.userDetails C ")
		            .append(" LEFT JOIN FETCH A.approvedBy D ")
		            .append(" LEFT JOIN FETCH D.userDetails E ");
       
		
		String where =  getWhereClause(dto);
		jpql.append(where);

		return jpql.toString();
	}
	private String getWhereClause(PaymentReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y'");
   		
   		if(dto.getVendor()!=null && !dto.getVendor().equals("")){
   			where.append(" AND A.vendorCode =:vendor or LOWER(A.vendorName) like CONCAT('%',LOWER(:vendor),'%')");
   		}
		
		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			where.append(" AND A.status =:status ");
   		}
		
	   		
   		return where.toString();
	}
	
	
	public String getpaymentbyadvancePaymentId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
				.append(" LEFT JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.partner C ")
			//	.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.advancePaymentId=:advancePaymentId ");
		return jpql.toString();
	}

	
	public String getPaymentByVendorCode(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvancePayment A ")
				.append(" LEFT JOIN FETCH A.createdBy B ")
				.append(" INNER JOIN FETCH A.partner C ")
			//	.append(" LEFT JOIN FETCH A.poAtt C ")
				.append(" WHERE A.isActive = 'Y' and A.vendorCode=:vendorCode ");
		return jpql.toString();
	}
	
}
