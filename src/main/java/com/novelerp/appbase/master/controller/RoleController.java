package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/role"}, method = RequestMethod.GET)
	public ModelAndView role() {
		System.out.println("..RoleController-role()");
		return new ModelAndView("role");
	}
	@RequestMapping(value = "/getRoleList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<RoleDto> getRoleList() {
		//Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<RoleDto> roleList = roleService.findDtos("getRoleByBPartner", params);
		
		return roleList;
	}
	@RequestMapping(value = "/getRoleById/{roleId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody RoleDto getroleListById(@PathVariable("roleId") Long roleId) {
		
		RoleDto role = roleService.getRoleById(roleId);
		return role;
	}
	
	@RequestMapping(value = "/saveRole", method=RequestMethod.POST)
	public @ResponseBody RoleDto  saveRole(@ModelAttribute("Role") RoleDto roleDto){
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		roleDto.setPartner(bPartnerDto);
		roleDto.setIsActive("Y");
		RoleDto role = new RoleDto();
		if(roleDto.getRoleId()==null){
			role=roleService.save(roleDto);
		}
		else{
			role=roleService.updateDto(roleDto);
		}
		return role;
	}
	@RequestMapping(value = "/deleteRole/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteRole(@PathVariable("Id") Long id){
		ResponseDto response=null;
		boolean isDeleted=false;
		if(id>0){
			isDeleted=roleService.deleteRole(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Role Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Role..!");
		}
		
		return response;
	}
	@RequestMapping(value = "/getroleList/{index}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String,Object> getroleList(@PathVariable("index") Long id) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<RoleDto> roleList = roleService.getRoles(id);
		map.put("DATA", roleList);
		return map;
	}

}
