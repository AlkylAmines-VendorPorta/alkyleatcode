package com.novelerp.eat.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;

@Component
public class EMDPaymentValidator implements Validator{
	
	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	/*@Autowired
	private PartnerOrgProductService partnerOrgProductService;*/
	
	@Override
	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(PaymentDetailDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		PaymentDetailDto payment=(PaymentDetailDto) object;
		TAHDRDto tahdr=new TAHDRDto();
		TAHDRDetailDto td=new TAHDRDetailDto();
		Map<String, Object> map=new HashMap<>();
		BPartnerDto partner=contextService.getPartner();
		
		if(payment.getTahdr()==null){
			validatorUtil.reject(errors, "invalid.payment", "Invalid Payment");
		}
		
		if(payment.getPaymentType()==null){
			validatorUtil.reject(errors, "invalid.payment", "Invalid Payment Type");
		}
		else{
			if(payment.getPaymentType().getCode()==null || payment.getPaymentType().getCode().equals("")){
				validatorUtil.reject(errors, "invalid.payment", "Invalid Payment Type");
			}
		}
		if(payment.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_BG)){
			checkForValidityDate(payment,errors);
		}
		if(payment.getTahdr()==null){
			validatorUtil.reject(errors, "invalid.payment", "Invalid Tender Selected ");
		}else{
			PaymentDetailDto paymentDetail=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(payment.getTahdr().getTahdrId(),AppBaseConstant.TENDER_PURCHASE_FEE);
			tahdr=tahdrService.findDto("getQueryForTAHDRById", AbstractContextServiceImpl.getParamMap("tahdrId", payment.getTahdr().getTahdrId()));
			td=tahdr.getTahdrDetail().iterator().next();
			map.put("tahdrDetailId", td.getTahdrDetailId());

			if(tahdr.getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
				map.put("partnerId", partner.getbPartnerId());
				if(paymentDetail.getPartnerOrg()!=null){
					map.put("factoryId", paymentDetail.getPartnerOrg().getPartnerOrgId());
					if(payment.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
						td=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturerEXEM", map);
					}else{
						td=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturer", map);
					}
					if(td==null){
						validatorUtil.reject(errors, "invalid.item", " Purchase is not allowed. No item in the tender exist in selected factory. ");
					}
				}else if(paymentDetail.getIsTrader().equals("Y")){
					td=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPTrader", map);
					String isTradingItem=sysConfiguratorService.getSystemPropertyConfigurator("eat.material.trading_item_check");
					boolean tradingItemCheck=false;
					if(isTradingItem.equalsIgnoreCase("Y")){
						tradingItemCheck=true;
					}
					if(td==null && tradingItemCheck){
						validatorUtil.reject(errors, "invalid.item", " Purchase is not allowed. No item in the tender exist in your Trading item List. ");
					}
				}else{
					validatorUtil.reject(errors, "invalid.buyer", " Invalid purchase role. ");
				}
				
			}
		}
	}
	
	public static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }
	
	private void checkForValidityDate(PaymentDetailDto payment, Errors errors)
	{
	Date date=new Date();
	Date todaysDate= 	removeTime(date);
	if(payment.getValidityDate()==null)
	{
		validatorUtil.reject(errors, "invalid.saleDate","Validity Date is Mandatory");
	}
	else if(payment.getValidityDate().compareTo(todaysDate)==0){
		validatorUtil.reject(errors, "invalid.saleDate","Validity Date should be a Future Date");
	}
	else if(payment.getValidityDate().compareTo(todaysDate)==1)
	{
		return;
	}
	else if(payment.getValidityDate().compareTo(todaysDate)==-1)
	{
		validatorUtil.reject(errors, "invalid.saleDate","Validity Date should be a Future Date");
	}
	}
	
}
