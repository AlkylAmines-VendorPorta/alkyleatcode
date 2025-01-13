package com.novelerp.eat.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.PartnerOrgProductService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderSectionDocService;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SignatureVerificationService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;
import com.novelerp.eat.validator.CommercialBidValidator;

/**
 * @author Aman Sahu
 * @author Vivek Birdi
 */
@Controller
public class TenderSubmissionController {
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ReferenceListService referenceListService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private PartnerOrgProductService partnerOrgProductService;
	
	@Autowired
	private BidderSectionDocService bidderSecDocService;
	
	@Autowired
	private SignatureVerificationService signatureVerification;
		
	@Autowired
	private CommercialBidService commercialBidService;
	
	@Autowired
	private CommercialBidValidator commercialBidvalidator;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	@RequestMapping(value= {"/tenderSubmission"},method =RequestMethod.GET)
	public ModelAndView tenderSubmission(){
		ModelAndView model=new ModelAndView("tenderSubmission");
		/*String fileSizeForWT = appPropertyUtil.getProperty("tender.file.size");*/
		String fileSizeForWT =sysConfiguratorService.getPropertyConfigurator("tender.file.size");

		/*String fileSizeForPT = appPropertyUtil.getProperty("partner.file.size");*/
		String fileSizeForPT =sysConfiguratorService.getPropertyConfigurator("partner.file.size");

		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		model.addObject("fileSizeForWT", fileSizeForWT);
		model.addObject("fileSizeForPT", fileSizeForPT);
		return model;
	}
	
	@RequestMapping(value= {"/auctionSubmission"},method =RequestMethod.GET)
	public ModelAndView auctionSubmission(){
		ModelAndView model=new ModelAndView("tenderSubmission");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		return model;
	}
	
	
	@RequestMapping(value= {"/myTenderSubmission"},method =RequestMethod.GET)
	public ModelAndView myTenderSubmission(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderSubmission");
		/*String fileSizeForWT = appPropertyUtil.getProperty("tender.file.size");*/
		String fileSizeForWT =sysConfiguratorService.getPropertyConfigurator("tender.file.size");

		/*String fileSizeForPT = appPropertyUtil.getProperty("partner.file.size");*/
		String fileSizeForPT =sysConfiguratorService.getPropertyConfigurator("partner.file.size");

		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		view.addObject("myTenderUrl","getPurchasedMyTender/"+tahdrId);
		view.addObject("fileSizeForWT", fileSizeForWT);
		view.addObject("fileSizeForPT", fileSizeForPT);
	    return view;
	}
	
	@RequestMapping(value= {"/myAuctionSubmission"},method =RequestMethod.GET)
	public ModelAndView myAuctionSubmission(@RequestParam("tahdrId") Long tahdrId){
		ModelAndView view=new ModelAndView("tenderSubmission");
		/*String fileSizeForWT = appPropertyUtil.getProperty("tender.file.size");*/
		String fileSizeForWT =sysConfiguratorService.getPropertyConfigurator("tender.file.size");

		/*String fileSizeForPT = appPropertyUtil.getProperty("partner.file.size");*/
		String fileSizeForPT =sysConfiguratorService.getPropertyConfigurator("partner.file.size");

		view.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		view.addObject("myTenderUrl","getPurchasedMyTender/"+tahdrId);
		view.addObject("fileSizeForWT", fileSizeForWT);
		view.addObject("fileSizeForPT", fileSizeForPT);
	    return view;
	}
	
	@RequestMapping(value= "/getPurchasedMyTender/{tahdrId}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPurchasedMyTender(@PathVariable("tahdrId") Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner = contextService.getPartner();
		response.addObject("bidTypeList", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
		
		Map<String,Object> map=new HashMap<>();
		map.put("tahdrId",tahdrId);
		map.put("partnerId", partner.getbPartnerId());
		List<BidderDto> bidders=new ArrayList<BidderDto>();
		BidderDto bidder=bidderService.findDto("getPurchasedTenderDetailByTahdrId",map);
		if(bidder!=null){
			bidders.add(bidder);
		}
		response.addObject("tenderType", partner.getAuctionPriority());
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",
				AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.EMD)));
		TAHDRDto tahdr= tahdrService.findDto(tahdrId);
		if(tahdr.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_WORKS)){
			List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeForEMDWorks", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
			response.addObject("paymentMode", refernceList);
		}
		else{
			response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
		}
		
		/*response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));*/
		response.addObject("bidderList", bidders);
		
		response.addObject("isVendor", partner.checkIsValidTypeCode("PT"));
		response.addObject("isContractor",partner.checkIsValidTypeCode("WT"));
		response.addObject("isCustomer",partner.checkIsValidTypeCode("FA"));
		response.addObject("isGstApplicable", partner.getIsGstApplicable());
		response.addObject("isIntraState", partner.isIntraState());
		response.addObject("bidStatusList", referenceListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS));
		return response;
	}
	
	@RequestMapping(value= "/getPurchasedTender/{docType}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)/*/{documentType}*/
	@ResponseBody
	public CustomResponseDto getPurchasedTAHDR(@PathVariable("docType")String docType){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner = contextService.getPartner();
		response.addObject("bidTypeList", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
		List<BidderDto> bidders=null;
		if(docType.equals(ContextConstant.DOCUMENT_TYPE_TENDER)){
			bidders=bidderService.getBidderTahdrDetailList(partner.getTenderPriority(),"getPurchasedTenderDetailByTypeCode");
			response.addObject("tenderType", partner.getTenderPriority());
			if(partner.getTenderPriority().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_WORKS)){
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeForEMDWorks", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			}
			else{
				response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
			}
			contextService.setSFTPRequiredInfo(partner, partner.getTenderPriority(), null);
		}else if(docType.equals(ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL)){
			response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
			contextService.setSFTPRequiredInfo(partner, partner.getAuctionPriority(), null);
		}else{
			bidders=bidderService.getBidderTahdrDetailList(partner.getAuctionPriority(),"getPurchasedTenderDetailByTypeCode");
			response.addObject("tenderType", partner.getAuctionPriority());
			response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
			contextService.setSFTPRequiredInfo(partner, partner.getAuctionPriority(), null);
		}
		
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",
				AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.EMD)));
		
		response.addObject("bidderList", bidders);
		
		response.addObject("isVendor", partner.checkIsValidTypeCode("PT"));
		response.addObject("isContractor",partner.checkIsValidTypeCode("WT"));
		response.addObject("isCustomer",partner.checkIsValidTypeCode("FA"));
		response.addObject("isGstApplicable", partner.getIsGstApplicable());
		response.addObject("isIntraState", partner.isIntraState());
		response.addObject("bidStatusList", referenceListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS));
		return response;
	}
	
	@RequestMapping(value= "/getPurchasedTenderByTypeCode/{typeCode}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getPurchasedTenderByTypeCode(@PathVariable("typeCode")String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner = contextService.getPartner();
		if(partner.checkIsValidTypeCode(typeCode)){
			response.addObject("bidTypeList", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
			List<BidderDto> bidders=bidderService.getBidderTahdrDetailList(typeCode,"getPurchasedTenderDetailByTypeCode");
			response.addObject("bidderList", bidders);
			response.addObject("bidStatusList", referenceListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS));
		}else{
			response.setMessage("Invalid Request");
		}
		contextService.setSFTPRequiredInfo(partner, typeCode, null);
		return response;
	}
	
	
		
	@RequestMapping(value= "/getBidsByBidderQuery/{tahdrDetailId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BidderDto getBidderBids(@PathVariable("tahdrDetailId") Long tahdrDetailId ){
		BidderDto bidder=bidderService.getBidsByBidderQuery(tahdrDetailId);
		return bidder;
	}
	
	@RequestMapping(value="/getEmdPaymentDetail/{tahdrId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getEmdPaymentDetail(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		PaymentDetailDto payment=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tahdrId,AppBaseConstant.EMD);
		List<PartnerOrgProductDto> prodList=null;
		PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tahdrId,AppBaseConstant.TENDER_PURCHASE_FEE);
		TAHDRDto tahdr=tahdrService.findDto(tahdrId);
		if(tahdr.getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			 if(paymentDetail.getIsTrader().equalsIgnoreCase("Y")){
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeForEMDWorks", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			   }
			 else{
				    if(null!=paymentDetail && null!=paymentDetail.getPartnerOrg()){
						prodList=partnerOrgProductService.getExmptedItemListByTahdr(tahdrId,partner.getbPartnerId(),paymentDetail.getPartnerOrg().getPartnerOrgId());
			        }
		        }
		}else{
			List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeForEMDWorks", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
			response.addObject("paymentMode", refernceList);
		}
		response.addObject("paymentDetail", paymentDetail);
		response.addObject("companyType", partner.getCompanyType());
		response.addObject("company_type_govt", AppBaseConstant.COMPANY_TYPE_GOVT);
		response.addObject("payment", payment);
		response.addObject("prodList", prodList);
		return response;
	}
	
	@RequestMapping(value="/submitFinalBid",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CommercialBidDto submitFinalBid(@ModelAttribute("CommercialBid")CommercialBidDto cb,HttpServletRequest request){
		ResponseDto response=new ResponseDto();
		response.setHasError(true);
		
		AttachmentDto digiSign=null;
		digiSign=cb.getDigiSignedDoc();
		cb=commercialBidService.findDto("getCommercialBidById",AbstractContextServiceImpl.getParamMap("commercialBidId", cb.getCommercialBidId()));
		cb.setDigiSignedDoc(digiSign);
		Errors errors=new Errors();
		commercialBidvalidator.validate(cb, request, errors);
		if(errors.getErrorCount()>0){
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			cb.setResponse(response);
		}else{
			/*response=signatureVerification.verifyDigitalSignature(digiSign);*/
			response=new ResponseDto(false, "Valid");
			if(response.isHasError()){
				cb.setResponse(response);
			}else{
				cb.setDigiSignedDoc(digiSign);
				cb.setStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
				cb=commercialBidService.submitCommercailBid(cb);
			}
		}
		
		return cb;
	}
	
	@RequestMapping(value="/getItemQuotedStatus/{tahdrDetailId}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getItemQuotedStatus(@PathVariable("tahdrDetailId")Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		List<ItemBidDto> itemBidList= itemBidService.findDtos("getQueryForQuotedStatus", AbstractContextServiceImpl.getParamMap("tahdrDetailId",tahdrDetailId));
		response.addObject("itemBidList", itemBidList);
		return response;
	}
	
	@RequestMapping(value="/deleteBidderSecDoc",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto deleteBidderSecDoc(@RequestParam("bidderSecDocId")Long bidderSecDocId,@RequestParam("bidderId")Long bidderId,@RequestParam(value="itemBidId", required=false)Long itemBidId,@RequestParam(value="tahdrDetailId", required=false)Long tahdrDetailId){
		CustomResponseDto response=new CustomResponseDto();
		ResponseDto res=new ResponseDto();
		BPartnerDto partner=contextService.getPartner();
		Map<String, Object> map=new HashMap<>();
		map.put("bidderSecDocId", bidderSecDocId);
		map.put("partnerId", partner.getbPartnerId());
		BidderSectionDocDto bidderSecDoc=bidderSecDocService.findDto("getBidderSecDocById",map);
		if(bidderSecDoc!=null){
			bidderSecDocService.deleteById(bidderSecDocId);
			commercialBidService.updateStatusForCommercialBid(bidderSecDoc.getBidSection(),bidderId);
			technicalBidService.updateStatusForTechnicalBid(bidderSecDoc.getBidSection(), bidderId, itemBidId);
			priceBidService.updateStatusForPriceBid(bidderSecDoc.getBidSection(), bidderId, itemBidId);
			bidderSecDoc.setAttachment(null);
			bidderSecDoc.setBidderSectionDocId(null);
			res.setHasError(false);
			res.setMessage("Deleted");
			bidderService.mailNotificationForReSubmission(tahdrDetailId);
		}else{
			bidderSecDoc=new BidderSectionDocDto();
			res.setHasError(true);
			res.setMessage("Invalid Input");
		}
		bidderSecDoc.setResponse(res);
		response.addObject("bidderSecDoc", bidderSecDoc);
		return response;
	}
	
	@RequestMapping(value= "/getBidderByBidderId/{bidderId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getBidderListByTahdrId(@PathVariable("bidderId")Long bidderId){
		CustomResponseDto response=new CustomResponseDto();
		BidderDto bidder= bidderService.findDto("getBidder",AbstractContextServiceImpl.getParamMap("bidderId", bidderId));
		response.addObject("bidder", bidder);
		return response;
	}
}
