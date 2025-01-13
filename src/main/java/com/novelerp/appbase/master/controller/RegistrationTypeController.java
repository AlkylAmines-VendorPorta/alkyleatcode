package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.RegistrationTypeDto;
import com.novelerp.appbase.master.service.RegistrationTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class RegistrationTypeController {
	@Autowired
	private RegistrationTypeService registrationTypeService;
	
	@RequestMapping(value = "/RegistrationType", method = RequestMethod.GET)
	public ModelAndView RegistrationTypeView() {
		return  new ModelAndView("registrationType");
	}
	
	@RequestMapping(value = "/getRegistrationTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<RegistrationTypeDto>  getRegistrationTypeList() {
		List<RegistrationTypeDto> registrationTypeDtoList = registrationTypeService.getRegistrationTypeList();
		
		return registrationTypeDtoList;
	}
	
	@RequestMapping(value = "/getRegistrationType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody RegistrationTypeDto getRegistrationType(@PathVariable("id") Long id) {
		
		RegistrationTypeDto registrationTypeDto=registrationTypeService.getRegistrationType(id);
		return registrationTypeDto;
	}

}


