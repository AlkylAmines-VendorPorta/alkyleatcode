package com.novelerp.appcontext.controller;

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
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.validator.SystemConfiguratorValidator;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;

@Controller
public class SystemConfiguratorController {
	
	@Autowired
	private SystemConfiguratorService systemConfiguratorService;
	
	@Autowired
	private SystemConfiguratorValidator systemConfiguratorValidator;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;

	@RequestMapping(value= {"/sysConfigurator"},method =RequestMethod.GET)
	public @ResponseBody ModelAndView openProfilePage() {
		ModelAndView model = new ModelAndView("sysConfigurator");
		RoleDto role=contextService.getDefaultRole();
		if(role!=null){
			model.addObject("access", roleAccessMasterService.getAccessByRoleId(role.getRoleId(),107l));
		}
		return model;
	}
	
	@RequestMapping(value = "/getSysConfiguratorList/{pageNumber}/{pageSize}/{searchMode}/{serachValue}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto sysConfigurator(@PathVariable("pageNumber")int pageNumber, 
																	 @PathVariable("pageSize") int pageSize,
																	 @PathVariable("searchMode") String searchMode , 
																	 @PathVariable("serachValue") String searchValue) {
		Map<String, Object> params=new HashMap<>();
		BPartnerDto partner=contextService.getPartner();
        List<SystemConfiguratorDto> systemConfiguratorList = systemConfiguratorService.getPartnerSystemConfigurator("getSysConfigurator",params,pageNumber, pageSize, searchMode, searchValue,partner.getbPartnerId());
        long countResult = systemConfiguratorService.getconfiguratorCount(params, searchMode, searchValue);
		int LastPage = (int) ((countResult / pageSize) + 1);
		CustomResponseDto customResponseDto = new CustomResponseDto();
		customResponseDto.setData(systemConfiguratorList);
		customResponseDto.addObject("LastPage", LastPage);
		return customResponseDto;
	}
	
	@RequestMapping(value = "/getSysConfigurator/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody SystemConfiguratorDto getSystemConfigurator(@PathVariable("id") Long id) {
		SystemConfiguratorDto dto=systemConfiguratorService.findDto(id);
		return dto;
	}
	
	@RequestMapping(value="/saveSysConfigurator", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto saveSysConfigurator(@ModelAttribute("SysConfigurator") SystemConfiguratorDto dto){
		CustomResponseDto response =new CustomResponseDto();
		Errors errors =  new Errors();
		systemConfiguratorValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			response.addObject("hasError", errors);
			response.addObject("result", false);
			response.addObject("message", "Configurator not saved or updated");
		}
		else{
			if(dto.getSystemConfiguratorId()==null){
				dto =systemConfiguratorService.save(dto);
				response.addObject("message", "Configurator add successfully");
			}else{
				dto =systemConfiguratorService.updateDto(dto);
				response.addObject("message", "Configurator Updated successfully");
			}
			response.addObject("systemConfigurator", dto);
			response.addObject("result", true);
			
		}
		return response;
	}
	
	@RequestMapping(value = "/deleteSysConfiguratorId/{sysConfiguratorId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto delete(@PathVariable("sysConfiguratorId") Long sysConfiguratorId) {
		CustomResponseDto response=new CustomResponseDto();
			boolean deleted = systemConfiguratorService.deleteById(sysConfiguratorId);
			if(deleted){
				response.addObject("result", true);
				response.addObject("message", "Configurator Deleted Successfully ");
			}else{
				response.addObject("result", false);
				response.addObject("message", "Not deleted ");
			}
			return	response;
			
	}
	
	@RequestMapping(value = "/addPartnerSystemConfifurator", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto addPartnerSystemConfifurator() {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto partner=contextService.getPartner();
		if(partner!=null){
			Map<String, Object> params=new HashMap<>();
			params.put("isSystemConfig", "N");
			params.put("partnerId", partner.getbPartnerId());
			List<SystemConfiguratorDto> partnerConfig=systemConfiguratorService.findDtos("getPartnerSysConfigurator", 
					params);
			if(CommonUtil.isCollectionEmpty(partnerConfig)){
				try{
					params.put("partnerId",1l);
					List<SystemConfiguratorDto> partnerSpecificConfig=systemConfiguratorService.findDtos("getPartnerSysConfigurator", 
							params);
					List<SystemConfiguratorDto> serverSpecificConfig=systemConfiguratorService.findDtos("getSpecficSysConfigurator", 
							AbstractContextServiceImpl.getParamMap("isSystemConfig", "Y"));
					boolean result=systemConfiguratorService.addPartnerAllRequiredSysConfig(partnerSpecificConfig);
					response.addObject("result", result);
					response.addObject("message", "System Configurator Added Succesfully");
				}catch (Exception e) {
					response.addObject("result", false);
					response.addObject("message", "System Configurator while Adding ");
				}
			}else{
				response.addObject("result", false);
				response.addObject("message", "System Configurator has already been added ");
			}
		}else{
			response.addObject("result", false);
			response.addObject("message", "No Partner Found in Session");
		}
		return	response;
	}
}
