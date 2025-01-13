package com.novelerp.appcontext.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.ListResponseDto;
import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.service.TileMasterService;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.appcontext.service.RoleService;


/** 
 * @author Aman
 *
 */
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
@Controller
public class RoleAccessMasterController {
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private TileMasterService tileMasterService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/roleDetails", method = RequestMethod.GET)
	public ModelAndView RoleAccessMasterView() {
		System.out.println("..RoleAccessMasterController-RoleAccessMasterView()");
		ModelAndView model=new ModelAndView("roleDetails");
		Set<RoleDto> role=contextService.getRoles();
		List<RoleDto> roleList=null;
			if(role!=null )
			{
				if(role.iterator().next().getValue().equals("SYSADM"))
					roleList=roleService.getAllRoles("SYSADM");
				else
					roleList=roleService.getAllRoles(role.iterator().next().getValue());
			}
		model.addObject("roleList", roleList);
		List<TileMasterDto> tileList=tileMasterService.getTileMasterList();
		model.addObject("tileList", tileList);
		
		List<TileMasterDto> subTileList=tileMasterService.getSubTileMasterList();
		model.addObject("subTileList", subTileList);
		/*Map<String, Object> params= new HashMap<>();		
		params.put("isSummary", "Y");
		
		List<TileMasterDto> tileList=tileMasterService.findDtos("QueryForTileList", params);
		model.addObject("tileList", tileList);
		
		Map<String, Object> maps= new HashMap<>();		
		params.put("isSummary", "N");
		
		List<TileMasterDto> subTileList=tileMasterService.findDtos("QueryForSubTileList", maps);
		model.addObject("subTileList", subTileList);*/
		return  model;
	}
	@RequestMapping(value = "/getRoleAccessMasterlist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<RoleAccessMasterDto> getRoleAccessMasterList() {
		
		/*List<RoleAccessMasterDto> roleAccessMasterDtoLIst=roleAccessMasterService.getRoleAccessMasterList();
		return roleAccessMasterDtoLIst;*/
		return null;
	}
	
	@RequestMapping(value = "/getRoleAccessMaster/{roleId}", method= {RequestMethod.GET,RequestMethod.POST}, produces = "application/json")
	public @ResponseBody List<RoleAccessMasterDto> getRoleAccessMaster(@PathVariable("roleId") Long id) {
		List<RoleAccessMasterDto> listRoleAccesDto=roleAccessMasterService.getRoleAccessMasterByRoleId(id);
		return listRoleAccesDto;
	}
	@RequestMapping(value="/saveRoleAccessMasters", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto saveRoleAccessMasters(@ModelAttribute("roleAccessMaster") ListResponseDto custDto){
		List<RoleAccessMasterDto> roleAccessListDto=custDto.getRoleList();
		return roleAccessMasterService.saveRoleAccessMaster(roleAccessListDto);
	}
	
	/*@RequestMapping(value = "/editRoleAccessMasters", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto editRoleAccessMasters(@ModelAttribute("roleAccessMaster") List<RoleAccessMasterDto> dto) {
		return roleAccessMasterService.editRoleAccessMaster(dto);
	}*/
	
	/*@RequestMapping(value = "/deleteRoleAccessMasters/{roleAccessMasterId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto deleteRoleAccessMasters(@PathVariable("roleAccessMasterId") Long id) {
		return roleAccessMasterService.deleteRoleAccessMaster(id);
	}
*/
}


