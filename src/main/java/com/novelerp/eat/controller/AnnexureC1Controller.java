/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SignatureVerificationService;

@Controller
public class AnnexureC1Controller {
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private SignatureVerificationService signatureVerification;
		
	@RequestMapping(value="/annexureC1",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView annexureC1(){
		ModelAndView view=new ModelAndView("annexureC1");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
	    return view;
	}
	
	@RequestMapping(value="/auctionAnnexureC1",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView auctionAnnexureC1(){
		ModelAndView view=new ModelAndView("annexureC1");
		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
	    return view;
	}
	
	@RequestMapping(value="getAnnexureC1Tenders/{typeCode}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getAnnexureC1Tenders(@PathVariable("typeCode")String typeCode){
		CustomResponseDto response= new  CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		map.put("partnerId", partner.getbPartnerId());
		map.put("tahdrTypeCode", typeCode);
		List<BidderDto> bidderList=new ArrayList<BidderDto>();
		if(typeCode.equals("FA") || typeCode.equals("RA")){
			if(typeCode.equals("RA")){
				map.put("tahdrTypeCode", "PT");
			}
			 bidderList=bidderService.findDtos("getAuctionAnnexureC1Tenders", map);
		}else{
			 bidderList=bidderService.findDtos("getAnnexureC1Tenders", map);
		}
		response.addObject("bidderList", bidderList);
		contextService.setSFTPRequiredInfo(partner, typeCode, null);
		return response;
	}
	
	@RequestMapping(value="getBidsForAnnexureC1Tender/{tahdrDetailId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getBidsForAnnexureC1(@PathVariable("tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response= new  CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		Map<String,Object> map=new HashMap<>();
		Map<String,Object> params=new HashMap<>();
		map.put("partnerId", partner.getbPartnerId());
		map.put("tahdrDetailId", tahdrDetailId);
		params.put("tahdrDetailId", tahdrDetailId);
		List<PriceBidDto> pbList=priceBidService.findDtos("getBidsForAnnexureC1", map);
		
		List<PriceBidDto> isLowestPriceBid=priceBidService.findDtos("getLowestItemBidListBytahdrDetailId", params);
		response.addObject("priceBidList", pbList);
		response.addObject("isLowestPriceBid", isLowestPriceBid);
		return response;
	}
	
	@RequestMapping(value="getPartnerBidsForAnnexureC1/{tahdrMaterialId}/{bidderId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getBidsForAnnexureC1(@PathVariable("tahdrMaterialId")Long tahdrMaterialId,
			                                                    @PathVariable("bidderId")Long bidderId){
		CustomResponseDto response= new  CustomResponseDto();
		Map<String,Object> map=new HashMap<>();/*
		Map<String,Object> params=new HashMap<>();*/
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("bidderId", bidderId);
		/*params.put("tahdrDetailId", tahdrDetailId);*/
		List<PriceBidDto> pbList=priceBidService.findDtos("getPartnerBidsForAnnexureC1", map);
		/*List<PriceBidDto> isLowestPriceBid=priceBidService.findDtos("getLowestItemBidListBytahdrDetailId", params);*/
		response.addObject("priceBidList", pbList);
		/*response.addObject("isLowestPriceBid", isLowestPriceBid);*/
		return response;
	}
	
	@RequestMapping(value="/saveAnnexureC1Bid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PriceBidDto saveBidderSectionDocument(@ModelAttribute("priceBid")PriceBidDto priceBid){
		BPartnerDto partner=contextService.getPartner();
		ItemBidDto itemBid=priceBid.getItemBid();
		Map<String,Object> map=new HashMap<>();
		map.put("partnerId", partner.getbPartnerId());
		map.put("tahdrMaterialId", itemBid.getTahdrMaterial().getTahdrMaterialId());
		PriceBidDto oldPriceBid= priceBidService.findDto("getPriceBid",map);
		
		if("Y".equals(priceBid.getIsMatched())){
			map=new HashMap<>();
			map.put("tahdrMaterialId", itemBid.getTahdrMaterial().getTahdrMaterialId());
			PriceBidDto lowestPb=priceBidService.findDto("getLowestItemBidForMaterialId", map);
			priceBid.setExGroupPriceRate(lowestPb.getExGroupPriceRate());
			priceBid.setOfferedQuantity(lowestPb.getOfferedQuantity());
			itemBid.setPriceBid(priceBid);
		}
			oldPriceBid.setIsMatched(priceBid.getIsMatched());
		
		return itemBidService.saveAnnexuteX1Bid(priceBid,oldPriceBid);
	}
	@RequestMapping(value="/confirmAnnexureC1/{bidderId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto confirmAnnexureC1(@PathVariable("bidderId")Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		if(bidderId!=null){
			boolean result=bidderService.updateStatusWithItemBidStatus(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED,
					AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED, bidderId);
			if(result){
				response.addObject("status", true);
				response.addObject("message", "Annexure C1 Bid is confirmed Successfully !");
			}else{
				response.addObject("status", false);
				response.addObject("message", "Annexure C1 Bid not confirmed!");
			}
		}else{
			response.addObject("status", false);
			response.addObject("message", "Something went wrong !");
		}
		return response;
	}
	
	@RequestMapping(value="/getItemwiseComfirmC1/{priceBidId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getItemwiseComfirmC1(@PathVariable("priceBidId")Long priceBidId){
		CustomResponseDto response=new CustomResponseDto();
		Map<String,Object> map=new HashMap<>();
		map.put("priceBidId", priceBidId);
		PriceBidDto priceBid= priceBidService.findDto("getPriceBidWithC1DigiFile",map);
		response.addObject("priceBid", priceBid);
		return response;
	}
	
	@RequestMapping(value="/confirmItemAnnexureC1",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto confirmItemAnnexureC1(@ModelAttribute("priceBid") PriceBidDto priceBid){
		CustomResponseDto response=new CustomResponseDto();
		if(priceBid.getPriceBidId()!=null && priceBid.getDigiSignedDoc()!=null && priceBid.getItemBid()!=null){
			ResponseDto digiResp= new ResponseDto();
			AttachmentDto digiSign=priceBid.getDigiSignedDoc();
			/*digiResp=signatureVerification.verifyDigitalSignature(digiSign);*/
			digiResp=new ResponseDto(false, "Valid");
			if(!digiResp.isHasError()){
				boolean result=priceBidService.saveAnnexureC1FileResposne(priceBid);
				if(result){
					itemBidService.updateItemBidStatus(AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_SUBMITED,priceBid.getItemBid().getItemBidId());
					response.addObject("message", "Annexure C1 Digital File Saved Successfully !");
					response.addObject("statusResult", true);
				}else{
					response.addObject("message", "Annexure C1 Digital File Not Saved !");
					response.addObject("statusResult", false);
				}
			}else{
				response.addObject("message", digiResp.getMessage());
				response.addObject("statusResult", false);
			}
		}else{
			response.addObject("message", "Something went wrong !");
			response.addObject("statusResult", false);
		}
		
		return response;
	}
	
}
