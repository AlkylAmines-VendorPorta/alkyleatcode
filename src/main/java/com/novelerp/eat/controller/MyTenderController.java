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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.DepartmentService;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.MyTenderService;
import com.novelerp.eat.service.PaymentDetailService;

/**
 * @author Aman Sahu
 *
 */
@Controller
public class MyTenderController {
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	private MyTenderService myTenderService;
	
	@Autowired
	private LocationTypeService locationTypeService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@RequestMapping(value= {"/myTenders"},method =RequestMethod.GET)
	public ModelAndView myTenders(){
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		String dataUrl="getMyTenders";
		String dataForTypeCode="getMyTendersByTypeCode";
		System.out.println("...inside MyTenderController-myTenders....");
		ModelAndView model= new ModelAndView("myTenders");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		model.addObject("role",role);
		return model;
	}
	
	@RequestMapping(value= {"/myAuctions"},method =RequestMethod.GET)
	public ModelAndView myAuctions(){
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		String dataUrl="getMyTenders";
		String dataForTypeCode="getMyTendersByTypeCode";
		System.out.println("...inside MyTenderController-myTenders....");
		ModelAndView model= new ModelAndView("myTenders");
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		model.addObject("role",role);
		return model;
	}

	@RequestMapping(value= "/getMyTenders", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto tenderPreparationData(@RequestParam("tenderTypeCode")String tenderTypeCode,
												@RequestParam("pageNumber")int pageNumber, 
												@RequestParam("pageSize") int pageSize,
												@RequestParam("searchMode") String searchMode , 
												@RequestParam("searchValue") String searchValue){
		
		RoleDto roleDto= contextService.getDefaultRole();
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		int LastPage=0;
		long countResult=0;
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,Object> listParam=new HashMap<String,Object>();
		UserDetailsDto userDetail=contextService.getUserDetails();
		String locationType=userDetail.getLocationTypeRef();
		param.put("code",locationType);
		LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
		if(locationTypeDto!=null){
			listParam.put("levels", locationTypeDto.getLevels());
			List<LocationTypeDto> officeType=locationTypeService.findDtos("getLocationTypeOnLevels", listParam);
			response.addObject("officeType", officeType);
		}
		List<DepartmentDto> deptList=deptService.getDepartmentList(partner);
		Map<String,String> tahdrTypeList=refListService.getReferenceListMap(AppBaseConstant.TENDER_TYPE);
		Map<String,String> bidTypeList=refListService.getReferenceListMap(AppBaseConstant.BID_TYPE);
		Map<String,String> priceBasis=refListService.getReferenceListMap(AppBaseConstant.BASIS_TYPE);
/*		Map<String,String> officeType=refListService.getReferenceListMap(AppBaseConstant.OFFICE_TYPE);*/
		Map<String,String> budgetType=refListService.getReferenceListMap(AppBaseConstant.BUDGET_TYPE);
		Map<String,String> bidSection=refListService.getReferenceListMap(AppBaseConstant.BID_SECTION);
		Map<String,String> tenderStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		Map<String,String> bidderStatus=refListService.getReferenceListMap(AppBaseConstant.BIDDER_STATUS);
		List<ReferenceListDto> viewButton= myTenderService.getMyTenderLinks(roleDto.getValue());
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",
				AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE)));
		response.addObject("deptList", deptList);
		
		response.addObject("bidderStatus", bidderStatus);
		response.addObject("tahdrTypeList", tahdrTypeList);
		response.addObject("bidTypeList", bidTypeList);
		response.addObject("priceBasis", priceBasis);
		/*response.addObject("officeType", officeType);*/
		response.addObject("budgetType", budgetType);
		response.addObject("tenderTypeCode", tenderTypeCode);
		response.addObject("bidSection", bidSection);
		response.addObject("viewButton", viewButton);
		response.addObject("tenderStatus", tenderStatus);
		response.addObject("roleDto", roleDto);
		response.addObject("isVendor", partner.checkIsValidTypeCode(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT));
		response.addObject("isContractor",partner.checkIsValidTypeCode(AppBaseConstant.TENDER_TYPE_CODE_WORKS));
		List<TAHDRDto> tahdrList=null;
		try{
			if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(roleDto.getValue())){
				if(tenderTypeCode.equals("RA") || tenderTypeCode.equals("FA")){
					if(tenderTypeCode.equals("RA")){
						tenderTypeCode="PT";
					}
					countResult = myTenderService.getAuctionByTypeCount(tenderTypeCode,partner.getbPartnerId(),searchMode, searchValue);
					tahdrList=myTenderService.getAuctionByTypeCode(tenderTypeCode,partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
				}else{
					countResult = myTenderService.getTahdrByTypeCodeCount(partner.getTenderPriority(),partner.getbPartnerId(),searchMode, searchValue);
					tahdrList=myTenderService.getTahdrByTypeCode(partner.getTenderPriority(),partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
				}
				response.addObject("isManufacturer", partner.getIsManufacturer());
				List<LocationTypeDto> officeType=locationTypeService.findDtos("getAllActiveLocationType", null);
				response.addObject("officeType", officeType);
				
			}else{
				if(tenderTypeCode.equals("RA") || tenderTypeCode.equals("FA")){
					if(tenderTypeCode.equals("RA")){
						tenderTypeCode="PT";
					}
					countResult = myTenderService.getAuctionByTypeCount(tenderTypeCode,partner.getbPartnerId(),searchMode, searchValue);
					tahdrList=myTenderService.getAuctionByTypeCode(tenderTypeCode,partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
				}else{
					countResult = myTenderService.getTahdrByTypeCodeCount(tenderTypeCode,partner.getbPartnerId(),searchMode, searchValue);
					tahdrList=myTenderService.getTahdrByTypeCode(tenderTypeCode,partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
				}
				
			}
			/*long countResult = tahdrList.size();*/
			LastPage = (int) ((countResult / pageSize) + 1);
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
		response.addObject("tenderType", partner.getTenderPriority());
		response.setData(tahdrList);
		response.addObject("LastPage", LastPage);
		contextService.setSFTPRequiredInfo(partner, tenderTypeCode, null);
		return response;
	}
	
	@RequestMapping(value= "/getMyTendersByTypeCode", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTahdrByTypeCode(@RequestParam("tenderTypeCode")String tenderTypeCode,
												@RequestParam("pageNumber")int pageNumber, 
												@RequestParam("pageSize") int pageSize,
												@RequestParam("searchMode") String searchMode , 
												@RequestParam("searchValue") String searchValue){
		
		CustomResponseDto response=new CustomResponseDto();
		RoleDto roleDto= contextService.getDefaultRole();
		BPartnerDto partner=contextService.getPartner();
		long countResult=0;
		Map<String, Object> objectMap = new HashMap<>();
		int LastPage=0;
		
		List<TAHDRDto> tahdrList=null;
		try{
			if(ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(roleDto.getValue())){
				countResult = myTenderService.getTahdrByTypeCodeCount(tenderTypeCode,partner.getbPartnerId(),searchMode, searchValue);
				tahdrList=myTenderService.getTahdrByTypeCode(tenderTypeCode,partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
			}else{
				countResult = myTenderService.getTahdrByTypeCodeCount(tenderTypeCode,partner.getbPartnerId(),searchMode, searchValue);
				tahdrList=myTenderService.getTahdrByTypeCode(tenderTypeCode,partner.getbPartnerId(),pageNumber,pageSize,searchMode,searchValue);
			}
			
			LastPage = (int) ((countResult / pageSize) + 1);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		response.setData(tahdrList);
		objectMap.put("LastPage", LastPage);
		response.setObjectMap(objectMap);
		contextService.setSFTPRequiredInfo(partner, tenderTypeCode, null);
		return response;
	}
	@RequestMapping(value= "/getBidderByTahdrId/{tahdrId}", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTahdrByTypeCode(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		BidderDto bidder=new BidderDto();
		PaymentDetailDto emdPayment=null;
		if(partner!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("tahdrId", tahdrId);
			params.put("partnerId", partner.getbPartnerId());
			bidder=bidderService.findDto("getSimpleBidderWithPaymentByTahdrId", params);
			
			params.put("paymentType", AppBaseConstant.EMD);
			emdPayment=paymentDetailService.findDto("getPartnerPaymentDetailByTypeAndTahdrId",params);
		}
		response.addObject("bidder", bidder);
		response.addObject("emdPayment", emdPayment);
		return response;
	}
	
}
