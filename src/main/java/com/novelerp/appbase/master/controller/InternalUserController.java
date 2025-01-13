package com.novelerp.appbase.master.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.service.InternalUserService;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.InternalUserValidator;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.Errors;

/** 
 * @author Aman
 *
 */
@Controller
public class InternalUserController {
	@Autowired
	private UserRolesService  userRolesService;
	
	@Autowired
	private InternalUserValidator internalUserValidator;
	
	@Autowired
	private	  DesignationService designationService;

	@Autowired
	private   LocationTypeService locationTypeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private InternalUserService internalUserService;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUserView", method = RequestMethod.GET)
	public ModelAndView getUserView() {
		ModelAndView modelAndView = new ModelAndView("internalUser");
		Set<RoleDto> role=contextService.getRoles();
		String userRole=role.iterator().next().getValue();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		if(role!=null ){
			if(AppBaseConstant.ROLE_SYS_ADMIN.equals(userRole)){
				modelAndView.addObject("role",roleService.getAllRoles(userRole));
				modelAndView.addObject("locationtype", locationTypeService.getLocationTypeList());
				modelAndView.addObject("designation", designationService.findAll());
				/*modelAndView.addObject("location", locationService.getLocationList());*/
			}else if(AppBaseConstant.ROLE_PARTNER_ADMIN.equals(userRole)){
				modelAndView.addObject("role",roleService.findDtos("getRoleByBPartner", params));
				modelAndView.addObject("locationtype", locationTypeService.getLocationTypeList());
				modelAndView.addObject("designation", designationService.findDtos("getDesignationListByPartner", params));
				/*modelAndView.addObject("location", locationService.getLocationList());*/
			}else if(AppBaseConstant.ROLE_LOCATION_ADMIN.equals(userRole)){
				UserDetailsDto userDetailsDto=userService.getDetailedUserById(contextService.getUser().getUserId());
				modelAndView.addObject("role",roleService.getAllRoles(role.iterator().next().getValue()));
				modelAndView.addObject("designation", designationService.findAll());
				if(userDetailsDto.getLocation()!=null && userDetailsDto.getLocationType()!=null){
					List<LocationDto> locationList=new ArrayList<LocationDto>();
					locationList.add(userDetailsDto.getLocation());
					List<LocationTypeDto> locationTypeList=new ArrayList<LocationTypeDto>();
					locationTypeList.add(userDetailsDto.getLocationType());
					
					modelAndView.addObject("locationtype", locationTypeList);
					/*modelAndView.addObject("location", locationList);*/
				}
			}	
		modelAndView.addObject("access", roleAccessMasterService.getAccessByRoleId(role.iterator().next().getRoleId(),12l));
		}
		return modelAndView;
	}
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public @ResponseBody com.novelerp.core.dto.CustomResponseDto getUserList() {
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		RoleDto role=contextService.getDefaultRole();
		List<UserRolesDto> userRolesList=null;
		if(role!=null ){
			BPartnerDto bpartnerdto =contextService.getPartner();
			if(AppBaseConstant.ROLE_SYS_ADMIN.equals(role.getValue())){
				userRolesList=userRolesService.getUserRoles(null,null,null);
			}else if(AppBaseConstant.ROLE_PARTNER_ADMIN.equals(role.getValue())){
				userRolesList=userRolesService.getUserRoles(null,null,bpartnerdto);
			}else if(AppBaseConstant.ROLE_LOCATION_ADMIN.equals(role.getValue())){
				UserDetailsDto userDetailsDto=userService.getDetailedUserById(contextService.getUser().getUserId());
				if(userDetailsDto.getLocation()!=null && userDetailsDto.getLocationType()!=null){
					userRolesList=userRolesService.getUserRoles(userDetailsDto.getLocationType().getName()
						,userDetailsDto.getOfficeLocation().getName(),bpartnerdto);
				}
			}	
			response.addObject("userRolesList", userRolesList);
		}
		return response;
	}
	
	@RequestMapping(value = "/getUserById/{userid}", method = RequestMethod.POST)
	public @ResponseBody UserRolesDto getUserById(@PathVariable("userid") Long id) {
		UserRolesDto userRolesDto=userRolesService.getUserRolesByUserId(id);
		return userRolesDto;
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto createUser(@ModelAttribute("userRole") UserRolesDto userRolesDto) {
		CustomResponseDto response =new CustomResponseDto();
		UserDto user=contextService.getUser();
		BPartnerDto partner=contextService.getPartner();
		userRolesDto.getUser().setPartner(partner);
		userRolesDto.getUser().setCreatedBy(user);
		userRolesDto.getUser().setUpdatedBy(user);
		Errors errors =  new Errors();
		internalUserValidator.validate(userRolesDto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}else{
			response= internalUserService.createInternalUser(userRolesDto);
		}
		return response;	
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto updateUser(@ModelAttribute("userRole")  UserRolesDto userRolesDto) {
		BPartnerDto partner=contextService.getPartner();
		Errors errors =  new Errors();
		internalUserValidator.validate(userRolesDto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else{
			Long userId=userRolesDto.getUser().getUserId();
			UserDto user=userService.findDto(userId);
			userRolesDto.getUser().setPassword(user.getPassword());
			userRolesDto.getUser().setPartner(partner);
			return internalUserService.updateInternalUser(userRolesDto);
		}
		
	}

	@RequestMapping(value = "/getSearchedUser/{searchliteral}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<UserRolesDto> getSearchedUserList( @PathVariable("searchliteral") String s) 
	{
		List<UserRolesDto> userList=userRolesService.getSearchedUserList(s);
		return userList;
	}
}
