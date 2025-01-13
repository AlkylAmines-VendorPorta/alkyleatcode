package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.MAuctionParticipantDao;
import com.novelerp.appbase.master.dto.MAuctionParticipantMapDto;
import com.novelerp.appbase.master.entity.MAuctionParticipantMap;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class MAuctionParticipantDaoImpl  extends AbstractJpaDAO<MAuctionParticipantMap, MAuctionParticipantMapDto> implements MAuctionParticipantDao {
	private static final String ENTITY_NAME="MAuctionParticipantMap";
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(MAuctionParticipantMap.class, MAuctionParticipantMapDto.class);
	}
	
	public String getAllSelectedParticipant() {
		 StringBuilder jpql=new StringBuilder("SELECT a from MAuctionParticipantMap a ");
		 jpql.append(" LEFT JOIN FETCH a.bPartner bp ");
		 jpql.append(" LEFT JOIN FETCH a.tahdr th ");
		 jpql.append(" where th.tahdrId =:auctionid");
		return jpql.toString();
	}
}
