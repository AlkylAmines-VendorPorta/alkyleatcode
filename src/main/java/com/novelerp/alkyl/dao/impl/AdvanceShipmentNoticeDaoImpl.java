package com.novelerp.alkyl.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.AdvanceShipmentNoticeDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AdvanceShipmentNoticeDaoImpl extends AbstractJpaDAO<AdvanceShipmentNotice, AdvanceShipmentNoticeDto>
		implements AdvanceShipmentNoticeDao {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void postConstruct() {

		setClazz(AdvanceShipmentNotice.class, AdvanceShipmentNoticeDto.class);
	}
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	public String getASN() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH A.invoice D ").append(" WHERE A.isActive = 'Y' ");
		return jpql.toString();
	}
	
	public String getASNReportlist(ASNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT Distinct(A) FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ")
//				.append(" INNER JOIN FETCH B.reqby c ")
				.append(" INNER JOIN FETCH A.createdBy D ")
				.append(" INNER JOIN FETCH D.userDetails E ")
		        .append(" INNER JOIN FETCH A.partner p")
		        .append(" LEFT JOIN FETCH A.grnPostedby gp")
		        .append(" LEFT JOIN FETCH gp.userDetails H ")
//		        .append(" INNER JOIN FETCH A.gateinBy gb")
//		        .append(" INNER JOIN FETCH D.userDetails E ")
		        .append(" LEFT JOIN FETCH A.gateinPostedby gpb")
		        .append(" LEFT JOIN FETCH gpb.userDetails F ")
//		        .append(" INNER JOIN FETCH A.reportedBy rb")
//		        .append(" INNER JOIN FETCH D.userDetails E ")
		        .append(" INNER JOIN FETCH PurchaseOrderLine G on (G.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ");
		        //.append(" LEFT JOIN FETCH AdvanceShipmentNoticeLine asnLine on (asnLine.advanceshipmentnotice.advanceShipmentNoticeId=A.advanceShipmentNoticeId)");
		

		String where =  getWhereClause(dto);
		jpql.append(where);
	//	System.out.println(jpql.toString());
	
		return jpql.toString();
	}
	private String getWhereClause(ASNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' AND A.advanceShipmentNoticeNo IS NOT NULL");
   		
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
   			where.append(" AND B.purchaseOrderNumber BETWEEN :fromNo AND :toNo ");
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			where.append(" AND B.purchaseOrderNumber =:fromNo ");
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			where.append(" AND B.purchaseOrderNumber =:toNo ");
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()!=null){
   			where.append(" AND A.advanceShipmentNoticeNo BETWEEN :asnNoFrom AND :asnNoTo ");
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()==null){
   			where.append(" AND A.advanceShipmentNoticeNo =:asnNoFrom ");
   		}
   		if(dto.getAsnNoFrom()==null && dto.getAsnNoTo()!=null){
   			where.append(" AND A.advanceShipmentNoticeNo =:asnNoTo ");
   			
   		}
   		
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()!=null){
   			where.append(" AND A.created BETWEEN :fromDate AND :toDate ");
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()==null){
   			where.append(" AND A.created =:fromDate ");
   		}
   		if(dto.getAsnDateFrom()==null && dto.getAsnDateTo()!=null){
   			where.append(" AND A.created =:toDate ");
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			where.append(" AND p.vendorSapCode =:vendorCode ");
   		}
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			
   			where.append(" AND A.status = (:status) ");  			
   		}
   		
   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			where.append(" AND B.reqby.userName =:requestedBy");
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND G.plant =:plant");
   		}
   		
   		return where.toString();
	}
	
	public String getSSNReportlist(SSNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT Distinct(A) FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ")
				//.append(" INNER JOIN FETCH B.reqby c ")
				//.append(" INNER JOIN FETCH A.createdBy D ")
				//.append(" INNER JOIN FETCH D.userDetails E ")
		        .append(" INNER JOIN FETCH A.partner p")
		        .append(" INNER JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ");
		
		String where =  getWhereClause(dto);
		jpql.append(where);
		return jpql.toString();
	}
	private String getWhereClause(SSNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' AND A.serviceSheetNo IS NOT NULL");
   		
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
   			where.append(" AND B.purchaseOrderNumber BETWEEN :fromNo AND :toNo");
   		}
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			where.append(" AND B.purchaseOrderNumber =:fromNo ");
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			where.append(" AND B.purchaseOrderNumber =:toNo");
   		}
   		
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()!=null){
   			where.append(" AND A.serviceSheetNo BETWEEN :ssnNoFrom AND :ssnNoTo ");
   		}
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()==null){
   			where.append(" AND A.serviceSheetNo =:ssnNoFrom ");
   		}
   		if(dto.getSsnNoFrom()==null && dto.getSsnNoTo()!=null){
   			where.append(" AND A.serviceSheetNo =:ssnNoTo ");
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()!=null){
   			where.append(" AND A.created BETWEEN :fromDate AND :toDate");
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()==null){
   			where.append(" AND A.created =:fromDate");
   		}
   		if(dto.getSsnDateFrom()==null && dto.getSsnDateTo()!=null){
   			where.append(" AND A.created =:toDate");
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			where.append(" AND p.vendorSapCode =:vendorCode");
   		}
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			//UPPER(A.status) like CONCAT('%',UPPER(:status),'%')
   			where.append(" AND A.status = (:status)");
   		}
   		
   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			where.append(" AND B.reqby.userName =:requestedBy");
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND D.plant =:plant");
   		}
   		
   		return where.toString();
	}
	
	public String getASNListBYStatus(ASNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.invoice E ")
				.append(" LEFT JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ").
				/*append(" INNER JOIN FETCH AdvanceShipmentNoticeLine E on "
						+ "(A.advanceShipmentNoticeId=E.advanceshipmentnotice.advanceShipmentNoticeId) ").*/
				append(" LEFT JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ");
				//append(" WHERE A.isActive = 'Y' and D.plant=:plant and A.status IN (:status)");
		//append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
	//	return jpql.toString();
		
		
		String where =  getWhereClauseASNList(dto);
		jpql.append(where);
		return jpql.toString();
	}
	
	private String getWhereClauseASNList(ASNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y' AND A.advanceShipmentNoticeNo IS NOT NULL AND A.status IN (:status)");
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND D.plant =:plant");
   		}
   		
   		return where.toString();
	}
	
	
	
	
	
	public String getASNBYStatus() {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.invoice E ")
				.append(" LEFT JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ").
				/*append(" INNER JOIN FETCH AdvanceShipmentNoticeLine E on "
						+ "(A.advanceShipmentNoticeId=E.advanceshipmentnotice.advanceShipmentNoticeId) ").*/
				append(" LEFT JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ").
				//append(" WHERE A.isActive = 'Y' and D.plant=:plant and A.status IN (:status)");
//		append(" WHERE A.isActive = 'Y' and A.status IN (:status) or (A.po is null and A.status != '"+AppBaseConstant.ASN_STATUS_GATE_OUT+"')");
				append(" WHERE A.isActive = 'Y' and A.status IN (:status) order by A.advanceShipmentNoticeId ASC");
		return jpql.toString();
	}
	
	public String getASNwithoutpo() {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.invoice E ")
				.append(" INNER JOIN FETCH A.partner C ").
				/*append(" INNER JOIN FETCH AdvanceShipmentNoticeLine E on "
						+ "(A.advanceShipmentNoticeId=E.advanceshipmentnotice.advanceShipmentNoticeId) ").*/
				//append(" INNER JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ").
				//append(" WHERE A.isActive = 'Y' and D.plant=:plant and A.status IN (:status)");
		append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
		return jpql.toString();
	}
	

	
	public String getASNBYStatusForGateEntry() {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.invoice E ")
				.append(" INNER JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ").
				/*append(" INNER JOIN FETCH AdvanceShipmentNoticeLine E on "
						+ "(A.advanceShipmentNoticeId=E.advanceshipmentnotice.advanceShipmentNoticeId) ").*/
				append(" INNER JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ").
				append(" WHERE A.isActive = 'Y' and A.status IN (:status) and B.purchaseOrderNumber=:pono");
		return jpql.toString();
	}

	public String getASNByPartnerId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH A.invoice D")
				.append(" WHERE A.isActive = 'Y' and C.bPartnerId = :partnerId ");
		return jpql.toString();
	}

	public String getASNByPOId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH A.invoice D")
				.append(" WHERE A.isActive = 'Y' and B.purchaseOrderId =:poId ORDER BY A.advanceShipmentNoticeNo DESC");
		return jpql.toString();
	}
	
	
	public String getApprovalPendingServiceSheet() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ")
				.append(" WHERE A.isActive = 'Y' and A.status = '"+AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS+"' and A.serviceSheetNo is not null ORDER BY A.serviceSheetNo DESC ");
		return jpql.toString();
	}
	
	public String getApprovalPendingServiceSheetbyUser() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ")
				.append(" WHERE A.isActive = 'Y' and A.status = '"+AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS+"' and A.ssnApproverId=:userName and A.serviceSheetNo is not null ORDER BY A.serviceSheetNo DESC ");
		return jpql.toString();
	}

	public String getASNByASNId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH A.invoice D").append(" INNER JOIN FETCH A.createdBy E")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	
	public String getASNByASNId101() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				//.append(" INNER JOIN FETCH A.po B ").
				 .append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH A.invoice D").append(" INNER JOIN FETCH A.createdBy E")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	
	public String getASNLineByASNId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH  AdvanceShipmentNoticeLine al on (A.advanceShipmentNoticeId=al.advanceshipmentnotice.advanceShipmentNoticeId)")
//				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
//				.append(" LEFT JOIN FETCH A.invoice D").append(" INNER JOIN FETCH A.createdBy E")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	
	public String getASNByASNIdforMain() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.po B ")
//				.append(" INNER JOIN FETCH A.partner C ")
//				.append(" LEFT JOIN FETCH A.invoice D").append(" INNER JOIN FETCH A.createdBy E")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}

	@Override
	public Integer getASNNumber(String plant) {
		StringBuilder query = new StringBuilder(" SELECT MAX(A.advanceShipmentNoticeNo) FROM AdvanceShipmentNotice A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.advanceShipmentNoticeNo,'') like '" + plant + "%' ");
		Query q = em.createQuery(query.toString());
		Integer x = (Integer) q.getSingleResult();
		if (null == x) {
			x = Integer.parseInt(((String) plant + "000000"));
		}
		return new Integer(x + 1);
	}

	@Override
	public boolean updateASNClosedStatus(Long asnId) {
		try {
		
			Date gateOutDate = new Date();
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE AdvanceShipmentNotice A ")
					.append(" set A.isGateOut='Y',A.gateOutDate=:goutdate, A.status = '"
							+ AppBaseConstant.ASN_STATUS_CLOSED + "'")
					.append(" where A.advanceShipmentNoticeId=:asnid and A.status='" + AppBaseConstant.ASN_STATUS_105
							+ "'");
			Query query = getEntityManager().createQuery(sb.toString());
			query.setParameter("asnid", asnId);
			query.setParameter("goutdate", gateOutDate);
			int result = query.executeUpdate();
			return result>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean updateASNGateOutStatus(Long asnId) {
		try {
			Date gateOutDate = new Date();
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE AdvanceShipmentNotice A ")
					.append(" set A.isGrn='Y',A.grnDate=:goutdate, A.status = '"
							+ AppBaseConstant.ASN_STATUS_CLOSED + "'")
					/*.append(" where A.advanceShipmentNoticeId=:asnid ")*/
					.append(" where A.advanceShipmentNoticeId=:asnid and A.status='" + AppBaseConstant.ASN_STATUS_GATE_OUT
							+ "'");
			Query query = getEntityManager().createQuery(sb.toString());
			query.setParameter("asnid", asnId);
			query.setParameter("goutdate", gateOutDate);
			int result = query.executeUpdate();
			return result>0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public String getServiceSheetNumber() {
		String serviceSheetPrefix = AppBaseConstant.PREFIX;
		StringBuilder query = new StringBuilder(" SELECT MAX(A.serviceSheetNo) FROM AdvanceShipmentNotice A ")
				.append(" WHERE A.isActive = 'Y' AND CONCAT(A.serviceSheetNo,'') like '" + serviceSheetPrefix + "%' ");
		Query q = em.createQuery(query.toString());
		String x = (String) q.getSingleResult();

		if (x == null) {

			x = serviceSheetPrefix + "000000";
			String[] y = x.split(serviceSheetPrefix);
			String z = y[1];
			int a = Integer.parseInt(z);
			a = a + 1;
			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
			// StringBuilder sb = new StringBuilder();
			// sb.append(x).append(a);
			String finalNo = x + a;
			// return sb.toString();
			return finalNo;
		} else {
			String[] y = x.split(serviceSheetPrefix);
			String z = y[1];
			int a = Integer.parseInt(z);
			a = a + 1;
			String leftPadded = StringUtils.leftPad("" + a, 7, "0");
			// String finalNo=x+a;
			// return finalNo;
			StringBuilder sb = new StringBuilder();
			sb.append(serviceSheetPrefix).append(leftPadded);
			return sb.toString();
			// String finalNo=y[0]+a;
			// return finalNo;

		}
	}
	public String getASNBYStatusForAdmin() {
		StringBuilder jpql = new StringBuilder(" SELECT DISTINCT(A) FROM AdvanceShipmentNotice A ")
				.append(" LEFT JOIN FETCH A.invoice D ").append(" INNER JOIN FETCH A.po B ")
				.append(" INNER JOIN FETCH A.partner C ").
				/*append(" INNER JOIN FETCH AdvanceShipmentNoticeLine E on "
						+ "(A.advanceShipmentNoticeId=E.advanceshipmentnotice.advanceShipmentNoticeId) ").*/
				append(" INNER JOIN FETCH PurchaseOrderLine D on (D.purchaseOrder.purchaseOrderId=A.po.purchaseOrderId ) ").
				append(" WHERE A.isActive = 'Y' and A.status IN (:status)");
		return jpql.toString();
	}
	
	public String getAsnAndPartnerByASNId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" LEFT JOIN FETCH B.partner E ")
				.append(" LEFT JOIN FETCH B.reqby F ")
				.append(" LEFT JOIN FETCH A.invoice D")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	public String getAsnByASNNo() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeNo = :asnId ");
		return jpql.toString();
	}
	public String getSSNBySSNNo() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" WHERE A.isActive = 'Y' and A.serviceSheetNo = :asnId ");
		return jpql.toString();
	}
	
//	public String getAsnAndpoByASNId() {
//		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
//				.append("WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
//		return jpql.toString();
//	}
	
	public String getAsnAndpoByASNId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ")
				.append("WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	
	public String getAsnbyheaderId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.securityPOHeader B ")
				.append(" WHERE A.isActive = 'Y' and A.securityPOHeader.asnHeaderId = :asnHeaderId ");
		return jpql.toString();
	}
	
	
	public String getAsnbyOutbounddeliveryNo() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" WHERE A.isActive = 'Y' and A.deliveryNoteNo = :outbounddeliveryNo ");
		return jpql.toString();
	}
	
	public String getASNByASNNoforCommercial() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.reportedBy B ")
				.append(" INNER JOIN FETCH A.gateinBy C ")
				.append(" INNER JOIN FETCH A.partner D")
				.append(" INNER JOIN FETCH A.createdBy E ")
				.append(" INNER JOIN FETCH E.userDetails F ")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeNo = :advanceShipmentNoticeNo ");
		return jpql.toString();
		
		
	}
	
	
	public String getASNDetailsByASNId() {
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNotice A ")
				.append(" INNER JOIN FETCH A.po B ").append(" INNER JOIN FETCH A.partner C ")
				.append(" INNER JOIN FETCH A.createdBy E")
				.append(" LEFT JOIN FETCH A.ssnApprovedBy F")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeId = :asnId ");
		return jpql.toString();
	}
	

}