/**
 * @author Ankush
 */
package com.novelerp.eat.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

public class DigitalSignatureValidator implements Validator {


	@Autowired
	private ValidationUtil validatorUtil;
	
	@Override
	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(AttachmentDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
	}

}
