package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.appbase.master.service.ScrutinyPointService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.ItemScrutinyService;
import com.novelerp.eat.service.TechnicalBidService;

/**
 * @author Aman Sahu
 *
 */
@Controller
public class ItemScrutinyController {
	
	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private  ScrutinyPointService scrutinyPointService;
	
	@Autowired
	private  BidderService bidderService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	private PartnerFinancialDetailsService partnerFinancialDetailsService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value= {"/createAllTechnicalScrutinyLine/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto createAllScrutinyLine(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		 Map<String, Object> map= new HashMap<>();		
			 map.put("bidderId", bidderId);
			 map.put("scrutinyType", AppBaseConstant.TECHNICAL_SCRUTINY);
		    List<ItemScrutinyDto> itemScrutinyList=itemScrutinyService.findDtos("getTechnicalScrutinyByBidderId", map);
		    response.addObject("result", false);
		if(CommonUtil.isCollectionEmpty(itemScrutinyList)){
			Map<String, Object> params= new HashMap<>();		
			params.put("bidderId", bidderId);
			params.put("bidderStatus", AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED);
			params.put("itemStatus", AppBaseConstant.BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED);
		    List<ItemBidDto> itemList=itemBidService.findDtos("getOpenedItemListByBidderId", params);
		    if(!CommonUtil.isCollectionEmpty(itemList)){
		    	for(ItemBidDto ib:itemList){
		    		List<ItemScrutinyDto> list=saveItemScrutinyForTechnical(ib.getItemBidId());
		    		itemScrutinyList.addAll(list);
		    	}
		    	response.addObject("result", true);
				response.addObject("message", "Success");
		}else{
			response.addObject("message", "Failed to create Technical Item Scrutiny:item not found");
		}
	    }else{
			response.addObject("message", "Already Created");
	    }
		return response;
	}
	
	@RequestMapping(value= {"/saveItemScrutinyForTechnical/{itemBidId}"},method =RequestMethod.POST)
	public @ResponseBody List<ItemScrutinyDto> saveItemScrutinyForTechnical(@PathVariable("itemBidId") Long itemBidId){
		Map<String, Object> params= new HashMap<>();		
		params.put("itemBidId", itemBidId);
		List<ItemScrutinyDto> itemScrutiny=itemScrutinyService.findDtos("getTechnicalItemscrutiny", params);//getItemscrutiny
		if(CommonUtil.isListEmpty(itemScrutiny)){	
			ItemBidDto itemBid=itemBidService.findDto("getItemBidWithBidder", params);
			TechnicalBidDto technicalBid=technicalBidService.findDto("getTechnicalBidByItemBid", params);
			Set<BidderSectionDocDto> technicalDoc=technicalBid!=null?technicalBid.getBidderSecDoc():null;
			Set<BidderGtpDto> bidderGtpList=technicalBid!=null?technicalBid.getBidderGtp():null;
			return itemScrutinyService.createScrutinyForTechnical(itemBid,bidderGtpList,technicalDoc);
			}
		else
			return itemScrutiny;
		
	}
	@RequestMapping(value= {"/saveItemScrutinyForCommercial/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveItemScrutinyForCommercial(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		/*RoleDto role= contextService.getDefaultRole();
		String roleName=role==null?"":role.getValue();*/
			Map<String, Object> params= new HashMap<>();		
			params.put("bidderId", bidderId);
			BidderDto bidder=bidderService.findDto("getCommercialDocByBidderId", params) ; 
			if(bidder!=null){
				CommercialBidDto commercialBid=commercialBidService.findDto("getCommercialBidByBidderId", params);
				Long partnerId=bidder.getPartner().getbPartnerId();
				
				Map<String, Object> financialDetailsparams =   new HashMap<>();
				financialDetailsparams.put("partnerId", partnerId);
				List<PartnerFinancialAttachmentDto> financialAttachments=partnerFinancialDetailsService.findDtos("getPartnerFinancialDocsQuery", financialDetailsparams);
				List<ItemScrutinyDto> itemScrutiny=itemScrutinyService.findDtos("getCommercialItemscrutiny", params);
				if(CommonUtil.isListEmpty(itemScrutiny)){	
					List<ScrutinyPointDto> scrutinyPointList=scrutinyPointService.findDtos("getOrderedScrutinyPointList", null);
					Set<BidderSectionDocDto> commercialDoc=commercialBid==null?null:commercialBid.getBidderSecDoc();
					itemScrutiny= itemScrutinyService.createScrutinyForCommercial(bidder,scrutinyPointList,commercialDoc);
				}
				Long itemScrutinyId=itemScrutiny.iterator().next().getItemScrutinyId();
				Map<String, Object> paramForLine= new HashMap<>();	
				paramForLine.put("itemScrutinyId", itemScrutinyId);
				List<ItemScrutinyLineDto> itemScrutinyLine=itemScrutinyLineService.findDtos("getScrutinyPoint", paramForLine);
				if(!CommonUtil.isCollectionEmpty(itemScrutinyLine)){
							response.addObject("itemScrutinyList", itemScrutinyLine);
							response.addObject("itemScrutiny", itemScrutiny);
							response.addObject("Status", "Technical Scrutiny is Approved");
						
				}
				response.addObject("result",true);
				response.addObject("commercialBid",commercialBid);
				response.addObject("financialAttachments",financialAttachments);
				response.addObject("resultMessage","Commercial Scrutiny Line created!");
			}else{
				response.addObject("result",false);
				response.addObject("resultMessage","Commercial Scrutiny Line not created");
			}
		return response;
	}
	
	@RequestMapping(value= {"/getFinalResp/{itemScrutinyId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getFinalResp(@PathVariable("itemScrutinyId") Long itemScrutinyId)
	{
		CustomResponseDto response=new CustomResponseDto();
		String role=contextService.getDefaultRole().getValue();
		Map<String, Object> params= new HashMap<>();		
		params.put("itemScrutinyId", itemScrutinyId);
		ItemScrutinyDto itemScrutiny=itemScrutinyService.findDto("getFinalResp",params);
		if(itemScrutiny!=null)
		{
			boolean isAllAudited=false;
			if(AppBaseConstant.ROLE_AUDITOR_USER.equals(role)){
				List<ItemScrutinyLineDto> pendingAuditingList=itemScrutinyLineService.findDtos("getPendingAuditingItemByItemScrutinyId", params);
				if(CommonUtil.isListEmpty(pendingAuditingList)){
					isAllAudited=true;
				}
				
			}else{
				isAllAudited=true;
			}
			
			List<ItemScrutinyLineDto> pendingScrutinyList=itemScrutinyLineService.findDtos("getPendingScrutinyByItemScrutinyId", params);
			List<ItemScrutinyLineDto> rejectedAuditingList=itemScrutinyLineService.findDtos("getRejectedAuditingListByItemScrutinyId", params);
			if(CommonUtil.isListEmpty(pendingScrutinyList) && isAllAudited){
				List<ItemScrutinyLineDto> deviatedItemScrutinyLine=itemScrutinyLineService.findDtos("getDeviatedListByItemScrutinyId", params);
				if(CommonUtil.isListEmpty(deviatedItemScrutinyLine)){
					response.addObject("isDeviation", false);
				}else{
					response.addObject("isDeviation", true);
				}
				response.addObject("isAllScrutinized", true);
				response.addObject("isAllAudited", true);
			}else{
				response.addObject("isAllScrutinized", false);
				response.addObject("isAllAudited", false);
			}
			response.addObject("itemScrutiny", itemScrutiny);
			response.addObject("isAnyRejected", rejectedAuditingList==null?false:true);
		}
		return response;
		
	}
	
	@RequestMapping(value= {"/getScrutinyStatus/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getScrutinyStatus(@PathVariable("bidderId") Long bidderId){
	CustomResponseDto response =new CustomResponseDto();
	Map<String, Object> params= new HashMap<>();		
	params.put("bidderId", bidderId);
	params.put("scrutinyType", AppBaseConstant.TECHNICAL_SCRUTINY);
	List<ItemScrutinyDto> techScrutiny=itemScrutinyService.findDtos("getTechnicalScrutinyByBidderId", params);
	params.put("scrutinyType", AppBaseConstant.COMMERCIAL_SCRUTINY);
	ItemScrutinyDto commScrutiny=itemScrutinyService.findDto("getCommercialScrutinyByBidderId", params);
	response.addObject("techScrutiny", techScrutiny);
	response.addObject("commScrutiny", commScrutiny);
	return response;
	}
	
}
