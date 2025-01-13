package com.novelerp.alkyl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.PoLineConditionDto;
import com.novelerp.alkyl.service.PoLineConditionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
@RequestMapping("/rest")
public class POLineConditionController{
	
	@Autowired
	private PoLineConditionService poLineConditionService;
	
	@PostMapping(value="/getPOLineConditions/{poLineId}")
	public @ResponseBody CustomResponseDto getPOLineConditions(@PathVariable("poLineId") Long poLineId){
		List<PoLineConditionDto> poLineConditions = poLineConditionService.findDtos("getPOLineConditionsByLineId", 
				AbstractContextServiceImpl.getParamMap("poLineId", poLineId));
		return new CustomResponseDto("poLineConditions",poLineConditions);
	}
	
} 