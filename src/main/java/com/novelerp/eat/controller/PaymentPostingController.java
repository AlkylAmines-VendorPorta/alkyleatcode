package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentDetailService;

@Controller
public class PaymentPostingController {

	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	private ReferenceListService referenceLinstService;

	@RequestMapping(value = "/getPaymentsForPosting", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getPaymentForApproval(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize,
			@RequestParam(value = "searchMode", required = false) String searchMode,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "showPushedData") String showPushedData) {

		CustomResponseDto customResponseDto = new CustomResponseDto();
		List<PaymentDetailDto> payments = null;
		long countResult = 0l;
		Map<String, Object> objectMap = new HashMap<>();
		Map<String, Object> param = new HashMap<String, Object>();

		countResult = paymentDetailService.getPaymentsForPostingCount(searchMode, searchValue, showPushedData);
		int LastPage = (int) ((countResult / pageSize) + 1);

		param.put("showPushedData", showPushedData);
		payments = paymentDetailService.getPaymentsForPosting(param, pageNumber, pageSize, searchMode, searchValue);
		objectMap.put("LastPage", LastPage);

		customResponseDto.setData(payments);
		customResponseDto.setObjectMap(objectMap);
		return customResponseDto;
	}

	@RequestMapping(value= {"/paymentPosting"},method =RequestMethod.GET)
	public ModelAndView paymentPosting(){
		return new ModelAndView("paymentPosting");
	}
	
	@RequestMapping(value = "/getPaymentModes", method = RequestMethod.POST)
	@ResponseBody
	public List<ReferenceListDto> getPaymentModes(){
		return referenceLinstService.find("referenceCode = 'PAYMENT_MODE'", null, null);
	}
	
	@RequestMapping(value = "/postPaymentDetail/{paymentDetailId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto postPaymentDetail(@PathVariable("paymentDetailId") Long paymentDetailId) {
		Object[] paymentDetails = paymentDetailService.getPaymentANdMISDetail(paymentDetailId);
		return paymentDetailService.postPaymentDetails(paymentDetails);
	}
}
