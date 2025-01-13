package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/** 
 * @author Aman
 *
 */
@Component
public class ExemptedCategoriesValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(ExemptedCategoriesDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		ExemptedCategoriesDto exemptedCategoriesDto = (ExemptedCategoriesDto) object;
		
		validator.rejectIfEmpty(exemptedCategoriesDto.getName(), errors, "invalid.Name", "Name is Required");
		validator.rejectIfEmpty(exemptedCategoriesDto.getCode(), errors, "invalid.Code", "Code is Required");
	}

}

