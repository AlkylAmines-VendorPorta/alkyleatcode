package com.novelerp.appcontext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRService;

@Controller
public class ReportController {
	
	
	@Autowired
	private FinancialYearService financialyear;
	
	@Autowired
	private TAHDRService tAHDRService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@RequestMapping(value= {"/reports"},method =RequestMethod.GET)
	public ModelAndView analytics(){
		ModelAndView mv=new ModelAndView("reports");
		mv.addObject("finYear", financialyear.findAll());
		mv.addObject("tahdrList", tAHDRService.findAll());
		mv.addObject("paymentType", paymentTypeService.findAll());
		return mv;
	}
	
	@RequestMapping(value= {"/getPaymentDetails/{tahdrId}/{fiscalYearId}/{paymentTypeId}/{fromDate}/{toDate}"},method =RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPaymentDetails(@PathVariable("tahdrId") Long tahdrId,
			@PathVariable("fiscalYearId") Long fiscalYearId,@PathVariable("paymentTypeId") Long paymentTypeId,
			@PathVariable("fromDate") Long fromDate,
			  @PathVariable("toDate") Long toDate){
		CustomResponseDto response=new CustomResponseDto();
		List<PaymentDetailDto> list=paymentDetailService.getPaymentDetails(tahdrId,fiscalYearId,paymentTypeId,fromDate,toDate,"");
		if(!CommonUtil.isCollectionEmpty(list)){
			response.addObject("paymentDetailList", list); 
			response.addObject("result", true);  	
		}else{
			response.addObject("result", false); 
			response.addObject("resultMessage", "No Record Found !"); 	
		}
		return response;
	}
}
