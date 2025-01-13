package com.novelerp.alkyl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.ASNLineComponent;
import com.novelerp.alkyl.component.CommercialASNLineComponent;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SecurityPOItemDto;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
@RequestMapping("/rest")
public class CommercialASNLineController {
	
	@Autowired
	private CommercialASNLineComponent asnLineComponent;
	
	@PostMapping(value="/getSecurityASNLines/{asnHeaderId}")
	public @ResponseBody CustomResponseDto getSecurityASNLines(@PathVariable("asnHeaderId") Long asnHeaderId){
				List<SecurityPOItemDto> asnLineList = asnLineComponent.getASNLineForASNCreation(asnHeaderId);
		
		CustomResponseDto resp = new CustomResponseDto("asnLineList",asnLineList);
		return resp;
	}
	


}
