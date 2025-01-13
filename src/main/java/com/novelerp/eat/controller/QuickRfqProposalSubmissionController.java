package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.service.BidderService;

@Controller
public class QuickRfqProposalSubmissionController {
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ReferenceListService referenceListService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	
	@RequestMapping(value= {"/quickRfqProposalSubmission"},method =RequestMethod.GET)
	public ModelAndView tenderSubmission(){
		ModelAndView model=new ModelAndView("quickRfqProposalSubmission");
		/*String fileSizeForWT = appPropertyUtil.getProperty("tender.file.size");*/
		String fileSizeForWT =sysConfiguratorService.getPropertyConfigurator("tender.file.size");

		/*String fileSizeForPT = appPropertyUtil.getProperty("partner.file.size");*/
		String fileSizeForPT =sysConfiguratorService.getPropertyConfigurator("partner.file.size");

		model.addObject("documentType",ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL);
		model.addObject("fileSizeForWT", fileSizeForWT);
		model.addObject("fileSizeForPT", fileSizeForPT);
		return model;
	}
	
	@RequestMapping(value= "/getQuickRFQ/{docType}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)/*/{documentType}*/
	@ResponseBody
	public CustomResponseDto getPurchasedTAHDR(@PathVariable("docType")String docType){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner = contextService.getPartner();
		response.addObject("bidTypeList", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
		List<BidderDto> bidders=null;
		if(docType.equals(ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL)){
			bidders=bidderService.getBidderTahdrDetailList("QRFQ","getRfqByTypeCode");
			response.addObject("tenderType", "QRFQ");
			contextService.setSFTPRequiredInfo(partner, "QRFQ", null);
			
		}else if(docType.equals(ContextConstant.DOCUMENT_REQUEST_FOR_PROPOSAL)){
			bidders=bidderService.getBidderTahdrDetailList("RFQ","getRfqByTypeCode");
			response.addObject("tenderType", "RFQ");
			contextService.setSFTPRequiredInfo(partner, "RFQ", null);
			
		}
		response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
		
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
	
	@RequestMapping(value= "/getRfqByTypeCode/{typeCode}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomResponseDto getRfqByTypeCode(@PathVariable("typeCode")String typeCode){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner = contextService.getPartner();
		if(partner.checkIsValidTypeCode(typeCode)){
			Map<String,Object> params=new HashMap<>();
			params.put("tahdrId",typeCode);
			params.put("partnerId", partner.getbPartnerId());
			response.addObject("bidTypeList", referenceListService.getReferenceListMap(AppBaseConstant.BID_TYPE));
			List<BidderDto> bidders=bidderService.findDtos("getRfqByTypeCode", params);
			response.addObject("bidderList", bidders);
			response.addObject("bidStatusList", referenceListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS));
		}else{
			response.setMessage("Invalid Request");
		}
		contextService.setSFTPRequiredInfo(partner, typeCode, null);
		return response;
	}
}
