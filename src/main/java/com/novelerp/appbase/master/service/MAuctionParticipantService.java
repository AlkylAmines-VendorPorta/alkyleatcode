package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.InvitationAuctionParticipantDto;
import com.novelerp.appbase.master.dto.MAuctionParticipantMapDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.MAuctionParticipantMap;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TAHDRDto;

public interface MAuctionParticipantService extends CommonService<MAuctionParticipantMap, MAuctionParticipantMapDto>{

	public ResponseDto saveAuctionParticipantMap(InvitationAuctionParticipantDto dto,TAHDRDto tahdto);
	public MAuctionParticipantMapDto getALLSelectedParticipant(Long auctionID);
}
