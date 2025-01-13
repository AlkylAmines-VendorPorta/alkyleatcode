package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class CumulativeReportController {
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TAHDRMaterialService tAHDRMaterialService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@RequestMapping(value= {"/CumulativeReports"},method =RequestMethod.GET)
	public ModelAndView tenderTechnicalScrutinyView(){
		ModelAndView view=new ModelAndView("CumulativeReports");
	    return view;
	}
	
	@RequestMapping(value= {"/getCumulativeSheetByTahdrId/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCumulativeSheetByTahdrId(@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role =contextService.getDefaultRole();
		 if(role!=null){
		     Map<String, Object> params= new HashMap<>();
		     params.put("tahdrId", tahdrId);
			 TAHDRDto tender=tAHDRService.findDto(tahdrId);
			 List<PriceBidDto> bidderListWithPriceBid=null;
			 if(tender!=null){
				 List<TAHDRMaterialDto> materialList=tAHDRMaterialService.findDtos("getQueryTahdrMaterialListByTahdrId", params);
				 List<BidderDto> bidder=bidderService.findDtos("getBidderListForQRFQByTahdrId", params);
				 Map<String, Object> map= new HashMap<>();
				 String typeCode=tender.getTahdrTypeCode();
				 if(typeCode.equals("QFA") || typeCode.equals("QRFQ") ||  typeCode.equals("RFQ")){
					 if(typeCode.equals("QFA")){
						 bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBBidderDetailListByTahdrId", params);
					 }else{
						 bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBBidderDetailListByTahdrId", params);
					 }
				 }else{
					 if(typeCode.equals("FA")){
						 bidderListWithPriceBid=priceBidService.findDtos("getAscendingPBOpenedBidderDetailListByTahdrId", params); 
					 }else{
						 bidderListWithPriceBid=priceBidService.findDtos("getDecendingPBOpenedBidderDetailListByTahdrId", params); 
					 }
					
					
				 }
				if(!CommonUtil.isCollectionEmpty(bidderListWithPriceBid)){
					for(int i=0;i<bidderListWithPriceBid.size();i++){
						PriceBidDto priceBid=bidderListWithPriceBid.get(i);
						BPartnerDto partner=priceBid.getPartner()==null?null:priceBid.getPartner();
						Long partnerId=partner.getbPartnerId();
						if(partner!=null && !map.containsKey("rankMap_"+partnerId) ){
								int overAllRank=priceBidService.getOverAllRankBy(tahdrId, partnerId,typeCode);
								map.put("rankMap_"+partnerId, overAllRank);
							}
						}
					}
					 
					 
					response.addObject("rank", map);
					response.addObject("bidder", bidder);
					response.addObject("materialList", materialList);
					response.addObject("bidderList", bidderListWithPriceBid);
					response.addObject("result", true); 
					
				}else{
					
					response.addObject("resultMessage", "Something went wrong !");
					response.addObject("result", false); 
				}
			 }else{
				 response.addObject("result", false); 
				 response.addObject("resultMessage", "You are not authorised!"); 
			 }
		return response;
	}
	
	@RequestMapping(value= {"/getCumulativeReportByTahdrId/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getCumulativeReportByTahdrId(@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role =contextService.getDefaultRole();
		 if(role!=null){
		     Map<String, Object> params= new HashMap<>();
		     params.put("tahdrId", tahdrId);
			 TAHDRDto tender=tAHDRService.findDto(tahdrId);
			 List<Object> bidderListWithPriceBid=null;
			 if(tender!=null){
				 /*Map<String, Object> map= new HashMap<>();*/
				 String typeCode=tender.getTahdrTypeCode();
				 if(typeCode.equals("QFA") || typeCode.equals("QRFQ") ||  typeCode.equals("RFQ")){
					 if(typeCode.equals("QFA")){
						 bidderListWithPriceBid=(List<Object>) priceBidService.getCumulativeSheetData(tahdrId, false);
					 }else{
						 bidderListWithPriceBid=(List<Object>) priceBidService.getCumulativeSheetData(tahdrId, false);
					 }
				 }else{
					 if(typeCode.equals("FA")){
						 bidderListWithPriceBid=(List<Object>) priceBidService.getCumulativeSheetData(tahdrId, true);
					 }else{
						 bidderListWithPriceBid=(List<Object>) priceBidService.getCumulativeSheetData(tahdrId, true);
					 }
					
					
				 }
				/*if(!CommonUtil.isCollectionEmpty(bidderListWithPriceBid)){
					for(int i=0;i<bidderListWithPriceBid.size();i++){
						PriceBidDto priceBid=bidderListWithPriceBid.get(i);
						BPartnerDto partner=priceBid.getPartner()==null?null:priceBid.getPartner();
						Long partnerId=partner.getbPartnerId();
						if(partner!=null && !map.containsKey("rankMap_"+partnerId) ){
								int overAllRank=priceBidService.getOverAllRankBy(tahdrId, partnerId,typeCode);
								map.put("rankMap_"+partnerId, overAllRank);
							}
						}
					}
					 
					 
					response.addObject("rank", map);*/
					response.addObject("bidderList", bidderListWithPriceBid);
					response.addObject("result", true); 
					
				}else{
					
					response.addObject("resultMessage", "Something went wrong !");
					response.addObject("result", false); 
				}
			 }else{
				 response.addObject("result", false); 
				 response.addObject("resultMessage", "You are not authorised!"); 
			 }
		return response;
	}
	
}
