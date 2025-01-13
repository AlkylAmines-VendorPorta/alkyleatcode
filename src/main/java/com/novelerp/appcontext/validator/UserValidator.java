package com.novelerp.appcontext.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.UserConverter;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/**
 * User Registration validator
 * @author Vivek Birdi
 *
 */
@Component
public class UserValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private PartnerValidator partnerValidator;
	
	public void validate(Object object, Errors errors) {
		/*Validates only UserDto object*/
		if(!object.getClass().isAssignableFrom(UserDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}

		UserDto user = (UserDto) object;
		validator.rejectIfInvalidMail(user.getEmail(), errors, "invalid.email", "Email is not valid");
		/*validator.rejectIfEmpty(user.getPassword(), errors, "invalid.password", "Password is not valid");*/
/*		validator.rejectIfEmpty(user.getPartner().getCrnNumber(), errors, "invalid.crnNumber", "Invalid Company Registration Number");
*/		validator.rejectIfInvalidPANNo(user.getPartner().getPanNumber(), errors, "invalid.pan", "Pan Number is not valid");
		/*validator.rejectIfEmpty(user.getUserDetails().getTelephone1(), errors, "invalid.phone", "Phone Number is not valid");*/
		//validator.rejectIfEmpty(user.getUserDetails().getFax1(), errors, "invalid.fax", "Fax Number is not valid");
		validator.rejectIfInvalidMobileNo(user.getUserDetails().getMobileNo(), errors, "invalid.mobile", "Mobile Number is not valid");
		dbValidations(user,errors);
		emailValidation(user,errors);
		partnerValidator.checkForUniquePanNo(user.getPartner(),errors);
		/*partnerValidator.checkForUniqueNumbers(user.getPartner(),errors);
		partnerValidator.checkForGSTN(user.getPartner(), errors);*/
	}

	private void dbValidations(UserDto user, Errors errors){
		Map<String, Object> params = AbstractServiceImpl.getParamMap("email", user.getEmail());
		
		List<UserDto> users =  userService.findDtos("getQueryForUserByEmail", params,userConverter);
		if(!CommonUtil.isListEmpty(users)){
			validator.reject(errors, "already.registered", "User with this email aleady registered.");
		}
	}
	private void emailValidation(UserDto user, Errors errors)
	{
		String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)" ;
		if(!(user.getEmail().matches(regex)))
		{
			validator.rejectIfEmpty(null, errors, "invalid.email", "Invalid Email Id");
		}
	}
	/*private void checkForUniqueNumbers(UserDto user, Errors errors)
	{
	   Map<String, Object> params=AbstractServiceImpl.getParamMap("gstinNumber", user.getPartner().getGstinNo());
	   params.put("panCardNumber", user.getPartner().getPanNumber());
	   params.put("crnNumber", user.getPartner().getCrnNumber());
	   List<BPartnerDto> partners=partnerServic.findDtos("getPartnerForValidation",params);
	   if(!CommonUtil.isListEmpty(partners))
	   {
		   validator.reject(errors, "already.registered", "User with given CRN,GSTN and Pan Card Number already registered.");
	   }
	}*/
}
