package com.novelerp.appcontext.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.configuration.AppInfo;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.dto.CustomResponseDto;

@Controller
public class Reload {

	@Autowired
	private AppContext appContext;

	@Autowired
	private AppPropertyUtil propertyUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private MailTemplateService mailTemplateService;

	@Autowired
	private AppInfo appInfo;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/reloadContext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reloadContext() {
		appContext.loadContext();
		return "OK";
	}

	@RequestMapping(value = "/reloadProperties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reloadProperties() {
		propertyUtil.read();
		return "OK";
	}

	@RequestMapping(value = "/loginGenerator", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String loginGenerator() {
		List<UserDto> userList = userService.findDtos("getQueryForUserListForLoginGenerator", null);
		MailTemplateDto mailTemplate = mailTemplateService
				.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_LOGIN_GENERATOR);
		Map<String, Object> mapList = AbstractContextServiceImpl.getParamMap("role", ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser = userService.findDto("getUserByRoleCode", mapList);
		int count = userService.loginGenerator(userList, mailTemplate, internalUser);
		int totalRecords = 0;
		if (!CommonUtil.isCollectionEmpty(userList)) {
			totalRecords = userList.size();
		}
		return count + " Records of " + totalRecords + " failed";
	}

	@RequestMapping(value = "/reloadServletContext", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String reloadServletContext() {
		appInfo.setServletContext(servletContext);
		return "OK";
	}

	@RequestMapping(value = "/loginGeneratorForVendor", method = { RequestMethod.GET,
			RequestMethod.POST },  produces = "application/json")
	public @ResponseBody CustomResponseDto loginGeneratorForVendor(@RequestBody List<Long> listOfUserID) {
		CustomResponseDto customResponseDto = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("listOfUserId", listOfUserID);
		List<UserDto> userList = userService.findDtos("getQueryForUserListForVendorsLoginGeneratorNew", params);
		/*MailTemplateDto mailTemplate = mailTemplateService
				.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_LOGIN_GENERATOR);*/
		MailTemplateDto mailTemplate = mailTemplateService
				.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_REGISTRATION_ALKYL);
		Map<String, Object> mapList = AbstractContextServiceImpl.getParamMap("role", ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser = userService.findDto("getUserByRoleCode", mapList);
		int count = userService.loginGenerator(userList, mailTemplate, internalUser);
		int totalRecords = 0;
		if (!CommonUtil.isCollectionEmpty(userList)) {
			totalRecords = userList.size();
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
