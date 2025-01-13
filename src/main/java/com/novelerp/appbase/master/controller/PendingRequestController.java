package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MBPartnerInvitationDto;
import com.novelerp.appbase.master.service.SendInvitationService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
@Controller
public class PendingRequestController {
	private static final Logger log=LoggerFactory.getLogger(PendingRequestController.class);
	
	@Autowired
	private SendInvitationService sendInvitationService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@RequestMapping(value= {"/pendingRequest"},method =RequestMethod.GET)
	public ModelAndView inviteParticipant(){
		sendInvitationService.getPendingRequestList();
		return new ModelAndView("pendingRequest");
	}
	
	@RequestMapping(value = "/getInvitationsList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MBPartnerInvitationDto> getInvitationList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("PanNo", bPartnerDto.getPanNumber());
		List<MBPartnerInvitationDto> invitationList = sendInvitationService.findDtos("getInvitationBPartnerByPan", params);
		/*List<BPartnerMapDto> bPartnerMapList = sendRequestToParticipantService.findDtos("getIRequestByBPartner", params);*/
		return invitationList;
	}
	
	@RequestMapping(value="/getInvitationListId/{id}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody MBPartnerInvitationDto getPartnerByPan(@PathVariable("id") Long id){

		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("invitationID",id);
		MBPartnerInvitationDto invitationList = sendInvitationService.findDto("getInvitationListByID", params);
		/*List<BPartnerMapDto> bPartnerMapList = sendRequestToParticipantService.findDtos("getIRequestByBPartner", params);*/
		
		return invitationList;
	}
	@RequestMapping(value="/updateStatus/{id}/{status}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto updateStatus(@PathVariable("id") Long id ,@PathVariable("status") String status){
		CustomResponseDto resp=new CustomResponseDto();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("invitationID",id);
		MBPartnerInvitationDto invitationList = sendInvitationService.findDto("getInvitationListByID", params);
		if(status.equals("approve")){
			invitationList.setIsInvitationApproved("Y");
		}
		if(status.equals("reject")){
			invitationList.setIsInvitationApproved("R");
		}
		/*MBPartnerInvitationDto resdto =sendInvitationService.updateDto(invitationList);*/
		resp =sendInvitationService.updateInvitationStation(invitationList);
		return resp;
	}
}
