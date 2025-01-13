package com.novelerp.alkyl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.AsnReminderService;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

@Controller
@RequestMapping("/rest")
public class AsnReminderController {
	
	@Autowired
	private AsnReminderService asnReminderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private ASNComponent asnComponent;
	
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;  
	
	@PostMapping(value="/getAsnReminders")
	public @ResponseBody CustomResponseDto getAsnReminders(){
		ContextDto context = contextService.getContext();
		UserDto user = context.getUserDto();
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			String role =contextService.getDefaultRole().getValue();
			if(role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)){
				List<AsnReminderDto> invitedVendorList = asnReminderService.findDtos("getAsnReminders", null);//AbstractContextServiceImpl.getParamMap("userId", user.getUserId())
				return new CustomResponseDto("invitedVendorList",invitedVendorList);
			}
		}
	
		return new CustomResponseDto("invitedVendorList",null);
	}
	
	@RequestMapping(value = "/deleteAsnReminder/{reminderId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto deleteAsnReminder(@PathVariable("reminderId") Long id) {
		
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=asnReminderService.deleteAsnReminder(id);
		}
		
		if(isDeleted){
			response= new ResponseDto(false,"ASN Reminder Deleted..!");
		}else{
			response= new ResponseDto(true,"Could Not Delete ASN Reminder..!");
		}
		}
		catch(Exception e)
		{
			response= new ResponseDto(true,"ASN Reminder is Already in use..!");
		}
		return response;
	}
	
	@RequestMapping(value = "/asnReminderGeneratorForPo", method = { RequestMethod.GET,
			RequestMethod.POST },  produces = "application/json")
	public @ResponseBody CustomResponseDto asnReminderGeneratorForVendor(@RequestBody List<Long> listOfPoNo) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("listOfPoNo", listOfPoNo);
		List<AsnReminderDto> reminderList = asnReminderService.findDtos("getQueryForReminderList", params);
		int totalRecords = 0;
		int count = 0;
		for(AsnReminderDto dto : reminderList) {
			try {
				asnComponent.sendASNCreationReminderMail(dto.getPoNo(), dto.getInvoiceNo(), dto.getInvoiceDate(),dto.getVendorEmail(),dto.getCCMail().getPoemail());
				//asnComponent.sendASNCreationReminderMail(dto.getPoNo(), dto.getInvoiceNo(), dto.getInvoiceDate(),dto.getVendorEmail(),dto.getCCMail().getReqby().toString(),dto.getCCMail().getPoemail());
			}catch (Exception e) {
				count++;
			}
		}
		
		if (!CommonUtil.isCollectionEmpty(reminderList)) {
			totalRecords = reminderList.size();
		}

		if (totalRecords > count) {
			customResponseDto.setSuccess(true);
		} else {
			customResponseDto.setSuccess(false);
		}
		customResponseDto.setMessage(count + " Records of " + totalRecords + " failed");

		return customResponseDto;
	}
	
}
