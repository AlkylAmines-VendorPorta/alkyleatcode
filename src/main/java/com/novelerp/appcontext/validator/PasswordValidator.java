package com.novelerp.appcontext.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.AuthManager;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PasswordValidator implements  Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	@Autowired
	private AuthManager authManager;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(UserDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		UserDto userDto=(UserDto) object;
		validatorUtil.rejectIfEmpty(userDto.getPassword(), errors, "invalid.newPassword", "Please Enter New Password");
		validatorUtil.rejectIfEmpty(userDto.getConfirmPassword(), errors, "invalid.confirmPassword", "Please Enter Confirm Password");
		comparePasswords(userDto,errors);
		compareOldPassword(userDto, errors);	
		compareOldPasswordWithNewPassword(userDto, errors);
	}
   private void comparePasswords(UserDto userDto,Errors errors){
	   if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
		   validatorUtil.rejectIfEmpty(null, errors, "invalid","Passwords do not match!");
	   }
   }
   private void compareOldPassword(UserDto userDto,Errors errors){
	   UserDto user = contextService.getUser();
	   Map<String, Object> params= new HashMap<>();		
		params.put("userId", user.getUserId());
		user=userService.findDto("getQueryForUserByUserId", params);
	   if(!authManager.check(userDto.getOldPassword(), user.getPassword())){
		   validatorUtil.rejectIfEmpty(null, errors, "invalid","Old passwords do not match!");
	   }
   }
   
   private void compareOldPasswordWithNewPassword(UserDto userDto,Errors errors){
	   if(userDto.getPassword().equals(userDto.getOldPassword())){
		   validatorUtil.rejectIfEmpty(null, errors, "invalid","New Password cannot be same as Old password!");
	   }
   }
}
