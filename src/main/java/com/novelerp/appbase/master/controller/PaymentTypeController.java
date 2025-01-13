package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.service.PaymentTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class PaymentTypeController {
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@RequestMapping(value = "/PaymentType", method = RequestMethod.GET)
	public ModelAndView PaymentTypeView() {
		return  new ModelAndView("paymentType");
	}
	
	@RequestMapping(value = "/getPaymentTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<PaymentTypeDto> getPaymentTypeDtoList() {
		
		List<PaymentTypeDto> paymentTypeDtoList = paymentTypeService.getPaymentTypeList();
		
		return paymentTypeDtoList;
	}
	
	@RequestMapping(value = "/getPaymentType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody PaymentTypeDto getPaymentTypeDto(@PathVariable("id") Long id) {
		PaymentTypeDto paymentTypeDto=paymentTypeService.getPaymentType(id);
		return paymentTypeDto;
	}

}


