package com.novelerp.eat.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.PaymentDetailDto;
@Component
public class PaymentDetailValidator implements Validator {


	@Autowired
	private ValidationUtil validator;
	
	@Override
	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(PaymentDetailDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
	}
	public void checkPaymentType(PaymentDetailDto dto,Errors errors){
		if((AppBaseConstant.REGISTRATION_FEE.equals(dto.getPaymentType().getCode()) || AppBaseConstant.RENEWAL_FEE.equals(dto.getPaymentType().getCode()) 
		|| AppBaseConstant.MANUFACTURER_PAYMENT.equals(dto.getVendorTypePayment()) || AppBaseConstant.TRADER_PAYMENT.equals(dto.getVendorTypePayment()))
		&& ("N".equals(dto.getPartner().getIsTrader()) && "N".equals(dto.getPartner().getIsManufacturer()))){
			validator.reject(errors, "invalid.paymentDetail", "You cannot pay for given payment type as you are not trader or manufacture");
			return;
		}
		if((AppBaseConstant.INFRA_REGISTRATION_FEE.equals(dto.getPaymentType().getCode()) || AppBaseConstant.INFRA_PAYMENT.equals(dto.getVendorTypePayment())) 
			&& "N".equals(dto.getPartner().getIsInfra())){
			validator.reject(errors, "invalid.paymentDetail", "You cannot pay for given payment type as you are not infra");
			return;
		}
		if("Y".equals(dto.getPartner().getIsTrader()) && AppBaseConstant.TRADER_PAYMENT.equals(dto.getVendorTypePayment()) 
			&& !AppBaseConstant.REGISTRATION_FEE.equals(dto.getPaymentType().getCode()) && !AppBaseConstant.RENEWAL_FEE.equals(dto.getPaymentType().getCode())){
			validator.reject(errors, "invalid.paymentType", "Invalid payment type selected for given vendor type payment");
		}
		if("Y".equals(dto.getPartner().getIsManufacturer()) && AppBaseConstant.MANUFACTURER_PAYMENT.equals(dto.getVendorTypePayment()) 
				&& !AppBaseConstant.REGISTRATION_FEE.equals(dto.getPaymentType().getCode())&& !AppBaseConstant.RENEWAL_FEE.equals(dto.getPaymentType().getCode())){
				validator.reject(errors, "invalid.paymentType", "Invalid payment type selected for given vendor type payment");
		}
		if("Y".equals(dto.getPartner().getIsInfra()) && AppBaseConstant.INFRA_PAYMENT.equals(dto.getVendorTypePayment())
				&& !AppBaseConstant.INFRA_REGISTRATION_FEE.equals(dto.getPaymentType().getCode())){
			validator.reject(errors, "invalid.paymentType", "Invalid payment type selected for given vendor type payment");
		}
	}
}
