package com.novelerp.eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class InviteAndRequestController {

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@RequestMapping(value= {"/InviteAndRequest"},method =RequestMethod.GET)
	public ModelAndView inviteAndRequest(){
		ModelAndView view= new ModelAndView("InviteAndRequest");
		BPartnerDto bPartnerDto =contextService.getPartner();
		String access=sysConfiguratorService.getPartnerPropertyConfigurator("invite.request.user", bPartnerDto.getbPartnerId());
		view.addObject("access_type",access);
		return view;
	}
	
	@RequestMapping(value = "/getTenderByTypeCode/{typeCode}", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTenderByTypeCode(@PathVariable("typeCode") String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		BPartnerDto bPartnerDto =contextService.getPartner();
		Long partnerId=bPartnerDto.getbPartnerId();
		String access=sysConfiguratorService.getPartnerPropertyConfigurator("invite.request.user", bPartnerDto.getbPartnerId());
		/*if(access.equals("")){
			sysConfiguratorService.addPartnerAllRequiredSysConfig();
		}*/
		String status=AppBaseConstant.DOCUMENT_STATUS_PUBLISHED;
		List<TAHDRDto> getTenderByTypeCode = tahdrService.getTenderByTypeCode(partnerId, status, typeCode);
		response.addObject("tenderList",getTenderByTypeCode);
		return response;
	}
}
