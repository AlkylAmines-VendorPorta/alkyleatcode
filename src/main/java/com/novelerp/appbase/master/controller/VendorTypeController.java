package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.VendorTypeDto;
import com.novelerp.appbase.master.service.VendorTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class VendorTypeController {
	@Autowired
	private VendorTypeService vendorTypeService;

	@RequestMapping(value = "/VendorType", method = RequestMethod.GET)
	public ModelAndView vendorTypeView() {
		ModelAndView modelAndView = new ModelAndView("vendorType");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getVendorTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<VendorTypeDto> getVendorTypeList() {
		
		List<VendorTypeDto> vendorTypelist=vendorTypeService.getVendorTypeList();
		return vendorTypelist;
	}
	
	@RequestMapping(value = "/getVendorType/{vendorTypeId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody VendorTypeDto getVendorType(@PathVariable("vendorTypeId") Long id) {
		
		VendorTypeDto vendorTypetype=vendorTypeService.getVendorType(id);
		return vendorTypetype;
	}

}
