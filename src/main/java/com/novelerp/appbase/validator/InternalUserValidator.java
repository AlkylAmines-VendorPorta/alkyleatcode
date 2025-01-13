package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/** 
 * @author Aman
 *
 */
@Component
public class InternalUserValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private UserRolesService userRolesService;

	public void validate(Object object, Errors errors) {
		/*Validates only UserDto object*/
		if(!object.getClass().isAssignableFrom(UserRolesDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		UserRolesDto userRolesDto = (UserRolesDto) object;
		
		validator.rejectIfEmpty(userRolesDto.getUser().getUserDetails().getFirstName(), errors, "invalid.FirstName", "First Name is Required");
		validator.rejectIfEmpty(userRolesDto.getUser().getUserDetails().getLastName(), errors, "invalid.LastName", "Last Name is Required");
		/*validator.rejectIfEmpty(userRolesDto.getUser().getUserDetails().getMiddleName(), errors, "invalid.MiddleName", "Middle Name is Required");*/
		if(userRolesDto.getUser().getUserDetails().getDesignation()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Designation", "Designation is Required");
		}
		if(userRolesDto.getRole()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Role", "Role is not valid");
		}
		if(userRolesDto.getUser().getUserDetails().getLocationType()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.LocationType", "LocationType is Required");
		}
		if(userRolesDto.getUser().getUserDetails().getOfficeLocation()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.Location", "Location is Required");
		}
		
		
	}
	public void validateInternalUser(UserRolesDto userRoles,Errors errors ){
		
		if(userRoles==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if(CommonUtil.isStringEmpty(userRoles.getUser().getUserDetails().getPlant())){
			validationUtil.reject(errors, "EmptyPlantType", "Plant name is Mandatory");
		}
		
		/*if(CommonUtil.isStringEmpty(userRoles.getUser().getUserDetails().getUserDesignation())){
			validationUtil.reject(errors, "EmptyDesignantion", "Designantion is Mandatory");
		}*/
		
		if(CommonUtil.isStringEmpty(userRoles.getUser().getUserDetails().getDepartment())){
			
			validationUtil.reject(errors, "EmptyDepartment", "Department is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(userRoles.getUser().getUserDetails().getEmail())){
			
			validationUtil.reject(errors, "EmptyEmailId", "Email is Mandatory");
		}
		if(CommonUtil.isStringEmpty(userRoles.getUser().getUserName())){
			
			validationUtil.reject(errors, "EmptyEmployeeCode", "Employee Code is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(userRoles.getUser().getUserDetails().getName())){
			
			validationUtil.reject(errors, "EmptyName", "Name Code is Mandatory");
		}
		
	}
}
