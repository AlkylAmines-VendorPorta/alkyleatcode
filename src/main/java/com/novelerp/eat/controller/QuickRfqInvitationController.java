package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.InvitationAuctionParticipantDto;
import com.novelerp.appbase.master.service.MAuctionParticipantService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class QuickRfqInvitationController {
	
	private final Logger log = LoggerFactory.getLogger(AuctionInvitationController.class);
	
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private MAuctionParticipantService mAuctionParticipantService;
	@Autowired
	private UserService userService;
	@Autowired
	private BPartnerService partnerService;
	
	@RequestMapping(value = "/quickRfqInvitation", method = RequestMethod.GET)
	public ModelAndView MaterialView() {
		ModelAndView mv=new ModelAndView("quickRfqInvitation");
		mv.addObject("regionList", regionService.findAll());
		
		return  mv;
	}
	
	@RequestMapping(value = "/getQuickRFQ", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TAHDRDto> getQuickRFQ() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TAHDRDto> getQuickRFQ = tahdrService.findDtos("getQuickRFQ", params);
		
		return getQuickRFQ;
	}
	
	@RequestMapping(value="/invitationQuickRfqParticipant",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto invitationQuickRfqParticipant(@ModelAttribute("InvitationAuctionParticipantDto") InvitationAuctionParticipantDto dto)
	{
		ResponseDto response = new ResponseDto();
		TAHDRDto tahdrdto =tahdrService.findDto(dto.getAuctionId());
		if(tahdrdto!=null){
			mAuctionParticipantService.saveAuctionParticipantMap(dto,tahdrdto);
			List <String> emailList=new ArrayList<>();
			String sub="INVITATION FOR Quick RFQ";
		     String message="Hi You have been invited for Quick RFQ";
			for(BPartnerDto partner:dto.getPartners()){
				Map<String, Object> param= new HashMap<>();
				param.put("bPartnerId", partner.getbPartnerId());
			 UserDto user =userService.findDto("getQueryForInvitedUserToSendMail", param);
			 String email=user.getEmail()==null?"":user.getEmail();
		     
		     if(tahdrdto.getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_QUICK_RFQ)
						|| tahdrdto.getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_RFQ)){
		     Map<String, Object> params= new HashMap<>();
		     params.put("partnerId", partner.getbPartnerId());
		     params.put("tahdrId", tahdrdto.getTahdrId());
		     BidderDto bidderDto=bidderService.findDto("getSimpleBidderByTahdrId", params);
		     if(bidderDto==null){
						BidderDto bidder =new BidderDto();
						bidder.setPartner(partner);
						bidder.setTahdr(tahdrdto);
						bidder.setStatus("DR");
						bidderService.save(bidder);
		     	}
		     }
		     emailList.add(email);
			}
			if(!CommonUtil.isCollectionEmpty(emailList)){
				partnerService.sendEmailToPartner(emailList, sub, message);
			}
			response.setHasError(false);
			response.setMessage("Invitation Send to vendors");
			return response;
		}else{
			response.setHasError(true);
			response.setMessage("Something went wrong !");
			return response;
		}
	}

}
