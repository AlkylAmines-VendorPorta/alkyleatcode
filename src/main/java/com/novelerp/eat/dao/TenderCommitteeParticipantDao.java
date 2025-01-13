package com.novelerp.eat.dao;
/**
 * 
 * @author varsha
 *
 */

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommitteeParticipant;

public interface TenderCommitteeParticipantDao extends CommonDao<TenderCommitteeParticipant, TenderCommitteeParticipantDto>{
	
	public int updateSessionKey(Long committeeParticipantId,String sessionKey);
	
	public int isSessionkeyMailed(Long committeeParticipantId) ;
	
	public String getQueryForMailListOfCommitteeParticipantByCommitteeId();

}
