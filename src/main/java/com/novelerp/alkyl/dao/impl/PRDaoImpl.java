package com.novelerp.alkyl.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PRDao;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRReadDto;
import com.novelerp.alkyl.entity.PR;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class PRDaoImpl extends AbstractJpaDAO<PR, PRDto> implements PRDao {

	@Override
	public void postConstruct() {
		setClazz(PR.class, PRDto.class);
	}
	
	@Override
	public String getPRByPRNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PR A ")
		.append(" LEFT JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ");
		jpql.append(" WHERE A.prNumber=:prNo and A.isActive='Y' ");
		return jpql.toString();
	}
	
	@Override
	public String getPR(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PR A ")
		.append(" INNER JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ")
		.append(" WHERE A.isActive='Y' ");
		jpql.append( " ORDER BY A.prId DESC");
		return jpql.toString();
	}
	
	
	public String getPRbyRole(){
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM PR A ")
		.append(" INNER JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ")
		.append(" LEFT JOIN FETCH A.releasedBy J ")
		.append(" INNER JOIN FETCH PRLine I ON (I.pr.prId=A.prId) ")
		.append("  WHERE A.isActive='Y' and I.isActive='Y' and I.plant=:plant and A.status IN (:status) ");
		jpql.append( " ORDER BY A.prId DESC");
		return jpql.toString();
	}
	
	public String getPRforEnquiry(){
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM PR A ")
		.append(" INNER JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ")
		.append(" INNER JOIN FETCH Bidder I ON (I.pr.prId=A.prId) ")
		.append(" INNER JOIN FETCH ItemBid J ON (J.bidder.bidderId=I.bidderId) ")
		.append(" INNER JOIN FETCH PRLine K ON (K.pr.prId=A.prId) ")
		.append("  WHERE A.isActive='Y' and K.plant=:plant ");
		jpql.append( " ORDER BY A.prId DESC");
		return jpql.toString();
	}
	
	public String getPRforEnquiryAdmin(){
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM PR A ")
		.append(" INNER JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ")
		.append(" INNER JOIN FETCH Bidder I ON (I.pr.prId=A.prId) ")
		.append(" INNER JOIN FETCH PRLine K ON (K.pr.prId=A.prId) ")
		.append("  WHERE A.isActive='Y' ");
		jpql.append( " ORDER BY A.prId DESC");
		return jpql.toString();
	}
	
	
	public String getPRForBidderApproval(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PR A ")
		.append(" INNER JOIN FETCH A.partner B ")
		.append(" LEFT JOIN FETCH A.requestedBy C ")
		.append(" LEFT JOIN FETCH A.tcApprover D ")
		.append(" LEFT JOIN FETCH A.approvedBy E ")
		.append(" LEFT JOIN FETCH A.buyer F ")
		.append(" LEFT JOIN FETCH A.createdBy G ")
		.append(" LEFT JOIN FETCH C.approver H ")
		.append(" WHERE A.isActive='Y' AND :currentDate>=A.bidEndDate ");
		jpql.append( " ORDER BY A.prId DESC");
		return jpql.toString();
	}

	@Override
	public List<Object> getQCFReport(Long prId) {
		// TODO Auto-generated method stub
		StringBuilder jpql = new StringBuilder(" select e.t_bidder_id,f.vendor_sap_code,line_number,material_code,material_desc,rate,total_freight,"
				+ "igst_amount,cgst_amount,sgst_amount,gross_amt,basic_amt,allocated_qty,split_basic_value,split_gross_value,split_landed_cost from t_price_bid a ")
				.append(" inner join t_item_bid b on (b.t_item_bid_id=a.t_item_bid_id) ")
				.append(" inner join t_pr_line c on (c.t_pr_line_id=b.t_pr_line_id) ")
				.append(" inner join t_pr d on (d.t_pr_id=c.t_pr_id) ")
				.append(" inner join t_bidder e on (e.t_bidder_id=b.t_bidder_id and c.t_pr_line_id=b.t_pr_line_id) ")
				.append(" inner join m_bpartner f on (f.m_bpartner_id=e.m_bpartner_id) ")
				.append(" inner join t_winner_selection g on (g.t_item_bid_id=b.t_item_bid_id) ")
				.append(" where d.t_pr_id="+prId);
		Query query=getEntityManager().createNativeQuery(jpql.toString());
		@SuppressWarnings("unchecked")
		List<Object> data= (List<Object>) query.getResultList();
		return data;
	}

	@Override
	public String getPRByFilter(PRReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM PR A ")
				.append(" INNER JOIN FETCH A.partner B ")
				.append(" LEFT JOIN FETCH A.requestedBy C ")
				.append(" LEFT JOIN FETCH A.tcApprover D ")
				.append(" LEFT JOIN FETCH A.approvedBy E ")
				.append(" LEFT JOIN FETCH A.createdBy G ")
				.append(" LEFT JOIN FETCH C.approver H ")
				.append(" LEFT JOIN FETCH A.releasedBy J ")
				.append(" INNER JOIN FETCH PRLine I ON (I.pr.prId=A.prId) ");
				String where =  getWhereClause(dto);
		   		jpql.append(where);
		return jpql.toString();
	}
	private String getWhereClause(PRReadDto dto){
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' ");
   		
   		if(dto.getPrNoFrom()!=null && dto.getPrNoTo()!=null){
   			where.append(" AND A.prNumber BETWEEN :fromNo AND :toNo ");
   		}
   		if(dto.getPrNoFrom()!=null && dto.getPrNoTo()==null){
   			where.append(" AND A.prNumber =:fromNo ");
   		}
   		if(dto.getPrNoFrom()==null && dto.getPrNoTo()!=null){
   			where.append(" AND A.prNumber =:toNo ");
   		}
   		if(dto.getPrDateFrom()!=null && dto.getPrDateTo()!=null){
//   			where.append("  AND A.date BETWEEN :fromDate AND :toDate ");
   			where.append("  AND A.releasedDate BETWEEN :prDateFrom AND :prDateTo ");
   		}
   		if(dto.getPrDateFrom()!=null && dto.getPrDateTo()==null){
//   			where.append(" AND A.date =:fromDate ");
   			where.append(" AND A.releasedDate =:prDateFrom ");
   		}
   		if(dto.getPrDateFrom()==null && dto.getPrDateTo()!=null){
//   			where.append(" AND A.date =:toDate ");
   			where.append(" AND A.releasedDate =:prDateTo ");
   		}
   		if(dto.getStatus()!=null){
   			where.append(" AND A.status =:status ");
   		}
   		if(dto.getBuyerCode()!=null){
   			where.append(" AND C.userName =:buyer ");
   		}
   		if(dto.getPlant()!=null){
   			where.append(" AND I.plant =:plant ");
   		}
   		return where.toString();
 	}
	
	
	public String getPlainPRByPRNumber(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PR A ");
		jpql.append(" WHERE A.prNumber=:prNo and A.isActive='Y' ");
		return jpql.toString();
	}
}
