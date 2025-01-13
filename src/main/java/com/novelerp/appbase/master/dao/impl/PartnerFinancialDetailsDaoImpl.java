package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerFinancialDetailsDao;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.entity.PartnerFinancialAttachment;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author varsha
 *
 */
@Repository
public class PartnerFinancialDetailsDaoImpl extends  AbstractJpaDAO<PartnerFinancialAttachment, PartnerFinancialAttachmentDto> implements PartnerFinancialDetailsDao{
	@PostConstruct
	public void init() {
		setClazz(PartnerFinancialAttachment.class,PartnerFinancialAttachmentDto.class);
		}
	
	public String getPartnerFinancialDetailsQuery()
	{
		StringBuilder query=new StringBuilder();
		query.append("select fa from PartnerFinancialAttachment fa ")
		.append(" LEFT JOIN FETCH fa.partner p")
		.append(" LEFT JOIN FETCH fa.attachment a")
		.append(" LEFT JOIN FETCH fa.financialYear fy")
        .append(" WHERE fa.partner.bPartnerId = :partnerId  ")
		.append(" AND (fy.financialYearId IN( Select y.financialYearId from FinancialYear y ")
		.append(" WHERE (now() between y.validfrom and y.validTo) ")
		.append(" OR  (:lastYear between y.validfrom and y.validTo) ")
		.append(" OR  (:lastTwoYear between y.validfrom and y.validTo)) OR fa.finacialType=:finacialType ) ")
		.append(" order by fy.name desc ");
        return query.toString();
	}
	
//	public String getFinancialDetailsQuery()
//	{
//		StringBuilder query=new StringBuilder();
//		query.append("select fa from PartnerFinancialAttachment fa ")
//		.append(" LEFT JOIN FETCH fa.partner p")
//		.append(" LEFT JOIN FETCH fa.attachment a")
//		.append(" LEFT JOIN FETCH fa.financialYear fy")
//        .append(" WHERE fa.partner.bPartnerId = :partnerId  ")
//		.append(" AND (fy.financialYearId IN( Select y.financialYearId from FinancialYear y ")
//		.append(" WHERE (now() between y.validfrom and y.validTo) ")
//		.append(" OR  (:lastYear between y.validfrom and y.validTo) ")
//		.append(" OR  (:lastTwoYear between y.validfrom and y.validTo))) ")
//		.append(" order by fy.name desc ");
//        return query.toString();
//	}
	public String getFinancialDetailsQuery()
	{
		StringBuilder query=new StringBuilder();
		query.append("select fa from PartnerFinancialAttachment fa ")
		.append(" LEFT JOIN FETCH fa.partner p")
		.append(" LEFT JOIN FETCH fa.attachment a")
        .append(" WHERE fa.partner.bPartnerId = :partnerId  ")
        .append(" order by fa.financialYearNew desc ");
        return query.toString();
	}
	
	public String getPartnerFinancialDocsQuery()
	{
		StringBuilder query=new StringBuilder();
		query.append("select fa from PartnerFinancialAttachment fa ")
		.append(" LEFT JOIN FETCH fa.partner p")
		.append(" LEFT JOIN FETCH fa.attachment a")
		.append(" LEFT JOIN FETCH fa.financialYear fy")
        .append(" WHERE fa.partner.bPartnerId = :partnerId  ");
        return query.toString();
	}
	public String getFinancialDetailsByCurrentDate()
	{
		StringBuilder query=new StringBuilder();
		query.append("select fa from PartnerFinancialAttachment fa ")
		.append(" LEFT JOIN FETCH fa.partner p")
		.append(" LEFT JOIN FETCH fa.attachment a")
		.append(" LEFT JOIN FETCH fa.financialYear fy")
        .append(" WHERE fa.partner.bPartnerId = :partnerId  ")
		.append(" AND (fy.financialYearId IN( Select y.financialYearId from FinancialYear y ")
		.append(" WHERE (sysdate between y.validfrom and y.validTo))) ")
		.append(" order by fy.name desc ");
        return query.toString();
	}
	

	public String getFinancialYear(){
		
		StringBuilder query=new StringBuilder();
		query.append("select financialYearNew  from PartnerFinancialAttachment fa ")
		.append(" LEFT JOIN FETCH fa.partner p")
		
        .append(" WHERE fa.partner.bPartnerId = :partnerId  ");
        return query.toString();
	}
}
