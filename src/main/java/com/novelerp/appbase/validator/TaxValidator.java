package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator; 


@Component
public class TaxValidator implements Validator {
	
   @Autowired
   private ValidationUtil validator;
   
   public void validate(Object object, Errors errors) {
	   if(!object.getClass().isAssignableFrom(TaxDto.class)){
		   validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
		   return;
	   }
	   TaxDto tax=(TaxDto) object;
	   
	   validator.rejectIfEmpty(tax.getName(),errors, "invalid.Name", "Name is Required");
		validator.rejectIfEmpty(tax.getCode(), errors, "invalid.Code", "Code is Required");
		if(tax.getTaxcategory()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.taxcategory", "Tax Category is Required");
		}
   }
}
