package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.TenderTypeDto;
import com.novelerp.appbase.master.service.TenderTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class TenderTypeController {
	@Autowired
	private TenderTypeService tenderTypeService;
	
	@RequestMapping(value = "/TenderType", method = RequestMethod.GET)
	public ModelAndView TenderTypeView() {
		return  new ModelAndView("tendertype");
	}
	
	@RequestMapping(value = "/getTenderTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TenderTypeDto> getTenderTypeList() {
		
		List<TenderTypeDto> tenderTypeDtoList=tenderTypeService.getTenderTypeList();
		return tenderTypeDtoList;
	}
	
	@RequestMapping(value = "/getTenderType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TenderTypeDto getTenderType(@PathVariable("id") Long id) {
		
		TenderTypeDto tenderTypeDto=tenderTypeService.getTenderType(id);
		return tenderTypeDto;
	}

}


