package com.novelerp.appbase.master.controller;

import java.util.HashMap;
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

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.service.GtpParameterService;
import com.novelerp.appbase.master.service.GtpParameterTypeService;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.validator.GtpParameterValidator;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman
 *
 */
@Controller
public class GtpParameterController {
	@Autowired
	private GtpParameterService gtpParameterService;
	
	@Autowired
	private GtpParameterTypeService gtpParameterTypeService;
	
	@Autowired
	private GtpParameterValidator gtpParameterValidator;
	
	@Autowired
	private MaterialService  materialService;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/GtpParameter", method = RequestMethod.GET)
	public ModelAndView GtpParameterView() {
		ModelAndView mv=new ModelAndView("gtpParameter");
		mv.addObject("material", materialService.getMaterialList());
		mv.addObject("gtpParameterType", gtpParameterTypeService.getGtpParameterTypeList());
		Set<RoleDto> role=contextService.getRoles();
		
		if(role!=null)
		{
			mv.addObject("access", roleAccessMasterService.getAccessByRoleId(role.iterator().next().getRoleId(),22l));
		}
		return  mv;
	}
	@RequestMapping(value = "/getGtpParameterList/{pageNumber}/{pageSize}/{searchMode}/{serachValue}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody com.novelerp.core.dto.CustomResponseDto getGtpParameterList(@PathVariable("pageNumber")int pageNumber, 
																   @PathVariable("pageSize") int pageSize,
																   @PathVariable("searchMode") String searchMode , 
																   @PathVariable("serachValue") String serachValue) {
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		List<GtpParameterDto> gtpParameterList = gtpParameterService.getGtpParameterList(pageNumber, pageSize, searchMode, serachValue,bPartnerDto.getbPartnerId());
		
		long countResult = gtpParameterService.getGtpParameterCount(searchMode, serachValue,bPartnerDto.getbPartnerId());
		int LastPage = (int) ((countResult / pageSize) + 1);
		com.novelerp.core.dto.CustomResponseDto customResponseDto = new com.novelerp.core.dto.CustomResponseDto();
		customResponseDto.setData(gtpParameterList);
		customResponseDto.addObject("LastPage", LastPage);
		return customResponseDto;
	}
	
	@RequestMapping(value = "/getGtpParameterById/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody GtpParameterDto getGtpParameter(@PathVariable("id") Long id) {
		
		GtpParameterDto gtpParameter=gtpParameterService.getGtpParameter(id);
		return gtpParameter;
	}
	@RequestMapping(value="/saveGtpParameter", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody GtpParameterDto saveGtpParameter(@ModelAttribute("GtpParameter") GtpParameterDto dto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setIsActive("Y");
		GtpParameterDto gtp=new GtpParameterDto();
		/*MaterialDto resdto = materialService.save(materialDto);*/
		if (dto.getGtpParameterId()==null){
			gtp=gtpParameterService.save(dto);
		}
		else{
			gtp=gtpParameterService.updateDto(dto);
		}
		
		return gtp;
		/*Errors errors =  new Errors();
		gtpParameterValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		return gtpParameterService.saveGtpParameter(dto);*/
	}
	
	/*@RequestMapping(value = "/editGtpParameter", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto editGtpParameter(@ModelAttribute("GtpParameter") GtpParameterDto dto) {
		Errors errors =  new Errors();
		gtpParameterValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return gtpParameterService.editGtpParameter(dto);
	}*/
	
	@RequestMapping(value = "/deleteGtpParameter/{gtpParameterId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteGtpParameter(@PathVariable("gtpParameterId") Long id) {
		
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=gtpParameterService.deleteGtpParameter(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"GTP Parameter Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete GTP Parameter..!");
		}
		}
		catch(Exception e){
			response= new ResponseDto(true,"GTP Parameter is Already in Use...!");
		}
		return response;
	/*}return gtpParameterService.deleteGtpParameter(id);*/
	}
	
	@RequestMapping(value = "/getGTPListByMaterialId/{materialId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody com.novelerp.core.dto.CustomResponseDto getGTPListByMaterialId(@PathVariable("materialId") Long materialId) {
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		Map<String, Object> params= new HashMap<>();		
		params.put("materialId", materialId);
		List<GtpParameterDto> gtpList=gtpParameterService.findDtos("getGtpParameterListByMaterial", params);
		response.addObject("gtpList", gtpList);
		return response;
	}

}

