package com.novelerp.eat.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LoginService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.ProfileSettingService;
import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private UserSessionService userSessionService;
	/*@Autowired
	private PartnerValidator partnerValidator;*/
	@Autowired
	private AppContext appContext;
	
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
    private MediaService mediaService;
	
	@Autowired
	private ProfileSettingService profileSettingService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public UserDto login(@ModelAttribute ("user") UserDto user, HttpServletRequest request) throws UnsupportedEncodingException{
		
		log.info("Inside LoginController loginUser()");
		HttpSession session = request.getSession(true);
		
		String storedCaptcha = (String) session.getAttribute("CAPTCHA");
		
		if (storedCaptcha==null || !storedCaptcha.equals(request.getParameter("UserCaptchaCode"))){
			log.info("Problem in login");
			ResponseDto responseDto=new ResponseDto(true, "Invalid Captcha");
			UserDto userDto=new UserDto();
			userDto.setResponse(responseDto);
			return userDto;
		}

		UserDto userDto  = loginService.validateLogin(user.getEmail(), user.getPassword());
		if(userDto!=null && userDto.getResponse().isHasError()){
			log.info("Problem in login:"+userDto.getResponse().getMessage());
			/*ResponseDto responseDto=new ResponseDto(true, "Invalid username and password");
			userDto=new UserDto();
			userDto.setResponse(responseDto);*/
			return userDto;
		}
		else if(userDto.getRoles() == null || userDto.getRoles().isEmpty()){
			ResponseDto responseDto=new ResponseDto(true, "User has not assigned any roles.");
			userDto.setResponse(responseDto);
			return userDto;
		}else{
			BPartnerDto partner=userDto.getPartner();
			if(partner!=null){
				if(partner.getIsApproved()!=null){
					if(partner.getIsApproved().equals("B")){
						ResponseDto responseDto=new ResponseDto(true, "You Have been Blacklisted !Kindly check your registered email.");
						userDto.setResponse(responseDto);
						return userDto;	
					}else{
						session.setAttribute("UserSession", userDto);
						ProfileSettingDto profile=profileSettingService.findDto("getProfileSettingByPartnerId", AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
						byte[] file=null;
						if(profile!=null){
							  if(profile.getProfileLogo()!=null){
								  file=  mediaService.getByteArrayByFile(profile.getProfileLogo().getName());
								  if(file!=null && file.length>0){
								  file = Base64.encodeBase64(file);
						           String base64Encoded = new String(file, "UTF-8");
						           session.setAttribute("userLogo", base64Encoded);
								  }
							  }
							session.setAttribute("userThemeColor", profile.getThemeColor());
							session.setAttribute("userUrlPattern",profile.getUrlPattern());
						}
						ResponseDto responseDto=new ResponseDto(false, "Logged In");
						userDto.setResponse(responseDto);
						contextService.contextInitializer(userDto);
						userSessionService.saveUserSession(request, userDto);
					}
				}else{
					session.setAttribute("UserSession", userDto);
					ResponseDto responseDto=new ResponseDto(false, "Logged In");
					userDto.setResponse(responseDto);
					contextService.contextInitializer(userDto);
					userSessionService.saveUserSession(request, userDto);
				}
			}else{
				ResponseDto responseDto=new ResponseDto(true, "Something went wrong !");
				userDto.setResponse(responseDto);
				return userDto; 
			}
		}		
		return userDto;
	}
	
	/*@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){
		return "login";
	}*/
	
	@RequestMapping(value="/rest/view",method = RequestMethod.GET)
	public @ResponseBody String showViewForUserRole(HttpSession session){
		UserDto userDto= contextService.getUser();
		BPartnerDto partner=contextService.getPartner();
		RoleDto role=contextService.getDefaultRole();
		if(userDto == null || userDto.getRoles() == null || partner==null || partner.getbPartnerId()==null || role==null || role.getValue()==null){		
			return "";			
		}
		
		Errors errors=new Errors();
		/*partnerValidator.checkVendorValidity(partner, errors,role);*/
		if (CommonUtil.isCollectionEmpty(errors.getErrorList())) {
			role = userDto.getRoles().iterator().next();
		}else{
			role = appContext.getRole(AppBaseConstant.ROLE_PARTNER_USER);
		}
		if("N".equals(userDto.getIsPasswordUpdated()) ){
			String name="resetpassword";
			role.setViewName(name);
		}
	    return role.getViewName();
	}
}
