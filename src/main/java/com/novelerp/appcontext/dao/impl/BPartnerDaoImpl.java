package com.novelerp.appcontext.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.BPartnerDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class BPartnerDaoImpl extends AbstractJpaDAO<Bpartner, BPartnerDto> implements BPartnerDao{
	
	@PostConstruct
	public void postConstruct() {
		setClazz(Bpartner.class, BPartnerDto.class);
	}

	public Bpartner getPartnerWithBPDetails (Long bPartnerId){
		Map<String, Object> params = getParamMap("bPartnerID", bPartnerId);
		return getSingleEntity(getQueryForBPDetails(), params);
	}
	
	private String getQueryForBPDetails(){
		StringBuilder jpql =  new StringBuilder("Select c from Bpartner c ");
		jpql.append("LEFT JOIN FETCH c.partnerCompanyDetails WHERE c.bPartnerId = :bPartnerID");	
		return jpql.toString();
	}
	

	@Override
	public Bpartner getPartnerWithDetails(Long bPartnerId) {
		Map<String, Object> params = getParamMap("bPartnerId", bPartnerId);
		return getSingleEntity(getPartnerWithAllDetails(), params);
	}
	public String getPartnerWithDetailsForAproval()
	{
		StringBuilder jpql = new StringBuilder(getPartnerWithAllDetails());
		/*jpql.append(" AND ( ")
		.append(" (pd.vendorTypePayment='TP' AND pd.isFAApproved='Y' )")
		.append(" OR (pdo.vendorTypePayment='MP' AND pdo.isFAApproved='Y')")
		.append(")");*/
	return jpql.toString();
	}
	private String getPartnerWithAllDetails()
	{
		StringBuilder jpql =  new StringBuilder(" Select bp from Bpartner bp ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCompanyContact pc ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCompanyAddress ca ");
		jpql.append(" LEFT JOIN FETCH bp.partnerBankDetail bd ");
		/*jpql.append(" LEFT JOIN FETCH bp.paymentDetails pd ");
		 * jpql.append(" LEFT JOIN FETCH bp.partnerSignatory ps ");
		 * 	jpql.append(" LEFT JOIN FETCH bp.directoryDetails dd ");
		jpql.append(" LEFT JOIN FETCH bp.partnerOrg po ")
		 .append(" LEFT JOIN FETCH po.paymentDetail pdo");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgUser ou ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgExperience exp ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgCertification cer ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgRegistration reg ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgBIS bis ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgProduct prd ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgPerformance per ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgRDAEC rdaec ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgOE ooe ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgInspection oi ");
		jpql.append(" LEFT JOIN FETCH bp.partnerOrgPBG pbg ");
		jpql.append(" LEFT JOIN FETCH bp.partnerFinancialAttachment fa ");
		
		jpql.append(" LEFT JOIN FETCH bp.partnerItemManufacturer it ");
		jpql.append(" LEFT JOIN FETCH it.partnerOrg itpo ");*/
		jpql.append(" WHERE bp.bPartnerId = :bPartnerId ");			
		return jpql.toString();
				
	}


	@Override
	public Bpartner getPartnerByPartnerId(Long bPartnerId) {
		Map<String, Object> params = getParamMap("bPartnerId", bPartnerId);
		return getSingleEntity(getPartnerWithAllDetails(), params);
	
	}

	public String getQueryForPartnerDetails(){
		StringBuilder jpql =  new StringBuilder("Select c from Bpartner c ");
		jpql.append(" LEFT JOIN FETCH  c.updatedBy ub");	
		jpql.append(" WHERE c.bPartnerId = :bPartnerId OR bp.isEEApproved='C' ");	
		return jpql.toString();
	}
	
	public String getQueryForPartnerApproval(RoleDto role){
		StringBuilder jpql =  new StringBuilder(" Select distinct(bp) from Bpartner bp ");
        jpql.append(" LEFT JOIN FETCH bp.panCardCopy pct ");
		jpql.append(" LEFT JOIN FETCH bp.gstinCopy gct ");
		jpql.append(" LEFT JOIN FETCH bp.companyRegCertificate rc ");
		jpql.append(" LEFT JOIN FETCH bp.ceSignCopy csc ");
		jpql.append(" LEFT JOIN FETCH bp.partnerSignCopy psc ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCoSignCopy pcsc ");
		jpql.append(" LEFT JOIN FETCH bp.partnershipDEEDCopy psdc ");
		jpql.append(" LEFT JOIN FETCH bp.officeLocation ol ");
		jpql.append(" LEFT JOIN FETCH bp.officeType ot ");
		String where =  getWhereClause(role);
		jpql.append(where);	
		return jpql.toString();
	}
	public String getQueryForPartnerApproval(RoleDto role,String searchMode,String searchValue){
		StringBuilder jpql =  new StringBuilder(" Select distinct(bp) from Bpartner bp ");
        jpql.append(" LEFT JOIN FETCH bp.panCardCopy pct ");
		jpql.append(" LEFT JOIN FETCH bp.gstinCopy gct ");
		jpql.append(" LEFT JOIN FETCH bp.companyRegCertificate rc ");
		jpql.append(" LEFT JOIN FETCH bp.ceSignCopy csc ");
		jpql.append(" LEFT JOIN FETCH bp.partnerSignCopy psc ");
		jpql.append(" LEFT JOIN FETCH bp.partnerCoSignCopy pcsc ");
		jpql.append(" LEFT JOIN FETCH bp.partnershipDEEDCopy psdc ");
		jpql.append(" LEFT JOIN FETCH bp.officeLocation ol ");
		jpql.append(" LEFT JOIN FETCH bp.officeType ot ");
		String where =  getWhereClause(role);
		jpql.append(where);	
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND (UPPER(bp.name) LIKE :searchValue OR (bp.panNumber) LIKE :searchValue OR UPPER(bp.crnNumber) LIKE :searchValue OR UPPER(bp.gstinNo) LIKE :searchValue) ");
		}
		return jpql.toString();
	}
	
	private String getWhereClause(RoleDto role){
		StringBuilder where = new StringBuilder();
		if(role==null || role.getValue()==null){
			return where.toString();
		}
		where.append(" WHERE bp.isActive ='Y' ");
		
		if(!CommonUtil.isStringEmpty(role.getValue()))
		{
			if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND bp.bPartnerId=b.bPartnerId AND ( ((b.isEEApproved IS NULL AND b.status = 'IP') OR  (b.isEEApproved = 'Y' AND b.status = 'CEC'))  OR   (( itm.isEEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND itm.isEEApproved ='Y' AND b.status = 'CEC'))) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=bp.bPartnerId AND ( ((bpr.isEEApproved IS NULL AND bpr.status = 'IP') OR  (bpr.isEEApproved = 'Y' AND bpr.status = 'CEC'))  OR   (( po.isEEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND po.isEEApproved ='Y' AND bpr.status = 'CEC'))) )) ");
				
			}
			if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND bp.bPartnerId=b.bPartnerId AND bp.isEEApproved=:isEEApproved AND (((b.isCEApproved IS NULL AND b.status='IP') OR (b.isCEApproved='C' AND b.status='IP')) OR (( itm.isCEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND b.status ='IP' )) ) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=bp.bPartnerId AND bp.isEEApproved=:isEEApproved AND (((bpr.isCEApproved IS NULL AND bpr.status='IP') OR (bpr.isCEApproved='C' AND bpr.status='IP')) OR (( po.isCEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND bpr.status ='IP')) ) )) ");
				/*where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND bp.bPartnerId=b.bPartnerId AND bp.isEEApproved=:isEEApproved AND (((b.isCEApproved IS NULL AND b.status='IP') OR (b.isCEApproved='C' AND b.status='IP')) OR (( itm.isCEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND b.status ='IP' )) OR (b.isCEApproved='Y' AND b.status='CO' AND b.ceSignCopy.attachmentId IS NULL)) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=bp.bPartnerId AND bp.isEEApproved=:isEEApproved AND (((bpr.isCEApproved IS NULL AND bpr.status='IP') OR (bpr.isCEApproved='C' AND bpr.status='IP')) OR (( po.isCEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND bpr.status ='IP')) OR (bpr.isCEApproved='Y' AND bpr.status='CO' AND bpr.ceSignCopy.attachmentId IS NULL)) )) ");
				*/
			}
			if(role.getValue().equals(AppBaseConstant.ROLE_SYS_ADMIN)){
				where.append(" AND bp.bPartnerId <>:bpartnerId");
			}
		}		
		return where.toString();
	}

	 public String getQueryForPartnerDetail()
	 {
		 StringBuilder jpql=new StringBuilder("SELECT b from Bpartner b ");
		 jpql.append(" LEFT JOIN FETCH b.panCardCopy pct ");
		 jpql.append(" LEFT JOIN FETCH b.gstinCopy gct ");
		 jpql.append(" LEFT JOIN FETCH b.companyRegCertificate irct ");
		 jpql.append(" LEFT JOIN FETCH b.ceSignCopy csc ");
		 jpql.append(" LEFT JOIN FETCH b.partnerSignCopy psc ");
		 jpql.append(" LEFT JOIN FETCH b.partnerCoSignCopy pcsc ");
		 jpql.append(" LEFT JOIN FETCH b.partnershipDEEDCopy psdc ");
		 jpql.append(" LEFT JOIN FETCH b.officeLocation ol ");
		 jpql.append(" LEFT JOIN FETCH b.officeType ot ");
		 jpql.append(" WHERE b.bPartnerId =:partnerId");
		 return jpql.toString();
	 }
	 public String getPartnerForValidation()
	 {
		 StringBuilder jpql=new StringBuilder("SELECT b from Bpartner b ");
		 jpql.append(" WHERE b.panNumber=:panCardNumber AND b.crnNumber=:crnNumber");
		 return jpql.toString();
	 }
	@Override
	public String getQueryForBPDetailsForSendInvitation() {
		 StringBuilder jpql=new StringBuilder("SELECT b from Bpartner b ");
		 jpql.append(" WHERE b.panNumber=:panCardNumber");
		 return jpql.toString();
	}
	
	public String getPartnerByPanNo() {
		 StringBuilder jpql=new StringBuilder("SELECT b from Bpartner b ");
		 jpql.append(" WHERE b.panNumber=:panCardNumber");
		 return jpql.toString();
	}

	 @Override
		public String getQueryForPartnersByStatus(RoleDto role,String status) {
			StringBuilder query=new StringBuilder(" Select b from Bpartner b ");
			query.append(" LEFT JOIN FETCH b.panCardCopy pct ");
			query.append(" LEFT JOIN FETCH b.gstinCopy gct ");
			query.append(" LEFT JOIN FETCH b.companyRegCertificate rc ");
			query.append(" LEFT JOIN FETCH b.partnershipDEEDCopy psdc ");
			query.append(" LEFT JOIN FETCH b.officeLocation ol ");
			query.append(" LEFT JOIN FETCH b.officeType ot ");
			String where=getWhereClaus(role,status);
			query.append(where);
			return query.toString();
		}
	 
	 @Override
		public String getQueryForApprovedPartners() {
			StringBuilder query=new StringBuilder(" Select b from Bpartner b ");
			query.append(" LEFT JOIN FETCH b.panCardCopy pct ");
			query.append(" LEFT JOIN FETCH b.gstinCopy gct ");
			query.append(" LEFT JOIN FETCH b.companyRegCertificate rc ");
			/*query.append(" WHERE b.status = 'CO' AND b.isCEApproved = 'Y'");*/
			query.append(" WHERE b.isApproved = 'Y' AND b.vendorSapCode is null ");
			
			return query.toString();
		}

	 
	@Override
	public String getQueryForPartnersByStatus(RoleDto role,String status,String searchColumn,String searchValue) {
		StringBuilder query=new StringBuilder(" Select b from Bpartner b ");
		query.append(" LEFT JOIN FETCH b.panCardCopy pct ");
		query.append(" LEFT JOIN FETCH b.gstinCopy gct ");
		query.append(" LEFT JOIN FETCH b.companyRegCertificate rc ");
		query.append(" LEFT JOIN FETCH b.partnershipDEEDCopy psdc ");
		query.append(" LEFT JOIN FETCH b.officeLocation ol ");
		query.append(" LEFT JOIN FETCH b.officeType ot ");
		String where=getWhereClaus(role,status);
		query.append(where);
		if(!"none".equalsIgnoreCase(searchValue)){
			query.append(" AND (UPPER(b.name) LIKE :searchValue OR UPPER(b.panNumber) LIKE :searchValue OR UPPER(b.crnNumber) LIKE :searchValue OR UPPER(b.gstinNo) LIKE :searchValue) ");
		}
		return query.toString();
	}
	 private String getWhereClaus(RoleDto role,String status)
	 {
		 StringBuilder where=new StringBuilder(" where b.isActive='Y' ");
		 if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND b.status IN('IP','RJ','CO') ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND b.status='RJ' ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND b.status='EEC' ");
			 }
			 where.append(" AND b.isEEApproved=:status ");
		 }
		 if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND b.status='CO' ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND b.status='RJ'  ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND b.status='CEC' ");
			 }
			 where.append(" AND b.isCEApproved=:status ");
		 }
		return where.toString();
	 }

	@Override
	public int deletePartners(Date toDate) {
		StringBuilder jpql=new StringBuilder(" Update Bpartner b set b.isPartnerDeleted='Y' ");
		jpql.append(" where b.status IN ('"+AppBaseConstant.PARTNER_STATUS_DRAFTED+"') AND Trunc(b.created)<=:toDate ");
		jpql.append(" AND  (b.value<>:code OR b.value is null) ");
		jpql.append(" AND  (b.isPartnerDeleted<>'Y' OR b.isPartnerDeleted is null) ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("toDate", toDate,TemporalType.DATE);
		query.setParameter("code", ContextConstant.USER_TYPE_INTERNAL);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	 public String getRecordCountWhereClaus(RoleDto role,String status)
	 {
		 StringBuilder where=new StringBuilder(" c.isActive='Y' ");
		 if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND c.status IN('IP','RJ','CO') ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND c.status='RJ' ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND c.status='EEC' ");
			 }
			 where.append(" AND c.isEEApproved=:status ");
		 }
		 if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND c.status='CO' ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND c.status='RJ'  ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND c.status='CEC' ");
			 }
			 where.append(" AND c.isCEApproved=:status ");
		 }
		return where.toString();
	 }
     @Override
	 public String getRecordCountWhereClaus(RoleDto role,String status,String searchMode,String searchValue)
	 {
		 StringBuilder where=new StringBuilder(" c.isActive='Y' ");
		 if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND c.status IN('IP','RJ','CO') ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND c.status='RJ' ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND c.status='EEC' ");
			 }
			 where.append(" AND c.isEEApproved=:status ");
		 }
		 if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		 {
			 if(status.equals(AppBaseConstant.APPROVED_STATUS))
			 {
				 where.append(" AND c.status='CO' ");
			 }else if(status.equals(AppBaseConstant.REJECTED_STATUS))
			 {
				 where.append(" AND c.status='RJ'  ");
			 }else if(status.equals(AppBaseConstant.CLARIFICATION_STATUS)){
				 where.append(" AND c.status='CEC' ");
			 }
			 where.append(" AND c.isCEApproved=:status ");
		 }
		 if(!"none".equalsIgnoreCase(searchValue)){
			 where.append(" AND (UPPER(c.name) LIKE :searchValue OR UPPER(c.panNumber) LIKE :searchValue OR UPPER(c.crnNumber) LIKE :searchValue OR UPPER(c.gstinNo) LIKE :searchValue) ");
		 }
		return where.toString();
	 }
	
	@Override
	public String getPrtnrAprlCountWhereClaus(RoleDto role){
		StringBuilder where = new StringBuilder();
		if(role==null){
			return where.toString();
		}
		where.append(" c.isActive ='Y' ");
		
		if(!CommonUtil.isStringEmpty(role.getValue()))
		{
			if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND c.bPartnerId=b.bPartnerId AND ( ((b.isEEApproved IS NULL AND b.status = 'IP') OR  (b.isEEApproved = 'Y' AND b.status = 'CEC'))  OR   (( itm.isEEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND itm.isEEApproved ='Y' AND b.status = 'CEC'))) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=c.bPartnerId AND ( ((bpr.isEEApproved IS NULL AND bpr.status = 'IP') OR  (bpr.isEEApproved = 'Y' AND bpr.status = 'CEC'))  OR   (( po.isEEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND po.isEEApproved ='Y' AND bpr.status = 'CEC'))) )) ");
				
			}
			if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND c.bPartnerId=b.bPartnerId AND c.isEEApproved=:isEEApproved AND (((b.isCEApproved IS NULL AND b.status='IP') OR (b.isCEApproved='C' AND b.status='IP')) OR (( itm.isCEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND b.status ='IP' ))) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=c.bPartnerId AND c.isEEApproved=:isEEApproved AND (((bpr.isCEApproved IS NULL AND bpr.status='IP') OR (bpr.isCEApproved='C' AND bpr.status='IP')) OR (( po.isCEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND bpr.status ='IP'))) )) ");
				
			}
			if(role.getValue().equals(AppBaseConstant.ROLE_SYS_ADMIN)){
				where.append(" AND c.bPartnerId <>:bpartnerId");
			}
		}		
		return where.toString();
	}
	@Override
	public String getPrtnrAprlCountWhereClaus(RoleDto role,String searchMode,String searchValue){
		StringBuilder where = new StringBuilder();
		if(role==null){
			return where.toString();
		}
		where.append(" c.isActive ='Y' ");
		if(!CommonUtil.isStringEmpty(role.getValue()))
		{
			if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND c.bPartnerId=b.bPartnerId AND ( ((b.isEEApproved IS NULL AND b.status = 'IP') OR  (b.isEEApproved = 'Y' AND b.status = 'CEC'))  OR   (( itm.isEEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND itm.isEEApproved ='Y' AND b.status = 'CEC'))) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=c.bPartnerId AND ( ((bpr.isEEApproved IS NULL AND bpr.status = 'IP') OR  (bpr.isEEApproved = 'Y' AND bpr.status = 'CEC'))  OR   (( po.isEEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND po.isEEApproved ='Y' AND bpr.status = 'CEC'))) )) ");
				
			}
			if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				where.append("  AND (EXISTS ( SELECT b from Bpartner b INNER JOIN  b.paymentDetails pd  INNER JOIN b.partnerItemManufacturer itm WHERE b.isTrader='Y' AND pd.isFAApproved='Y' AND c.bPartnerId=b.bPartnerId AND c.isEEApproved=:isEEApproved AND (((b.isCEApproved IS NULL AND b.status='IP') OR (b.isCEApproved='C' AND b.status='IP')) OR (( itm.isCEApproved IS NULL AND b.status = 'IP')  OR ( itm.isCEApproved = 'C' AND b.status ='IP' ))) ) ");
				where.append(" OR EXISTS ( SELECT bpr from Bpartner bpr INNER JOIN  bpr.partnerOrg po INNER JOIN po.paymentDetail opd WHERE bpr.isManufacturer='Y' AND opd.isFAApproved='Y' AND bpr.bPartnerId=c.bPartnerId AND c.isEEApproved=:isEEApproved AND (((bpr.isCEApproved IS NULL AND bpr.status='IP') OR (bpr.isCEApproved='C' AND bpr.status='IP')) OR (( po.isCEApproved IS NULL AND bpr.status = 'IP')  OR ( po.isCEApproved = 'C' AND bpr.status ='IP'))) )) ");
				
			}
			if(role.getValue().equals(AppBaseConstant.ROLE_SYS_ADMIN)){
				where.append(" AND c.bPartnerId <>:bpartnerId");
			}
		}
		if(!"none".equalsIgnoreCase(searchValue)){
			where.append(" AND (UPPER(c.name) LIKE :searchValue OR UPPER(c.panNumber) LIKE :searchValue OR UPPER(c.crnNumber) LIKE :searchValue OR UPPER(c.gstinNo) LIKE :searchValue) ");
		}
		return where.toString();
	}
	public String getQueryForPartnerRenewal()
	{
		StringBuilder jpql=new StringBuilder(" Select b from Bpartner b ");
	    jpql.append(" where Trunc(b.validTo)<:currentDate  and (b.isRenewed<>'N' or b.isRenewed is null) ");
		return jpql.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPartnerLocAndBankDetail(long bpartnerId){
		StringBuilder queryString = new StringBuilder();
		queryString.append("select bp.name as vendor_name, bp.PAN_NO, bp.GSTIN_NO, bankMaster.name as bank_name, bankBranch.branch_name, ");
		queryString.append("bankBranch.IFSC_CODE, bankdetail.ACCOUNT_NUMBER, bankdetail.BENIFICIARY_NAME, usrdtl.AD_USER_DETAILS_ID, ");
		queryString.append("loc.ADDRESS_1 as address, loc.CITY, dist.NAME as district, loc.POSTAL, usrdtl.MOBILE_NO, usrdtl.FAX_1,region.code ");
		queryString.append("from M_BPARTNER bp ");
		queryString.append("left join ad_user usr on ( usr.m_bpartner_id = bp.M_BPARTNER_ID ) ");
		queryString.append("left join AD_USER_DETAILS usrdtl on (usr.AD_USER_DETAILS_ID = usrdtl.AD_USER_DETAILS_ID)");
		queryString.append("left join m_bp_bank_detail bankdetail on ( bankdetail.M_BPARTNER_ID = bp.M_BPARTNER_ID ) ");
		queryString.append("left join M_BANK_NAME_DETAILS bankMaster on ( bankdetail.M_BANK_NAME_DETAILS_ID = bankMaster.M_BANK_NAME_DETAILS_ID ) ");
		queryString.append("left join M_BANK_BRANCH_DETAILS bankBranch on (bankdetail.M_BANK_BRANCH_DETAILS_ID = bankBranch.M_BANK_BRANCH_DETAILS_ID) ");
		queryString.append("left join M_BP_COMPANY_ADDRESS cadrs on (cadrs.M_BPARTNER_ID = bp.M_BPARTNER_ID) ");
		queryString.append("left join C_LOCATION loc on (loc.C_LOCATION_ID = cadrs.C_LOCATION_ID) ");
		queryString.append("left join C_DISTRICT dist on (loc.C_DISTRICT_ID = dist.C_DISTRICT_ID) ");
		queryString.append("LEFT JOIN C_REGION region ON ( loc.C_REGION_ID = region.C_REGION_ID) ");
		queryString.append("where bp.M_BPARTNER_ID = :bpartnerId ");
		Query query = getEntityManager().createNativeQuery(queryString.toString());
		query.setParameter("bpartnerId", bpartnerId);
		List<Object[]> obj =  query.getResultList(); 
		return obj;
	}
   public String getPartneByCode(){
	   StringBuilder jpql=new StringBuilder(" Select b from Bpartner b ")
	   .append(" where b.value=:partnerCode ");
	   return jpql.toString();
   }
   @Override
   public int vendorBlacklisting(Long bpartnerId,Long userId,String comment){
	   StringBuilder jpql= new StringBuilder();
		jpql.append(" Update Bpartner b SET b.blacklistingReason=:comment,b.isApproved='B',updatedBy.userId=:userId");
		jpql.append( " WHERE b.bPartnerId =:bpartnerId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("bpartnerId", bpartnerId);
		query.setParameter("comment", comment);
		query.setParameter("userId", userId);
		int count= query.executeUpdate();
		return count;
   }
   public String getInfraVendor(){
	   StringBuilder jpql=new StringBuilder("Select bp from Bpartner bp ")
	   .append(" where bp.isInfra='Y' AND bp.bPartnerId=:partnerId");
	   return jpql.toString();
   }
     @Override
     public  String getInfraApprovalVendors(RoleDto role,BPartnerDto dto){
	   StringBuilder jpql=new StringBuilder("Select distinct(bp) from Bpartner bp ")
	    .append(" INNER JOIN FETCH bp.paymentDetails pd ")
	    .append(" inner join pd.paymentType pt ")
	    .append(" where bp.isInfra='Y' AND bp.bPartnerId<>:partnerId AND pd.isFAApproved='Y' AND pd.vendorTypePayment=:paymentType ")
	    .append(" AND pt.code=:paymentCode ")
	    .append(" AND EXISTS(select ii from PartnerInfraItem ii where ii.levelNo=:level AND ii.partner.bPartnerId=bp.bPartnerId AND  ii.status=:status) ");
	   return jpql.toString();
    }

	@Override
	public String getInfraApprovalVendorsCount(RoleDto role, BPartnerDto dto) {
		 StringBuilder jpql=new StringBuilder("Select count(bp) from Bpartner bp ")
				    .append(" INNER JOIN  bp.paymentDetails pd ")
				    .append(" inner join pd.paymentType pt ")
				    .append(" where bp.isInfra='Y' AND bp.bPartnerId<>:partnerId AND pd.isFAApproved='Y' AND pd.vendorTypePayment=:paymentType ")
				    .append(" AND pt.code=:paymentCode ")
				    .append(" AND EXISTS(select ii from PartnerInfraItem ii where ii.levelNo=:level AND ii.partner.bPartnerId=bp.bPartnerId AND  ii.status=:status) ");
				   return jpql.toString();
	}
	public String getPartnerWithOrgAndPayment(){
		StringBuilder jpql=new StringBuilder("select bp from Bpartner bp ")
				.append(" LEFT JOIN FETCH bp.paymentDetails pd ")
				.append(" LEFT JOIN FETCH bp.partnerOrg po ")
				.append(" LEFT JOIN FETCH po.paymentDetail popd ")
				.append(" WHERE bp.bPartnerId=:partnerId ");
		return jpql.toString();
	}
  
	public String getPartnerByVendorSAPCode(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" WHERE A.vendorSapCode=:vendorCode ")
				.append(" and A.status='"+AppBaseConstant.DOCUMENT_STATUS_COMPLETE+"' ");
		return jpql.toString();
	}
	
	public String getPartnerByVendorPartnerId(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" WHERE A.bPartnerId=:partnerId");
		return jpql.toString();
	}
	
/*	public String getPartnersForQCF(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" INNER JOIN FETCH Bidder B ON (bp.bPartnerId=B.partner.bPartnerId ")
				.append(" INNER JOIN FETCH B.pr C ")
				.append(" WHERE C.prId=:prId ");
		jpql.append( " ORDER BY B.bidderId ASC");
		return jpql.toString();
	}*/
	public String getPartnerByVendorSAPCodeForPO(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" WHERE A.vendorSapCode=:vendorCode ");
		return jpql.toString();
	}
	public String getPartialUpdatedVendor(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" WHERE A.isProfileUpdated='"+AppBaseConstant.PARTIAL_STATUS+"' ")
				.append(" and A.status='"+AppBaseConstant.DOCUMENT_STATUS_DRAFTED+"' ")
				.append(" and A.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getPartnerByPartnerId(){
		StringBuilder jpql=new StringBuilder("select A from Bpartner A ")
				.append(" WHERE A.bPartnerId=:partnerId ");
		return jpql.toString();
	}

//	@Override
//	public String getVendorCredentailReportlist(VendorCredentialReadDto dto) {
//		StringBuilder jpql = new StringBuilder();
//	
//		jpql.append(" Select Distinct(bp) from Bpartner bp ");
//
//		jpql.append(" INNER JOIN FETCH bp.partnerCompanyAddress ca");
////		jpql.append(" INNER JOIN FETCH ca.location l");
////		jpql.append(" INNER JOIN FETCH l.district d ");
////		jpql.append(" INNER JOIN FETCH l.region r");
//		jpql.append(" LEFT JOIN FETCH bp.user u ON bp.");
//		String where =  getWhereClause(dto);
//		jpql.append(where);
// 
//    	return jpql.toString();
//		
//	
////		jpql.append(" INNER JOIN partnerCompanyAddress B ON A.bPartnerId=B.bPartnerId");
//		
////		jpql.append(" INNER JOIN FETCH l.district d ");
////		jpql.append(" INNER JOIN FETCH l.region r");	
//
////		jpql.append(" Select Distinct(A) from PartnerCompanyAddress A ");
////		
////		jpql.append(" LEFT JOIN FETCH A.location l ");
////		jpql.append(" LEFT JOIN FETCH A.partner p");
////		jpql.append(" LEFT JOIN FETCH l.district d ");
////		jpql.append(" LEFT JOIN FETCH l.region r");	
////		jpql.append(" LEFT JOIN FETCH User U ON U.partner.bPartnerId=p.bPartnerId");
//		
//	
//  		
//
//		
////		jpql.append(" select a.m_bpartner_id as ID,a.vendor_sap_code as vendorcode from m_bpartner as a left join m_bp_company_address as h on a.m_bpartner_id=h.m_bpartner_id ");
//////		jpql.append(" Left join m_bp_company_address h on (h.m_bpartner_id=a.m_bpartner_id)");
////		jpql.append(" WHERE (a.vendor_sap_code = '"+dto.getVendorCode()+"' AND h.is_primary_account ='Y' )");
////
////		Query 	query=getEntityManager().createNativeQuery(jpql.toString());	
////		List<BPartnerDto> resultSet= query.getResultList();
////		return resultSet;
//		
//	}
//       private String getWhereClause(VendorCredentialReadDto dto){
//		
//		
//   		StringBuilder where = new StringBuilder();
//   		where.append(" WHERE ca.isPrimaryAccount ='Y' ");
////   		where.append(" WHERE A.isPrimaryAccount='Y' ");
////   		   		
////   		if(dto.getDistrict()!=null && !dto.getDistrict().equals("")){
////   			where.append(" AND d.name = :district");
////   		}
////   		if(dto.getState()!=null && !dto.getState().equals("")){
////   			where.append(" AND r.name=:state");
////   		}
//   		
//   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
//   			where.append(" AND bp.vendorSapCode=:vendorCode");
//   		}
//   		
////   		if(dto.getMailID()!=null && !dto.getMailID().equals("")){
////   			where.append(" AND U.email=:mailID");
////   		}
////   		
////   		if(dto.getStatus()!=null && !dto.getStatus().equals("")) {
////   			where.append("AND p.status=:status");
////   		}
//   		
//   		return where.toString();
//
//	}
//	


}
