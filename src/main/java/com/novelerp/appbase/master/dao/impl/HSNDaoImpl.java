package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.HSNDao;
import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.entity.HSN;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Ankita 
 *
 */
@Repository
public class HSNDaoImpl extends AbstractJpaDAO<HSN, HSNDto> implements HSNDao {

private static final String ENTITY_NAME="M_HSN";
	
	@PostConstruct
	private void init() {
		setClazz(HSN.class, HSNDto.class);
	}
	
	public String getHSnByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select h from HSN h ")
		.append(" WHERE h.partner.bPartnerId = :BPartnerId")
		.append(" AND h.isActive = 'Y'");
		
		return jpql.toString();
	}
	

}
