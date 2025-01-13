package com.novelerp.appbase.master.controller;

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

import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.master.service.MaterialSpecificationService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

/** 
 * 
 * @author Aman
 *
 */
@Controller
public class MaterialSpecificationController {
	@Autowired
	private MaterialSpecificationService materialSpecificationService;
	
	@Autowired
	private MaterialService materialService;
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/MaterialSpecification", method = RequestMethod.GET)
	public ModelAndView MaterialSpecificationView() {
		ModelAndView mv=new ModelAndView("materialSpecification");
		BPartnerDto bPartnerDto =contextService.getPartner();
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		mv.addObject("material",materialService.findDtos("getMaterialListByBPartner", params));
		return  mv;
	}
	
	@RequestMapping(value = "/getMaterialSpecificationList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<MaterialSpecificationDto>  getMaterialSpecificationList() {

		List<MaterialSpecificationDto> materialSpecificationList = materialSpecificationService.getMaterialSpecificationList();
		return materialSpecificationList;
	}
	
	/*@RequestMapping(value = "/getMaterialSpecificationFromBomId/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody MaterialSpecificationDto getMaterialSpecification(@PathVariable("id") Long id) {
		
		MaterialSpecificationDto materialSpecificationDto=materialSpecificationService.getMaterialSpecification(id);
		return materialSpecificationDto;
	}*/
	
	@RequestMapping(value = "/getMaterialSpecificationFromBomId/{bomVersionId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getBOMListFromMaterial(@PathVariable("bomVersionId") Long bomVersionId) {
		
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bomVersionId", bomVersionId);
 		List<MaterialSpecificationDto> matSpec = materialSpecificationService.findDtos("getSpecificationListByBomversionId", params);
 		CustomResponseDto response = new CustomResponseDto("matSpec", matSpec);
 		return response;
	}
	
	@RequestMapping(value="/saveSpecification", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MaterialSpecificationDto saveSpecification(@ModelAttribute ("materialSpecification") MaterialSpecificationDto dto){
		MaterialSpecificationDto responseDto=new MaterialSpecificationDto();
		if(dto.getMaterialSpecificationId() == null){
			responseDto= materialSpecificationService.save(dto);
		}
		else{
			responseDto= materialSpecificationService.updateDto(dto);
		}
		
		responseDto.setMaterial(dto.getMaterial());
		return responseDto;
	}
	
	@RequestMapping(value="/deleteMaterialSpecification/{materialSpecificationId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("materialSpecificationId") Long materialSpecificationId){
		boolean deleted =  materialSpecificationService.deleteById(materialSpecificationId);
		if(deleted){
			return new ResponseDto(false, "Record deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}

	@RequestMapping(value = "/getMaterialSpecificationFromMaterial/{bomVersionId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getMaterialSpecificationFromMaterial(@PathVariable("bomVersionId") Long bomVersionId) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("bomVersionId", bomVersionId);
 		List<MaterialSpecificationDto> materialSpecification = materialSpecificationService.findDtos("getSpecificationListByBomversionId", params);
 		CustomResponseDto responseDto=new CustomResponseDto();
		responseDto.addObject("materialSpecification", materialSpecification);
		return responseDto;
	}
	
}

