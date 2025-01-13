package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PartnerSignatoryDao;
import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Aman
 */

@Repository
public class PartnerSignatoryDaoImpl extends AbstractJpaDAO<PartnerSignatory, PartnerSignatoryDto> implements PartnerSignatoryDao{

	@PostConstruct
	private void init() {
		setClazz(PartnerSignatory.class, PartnerSignatoryDto.class);
	}
	
	public String getPartnerSignatoryQuery(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM PartnerSignatory e")
		.append(" LEFT JOIN FETCH e.createdBy c")
		.append(" LEFT JOIN FETCH e.updatedBy u")
		.append(" LEFT JOIN FETCH e.userDetail ud")
		.append(" LEFT JOIN FETCH ud.designation udd")
		.append(" LEFT JOIN FETCH ud.partner p")
		.append(" LEFT JOIN FETCH ud.createdBy c")
		.append(" LEFT JOIN FETCH ud.updatedBy u")
		.append(" LEFT JOIN FETCH e.location l")
		.append(" LEFT JOIN FETCH l.partner p")
		.append(" LEFT JOIN FETCH l.createdBy c")
		.append(" LEFT JOIN FETCH l.updatedBy u")
		.append(" LEFT JOIN FETCH l.country cn")
		.append(" LEFT JOIN FETCH l.region r")
		.append(" LEFT JOIN FETCH l.district d")
		.append(" LEFT JOIN FETCH e.attorneyCertificate ac")
		.append(" LEFT JOIN FETCH e.digitallySignTestDoc dstc")
		.append(" WHERE e.partner.bPartnerId= :partnerId")
		.append(" AND e.isActive='Y' ");
		return query.toString();
	}

}
