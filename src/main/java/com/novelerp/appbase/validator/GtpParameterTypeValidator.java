package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/** 
 * @author Aman
 *
 */
@Component
public class GtpParameterTypeValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	public void validate(Object object, Errors errors) {
		/*Validates only UserDto object*/
		if(!object.getClass().isAssignableFrom(GtpParameterTypeDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		GtpParameterTypeDto gtpParameterType = (GtpParameterTypeDto) object;
		
		validator.rejectIfEmpty(gtpParameterType.getName(), errors, "invalid.Name", "Name is Required");
		validator.rejectIfEmpty(gtpParameterType.getCode(), errors, "invalid.Code", "Code is Required");

	}

}
