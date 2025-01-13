package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentApprovalService;
import com.novelerp.eat.service.PaymentDetailService;

/**
 * 
 * @author varsha
 *
 */
@Controller
public class PaymentApprovalController {

	private Logger Log =  LoggerFactory.getLogger(PaymentApprovalController.class);
	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentApprovalService paymentApprovalService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value= {"/approval"},method =RequestMethod.GET)
	public ModelAndView approval(HttpServletRequest request)
	{   
		ModelAndView view=new ModelAndView("paymentApproval");
		view.addObject("role", contextService.getDefaultRole());
	    return view;
	}
	
	/*@RequestMapping(value= {"/getPayments"},method =RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto getPaymentForApproval()
	{
		RoleDto roleDto= contextService.getDefaultRole();
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("payment", paymentDetailService.getPaymentsForApproval());
		response.addObject("role", roleDto.getValue());
		return response;
	}*/
	@RequestMapping(value = "/getPayments", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getPaymentForApproval(@RequestParam(value="paymentStatus", required=false)String paymentStatus, 
														   @RequestParam("pageNumber")int pageNumber, 
														   @RequestParam("pageSize") int pageSize,
														   @RequestParam("searchMode") String searchMode , 
														   @RequestParam("searchValue") String searchValue) {
	
		Log.info("getPaymentForApproval -- " +searchValue);
		
		RoleDto roleDto= contextService.getDefaultRole();
		UserDetailsDto userDetail=contextService.getUserDetails();

		CustomResponseDto customResponseDto=new CustomResponseDto();
		List<PaymentDetailDto> payments=null;
		long countResult=0l;
		Map<String, Object> objectMap = new HashMap<>();
		Map<String,Object> param=new HashMap<String,Object>();
		if(!CommonUtil.isStringEmpty(paymentStatus) ){
			Log.info("getPaymentForApproval -- payment status: " + paymentStatus);
			String locationType=userDetail.getLocationTypeRef();
			UserDetailsDto officeLocation=userDetailsService.findDto("getOfficeLocationFromUserDetailsID", AbstractServiceImpl.getParamMap("userDetailsId",userDetail.getUserDetailsId()));
			param.put("paymentStatus", paymentStatus);
			param.put("onlinePaymentMode",AppBaseConstant.PAYMENT_MODE_OP);
			countResult = paymentDetailService.getPaymentStatusCount(paymentStatus,searchMode, searchValue,locationType,officeLocation.getOfficeLocation().getName());
			int LastPage = (int) ((countResult / pageSize) + 1);
			payments=paymentDetailService.getPaymentByStatus(param,pageNumber, pageSize,searchMode, searchValue,locationType,officeLocation.getOfficeLocation().getName());
			objectMap.put("LastPage", LastPage);
		}
		else{
			param.put("onlinePaymentMode",AppBaseConstant.PAYMENT_MODE_OP);
			Log.info("getPaymentForApproval -- out payment status: " + paymentStatus);
			String locationType=userDetail.getLocationTypeRef();
			UserDetailsDto officeLocation=userDetailsService.findDto("getOfficeLocationFromUserDetailsID", AbstractServiceImpl.getParamMap("userDetailsId",userDetail.getUserDetailsId()));
			countResult = paymentDetailService.getPaymentStatusCount(paymentStatus,searchMode, searchValue,locationType,officeLocation.getOfficeLocation().getName());
			int LastPage = (int) ((countResult / pageSize) + 1);
			payments=paymentDetailService.getPaymentsForApproval(param,pageNumber, pageSize,searchMode, searchValue,locationType,officeLocation.getOfficeLocation().getName());
			objectMap.put("LastPage", LastPage);
		}
		customResponseDto.setData(payments);
		customResponseDto.setObjectMap(objectMap);
		customResponseDto.addObject("role", roleDto.getValue());
		return customResponseDto;
	}
	
	@RequestMapping(value= {"/updatePaymentStatus"},method =RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseDto updatePaymentStatus(@ModelAttribute("paymentDetail") PaymentDetailDto paymentDetailDto)
	{   
		RoleDto roleDto= contextService.getDefaultRole();
		UserDto sessionUser=contextService.getUser();
		if(roleDto==null || roleDto.getValue()==null || sessionUser==null || sessionUser.getUserId()==null){
			return new ResponseDto(true, "Please Re-login");
		}
		
		if(paymentDetailDto.getPartner()==null || paymentDetailDto.getPartner().getbPartnerId()==null)
		{
			return new ResponseDto(true, "Partner is null");
		}
		/*PaymentDetailDto dbPayment=null;*/
		/*if(AppBaseConstant.RENEWAL_FEE.equals(paymentDetailDto.getPaymentType().getCode())){*/
			Map<String,Object> map=AbstractServiceImpl.getParamMap("paymentDetailId",paymentDetailDto.getPaymentDetailId());
			PaymentDetailDto dbPayment=paymentDetailService.findDto("getPaymentById",map);
		/*}*/
		ResponseDto response=paymentApprovalService.updatePaymentStatus(paymentDetailDto,roleDto.getValue(),sessionUser,dbPayment);
		if(!response.isHasError()){
			Map<String, Object> params = new HashMap<>();
			params.put("bPartnerId", paymentDetailDto.getPartner().getbPartnerId());
			UserDto user = userService.findDto("getQueryForUserByPartnerId", params);
		   /* Map<String,Object> param=AbstractServiceImpl.getParamMap("paymentDetailId",paymentDetailDto.getPaymentDetailId());
			paymentDetailDto=paymentDetailService.findDto("getPaymentById", param);
			*/
			dbPayment.setIsFOApproved(paymentDetailDto.getIsFOApproved());
			dbPayment.setIsFAApproved(paymentDetailDto.getIsFAApproved());
			dbPayment.setFoComment(paymentDetailDto.getFoComment());
			dbPayment.setFaComment(paymentDetailDto.getFaComment());
			response=paymentApprovalService.sendPaymentStatusMail(dbPayment, roleDto, user);
			if(roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN) && paymentDetailDto.getIsFAApproved().equals("Y")){
				paymentApprovalService.sendPaymentStatusSMS(dbPayment);
			}
		}
		return response;
		
	}
	/*@RequestMapping(value="/getPaymentsByStatus/{paymentStatus}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPaymentsByStatus(@PathVariable("paymentStatus") String paymentStatus){
		List<PaymentDetailDto> payments=paymentDetailService.getPaymentByStatus(paymentStatus);
		return new CustomResponseDto("payments",payments);
	}*/
}
