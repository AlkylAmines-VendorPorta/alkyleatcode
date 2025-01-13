package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.RatingWeightageService;
import com.novelerp.eat.service.TahdrAwardWinnerService;

@Controller
public class RatingController {
	@Autowired
	RatingWeightageService ratingWeightageService;
	@Autowired
	BPartnerService bPartnerService;
	@Autowired
	BidderService bidderService;

	private static Logger log = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	private TahdrAwardWinnerService tahdrAwardService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@RequestMapping(value = { "/rating" }, method = RequestMethod.GET)
	public ModelAndView rating() {
		ModelAndView modelAndView = new ModelAndView("vendorRating");
		modelAndView.addObject("type", "rating");
		modelAndView.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		return modelAndView;
	}
	@RequestMapping(value = { "/auctionRating" }, method = RequestMethod.GET)
	public ModelAndView auctionRating() {
		ModelAndView modelAndView = new ModelAndView("vendorRating");
		modelAndView.addObject("type", "rating");
		modelAndView.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/rfqRating" }, method = RequestMethod.GET)
	public ModelAndView rfqRating() {
		ModelAndView modelAndView = new ModelAndView("vendorRating");
		modelAndView.addObject("type", "rating");
		modelAndView.addObject("documentType",ContextConstant.DOCUMENT_REQUEST_FOR_PROPOSAL);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/quickRfqRating" }, method = RequestMethod.GET)
	public ModelAndView quickRfqRating() {
		ModelAndView modelAndView = new ModelAndView("vendorRating");
		modelAndView.addObject("type", "rating");
		modelAndView.addObject("documentType",ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/quickAuctionRating" }, method = RequestMethod.GET)
	public ModelAndView quickAuctionRating() {
		ModelAndView modelAndView = new ModelAndView("vendorRating");
		modelAndView.addObject("type", "rating");
		modelAndView.addObject("documentType",ContextConstant.DOCUMENT_TYPE_QUICK_AUCTION);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/getTahdrForRating/{typeCode}" },method =RequestMethod.POST)
	public@ResponseBody CustomResponseDto getTahdrForRating(@PathVariable ("typeCode") String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("typeCode", typeCode);
		if(typeCode.equalsIgnoreCase("PT") || typeCode.equalsIgnoreCase("WT")
        || typeCode.equalsIgnoreCase("FA") ||typeCode.equalsIgnoreCase("RA")){
			params.put("status", AppBaseConstant.TENDER_AWARD_WINNER_COMPLETED);	
		}else if(typeCode.equalsIgnoreCase("RFQ")){
			params.put("status", AppBaseConstant.RFQ_AWARD_WINNER_COMPLETED);
		}else if(typeCode.equalsIgnoreCase("QRFQ")){
			params.put("status", AppBaseConstant.QUICK_RFQ_AWARD_WINNER_COMPLETED);
		}else if(typeCode.equalsIgnoreCase("QRA") || typeCode.equalsIgnoreCase("QFA")){
			params.put("status", AppBaseConstant.QUICK_AUCTION_AWARD_WINNER_COMPLETED);
		}
		
		List<WinnerSelectionDto> WinnerSelectionList=tahdrAwardService.findDtos("getWinnerListByTypecodeandStatus", params);
		response.addObject("WinnerSelectionList", WinnerSelectionList);
		return response;
	}

	@RequestMapping(value="/saveRating", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto saveRatting(@ModelAttribute("ratting") WinnerSelectionDto winnerSelectionDto) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		BPartnerDto bPartner = contextService.getPartner();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bpartnerID", bPartner.getbPartnerId());
		RatingWeightageDto ratingWeightageDto = ratingWeightageService.findOne("m_bpartner_id=:bpartnerID", params,null);
		if(ratingWeightageDto==null){
			params.put("bpartnerID", 1);
			ratingWeightageDto = ratingWeightageService.findOne("m_bpartner_id=:bpartnerID ", params,null);
			/*customResponseDto.addObject("result", false);
			customResponseDto.addObject("message", "Rating Wieghtage Not submitted yet");
			return customResponseDto;*/
		}
		customResponseDto=tahdrAwardService.calculateRating(winnerSelectionDto, ratingWeightageDto);
		boolean isRatingCalculated=customResponseDto.getObjectMap().get("result")==null?false:(boolean) customResponseDto.getObjectMap().get("result");
		if(isRatingCalculated){
			Map<String , Object > param = new HashMap<String, Object>();
			param.put("bidderId", winnerSelectionDto.getItemBid().getBidder().getBidderId());
			BidderDto bidderDto = bidderService.findDto("getBidder",param);  //winnerSelectionDto.getBidder().getBidderId()
			BPartnerDto bPartnerDto = bPartnerService.findDto(bidderDto.getPartner().getbPartnerId());
			int resultCount=tahdrAwardService.updateRating(customResponseDto, winnerSelectionDto, bidderDto, bPartnerDto);
			if(resultCount==1){	
				customResponseDto.addObject("result", true);
				customResponseDto.addObject("message", "Rating Updated Successfully");
			}else{
				customResponseDto.addObject("result", false);
				customResponseDto.addObject("message", "Rating Not submitted");
			}
		}else{
			customResponseDto.addObject("result", false);
			customResponseDto.addObject("message", "Rating Calculating issue");
		}
		return customResponseDto;
	}
}
