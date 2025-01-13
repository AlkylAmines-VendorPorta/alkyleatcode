package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.master.service.MaterialVersionService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman
 *
 */
@Controller
public class MaterialVersionController {
	@Autowired
	private MaterialVersionService materialVersionService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/MaterialVersion", method = RequestMethod.GET)
	public ModelAndView MaterialVersionView() {
		ModelAndView mv= new ModelAndView("materialVersion");
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		mv.addObject("material",materialService.findDtos("getMaterialListByBPartner", params));
		return mv;
	}
	

	/*@RequestMapping(value = "/getMaterialVersionList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialVersionDto> getMaterialVersionList() {
		List<MaterialVersionDto> materialVersionDtoList = materialVersionService.getMaterialVersionList();
		return materialVersionDtoList;
	}*/
	@RequestMapping(value = "/getMaterialVersionList/{materialId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getMaterialVersionList(@PathVariable("materialId") Long materialId) {
		/*BPartnerDto bPartnerDto =contextService.getPartner();*/
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("materialId", materialId);
		List<MaterialVersionDto> materialVersions = materialVersionService.findDtos("getQueryForMaterialVersions", params);
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("materialVersions", materialVersions);
		return response;
	}
	
	@RequestMapping(value = "/getMaterialVersion/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialVersionDto getMaterialVersion(@PathVariable("id") Long id) {
		
		MaterialVersionDto materialVersionDto=materialVersionService.findDto(id);
		return materialVersionDto;
	}
	
	@RequestMapping(value = "/getMaterialVersionByMaterialId/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialVersionDto> getMaterialVersionByMaterialId(@PathVariable("id") Long id) {
		
		List<MaterialVersionDto> materialVersionList=materialVersionService.findDtos("getMaterialVersionByMaterial",AbstractContextServiceImpl.getParamMap("materialId", id));
		return materialVersionList;
	}
	@RequestMapping(value = "/saveMaterialVersion", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody MaterialVersionDto saveMaterialVersion(@ModelAttribute("materialVersion") MaterialVersionDto dto)
	{
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params=new HashMap<>();
		params.put("material", dto.getMaterial().getMaterialId());
		params.put("versionNumber", dto.getCode());
		params.put("BPartnerId", bPartnerDto.getbPartnerId());
		dto.setPartner(bPartnerDto);
	    MaterialVersionDto materialVersion=materialVersionService.findDto("getQueryForMaterialVersionIsExist", params);
		return materialVersionService.saveMaterialVersion(materialVersion, dto);
		
	}
	@RequestMapping(value="/deleteMaterialVersion/{materialVersionId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("materialVersionId") Long materialVersionId){		
		boolean deleted = materialVersionService.deleteById(materialVersionId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true,"Problem in deleting record");
	}
	
}


