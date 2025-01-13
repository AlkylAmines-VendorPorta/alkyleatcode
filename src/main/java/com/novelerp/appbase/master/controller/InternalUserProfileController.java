package com.novelerp.appbase.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.core.dto.CustomResponseDto;

/** 
 * @author Aman
 *
 */
@Controller
public class InternalUserProfileController {
	
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private UserRolesService userRolesService;
	
	@RequestMapping(value= {"/getInternalUserProfile"},method =RequestMethod.GET)
	public ModelAndView getInternalUserProfile(){
		return new ModelAndView("internalUserProfile");
	}
	
	@RequestMapping(value = "/getUserProfileDetail", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getUserProfileDetail() {
		CustomResponseDto response =new CustomResponseDto();
		UserDto user=contextService.getUser();
		if(null!=user){
			Long userId=user.getUserId();
			UserRolesDto userRole=userRolesService.getUserRolesByUserId(userId);
			
			response.addObject("userRole", userRole);
		}
		
		return response;
	}

}
