package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MBPartnerInvitationDto;
import com.novelerp.appbase.master.service.InternalUserService;
import com.novelerp.appbase.master.service.SendInvitationService;
import com.novelerp.appbase.master.service.SendRequestToParticipantService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
/**
 * 
 * @author Sanjeev
 *
 */
@Controller
@RequestMapping("/invite")
public class SendRequestForParticipationController {
	private static final Logger log=LoggerFactory.getLogger(SendRequestForParticipationController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private SendInvitationService sendInvitationService;
	
	@Autowired
	private InternalUserService internalUserService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@Autowired
	private SendRequestToParticipantService sendRequestToParticipantService;
	/*@RequestMapping(value = "/SendInvitation", method = RequestMethod.GET)
	public ModelAndView companyTypeView() {
		ModelAndView modelAndView = new ModelAndView("companyType");
		return modelAndView;
	}
	*/
	@RequestMapping(value = "/getInvitationList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MBPartnerInvitationDto> getInvitationList() {
		//Set<RoleDto> role=contextService.getRoles();
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<MBPartnerInvitationDto> invitationList = sendInvitationService.findDtos("getInvitationByBPartner", params);
		/*List<BPartnerMapDto> bPartnerMapList = sendRequestToParticipantService.findDtos("getIRequestByBPartner", params);*/
		
		return invitationList;
	}
	
	@RequestMapping(value= {"/inviteParticipant"},method =RequestMethod.GET)
	public ModelAndView inviteParticipant(){
		ModelAndView model=new ModelAndView("inviteParticipant");
		model.addObject("quickParticipation", false);
		return model;
	}
	
	@RequestMapping(value= {"/inviteQuickParticipant"},method =RequestMethod.GET)
	public ModelAndView inviteQuickParticipant(){
		ModelAndView model=new ModelAndView("inviteParticipant");
		model.addObject("quickParticipation", true);
		return model;
	}
	
	@RequestMapping(value="/getBpartnerForInvitation/{panNo}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto save(@PathVariable("panNo") String panNo){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		Map<String, Object> params= new HashMap<>();
		params.put("partnerId", partner.getbPartnerId());
		params.put("PanNo", panNo);
		MBPartnerInvitationDto mBpartnerInvitationdto =sendInvitationService.findDto("getQueryForInvitedVendor", params);
		if(mBpartnerInvitationdto == null){
			UserDto user=userService.findDto("getUserQueryForInvitation", AbstractContextServiceImpl.getParamMap("PanNo", panNo));
			response.addObject("user", user);
			String access=sysConfiguratorService.getPartnerPropertyConfigurator("invite.request.user", partner.getbPartnerId());
			response.addObject("access", access);
		}else{
			response.setMessage("Vendor Already Invited");
			response.setSuccess(false);
		}
		return response ;
	}
	@RequestMapping(value="/getPartnerById/{id}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody MBPartnerInvitationDto getPartnerByPan(@PathVariable("id") Long id){

		
		MBPartnerInvitationDto invitationList = sendInvitationService.findDto(id);
		/*List<BPartnerMapDto> bPartnerMapList = sendRequestToParticipantService.findDtos("getIRequestByBPartner", params);*/
		
		return invitationList;
	}
	
	/*@RequestMapping(value="/rest/sendInvitationMail", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto sendInvitationMail(@ModelAttribute("userDto") UserDto user){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto bpartnetdto =	contextService.getPartner();
		Map<String, Object> params= new HashMap<>();
		params.put("partnerId", bpartnetdto.getbPartnerId());
		params.put("PanNo", user.getPartner().getPanNumber());
		MBPartnerInvitationDto mBPartnerDto=sendInvitationService.findDto("getQueryForInvitedVendor", params);
		if(mBPartnerDto==null){
				mBPartnerDto =sendInvitationService.saveInvitationDetails(bpartnetdto,null, user,false);
			if(!mBPartnerDto.getResponse().isHasError()){
				MailDto mailDto = new MailDto();
				mailDto.setSubject("Invitation From '"+bpartnetdto.getName()+"'");
				mailDto.setMailContent("<p>Hi </p><br><p>You have been invited by "+bpartnetdto.getName()+", as his Partner.After Registering Do approve the requesting of invitation in Pending Request tile. ");			
				mailDto.setSingleRecipient(user.getEmail());
				mailService.sendSingleEmailWithResult(mailDto,true);
			}	
			boolean result=mBPartnerDto.getResponse().isHasError()==false?true:false;
			response.addObject("RESULT_MESSAGE", mBPartnerDto.getResponse().getMessage());
			response.addObject("RESULT_STATUS", result);
		}else{
				response.addObject("RESULT_MESSAGE", "Vendor already invited");
				response.addObject("RESULT_STATUS",false);
		}
		return response;
	}*/
	
	
	@RequestMapping(value="/rest/sendInvitationMail", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto sendInvitationMailWithEmail(@RequestBody UserDto user){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto bpartnetdto =	contextService.getPartner();
		Map<String, Object> params= new HashMap<>();
		params.put("partnerId", bpartnetdto.getbPartnerId());
		params.put("email", user.getEmail());
		MBPartnerInvitationDto mBPartnerDto=sendInvitationService.findDto("getQueryForInvitedVendorByEmail", params);
		if(mBPartnerDto==null){
				mBPartnerDto =sendInvitationService.saveInvitationDetails(bpartnetdto,null, user,false);
			if(!mBPartnerDto.getResponse().isHasError()){
				MailDto mailDto = new MailDto();
				mailDto.setSubject("Invitation From '"+bpartnetdto.getName()+"'");
				mailDto.setMailContent("<p>Hi </p><br><p>You have been invited by "+bpartnetdto.getName()+", as his Partner.After Registering Do approve the requesting of invitation in Pending Request tile. ");			
				mailDto.setSingleRecipient(user.getEmail());
				mailService.sendSingleEmailWithResult(mailDto,true);
			}	
			boolean result=mBPartnerDto.getResponse().isHasError()==false?true:false;
			response.addObject("RESULT_MESSAGE", mBPartnerDto.getResponse().getMessage());
			response.addObject("RESULT_STATUS", result);
		}else{
				response.addObject("RESULT_MESSAGE", "Vendor already invited");
				response.addObject("RESULT_STATUS",false);
		}
		return response;
	}
	
	@RequestMapping(value="/sendInvitationMailForQuickAuction", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto sendInvitationMailForQuickAuction(@ModelAttribute("userDto") UserDto user){ 
		CustomResponseDto response=new CustomResponseDto();
	        BPartnerDto bpartnetdto =	contextService.getPartner();
	        UserDto sessionUser =contextService.getUser();
	        if(bpartnetdto!=null && sessionUser!=null){
	        	response=internalUserService.createNewInvitedUser(user);
	        	boolean isUserAdded=response.getObjectMap().get("RESULT_STATUS")==null?false:(boolean) response.getObjectMap().get("RESULT_STATUS");
	        	if(isUserAdded){
	        		String password=response.getObjectMap().get("GeneratedPassword")==null?"":(String) response.getObjectMap().get("GeneratedPassword");
	        		BPartnerDto requestedPartner=response.getObjectMap().get("GeneratedPartner")==null?null:(BPartnerDto) response.getObjectMap().get("GeneratedPartner");
	        		com.novelerp.core.dto.ResponseDto invitationResp = new com.novelerp.core.dto.ResponseDto();
	        		MBPartnerInvitationDto mBPartnerDto =sendInvitationService.saveInvitationDetails(bpartnetdto,requestedPartner, user,true);
	        		invitationResp=mBPartnerDto.getResponse();
	        		if(invitationResp!=null && !invitationResp.isHasError()){
	        			response.addObject("RESULT_MESSAGE", "User Added Successfully And invited");
						response.addObject("RESULT_STATUS",true);
	        		}else{
	        			response.addObject("RESULT_MESSAGE", "User not Added But inviation failed");
						response.addObject("RESULT_STATUS",false);
	        		}
	        		MailDto mailDto = new MailDto();
					mailDto.setSubject("Auto Generated Password Login");
					mailDto.setMailContent("<p>Hi "+user.getName()+"</p><br><p>You have been invited by "+bpartnetdto.getName()+"("+sessionUser.getEmail()+"), as his partner. </p><br><p>Your Password for login is - <p>"+ password);
					mailDto.setSingleRecipient(user.getEmail());
					mailService.sendSingleEmailWithResult(mailDto,true);
	        	}
	        }
			return response;
	}
	

}
