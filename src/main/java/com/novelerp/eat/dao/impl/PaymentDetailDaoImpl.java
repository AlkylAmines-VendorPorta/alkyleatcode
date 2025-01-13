/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.PaymentDetailDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

@Repository
public class PaymentDetailDaoImpl extends AbstractJpaDAO<PaymentDetail, PaymentDetailDto> implements PaymentDetailDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(PaymentDetail.class, PaymentDetailDto.class);
	}
	@Override
	public String getQueryForPaymentDetail(RoleDto role) {
		StringBuilder query = new StringBuilder(getPaymentDetailsQuery());
		query.append(" AND pd.paymentGatewayStatus IS NOT NULL ");
		 if(role!=null)
		 {
			if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER) || role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
			{ 
				query.append(" AND pd.isFAApproved=:isFAApproved ");
			}
		 }
		return query.toString();
	}
	
	public String getPaymentDetailsQuery()
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd")
		.append(" LEFT JOIN FETCH pd.createdBy c")
		.append(" LEFT JOIN FETCH pd.updatedBy u")
		.append(" LEFT JOIN FETCH pd.partner p")
		.append(" LEFT JOIN FETCH pd.paymentType pt")
		.append(" LEFT JOIN FETCH pd.partnerOrg pdo")
		.append(" WHERE pd.partner.bPartnerId = :partnerId AND (pd.paymentType.code='RG' OR pd.paymentType.code='RN' OR pd.paymentType.code='INFRG') ");
		return query.toString();
	}
	public String getPaymentsForOrg()
	{
		StringBuilder query = new StringBuilder("SELECT pd from PaymentDetail pd")
		.append(" LEFT JOIN FETCH pd.createdBy c")
		.append(" LEFT JOIN FETCH pd.updatedBy u")
		.append(" LEFT JOIN FETCH pd.partner p")
		.append(" LEFT JOIN FETCH pd.paymentType pt")
		.append(" LEFT JOIN FETCH pd.partnerOrg pdo")
		.append(" WHERE pd.partner.bPartnerId = :partnerId  ")
		.append(" AND (pd.paymentType.code='RG' OR (pd.paymentType.code='RN' AND pdo.validTo IS NOT NULL) ) AND pd.vendorTypePayment='MP' AND pd.paymentGatewayStatus=:paymentStatus ");
		return query.toString();
	}
	
	public String getPaymentsQuery()
	{
		StringBuilder query = new StringBuilder(getPaymentDetailsQuery());
		query.append(" AND pd.isFAApproved='Y' ");
		return query.toString();
	}
	
	public String getTahdrPaymentDetails(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd")
		.append(" LEFT JOIN FETCH pd.tahdrDetail td")
		.append(" LEFT JOIN FETCH pd.paymentType pt")
		.append(" LEFT JOIN FETCH pd.tahdr t ")
		.append(" LEFT JOIN FETCH pd.partner p ")
		.append(" LEFT JOIN FETCH pd.partnerOrg po ")
		.append(" LEFT JOIN FETCH pd.bankGuaranteeAttachment bg ");
		query.append(" LEFT JOIN FETCH pd.foApprovedBy fou");
		query.append(" LEFT JOIN FETCH pd.faApprovedBy fau");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByTahdrId(){
		StringBuilder query = new StringBuilder(getTahdrPaymentDetails());
		query.append(" WHERE pd.tahdr.tahdrId = :tahdrId");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByDates(){
		StringBuilder query = new StringBuilder(getTahdrPaymentDetails());
		query.append(" WHERE pd.tahdr.tahdrId = :tahdrId AND pd.created BETWEEN :fromDate AND :toDate ");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByfiscalyear(){
		StringBuilder query = new StringBuilder(getTahdrPaymentDetails());
		query.append(" WHERE pd.created BETWEEN :fromDate AND :toDate ");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByPaymentType(){
		StringBuilder query = new StringBuilder(getTahdrPaymentDetails());
		query.append(" WHERE pt.paymentTypeId=:paymentTypeId ");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByChargeCodeQuery(){
		StringBuilder query = new StringBuilder(getTahdrPaymentDetailsByTahdrId());
		query.append(" AND pd.paymentType.code=:paymentTypeCode ");
		return query.toString();
	}
	
	public String getTahdrPaymentDetailsByChargeCodePartnerQuery()
	{
		StringBuilder query= new StringBuilder(getTahdrPaymentDetailsByChargeCodeQuery());
		query.append(" AND p.bPartnerId=:partnerId ")
		.append(" and (pd.paymentGatewayStatus is not null or pd.paymentMode<>'"+AppBaseConstant.PAYMENT_MODE_OP+"' ) ");
		return query.toString();
	}
	public String getQueryForPaymentApproval(RoleDto role,String searchColumn, String searchValue,String locationType,String officeLocation)
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd ")
		.append(" LEFT JOIN FETCH pd.paymentType pt ")
		.append(" LEFT JOIN FETCH pd.tahdr t ")
		.append(" LEFT JOIN FETCH pd.tahdrDetail td ")
		.append(" LEFT JOIN FETCH t.officeType ot ")
		.append(" LEFT JOIN FETCH t.officeLocation ol ")
		.append(" LEFT JOIN FETCH pd.partner p ")
		.append(" LEFT JOIN FETCH pd.partnerOrg po ")
		.append(" LEFT JOIN FETCH pd.bankGuaranteeAttachment bg ");
		String where =  getWhereClause(role,locationType,officeLocation);
		query.append(where);
		query.append("  AND pd.paymentMode NOT IN(:onlinePaymentMode,'"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"') ");
		if(!"none".equalsIgnoreCase(searchValue)){
			query.append(" AND UPPER(pd.partner.name) LIKE UPPER(:searchValue) ");
		}
		query.append(" order by pd.paymentDetailId desc");
		return query.toString();
	}
	
	@Override
	public String getQueryForPaymentPosting(String searchColumn, String searchValue)
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd ")
		.append(" LEFT JOIN FETCH pd.paymentType pt ")
		.append(" LEFT JOIN FETCH pd.tahdr t ")
		.append(" LEFT JOIN FETCH pd.tahdrDetail td ")
		.append(" LEFT JOIN FETCH t.officeType ot ")
		.append(" LEFT JOIN FETCH t.officeLocation ol ")
		.append(" LEFT JOIN FETCH pd.partner p ")
		.append(" LEFT JOIN FETCH pd.partnerOrg po ")
		.append(" where pd.isFAApproved = 'Y' ")
		.append(" AND pd.isPushed = :showPushedData ");
		if(!("none".equalsIgnoreCase(searchValue))){
			query.append(" AND UPPER(pd."+searchColumn+") LIKE UPPER(:searchValue) ");
		}
		query.append(" order by pd.paymentDetailId desc");
		return query.toString();
	}
	@Override
	public Object[] getUnPushedPayments(Long paymentDetailId )
	{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT pd.paymentDetailId,")
		.append(" CASE WHEN (pd.referenceNo IS NOT NULL) THEN pd.referenceNo ELSE CASE WHEN mis.docNo IS NOT NULL THEN mis.docNo ELSE '0' END END as doc_no, ")
		.append(" CASE WHEN (mis.charges IS NOT NULL) THEN mis.charges ELSE 0 END as charges, ")
		.append(" pd.total, ")
		.append(" pt.paymentPostingCode, ")
		.append(" bp.vendorSapCode, ")
		.append(" t.tahdrCode, ")
		.append(" ol.code ")
		.append(" from PaymentDetail pd ")
		.append(" left join fetch PaymentType pt on pt.paymentTypeId = pd.paymentType.paymentTypeId")
		.append(" left join fetch MIS mis on mis.docNo = pd.docNo or mis.docNo = pd.referenceNo ")
		.append(" left join fetch Bpartner bp on bp.bPartnerId=pd.partner.bPartnerId  ")
		.append(" left join fetch TAHDR t on t.tahdrId=pd.tahdr.tahdrId  ")
		.append(" left join fetch OfficeLocation ol on t.officeLocation.officeLocationId=ol.officeLocationId")
		.append(" WHERE pd.isFAApproved = 'Y' ")
		.append(" AND pd.isPushed <> 'Y' ")
		.append(" AND pd.paymentDetailId = :paymentDetailId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("paymentDetailId", paymentDetailId);
		Object[] results = (Object[]) query.getSingleResult();
		return results;
	}
	
	@Override
	@Transactional
	public int upDatePushedPaymentDetails(List<Long> paymentDetailIds){
		String jpql = "UPDATE PaymentDetail pd " + 
	              "SET pd.isPushed = :updated_status " +
	              "WHERE pd.paymentDetailId IN :ids";
	Query query=getEntityManager().createQuery(jpql.toString());
	query.setParameter("updated_status", "Y");
	query.setParameter("ids", paymentDetailIds);
	return query.executeUpdate();
	}
	
	@Override
	public String getQueryForPaymentPostingCount(String searchColumn, String searchValue){
		StringBuilder query = new StringBuilder();
		query.append(" c.isFAApproved = 'Y' AND c.isPushed = :showPushedData");
		if(!("none".equalsIgnoreCase(searchValue))){
			query.append(" AND UPPER(c."+searchColumn+") LIKE UPPER(:searchValue) ");
		}
		return query.toString();
	}
	
	public String getQueryForDocNo(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd FROM PaymentDetail pd LEFT JOIN FETCH pd.paymentType pt WHERE pd.docNo=:docNo");
		return query.toString();
	}
	private String getWhereClause(RoleDto role,String locationType,String officeLocation)
	{
		StringBuilder query = new StringBuilder();
		query.append(" where pd.isActive='Y' ");
		/*query.append(" AND p.isApproved=null ");*/
		if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
		{
			query.append(" AND pd.isFOApproved=null ");
			if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
			query.append(" AND (( pd.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
			/*query.append(" AND (pd.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
			query.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
			}
			else{	
				query.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
			}
		}
		if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_ADMIN))
		{
			query.append(" AND pd.isFOApproved='Y' AND pd.isFAApproved=null ");
			if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
				query.append(" AND (( pd.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
				/*query.append(" AND ( pd.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
				query.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
				}
				else{
					query.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
				}
		}
		return query.toString();
	}
	@Override
	public int updatePaymentDetail(Long partnerOrgId, Long paymentDetailId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update PaymentDetail pd SET pd.partnerOrg.partnerOrgId=:partnerOrgId ");
		jpql.append( " where pd.paymentDetailId=:paymentDetailId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("partnerOrgId", partnerOrgId);
		query.setParameter("paymentDetailId", paymentDetailId);
		return query.executeUpdate();
	}
	@Override
	public String getPaymentsByStatus(RoleDto role,String searchColumn, String searchValue,String locationType,String officeLocation) {
		StringBuilder jpql=new StringBuilder(" Select p from PaymentDetail p ")
		.append(" LEFT JOIN FETCH p.paymentType pt ")
		.append(" LEFT JOIN FETCH p.partner ptr ")
		.append(" LEFT JOIN FETCH p.tahdr t ")
		.append(" LEFT JOIN FETCH p.tahdrDetail td ")
		.append(" LEFT JOIN FETCH t.officeType ot ")
		.append(" LEFT JOIN FETCH t.officeLocation ol ")
		.append(" LEFT JOIN FETCH p.partnerOrg po ");
		if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
		{
			jpql.append(" where p.isActive='Y' AND p.isFOApproved=:paymentStatus AND p.paymentMode NOT IN(:onlinePaymentMode,'"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"') ");
			if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
			/*jpql.append(" AND (p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
				jpql.append(" AND (( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
				jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
			}
			else{
				jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
			}
		}else if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN))
		{
			jpql.append(" where p.isActive='Y' AND p.isFOApproved='Y' AND p.isFAApproved=:paymentStatus AND p.paymentMode NOT IN(:onlinePaymentMode,'"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"') ");
			if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
				/*jpql.append(" AND (p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
				jpql.append(" AND (( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
				jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
				}
				else{
					jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
				}
		}
		if(!"none".equalsIgnoreCase(searchValue)){
			jpql.append(" AND UPPER(p.partner.name) LIKE UPPER(:searchValue) ");
		}
		jpql.append(" order by p.updated desc");
		return jpql.toString();
	}
	
	public String getPaymentDetailsWithPaymentType()
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd ")
		.append(" INNER JOIN FETCH pd.paymentType pt ")
		.append(" LEFT JOIN FETCH pd.partnerOrg org ")
		.append(" LEFT JOIN FETCH pd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH pd.updatedBy ub ")
		.append(" LEFT JOIN FETCH pd.partner bp ");
		return query.toString();
	}
	
	public String getPaymentDetailsByDocNumber()
	{
		StringBuilder query = new StringBuilder(getPaymentDetailsWithPaymentType());
		query.append(" LEFT JOIN FETCH pd.createdBy cb ")
		.append(" LEFT JOIN FETCH cb.userDetails ud ")
		.append(" WHERE pd.docNo=:docNo ");
		return query.toString();
	}
	public String getPaymentById()
	{
		StringBuilder jpql=new StringBuilder(getPaymentDetailsWithPaymentType())
		.append(" LEFT JOIN FETCH pd.createdBy cb ")
		.append(" LEFT JOIN FETCH cb.userDetails ud ")		
		.append(" WHERE pd.paymentDetailId=:paymentDetailId ");
		return jpql.toString();
	}
	public String getQueryForRejectedPayment(){
		StringBuilder jpql=new StringBuilder(" SELECT pd from PaymentDetail pd ")
		.append(" INNER JOIN FETCH pd.paymentType pt ")
		.append(" where (pd.isFOApproved='N' OR pd.isFAApproved='N') AND pd.partner.bPartnerId=:partnerId ");
		return jpql.toString();
	}
	
	public String getQueryForPaymentReceipt(String paymentType,String roleName)
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd ")
		.append(" INNER JOIN FETCH pd.paymentType pt ")
		.append(" LEFT JOIN FETCH pt.hsn h ")
		.append(" LEFT JOIN FETCH pd.partnerOrg org ")
		.append(" LEFT JOIN FETCH pd.tahdr t ")
		.append(" LEFT JOIN FETCH t.tahdrDetail td ")
		.append(" LEFT JOIN FETCH pd.partner bp ")
		.append(" LEFT JOIN FETCH pd.createdBy cr ")
		.append(" LEFT JOIN FETCH cr.userDetails ud ");
		if(roleName.equalsIgnoreCase(AppBaseConstant.ROLE_FINANCE_OPERATOR) || roleName.equalsIgnoreCase(AppBaseConstant.ROLE_FINANCE_ADMIN)){
			if(paymentType.equalsIgnoreCase(AppBaseConstant.TENDER_PURCHASE_FEE)
					|| paymentType.equalsIgnoreCase(AppBaseConstant.EMD)){
				query.append(" where pd.paymentDetailId= :paymentDetailId  AND pt.code=:paymentType");
			}
			else if(paymentType.equalsIgnoreCase(AppBaseConstant.REGISTRATION_FEE)
					|| paymentType.equalsIgnoreCase(AppBaseConstant.RENEWAL_FEE) || paymentType.equalsIgnoreCase(AppBaseConstant.INFRA_REGISTRATION_FEE)){
				query.append(" where pd.paymentDetailId= :paymentDetailId AND pt.code=:paymentType ");
			}
		}
		else{
			if(paymentType.equalsIgnoreCase(AppBaseConstant.TENDER_PURCHASE_FEE)
					|| paymentType.equalsIgnoreCase(AppBaseConstant.EMD)){
				query.append(" where t.tahdrId= :tahdrId AND bp.bPartnerId= :partnerId AND pt.code=:paymentType");
			}
			else if(paymentType.equalsIgnoreCase(AppBaseConstant.REGISTRATION_FEE)
					|| paymentType.equalsIgnoreCase(AppBaseConstant.RENEWAL_FEE) || paymentType.equalsIgnoreCase(AppBaseConstant.INFRA_REGISTRATION_FEE)){
				query.append(" where pd.paymentDetailId= :paymentDetailId AND pt.code=:paymentType ");
			}			
		}
		return query.toString();
	}
	
	/*@Override
	public String getPaymentApprovalCountQry(String parameterFlag,RoleDto role,String searchColumn,String searchValue,String locationType,String officeLocation){
		StringBuilder jpql =  new StringBuilder(" c.isActive='Y' ");
		if(parameterFlag!=null && parameterFlag.equalsIgnoreCase("Y")){
			if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
			{
				if(locationType.equalsIgnoreCase("HO") && officeLocation.equalsIgnoreCase("Prakash Gadh"))	{
					jpql.append(" AND ( c.paymentType.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"')) ");
					jpql.append(" OR (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
					else{
						jpql.append(" AND (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (c.isFOApproved=:paymentStatus AND UPPER(c.partner.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (c.isFOApproved=:paymentStatus) ");
				}
			}else if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN))
			{
				if(locationType.equalsIgnoreCase("HO") && officeLocation.equalsIgnoreCase("Prakash Gadh"))	{
					jpql.append(" AND (c.paymentType.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"')) ");
					jpql.append(" OR (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
					else{
						jpql.append(" AND (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (c.isFOApproved='Y' AND c.isFAApproved=:paymentStatus AND UPPER(c.partner.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (c.isFOApproved='Y' AND c.isFAApproved=:paymentStatus) ");
				}
			}
		}
		else if(parameterFlag==null){
			if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
			{
				
				if(locationType.equalsIgnoreCase("HO") && officeLocation.equalsIgnoreCase("Prakash Gadh"))	{
					jpql.append(" AND (c.paymentType.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"')) ");
					jpql.append(" OR (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
					else{
						jpql.append(" AND (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (c.isFOApproved=null AND UPPER(c.partner.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (c.isFOApproved=null) ");
				}
			}
			if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_ADMIN))
			{
				if(locationType.equalsIgnoreCase("HO") && officeLocation.equalsIgnoreCase("Prakash Gadh"))	{
					jpql.append(" AND (c.paymentType.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"')) ");
					jpql.append(" OR (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
					else{
						jpql.append(" AND (c.tahdr.officeType.code='"+locationType+"' AND c.tahdr.officeLocation.name='"+officeLocation+"') ");
					}
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (c.isFOApproved='Y' AND c.isFAApproved=null AND UPPER(c.partner.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (c.isFOApproved='Y' AND c.isFAApproved=null) ");
				}
			}
		}
		return jpql.toString();
	}*/
	
	@Override
	@Transactional
	public long getPaymentApprovalCountQry(String parameterFlag,RoleDto role,String searchColumn,String searchValue,String locationType,String officeLocation,String paymentStatus){		
		StringBuilder jpql=new StringBuilder(" Select COUNT(p) from PaymentDetail p ")
				.append(" LEFT JOIN  p.paymentType pt ")
				.append(" LEFT JOIN  p.partner ptr ")
				.append(" LEFT JOIN  p.tahdr t ")
				.append(" LEFT JOIN  p.tahdrDetail td ")
				.append(" LEFT JOIN  t.officeType ot ")
				.append(" LEFT JOIN  t.officeLocation ol ")
				.append(" LEFT JOIN  p.partnerOrg po ")
				.append(" where p.isActive='Y' AND p.paymentMode NOT IN('"+AppBaseConstant.PAYMENT_MODE_OP+"','"+AppBaseConstant.PAYMENT_MODE_ISEXEMP+"') ");
		if(parameterFlag!=null && parameterFlag.equalsIgnoreCase("Y")){
			if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
			{
				if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
					jpql.append(" AND ((p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
					jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
					}
					else{
						jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (p.isFOApproved=:paymentStatus AND UPPER(ptr.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (p.isFOApproved=:paymentStatus) ");
				}
			}else if(role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN))
			{
				if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
					jpql.append(" AND ((p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
					/*jpql.append(" AND ( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
					jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
					}
					else{
						jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (p.isFOApproved='Y' AND p.isFAApproved=:paymentStatus AND UPPER(ptr.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (p.isFOApproved='Y' AND p.isFAApproved=:paymentStatus) ");
				}
			}
		}
		else if(parameterFlag==null){
			if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
			{
				
				if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
					jpql.append(" AND (( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
					/*jpql.append(" AND ( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
					jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
					}
					else{
						jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
					}
				
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (p.isFOApproved=null AND UPPER(ptr.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (p.isFOApproved=null) ");
				}
			}
			if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_FINANCE_ADMIN))
			{
				if(locationType.equalsIgnoreCase(AppBaseConstant.OFFICE_TYPE_OT) && officeLocation.equalsIgnoreCase(AppBaseConstant.OFFICE_LOCATION_OL))	{
					jpql.append(" AND (( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"')) OR (pt.code IN('"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");
					/*jpql.append(" AND ( p.partner.status='"+AppBaseConstant.PARTNER_STATUS_IN_PROGRESS+"' AND (pt.code IN('"+AppBaseConstant.REGISTRATION_FEE+"','"+AppBaseConstant.RENEWAL_FEE+"','"+AppBaseConstant.INFRA_REGISTRATION_FEE+"')) ");*/
					jpql.append(" OR (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"')) ");
					}
					else{
						jpql.append(" AND (ot.code='"+locationType+"' AND ol.name='"+officeLocation+"') ");
					}
				if(!"none".equalsIgnoreCase(searchValue)){
					jpql.append(" AND (p.isFOApproved='Y' AND p.isFAApproved=null AND UPPER(ptr.name) LIKE UPPER(:searchValue)) ");
				}
				else{
					jpql.append(" AND (p.isFOApproved='Y' AND p.isFAApproved=null) ");
				}
			}
		}
		Query q = getEntityManager().createQuery(jpql.toString());
		if(parameterFlag!=null && parameterFlag.equalsIgnoreCase("Y")){
		q.setParameter("paymentStatus", paymentStatus);}
		if(!"none".equalsIgnoreCase(searchValue)){
		q.setParameter("searchValue", "%" + searchValue + "%");}
		Long count= (Long) q.getSingleResult();
		return count;
	}
	
	public String getPaymentsForApprovalCheck(){
		StringBuilder jpql=new StringBuilder(" select pd from PaymentDetail pd ")
				.append(" LEFT JOIN FETCH pd.partner p ")
				.append(" where pd.partner.bPartnerId=:partnerId AND (pd.paymentType.code=:regFee OR pd.paymentType.code=:renewalFee )")
				.append(" AND ((pd.isFAApproved IS NULL AND pd.isFOApproved IS NULL )"
						+ " OR (pd.isFAApproved IS NULL AND pd.isFOApproved='Y' ))");
		return jpql.toString();
	}
	
	public String getPartnerPaymentDetailByTypeAndTahdrId(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT pd from PaymentDetail pd ")
		.append(" WHERE pd.isActive='Y' AND pd.partner.bPartnerId=:partnerId AND pd.tahdr.tahdrId=:tahdrId AND pd.paymentType.name=:paymentType");
		return query.toString();
	}
	public String getPaymentsForPartner(){
		StringBuilder jpql=new StringBuilder(" Select pd from PaymentDetail pd ")
		.append(" WHERE pd.isActive='Y' AND pd.isCEApproved is null AND pd.partner.bPartnerId=:partnerId AND pd.paymentType.code='"+AppBaseConstant.RENEWAL_FEE+"' and pd.vendorTypePayment='"+AppBaseConstant.TRADER_PAYMENT+"'");
		return jpql.toString();
	}
}
