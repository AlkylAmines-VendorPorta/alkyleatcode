package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class TileMasterValidator implements Validator {
	
	@Autowired
	private ValidationUtil validator;
	
	public void validate(Object object, Errors errors) {
		/*Validates only UserDto object*/
		if(!object.getClass().isAssignableFrom(TileMasterDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		TileMasterDto dto = (TileMasterDto) object;
        if(dto!=null){
        		validator.rejectIfEmpty(dto.getName(), errors, "invalid.Name", "Value is Required");
				validator.rejectIfEmpty(dto.getCode(), errors, "invalid.Code", "Code is Required");
				validator.rejectIfEmpty(dto.getDescription(), errors, "invalid.Description", "Description is Required");
          
        }
		
	}

}

