package com.novelerp.alkyl.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.commons.util.CommonValidationsUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class UserInvitationValidator implements Validator {
	@Autowired
	private ValidationUtil validationUtil;
	
	@Override
	public void validate(Object object, Errors errors) {

	}
	public void validateUserInvitation(UserDto userInvitationDetails, Errors errors ){
		
		if(userInvitationDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		if(CommonUtil.isStringEmpty(userInvitationDetails.getEmail())){
			validationUtil.reject(errors, "EmptyEmail", "Email is Mandatory");
		}else{
			if(!CommonValidationsUtil.isValidEmail(userInvitationDetails.getEmail())){
				validationUtil.reject(errors, "EmptyEmail", "Enter Valid Email-Id");
			}
		}
		if (userInvitationDetails.getPartner()!=null && CommonUtil.isStringEmpty(userInvitationDetails.getPartner().getName())){
			validationUtil.reject(errors, "EmptyCompanyName", "Fill Company Name");
		}
		
		if(userInvitationDetails.getUserDetails()!= null && CommonUtil.isStringEmpty(userInvitationDetails.getUserDetails().getMobileNo() ) ){
			validationUtil.reject(errors, "EmptyMobileNo", "Enter valid 10 digit Mobile Number");
		}
		
		
		if(CommonUtil.isStringEmpty(userInvitationDetails.getUserDetails().getName())){
			
			validationUtil.reject(errors, "EmptyUserName", "Enter USer Name");
		}
		
	} 
}
