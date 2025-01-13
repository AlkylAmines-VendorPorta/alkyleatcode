package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.SendRequestToParticipantDao;
import com.novelerp.appbase.master.dto.BPartnerMapDto;
import com.novelerp.appbase.master.entity.BPartnerMap;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class SendRequestToParticipantDaoImpl extends AbstractJpaDAO<BPartnerMap, BPartnerMapDto>  implements SendRequestToParticipantDao {

	private static final String ENTITY_NAME="m_bpartner_map";
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(BPartnerMap.class, BPartnerMapDto.class);
	}
	
	public String getIRequestByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select i from BPartnerMap i ")
		.append(" WHERE i.partner.bPartnerId = :BPartnerId")
		.append(" AND i.isRequestApproved = 'N'");
		
		return jpql.toString();
	}
}
