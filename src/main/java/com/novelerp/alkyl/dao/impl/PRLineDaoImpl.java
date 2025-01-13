package com.novelerp.alkyl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PRLineDao;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dto.TenderCommitteeDto;

@Repository
public class PRLineDaoImpl extends AbstractJpaDAO<PRLine, PRLineDto> implements PRLineDao {

	@Override
	public void postConstruct() {
		// TODO Auto-generated method stub
		setClazz(PRLine.class, PRLineDto.class);
	}
	
	
	public String getPRLinebyPrId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" LEFT JOIN FETCH A.pr B ")
		.append(" LEFT JOIN FETCH A.fixedVendor C ")
		.append(" LEFT JOIN FETCH A.desiredVendor D ")
		.append(" LEFT JOIN FETCH A.buyer E ")
		.append(" LEFT JOIN FETCH B.createdBy F ")
		.append(" LEFT JOIN FETCH B.releasedBy G ")
		.append(" WHERE A.pr.prId=:prId and A.pr.isActive='Y' and A.isActive='Y' ");
		jpql.append( " ORDER BY A.prLineNumber ASC");
		return jpql.toString();
	}
	
	
	public String getUnAssignedPrLineEnquiry(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" WHERE NOT EXISTS ")
			.append(" ( SELECT B FROM ItemBid B ")
				.append(" INNER JOIN Bidder C ON (B.bidder.bidderId=C.bidderId) ")
				.append(" WHERE C.bidderId=:bidderId AND B.prLine.prLineId=A.prLineId )")
		.append(" and A.pr.prId=:prId and A.pr.isActive='Y' ");
		jpql.append( " ORDER BY A.prLineNumber ASC");
		return jpql.toString();
	}
	
	
	public String getQcfPRLineByPrId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" INNER JOIN FETCH A.pr B ")
		.append(" WHERE A.pr.prId=:prId and A.pr.isActive='Y' ");
		jpql.append( " ORDER BY A.prLineNumber ASC");
		return jpql.toString();
	}
	public String getPRLineByRole(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" LEFT JOIN FETCH A.pr B ")
		.append(" LEFT JOIN FETCH A.fixedVendor C ")
		.append(" LEFT JOIN FETCH A.desiredVendor D ")
		.append(" LEFT JOIN FETCH A.buyer E ")
		.append(" INNER JOIN FETCH B.partner F ")
		.append(" LEFT JOIN FETCH B.requestedBy G ")
		.append(" LEFT JOIN FETCH B.tcApprover H ")
		.append(" LEFT JOIN FETCH B.approvedBy I ")
		.append(" LEFT JOIN FETCH B.buyer J ")
		.append(" LEFT JOIN FETCH B.createdBy K ")
		.append(" LEFT JOIN FETCH G.approver L ")
		.append(" WHERE B.status IN (:status)  and A.pr.isActive='Y' and A.isActive='Y' ");
//		jpql.append(" Order By A.pr.prId DESC");
	//	jpql.append( " ORDER BY A.prLineNumber ASC ");
		jpql.append( " ORDER BY A.prLineNumber ASC , A.pr.releasedDate ASC");
	//	jpql.append( " ORDER BY A.pr.releasedDate ASC");
		return jpql.toString();
	}


	@Override
	public String getPRLineByFilterQuery(PRLineFilterDto prLineDto) {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
				.append(" LEFT JOIN FETCH A.pr B ")
				.append(" LEFT JOIN FETCH A.fixedVendor C ")
				.append(" LEFT JOIN FETCH A.desiredVendor D ")
				.append(" LEFT JOIN FETCH A.buyer E ")
				.append(" INNER JOIN FETCH B.partner F ")
				.append(" LEFT JOIN FETCH B.requestedBy G ")
				.append(" LEFT JOIN FETCH B.tcApprover H ")
				.append(" LEFT JOIN FETCH B.approvedBy I ")
				.append(" LEFT JOIN FETCH B.buyer J ")
				.append(" LEFT JOIN FETCH B.createdBy K ")
				.append(" LEFT JOIN FETCH G.approver L ");
		       
				String where =  getWhereClause(prLineDto);
		        
		   		jpql.append(where);
		   		jpql.append( " ORDER BY A.prLineNumber ASC ");
				return jpql.toString();
	}
	private String getWhereClause(PRLineFilterDto prLineDto){
	StringBuilder where = new StringBuilder();
	where.append(" WHERE A.isActive ='Y' and A.pr.isActive='Y' ");
	//	where.append(" WHERE A.isActive ='Y' and B.status='" + AppBaseConstant.PR_STATUS_ACCEPTED + "' and A.pr.isActive='Y' ");
		/*where.append(" WHERE b.partner.bPartnerId=:partnerId ");*/	
//		if(prLineDto.getPlant()!=null) {
//			where.append(" AND A.plant=:plant ");
//		}
//		if(prLineDto.getUserId()!=null){
//			where.append(" AND A.buyer.userId=:userID ");
//		}
		if(prLineDto.getPrNoFrom()!=null && prLineDto.getPrNoTo()!=null){
   			where.append(" AND B.prNumber BETWEEN :prNoFrom AND :prNoTo ");
   		}
   		if(prLineDto.getPrNoFrom()!=null && prLineDto.getPrNoTo()==null){
   			where.append(" AND B.prNumber =:prNoFrom ");
   		}
   		
   		if(prLineDto.getPrNoFrom()==null && prLineDto.getPrNoTo()!=null){
   			where.append(" AND B.prNumber =:prNoTo ");
   		}
		
		if(prLineDto.getPrDateFrom()!=null && prLineDto.getPrDateTo()!=null){
			where.append(" AND B.releasedDate BETWEEN :prDateFrom AND :prDateTo ");
//   			where.append(" AND B.date BETWEEN :prDateFrom AND :prDateTo ");
   		}
   		if(prLineDto.getPrDateFrom()!=null && prLineDto.getPrDateTo()==null){
   			where.append(" AND B.releasedDate =:prDateFrom ");
//   			where.append(" AND B.date =:prDateFrom ");
   		}
   		if(prLineDto.getPrDateFrom()==null && prLineDto.getPrDateTo()!=null){
   			where.append(" AND B.releasedDate =:prDateTo ");
//   			where.append(" AND B.date =:prDateTo ");
   		}
   		
   		if(prLineDto.getPurchaseGroupFrom()!=null && prLineDto.getPurchaseGroupTo()!=null){
//   			where.append(" AND A.purchaseGroup BETWEEN :purchaseGroupFrom AND :purchaseGroupTo");
   			where.append(" AND E.purchaseGroup BETWEEN :purchaseGroupFrom AND :purchaseGroupTo");
   		}
   		
   		if(prLineDto.getPurchaseGroupFrom()!=null && prLineDto.getPurchaseGroupTo()==null){   			
//   			where.append(" AND A.purchaseGroup =:purchaseGroupFrom ");
   			where.append(" AND E.purchaseGroup =:purchaseGroupFrom ");
   		}
   		
   		if(prLineDto.getPurchaseGroupFrom()==null && prLineDto.getPurchaseGroupTo()!=null){
//   			where.append(" AND A.purchaseGroup =:purchaseGroupTo ");
   			where.append(" AND E.purchaseGroup =:purchaseGroupTo ");
   		}
   		
   		if(prLineDto.getStatus()!=null){
   			where.append(" AND B.status =:status ");
   		}
   		
   		if(prLineDto.getPlant()!=null){
   			where.append(" AND A.plant =:plant ");
   		}

		return where.toString();
	}
	public String getPRLineForBuyer(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
				.append(" LEFT JOIN FETCH A.pr B ")
				.append(" LEFT JOIN FETCH A.fixedVendor C ")
				.append(" LEFT JOIN FETCH A.desiredVendor D ")
				.append(" LEFT JOIN FETCH A.buyer E ")
				.append(" INNER JOIN FETCH B.partner F ")
				.append(" LEFT JOIN FETCH B.requestedBy G ")
				.append(" LEFT JOIN FETCH B.tcApprover H ")
				.append(" LEFT JOIN FETCH B.approvedBy I ")
				.append(" LEFT JOIN FETCH B.buyer J ")
				.append(" LEFT JOIN FETCH B.createdBy K ")
				.append(" LEFT JOIN FETCH G.approver L ")
				.append(" LEFT JOIN FETCH B.releasedBy M ")
		.append(" WHERE A.isActive='Y' and A.pr.isActive='Y' and A.pr.status='BA' ");
		jpql.append( " ORDER BY B.releasedDate ASC, A.prLineNumber ASC");
//		jpql.append( " ORDER BY B.prNumber ASC, A.prLineNumber ASC");
		return jpql.toString();
	}
	public String getPRLineByPRANDLINE(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" LEFT JOIN FETCH A.pr B ")
//		.append(" LEFT JOIN FETCH B.partner C ")
//		.append(" LEFT JOIN FETCH A.fixedVendor C ")
//		.append(" LEFT JOIN FETCH A.desiredVendor D ")
		.append(" LEFT JOIN FETCH A.buyer E ")
		.append(" LEFT JOIN FETCH B.createdBy K ")
		.append(" LEFT JOIN FETCH B.approvedBy I ")
//		.append(" INNER JOIN FETCH B.partner F ")
//		.append(" LEFT JOIN FETCH B.requestedBy G ")
//		.append(" LEFT JOIN FETCH B.tcApprover H ")
//		.append(" LEFT JOIN FETCH B.approvedBy I ")
//		.append(" LEFT JOIN FETCH B.buyer J ")
//		.append(" LEFT JOIN FETCH B.createdBy K ")
//		.append(" LEFT JOIN FETCH G.approver L ")
//		.append(" WHERE B.prId=:prId  and B.isActive='Y' and A.prLineNumber=:lineNo and A.isActive='Y' ");
		.append(" WHERE B.prNumber=:prNumber  and B.isActive='Y' and A.prLineNumber=:lineNo and A.isActive='Y' ")
		.append("  ORDER BY A.pr.releasedDate ASC,  A.prLineNumber ASC");
	//	.append("  ORDER BY B.releasedDate ASC, A.prLineNumber ASC");
		return jpql.toString();
	}
	
	public String getPRLineByPRANDLINEorBuyer(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
		.append(" INNER JOIN FETCH A.pr B ")
		.append(" LEFT JOIN FETCH A.buyer E ")	
		.append(" LEFT JOIN FETCH B.createdBy K ")
		.append(" LEFT JOIN FETCH B.approvedBy I ")
	//	.append(" WHERE E.purchaseGroup =:purchaseGroup and B.isActive='Y' and A.isActive='Y'");
		.append(" WHERE B.prNumber=:prNumber and E.purchaseGroup =:purchaseGroup and B.isActive='Y' and A.prLineNumber=:lineNo and A.isActive='Y'");
	//	jpql.append("  ORDER BY B.releasedDate ASC ");
		return jpql.toString();
	}
	
	
	public String getServiceLineByPRandLineItemNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PRLine A ")
				.append(" LEFT JOIN FETCH A.pr B ")
//				.append(" LEFT JOIN FETCH B.partner C ")
//				.append(" LEFT JOIN FETCH A.fixedVendor C ")
//				.append(" LEFT JOIN FETCH A.desiredVendor D ")
				.append(" LEFT JOIN FETCH A.buyer E ")
//				.append(" INNER JOIN FETCH B.partner F ")
//				.append(" LEFT JOIN FETCH B.requestedBy G ")
//				.append(" LEFT JOIN FETCH B.tcApprover H ")
//				.append(" LEFT JOIN FETCH B.approvedBy I ")
//				.append(" LEFT JOIN FETCH B.buyer J ")
//				.append(" LEFT JOIN FETCH B.createdBy K ")
//				.append(" LEFT JOIN FETCH G.approver L ")
				.append(" WHERE A.lineNumber = :lineNumber and B.prNumber=:prNumber");
		
				//.append(" ORDER BY A.prLineNumber ");
				
				//.append(" LEFT JOIN FETCH A.parentPRLine C ")
				//.append(" WHERE C.lineNumber = :lineNumber and A.prLineNumber = :prLineNumber and B.prNumber=:prNumber and C.prLineId is not null")
				//.append("  ORDER BY C.lineNumber, A.prLineNumber ");
		return jpql.toString();
	}
	
	


}
