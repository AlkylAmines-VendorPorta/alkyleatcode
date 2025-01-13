package com.novelerp.appbase.master.controller;

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

import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.service.MaterialGroupService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

@Controller
public class MaterialGroupController {

	@Autowired
	private MaterialGroupService materialGroupService;
	
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/materialGroup"}, method = RequestMethod.GET)
	public ModelAndView materialGroupView() {
		return new ModelAndView("materialGroup");
	}
	
	@RequestMapping(value = "/getMaterialGroupList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialGroupDto> getMaterialGroupList() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<MaterialGroupDto> materialGroupList = materialGroupService.findDtos("getMaterialGroupListByBPartner", params);
		
		return materialGroupList;
	}
	
	@RequestMapping(value = "/getMaterialGroup/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialGroupDto getmaterialGroup(@PathVariable("id") Long id) {
		
		MaterialGroupDto materialGroupDto=materialGroupService.findDto(id);
		return materialGroupDto;
	}
	@RequestMapping(value = "/saveMaterialGroup", method=RequestMethod.POST , produces="application/json")
	public @ResponseBody MaterialGroupDto  saveMaterialGroup(@ModelAttribute("MaterialGroup") MaterialGroupDto materialGroupDto){
		MaterialGroupDto materialGroup=new MaterialGroupDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		materialGroupDto.setPartner(bPartnerDto);
		materialGroupDto.setIsActive("Y");
		if(materialGroupDto.getMaterialGroupId()==null){
			materialGroup=materialGroupService.save(materialGroupDto);
		}
		else{
			materialGroup=materialGroupService.updateDto(materialGroupDto);
		}
		
		return materialGroup;
		
		
	}
	
	@RequestMapping(value = "/deleteMaterialGroup/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteMaterialGroup(@PathVariable("Id") Long id){
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=materialGroupService.deleteMaterialGroup(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Material Group Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Material Group..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"Material Group is Already in Use..!");
		}
		
		return response;
	}

}
	/*@RequestMapping(value = "/deleteMaterialGroup", method=RequestMethod.POST)
	public @ResponseBody MaterialGroupDto  deletematerialgroupmaster(@ModelAttribute("MaterialGroup") MaterialGroupDto materialGroupDto){
		MaterialGroupDto dto =materialGroupService.findDto(materialGroupDto.getMaterialGroupId());
		dto.setIsActive("N");
		MaterialGroupDto resdto = materialGroupService.updateDto(dto);
		
		return resdto;
		
		
	}*/

