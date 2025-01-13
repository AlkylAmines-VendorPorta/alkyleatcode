package com.novelerp.appbase.master.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.service.SMSService;
/**
 * 
 * @author Varsha
 *
 */
@Controller
public class PartnerSchedularController {
	Logger log=LoggerFactory.getLogger(PartnerSchedularController.class);
	@Autowired
	private BPartnerService partnerService;
	@Autowired
	private UserService userService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private SMSService smsService;
	@Autowired
	private FinancialYearService financialYearService;
	
	@RequestMapping(value= {"/deletePartners"},method =RequestMethod.GET)
	public void deletePartners(){
		try {
			partnerService.deletePartners();
		} catch (Exception e) {
			log.error("Exception" + e);
		}
	}
	@RequestMapping(value= {"/sendReminderForProfileUpdate"},method =RequestMethod.GET)
	public void sendReminderForProfileUpdate(){
		try {
			userService.sendReminderForProfileUpdate();
		} catch (Exception e) {
			log.error("Exception" + e);
		}    
	}
	@RequestMapping(value= {"/partnersRenewal"},method =RequestMethod.GET)
	public void partnersRenewal(){
		try{
		Date date = DateUtil.getDateWithoutTime(new Date());
    	Map<String, Object> param=AbstractServiceImpl.getParamMap("currentDate", date);
		List<BPartnerDto> partners=partnerService.findDtos("getQueryForPartnerRenewal", param);
		if(!CommonUtil.isCollectionEmpty(partners)){
			Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
    		UserDto internalUser=userService.findDto("getUserByRoleCode", codeMap);
				for (BPartnerDto partner : partners) {
					boolean result = partnerService.partnersRenewal(partner);
					if (result) {
						partnerService.sendMailOnVedorExpired(partner, internalUser);
					}
				}
	    }
		
		List<PartnerOrgDto> partnerOrgs=partnerOrgService.findDtos("getQueryForOrgRenewal", param);
		if(!CommonUtil.isCollectionEmpty(partnerOrgs))
		{
			Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", codeMap);
			for(PartnerOrgDto dto:partnerOrgs)
			{
				boolean result=partnerOrgService.updateOrgForRenewal(dto);
				if(result && dto.getCreatedBy()!=null && dto.getCreatedBy().getEmail()!=null)
					{
						partnerOrgService.sendMailOnFactoryExpired(dto,internalUser);		
					}
			}
	    }
		}catch (Exception e) {
			log.error("Exception"+e);
		}
	}
	@RequestMapping(value= {"/partnersRenewalReminder"},method =RequestMethod.GET)
	public void partnerRenewalReminder()
	{
		try{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +7);
		Date date=DateUtil.getDateWithoutTime(cal.getTime());
		Map<String, Object> param=AbstractServiceImpl.getParamMap("date",date);
        List<UserDto> users=userService.findDtos("getUserForPartnerRenewalReminder", param);
        if(!CommonUtil.isCollectionEmpty(users)){
        	 Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			    UserDto internalUser=userService.findDto("getUserByRoleCode", map);
	          	MailDto mailDto=new MailDto();
	    		mailDto.setSubject("Reminder for payment of Vendor Registration Renewal fees");
	    		for(UserDto user:users)
	    		{
	    			  partnerService.sendMailOnRenewalReminder(user, internalUser);
	    			  String validTo=new SimpleDateFormat("dd-MM-yyyy").format(user.getPartner().getValidTo());
	    			  if(user.getUserDetails() != null ){
			            	Map<String, String> smsParams = new HashMap<String, String>();
			            	smsParams.put(AppBaseConstant.SMS_PARAMETER_1, validTo.replaceAll(" ", "%20"));
			            	if(user.getUserDetails().getMobileNo() != null)
			            		smsService.sendSMS(user.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_VENDOR_RENEWAL_DUE, null);
			          }
	    		}
	    }
        List<PartnerOrgDto> orgs=partnerOrgService.findDtos("getQueryForOrgRenewalReminder", param);
        if(!CommonUtil.isCollectionEmpty(orgs))
        {
        	MailDto mailDto=new MailDto();
        	Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", map);
    		mailDto.setSubject("Reminder for payment of Vendor Factory Renewal fees");
    		for(PartnerOrgDto partnerOrg:orgs)
    		{
    			partnerOrgService.sendMailOnFactoryRenewalReminder(partnerOrg, internalUser);
       		}
        }
		}catch(Exception e){
			log.error("Exception"+ e);
		}
    }	
	@RequestMapping(value= {"/updateFinancialYear"},method =RequestMethod.GET)
	public void updateFinancialYears(){
		financialYearService.saveFinancialYear();
	}
}