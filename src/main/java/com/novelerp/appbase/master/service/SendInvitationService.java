package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MBPartnerInvitationDto;
import com.novelerp.appbase.master.entity.MBPartnerInvitation;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;

public interface SendInvitationService extends CommonService<MBPartnerInvitation, MBPartnerInvitationDto>{
	
	public MBPartnerInvitationDto saveInvitationDetails(BPartnerDto bpartnerdto,BPartnerDto requestedPartner, UserDto userdto,boolean isAutoApproved);
	public List<MBPartnerInvitation> getPendingRequestList();
	public CustomResponseDto updateInvitationStation(MBPartnerInvitationDto invitationList);

}
