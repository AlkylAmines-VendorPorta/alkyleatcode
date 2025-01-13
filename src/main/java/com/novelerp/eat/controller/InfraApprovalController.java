package com.novelerp.eat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.service.InfraApprovalLevelService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Aman Sahu
 *
 */

@Controller
public class InfraApprovalController {
	
	@Autowired
	private BPartnerService bPartnerService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private InfraApprovalLevelService infraApprovalLevelService;
	@Autowired
	private UserDetailsService userDetailService;
	
	@RequestMapping(value="/infraItemApproval",method=RequestMethod.GET)
	public @ResponseBody ModelAndView infraItemApproval(HttpServletRequest request){
		RoleDto role=contextService.getDefaultRole();
		ModelAndView view=new ModelAndView("vendorInfraDetails");
		if(AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			view.addObject("pageInfo","vendorDetails");
		}else{
			view.addObject("pageInfo","vendorApproval");
		}
		view.addObject("role",role.getValue());
		return view;
	}
	
	@RequestMapping(value="/getInfraVendor", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraVendor(){
		BPartnerDto partner=contextService.getPartner();
		Map<String, Object> param=AbstractContextServiceImpl.getParamMap("partnerId",partner.getbPartnerId());
		List<BPartnerDto> partners=bPartnerService.findDtos("getInfraVendor",param);
		contextService.setSFTPRequiredInfo(partner, AppBaseConstant.INFRA_ATTACHMENT,null);
		return new CustomResponseDto("partners", partners);
	}
	/*@RequestMapping(value="/getInfraVendors", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto loadVendor(){
		return infraApprovalService.loadVendor();	
	}*/
	@RequestMapping(value="/getInfraVendors", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraVendors(){
		BPartnerDto dto=contextService.getPartner();
		RoleDto role=contextService.getDefaultRole();
		UserDetailsDto userDetails=contextService.getUserDetails();
		CustomResponseDto response=new CustomResponseDto();
		if(role!=null && role.getRoleId()!=null && userDetails!=null && userDetails.getUserDetailsId()!=null){
		   Map<String,Object> map=AbstractServiceImpl.getParamMap("userDetailsId",userDetails.getUserDetailsId());
		   UserDetailsDto userDetailsDto=userDetailService.findDto("getUserDetailsWithDesignation",map);
		   Map<String,Object> param=AbstractServiceImpl.getParamMap("roleId",role.getRoleId());
		   param.put("designationId",userDetailsDto.getDesignation().getDesignationId());
		   List<InfraApprovalLevelDto> levels=infraApprovalLevelService.findDtos("getQueryForRoleValidate", param);
		   if(!CommonUtil.isCollectionEmpty(levels)){
			   List<BPartnerDto> partners=null;
		      /* List<BPartnerDto> partners=bPartnerService.getInfraApprovalVendors(dto);*/
		       response.addObject("partners", partners);
		       response.addObject("hasError",false);
		  }else{
			   response.addObject("hasError",true);
			   response.addObject("message","Unauthorized User !");
		   } 
		  return response;
		}
		response.addObject("hasError",true);
		response.addObject("message","Please Relogin");
		contextService.setSFTPRequiredInfo(dto, AppBaseConstant.INFRA_ATTACHMENT,null);
		return response;
	}
}
