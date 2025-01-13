package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/** 
 * @author Aman
 *
 */
@Component
public class UserRolesConverter extends CustomDozerConverter<UserRoles, UserRolesDto> implements ObjectConverter<UserRoles, UserRolesDto>{

	
@Override
public UserRolesDto convertEntityToDto(UserRoles entity, Class<UserRolesDto> dto) {
	if(entity==null){
		return null;
	}
	UserRolesDto userRoles = new UserRolesDto();
	userRoles.setUserRolesId(entity.getUserRolesId());
	if(entity.getUser()!=null)
	{
		UserDto userDto=new UserDto();
		userDto.setUserId(entity.getUser().getUserId());
		userDto.setEmail(entity.getUser().getEmail());
		userDto.setName(entity.getUser().getName());
		userDto.setPassword(entity.getUser().getPassword());
		userDto.setIsActive(entity.getUser().getIsActive());
		userDto.setUserName(entity.getUser().getUserName());
		if(entity.getUser().getUserDetails()!=null)
		{
			UserDetails userDetails=entity.getUser().getUserDetails();
			UserDetailsDto usersDetailsDto=new UserDetailsDto();
			usersDetailsDto.setUserDetailsId(userDetails.getUserDetailsId());
			usersDetailsDto.setFirstName(userDetails.getFirstName());
			usersDetailsDto.setLastName(userDetails.getLastName());
			usersDetailsDto.setMiddleName(userDetails.getMiddleName());
			usersDetailsDto.setName(userDetails.getName());
			usersDetailsDto.setDepartment(userDetails.getDepartment());
			usersDetailsDto.setUserDesignation(userDetails.getUserDesignation());
			usersDetailsDto.setPlant(userDetails.getPlant());
			usersDetailsDto.setMobileNo(userDetails.getMobileNo());
			usersDetailsDto.setApprover(userDetails.getApprover());
			usersDetailsDto.setActiveFrom(userDetails.getActiveFrom());
			usersDetailsDto.setActiveTill(userDetails.getActiveTill());
			usersDetailsDto.setCostCentre(userDetails.getCostCentre());
			usersDetailsDto.setApproverId(userDetails.getApproverId());
			usersDetailsDto.setSapId(userDetails.getSapId());
			if(userDetails.getApproverUser()!=null){
				UserDto approverUser = new UserDto();
				approverUser.setUserId(userDetails.getApproverUser().getUserId());
				approverUser.setUserName(userDetails.getApproverUser().getUserName());
				approverUser.setName(userDetails.getApproverUser().getName());
				usersDetailsDto.setApproverUser(approverUser);
			}
			if(userDetails.getDesignation()!=null)
			{
				DesignationDto designation=new DesignationDto();
				designation.setDesignationId(userDetails.getDesignation().getDesignationId());
				designation.setName(userDetails.getDesignation().getName());
				usersDetailsDto.setDesignation(designation);
			}
			if(userDetails.getLocation()!=null)
			{
				LocationDto location=new LocationDto();
				location.setLocationId(userDetails.getLocation().getLocationId());
				location.setAddress1(userDetails.getLocation().getAddress1());
				usersDetailsDto.setLocation(location);
			}
			if(userDetails.getOfficeLocation()!=null)
			{
				OfficeLocationDto officeLocation=new OfficeLocationDto();
				officeLocation.setOfficeLocationId(userDetails.getOfficeLocation().getOfficeLocationId());
				officeLocation.setName(userDetails.getOfficeLocation().getName());
				usersDetailsDto.setOfficeLocation(officeLocation);
			}
			usersDetailsDto.setLocationTypeRef(userDetails.getLocationTypeRef());
/*			if(userDetails.getLocationType()!=null)
			{
				LocationTypeDto locationType= new LocationTypeDto();
				locationType.setLocationTypeId(userDetails.getLocationType().getLocationTypeId());
				locationType.setName(userDetails.getLocationType().getName());
				usersDetailsDto.setLocationType(locationType);
			}
*/			userDto.setUserDetails(usersDetailsDto);
		}
		userRoles.setUser(userDto);
	}
	if(entity.getRole()!=null)
	{
		RoleDto roleDto=new RoleDto();
		roleDto.setRoleId(entity.getRole().getRoleId());
		roleDto.setName(entity.getRole().getName());
		roleDto.setValue(entity.getRole().getValue());
		userRoles.setRole(roleDto);
	}
	
	
	return userRoles;
}
}
