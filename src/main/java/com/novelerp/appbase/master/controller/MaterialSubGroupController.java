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

import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.service.MaterialGroupService;
import com.novelerp.appbase.master.service.MaterialSubGroupService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman
 *
 */
@Controller
public class MaterialSubGroupController {
	@Autowired
	private MaterialSubGroupService materialSubGroupService;
	
	@Autowired
	private MaterialGroupService materialGroupService;
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/MaterialSubGroup", method = RequestMethod.GET)
	public ModelAndView MaterialSubGroupView() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		ModelAndView mv=new ModelAndView("materialSubGroup");
		mv.addObject("materialGroup", materialGroupService.findDtos("getMaterialGroupListByBPartner", params));
		return  mv;
	}
	
	@RequestMapping(value = "/getMaterialSubGroupList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialSubGroupDto> getMaterialSubGroupLIst() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<MaterialSubGroupDto> materialGroupList = materialSubGroupService.findDtos("getMaterialSubGroupListByBPartner", params);
		return materialGroupList;
	}
	
	@RequestMapping(value = "/getMaterialSubGroup/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialSubGroupDto getMaterialSubGroup(@PathVariable("id") Long id) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("materialSubGroupId", id);
		MaterialSubGroupDto materialSubGroupDto =materialSubGroupService.findDto("getMaterialSubGroupById", params);
		return materialSubGroupDto;
	}

	@RequestMapping(value = "/getSubGroupByGroup/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialSubGroupDto> getSubGroupByGroupId(@PathVariable("id") Long groupId) {
		System.out.println("..MaterialSubGroupController-getSubGroupByGroupId()");
		return materialSubGroupService.getSubGroupListByGroupId(groupId);
	}
	/*@RequestMapping(value = "/addNewMaterialSubGroup", method=RequestMethod.POST)
	public @ResponseBody MaterialSubGroupDto  addNewMaterialSubGroup(@ModelAttribute("MaterialSubGroup") MaterialSubGroupDto materialsubGroupDto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		materialsubGroupDto.setPartner(bPartnerDto);
		materialsubGroupDto.setIsActive("Y");
		MaterialSubGroupDto resdto = materialSubGroupService.save(materialsubGroupDto);
		return resdto;
	}*/
	@RequestMapping(value ="/saveMaterialSubGroup" , method = RequestMethod.POST , produces="application/json")
	public @ResponseBody MaterialSubGroupDto saveMaterialSubGroup(@ModelAttribute ("MaterialSubGroup") MaterialSubGroupDto dto){
		MaterialSubGroupDto subGroup=new MaterialSubGroupDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setIsActive("Y");
		if (dto.getMaterialSubGroupId()==null){
			subGroup=materialSubGroupService.save(dto);
		}
		else{
			subGroup=materialSubGroupService.updateDto(dto);
		}
		return subGroup;
	}
	/*@RequestMapping(value = "/upadateMaterialSubgroups", method=RequestMethod.POST)
	public @ResponseBody MaterialSubGroupDto  upadateMaterialSubgroups(@ModelAttribute("MaterialSubGroup") MaterialSubGroupDto materialsubGroupDto){
		MaterialSubGroupDto dto =materialSubGroupService.findDto(materialsubGroupDto.getMaterialSubGroupId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(materialsubGroupDto.getName());
		dto.setCode(materialsubGroupDto.getCode());
		dto.setDescription(materialsubGroupDto.getDescription());
		MaterialSubGroupDto resdto = materialSubGroupService.updateDto(dto);
		
		return resdto;
		
	}*/
	/*@RequestMapping(value = "/deleteNewMaterialSubGroup", method=RequestMethod.POST)
	public @ResponseBody MaterialSubGroupDto  deleteNewMaterialSubGroup(@ModelAttribute("MaterialSubGroup") MaterialSubGroupDto materialsubGroupDto){
		MaterialSubGroupDto dto =materialSubGroupService.findDto(materialsubGroupDto.getMaterialSubGroupId());
		dto.setIsActive("N");
		MaterialSubGroupDto resdto = materialSubGroupService.updateDto(dto);
		
		return resdto;
		
		
	}*/
	@RequestMapping(value = "/deleteMaterialSubGroup/{Id}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteMaterialSubGroup(@PathVariable("Id") Long id) {
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=materialSubGroupService.deleteMaterialSubGroup(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"Material Sub Group Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete Material Sub Group..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"Material Sub Group is Already in Use..!");
		}
		return response;
	}
	
}


