package com.novelerp.eat.schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.eat.service.ReminderMailService;
import com.novelerp.eat.service.SMSService;

@Controller
@EnableScheduling
@Configuration
public class DailySchedular {

	private Logger Log =  LoggerFactory.getLogger(DailySchedular.class);
	
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
	@Autowired
	private ReminderMailService reminderMailService;

	
	@RequestMapping(value ={"/dailySchedule"}, method = RequestMethod.GET)
	@Scheduled(cron = AppBaseConstant.SCHEDULAR_TIME)
	public void dailySchedulingTask() 
	{/*
		Log.info("..in dailySchedulingTask method..");
		try{
		partnerService.deletePartners();
		userService.sendReminderForProfileUpdate();
		financialYearService.saveFinancialYear();
		reminderMailService.mailReminderForTahdrOpening();	
		reminderMailService.mailReminderForTahdrSubmission();
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("dailySchedulingTask/DailySchedular" + e);
		}
	*/}
}
