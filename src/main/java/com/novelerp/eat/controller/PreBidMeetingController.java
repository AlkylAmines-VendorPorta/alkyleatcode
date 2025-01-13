package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.PreBidMeetingService;

@Controller
public class PreBidMeetingController {
	
	@Autowired
	private PreBidMeetingService preBidMeetingService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value= {"/preBidMeeting"},method =RequestMethod.GET)
	public ModelAndView tenderTechnicalScrutinyView(){
		ModelAndView view=new ModelAndView("preBidMeeting");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionpreBidMeeting"},method =RequestMethod.GET)
	public ModelAndView auctionTechnicalScrutiny(){
		ModelAndView view=new ModelAndView("preBidMeeting");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	@RequestMapping(value = "/getTenderListForPreBidMeeting/{typeCode}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getTenderListForPreBidMeeting(@PathVariable("typeCode") String typeCode) {
		BPartnerDto partnerDto=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partnerDto,typeCode,null);
		return preBidMeetingService.getTenderListForPreBidMeeting(typeCode);
	}
	
	@RequestMapping(value= {"/fetchTenderListForPreBid"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
			@RequestParam("pageSize") int pageSize,
			@RequestParam("searchMode") String searchColumn , 
			@RequestParam("serachValue") String searchValue,@RequestParam("typeCode") String typeCode){
		BPartnerDto partnerDto=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partnerDto,typeCode,null);
		return preBidMeetingService.getTenderList(pageNumber, pageSize, searchColumn, searchValue, typeCode);
	}
	
	@RequestMapping(value = "/saveMom", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto uploadMOM(@ModelAttribute("tahdr")TAHDRDto tahdr) {
		return preBidMeetingService.uploadMOM(tahdr);
	}
	
}
