package com.novelerp.appcontext.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.appbase.master.dto.MAuctionParticipantMapDto;
import com.novelerp.appbase.master.service.MAuctionParticipantService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.appcontext.validator.PartnerValidator;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.service.GSTVendorClassService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.ReconAccountService;
import com.novelerp.eat.service.VendorAccountGroupService;
import com.novelerp.eat.service.WithHoldingTaxCodeService;

@Controller
@RequestMapping("/rest")
public class PartnerController {

	private final Logger log = LoggerFactory.getLogger(PartnerController.class);
	@Autowired
	private BPartnerService partnerService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerValidator partnerValidator;
	@Autowired
	private ReferenceListService referenceListService;
	
	@Autowired
	private UserRolesService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MAuctionParticipantService mAuctionParticipantService;
	
	/*@Autowired
	private PaymentDetailService paymentDetailService;*/
	/*@Autowired
	private PartnerOrgService partnerOrgService;*/
	/*@Autowired
	WebServiceClient sapServiceClient;*/
	@Autowired
	private WithHoldingTaxCodeService withHoldingTaxCodeService;
	@Autowired
	private VendorAccountGroupService vendorAccountGroupService;
	@Autowired
	private GSTVendorClassService gstVendorClassService;
	@Autowired
	private ReconAccountService reconAccountService;
	/*@Autowired
	private SapVendorDetailService sapVendorDetailService;
	@Autowired
	private LocationTypeService locationTypeService;*/
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService sftpFileService;
	/*@Autowired
	private SystemConfiguratorService sysConfiguratorService;*/
	/*@Autowired
	private AppPropertyUtil appPropertyUtil;*/
	@Autowired
	private PaymentDetailService paymentDetailService;
    /*@Autowired
	private UserDetailsService userDetailsService;*/
	public static final String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	@Autowired
	private BPartnerComponent partnerComponent;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value= {"/partnerRegistration"},method =RequestMethod.GET)
	public ModelAndView openProfilePage(){
		ModelAndView view = new ModelAndView("partnerRegistration");
	    RoleDto role=contextService.getDefaultRole();
	    String roleName=role.getValue();
	    if(AppBaseConstant.ROLE_VENDOR_ADMIN.equalsIgnoreCase(roleName) || AppBaseConstant.ROLE_PARTNER_USER.equalsIgnoreCase(roleName)
	    || AppBaseConstant.ROLE_SYS_ADMIN.equalsIgnoreCase(roleName) || AppBaseConstant.ROLE_PARTNER_ADMIN.equalsIgnoreCase(roleName)){
	    	view.addObject("role", role);
		    view.addObject("partnerData", "partnerRegistration");
		    return view;
	    }
	    else{
	    	return new ModelAndView("internalUserProfile");
	    }
	    
       
	}
	
	@RequestMapping(value= {"/partnerProfiles"},method =RequestMethod.GET)
	public ModelAndView partnerprofiles(){
		ModelAndView view = new ModelAndView("partnerRegistration");
		RoleDto role=contextService.getDefaultRole();
		view.addObject("role", role);
		view.addObject("partnerData", "partnerProfiles");
        return view;
	}

	
	@RequestMapping(value="/getPartner", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerData(){
		log.debug("Get Logged in Partner data");
		
		Map<String, String> companyTypes = referenceListService.getReferenceListMap(CoreReferenceConstants.COMPANY_TYPE);
		Map<String, String> contractorTypes = referenceListService.getReferenceListMap(CoreReferenceConstants.CONTRACTOR_TYPE);
		
		BPartnerDto partnerDto = contextService.getPartner();
		List<BPartnerDto> partners = new ArrayList<>();
	
		Long partnerID = null;
		if(partnerDto !=null){
			partnerID = partnerDto.getbPartnerId();
			Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerId", partnerID);
			BPartnerDto partner =  partnerService.findDto("getQueryForPartnerDetail", param);
			/*BPartnerDto partner =  partnerService.findDto(partnerID);*/
			partners.add(partner);
		}
	    Map<String, Object> params=AbstractServiceImpl.getParamMap("partnerId", partnerID);
		List<PaymentDetailDto> paymentDetails=paymentDetailService.findDtos("getPaymentDetailsQuery", params);
		CustomResponseDto response = new CustomResponseDto(true,"Success");
		response.addObject("companyTypes", companyTypes);
		response.addObject("contractorTypes", contractorTypes);
		response.addObject("partners", partners);
		response.addObject("paymentDetails", paymentDetails);
		return response;
	}
	

	
	
	
	@RequestMapping(value = "/editPartner", method=RequestMethod.POST)
	public @ResponseBody BPartnerDto edit(@ModelAttribute("partnerDto") BPartnerDto partner){
		log.debug("Editing partner... ");
		BPartnerDto dto=partnerService.findDto(partner.getbPartnerId());
		partner.setRegistrationType(dto.getRegistrationType());
		Errors errors =  new Errors();
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{   partner.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return partner;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) ||  role.getValue().equals(AppBaseConstant.ROLE_PARTNER_ADMIN))
		{
		partnerValidator.validate(partner, errors);
		if(errors.getErrorCount()>0){
			ResponseDto response=new ResponseDto();
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			partner.setResponse(response);
			return partner;
		}
		}
		
		return partnerService.updatePartner(partner);
	}
	

	
	@RequestMapping(value="/getPartners", method={RequestMethod.GET,RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartners(){
		BPartnerDto partnerDto = contextService.getPartner();
		Long partnerId = partnerDto.getbPartnerId();
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status",AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
		List<BPartnerDto> partners =partnerService.find(" status=:status", param, null);
	/*	List<BPartnerDto> partners =partnerService.getPartnerForApproval(partnerId);*/
		/*List<BPartnerDto> partners=partnerService.find(" isSubmited='Y' AND bPartnerId <>"+partnerId+ " ", map, null);*/
		Map<String, String> companyTypes = referenceListService.getReferenceListMap(CoreReferenceConstants.COMPANY_TYPE);
		Map<String, String> contractorTypes = referenceListService.getReferenceListMap(CoreReferenceConstants.CONTRACTOR_TYPE);
		CustomResponseDto response = new CustomResponseDto("partners",partners);
		response.addObject("companyTypes", companyTypes);
		response.addObject("contractorTypes", contractorTypes);
		return response;
	}
	
	@RequestMapping(value="/getAllParners/{tahdrType}", method={RequestMethod.GET,RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> getAllParners(@PathVariable("tahdrType")String tenderType){
		 List<UserDto> userdto = null;
		 Map<String, Object> params= new HashMap<>();
		if(tenderType.equals("RA")){
		
		params.put("isContractor", "Y");
		params.put("isCustomer", "N");
		userdto=  userService.findDtos("getQueryForAllPartnerRA", params);
		}else{
			params.put("isContractor", "N");
			params.put("isCustomer", "Y");
		 userdto=  userService.findDtos("getQueryForAllPartnerRA", params);
		}
		return userdto;
		
	}
	
	@RequestMapping(value="/getEligiblePartnersByTahdrId/{tahdrId}", method={RequestMethod.GET,RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> getEligiblePartnersByTahdrId(@PathVariable("tahdrId")Long tahdrId){
		BPartnerDto partnerDto = contextService.getPartner();
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		params.put("partnerId", partnerDto.getbPartnerId());
		List<UserDto> userdto =  userService.findDtos("getQueryForEligiblePartnersByTahdrId", params);
		
		return userdto;
		
	}
	
	/*@RequestMapping(value="/getNotInvitedParners/{tahdrId}", method={RequestMethod.GET,RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> getNotInvitedParners(@PathVariable("tahdrType")String tenderType){
		List<UserDto> userdto = null;
		Map<String, Object> params= new HashMap<>();
		BPartnerDto partner=contextService.getPartner();
		params.put("partnerId", partner.getbPartnerId());
		if(tenderType.equals("RA")){
		params.put("isContractor", "Y");
		params.put("isCustomer", "N");
		userdto=  userService.findDtos("getQueryForAllPartnerRA", params);
		}else{
			params.put("isContractor", "N");
			params.put("isCustomer", "Y");
		 userdto=  userService.findDtos("getQueryForAllPartnerRA", params);
		}
		return userdto;
		
	}*/
	
	@RequestMapping(value="/getFilteredPartners/{email}/{name}/{flg}", method={RequestMethod.GET,RequestMethod.POST},consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UserDto> getFilteredPartners(@PathVariable("email")String email,
			@PathVariable("name")String name,@PathVariable("flg")String flg){
		 List<UserDto> userdto = null;
		 Map<String, Object> params= new HashMap<>();
		if(flg.equals("S")){
			params.put("isContractor", "Y");
			params.put("isCustomer", "N");
		
		userdto=  userService.findFilteredPartner(params,email,name);
		}
		if(flg.equals("B")){
			params.put("isContractor", "N");
			params.put("isCustomer", "Y");
			
		 userdto=  userService.findFilteredPartner(params,email,name);
		}
		if(flg.equals("SB")){
			params.put("isContractor", "Y");
			params.put("isCustomer", "Y");
			
			userdto=  userService.findFilteredPartner(params,email,name);
			}
		return userdto;
		
	}
	
	
	@RequestMapping(value="/submitPartnerDetails",method=RequestMethod.POST)
	public @ResponseBody ResponseDto submitPartnerDetails(@ModelAttribute("partnerDetails") BPartnerDto dto)
	{
		ResponseDto response=new ResponseDto();
		response= partnerService.submitPartnerDetails(dto);
		if(response.isHasError()==false){
			Map<String, Object> params= new HashMap<>();		
			params.put("bPartnerId", dto.getbPartnerId());
			UserDto user=userService.findDto("getQueryForUserByPartnerId", params);
		    BPartnerDto partner=partnerService.findDto(dto.getbPartnerId());
		    String partnerStatus=partner==null?"":partner.getStatus();
			if(user!=null && partnerStatus.equals(AppBaseConstant.PARTNER_STATUS_COMPLETE)){
	    	   String email=user.getEmail()==null?"":user.getEmail();
			     String sub="Registration Approval";
			     String message="<p>Hi</p><br><p>Your Registration has been Approved. <p><p>Regards,</p><p>NovelERP</p>";
			     partnerService.sendEmailToPartner(email, sub, message);
	       }
			else if(user!=null && partnerStatus.equals(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS)){
				 String email=user.getEmail()==null?"":user.getEmail();
				 String sub="Registration Submitted";
			     String message="<p>Hi</p><br><p>Your Profile for EauctionApp has been submitted.<p><p>Regards,</p><p>NovelERP</p>";
			     partnerService.sendEmailToPartner(email, sub, message);
			}
		}
		
		return response;
	}
	
	
	@RequestMapping(value="/partnerRegistrationApproval",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto partnerRegistrationApproval(@ModelAttribute("partnerDetails") BPartnerDto dto)
	{
		BPartnerDto partnerDto=partnerService.findDto(dto.getbPartnerId());
		RoleDto role=contextService.getDefaultRole();
		ResponseDto response=new ResponseDto();
		response= partnerService.partnerRegistrationApproval(partnerDto,dto);
		if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_NOVEL_ADMIN) && (dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS) || dto.getIsApproved().equals(AppBaseConstant.REJECTED_STATUS))){
			if(dto.getbPartnerId()!=null && response.isHasError()==false){
					Map<String, Object> params= new HashMap<>();		
					params.put("bPartnerId", dto.getbPartnerId());
					UserDto user=userService.findDto("getQueryForUserByPartnerId", params);
				  
					if(user!=null){
			    	   String email=user.getEmail()==null?"":user.getEmail();
			    	   String sub="";
			    	   String message="";
			    	    if(dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS))
			    	    {
					      sub="Registration Approval";
					      message="<p>Hi</p><br><p>Your Registration has been Approved. <p><p>Regards,</p><p>NovelERP</p>";
			    	    }else if(dto.getIsApproved().equals(AppBaseConstant.REJECTED_STATUS))
			    	    {
			    	       sub="Registration Rejection";
						   message="<p>Hi</p><br><p>Your Registration has been Rejected. <p><p>Regards,</p><p>NovelERP</p>";
				    	   
			    	    }
					     partnerService.sendEmailToPartner(email, sub, message);
			       }
				}
		}
		else if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_NOVEL_ADMIN) && dto.getIsApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)){
			if(dto.getbPartnerId()!=null && response.isHasError()==false){
				Map<String, Object> params= new HashMap<>();		
				params.put("bPartnerId", dto.getbPartnerId());
				UserDto user=userService.findDto("getQueryForUserByPartnerId", params);
			  
				if(user!=null){
		    	   String email=user.getEmail()==null?"":user.getEmail();
				     String sub="Profile Under Clarification";
				     String message="<p>Hi</p><br><p>Your Registration is under clarification.Kindly Login to clarify your profile. <p><p>Regards,</p><p>NovelERP</p>";
				     partnerService.sendEmailToPartner(email, sub, message);
		       }
			}
		}
		
		return response;
	}
	
	@RequestMapping(value="/getSelectedParticipant/{auctionID}",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody List<MAuctionParticipantMapDto> getSelectedParticipant(@PathVariable("auctionID") Long auctionID)
	{
		Map<String, Object> params= new HashMap<>();
		params.put("auctionid", auctionID);
		 List<MAuctionParticipantMapDto> participantlist= mAuctionParticipantService.findDtos("getAllSelectedParticipant", params);
		
		return participantlist;
		
	}
	@RequestMapping(value="/sendMailToSelectedPartcipant/{auctionID}",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto sendMailToSelectedPartcipant(@PathVariable("auctionID") Long auctionID)
	{
		Map<String, Object> params= new HashMap<>();
		params.put("auctionid", auctionID);
		 List<MAuctionParticipantMapDto> participantlist= mAuctionParticipantService.findDtos("getAllSelectedParticipant", params);
		 
		 for(MAuctionParticipantMapDto bPartner: participantlist){
			 Map<String, Object> param= new HashMap<>();
				param.put("bPartnerId", bPartner.getbPartner().getbPartnerId());
			 UserDto user =userService.findDto("getQueryForInvitedUserToSendMail", param);
			 String email=user.getEmail()==null?"":user.getEmail();
		     String sub="INVITATION FOT AUCTION";
		     String message="Hi You have been invited for auction";
		     partnerService.sendEmailToPartner(email, sub, message);
		 }
		 	
		return null;
		
	}

	@RequestMapping(value = "/updatePartnerProfile/{bpartnerId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BPartnerDto updatePartnerForEditProfile(@PathVariable("bpartnerId") Long bpartnerId) {
		BPartnerDto dto = partnerService.findDto(bpartnerId);
		RoleDto role = contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		  ResponseDto response = new ResponseDto();
		  int updatedValue=partnerService.updatePartnerForEditProfile(dto, role);
		  if (updatedValue > 0) {
			partnerService.sendMailOnEditProfile(dto);
		    response.setHasError(false);
			dto.setResponse(response);
			return dto;
		} else {
			response.setHasError(true);
			dto.setResponse(response);
			return dto;
		}

	}


@RequestMapping(value = "/getRegisteredVendorList/{isApproved}", method=RequestMethod.POST)
public @ResponseBody CustomResponseDto getRegisteredVendorList(@PathVariable("isApproved") String isApproved) {
	
	Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("isApproved", isApproved);
	List<UserRolesDto> userDetailList= userRoleService.findDtos("getUserRegisteredListByRole", params);
	CustomResponseDto response = new CustomResponseDto("DATA", userDetailList);
	return response;
}

@RequestMapping(value = "/getRegisteredCustomerList/{isApproved}", method=RequestMethod.POST)
public @ResponseBody CustomResponseDto getRegisteredCustomerList(@PathVariable("isApproved") String isApproved) {
	
	Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("isApproved", isApproved);
	List<UserRolesDto> userDetailList= userRoleService.findDtos("getCustomerRegisteredListByRole", params);
	CustomResponseDto response = new CustomResponseDto("DATA", userDetailList);
	return response;
}
	@RequestMapping(value = "/getPartnerDetails/{bPartnerId}", method=RequestMethod.POST)
	public @ResponseBody BPartnerDto getPartnerDetails(@PathVariable("bPartnerId") Long bPartnerId) {
		Map<String, Object> param=AbstractServiceImpl.getParamMap("partnerId", bPartnerId);
 		return partnerService.findDto("getQueryForPartnerDetail", param);
 		
	}
	
	@RequestMapping(value = "/completePartnerRegistration", method=RequestMethod.POST)
	public @ResponseBody BPartnerDto getPartnerDetails(@ModelAttribute("partnerDetails") BPartnerDto dto) {
		Errors errors = new Errors();
		ResponseDto response = new ResponseDto();
		partnerValidator.checkCoSignCopy(dto, errors);
		if (errors.getErrorCount() > 0) {
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			response.setMessage("Uploaded file has issue:");
			dto.setResponse(response);
			return dto;
		}
		Map<String, Object> param = AbstractServiceImpl.getParamMap("partnerCoSignCopy.attachmentId",
				dto.getPartnerCoSignCopy().getAttachmentId());
		param.put("isRegCompleted", "Y");
		int result = partnerService.updateByJpql(param, "bPartnerId", dto.getbPartnerId());
		if (result > 0) {
			dto.setResponse(new ResponseDto(false, "Your Registration Process Complteted!"));
		} else {
			dto.setResponse(new ResponseDto(true, "Error In Process"));
		}
		return dto;
	}

	@RequestMapping(value = "/getVendors/{approveStatus}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getApprovedVendors(@PathVariable("approveStatus") String approveStatus) {
		List<BPartnerDto> partners = partnerService.getVendors(approveStatus);
		CustomResponseDto response = new CustomResponseDto("partners", partners);
		return response;
	}

	@RequestMapping(value = "/getVendorsList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getApprovedVendorBySearch(@RequestParam("approveStatus") String approveStatus,
			@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("searchMode") String searchMode,@RequestParam("searchValue") String searchValue) {
		List<BPartnerDto> partners=new ArrayList<>();
		long countResult = 0;
		if(CommonUtil.isStringEmpty(approveStatus)){
			BPartnerDto partner=contextService.getPartner();
			if(partner!=null && partner.getbPartnerId()!=null){
			 partners=partnerService.getPartnerForApproval(partner.getbPartnerId(), pageNumber, pageSize, searchMode, searchValue);
			 countResult=partnerService.getCountForPrtnrAprvl(partner.getbPartnerId(), searchMode, searchValue);
		    }
		}else{
	        	partners = partnerService.getVendors(approveStatus, pageNumber, pageSize, searchMode, searchValue);
		        countResult = partnerService.getRecordCount(approveStatus, searchMode, searchValue);
		}
		int lastPage = (int) ((countResult / pageSize) + 1);
		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("lastPage", lastPage);
		objectMap.put("partners", partners);
		CustomResponseDto response = new CustomResponseDto();
		response.setObjectMap(objectMap);
		return response;
	}

	@RequestMapping(value = { "/approveVendor" }, method = RequestMethod.GET)
	public ModelAndView approveVendor() {
		ModelAndView model = new ModelAndView("approveVendor");
		model.addObject("withHoldingTaxCodes", withHoldingTaxCodeService.findAll());
		model.addObject("vendorAccGroups", vendorAccountGroupService.findAll());
		model.addObject("sgtVendorClasses", gstVendorClassService.findAll());
		model.addObject("reconAccounts", reconAccountService.findAll());
		return model;
	}

	@RequestMapping(value = "/getApprovedVendorsList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getApprovedVendors1() {
		List<BPartnerDto> partners = partnerService.getVendors("Y");
		Map<String, Object> objectMap = new HashMap<>();
		objectMap.put("partners", partners);
		CustomResponseDto response = new CustomResponseDto();
		response.setObjectMap(objectMap);
		return response;
	}

	@RequestMapping(value = "/creatSapVendorCode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto createVendor(@ModelAttribute("userDetails") SapVendorDetailDto sapVendorDto) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		/*try {
			sapVendorDto = sapVendorDetailService.setDtoDetail(sapVendorDto);
			ZVENDORCREATEResponse zvendorcreateResponse = sapServiceClient.createVendor(sapVendorDto);
			if (zvendorcreateResponse != null) {
				ResponseDto responseDto = partnerService.updateVendorSapCode(zvendorcreateResponse, sapVendorDto);
				customResponseDto.addObject("vendorCode", zvendorcreateResponse.getLIFNR());
				customResponseDto.setResponse(responseDto);
				return customResponseDto;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		ResponseDto responseDto = new ResponseDto(true, "Something went Wrong");
		customResponseDto.setResponse(responseDto);*/
		return customResponseDto;
	}

	
	@RequestMapping(value = "/loggedInUserDetails", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody UserDto createVendor() {
		
		return partnerComponent.getLoggedInUser();
	}
	
	@RequestMapping(value = "/updateVendorPartnerStatus/{bPartnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateVendorPartnerStatus(@PathVariable("bPartnerId") Long bPartnerId) {
		HashMap<String, Object> partnerMap=new HashMap<>();
		partnerMap.put("status", AppBaseConstant.PARTNER_STATUS_DRAFTED);
		int result=partnerService.updateByJpql(partnerMap, AbstractContextServiceImpl.getParamMap("bPartnerId", bPartnerId));
		return result>0?new CustomResponseDto(true,"Edit Mode On!"):new CustomResponseDto(false,"Failed to Start Edit Mode");
	}
	
	@PostMapping("/changeRole/{roleId}")
	public @ResponseBody CustomResponseDto changeRole(@PathVariable("roleId") Long roleId){
		RoleDto roledto=roleService.findDto(roleId);
		Set<RoleDto> roleSet =  new HashSet<>();
		roleSet.add(roledto);
		contextService.setRoles(roleSet);
		List<Object> tilesUrl=userService.getTilesUrl(roledto.getValue());
		CustomResponseDto resp = new CustomResponseDto();
		resp.addObject("tileList", tilesUrl);
		resp.setSuccess(true);
		return resp;
		
	}
	
}