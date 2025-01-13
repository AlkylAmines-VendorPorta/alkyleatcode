package com.novelerp.eat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.service.PartnerItemTypeTestDetailsService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

@Controller
public class InfraTypeTestController {
	
	@Autowired
	private PartnerItemTypeTestDetailsService partnerItemTypeTestDetailsService;
	
	@RequestMapping(value="/getInfraTypeTest", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraTypeTest(@RequestParam("partnerId") Long partnerId, @RequestParam("infraItemId") Long infraItemId){
		return partnerItemTypeTestDetailsService.getItemTypeTest(partnerId, infraItemId);
	}
	
	@RequestMapping(value="/saveTypeTest", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody PartnerItemTypeTestDetailsDto saveTypeTest(@ModelAttribute("infraTypeTest") PartnerItemTypeTestDetailsDto dto){
		PartnerItemTypeTestDetailsDto typeTest=new PartnerItemTypeTestDetailsDto();
		if(null!=dto.getPartnerItemTypeTestId()){
		  Map<String,Object> param=AbstractServiceImpl.getParamMap("typeTestId",dto.getPartnerItemTypeTestId());
		  typeTest=partnerItemTypeTestDetailsService.findDto("getInfraTypeTestById", param);
		}
		return partnerItemTypeTestDetailsService.saveTypeTest(dto, typeTest);
	}

}
