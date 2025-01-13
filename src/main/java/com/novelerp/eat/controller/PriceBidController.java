/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.PriceBidValidator;

@Controller
public class PriceBidController {
	
	@Autowired
	private TAHDRService tAHDRService;

	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private PriceBidValidator priceBidValidtor;
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	/*@Autowired
	private FileValidator fileValidator;*/
	
	@RequestMapping(value="/savePriceBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto savePriceBid(@ModelAttribute("itemBid")ItemBidDto itemBid){
		PriceBidDto pbNew=null;
		Long tahdrId=itemBid.getBidder()==null?0l:itemBid.getBidder().getTahdr()==null?0l:itemBid.getBidder().getTahdr().getTahdrId();
		TAHDRDto tenderDto=tAHDRService.findDto(tahdrId);
		itemBid.getBidder().setTahdr(tenderDto);
		if(itemBid.getItemBidId()!=null && itemBid.getPriceBid().getMaterialSpecification()!=null){
			Errors errors= new Errors();
			PriceBidDto pb=itemBid.getPriceBid();
			pb.setItemBid(itemBid);
			priceBidValidtor.saveValidator(itemBid.getPriceBid(), errors);
			if(errors.getErrorCount()>0){
				ResponseDto response=new ResponseDto();
				response.setErrors(errors.getErrorList());
				response.setHasError(true);
				itemBid.setResponse(response);
				return itemBid;
			}
			pbNew=priceBidService.mergeUpdatedParts(itemBid.getTahdrMaterial().getTahdrMaterialId(),itemBid.getPriceBid().getPriceBidId());
			itemBid=itemBidService.savePriceBid(itemBid,pbNew);
		}else{
			Errors errors= new Errors();
			PriceBidDto pb=itemBid.getPriceBid();
			pb.setItemBid(itemBid);
			priceBidValidtor.saveValidator(pb, errors);
			if(errors.getErrorCount()>0){
				if(errors.getErrorCount()>0){
					ResponseDto response=new ResponseDto();
					response.setErrors(errors.getErrorList());
					response.setHasError(true);
					itemBid.setResponse(response);
					return itemBid;
				}
			}
			
			itemBid=itemBidService.savePriceBid(itemBid);
		}
		return itemBid;
	}

	@RequestMapping(value="/saveRevisedPriceBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto saveRevisedPriceBid(@ModelAttribute("itemBid")ItemBidDto itemBid){
		itemBid=itemBidService.saveRevisedBid(itemBid);
		return itemBid;
	}
	
	@RequestMapping(value="/savePriceSectionDocument",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto savePriceSectionDocument(@ModelAttribute("itemBid")ItemBidDto itemBid){
		if(itemBid==null || itemBid.getPriceBid()==null || itemBid.getPriceBid().getBidderSecDoc()==null || itemBid.getTahdrMaterial()==null){
			return new ItemBidDto();
		}
		TAHDRMaterialDto tm=tahdrMaterialService.findDto("getTahdrMaterialByIdQuery",AbstractContextServiceImpl.getParamMap("tahdrMaterialId", itemBid.getTahdrMaterial().getTahdrMaterialId()));
		if(tm!=null && AppBaseConstant.TAHDR_MATERIAL_TYPE_CODE_BOM.equals(tm.getMaterialTypeCode()) 
				&& tm.getTahdrDetail()!=null && tm.getTahdrDetail().getTahdr()!=null 
				&& tm.getTahdrDetail().getTahdr().getTahdrTypeCode()!=null 
				&& tm.getTahdrDetail().getTahdr().getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)
				&& null == itemBid.getPriceBid().getPriceBidId()){
			ResponseDto resp=new ResponseDto(true, "Fill Price Bid First");
			itemBid.setResponse(resp);
			return itemBid;
		}else{
			return itemBidService.savePriceSectionDocument(itemBid);
		}
	}

	@RequestMapping(value="/submitPriceBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto submitPriceBid(@ModelAttribute("itemBid")ItemBidDto itemBid,HttpServletRequest request){
		
		PriceBidDto priceBid=itemBid.getPriceBid();
		priceBid.setItemBid(itemBid);
		
		AttachmentDto digiSign=null;
		if(itemBid!=null && itemBid.getPriceBid()!=null ){
			digiSign=itemBid.getPriceBid().getDigiSignedDoc();
		}		
		Errors errors =  new Errors();
		priceBidValidtor.validate(priceBid,request, errors);
		if(errors.getErrorCount()>0){
			priceBid.setItemBid(null);
			itemBid.setPriceBid(priceBid);
			ResponseDto responseData=new ResponseDto();
			responseData.setHasError(true);
			responseData.setErrors(errors.getErrorList());
			itemBid.setResponse(responseData);
			return itemBid;
		}else{
				BPartnerDto partner=contextService.getPartner();
				Map<String, Object> param=new HashMap<>();
				param.put("itemBidId", itemBid.getItemBidId());
				param.put("partnerId", partner.getbPartnerId());
				
				PriceBidDto pb=priceBidService.findDto("verifyPriceBid", param);
				itemBid.setPriceBid(pb);
				itemBid.getPriceBid().setDigiSignedDoc(digiSign);
				itemBid.getPriceBid().setItemBid(itemBid);
				
				Map<String, Object> map=new HashMap<>();
				map.put("itemBidId", itemBid.getItemBidId());
				ItemBidDto dto =itemBidService.findDto("getTahdrDataFromItemBidId", map);
				TAHDRDto tahdr=dto.getBidder().getTahdr();
				map.put("partnerId", partner.getbPartnerId()); 
				map.put("tahdrId", tahdr.getTahdrId());
				List<PriceBidDto> parts=priceBidService.findDtos("getPartsForPartner",map);
				Long bidderId=itemBid.getBidder()==null?0l:itemBid.getBidder().getBidderId();
				BidderDto bidder=bidderService.findDto("getTAHDRByBidderId",AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
				itemBid.setBidder(bidder);
				if("Y".equals(pb.getIsRevised())){
					itemBid=itemBidService.submitRevisedPriceBid(itemBid,parts);
				}else{
					itemBid=itemBidService.submitPriceBid(itemBid,parts);
				}					
		}
		return itemBid;
	}
	
	@RequestMapping(value="/getPriceBid/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBid(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		PriceBidDto pb=priceBidService.getPriceBid(tahdrMaterialId, partner.getbPartnerId());
		
		response.addObject("priceBid", pb);
		return response;
	}
	
	@RequestMapping(value="/getPartnerPriceBid/{tahdrMaterialId}/{partnerId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPartnerPriceBid(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		PriceBidDto pb=priceBidService.getPriceBid(tahdrMaterialId, partnerId);
		List<PriceBidDto> partsPriceBidList=priceBidService.getPriceBidForParts(tahdrMaterialId, partnerId);
		response.addObject("partsPriceBidList", partsPriceBidList);
		response.addObject("priceBid", pb);
		return response;
	}
	
	@RequestMapping(value="/getPriceBidBidderSecDoc/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidBidderSecDoc(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		List<BidderSectionDocDto> bidderSecDocList=priceBidService.getPriceBidSectionDoc(tahdrMaterialId, partner.getbPartnerId());
		response.addObject("bidderSecDocList", bidderSecDocList);
		return response;
	}
	
	@RequestMapping(value="/getPartnerPriceBidBidderSecDoc/{tahdrMaterialId}/{partnerId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPartnerPriceBidBidderSecDoc(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		List<BidderSectionDocDto> bidderSecDocList=priceBidService.getPriceBidSectionDoc(tahdrMaterialId,partnerId);
		response.addObject("bidderSecDocList", bidderSecDocList);
		return response;
	}
	
	@RequestMapping(value="/getPriceBidForParts/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPriceBidForParts(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		List<PriceBidDto> partsPriceBidList=priceBidService.getPriceBidForParts(tahdrMaterialId, partner.getbPartnerId());
		response.addObject("partsPriceBidList", partsPriceBidList);
		return response;
	}
	
	@RequestMapping(value="/getPartnerPriceBidForParts/{tahdrMaterialId}/{partnerId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPartnerPriceBidForParts(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		List<PriceBidDto> partsPriceBidList=priceBidService.getPriceBidForParts(tahdrMaterialId, partnerId);
		response.addObject("partsPriceBidList", partsPriceBidList);
		return response;
	}
	
	@RequestMapping(value="/deleteFinalPBDoc/{bidderId}/{itemBidId}/{tahdrDetailId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto deleteFinalPBDoc(@PathVariable("bidderId")Long bidderId,@PathVariable(value="itemBidId")Long itemBidId,@PathVariable(value="tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		if(bidderId!=null && itemBidId!=null){
			Long result=priceBidService.updateStatusForPriceBid(AppBaseConstant.PRICE_BID, bidderId, itemBidId);
			response.setSuccess(true);
			response.setMessage("Deleted");
			if(result>0){
				bidderService.mailNotificationForReSubmission(tahdrDetailId);
			}
		}else{
			response.setSuccess(false);
			response.setMessage("Cannot Be Deleted");
		}
		return response;
	}
}
