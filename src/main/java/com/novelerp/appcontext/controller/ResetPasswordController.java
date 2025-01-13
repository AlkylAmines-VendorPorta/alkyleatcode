package com.novelerp.appcontext.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.validator.PasswordValidator;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.AuthGenerator;
import com.novelerp.core.util.AuthManager;
import com.novelerp.eat.service.SMSService;
/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class ResetPasswordController {
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private UserService userService;
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	AuthManager authManager;
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value= {"/changePassword"},method =RequestMethod.GET)
	public ModelAndView viewChangePassword(){
		ModelAndView view = new ModelAndView("resetPassword");
		return view;
	}
	@RequestMapping(value= {"/updatePassword"},method =RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto updatePassword(@RequestBody UserDto dto){
		Map<String, Object> map=new HashMap<>();
		int result = 0;
		UserDto userDto = contextService.getUser();
		Errors errors =  new Errors();
		passwordValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			ResponseDto response=new ResponseDto();
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			return response;
		}
		else{
			map.put("password", authManager.getSaltedHash(dto.getPassword(), userDto.getEmail()));
			map.put("isPasswordUpdated","Y");
			result=userService.updateByJpql(map, "userId", userDto.getUserId());
		}
		if(result>0)
		{
			/*MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PASSWORD_RESET_TEMPLATE);*/
			MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_FORGOT_PASSWORD);
			BPartnerDto partner=contextService.getPartner();
			userDto.setPartner(partner);
			userDto.setPassword(dto.getPassword());
			userService.sendMailOnForgotPassword(userDto,mailTemplate);
			/*userService.sendMailForPassword(userDto, mailTemplate);*/
			smsService.sendSMS(userDto.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PASSWORD_CHANGED, null);
    		return new ResponseDto(false, "Your password has been changed successfully...!");
		}
		return new ResponseDto(false, "Error In Password Change");
	}
	
	@RequestMapping(value= {"/forgotPassword"},method =RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto forgotPassword(@RequestBody UserDto dto){
		Map<String, Object> map=new HashMap<>();
		int result = 0;
		if(dto!=null && dto!=null){
			String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
			Map<String, Object> params = AbstractServiceImpl.getParamMap("email", dto.getUserName()); 
			/*UserDto userDto = userService.findDto("getQueryForUserWithPartnerByEmail", params);*/
			UserDto userDto = userService.findDto("getQueryForUserWithPartnerByEmailANDUserID", params);
			if(null!=userDto){
				map.put("password", authManager.getSaltedHash(password, userDto.getEmail()));
				result=userService.updateByJpql(map, "userId", userDto.getUserId());
			}else{
				return new ResponseDto(true, "User does not Exist");
		}		
		if(result>0){
			    userDto.setPassword(password);
			   /* userService.sendMailOnForgotPassword(userDto);*/
				if(userDto.getUserDetails() != null){
					if(userDto.getUserDetails().getMobileNo() != null)
					smsService.sendSMS(userDto.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_NEW_PASSWORD, null);
				}	
				MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_FORGOT_PASSWORD);
				userService.sendMailOnForgotPassword(userDto,mailTemplate);
				/*MailDto mailDto = new MailDto();
				mailDto.setSubject("Forgot Password Request");
				mailDto.setMailContent("<p>Hi "+userDto.getName()+"</p><br><p>Your request to reset the forgot password has been processed successfully.<p>"
						+ "<p>Please use for login is - <p>"+ password);
				mailDto.setSingleRecipient(userDto.getEmail());
				mailService.sendSingleEmailWithResult(mailDto,true);*/
				
			return new ResponseDto(false, "Your password has been Reset, Please check your mail for new Password...!");
		}
		return new ResponseDto(false, "Error In Password Reset");
		}else
			return new ResponseDto(false, "Please submit correct Email Id");
		}
	
	
/*	@RequestMapping(value= {"/genratePassword/{userId}"},method = {RequestMethod.GET,RequestMethod.POST},produces="application/json")
	public @ResponseBody ResponseDto generatePassword(@PathVariable("userId") Long userId ){
		Map<String, Object> map=new HashMap<>();
		UserDto dto=userService.findDto(userId);
		int result = 0;
		if(dto!=null && dto.getEmail()!=null){
			String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
			Map<String, Object> params = AbstractServiceImpl.getParamMap("email", dto.getEmail().toLowerCase().trim()); 
			UserDto userDto = userService.findDto("getQueryForUserWithPartnerByEmail", params);
			if(null!=userDto){
				map.put("password", authManager.getSaltedHash(password, userDto.getEmail()));
				result=userService.updateByJpql(map, "userId", userDto.getUserId());
			}else{
				return new ResponseDto(true, "User does not Exist");
		}		
		if(result>0){
			    userDto.setPassword(password);
			    userService.sendMailOnForgotPassword(userDto);
				if(userDto.getUserDetails() != null){
					if(userDto.getUserDetails().getMobileNo() != null)
					smsService.sendSMS(userDto.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_NEW_PASSWORD, null);
				}	
				MailDto mailDto = new MailDto();
				mailDto.setSubject("Forgot Password Request");
				mailDto.setMailContent("<p>Hi "+userDto.getName()+"</p><br><p>Your request to reset the forgot password has been processed successfully.<p>"
						+ "<p>Please use for login is - <p>"+ password);
				mailDto.setSingleRecipient(userDto.getEmail());
				mailService.sendSingleEmailWithResult(mailDto,true);
				
			return new ResponseDto(false, "Your password has been Reset, Please check your mail for new Password...!");
		}
		return new ResponseDto(false, "Error In Password Reset");
		}else
			return new ResponseDto(false, "Please submit correct Email Id");
		}
	*/
	@RequestMapping(value= {"/genratePassword/{userId}"},method = {RequestMethod.GET,RequestMethod.POST},produces="application/json")
	public @ResponseBody ResponseDto generatePassword(@PathVariable("userId") Long userId ){
		Map<String, Object> map=new HashMap<>();
		UserDto dto=userService.findDto(userId);
		int result = 0;
		if(dto!=null && dto.getEmail()!=null){
			String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
			map.put("password", authManager.getSaltedHash(password, dto.getEmail()));
			result=userService.updateByJpql(map, "userId", dto.getUserId());		
		if(result>0){
			dto.setPassword(password);
			   /* userService.sendMailOnForgotPassword(dto);
				if(userDto.getUserDetails() != null){
					if(userDto.getUserDetails().getMobileNo() != null)
					smsService.sendSMS(userDto.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_NEW_PASSWORD, null);
				}	*/
				/*MailDto mailDto = new MailDto();
				mailDto.setSubject("Forgot Password Request");
				mailDto.setMailContent("<p>Hi "+userDto.getName()+"</p><br><p>Your request to reset the forgot password has been processed successfully.<p>"
						+ "<p>Please use for login is - <p>"+ password);
				mailDto.setSingleRecipient(userDto.getEmail());
				mailService.sendSingleEmailWithResult(mailDto,true);*/
			MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_FORGOT_PASSWORD);
			userService.sendMailOnForgotPassword(dto,mailTemplate);
			return new ResponseDto(false, "Your password has been Reset, Please check your mail for new Password...!");
		}
		return new ResponseDto(false, "Error In Password Reset");
		}else
			return new ResponseDto(false, "Please submit correct Email Id");
		}
	
	}
	