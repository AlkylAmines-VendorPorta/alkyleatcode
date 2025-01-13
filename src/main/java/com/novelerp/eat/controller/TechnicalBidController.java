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
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.validator.TechnicalBidValidator;

@Controller
public class TechnicalBidController {

	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService; 
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TechnicalBidValidator technicalBidValidator;
	
	@RequestMapping(value="/saveTechnicalBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto saveTechnicalBid(@ModelAttribute("itemBid")ItemBidDto itemBid){
		Errors errors=new Errors();
		ResponseDto response=null;
		if(itemBid!=null){
			TechnicalBidDto tb=itemBid.getTechnicalBid();
			if(tb!=null){
				tb.setItemBid(itemBid);
				technicalBidValidator.saveValidate(tb, errors);
				if(errors.getErrorCount()>0){
					response=new ResponseDto();
					response.setHasError(true);
					response.setErrors(errors.getErrorList());
					itemBid.setResponse(response);
					return itemBid;
				}else{
					return itemBidService.saveTechnicalBid(itemBid);
				}
			}else{
				response=new ResponseDto(true,"Invalid Bid");
				tb=new TechnicalBidDto();
				tb.setResponse(response);
				itemBid.setTechnicalBid(tb);
				return itemBid;
			}
		}else{
			response=new ResponseDto(true,"Invalid Bid");
			itemBid=new ItemBidDto();
			itemBid.setResponse(response);
			return itemBid;
		}
		
	}

	@RequestMapping(value="/saveTechnicalSectionDocument",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto saveTechnicalSectionDocument(@ModelAttribute("itemBid")ItemBidDto itemBid){
		
		return itemBidService.saveTechnicalSectionDocument(itemBid);
	}

	@RequestMapping(value="/submitTechnicalBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemBidDto submitTechnicalBid(@ModelAttribute("itemBid")ItemBidDto itemBid,HttpServletRequest request){
		BPartnerDto partner=contextService.getPartner();
		ResponseDto response=new ResponseDto();
		response.setHasError(true);
		AttachmentDto digiSign=null;
		
		Errors errors=new Errors();
		TechnicalBidDto tb=itemBid.getTechnicalBid();
		tb.setItemBid(itemBid);
		if(itemBid!=null && tb!=null ){
			digiSign=tb.getDigiSignedDoc();
		}	
		technicalBidValidator.validate(tb, request,errors);
		
		if(errors.getErrorCount()>0){
			response.setErrors(errors.getErrorList());
			itemBid.setResponse(response);
		}else{
			Map<String, Object> map=new HashMap<>();
			map.put("itemBidId", itemBid.getItemBidId());
			map.put("partnerId", partner.getbPartnerId());
			tb =technicalBidService.findDto("verifyTechnicalBid", map);
			itemBid.setTechnicalBid(tb);
			
			itemBid.getTechnicalBid().setDigiSignedDoc(digiSign);
			itemBid.getTechnicalBid().setItemBid(itemBid);
			itemBid=itemBidService.submitTechnicalBid(itemBid);
			
			/*PriceBidDto pb=priceBidService.findDto("verifyPriceBid", map);
			if(pb!=null){
				pb.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
				pb.setDigiSignedDoc(null);
				priceBidService.updateDto(pb);
			}*/
			
		}
		return itemBid;
	}
	
	@RequestMapping(value="/getTechnicalBidWithBidderGtp/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidWithBidderGtp(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		TechnicalBidDto tb=technicalBidService.getTechnicalBid(tahdrMaterialId, partner.getbPartnerId());
		List<BidderGtpDto> bidderGtpList=technicalBidService.getTechnicalBidBidderGtp(tahdrMaterialId, partner.getbPartnerId());
		
		response.addObject("technicalBid", tb);
		response.addObject("bidderGtpList", bidderGtpList);
		return response;
	}
	
	@RequestMapping(value="/getTechnicalBid/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBid(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		TechnicalBidDto tb=technicalBidService.getTechnicalBid(tahdrMaterialId, partner.getbPartnerId());
		
		response.addObject("technicalBid", tb);
		return response;
	}
	
	@RequestMapping(value="/getTechnicalBidBidderGtp/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidBidderGtp(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		List<BidderGtpDto> bidderGtpList=technicalBidService.getTechnicalBidBidderGtp(tahdrMaterialId, partner.getbPartnerId());
		response.addObject("bidderGtpList", bidderGtpList);
		return response;
	}
	
	@RequestMapping(value="/getTechnicalBidderGtp/{tahdrMaterialId}/{partnerId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidderGtp(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		List<BidderGtpDto> bidderGtpList=technicalBidService.getTechnicalBidBidderGtp(tahdrMaterialId, partnerId);
		response.addObject("bidderGtpList", bidderGtpList);
		return response;
	}
	
	@RequestMapping(value="/getTechnicalBidBidderSecDoc/{tahdrMaterialId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidBidderSecDoc(@PathVariable("tahdrMaterialId") Long tahdrMaterialId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		BPartnerDto partner=contextService.getPartner();
		CustomResponseDto response=new CustomResponseDto();
		List<BidderSectionDocDto> bidderSecDocList=technicalBidService.getTechnicalBidSectionDoc(tahdrMaterialId, partner.getbPartnerId());
		response.addObject("bidderSecDocList", bidderSecDocList);
		return response;
	}
	
	@RequestMapping(value="/getTechnicalBidderSecDoc/{tahdrMaterialId}/{partnerId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getTechnicalBidderSecDoc(@PathVariable("tahdrMaterialId") Long tahdrMaterialId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		List<BidderSectionDocDto> bidderSecDocList=technicalBidService.getTechnicalBidSectionDoc(tahdrMaterialId,partnerId);
		response.addObject("bidderSecDocList", bidderSecDocList);
		return response;
	}
	
	
	@RequestMapping(value="/deleteFinalTBDoc/{bidderId}/{itemBidId}/{tahdrDetailId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto deleteFinalTBDoc(@PathVariable("bidderId")Long bidderId,@PathVariable(value="itemBidId")Long itemBidId,@PathVariable(value="tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		if(bidderId!=null && itemBidId!=null){
			Long result=technicalBidService.updateStatusForTechnicalBid(AppBaseConstant.TECHNICAL_SECTION, bidderId, itemBidId);
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
