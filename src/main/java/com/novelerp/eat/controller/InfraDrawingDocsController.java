package com.novelerp.eat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.service.PartnerItemDrawingDocService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

@Controller
public class InfraDrawingDocsController {
	
	@Autowired
	private PartnerItemDrawingDocService partnerItemDrawingDocService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value="/getInfraDrawingDocs", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraDrawingTest(@RequestParam("partnerId") Long partnerId,
			                                                @RequestParam("infraItemId") Long infraItemId){
	   return partnerItemDrawingDocService.getInfraItemDrawingDoc(partnerId, infraItemId);
	}
	
	@RequestMapping(value="/saveinfraDrawingDocs", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody PartnerItemDrawingDocDto saveinfraDrawingDocs(@ModelAttribute("infraDrawingDocs") PartnerItemDrawingDocDto dto){
		RoleDto role=contextService.getDefaultRole();
		PartnerItemDrawingDocDto itemDoc=null;
		if(role!=null && role.getValue()!=null && !AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) && !AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
		 	Map<String,Object> map=AbstractServiceImpl.getParamMap("docId",dto.getPartnerItemDrawingDocId());
			itemDoc=partnerItemDrawingDocService.findDto("getInfraDrawingDocById", map);
		}
		return partnerItemDrawingDocService.saveInfraDrawingDocs(itemDoc,dto);
	}
	
	

}
