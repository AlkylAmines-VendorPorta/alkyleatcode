package com.novelerp.eat.msedcl.panverification;

import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.AppPropertyUtil;

@Component
public class PANVerification {

	@Autowired
	private AppPropertyUtil propertyUtil;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	
	private Logger log = LoggerFactory.getLogger(PANVerification.class);


	public boolean isPANCardValid(String panNo, HttpServletRequest request) throws Exception {
		if (CommonUtil.isStringEmpty(panNo)) {
			throw new InvalidParameterException();
		}
		StringBuilder url = new StringBuilder();
		/*url.append("");
		url.append(":"+request.getLocalPort());*/
		/*url.append( propertyUtil.getProperty("eat.pan.verification.serviceurl"));*/
		url.append(sysConfiguratorService.getPropertyConfigurator("eat.pan.verification.serviceurl"));
		/*url.append("http://localhost:8081/panverification/verifyPAN?pan_no=BGRPA1392K");*/
		url.append( panNo);
		RestTemplate restTemplate = new RestTemplate();
		String responseBody = null;
		ResponseEntity<String> response;
		try {
			response = restTemplate.getForEntity(url.toString(), String.class);
			responseBody = response.getBody();
			if (!CommonUtil.isStringEmpty(responseBody)) {
				return validate(responseBody);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		System.out.println("response- " + responseBody);
		return false;
	}

	public boolean validate(String panDeatils) throws Exception {

		if (CommonUtil.isStringEmpty(panDeatils)) {
			throw new InvalidParameterException();
		}
		String data[] = panDeatils.split("\\^");
		if (data.length > 0) {
			if ("1".equalsIgnoreCase(data[0]) && "E".equalsIgnoreCase(data[2])) {
				return true;
			}
		}
		return false;
	}

}
