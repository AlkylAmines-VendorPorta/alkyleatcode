/**
 * @author Ankush
 */

package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.converter.TAHDRDetailConverter;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.TAHDRApprovalMatrixService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.TAHDRValidator;

@Controller
public class TAHDRController {

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private LocationTypeService locationTypeService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
		
	@Autowired
	private TAHDRDetailConverter tahdrDetailConverter;
	
	@Autowired
	private TAHDRApprovalMatrixService tahdrApprovalMatrixService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@Autowired
	private TAHDRValidator tahdrValidator;
	
	@Autowired
	private BidderService bidderService;
	
	@RequestMapping(value= "/tenderPreparation", method=RequestMethod.GET)
	public ModelAndView tenderPreparation(){
		ModelAndView model= new ModelAndView("tenderPreparation");
		String dataUrl="tenderPreparationData";
		String dataForTypeCode="getTAHDRByTypeCode";
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_TENDER);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
	@RequestMapping(value= "/auctionPreparation", method=RequestMethod.GET)
	public ModelAndView auctionPreparation(){
		ModelAndView model= new ModelAndView("tenderPreparation");
		String dataUrl="tenderPreparationData";
		String dataForTypeCode="getTAHDRByTypeCode";
		model.addObject("documentType",ContextConstant.DOCUMENT_TYPE_AUCTION);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
	@RequestMapping(value= "/createTahdr", method=RequestMethod.POST)
	public @ResponseBody TAHDRDto createTAHDR(@ModelAttribute("tahdr") TAHDRDto tahdr){
		/*ResponseDto response=null;*/
		/*TAHDRDto dto=tahdrService.findDto("getQueryForTAHDRByCode", AbstractContextServiceImpl.getParamMap("tahdrCode", tahdr.getTahdrCode()));*/
		
		Errors errors =  new Errors();
		ResponseDto response=new ResponseDto();
		tahdrValidator.validate(tahdr, errors);
		if(errors.getErrorCount()>0){
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			tahdr.setResponse(response);
		}else{
			TAHDRDto dto=tahdrService.findDto("getQueryForTAHDRByCode", AbstractContextServiceImpl.getParamMap("tahdrCode", tahdr.getTahdrCode()));	
			if((null==dto && (null==tahdr.getTahdrId() || !(tahdr.getTahdrId()>0)))){
				tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
				tahdr=tahdrService.save(tahdr);	
			}
			else if(null!=dto && (CommonUtil.isEqual(dto.getTahdrId(), tahdr.getTahdrId()))){
				Map<String, Object> tenderDoc = new HashMap<>();
				tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
				tahdr=tahdrService.updateDto(tahdr);
				if(dto.getTahdrDetail()!=null && !dto.getTahdrDetail().isEmpty() ){
				if(dto.getTahdrDetail().iterator().next().getTenderDoc()!=null && dto.getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
					tenderDoc.put("tenderDoc", null);
					tahdrDetailService.updateByJpql(tenderDoc, "tahdrDetailId",dto.getTahdrDetail().iterator().next().getTahdrDetailId());
				}
				}
			}
		}
		
		
		/*if(null!=dto && (dto.getTahdrId()!=tahdr.getTahdrId())){*/
		/*if(null!=dto && (!CommonUtil.isEqual(dto.getTahdrId(), tahdr.getTahdrId()))){
			response= new ResponseDto(true, "Tender Code Already Exists..!");
			tahdr.setResponse(response);
		}else*/
		/*if(null!=dto && (CommonUtil.isEqual(dto.getTahdrId(), tahdr.getTahdrId()))){
			Map<String, Object> tenderDoc = new HashMap<>();
			tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			tahdr=tahdrService.updateDto(tahdr);
			if(dto.getTahdrDetail()!=null && !dto.getTahdrDetail().isEmpty() ){
			if(dto.getTahdrDetail().iterator().next().getTenderDoc()!=null && dto.getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DRAFTED)){
				tenderDoc.put("tenderDoc", null);
				tahdrDetailService.updateByJpql(tenderDoc, "tahdrDetailId",dto.getTahdrDetail().iterator().next().getTahdrDetailId());
			}
			}
		}else if(null==dto && (null==tahdr.getTahdrId() || !(tahdr.getTahdrId()>0))){
			tahdr.setTahdrStatusCode(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
			tahdr=tahdrService.save(tahdr);	
		}*/
		
		return tahdr;
	}
	
	/*@RequestMapping(value= "/getTAHDRByTypeCode/{tahdrTypeCode}", method=RequestMethod.POST)
	@ResponseBody
	public List<TAHDRDto> getTAHDRByTypeCode(@PathVariable("tahdrTypeCode")String tenderTypeCode){
		List<TAHDRDto> tahdrList=null;
		UserDetailsDto userDetail=contextService.getUserDetails();
		RoleDto roleDto= contextService.getDefaultRole();
		String role=roleDto.getValue();
		try{
			List<String> listOfTenders = tahdrService.getAccessClause(roleDto.getValue());
			Map<String,Object> listParam=new HashMap<String,Object>();
			listParam.put("tenderStatusList", listOfTenders);
			listParam.put("typeCode",tenderTypeCode);
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role)){
				String locationType=userDetail.getLocationTypeRef();
				listParam.put("locationType",locationType);
				tahdrList= tahdrService.findDtos("getTenderListsByRoleAndLoc",listParam);
			}else{
				tahdrList= tahdrService.findDtos("getTenderListsByRole",listParam);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return tahdrList;
	}*/
	
	@RequestMapping(value= "/getTAHDRByTypeCode", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto getTAHDRByTypeCode(@RequestParam("tahdrTypeCode")String tenderTypeCode,
												@RequestParam("pageNumber")int pageNumber, 
												@RequestParam("pageSize") int pageSize,
												@RequestParam("searchMode") String searchMode , 
												@RequestParam("searchValue") String searchValue){
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> objectMap = new HashMap<>();
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,Object> params=new HashMap<String,Object>();
		List<TAHDRDto> approvalMatrixList=null;
		UserDetailsDto userDetail=contextService.getUserDetails();
		UserDto userDto=contextService.getUser();
		param.put("userId", userDto.getUserId());
		param.put("typeCode", tenderTypeCode); 
		try{
				String locationType=userDetail.getLocationTypeRef();
				params.put("code",locationType);
				LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", params);
				param.put("levels", locationTypeDto.getLevels()); 
				long countResult = tahdrService.getTahdrListQueryCount(param,searchMode, searchValue);
				int LastPage = (int) ((countResult / pageSize) + 1);
				approvalMatrixList=tahdrService.getTahdrList(param, pageNumber, pageSize,searchMode, searchValue);
				objectMap.put("LastPage", LastPage);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		customResponseDto.setData(approvalMatrixList);
		customResponseDto.setObjectMap(objectMap);
		BPartnerDto partner=contextService.getPartner();
		contextService.setSFTPRequiredInfo(partner, tenderTypeCode, null);
		return customResponseDto;
	}
	
	@RequestMapping(value= "/tenderPreparationData", method=RequestMethod.POST)
	@ResponseBody
	public CustomResponseDto tenderPreparationData(@RequestParam("tenderTypeCode")String tenderTypeCode,
												   @RequestParam("pageNumber")int pageNumber, 
												   @RequestParam("pageSize") int pageSize,
												   @RequestParam("searchMode") String searchMode , 
												   @RequestParam("searchValue") String searchValue){
		CustomResponseDto response=new CustomResponseDto();
		Map<String,Object> param=new HashMap<String,Object>();
		Map<String,Object> listParam=new HashMap<String,Object>();
		BPartnerDto partner=contextService.getPartner();
		UserDetailsDto userDetail=contextService.getUserDetails();
		String locationType=userDetail.getLocationTypeRef();
		param.put("code",locationType);
		LocationTypeDto locationTypeDto=locationTypeService.findDto("getLocationTypeOnType", param);
		if(locationTypeDto!=null){
			listParam.put("levels", locationTypeDto.getLevels());
			List<LocationTypeDto> officeType=locationTypeService.findDtos("getLocationTypeOnLevels", listParam);
			response.addObject("officeType", officeType);
		}
		
		RoleDto roleDto= contextService.getDefaultRole();
		List<DepartmentDto> deptList=deptService.getDepartmentList(partner);
		Map<String,String> tahdrTypeList=refListService.getReferenceListMap(AppBaseConstant.TENDER_TYPE);
		Map<String,String> bidTypeList=refListService.getReferenceListMap(AppBaseConstant.BID_TYPE);
		Map<String,String> priceBasis=refListService.getReferenceListMap(AppBaseConstant.BASIS_TYPE);
		/*Map<String,String> officeType=refListService.getReferenceListMap(AppBaseConstant.OFFICE_TYPE);*/
		Map<String,String> budgetType=refListService.getReferenceListMap(AppBaseConstant.BUDGET_TYPE);
		Map<String,String> bidSection=refListService.getReferenceListMap(AppBaseConstant.BID_SECTION);
		Map<String,String> tenderStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		CustomResponseDto tahdrList=getTAHDRByTypeCode(tenderTypeCode,pageNumber,pageSize,searchMode,searchValue);
		List<ReferenceListDto> viewButton= tahdrService.getAccessAction(roleDto.getValue());
		response.addObject("paymentType", paymentTypeService.findDto("getPaymentTypeByCode",AbstractContextServiceImpl.getParamMap("paymentTypeCode",AppBaseConstant.TENDER_PURCHASE_FEE)));
		response.addObject("deptList", deptList);
		response.addObject("tahdrTypeList", tahdrTypeList);
		response.addObject("bidTypeList", bidTypeList);
		response.addObject("listTahdr", tahdrList);
		response.addObject("priceBasis", priceBasis);
		response.addObject("budgetType", budgetType);
		response.addObject("tenderTypeCode", tenderTypeCode);
		response.addObject("bidSection", bidSection);
		response.addObject("viewButton", viewButton);
		response.addObject("tenderStatus", tenderStatus);
		response.addObject("roleDto", roleDto);
		contextService.setSFTPRequiredInfo(partner, tenderTypeCode, null);
		return response;
	}
	
	/*@RequestMapping(value= "/getTAHDR/{tahdrId}", method=RequestMethod.POST)
	public @ResponseBody TAHDRDto getTAHDR(@PathVariable("tahdrId")Long tahdrId){
		TAHDRDto tahdr=null;
		try{
			tahdr=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId),tahdrDetailConverter);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tahdr;
	}*/
	
	@RequestMapping(value= "/getTAHDR/{tahdrId}/{tahdrDetailId}", method=RequestMethod.POST)
	@ResponseBody
	public  CustomResponseDto getTAHDR(@PathVariable("tahdrId")Long tahdrId,@PathVariable("tahdrDetailId")Long tahdrDetailId){
		Map<String,Object> listParam=new HashMap<String,Object>();
		TAHDRDto tahdr=null;
		TAHDRDetailDto tahdrDetail=null;
		TAHDRApprovalMatrixDto tahdrApprovalMatrix=null;
		UserDto userDto=contextService.getUser();
		List<TAHDRApprovalMatrixDto> approvalMatrix=null;
		tahdr=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		tahdrDetail=tahdrDetailService.findDto("getQueryForTAHDRDetailByTahdrDetailId", AbstractContextServiceImpl.getParamMap("tahdrDetailId", tahdrDetailId));
		approvalMatrix=tahdrApprovalMatrixService.findDtos("getTahdrApprovalMatrixDetails", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId));
		listParam.put("userId", userDto.getUserId());
		listParam.put("tahdrId", tahdrId);
		tahdrApprovalMatrix=tahdrApprovalMatrixService.findDto("getApprovalMatrixDataFromTahdrId",listParam);
		Map<String,String> tahdrStatus=refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("tahdr", tahdr);
		response.addObject("tahdrDetail", tahdrDetail);
		response.addObject("approvalMatrix", approvalMatrix);
		response.addObject("tahdrApprovalMatrix", tahdrApprovalMatrix);
		response.addObject("tahdrStatus", tahdrStatus);
		return response;
	}

	@RequestMapping(value="/submitTahdrDetails",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto submitTahdrDetails(@ModelAttribute("tahdrId") TAHDRDto tahdr)
	{
		Map<String,Object> listParam=new HashMap<String,Object>();
		ResponseDto tahdrData=null;
		UserDto userDto=contextService.getUser();
		TAHDRDetailDto  tahdrDetaildto=tahdrDetailService.findDto(tahdr.getTahdrDetailList().iterator().next().getTahdrDetailId());
		tahdrDetaildto.setPublishingDate(tahdr.getTahdrDetailList().iterator().next().getPublishingDate()); 
		tahdrDetaildto.setTahdr(tahdr);
		listParam.put("userId", userDto.getUserId());
		listParam.put("tahdrId", tahdr.getTahdrId());
		TAHDRApprovalMatrixDto matrixUser=tahdrApprovalMatrixService.findDto("getNextUserToUpdateStatusAP", listParam);
		if(matrixUser!=null){
		tahdrData= tahdrService.submitTahdrDetails(tahdr,userDto,matrixUser.getUser(),tahdrDetaildto);
		}
		else{
		tahdrData= tahdrService.submitTahdrDetails(tahdr,userDto,null,tahdrDetaildto);
		}
		TAHDRDto tahdrDto=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdr.getTahdrId()),tahdrDetailConverter);
		List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdr.getTahdrId()));
		List<TAHDRApprovalMatrixDto> approvalMatrixuser = tahdrApprovalMatrixService.findDtos("getTahdrApprovalMatrixDetails", AbstractContextServiceImpl.getParamMap("tahdrId", tahdr.getTahdrId()));
		if(tahdrData!=null && !tahdrData.isHasError()){
			tahdrService.mailNotificationStatusWise(tahdrDto,bidderData,approvalMatrixuser,tahdr.getTahdrStatusCode());
			}
		
		CustomResponseDto response= new CustomResponseDto();
		response.addObject("statusData", tahdrData);
		response.addObject("tahdrData", tahdrDto);
		return response;
	}
	
	@RequestMapping(value= "/getRejectedTahdr/{tahdrId}", method=RequestMethod.POST)
	@ResponseBody
	public  CustomResponseDto getRejectedTahdr(@PathVariable("tahdrId")Long tahdrId){
		CustomResponseDto response=new CustomResponseDto();
		
		boolean result=false;
		
		if(tahdrId!=null){
		Map<String,Object> listParam=new HashMap<String,Object>();
		listParam.put("tahdrId", tahdrId);
		result=tahdrService.setTenderStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED,tahdrId);
		ResponseDto matrixUser=tahdrService.changeStatusFromRJtoDR(tahdrId);
		TAHDRDto tahdrDto=tahdrService.findDto("getQueryForTAHDRById",AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId),tahdrDetailConverter);
		response.addObject("resultStatus", result);
		response.addObject("approvalMatrixData", matrixUser);
		response.addObject("tahdrData", tahdrDto);
		}
		else{
			response.addObject("resultStatus", result);
		}
		return response;
	}

	@RequestMapping(value= "/getTAHDRDetailsById/{tahdrId}", method=RequestMethod.POST)
	@ResponseBody
	public  CustomResponseDto getTAHDRDetail(@PathVariable("tahdrId")Long tahdrId){
		TAHDRDto tahdr=tahdrService.findDto("getTahdrWithDetailAndDoc",AbstractContextServiceImpl.getParamMap("tahdrId",tahdrId));
		String websiteUrl = AppBaseConstant.WEBSITE_URL;
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("tahdr", tahdr);
		response.addObject("websiteUrl", websiteUrl);
		return response;
	}
	@RequestMapping(value = "/getPrivateAuctionList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TAHDRDto> getPrivateAuctionList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TAHDRDto> privateAuctionList = tahdrService.findDtos("getPrivateAuctionList", params);
		
		return privateAuctionList;
	}
	
	/*@RequestMapping(value = "/getQuickRFQ", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TAHDRDto> getQuickRFQ() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TAHDRDto> getQuickRFQ = tahdrService.findDtos("getQuickRFQ", params);
		
		return getQuickRFQ;
	}*/
	
	@RequestMapping(value = "/getRFQ", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TAHDRDto> getRFQ() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<TAHDRDto> getRFQ = tahdrService.findDtos("getRFQ", params);
		
		return getRFQ;
	}
	
	@RequestMapping(value = "/getPrivateAuctionDetailsByID/{tahdrId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TAHDRDto getPrivateAuctionDetailsByID(@PathVariable("tahdrId") Long tahdrId) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("tahdrId", tahdrId);
		TAHDRDto tahdrDto=tahdrService.findDto("getPrivateAuctionListByID", params);
		return tahdrDto;
	}
}

