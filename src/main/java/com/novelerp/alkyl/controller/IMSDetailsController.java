package com.novelerp.alkyl.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dto.IMSDetailsDto;
import com.novelerp.alkyl.service.IMSDetailsService;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;

@Controller
@RequestMapping("/rest")
public class IMSDetailsController {

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private IMSDetailsService imsDetailservice;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private VendorRegistrationValidator validator;
	
	@PostMapping(value="/saveIMSDetails")
	public @ResponseBody CustomResponseDto saveIMSDetails(@RequestBody IMSDetailsDto imsDto){
		CustomResponseDto resp=new CustomResponseDto();
		Errors errors = new Errors();
		validator.validateIMSDetails(imsDto, errors);
		if(errors.getErrorCount()>0){
			imsDto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("imsDetails", imsDto);
			return resp;
		}
		imsDto=imsDetailservice.save(imsDto);
		resp.addObject("imsDetails", imsDto);
		return resp;
	}
	
	@PostMapping(value="/getIMSDetails/{partnerId}")
	public @ResponseBody CustomResponseDto getIMSDetaiils(@PathVariable("partnerId") Long partnerId){
		CustomResponseDto resp=new CustomResponseDto();
		Map<String,String> inspectionStandardList=refListService.getReferenceListMap(AppBaseConstant.METHODS_OF_INSPECTION_STANDARD);
		IMSDetailsDto imsDto=imsDetailservice.findDto("getIMSDetails", AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		resp.addObject("imsDetails", imsDto);
		resp.addObject("inspectionStandardList", inspectionStandardList);
		return resp;
	}
	@PostMapping(value="/submitRegistration/{partnerId}")
	public @ResponseBody CustomResponseDto setVendorStatus(@PathVariable("partnerId") Long partnerId){
		CustomResponseDto resp=new CustomResponseDto();
		Errors errors = new Errors();

	BPartnerDto partnerDto =  partnerService.findDto("getPartialUpdatedVendor",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		if(partnerDto == null) {
			resp.setSuccess(false);
			resp.setMessage("No details needed for submission");
			return resp;
		}
		validator.validateRegistrationSubmit(partnerId, errors);
		if(errors.getErrorCount()>0){
			resp.setSuccess(false);
			resp.setMessage(errors.getErrorString());
			return resp;
		}
		
		
		
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS);
		param.put("isProfileUpdated", "Y");
		int response = partnerService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId));
		/*int response=partnerService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.DOCUMENT_STATUS_IN_PROGRESS)
				, AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId));
		*/if(response>0){
			partnerService.updatePartnerRole(partnerId, AppBaseConstant.PARTICIPANT);
			MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_REGISTRATION_SUCCESSFUL);

			UserDto user=contextService.getUser();
			userService.sendMailOnVendorRegistrationSubmit(user,mailTemplate);
			resp.setSuccess(true);
			resp.setMessage("Details submitted successfully,Please Relogin To See Open PO");
		}else{
			resp.setSuccess(false);
			resp.setMessage("Registration Not Sumitted ");
		}
		return resp;
		
	}
}
