package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.service.BidsService;
import com.novelerp.eat.service.LiveBidService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.ScrutinyFileService;
import com.novelerp.eat.service.TahdrAwardWinnerService;
import com.novelerp.eat.service.TempPriceBidService;


/*createdby: Aman Sahu*/

@Controller
public class BidsController {
	
	@Autowired
	private BidsService bidsService;
	
	@Autowired
	private ScrutinyFileService scrutinyFileService;
	
	@Autowired
	private LiveBidService liveBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private TempPriceBidService tempPriceBidService;
	
	@Autowired
	private TahdrAwardWinnerService tahdrAwardWinnerService;

	@RequestMapping(value= {"/tenderBids"},method =RequestMethod.GET)
	public ModelAndView tenderBids(){
		ModelAndView view=new ModelAndView("tenderBids");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/newTenderBids"},method =RequestMethod.GET)
	public ModelAndView newTenderBids(){
		ModelAndView view=new ModelAndView("newTenderBids");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value= {"/newAuctionBids"},method =RequestMethod.GET)
	public ModelAndView newAuctionBids(){
		ModelAndView view=new ModelAndView("newTenderBids");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/auctionBids"},method =RequestMethod.GET)
	public ModelAndView auctionBids(){
		ModelAndView view=new ModelAndView("tenderBids");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}

	@RequestMapping(value= {"/getTenders"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@RequestParam("pageNumber")int pageNumber, 
														@RequestParam("pageSize") int pageSize,
														@RequestParam("searchMode") String searchColumn , 
														@RequestParam("serachValue") String searchValue,
														@RequestParam("typeCode") String typeCode){
		return bidsService.getTenderList(pageNumber, pageSize, searchColumn, searchValue, typeCode);
	}
	
	@RequestMapping(value= {"/getBidderList/{tahdrId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderList(@PathVariable("tahdrId") Long tahdrId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		if(tahdrId!=null && tahdrMaterialId!=null){
			response=bidsService.getBidderList(tahdrId, tahdrMaterialId);
		}else{
			response.addObject("result", false);
			response.addObject("message", "Request is not valid");
		}
		return response;
	}
	
	@RequestMapping(value= {"/getBidderBids/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderBids(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		response=bidsService.getBidderBids(bidderId, tahdrMaterialId);
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerTechnicalBidData/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerTechnicalBids(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		response.addObject("technicalBid", bidsService.getTechnicalBid(technicalParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerCommercialBidData/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerCommercialBids(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> commercialParams= new HashMap<>();
		commercialParams.put("bidderId", bidderId);
		response.addObject("commercialBid", bidsService.getCommercialBid(commercialParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerPriceBidData/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerPriceBids(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		response.addObject("priceBid", bidsService.getPriceBid(technicalParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerFinalTechnicalScrutinyData/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerFinalTechnicalScrutinyData(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		technicalParams.put("scrutinyType", "TECHSCR");
		technicalParams.put("scrutinyLevel", "FINAL");
		response.addObject("technicalScrutiny", bidsService.getFinalTechnicalScrutinyBid(technicalParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerPreTechnicalScrutinyData/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerPreTechnicalScrutinyData(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		technicalParams.put("scrutinyType", "TECHSCR");
		technicalParams.put("scrutinyLevel", "PRELIMINARY");
		response.addObject("technicalScrutiny", bidsService.getPreTechnicalScrutinyBid(technicalParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerFinalCommercialScrutinyData/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerFinalCommercialScrutinyData(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> commercialParams= new HashMap<>();
		commercialParams.put("bidderId", bidderId);
		commercialParams.put("scrutinyType", "COMMSCR");
		commercialParams.put("scrutinyLevel", "FINAL");
		response.addObject("commercialScrutiny", bidsService.getFinalCommercialScrutinyBid(commercialParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerPreCommercialScrutinyData/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerPreCommercialScrutinyData(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> commercialParams= new HashMap<>();
		commercialParams.put("bidderId", bidderId);
		commercialParams.put("scrutinyType", "COMMSCR");
		commercialParams.put("scrutinyLevel", "PRELIMINARY");
		response.addObject("commercialScrutiny", bidsService.getPreCommercialScrutinyBid(commercialParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPartnerTempPriceBidData/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerTempPriceBids(@PathVariable("bidderId") Long bidderId,@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> technicalParams= new HashMap<>();
		technicalParams.put("bidderId", bidderId);
		technicalParams.put("tahdrMaterialId", tahdrMaterialId);
		response.addObject("technicalBid", bidsService.getTempPriceBid(technicalParams));
		return response;
	}
	
	@RequestMapping(value= {"/getPreliminaryScrutinyFiles/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPreliminaryScrutinyFiles(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("scrutinyLevel", "PRELIMINARY");
		params.put("scrutinyType", "TECHSCR");
		List<ScrutinyFileDto> preliminaryTechScrutiny=scrutinyFileService.findDtos("getTechnicalScrutinyFileByBidderId", params);
		
		params.put("scrutinyType", "COMMSCR");
		ScrutinyFileDto preliminaryCommScrutiny=scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", params);
		response.addObject("techScrutinyFile", preliminaryTechScrutiny);
		response.addObject("commScrutinyFile", preliminaryCommScrutiny);
		response.addObject("scrutinyLevel", "PRELIMINARY");
		return response;
	}
	
	@RequestMapping(value= {"/getFinalScrutinyFiles/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalScrutinyFiles(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("scrutinyLevel", "FINAL");
		params.put("scrutinyType", "TECHSCR");
		List<ScrutinyFileDto> finalTechScrutiny=scrutinyFileService.findDtos("getTechnicalScrutinyFileByBidderId", params);
		
		params.put("scrutinyType", "COMMSCR");
		ScrutinyFileDto finalCommScrutiny=scrutinyFileService.findDto("getCommercialScrutinyFileAndBidderId", params);
		response.addObject("techScrutinyFile", finalTechScrutiny);
		response.addObject("commScrutinyFile", finalCommScrutiny);
		response.addObject("scrutinyLevel", "FINAL");
		return response;
	}
	
	@RequestMapping(value= {"/getSelfBidHistory/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getSelfBidHistory(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		List<PriceBidDto> bidHistory=liveBidService.getBidListByBidderId(tahdrMaterialId,bidderId);
		response.addObject("bidHistory", bidHistory);
		return response;
	}
	
	@RequestMapping(value= {"/getCompleteBidHistory/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCompleteBidHistory(@PathVariable("tahdrMaterialId") Long tahdrMaterialId){
		CustomResponseDto response=new CustomResponseDto();
		List<PriceBidDto> bidHistory=liveBidService.getCompleteBidListTahdrMaterialId(tahdrMaterialId);
		response.addObject("bidHistory", bidHistory);
		return response;
	}
	
	/*@RequestMapping(value= {"/getAllocatedQunatity/{tahdrMaterialId}/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getAllocatedQunatity(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("tahdrMaterialId", tahdrMaterialId);
		WinnerSelectionDto dto=tahdrAwardWinnerService.findDto("getwinnerDataFromBidderID",params);
		Long allocatedQty=0l;
		if(dto!=null){
			allocatedQty=dto.getAllocatedQty();
		}
		response.addObject("allocatedQty", allocatedQty);
		return response;
	}*/
	
	@RequestMapping(value= {"/getQuickBidderAllBids/{bidderId}/{tahdrMaterialId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getQuickBidderAllBids(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("bidderId", bidderId);
		params.put("tahdrMaterialId", tahdrMaterialId);
		 if(tahdrMaterialId!=null && bidderId!=null){
			 List<PriceBidDto> bidHistory=priceBidService.findDtos("getSelfPBQuickBidListByBidderIdAndMaterialId", params);
			 List<PriceBidDto> tempBidHistory=tempPriceBidService.findDtos("getSelfTempQuickPriceBidListByBidderIdAndMaterialId", params);
			 bidHistory.addAll(tempBidHistory);
			 response.addObject("bidHistory", bidHistory);
			 response.addObject("result", true);
		 }else{
			 response.addObject("result", false); 
			 response.addObject("resultMessage", "Something went wrong !"); 
		 }
		return response;
	}
	
	@RequestMapping(value= {"/getPaymentDetailsListByTahdrId/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPaymentDetailsListByTahdrId(@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		List<PaymentDetailDto> list=paymentDetailService.findDtos("getTahdrPaymentDetailsByTahdrId", params);
		if(!CommonUtil.isCollectionEmpty(list)){
			response.addObject("paymentDetailList", list); 
			response.addObject("result", true);  	
		}else{
			response.addObject("result", false); 
			response.addObject("resultMessage", "Something went wrong !"); 	
		}
		return response;
	}
	
	@RequestMapping(value= {"/getPaymentDetails/{tahdrId}/{fromDate}/{toDate}/{appStatus}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPaymentDetails(@PathVariable("tahdrId") Long tahdrId,
			  @PathVariable("fromDate") Long fromDate,
			  @PathVariable("toDate") Long toDate,@PathVariable("appStatus") String appStatus){
		CustomResponseDto response=new CustomResponseDto();
		List<PaymentDetailDto> list=paymentDetailService.searchPaymentDetails(tahdrId, fromDate, toDate, appStatus);
		if(!CommonUtil.isCollectionEmpty(list)){
				response.addObject("paymentDetailList", list); 
				response.addObject("result", true);  	
		}else{
				response.addObject("result", false); 
				response.addObject("resultMessage", "No Payment Found!"); 	
		}
		return response;
	}
}
