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

import com.novelerp.appbase.master.dto.MaterialTypeDto;
import com.novelerp.appbase.master.service.MaterialTypeService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class MaterialTypeController {
	@Autowired
	private MaterialTypeService materialTypeService;
	
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/MaterialType", method = RequestMethod.GET)
	public ModelAndView MaterialTypeView() {
		return  new ModelAndView("materialType");
	}
	
	@RequestMapping(value = "/getMaterialTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialTypeDto> getMaterialTypeList() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<MaterialTypeDto> materialTypeDtoList = materialTypeService.findDtos("getMaterialTypeByBPartner", params);
		return materialTypeDtoList;
	}
	
	@RequestMapping(value = "/getMaterialType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialTypeDto getMaterialType(@PathVariable("id") Long id) {
		MaterialTypeDto materialTypeDto=materialTypeService.findDto(id);
		return materialTypeDto;
	}
	
	@RequestMapping(value="/saveMaterialType", method=RequestMethod.POST , produces="application/json")
	public @ResponseBody MaterialTypeDto saveMaterialType(@ModelAttribute("MaterialType") MaterialTypeDto materialTypeDto){
		BPartnerDto bPartnerDto= contextService.getPartner();
		materialTypeDto.setPartner(bPartnerDto);
		materialTypeDto.setIsActive("Y");
		MaterialTypeDto materialType=new MaterialTypeDto();
		if(materialTypeDto.getMaterialTypeId()==null){
			materialType=materialTypeService.save(materialTypeDto);
		}
		else{
			materialType=materialTypeService.updateDto(materialTypeDto);
		}
		return materialType;
	}
	
	@RequestMapping(value="/deleteMaterialType/{Id}" ,method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto deleteMaterialType(@PathVariable("Id") Long id){
		ResponseDto response=null;
		boolean isDeleted=false;
		if(id>0){
			isDeleted=materialTypeService.deleteMaterialType(id);
		}
		if(isDeleted){
			response=new ResponseDto(false, "Material Type Deleted...!");
		}
		else{
			response=new ResponseDto(true, "Could not deleted Material Type");
		}
		return response;
		
	}
	/*@RequestMapping(value = "/addNewMaterialType", method=RequestMethod.POST)
	public @ResponseBody MaterialTypeDto  addNewMaterialType(@ModelAttribute("MaterialType") MaterialTypeDto materialTypeDto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		materialTypeDto.setPartner(bPartnerDto);
		materialTypeDto.setIsActive("Y");
		MaterialTypeDto resdto = materialTypeService.save(materialTypeDto);
		
		return resdto;
		
		
	}
	@RequestMapping(value = "/upadateMaterialType", method=RequestMethod.POST)
	public @ResponseBody MaterialTypeDto  upadateMaterialType(@ModelAttribute("MaterialType") MaterialTypeDto materialTypeDto){
		MaterialTypeDto dto =materialTypeService.findDto(materialTypeDto.getMaterialTypeId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(materialTypeDto.getName());
		dto.setCode(materialTypeDto.getCode());
		dto.setDescription(materialTypeDto.getDescription());
		MaterialTypeDto resdto = materialTypeService.updateDto(dto);
		
		return resdto;
		
		
	}
	@RequestMapping(value = "/deleteNewMaterialType", method=RequestMethod.POST)
	public @ResponseBody MaterialTypeDto  deleteNewMaterialType(@ModelAttribute("MaterialType") MaterialTypeDto materialTypeDto){
		MaterialTypeDto dto =materialTypeService.findDto(materialTypeDto.getMaterialTypeId());
		dto.setIsActive("N");
		MaterialTypeDto resdto = materialTypeService.updateDto(dto);
		
		return resdto;
		
		
	}*/
}
