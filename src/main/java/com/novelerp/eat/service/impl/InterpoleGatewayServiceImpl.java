package com.novelerp.eat.service.impl;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.exception.InvalidDataException;
import com.novelerp.core.exception.SecurityException;
import com.novelerp.core.service.SecurityService;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PaymentGatewayDto;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PaymentGatewayResponseService;
import com.novelerp.eat.service.PaymentGatewayService;

@Service
public class InterpoleGatewayServiceImpl implements PaymentGatewayService {

	private Logger log = LoggerFactory.getLogger(InterpoleGatewayServiceImpl.class);
	@Autowired
	@Qualifier("MD5_SEC")
	private SecurityService securityService;

	@Autowired
	PaymentDetailService paymentDetailService;

	@Autowired
	PaymentGatewayResponseService paymentGatewayResponseService;

	@Autowired
	private AppPropertyUtil propertyUtil;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;


	@Override
	public PaymentGatewayResponseDto process(String message) {
		/*String secKey = propertyUtil.getProperty("eat.payment.gateway.key");*/
		String secKey =sysConfiguratorService.getPropertyConfigurator("eat.payment.gateway.key");

		/*String iv = propertyUtil.getProperty("eat.payment.gateway.iv");*/
		String iv =sysConfiguratorService.getPropertyConfigurator("eat.payment.gateway.iv");

		PaymentGatewayResponseDto paymentGatewayResponseDto = new PaymentGatewayResponseDto();
		try {
			String decryptedMessage = securityService.decryptDataNoPadding(message, secKey, iv);
			String jString = parseJsonString(decryptedMessage);
			JSONObject jObj = getJsonObject(jString);
			String requestID = (String) jObj.get("request_id");
			String status = (String) jObj.get("status");
			paymentGatewayResponseDto.setDocNo(Long.parseLong(requestID));
			paymentGatewayResponseDto.setStatus(status);
			paymentGatewayResponseDto.setResponse(new ResponseDto(false, status));
			log.info(decryptedMessage);
			System.out.println("Decrypted text >>" + decryptedMessage);
		} catch (InvalidDataException e) {
			paymentGatewayResponseDto.setResponse(new ResponseDto(false, e.getMessage()));
			log.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		} catch (SecurityException e) {
			paymentGatewayResponseDto.setResponse(new ResponseDto(false, e.getMessage()));
			log.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			paymentGatewayResponseDto.setResponse(new ResponseDto(true, null));
			log.error(e.getMessage(), e);
			System.out.println(e.getMessage());
		}
		return paymentGatewayResponseDto;
	}

	@Override
	public String encryptmsg(String data) throws InvalidDataException {
		if (CommonUtil.isStringEmpty(data)) {
			throw new InvalidDataException("Invalid Data");
		}
		/*String secKey = propertyUtil.getProperty("eat.payment.gateway.key");*/
		String secKey =sysConfiguratorService.getPropertyConfigurator("eat.payment.gateway.key");

		/*String iv = propertyUtil.getProperty("eat.payment.gateway.iv");*/
		String iv =sysConfiguratorService.getPropertyConfigurator("eat.payment.gateway.iv");

		String encryptedMsg = null;
		try {
			String msg = data + "|hash=" + securityService.getCheckusm(data);
			encryptedMsg = securityService.secureData(msg, secKey, iv);
		} catch (NoSuchAlgorithmException e) {
			log.error("Error", e);
		} catch (InvalidDataException e) {
			log.error("Error", e);
		}
		return encryptedMsg;
	}

	@Override
	public String getRegistrationPaymentData(UserDto userDto, BPartnerDto bPartner, Long requestID) {
		if (userDto == null) {
			return null;
		}
		PaymentGatewayDto paymentGatewayDto = new PaymentGatewayDto();
		paymentGatewayDto.setRequestId(requestID); //
		paymentGatewayDto.setTxnAmt(new BigDecimal(1));
		paymentGatewayDto.setEmail("NA");
		paymentGatewayDto.setMobileNo("NA");
		//paymentGatewayDto.setPayeeName(userDto.getUserDetails().getFirstName());
		String redirectUrl=sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.PAYMENT_GATEWAY_REDIRECT_URL);
		paymentGatewayDto.setRedirectUrl(redirectUrl);
		//paymentGatewayDto.setDate(DateUtil.getDateString(new Date(), "dd-MM-yyyy hh:mm"));
		//paymentGatewayDto.setPaymentType(AppBaseConstant.REGISTRATION_FEE);
		
		StringBuilder udf = new StringBuilder(AppBaseConstant.PAYMENT_GATEWAY_DOCNO_PREFIX+requestID);
		String date = DateUtil.getDateString(new Date(), "dd-MM-yyyy");
		if (!CommonUtil.isStringEmpty(date)) {
			udf.append("_" + date);
		}
		
		paymentGatewayDto.setUdf(udf.toString());
		try {
			return process(paymentGatewayDto);
		} catch (InvalidDataException e) {
			log.error("error", e);
		}
		return null;
	}

	@Override
	public String getEmdPaymentData(UserDto userDto, TAHDRDetailDto td, BPartnerDto bPartner, Long requestID) {

		if (userDto == null || td == null) {
			return null;
		}
		try {

			PaymentGatewayDto paymentGatewayDto = new PaymentGatewayDto();
			paymentGatewayDto.setTxnAmt(new BigDecimal(1)); // td.getEmdFee()
			paymentGatewayDto.setEmail("NA");
			paymentGatewayDto.setMobileNo("NA");
			//paymentGatewayDto.setPayeeName(userDto.getUserDetails().getFirstName());
			//paymentGatewayDto.setTenderNo(td.getTahdr().getTahdrCode());
			String redirectUrl=sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.PAYMENT_GATEWAY_REDIRECT_URL);
			paymentGatewayDto.setRedirectUrl(redirectUrl);
			paymentGatewayDto.setRequestId(requestID);

			/*paymentGatewayDto.setPaymentType(AppBaseConstant.EMD);*/
			/*StringBuilder udf = new StringBuilder("TENDER01/625_ACME MFG_VEND101");*/
			StringBuilder udf = new StringBuilder(AppBaseConstant.PAYMENT_GATEWAY_DOCNO_PREFIX+requestID);
			String date = DateUtil.getDateString(new Date(), "dd-MM-yyyy");
			if (!CommonUtil.isStringEmpty(date)) {
				udf.append("_" + date);
			}
			
			paymentGatewayDto.setUdf(udf.toString());
			return process(paymentGatewayDto);
		} catch (InvalidDataException e) {
			log.error("error", e);
		}
		return null;
	}

	@Override
	public String getTahdrFeesPaymentData(UserDto userDto, TAHDRDetailDto td, BPartnerDto bPartner,
			PaymentDetailDto paymentDetailDto) {

		if (userDto == null || td == null) {
			return null;
		}
		try {

			PaymentGatewayDto paymentGatewayDto = new PaymentGatewayDto();
			paymentGatewayDto.setTxnAmt(new BigDecimal(1)); // paymentDetailDto.getTotal()
			paymentGatewayDto.setEmail("NA");
			paymentGatewayDto.setMobileNo("NA");
			//paymentGatewayDto.setPayeeName(userDto.getUserDetails().getFirstName());
			//paymentGatewayDto.setTenderNo(td.getTahdr().getTahdrCode());
			String redirectUrl=sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.PAYMENT_GATEWAY_REDIRECT_URL);
			paymentGatewayDto.setRedirectUrl(redirectUrl);
			paymentGatewayDto.setRequestId(paymentDetailDto.getDocNo());

			/*paymentGatewayDto.setPaymentType(AppBaseConstant.TENDER_PURCHASE_FEE);*/
			/*StringBuilder udf = new StringBuilder("TENDER01/625_ACME MFG_VEND101");*/
			StringBuilder udf = new StringBuilder(AppBaseConstant.PAYMENT_GATEWAY_DOCNO_PREFIX+paymentDetailDto.getDocNo());
			String date = DateUtil.getDateString(new Date(), "dd-MM-yyyy");
			if (!CommonUtil.isStringEmpty(date)) {
				udf.append("_" + date);
			}
			
			paymentGatewayDto.setUdf(udf.toString());
			return process(paymentGatewayDto);
		} catch (InvalidDataException e) {
			log.error("error", e);
		}
		return null;
	}

	private String getMsg(PaymentGatewayDto paymentGatewayDto) {
		if (paymentGatewayDto == null) {
			return "";
		}
		StringBuilder data = new StringBuilder();
		data.append("request_id=" + paymentGatewayDto.getRequestId());
		if (paymentGatewayDto.getTxnAmt() != null)
			data.append("|txn_amt=" + paymentGatewayDto.getTxnAmt());
		//if (paymentGatewayDto.getEmail() != null)
			data.append("|email=NA");
		//if (paymentGatewayDto.getMobileNo() != null)
			data.append("|mob=NA");
		if (paymentGatewayDto.getRedirectUrl() != null)
			data.append("|redirect_url=" + paymentGatewayDto.getRedirectUrl());
		if (paymentGatewayDto.getUdf() != null)
			data.append("|udf=" + paymentGatewayDto.getUdf());
		return data.toString();
	}

	private String process(PaymentGatewayDto paymentGatewayDto) throws InvalidDataException {
		if (paymentGatewayDto == null) {
			throw new InvalidDataException("Invalid Data");
		}
		return encryptmsg(getMsg(paymentGatewayDto));
	}

	private String parseJsonString(String data) {
		if (CommonUtil.isStringEmpty(data)) {
			throw new InvalidParameterException();
		}
		data = data.replaceAll("\\|", "\\\",\\\"");
		data = data.replaceAll("=", "\\\":\\\"");
		data = "{\"" + data + "\"}";
		return data;
	}

	private JSONObject getJsonObject(String jsonString) throws ParseException {
		if (CommonUtil.isStringEmpty(jsonString)) {
			throw new InvalidParameterException();
		}
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject) parser.parse(jsonString);
		return jObj;
	}

}
