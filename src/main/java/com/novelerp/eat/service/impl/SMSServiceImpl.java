package com.novelerp.eat.service.impl;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.SendSMS;
import com.novelerp.eat.service.SMSService;

@Service
public class SMSServiceImpl implements SMSService {

	@Autowired
	private AppPropertyUtil propertyUtil;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	
	private static Logger log = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Override
	public Boolean sendSMS(String mnumber, String templateid, Map<String, String> params) {
		boolean isSMSSent = false;
		boolean isProxy;
		/*String isSetProxy = propertyUtil.getProperty("sms.isSetProxy");*/
		String isSetProxy = sysConfiguratorService.getPropertyConfigurator("sms.isSetProxy");

		if("Y".equalsIgnoreCase(isSetProxy)){
			isProxy = true;
		}else{
			isProxy = false;
		}
		try {
			String requestURL = getSMSBody(mnumber, templateid, params);
			SendSMS sendSMS = new SendSMS(requestURL, isProxy);
			Thread t = new Thread(sendSMS);
			t.start();
			isSMSSent = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return isSMSSent;
	}

	private String getSMSBody(String mnumber, String templateid, Map<String, String> map) throws Exception {

		if (CommonUtil.isStringEmpty(mnumber) || CommonUtil.isStringEmpty(templateid)) {
			throw new InvalidParameterException("invalid parameter mobile number and templateId");
		}
		String parameters = getParameter(map);
		/*String smsurl = propertyUtil.getProperty("sms.request.url");*/
		String smsurl =sysConfiguratorService.getPropertyConfigurator("sms.request.url");

		
		StringBuilder requestUrl = new StringBuilder(smsurl);
		try {
			requestUrl.append("&templateid=" + templateid);
			requestUrl.append("&mnumber=" + mnumber);
			requestUrl.append(parameters);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return requestUrl.toString();
	}

	private String getParameter(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuilder param = new StringBuilder();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Entry) iterator.next();
			param.append("&");
			param.append(pair.getKey() + "=" + pair.getValue());
		}
		return param.toString();
	}

	public static void main(String a[]) throws Exception {
		SMSServiceImpl impl = new SMSServiceImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("F1", "Tender%20EDFDE/DT/21/6");
		impl.sendSMS("9768815800", "Hi", null);
	}

}
