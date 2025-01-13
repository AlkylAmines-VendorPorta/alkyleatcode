package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.FinancialDocumentsDto;
import com.novelerp.appbase.master.service.FinancialDocumentsService;

/** 
 * @author Aman
 *
 */
@Controller
public class FinancialDocumentsController {
	@Autowired
	private FinancialDocumentsService financialDocumentsService;

	@RequestMapping(value = "/FinancialDocuments", method = RequestMethod.GET)
	public ModelAndView financialDocumentsServiceView() {
		ModelAndView modelAndView = new ModelAndView("financialDocuments");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getFinancialDocumentsList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<FinancialDocumentsDto> getFinancialDocumentsServiceList() {
		
		List<FinancialDocumentsDto> financialDocumentslist=financialDocumentsService.getFinancialDocumentsList();
		return financialDocumentslist;
	}
	
	@RequestMapping(value = "/getFinancialDocuments/{financialDocumentsId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody FinancialDocumentsDto getBidtype(@PathVariable("financialDocumentsId") Long id) {
		
		FinancialDocumentsDto financialDocuments=financialDocumentsService.getFinancialDocuments(id);
		return financialDocuments;
	}


}




