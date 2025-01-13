package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.InvitationReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SAPSalesOrderDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.alkyl.validator.UserInvitationValidator;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RegisterUser;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.validator.UserValidator;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.msedcl.panverification.PANVerification;

@Controller
@RequestMapping("/rest")
public class PartnerInviteController {
	
	@Autowired
	private RegisterUser registerUser;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PANVerification panVerification;
	
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;  
	
	@Autowired
	private AppContext appContext;
	
	@Autowired
	private UserInvitationValidator validator;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto register(@RequestBody UserDto user, HttpServletRequest request){
		CustomResponseDto resp = new CustomResponseDto();
		boolean isPanValid = false;
		Errors errors =  new Errors();
		HttpSession session1 = request.getSession(true);
		
		validator.validateUserInvitation(user, errors);
		if(errors.getErrorCount()>0){
			user.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("user", user);
			return resp;
		}
		
		/*String storedCaptcha = (String) session1.getAttribute("CAPTCHA");
		String userCaptcha = user.getUserDetails().getUserCaptchaCode();
		if(storedCaptcha==null || userCaptcha== null || !storedCaptcha.equals(userCaptcha)){
			errors.addError("invalid.captcha", "Invalid Captcha");
		}
		try {
			isPanValid = panVerification.isPANCardValid(user.getPartner().getPanNumber(), request);
			isPanValid = true;
			if(!isPanValid){
				errors.addError("invalid.pancard", "Invalid Pan Card.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		userValidator.validate(user, errors);
		if(errors.getErrorCount()>0){
			return new ResponseDto(true, "Request has validation errors, look for error List",
					errors.getErrorList());
		}*/
		
		
	   /* boolean registered =  userService.register(user);
		if(registered){
			sendEmailService.sendEmail(user.getEmail());
			return new ResponseDto(false, "User Registered.");
		}*/
		UserDto existingUser=userService.findDto("getQueryForUserByEmail", AbstractContextServiceImpl.getParamMap("email", user.getEmail()));
		if(existingUser==null){
			user =  registerUser.register(user);
			
			if(user!=null && user.getEmail()!=null && user.getPassword()!=null){
//				MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PASSWORD_AUTO_TEMPLATE);
				MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_REGISTRATION_ALKYL);
				MailTemplateDto mailTemplate1=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_MAIL_TO_INVITER);
				userService.sendMailOnVendorInvitation(user,mailTemplate);
				userService.sendInvitationMailToInviter(user,mailTemplate1);
				user.setResponse(new ResponseDto(false, "Invitation Sends Successfully."));
				Map<String, Object> map=new HashMap<>();
				map.put("isPasswordUpdated","Y");
				userService.updateByJpql(map, "userId", user.getUserId());
			}else{
				user =new UserDto();
				user.setResponse(new ResponseDto(true, "Registration unsuccessful."));
			}
		}else{
			user.setResponse(new ResponseDto(true, "User Already Registered."));
		}
		
		
		resp.addObject("user", user);
		return resp;
	}
	
	@PostMapping(value="/getInvitedVendors")
	public @ResponseBody CustomResponseDto getInvitedVendors(){
		ContextDto context = contextService.getContext();
		UserDto user = context.getUserDto();
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			String role =contextService.getDefaultRole().getValue();
//			if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)){
			if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)  || role.equals(AppBaseConstant.ROLE_VENDOR_APPROVAL)){
				List<UserDto> invitedVendorList = userService.findDtos("getInvitedUsers", null);//AbstractContextServiceImpl.getParamMap("userId", user.getUserId())
				return new CustomResponseDto("invitedVendorList",invitedVendorList);
			}
		}
	
		return new CustomResponseDto("invitedVendorList",null);
	}
	
	@RequestMapping(value = { "/getSearchResponse/" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getVendorByMail(@RequestBody UserDto dto ) {
		CustomResponseDto resp = new CustomResponseDto();
		UserDto user=userService.findDto("getQueryForUserByEmail", AbstractContextServiceImpl.getParamMap("email", dto.getEmail()));
		if(user!=null){
			resp.setSuccess(true);
			resp.setMessage("User Already Invited");
		}else{
			resp.setSuccess(false);
			resp.setMessage("");
		}
		return resp;
	}
	
	@RequestMapping(value = { "/getVendorByName/{name}" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getVendorByName(@PathVariable("name") String name) {
		CustomResponseDto resp = new CustomResponseDto();
		List<UserDto> userList=userService.findDtos("getQueryForUserByName", AbstractContextServiceImpl.getParamMap("name", "%"+name+"%"));
		if(userList!=null){
			resp.addObject("userList", userList);
			resp.setSuccess(true);
			resp.setMessage("User Already Invited");
		}else{
			resp.setSuccess(false);
			resp.setMessage("");
		}
		return resp;
	}
	
	@PostMapping(value="/getVendorsForProfile/{name}")
	public @ResponseBody CustomResponseDto getVendorsForProfile(@PathVariable("name") String name){
		ContextDto context = contextService.getContext();
		UserDto user = context.getUserDto();
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			String role =contextService.getDefaultRole().getValue();
//			if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)){
			if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN) || role.equals(AppBaseConstant.ROLE_VENDOR_APPROVAL)){
				Map<String, Object> map=new HashMap<>();
				map.put("name",'%'+name+'%');
				List<UserDto> invitedVendorList = userService.findDtos("getVendorForProfile", map);//AbstractContextServiceImpl.getParamMap("userId", user.getUserId())
				return new CustomResponseDto("invitedVendorList",invitedVendorList);
			}
		}
	
		return new CustomResponseDto("invitedVendorList",null);
	} 
	
	@PostMapping(value="/getvendor/{name}")
	 public @ResponseBody CustomResponseDto getvendor(@PathVariable("name") String name){

	 List<Object> userList = userDao.getVendorListByName(name);
	  return new CustomResponseDto("userList",userList);

}
	
	@PostMapping(value = "/getvendorDataFromSAP/{vendorCode}")
	public @ResponseBody CustomResponseDto getvendorNameSAP(@PathVariable("vendorCode") String vendorCode) {
		
		CustomResponseDto resp = new CustomResponseDto();
		
		// Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				} };

				// Install the all-trusting trust manager
				SSLContext sc = null;
				try {
					sc = SSLContext.getInstance("SSL");
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
				} catch (KeyManagementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				// Create all-trusting host name verifier
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};

				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		//		String url=	"https://172.18.2.36:44300/sap/bc/yweb03_ws_29?sap-client=100&vendor="+vendorCode;
			String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_66?sap-client=100&vendor=" + vendorCode;
			//	String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_66?sap-client=100&vendor=" + vendorCode;
		
		
		System.out.println(url);
		List<String> sapList = new ArrayList<>();
		URLConnection request = null;
		
		//Map<String, Object> jsonArray = new HashMap<String, Object>();
		HashMap<String, Object> jsonArray = new HashMap<String, Object>();
		
		try {
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			
			JSONObject obj = new JSONObject(sb.toString());
			
			
			
			String Message = obj.getString("Msg");
				
				if (Message.equals("Vendor not found.")) {

					resp.setMessage("Vendor not found in SAP,send new invite");
					resp.setSuccess(false);

				}else {
			
					String vendorName = obj.getString("name");
					String emailId=obj.getString("emailid");
					String mobileNo=obj.getString("phone");
					
					
					
					Map<String, String> VendorData = new HashMap<>();
					VendorData.put("vendorName", vendorName);
					
				
				
					
					 Map<String, Object> params = new HashMap<String, Object>();
						params.put("email", emailId);
						params.put("vendorSapCode", vendorCode);
					
				
					List<UserDto> userList=userService.findDtos("getQueryForUserByVendorsapCode", params);
				
					
						VendorData.put("vendorEmail", emailId);
						VendorData.put("vendorMobileNo", mobileNo);
					
				
					if(userList.size()>0) {
						resp.setMessage("User Exist in SAP and Portal");
						VendorData.put("vendorName", userList.get(0).getPartner().getName());
						VendorData.put("userName", userList.get(0).getUserDetails().getName());
						VendorData.put("vendorEmail", userList.get(0).getUserDetails().getEmail());
						VendorData.put("vendorMobileNo", userList.get(0).getUserDetails().getMobileNo());
						resp.setSuccess(true);
					}else {
						resp.setMessage("User Exist in SAP and not in Portal");
						resp.setSuccess(false);
					}
					
					
					
					resp.addObject("VendorData", VendorData);
				}

		 }catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	
		
		return resp;
	}
	
	
	
	@RequestMapping(value= "/sapVendorRegistration/{emailId}/{vendorSapCode}/{companyName}/**", method=RequestMethod.GET)
	
	public @ResponseBody CustomResponseDto sapVendorRegistration(@PathVariable("companyName") String companyName, @PathVariable("emailId")  String emailId,@PathVariable("vendorSapCode")  String vendorSapCode,HttpServletRequest request){
		
		CustomResponseDto resp = new CustomResponseDto();
		
		 final String path =request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
		 final String bestMatchingPattern =request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

		    String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

		//    String moduleName;
		    if (null != arguments && !arguments.isEmpty()) {
		    	companyName = companyName + '/' + arguments;
		    } else {
		    	companyName = companyName;
		    }

	
		UserDto user=new UserDto();
		user.setName(companyName);
		user.setEmail(emailId);
		user.setUserName(vendorSapCode);
		user.setPassword("Pass,123");
		BPartnerDto partnerDto = new BPartnerDto();
		partnerDto.setName(companyName);
		user.setPartner(partnerDto);
		UserDetailsDto userdetailsDto=new UserDetailsDto();
		userdetailsDto.setName(companyName);
        user.setUserDetails(userdetailsDto);
        
        
        Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", emailId);
		params.put("vendorSapCode", vendorSapCode);
		UserDto existingUser=userService.findDto("getQueryForUserByEmailandSapCode", params);
		if(existingUser==null){
			user =  registerUser.register(user);
			//user.setResponse(new ResponseDto(false,"User Not Registered on Portal."));
			resp.setMessage("User Not Registered on Portal.");
			resp.setSuccess(false);
		}else{
			//user.setResponse(new ResponseDto(true, "User Already Registered on Portal."));
			resp.setMessage("User Already Registered on Portal.");
			resp.setSuccess(true);
		}
		
		
		//resp.addObject("user", user);
		return resp;
	}


	
}
