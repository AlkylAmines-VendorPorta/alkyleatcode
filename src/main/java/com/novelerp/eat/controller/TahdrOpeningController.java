/**
 * @author Ankush
 */
package com.novelerp.eat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class TahdrOpeningController {

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired 
	 @Qualifier("jwtUserContext") private ContextService contextService; 
	
	@Autowired
	private ReferenceListService refListService;
	
	@RequestMapping(value="tenderOpening",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView tenderOpening(){
		ModelAndView model= new ModelAndView("tenderOpening");
		return model;
	}
	
	@RequestMapping(value="getTendersForOpening",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto geTendersForOpening(){
		CustomResponseDto response= new CustomResponseDto();
		Map<String,String> openingTypeMap=refListService.getReferenceListMap(AppBaseConstant.TAHDR_OPENING_TYPES);
		
		Map<String, Object> map=new HashMap<>();
		map.put("userId", contextService.getUser().getUserId());
		map.put("now", new Date());
		List<TAHDRDto> tahdrList=tahdrService.findDtos("getQueryForTahdrOpening", map);
		
		response.addObject("openingTypeMap", openingTypeMap);
		response.addObject("tahdrList", tahdrList);
		
		return response;
	}
	
}
