package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.TenderBudgetTypeDto;
import com.novelerp.appbase.master.service.TenderBudgetTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class TenderBudgetTypeController {
	@Autowired
	private TenderBudgetTypeService tenderBudgetTypeService;
	
	@RequestMapping(value = "/TenderBudgetType", method = RequestMethod.GET)
	public ModelAndView TenderBudgetTypeView() {
		return  new ModelAndView("tenderBudgetType");
	}
	
	@RequestMapping(value = "/getTenderBudgetTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<TenderBudgetTypeDto> getTenderBudgetTypeList() {
		
		List<TenderBudgetTypeDto> tenderBudgetTypeDtolist = tenderBudgetTypeService.getTenderBudgetTypeList();
		
		return tenderBudgetTypeDtolist;
	}
	
	@RequestMapping(value = "/getTenderBudgetType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody TenderBudgetTypeDto getTenderBudgetType(@PathVariable("id") Long id) {
		
		TenderBudgetTypeDto tenderBudgetTypeDto=tenderBudgetTypeService.getTenderBudgetType(id);
		return tenderBudgetTypeDto;
	}
}

