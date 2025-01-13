package com.novelerp.eat.service;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.exception.InvalidDataException;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;

public interface PaymentGatewayService {

	public static final int SUCCESS=1;
	public static final int FAILURE=0;
	public static final int SECURITY_PROBLEM=400;
	public static final int INVALID_DATA=402; 

	public PaymentGatewayResponseDto process(String message);
	public String encryptmsg(String data) throws InvalidDataException;
	public String getEmdPaymentData(UserDto dto, TAHDRDetailDto tAHDRDetailDto, BPartnerDto bPartner, Long requestID);
	public String getTahdrFeesPaymentData(UserDto userDto, TAHDRDetailDto td, BPartnerDto bPartner, PaymentDetailDto paymentDetailDto);
	public String getRegistrationPaymentData(UserDto userDto, BPartnerDto bPartner, Long requestID);
	
}
