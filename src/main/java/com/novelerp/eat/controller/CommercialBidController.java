/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.Set;

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

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.CommercialBidValidator;

@Controller
public class CommercialBidController {
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private CommercialBidService commercialBidService;

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private CommercialBidValidator commercialBidValidator;
	
	@RequestMapping(value= "/getCommercialBid/{tahdrId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getCommercialBid(@PathVariable("tahdrId") Long tahdrId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		CommercialBidDto cb=commercialBidService.getCommercialBid(tahdrId, partner.getbPartnerId());
		TAHDRDto tahdr=tahdrService.findDto("getTahdrWithActiveDetail", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
		ReferenceListDto refDto=refListService.findDto("getReferenceListByCode",AbstractContextServiceImpl.getParamMap("code", td.getCommencementPeriodCode()));
		if(cb!=null){
			response.addObject("bidder",cb.getBidder());
		}else{
			response.addObject("bidder",null);
		}
		
		response.addObject("commercialBid", cb);
		response.addObject("ref", refDto);
		return response;
	}
	
	@RequestMapping(value= "/getPartnerCommercialBid/{tahdrId}/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPartnerCommercialBid(@PathVariable("tahdrId") Long tahdrId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		CommercialBidDto cb=commercialBidService.getCommercialBid(tahdrId, partnerId);
		TAHDRDto tahdr=tahdrService.findDto("getTahdrWithActiveDetail", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
		ReferenceListDto refDto=refListService.findDto("getReferenceListByCode",AbstractContextServiceImpl.getParamMap("code", td.getCommencementPeriodCode()));
		if(cb!=null){
			response.addObject("bidder",cb.getBidder());
		}else{
			response.addObject("bidder",null);
		}
		
		response.addObject("commercialBid", cb);
		response.addObject("ref", refDto);
		return response;
	}
	
	@RequestMapping(value= "/getCommercialBidDocs/{tahdrId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getCommercialBidDocs(@PathVariable("tahdrId") Long tahdrId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		Set<BidderSectionDocDto> bidderSecDocSet=commercialBidService.getCommercialBidDocs(tahdrId, AppBaseConstant.COMMERCIAL_SECTION, partner.getbPartnerId());
		response.addObject("bidderSecDocSet", bidderSecDocSet);
		return response;
	}
	
	@RequestMapping(value= "/getPartnerCommercialBidDocs/{tahdrId}/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getCommercialBidDocs(@PathVariable("tahdrId") Long tahdrId,@PathVariable("partnerId") Long partnerId, HttpServletRequest request){
		CommonUtil.resetReportFileName(request);
		CustomResponseDto response=new CustomResponseDto();
		Set<BidderSectionDocDto> bidderSecDocSet=commercialBidService.getCommercialBidDocs(tahdrId, AppBaseConstant.COMMERCIAL_SECTION, partnerId);
		response.addObject("bidderSecDocSet", bidderSecDocSet);
		return response;
	}
	
	@RequestMapping(value="/saveCommercialBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CommercialBidDto saveCommercialBid(@ModelAttribute("commercialBid")CommercialBidDto cbDto){
		Errors errors =  new Errors();
		if(cbDto!=null && cbDto.getBidder()!=null && (cbDto.getBidder().getBidderId()!=null || cbDto.getBidder().getBidderId()>0)){
			CommercialBidDto cb=commercialBidService.findDto("getCommercialBidByBidderId", AbstractContextServiceImpl.getParamMap("bidderId", cbDto.getBidder().getBidderId()));
			if(cb!=null && cbDto!=null && cbDto.getBidder()!=null){
				cb=commercialBidService.copyNewDtoToOld(cbDto, cb);
			}else{
				cb=cbDto;
			}
			commercialBidValidator.saveValidate(cb, errors);
			if(errors.getErrorCount()>0){
				ResponseDto response=new ResponseDto();
				response.setHasError(true);
				response.setErrors(errors.getErrorList());
				cbDto.setResponse(response);
				return cbDto;
			}else{
				return commercialBidService.save(cb);
			}
		}else{
			ResponseDto resp=new ResponseDto(true, "Missing Commercial Bid information.");
			cbDto.setResponse(resp);
			return cbDto;
		}
		
		/*return bidderService.saveCommercialBid(bidder);*/
	}

	@RequestMapping(value="/saveCommercialSectionDocument",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CommercialBidDto saveCommercialSectionDocument(@ModelAttribute("commercialBid")CommercialBidDto cbDto){
		if(cbDto!=null && cbDto.getBidder()!=null){
			CommercialBidDto cb=commercialBidService.findDto("getCommercialBidByBidderId", AbstractContextServiceImpl.getParamMap("bidderId", cbDto.getBidder().getBidderId()));
			if(cb!=null){
				cb=commercialBidService.copyNewDtoToOld(cbDto, cb);
			}else{
				cb=cbDto;
			}
			return commercialBidService.save(cb);
		}else{
			ResponseDto resp=new ResponseDto(true, "Missing Commercial Bid information.");
			cbDto=new CommercialBidDto();
			cbDto.setResponse(resp);
			return cbDto;
		}
	}
	
	@RequestMapping(value="/deleteFinalCBDoc/{bidderId}/{tahdrDetailId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto deleteFinalCBDoc(@PathVariable("bidderId")Long bidderId,@PathVariable("tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		if(bidderId!=null){
			Long result=commercialBidService.updateStatusForCommercialBid(AppBaseConstant.COMMERCIAL_SECTION,bidderId);
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
