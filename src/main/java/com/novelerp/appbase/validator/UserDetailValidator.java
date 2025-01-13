package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/** 
 * @author Aman
 *
 */
@Component
public class UserDetailValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(UserDetailsDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		UserDetailsDto userDetailDto = (UserDetailsDto) object;
		
		validator.rejectIfEmpty(userDetailDto.getFirstName(), errors, "invalid.FirstName", "First Name is Required");
		validator.rejectIfEmpty(userDetailDto.getLastName(), errors, "invalid.LastName", "Last Name is Required");
		if(userDetailDto.getDesignation()==null ||userDetailDto.getDesignation().getDesignationId()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Designation", "Designation is Required");
		}
		/*if(userDetailDto.getLocation().getAddress1()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Address", "Registered Address is Required");
		}
		if(userDetailDto.getLocation().getCity()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.City", "City is Required");
		}
		if(userDetailDto.getLocation().getRegion()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Region", "Region is Required");
		}
		if(userDetailDto.getLocation().getCountry()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Country", "Country is Required");
		}
		if(userDetailDto.getLocation().getPostal()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Pincode", "Pincode is Required");
		}
		if(userDetailDto.getLocation().getDistrict()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.District", "District is Required");
		}*/
		if(userDetailDto.getDob()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Dob", "DateOfBirth is Required");
		}
		if(userDetailDto.getGender()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Gender", "Gender is Required");
		}
	}
}
