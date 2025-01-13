package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/** 
 * @author Aman
 *
 */
@Component
public class GtpParameterValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	public void validate(Object object, Errors errors) {
		/*Validates only UserDto object*/
		if(!object.getClass().isAssignableFrom(GtpParameterDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		GtpParameterDto gtpParameter = (GtpParameterDto) object;
		
		validator.rejectIfEmpty(gtpParameter.getName(), errors, "invalid.Name", "Name is Required");
		validator.rejectIfEmpty(gtpParameter.getCode(), errors, "invalid.Code", "Code is Required");
		if(gtpParameter.getGtpParameterType()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.GtpParameterType", "GtpParameter Type is Required");
		}

	}
}
