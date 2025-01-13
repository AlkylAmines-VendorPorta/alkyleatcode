package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.service.IsStdService;
import com.novelerp.appbase.master.service.MaterialService;

/** 
 * @author Aman
 *
 */
@Controller
public class IsStdController {
	@Autowired
	private IsStdService isStdService;
	
	@Autowired
	private MaterialService  materialService;
	
	@RequestMapping(value = "/IsStd", method = RequestMethod.GET)
	public ModelAndView IsStdView() {
		ModelAndView mv=new ModelAndView("isStd");
		mv.addObject("material", materialService.getMaterialList());
		return  mv;
	}
	
	@RequestMapping(value = "/getIsStdlist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<IsStdDto> getIsstandardList() {
		
		List<IsStdDto> isstandardlist=isStdService.getIsStdList();
		return isstandardlist;
	}
	
	@RequestMapping(value = "/getIsStd/{isstandardId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody IsStdDto getIsstandard(@PathVariable("isstandardId") Long id) {
		
		IsStdDto isstandard=isStdService.getIsStd(id);
		return isstandard;
		}

}


