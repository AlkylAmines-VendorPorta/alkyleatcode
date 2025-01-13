package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerBankDetailDao;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class PartnerBankDetailDaoImpl extends AbstractJpaDAO<PartnerBankDetail, PartnerBankDetailDto> implements PartnerBankDetailDao{

	@PostConstruct
	public void init(){
		setClazz(PartnerBankDetail.class, PartnerBankDetailDto.class);
	}
	
	public String getBankDetailsQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerBankDetail e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.partner p")
		.append(" LEFT JOIN FETCH e.bankNameDetails bnd")
		.append(" LEFT JOIN FETCH e.branchName bd")
		.append(" LEFT JOIN FETCH bd.branchState bs")
		.append(" LEFT JOIN FETCH e.bankDetailFile bf")
		.append(" WHERE  e.partner.bPartnerId = :bPartnerId");
		return query.toString();
	}
	public String getBankDetailsById(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM PartnerBankDetail e")
		.append(" LEFT JOIN FETCH e.branchName bd")
		.append(" LEFT JOIN FETCH bd.branchState bs")
		.append(" LEFT JOIN FETCH e.bankNameDetails bn")
		.append(" WHERE  e.partnerBankDetailId = :id");
		return query.toString();
	}
}
