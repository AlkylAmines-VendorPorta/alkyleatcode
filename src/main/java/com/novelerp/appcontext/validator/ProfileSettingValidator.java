package com.novelerp.appcontext.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.novelerp.appcontext.dto.ProfileSettingDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

public class ProfileSettingValidator implements  Validator{

	@Autowired
	private ValidationUtil validatorUtil;

	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(ProfileSettingDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		ProfileSettingDto profile =  (ProfileSettingDto) object;
	
		
	}
	
}
