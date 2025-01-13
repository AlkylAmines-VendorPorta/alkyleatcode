package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.validator.InfraItemValidator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class PartnerInfraItemController {
	@Autowired
	private PartnerInfraItemService partnerInfraItemService;
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private InfraItemValidator infraItemValidator;
	@RequestMapping(value="/getInfraItems/{partnerId}", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraItems(@PathVariable("partnerId")Long partnerId){
		List<PartnerInfraItemDto> infraItems=partnerInfraItemService.getInfraItems(partnerId);
	    return new CustomResponseDto("infraItems", infraItems);
	}
	@RequestMapping(value="/saveInfraItems", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody PartnerInfraItemDto saveInfraItems(@ModelAttribute("infraItem") PartnerInfraItemDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())||AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			Errors error=new Errors();
			infraItemValidator.checkForDuplicateInfraItem(dto,error);
			if(!CommonUtil.isCollectionEmpty(error.getErrorList())){
				dto.setResponse(new ResponseDto(true, "Check Following Issues: ",error.getErrorList()));
				return dto;
			}
			PartnerInfraItemDto infraItem=null;
			if(null!=dto.getPartnerInfraItemId()){
			   Map<String,Object> param=AbstractServiceImpl.getParamMap("infraItemId",dto.getPartnerInfraItemId());
			   infraItem=partnerInfraItemService.findDto("getQueryForInfraItemById",param);
			}
			return partnerInfraItemService.saveInfraItems(dto,infraItem);
		}
		dto.setResponse(new ResponseDto(true, "Invalid Role Found"));
		return dto;
	}
	@RequestMapping(value="/editInfraItem", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody PartnerInfraItemDto editInfraItem(@PathVariable("infraItemId") Long infraItemId){
		RoleDto role=contextService.getDefaultRole();
		if(infraItemId!=null &&  (AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()))||AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			Map<String,Object> param=AbstractServiceImpl.getParamMap("infraItemId",infraItemId);
			PartnerInfraItemDto infraItem=partnerInfraItemService.findDto("getQueryForInfraItemById",param);
			return null;
		}
		/*dto.setResponse(new ResponseDto(true, "Invalid Role Found"));*/
		return null;
	}
}
