package com.novelerp.appbase.master.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.BidTypeDto;
import com.novelerp.appbase.master.service.BidTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class BidTypeController {
	private static final Logger log=LoggerFactory.getLogger(BidTypeController.class);
	@Autowired
	private BidTypeService bidtypeService;

	@RequestMapping(value = "/BidType", method = RequestMethod.GET)
	public ModelAndView bidtypeView() {
		ModelAndView modelAndView = new ModelAndView("bidType");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getBidTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<BidTypeDto> getBidtypeList(HttpSession session) {
		List<BidTypeDto> bidtypelist=bidtypeService.getBidTypeList();
		return bidtypelist;
	}
	
	@RequestMapping(value = "/getBidType/{bidtypeId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BidTypeDto getBidtype(@PathVariable("bidtypeId") Long id) {
		BidTypeDto bidtype=bidtypeService.getBidType(id);
		return bidtype;
	}

}



