package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.DocumentTypeDto;
import com.novelerp.appbase.master.service.DocumentTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class DocumentTypeController {
	@Autowired
	private DocumentTypeService documentTypeService;
	
	@RequestMapping(value = "/DocumentType", method = RequestMethod.GET)
	public ModelAndView DocumentTypeView() {
		return  new ModelAndView("documentType");
	}
	
	@RequestMapping(value = "/getDocumentTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<DocumentTypeDto> getDocumentTypeList() {
		return documentTypeService.getDocumentTypeList();
	}
		
	@RequestMapping(value = "/getDocumentType/{Id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody DocumentTypeDto getDocumentType(@PathVariable("Id") Long id) {
		
		DocumentTypeDto documentTypeDto=documentTypeService.getDocumentType(id);
		return documentTypeDto;
	}

}


