package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.service.VendorProfileHistoryService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class VendorProfileHistoryController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VendorProfileHistoryService vendorHistoryService;
	
	@RequestMapping(value = "/getProfileHistory/{bpartnerID}", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody CustomResponseDto getProfileHistory(@PathVariable(name = "bpartnerID") Long partnerId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("partnerId", partnerId);
	
		List<VendorProfileHistoryDto> dto=vendorHistoryService.findDtos("getVendorHistory", param);
		CustomResponseDto customResponseDto = new CustomResponseDto();
		customResponseDto.addObject("history", dto);
		customResponseDto.setSuccess(true);
		return customResponseDto;
		
	}

}
