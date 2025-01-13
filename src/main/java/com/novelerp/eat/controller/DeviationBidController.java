package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.DeviationBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.SignatureVerificationService;
import com.novelerp.eat.service.TAHDRService;

/**
 * @author Aman Sahu
 *
 */
@Controller
public class DeviationBidController {

	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRService tAHDRService;

	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private SignatureVerificationService signatureVerification;
	
	@Autowired
	private DeviationBidService deviationBidService;

	
	@RequestMapping(value= {"/deviationBid"},method =RequestMethod.GET)
	public ModelAndView deviationBid(){
		ModelAndView view=new ModelAndView("deviationBid");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	@RequestMapping(value= {"/auctionDeviationBid"},method =RequestMethod.GET)
	public ModelAndView auctionDeviationBid(){
		ModelAndView view=new ModelAndView("deviationBid");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionDeviationBid"},method =RequestMethod.GET)
	public ModelAndView myTenderScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("deviationBid");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getBiddeyMyTenderListForDeviation/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/myTenderDeviationBid"},method =RequestMethod.GET)
	public ModelAndView myAuctionScheduling(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("deviationBid");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","getBiddeyMyTenderListForDeviation/"+tahdrId);
	    return view;
	}
	
	@RequestMapping(value= {"/getBiddeyMyTenderListForDeviation/{tahdrId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		RoleDto role=contextService.getDefaultRole();
		if(partner!=null){
			Map<String, Object> params= new HashMap<>();
			params.put("partnerId", partner.getbPartnerId());
			params.put("tahdrId", tahdrId);
			List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
			TAHDRDto tender=null;
			if(role!=null){ 
				
				if(role.getValue().equals("VENADM")){
					params.put("status", "DBSCH");
					tender=tAHDRService.findDto("getDeviationMyTenderForBidder", params);
				}
				else{
					params.put("status", "DBOP");
					tender=tAHDRService.findDto("getDeviationMyTenderForBidder", params);
				}
				tahdrList.add(tender);
			response.addObject("tahdrList", tahdrList);
		}
	}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("statusList", statusList);
		return response;
	}
	
	@RequestMapping(value= {"/getBidderTenderListForDeviation/{typeCode}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderList(@PathVariable("typeCode") String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		RoleDto role=contextService.getDefaultRole();
		if(partner!=null){
			Map<String, Object> params= new HashMap<>();
			params.put("partnerId", partner.getbPartnerId());
			params.put("typeCode", typeCode);
			List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
			if(role!=null){ 
				
				if(role.getValue().equals("VENADM")){
					params.put("status", "DBSCH");
					tahdrList=tAHDRService.findDtos("getDeviationTenderForBidder", params);
				}
				else{
					params.put("status", "DBOP");
					tahdrList=tAHDRService.findDtos("getDeviationTenderForBidder", params);
				}
			 
			response.addObject("tahdrList", tahdrList);
		}
	}
		Map<String, String> statusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("statusList", statusList);
		response.addObject("bidderStatusList", bidderStatusList);
		contextService.setSFTPRequiredInfo(partner, typeCode, null);
		return response;
	}
	@RequestMapping(value= {"/getDeviationBidderListByTahdrDetailId/{tahdrDetailId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderListByTahdrDetailId(@PathVariable("tahdrDetailId") Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrDetailId", tahdrDetailId);
		BPartnerDto partner=contextService.getPartner();
		Set<RoleDto> role=contextService.getRoles();
		List<BidderDto> bidderList=new ArrayList<BidderDto>();
		if(!CommonUtil.isCollectionEmpty(role)){ 
			String userRole=role.iterator().next().getValue();
			if(userRole.equals("VENADM")){
				params.put("partnerId", partner.getbPartnerId());
				bidderList=bidderService.findDtos("getBidderDetailForScrutiny",params);
			}
			else{
				/*params.put("status1", "DBOP");
				params.put("status2", "FTBP");*/
				/*bidderList=bidderService.findDtos("getBidderDetailListForScrutiny",params);*/
				bidderList=bidderService.findDtos("getBidderListByStatus",params);
			}
		}
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		response.addObject("bidderStatusList", bidderStatusList);
		response.addObject("bidderList", bidderList);
		return response;
		
	}
	
	@RequestMapping(value= {"/saveTechnicalDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine)
	{
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			try{
				response=deviationBidService.saveTechicalDeviationByBidder(itemScrutinyLine);
				itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				boolean result=response.getObjectMap().get("resultStatus")==null?false:(boolean) response.getObjectMap().get("resultStatus");
				if(result){
					response.addObject("itemScrutinyLine", itemScrutinyLine);
				}
			}catch(Exception ex){
				response.addObject("Status", ex.getMessage());
				response.addObject("resultStatus", false);
			}
		}
		else{
			response.addObject("Status", "Deviation Response Not Updated");
			response.addObject("resultStatus", false);
		}
		return response;
		
	}
	@RequestMapping(value= {"/saveCommercialDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveCommercialDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine)
	{
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();
		
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			try{
				response=deviationBidService.saveCommercialDeviationByBidder(itemScrutinyLine);
				itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				boolean result=response.getObjectMap().get("resultStatus")==null?false:(boolean) response.getObjectMap().get("resultStatus");
				if(result){
					response.addObject("itemScrutinyLine",itemScrutinyLine );
				}
			}catch(Exception ex){
				response.addObject("Status", ex.getMessage());
				response.addObject("resultStatus", false);
			}
			
		}
		else{
			response.addObject("Status", "Deviation Response Not Updated");
			response.addObject("resultStatus", false);
		}
		return response;
	}
	@RequestMapping(value= {"/saveTechnicalDocumentDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveTechnicalDocumentDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine)
	{
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			try{
				response=deviationBidService.saveTechicalDeviationByBidder(itemScrutinyLine);
				itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				
				response.addObject("itemScrutinyLine", itemScrutinyLine);
					
			}catch(Exception ex){
				response.addObject("Status", ex.getMessage());
				response.addObject("resultStatus", false);
			}
		}
		else{
			response.addObject("Status", "Deviation Response Not Updated");
			   response.addObject("resultStatus", false);
		}
		return response;
	}
	@RequestMapping(value= {"/saveCommercialDocumentDeviationResponse"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveCommercialDocumentDeviationResponse(@ModelAttribute("itemScrutinyLine") ItemScrutinyLineDto itemScrutinyLine)
	{
		CustomResponseDto response=new CustomResponseDto();
		if(itemScrutinyLine.getItemScrutinyLineId() != null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyLineId", itemScrutinyLine.getItemScrutinyLineId());
			try{
				response=deviationBidService.saveCommercialDeviationByBidder(itemScrutinyLine);
				itemScrutinyLine=itemScrutinyLineService.findDto("getItemScrtunyLineById", params);
				
				response.addObject("itemScrutinyLine", itemScrutinyLine);
					
			}catch(Exception ex){
				response.addObject("Status", ex.getMessage());
				response.addObject("resultStatus", false);
			}
		}
		else{
			response.addObject("Status", "Deviation Response Not Updated");
			response.addObject("resultStatus", false);
		}
		return response;
		
	}
	@RequestMapping(value= {"/confirmTechnicalDeviationByBidder"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmTechnicalDeviationByBidder(@ModelAttribute("itemScrutiny") ItemScrutinyDto itemScrutiny){
		CustomResponseDto response=new CustomResponseDto();
		ResponseDto digiResp= new ResponseDto();
		if(itemScrutiny.getItemScrutinyId() != null && itemScrutiny.getItemBid()!=null && itemScrutiny.getBidder()!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyId", itemScrutiny.getItemScrutinyId());
			params.put("scrutinyType", "TECHSCR");
			List<ItemScrutinyLineDto> isAllDeviationSubmitted=itemScrutinyLineService.findDtos("getPendingDeviationItemByItemScrutinyId", params);
			if(CommonUtil.isCollectionEmpty(isAllDeviationSubmitted)){
				AttachmentDto digiSign=itemScrutiny.getScrutinyFile();
				/*digiResp=signatureVerification.verifyDigitalSignature(digiSign);*/
				digiResp=new ResponseDto(false, "Valid");
				if(!digiResp.isHasError()){
					try{
						response=deviationBidService.confirmTechicalDeviationByBidder(itemScrutiny);
					}catch(Exception ex){
						response.addObject("Status", ex.getMessage());
						response.addObject("statusResult", false);
					}
				}else{
					response.addObject("Status", digiResp.getMessage());
					response.addObject("statusResult", false);
				}
				
			}else{
				response.addObject("Status", "Deviation Not Submitted For each Technical Deviation");
				response.addObject("statusResult", false);
			}
			
		}
		else{
			response.addObject("Status", "Deviation Not Updated");
			response.addObject("statusResult", false);
		}
		return response;
		
	}
	@RequestMapping(value= {"/confirmCommercialDeviationByBidder"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmCommercialDeviationByBidder(@ModelAttribute("itemScrutiny") ItemScrutinyDto itemScrutiny){
		CustomResponseDto response=new CustomResponseDto();
		ResponseDto digiResp= new ResponseDto();
		if(itemScrutiny.getItemScrutinyId() != null && itemScrutiny.getBidder()!=null){
			Map<String, Object> params= new HashMap<>();		
			params.put("itemScrutinyId", itemScrutiny.getItemScrutinyId());
			params.put("scrutinyType", "COMMSCR");
			List<ItemScrutinyLineDto> isAllDeviationSubmitted=itemScrutinyLineService.findDtos("getPendingDeviationItemByItemScrutinyId", params);
			if(CommonUtil.isCollectionEmpty(isAllDeviationSubmitted)){
				AttachmentDto digiSign=itemScrutiny.getScrutinyFile();
				/*digiResp=signatureVerification.verifyDigitalSignature(digiSign);*/
				digiResp=new ResponseDto(false, "Valid");
				if(!digiResp.isHasError()){
					try{
						response=deviationBidService.confirmCommercialDeviationByBidder(itemScrutiny);
					}catch(Exception ex){
						response.addObject("Status", ex.getMessage());
						response.addObject("statusResult", false);
					}
				}else{
					response.addObject("Status", "Uploaded Digital Signed File Has Error! ");
					response.addObject("statusResult", false);
				}
			}else{
				response.addObject("Status", "Deviation Not Submitted For each Commercial Deviation");
				response.addObject("statusResult", false);
			}
		}
		else{
			response.addObject("Status", "Something went wrong !");
			response.addObject("statusResult", false);
		}
		return response;
		
	}
	
	@RequestMapping(value= {"/confirmAllDeviation/{bidderId}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmAllDeviation(@PathVariable("bidderId") Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("bidderId", bidderId);
		params.put("status", AppBaseConstant.BIDDER_STATUS_DEVIATION_CALLED);
		List<ItemBidDto> notSubmittedDeviation=itemBidService.findDtos("getItemListByBidderIdAndOneStatus", params);
		if(CommonUtil.isCollectionEmpty(notSubmittedDeviation)){
			response.addObject("Status", "All Deviation is submitted !");
			response.addObject("statusResult", true);
		}else{
			response.addObject("Status", "Few Item deviation is not submitted !");
			response.addObject("statusResult", false);
		}
		return response;
	}
	
}
