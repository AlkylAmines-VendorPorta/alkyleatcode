package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TenderCommitteeParticipantDao;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommitteeParticipant;

/**
 * 
 * @author varsha
 *
 */
@Repository
public class TenderCommitteeParticipantDaoImpl extends AbstractJpaDAO<TenderCommitteeParticipant, TenderCommitteeParticipantDto> implements TenderCommitteeParticipantDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(TenderCommitteeParticipant.class, TenderCommitteeParticipantDto.class);
	}
	public String getQueryForCommitteeParticipant()
	{
		StringBuilder jpql=new StringBuilder("select p from TenderCommitteeParticipant p");
		jpql.append(" LEFT JOIN FETCH p.tenderCommittee tc ");
		jpql.append(" LEFT JOIN FETCH tc.chairPerson cp ");
		jpql.append(" LEFT JOIN FETCH tc.tahdr tah ");
		jpql.append(" LEFT JOIN FETCH p.designation d ");
		jpql.append(" LEFT JOIN FETCH p.user u ");
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" where p.tenderCommittee.tenderCommitteeId=:tenderCommitteId ");
		return jpql.toString();
	}
	
	public String getCommitteeParticipantByCommitteeIdAndParticipantId()
	{
		StringBuilder jpql=new StringBuilder("select p from TenderCommitteeParticipant p");
		jpql.append(" where p.tenderCommittee.tenderCommitteeId=:committeeId AND p.user.userId=:userId");
		return jpql.toString();
	}
	
	public String getQueryForCommitteeWithSessionKey()
	{
		StringBuilder jpql=new StringBuilder("select p from TenderCommitteeParticipant p");
		jpql.append(" LEFT JOIN FETCH p.tenderCommittee tc ");
		jpql.append(" LEFT JOIN FETCH p.user u ");
		jpql.append(" where p.tenderCommittee.tahdr.tahdrId=:tahdrId AND tc.isActive='Y' "
				+ " AND (u.userId=:userId OR tc.chairPerson.userId=:userId ) AND tc.bidOpeningType=:openingType");
		return jpql.toString();
	}
	
	@Override
	public int updateSessionKey(Long committeeParticipantId,String sessionKey) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TenderCommitteeParticipant b SET sessionKey=:sessionKey ");
		jpql.append( " where b.committeeParticipantId=:committeeParticipantId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("committeeParticipantId", committeeParticipantId);
		query.setParameter("sessionKey", sessionKey);
		int count= query.executeUpdate();
		return count;
	}
	@Override
	public int isSessionkeyMailed(Long committeeParticipantId) {
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Update TenderCommitteeParticipant b SET isMailed='Y' ");
		jpql.append( " where b.committeeParticipantId=:committeeParticipantId ");
		
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("committeeParticipantId", committeeParticipantId);
		int count= query.executeUpdate();
		return count;
	}
	
	@Override
	public String getQueryForMailListOfCommitteeParticipantByCommitteeId(){
		
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select cp from TenderCommitteeParticipant cp ");
		jpql.append(" LEFT JOIN FETCH cp.tenderCommittee c ");
		jpql.append(" LEFT JOIN FETCH cp.createdBy cr ");
		jpql.append(" LEFT JOIN FETCH cp.user u ");
		jpql.append(" LEFT JOIN FETCH u.partner p ");
		jpql.append(" LEFT JOIN FETCH c.tahdr t ");
		jpql.append(" LEFT JOIN FETCH u.userDetails ud ");
		jpql.append(" where cp.tenderCommittee.tenderCommitteeId=:committeeId ");
		return jpql.toString();
	}
}
