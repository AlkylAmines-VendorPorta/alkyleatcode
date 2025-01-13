package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerOrgDao;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */
@Repository
public class PartnerOrgDaoImpl extends AbstractJpaDAO<PartnerOrg, PartnerOrgDto> implements PartnerOrgDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerOrg.class, PartnerOrgDto.class);
	}
	

	@Override
	public String getPartnerOrgsQuery(RoleDto role) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT o from PartnerOrg o")
		.append(" INNER JOIN FETCH o.partner op ")
		.append(" INNER JOIN FETCH o.location l ")
		/*.append(" INNER JOIN FETCH l.partner p")*/
		/*.append(" INNER JOIN FETCH l.createdBy c")
		.append(" INNER JOIN FETCH l.updatedBy u")*/
		.append(" INNER JOIN FETCH l.country cn")
		.append(" INNER JOIN FETCH l.region r")
		.append(" INNER JOIN FETCH l.district d")
		/*.append(" INNER JOIN FETCH o.createdBy oc")
		.append(" INNER JOIN FETCH o.updatedBy ou")	*/
		.append(" LEFT JOIN FETCH o.licenceCopy lc")	
		.append(" LEFT JOIN FETCH o.machinaryListCopy mc")	
		.append(" LEFT JOIN FETCH o.inspectionReportCopy irc")	
		.append(" LEFT JOIN FETCH o.staffListCopy slc")
		.append(" LEFT JOIN FETCH o.authorizationCertificate ac")
		.append(" LEFT JOIN FETCH o.testingEquipmentDetails ted ")
		.append(" INNER JOIN FETCH o.paymentDetail pd ");
		String where=getWhereClause(role);
		query.append(where);
		return query.toString();
	}
	private String getWhereClause(RoleDto role)
	{
		StringBuilder where = new StringBuilder();
		where.append(" WHERE o.partner.bPartnerId=:partnerId AND o.partnerItemManufacturer.partnerItemManufacturerId IS NUll ");
		if(role==null || role.getValue()==null){
			return where.toString();
		}
		/*if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			where.append(" AND o.isEEApproved=null AND o.paymentDetail.isFAApproved=:isFAApproved ");
		 }*/
		if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		 {
			where.append(" AND o.paymentDetail.isFAApproved=:isFAApproved AND EXISTS (select po from PartnerOrg po LEFT JOIN po.partnerOrgInspection poi INNER JOIN po.partner ptr where po.isEEApproved=null OR poi.isEEApproved=null OR poi.isCEApproved='C' OR ptr.isEEApproved=null ) ");
		 }
		if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		{
			where.append(" AND o.isEEApproved='Y' AND o.paymentDetail.isFAApproved=:isFAApproved AND EXISTS (select po from PartnerOrg po INNER JOIN po.partnerOrgInspection poi INNER JOIN po.partner ptr where po.isCEApproved=null OR poi.isCEApproved=null OR ptr.isCEApproved=null )");
		}
		
		return where.toString();
	}
	public String getPartnerOrgQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT o from PartnerOrg o")
		.append(" INNER JOIN FETCH o.location l ")
		.append(" INNER JOIN FETCH l.partner p")
		.append(" INNER JOIN FETCH l.createdBy c")
		.append(" INNER JOIN FETCH l.updatedBy u")
		.append(" INNER JOIN FETCH l.country cn")
		.append(" INNER JOIN FETCH l.region r")
		.append(" INNER JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH o.createdBy oc")
		.append(" INNER JOIN FETCH o.updatedBy ou")	
		.append(" LEFT JOIN FETCH o.licenceCopy lc")	
		.append(" LEFT JOIN FETCH o.machinaryListCopy mc")	
		.append(" LEFT JOIN FETCH o.inspectionReportCopy irc")
		.append(" LEFT JOIN FETCH o.staffListCopy slc")
		.append(" LEFT JOIN FETCH o.authorizationCertificate ac")
		.append(" LEFT JOIN FETCH o.testingEquipmentDetails ted ")
		.append(" LEFT JOIN FETCH o.paymentDetail pd")	
		.append(" WHERE o.partner.bPartnerId = :partnerId" );
		return query.toString();
	}
	
	public String getDefaultFactoryAddress(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT f FROM PartnerOrg f ")
		.append(" LEFT JOIN FETCH f.location l ")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" WHERE f.partnerOrgId = :partnerOrgId ORDER BY f.created ASC " );
		/*.append(" WHERE l.created=( SELECT Min(l.created) from Location WHERE l.partner.bPartnerId = :partnerId)" );*/
		return query.toString();
	}
	
	public String getFactoryListQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT f FROM PartnerOrg f ")
		.append(" WHERE f.partner.bPartnerId = :partnerId " )
		.append(" AND f.partnerItemManufacturer.partnerItemManufacturerId IS NULL " );
		
		return query.toString();
	}
	public String getPartnerOrgByPaymentId(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT po FROM PartnerOrg po ")
		.append(" WHERE po.partner.bPartnerId =:partnerId  AND po.paymentDetail.paymentDetailId=:paymentId" );
		return query.toString();
	}
	
	public String getPartnerManufacturerOrgsQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT o from PartnerOrg o")
		.append(" INNER JOIN FETCH o.location l ")
		.append(" INNER JOIN FETCH l.country cn")
		.append(" INNER JOIN FETCH l.region r")
		.append(" INNER JOIN FETCH l.district d")
		.append(" INNER JOIN FETCH o.partnerItemManufacturer itm")
		.append(" LEFT JOIN FETCH o.licenceCopy lc")	
		.append(" LEFT JOIN FETCH o.machinaryListCopy mc")	
		.append(" LEFT JOIN FETCH o.inspectionReportCopy irc")	
		.append(" LEFT JOIN FETCH o.staffListCopy slc")
		.append(" LEFT JOIN FETCH o.authorizationCertificate ac")
		.append(" LEFT JOIN FETCH o.testingEquipmentDetails ted ")
		.append(" WHERE o.partnerItemManufacturer.partnerItemManufacturerId=:partnerManufacturerId ");
		return query.toString();
	}
	
	public String getQueryForOrgApprovalCheck()
	{
		StringBuilder jpql=new StringBuilder(" Select po From PartnerOrg po ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgUser ou ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgExperience exp ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgCertification cer ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgRegistration reg ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgBIS bis ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgProduct prd ");
		jpql.append(" LEFT JOIN FETCH po.partnerOrgPerformance per ");
		/*jpql.append(" LEFT JOIN FETCH po.partnerOrgRDAEC rdaec ");*/
		jpql.append(" LEFT JOIN FETCH po.partnerOrgOE ooe ");
		jpql.append(" WHERE po.partnerOrgId=:partnerOrgId ");
		return jpql.toString();		
	}

	public String getQueryForOrgRenewal()
	{
		StringBuilder jpql=new StringBuilder(" Select o from PartnerOrg o ")
		.append(" LEFT JOIN FETCH o.createdBy cb ")
		.append(" LEFT JOIN FETCH o.location l ")
		.append(" LEFT JOIN FETCH o.partner p ")
	    .append(" where Trunc(o.validTo)<:currentDate and (o.isRenewed<>'N' or o.isRenewed is null)");
		return jpql.toString();
	}
	
	public String getPartnerOrgByPartnerId()
	{
		StringBuilder jpql=new StringBuilder(" Select o from PartnerOrg o ")
		.append(" where o.partner.bPartnerId=:partnerId and o.partnerItemManufacturer.partnerItemManufacturerId IS NULL ");
		return jpql.toString();
	}
    public String getPartnerOrgForRenewal()
    {
    	StringBuilder jpql=new StringBuilder(" Select o from PartnerOrg o ")
        .append(" where o.partner.bPartnerId=:partnerId AND o.partnerItemManufacturer.partnerItemManufacturerId IS NULL AND o.isRenewed='N' ");
    	return jpql.toString();
    }
    public String getQueryForOrgValidity(){
    	StringBuilder jpql=new StringBuilder(getPartnerOrgByPartnerId())
    			.append(" AND (o.isRenewed='N' OR Trunc(o.validTo)<:date) ");
    	return jpql.toString();
    }
    public String getQueryForOrgRenewalReminder(){
    	StringBuilder jpql=new StringBuilder(" Select o from PartnerOrg o ")
    	.append(" INNER JOIN FETCH o.createdBy cb ")
    	.append(" INNER JOIN FETCH o.partner p ")
    	.append(" INNER JOIN FETCH o.location ol ")
    	.append(" where Trunc(o.validTo)=:date ");
    	return jpql.toString();
    }
    public String getOrgWithValidDate(){
    	StringBuilder jpql=new StringBuilder(getPartnerOrgByPartnerId())
    			.append(" AND  o.validTo IS NOT NULL ");
    	return jpql.toString();
    }
    public String getPartnerOrgById(){
    	StringBuilder jpql=new StringBuilder("Select po from PartnerOrg po ")
    	.append(" LEFT JOIN FETCH po.partner p ")
    	.append(" LEFT JOIN FETCH po.paymentDetail pd ")
    	.append(" LEFT JOIN FETCH pd.paymentType pt ")
    	.append(" where po.partnerOrgId=:partnerOrgId ");
    	return jpql.toString();
    }
    public String getPlainPartnerOrgById(){
    	StringBuilder jpql=new StringBuilder("Select po from PartnerOrg po ")
    	.append(" where po.partnerOrgId=:partnerOrgId ");
    	return jpql.toString();
    }
}
