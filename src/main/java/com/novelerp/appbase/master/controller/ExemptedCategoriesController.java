package com.novelerp.appbase.master.controller;

import java.util.List;
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

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.appbase.master.service.ExemptedCategoriesService;
import com.novelerp.appbase.validator.ExemptedCategoriesValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.core.dto.Errors;

/**
 * 
 * @author Aman Sahu
 *
 */
@Controller
public class ExemptedCategoriesController {

	@Autowired
	private ExemptedCategoriesService exemptedCategoriesService;
	
	@Autowired
	private ExemptedCategoriesValidator exemptedCategoriesValidator;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;

	@RequestMapping(value = "/ExemptedCategories", method = RequestMethod.GET)
	public ModelAndView exemptedCategoriesView() {
		ModelAndView modelAndView = new ModelAndView("exemptedCategories");
		Set<RoleDto> role=contextService.getRoles();
		
		if(role!=null)
		{
			modelAndView.addObject("access", roleAccessMasterService.getAccessByRoleId(role.iterator().next().getRoleId(),29l));
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/getExemptedCategoriesList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<ExemptedCategoriesDto> getExemptedCategoriesList() {
		
		List<ExemptedCategoriesDto> exemptedCategorieslist=exemptedCategoriesService.getExemptedCategoriesList();
		return exemptedCategorieslist;
	}
	
	@RequestMapping(value = "/getExemptedCategories/{exemptedCategoriesId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ExemptedCategoriesDto getExemptedCategories(@PathVariable("exemptedCategoriesId") Long id) {
		
		ExemptedCategoriesDto exemptedCategories=exemptedCategoriesService.getExemptedCategories(id);
		return exemptedCategories;
	}
	@RequestMapping(value="/saveExemptedCategories", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto save(@ModelAttribute("ExemptedCategories")  ExemptedCategoriesDto dto){
		Errors errors =  new Errors();
		exemptedCategoriesValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return exemptedCategoriesService.saveExemptedCategories(dto);
	}
	
	@RequestMapping(value = "/editExemptedCategories", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto edit(@ModelAttribute("ExemptedCategories")  ExemptedCategoriesDto dto) {
		Errors errors =  new Errors();
		exemptedCategoriesValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return exemptedCategoriesService.editExemptedCategories(dto);
	}
	
	@RequestMapping(value = "/deleteExemptedCategories/{exemptedCategoriesId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto delete(@PathVariable("exemptedCategoriesId") Long id) {
		return exemptedCategoriesService.deleteExemptedCategories(id);
	}
}
