package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.InvitationAuctionParticipantDto;
import com.novelerp.appbase.master.service.CompanyTypeService;
import com.novelerp.appbase.master.service.MAuctionParticipantService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class AuctionInvitationController {
	private final Logger log = LoggerFactory.getLogger(AuctionInvitationController.class);
	
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private CompanyTypeService companyTypeService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private MAuctionParticipantService mAuctionParticipantService;
	@Autowired
	private UserService userService;
	@Autowired
	private BPartnerService partnerService;
	
	@RequestMapping(value = "/auctionInvitation", method = RequestMethod.GET)
	public ModelAndView MaterialView() {
		ModelAndView mv=new ModelAndView("auctionInvitation");
		mv.addObject("regionList", regionService.findAll());
		/*mv.addObject("companyTypeList", companyTypeService.getCompanyTypeList());*/
		
		return  mv;
	}
	@RequestMapping(value="/invitationAuctionParticipant",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto invitationAuctionParticipant(@ModelAttribute("InvitationAuctionParticipantDto") InvitationAuctionParticipantDto dto)
	{
		ResponseDto response = new ResponseDto();
		TAHDRDto tahdrdto =tahdrService.findDto(dto.getAuctionId());
		if(tahdrdto!=null){
			mAuctionParticipantService.saveAuctionParticipantMap(dto,tahdrdto);
			List <String> emailList=new ArrayList<>();
			String sub="INVITATION FOT AUCTION";
		     String message="Hi You have been invited for auction";
			for(BPartnerDto partner:dto.getPartners()){
				Map<String, Object> param= new HashMap<>();
				param.put("bPartnerId", partner.getbPartnerId());
			 UserDto user =userService.findDto("getQueryForInvitedUserToSendMail", param);
			 String email=user.getEmail()==null?"":user.getEmail();
		    /* String sub="Auction Invitation";
		     String message="YOU HAVE BEEN INVITATED FOR AUCTION "+tahdrdto.getTahdrCode()+".PLEASE PROCEED TO LIVE BID.";*/
		     
		     if(tahdrdto.getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_QUICK_REVERSE_AUCTION)
						|| tahdrdto.getTahdrTypeCode().equalsIgnoreCase(ContextConstant.TAHDR_TYPE_QUICK_FORWARD_AUCTION)){
		     Map<String, Object> params= new HashMap<>();
		     params.put("partnerId", partner.getbPartnerId());
		     params.put("tahdrId", tahdrdto.getTahdrId());
		     BidderDto bidderDto=bidderService.findDto("getSimpleBidderByTahdrId", params);
		     if(bidderDto==null){
						BidderDto bidder =new BidderDto();
						bidder.setPartner(partner);
						bidder.setTahdr(tahdrdto);
						bidder.setStatus("NEW");
						bidderService.save(bidder);
		     	}
		     }
		     emailList.add(email);
			    /* partnerService.sendEmailToPartner(email, sub, message);*/
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
	@RequestMapping(value="/getRefrenceDropdownList", method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getRefrenceDropdownList(){
		log.debug("Get Logged in Partner data");
		
		Map<String, String> companyTypes = referenceListService.getReferenceListMap(CoreReferenceConstants.COMPANY_TYPE);
		
		CustomResponseDto response = new CustomResponseDto(true,"Success");
		response.addObject("companyTypes", companyTypes);
		
		return response;
	}

}
