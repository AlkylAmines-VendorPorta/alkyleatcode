package com.novelerp.appcontext.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.util.CaptchaGenerator;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class CaptchaController {

	@Autowired
	CaptchaGenerator captchaGenerator;
	
	@RequestMapping(value = "/createCaptcha", method = RequestMethod.POST)
	public @ResponseBody ResponseDto register(HttpServletRequest request) {

		String captchaString = captchaGenerator.generateCaptchaText(4);
		HttpSession session = request.getSession(true);
		session.setAttribute("CAPTCHA", captchaString);
		return new ResponseDto(false, captchaString);
	}

}
