package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.PaymentModeDto;
import com.novelerp.appbase.master.service.PaymentModeService;

/** 
 * @author Aman
 *
 */
@Controller
public class PaymentModeController {
	@Autowired
	private PaymentModeService paymentModeService;
	
	@RequestMapping(value = "/PaymentMode", method = RequestMethod.GET)
	public ModelAndView PaymentModeView() {
		System.out.println("..PaymentModeController-PaymentModeView()");
		return  new ModelAndView("PaymentMode");
	}
	
	@RequestMapping(value = "/getPaymentMode", method = RequestMethod.POST)
	public List<PaymentModeDto> getPaymentMode() {
		System.out.println("..PaymentModeController-getPaymentMode()");
		return paymentModeService.getPaymentModeList();
	}

}


