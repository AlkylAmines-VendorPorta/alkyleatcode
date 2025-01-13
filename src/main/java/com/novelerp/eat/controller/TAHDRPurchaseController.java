package com.novelerp.eat.controller;

import java.util.List;

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

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.validator.TahdrPurchaseValidator;

@Controller
public class TAHDRPurchaseController {
		
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private ReferenceListService referenceListService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@Autowired
	private TahdrPurchaseValidator tahdrPurchaseValidator;
	
	/*view and load payment mode and type*/
	@RequestMapping(value = "/tenderPurchase", method = RequestMethod.GET)
	public ModelAndView tenderPurchaseView() {
		ModelAndView model=new ModelAndView("tenderPurchase");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		return  model;
	}
	/*view and load payment mode and type*/
	
	/*view and load payment mode and type*/
	@RequestMapping(value = "/auctionPurchase", method = RequestMethod.GET)
	public ModelAndView auctionPurchaseView() {
		ModelAndView model=new ModelAndView("tenderPurchase");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		return  model;
	}
	/*view and load payment mode and type*/
	
	/*@RequestMapping(value = "/getTenderListForPurchase/{docType}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")///{documentType}
	public @ResponseBody CustomResponseDto getTenderListForPurchase(@PathVariable("docType")String docType) {@PathVariable("documentType")String docType
		
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partnerDto = contextService.getPartner();
		List<TAHDRDetailDto> tenderList = null;
		
		if((partnerDto.getIsCEApproved()!=null && partnerDto.getIsCEApproved().equals("Y"))&&
				(partnerDto.getTenderPriority().equals(AppBaseConstant.TENDER_TYPE_CODE_WORKS)
				|| partnerDto.getAuctionPriority().equals(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)))
		{
		if(docType.equals(ContextConstant.DOCUMENT_TYPE_TENDER)){
			tenderList=tahdrDetailService.getTenderList(partnerDto.getTenderPriority(),partnerDto);
		}else{
			tenderList=tahdrDetailService.getTenderList(partnerDto.getAuctionPriority(),partnerDto);
		}
		
		response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",
				AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE)));
		
		if(partnerDto.checkIsValidTypeCode(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			List<PartnerOrgDto> factoryList=partnerOrgService.findDtos("getFactoryListQuery", AbstractContextServiceImpl.getParamMap("partnerId", partnerDto.getbPartnerId()));
			response.addObject("factoryList", factoryList);
		}
		
		if(!tenderList.isEmpty())
		{
			PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tenderList.get(0).getTahdr().getTahdrId(),AppBaseConstant.TENDER_PURCHASE_FEE);
			response.addObject("paymentDetail", paymentDetail);
		}
		response.addObject("tenderList", tenderList);
		response.addObject("tenderType", partnerDto.getTenderPriority());
		response.addObject("isVendor", partnerDto.checkIsValidTypeCode("PT"));
		response.addObject("isContractor",partnerDto.checkIsValidTypeCode("WT"));
		response.addObject("isTrader", partnerDto.getIsTrader());
		response.addObject("isManufacturer", partnerDto.getIsManufacturer());
		response.addObject("isCustomer", partnerDto.getIsCustomer());
		response.addObject("status", true);
		response.addObject("isIntraState", partnerDto.isIntraState());
		
		}
		else{
			response.addObject("status", false);
			response.addObject("resultMessage","Approval Pending From Chief Engineer !");
		}
		return response;
	}*/
/*	
	@RequestMapping(value = "/getTahdrListForPurchase/{typeCode}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getTahdrForPurchaseByTypeCode(@PathVariable("typeCode")String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partnerDto = contextService.getPartner();
		List<TAHDRDetailDto> tenderList = tahdrDetailService.getTenderList(typeCode,partnerDto);
		if(partnerDto.getIsCEApproved()!=null && partnerDto.getIsCEApproved().equals("Y"))
		{
		if(!tenderList.isEmpty())
		{
			PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tenderList.get(0).getTahdr().getTahdrId(),AppBaseConstant.TENDER_PURCHASE_FEE);
			response.addObject("paymentDetail", paymentDetail);
			
			if("PT".equals(typeCode)){
				List<PartnerOrgDto> factoryList=partnerOrgService.findDtos("getFactoryListQuery", AbstractContextServiceImpl.getParamMap("partnerId", partnerDto.getbPartnerId()));
				response.addObject("factoryList",factoryList );
			}
			
		}
		response.addObject("tenderList", tenderList);
		response.addObject("status", true);
		}
		else{
			response.addObject("status", false);
			response.addObject("resultMessage","Approval Pending From Chief Engineer !");
		}
		return response;
	}*/
	
	@RequestMapping(value= "/getTahdrListForPurchase", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTahdrForPurchaseByTypeCode(@RequestParam("docType")String typeCode,
												@RequestParam("pageNumber")int pageNumber, 
												@RequestParam("pageSize") int pageSize,
												@RequestParam("searchMode") String searchMode , 
												@RequestParam("searchValue") String searchValue){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partnerDto = contextService.getPartner();
		List<TAHDRDetailDto> tenderList = null;
		long countResult = tahdrDetailService.getTahdrPurchaseQueryCount(typeCode,partnerDto,searchMode, searchValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		tenderList=tahdrDetailService.getTenderList(typeCode,partnerDto, pageNumber, pageSize,searchMode, searchValue);
		response.addObject("LastPage", LastPage);
		if(partnerDto.getIsCEApproved()!=null && partnerDto.getIsCEApproved().equals("Y")){
			if(partnerDto.getIsRegCompleted()!=null && partnerDto.getIsRegCompleted().equals("Y"))
			{
			if(!tenderList.isEmpty())
			{
				PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tenderList.get(0).getTahdr().getTahdrId(),AppBaseConstant.TENDER_PURCHASE_FEE);
				response.addObject("paymentDetail", paymentDetail);
				
				if("PT".equals(typeCode)){
					List<PartnerOrgDto> factoryList=partnerOrgService.findDtos("getFactoryListQuery", AbstractContextServiceImpl.getParamMap("partnerId", partnerDto.getbPartnerId()));
					response.addObject("factoryList",factoryList );
				}
				
			}
			if(typeCode.equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_WORKS)){
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeFortahdrPurchaseWT", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			}
			else{
				/*response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));*/
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeFortahdrPurchasePT", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			}
			response.setData(tenderList);
			response.addObject("status", true);
			}
			else{
				response.addObject("status", false);
				response.addObject("resultMessage","Your Registration is not Complete, Kindly upload Co-Sogned File !");
			}
		}else{
			response.addObject("status", false);
			response.addObject("resultMessage","Approval is pending from CE !");
		}
		PaymentTypeDto paymentType= paymentTypeService.findDto("getPaymentTypeByCode",AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE));
		response.addObject("paymentType", paymentType);
		response.addObject("tenderType", typeCode);
		response.addObject("isVendor", partnerDto.checkIsValidTypeCode("PT"));
		response.addObject("isContractor",partnerDto.checkIsValidTypeCode("WT"));
		response.addObject("isTrader", partnerDto.getIsTrader());
		response.addObject("isManufacturer", partnerDto.getIsManufacturer());
		response.addObject("isCustomer", partnerDto.getIsCustomer());
		response.addObject("status", true);
		response.addObject("isIntraState", partnerDto.isIntraState());
		return response;	
	}
	@RequestMapping(value = "/getTenderDocPurchaseDetails/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getTenderDocPurchaseDetails(@PathVariable("id") Long id) {
		CustomResponseDto response=new CustomResponseDto();
		PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(id,AppBaseConstant.TENDER_PURCHASE_FEE);
		TAHDRDetailDto tahdrDetailDto=tahdrDetailService.findDto("QueryForTAHDRDetailByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", id));
		response.addObject("tahdrDetail", tahdrDetailDto);
		response.addObject("paymentDetail", paymentDetail);
		response.addObject("paymentDetail", paymentDetail);
		return response;
	}
	
	@RequestMapping(value="/tahdrDocPurchase")
	public @ResponseBody BidderDto tahdrDocPayment(@ModelAttribute("paymentDetail") PaymentDetailDto payment){
		BidderDto bidderDto=new BidderDto();
		try{
			PaymentDetailDto paymentDetail=new PaymentDetailDto();
			paymentDetail=paymentDetailService.purchaseTahdrDoc(payment);
			paymentDetail.setPartnerOrg(payment.getPartnerOrg());
			Errors errors =  new Errors();
			ResponseDto response=new ResponseDto();
			tahdrPurchaseValidator.validate(paymentDetail, errors);
			if(errors.getErrorCount()>0){
				response.setHasError(true);
				response.setErrors(errors.getErrorList());
				bidderDto.setResponse(response);
			}else{
				if(paymentDetail.getPaymentDetailId()==null){
				bidderDto=bidderService.purchaseTender(paymentDetail);
				}
				else{
					paymentDetail=paymentDetailService.updateDto(paymentDetail);
					bidderDto=bidderService.findDto("getBidderFromPaymentDetailId", AbstractContextServiceImpl.getParamMap("paymentDetailId", paymentDetail.getPaymentDetailId()));
					if(paymentDetail!=null)
					response.setHasError(false);
					response.setMessage("Record Saved");
					bidderDto.setResponse(response);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bidderDto;
	}
	
		@RequestMapping(value= "/getTenderListForPurchase", method=RequestMethod.POST)
		@ResponseBody
		public CustomResponseDto getTenderListForPurchase(@RequestParam("docType")String docType,
													@RequestParam("pageNumber")int pageNumber, 
													@RequestParam("pageSize") int pageSize,
													@RequestParam("searchMode") String searchMode , 
													@RequestParam("searchValue") String searchValue){
			
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partnerDto = contextService.getPartner();
		List<TAHDRDetailDto> tenderList = null;
		/*Map<String, Object> objectMap = new HashMap<>();*/
		if(partnerDto.getIsCEApproved()!=null && partnerDto.getIsCEApproved().equals("Y"))
		{
		if(docType.equals(ContextConstant.DOCUMENT_TYPE_TENDER)){
			long countResult = tahdrDetailService.getTahdrPurchaseQueryCount(partnerDto.getTenderPriority(),partnerDto,searchMode, searchValue);
			int LastPage = (int) ((countResult / pageSize) + 1);
			tenderList=tahdrDetailService.getTenderList(partnerDto.getTenderPriority(),partnerDto, pageNumber, pageSize,searchMode, searchValue);
			response.addObject("LastPage", LastPage);
			if(partnerDto.getTenderPriority().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_WORKS)){
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeFortahdrPurchaseWT", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			}
			else{
				/*response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));*/
				List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeFortahdrPurchasePT", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
				response.addObject("paymentMode", refernceList);
			}
			response.addObject("tenderType", partnerDto.getTenderPriority());
		}else{
			long countResult = tahdrDetailService.getTahdrPurchaseQueryCount(partnerDto.getAuctionPriority(),partnerDto,searchMode, searchValue);
			int LastPage = (int) ((countResult / pageSize) + 1);
			tenderList=tahdrDetailService.getTenderList(partnerDto.getAuctionPriority(),partnerDto, pageNumber, pageSize,searchMode, searchValue);
			response.addObject("LastPage", LastPage);
			/*response.addObject("paymentMode", referenceListService.getReferenceListMap(AppBaseConstant.PAYMENT_MODE));*/
			List<ReferenceListDto> refernceList=referenceListService.findDtos("getPaymentModeFortahdrPurchasePT", AbstractContextServiceImpl.getParamMap("code",AppBaseConstant.PAYMENT_MODE));
			response.addObject("paymentMode", refernceList);
			response.addObject("tenderType", partnerDto.getAuctionPriority());
		}
		
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE)));
		
		if(partnerDto.checkIsValidTypeCode(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			List<PartnerOrgDto> factoryList=partnerOrgService.findDtos("getFactoryListQuery", AbstractContextServiceImpl.getParamMap("partnerId", partnerDto.getbPartnerId()));
			response.addObject("factoryList", factoryList);
		}
		
		if(!tenderList.isEmpty())
		{
			PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tenderList.get(0).getTahdr().getTahdrId(),AppBaseConstant.TENDER_PURCHASE_FEE);
			response.addObject("paymentDetail", paymentDetail);
		}
		response.setData(tenderList);
		response.addObject("isVendor", partnerDto.checkIsValidTypeCode("PT"));
		response.addObject("isContractor",partnerDto.checkIsValidTypeCode("WT"));
		response.addObject("isTrader", partnerDto.getIsTrader());
		response.addObject("isManufacturer", partnerDto.getIsManufacturer());
		response.addObject("isCustomer", partnerDto.getIsCustomer());
		response.addObject("status", true);
		response.addObject("isIntraState", partnerDto.isIntraState());
		
		}
		else{
			response.addObject("status", false);
			response.addObject("resultMessage","Approval Pending From Chief Engineer !");
		}
		return response;
	}
}
