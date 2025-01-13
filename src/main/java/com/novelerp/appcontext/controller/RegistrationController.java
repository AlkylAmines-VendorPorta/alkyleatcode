package com.novelerp.appcontext.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.RegisterUser;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.validator.UserValidator;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.msedcl.panverification.PANVerification;

/**
 * User registration controller. 
 * @author Vivek Birdi
 *
 */
@Controller
@RequestMapping("/newUser")
public class RegistrationController {
	
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
	
	private final Logger log = LoggerFactory.getLogger(PartnerController.class);

	
	@RequestMapping(value= "/registration", method=RequestMethod.GET)
	public ModelAndView openRegistration(){
		return new ModelAndView("registration"); 
	}
	@RequestMapping(value= "/rest/register", method=RequestMethod.POST)
	public @ResponseBody ResponseDto register(@RequestBody UserDto user, HttpServletRequest request){
		
		boolean isPanValid = false;
		Errors errors =  new Errors();
		HttpSession session1 = request.getSession(true);
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
		UserDto userDto =  registerUser.register(user);
		if(userDto!=null && userDto.getEmail()!=null && userDto.getPassword()!=null){
			MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PASSWORD_AUTO_TEMPLATE);
			userService.sendMailForPassword(userDto,mailTemplate);
			return new ResponseDto(false, "User Registered.");
		}
		return new ResponseDto(true, "Registration unsuccessful.");
	}
}
