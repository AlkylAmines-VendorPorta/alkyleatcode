package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.HolderTypeDto;
import com.novelerp.appbase.master.service.HolderTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class HolderTypeController {
	@Autowired
	private HolderTypeService holderTypeService;
	
	@RequestMapping(value = "/HolderType", method = RequestMethod.GET)
	public ModelAndView HolderTypeView() {
		return  new ModelAndView("holderType");
	}
	
	@RequestMapping(value = "/getHolderTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<HolderTypeDto> getHolderTypeList() {
		
		List<HolderTypeDto> holderTypeDtoList =holderTypeService.getHolderTypeList();
		
		return holderTypeDtoList;
	}
	
	@RequestMapping(value = "/getHolderType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody HolderTypeDto getHolderType(@PathVariable("id") Long id) {
		
		HolderTypeDto holderTypeDto=holderTypeService.getHolderType(id);
		return holderTypeDto;
	}
	

}

