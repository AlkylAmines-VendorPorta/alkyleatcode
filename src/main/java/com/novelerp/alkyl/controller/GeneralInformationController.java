package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class GeneralInformationController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private BPartnerService partnerService;

	@Autowired
	private BPartnerComponent bpComponent;

	@Autowired
	private VendorRegistrationValidator validator;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
//	@RequestMapping(value = { "/getgeneralInformationPaneDetails/{partnerId}" }, method = RequestMethod.POST, produces = "application/json")
//	public @ResponseBody CustomResponseDto getgeneralInformationPaneDetails(@PathVariable("partnerId") Long partnerId) {
//		CustomResponseDto resp = new CustomResponseDto();
//		Map<String, String> companyTypeList = refListService.getReferenceListMap(CoreReferenceConstants.COMPANY_TYPE);
//		Map<String, String> vendorTypeList = refListService.getReferenceListMap(CoreReferenceConstants.VENDOR_TYPE);
//		resp.addObject("companyTypeList", companyTypeList);
//		resp.addObject("vendorTypeList", vendorTypeList);
//		
//		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
//				AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
//		resp.addObject("companyDetails", oldDto);
//		return resp;
//		
//		
//	}
	@RequestMapping(value = { "/generalInformation" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getGeneralinformation() {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> companyTypeList = refListService.getReferenceListMap(CoreReferenceConstants.COMPANY_TYPE);
		Map<String, String> vendorTypeList = refListService.getReferenceListMap(CoreReferenceConstants.VENDOR_TYPE);
		resp.addObject("companyTypeList", companyTypeList);
		resp.addObject("vendorTypeList", vendorTypeList);
		return resp;
	}

	@RequestMapping(value = { "/saveCompanyDetails" }, method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveContactDetails(@RequestBody BPartnerDto dto) {
		CustomResponseDto resp = new CustomResponseDto();
		Errors errors = new Errors();
		validator.validateGeneralInformation(dto, errors);
		if (errors.getErrorCount() > 0) {
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("companyDetails", dto);
			return resp;
		}
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
				AbstractContextServiceImpl.getParamMap("partnerId", dto.getbPartnerId()));
		oldDto = bpComponent.saveCompanyDetails(oldDto, dto);
		resp.addObject("companyDetails", oldDto);
		return resp;
	}

	@RequestMapping(value = { "/getCompanyDetails/{partnerId}" }, method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getContactDetails(@PathVariable("partnerId") Long partnerId) {
		CustomResponseDto resp = new CustomResponseDto();
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
				AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		resp.addObject("companyDetails", oldDto);
		return resp;
	}
	
	

	
	@PostMapping(value = "/getUpdateCredential")
	public @ResponseBody CustomResponseDto getUpdateCredential(@RequestBody VendorCredentialReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		
	//	List<UserDto> UserCredentialList = userService.getVendorListReportbyFilter(dto);
		List<PartnerCompanyAddressDto> UserCredentialList = partnerCompanyAddressService.getVendorListReportbyFilter(dto);
		resp=new CustomResponseDto("UserCredentialList", UserCredentialList);
		return resp;
		
	}
	

	@RequestMapping(value = {"/getUserByUsernameOrEmail/{userNameOremail}/{vendorType}","/getUserByUsernameOrEmail/{vendorType}"}, method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody CustomResponseDto getUserByUsernameOrEmail(@PathVariable(value="userNameOremail",required = false) String name,
			@PathVariable(name = "vendorType") String vendorType) {

		CustomResponseDto responseDto = new CustomResponseDto();
		List<UserDto> userDtos = null;
		List<PartnerCompanyAddressDto> partnerCompanyAddressDtos=null;
		List<Object> obj = null;
		String userNameorEmail = "";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			if (!(CommonUtil.isStringEmpty(name))) {
				params.put("usernameOrEmail", "%" + name.toLowerCase().trim() + "%");
				userNameorEmail = "%" + name.toLowerCase().trim() + "%";
			}else{
				name="";
			}
			// userDtos = userService.findDtos("getUserByEmailOrUserName", params);
			if(vendorType.equals("internaluser")){
				userDtos = userService.getUserByEmailOrUserName(userNameorEmail, vendorType);	
				responseDto.addObject("userList", userDtos);
			}else if(vendorType.equals("RG")){
				partnerCompanyAddressDtos = partnerCompanyAddressService.getRegisteredVendors(name);
				responseDto.addObject("userList", partnerCompanyAddressDtos);
			}else if(vendorType.equals("NRG")){
				partnerCompanyAddressDtos = partnerCompanyAddressService.getNotRegisteredVendors(name);
				responseDto.addObject("userList", partnerCompanyAddressDtos);
			}else if(vendorType.equals("ALL")){				
				partnerCompanyAddressDtos = partnerCompanyAddressService.getAllVendors(name);
				obj=partnerCompanyAddressService.getAllVendorsForUpdateCred(name);
				responseDto.addObject("userList", obj);
			}else if(vendorType.equals("IN")){
				partnerCompanyAddressDtos = partnerCompanyAddressService.getAllInvitedVendors(name);
				responseDto.addObject("userList", partnerCompanyAddressDtos);
			}else if(vendorType.equals("RESEND")){
				obj = partnerCompanyAddressService.getResendInvitationVendors(name);
				responseDto.addObject("userList", obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//responseDto.setData(userDtos);
//		responseDto.addObject("userList", userDtos);
		return responseDto;
	}// Finding User By UserName or Email Id

	@RequestMapping(value = "/updateUserEmail/{email}/{userId}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody CustomResponseDto updateEmail(@PathVariable(name = "email") String email,
			@PathVariable(name = "userId") Long userId) {
		CustomResponseDto responseDto = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		Integer result;
		try {
			UserDto existingUser = userService.findDto("getQueryForUserByEmail",
					AbstractContextServiceImpl.getParamMap("email", email.toLowerCase().trim()));

			if (existingUser == null) {
				params.put("email", email.trim());
				result = userService.updateByJpql(params, "userId", userId);

				if (result > 0) {
					responseDto.setMessage("Email Updated");
					responseDto.setSuccess(true);
				}

			} else {
				responseDto.setMessage("Please update with another E-mail Id");
				responseDto.setSuccess(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDto;
	}// Update User By Email Id

	@RequestMapping(value = "/updateUserName/{username}/{userId}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody CustomResponseDto updateUserName(@PathVariable(name = "username") String name,
			@PathVariable(name = "userId") Long userId) {
		CustomResponseDto responseDto = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		Integer result;
		try {

			params.put("userName", name);
			result = userService.updateByJpql(params, "userId", userId);
			if (result > 0) {
				responseDto.setMessage("UserName Updated");
				responseDto.setSuccess(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDto;
	}// Update User By Email Id

	@RequestMapping(value = "/updateUserNameOrEmail", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody CustomResponseDto updateUserName(@RequestBody UserDto userDto) {
		CustomResponseDto responseDto = new CustomResponseDto();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		Integer result;
		try {
			queryParams.put("email", userDto.getEmail().toLowerCase().trim());
			queryParams.put("username", userDto.getUserName().toLowerCase().trim());
			queryParams.put("userId", userDto.getUserId());
			UserDto existingDto = userService.findDto("updateUserEmailOrUserName", queryParams);
			if (existingDto == null) {
				params.put("userName", userDto.getUserName().toLowerCase().trim());
				params.put("email", userDto.getEmail().toLowerCase().trim());
				result = userService.updateByJpql(params, "userId", userDto.getUserId());
				if (result > 0) {
					responseDto.setMessage("UserName/Email Updated");
					responseDto.setSuccess(true);
				}
				else {
					responseDto.setMessage("UserName/Email already Exists ");
					responseDto.setSuccess(false);
				}
			}
			else {
				responseDto.setMessage("UserName/Email already Exists ");
				responseDto.setSuccess(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseDto.setMessage("UserName/Email already Exists ");
			responseDto.setSuccess(false);
		}
		return responseDto;
	}// Update UserName and Email By Email Id
	
	@PostMapping(value="/searchVendorForEnquiry/{userNameOremail}")
	public @ResponseBody CustomResponseDto searchVendorForEnquiry(@PathVariable("userNameOremail") String name) {

		CustomResponseDto responseDto = new CustomResponseDto();
		List<PartnerCompanyAddressDto> partnerCompanyAddressDtos=null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			if (!(CommonUtil.isStringEmpty(name))) {
				params.put("usernameOrEmail", "%" + name.toLowerCase().trim() + "%");
				name = "%" + name.toLowerCase().trim() + "%";
			}else{
				name="";
			}
				partnerCompanyAddressDtos = partnerCompanyAddressService.getVendorsforEnquiry(name);
				responseDto.addObject("userList", partnerCompanyAddressDtos);
		} catch (Exception e) {
			log.info("ERROR",e);
		}
		return responseDto;
	}
	
}
