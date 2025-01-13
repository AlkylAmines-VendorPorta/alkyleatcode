package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.service.PublicNoticeService;
import com.novelerp.appbase.validator.PublicNoticeValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.Errors;

/** 
 * @author Aman
 *
 */
@Controller
public class PublicNoticeController {
	@Autowired
	private PublicNoticeService publicNoticeService;
	
	@Autowired
	private PublicNoticeValidator publicNoticeValidator;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/PublicNotice", method = RequestMethod.GET)
	public ModelAndView PublicNoticeView() {
		ModelAndView mv=new ModelAndView("publicNoticesMaster");
		Set<RoleDto> role=contextService.getRoles();
		if(role!=null)
		{
			mv.addObject("access", roleAccessMasterService.getAccessByRoleId(role.iterator().next().getRoleId(),43l));
		}
		return  mv;
	}
	@RequestMapping(value = "/getPublicNoticelist", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<PublicNoticeDto> getPublicnoticeList() {
		
		List<PublicNoticeDto> publicnoticelist=publicNoticeService.findDtos("getPublicNoticesList", null);
		return publicnoticelist;
	}
	
	@RequestMapping(value = "/getHomePublicNoticeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<PublicNoticeDto> getHomePublicNoticeList() {
		
		List<PublicNoticeDto> publicnoticelist=publicNoticeService.findDtos("getHomePublicNoticeLists", new HashMap<>());
		return publicnoticelist;
	}
	
	@RequestMapping(value = "/getPublicNotice/{publicnoticeId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody PublicNoticeDto getPublicnotice(@PathVariable("publicnoticeId") Long publicNoticeId) {
		return publicNoticeService.findDto("getPublicNoticebyId", AbstractContextServiceImpl.getParamMap("publicNoticeId", publicNoticeId));
	}
	
	@RequestMapping(value="/savePublicNotices", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto savePublicNotices(@ModelAttribute("PublicNotice") PublicNoticeDto dto){
		Errors errors =  new Errors();
		publicNoticeValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return publicNoticeService.savePublicNotice(dto);
	}
	
	@RequestMapping(value = "/editPublicNotices", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto editPublicNotices(@ModelAttribute("PublicNotice") PublicNoticeDto dto) {
		Errors errors =  new Errors();
		publicNoticeValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return publicNoticeService.editPublicNotice(dto);
	}
	
	@RequestMapping(value = "/deletePublicNotices/{publicNoticesId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto deletePublicNotices(@PathVariable("publicNoticesId") Long id) {
		return publicNoticeService.deletePublicNotice(id);
	}

}

