package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommitteeParticipant;
/**
 * 
 * @author varsha
 *
 */
public interface TenderCommitteeParticipantService extends CommonService<TenderCommitteeParticipant, TenderCommitteeParticipantDto>{

	public boolean checkTenderOppening(Long tenderCommiiteeId);
	
	public boolean updateSessionKey(Long committeeParticipantId,String sessionKey);
	
	public boolean sendSessionKeyToParticipant(List<TenderCommitteeParticipantDto> participantList,String tenderNo);
}
