/**
 * @author Ankush
 */
package com.novelerp.eat.validator;

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
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRService;

@Component
public class TahdrPurchaseValidator implements Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	/*@Autowired
	private PartnerOrgProductService partnerOrgProductService;*/
	
	/* (non-Javadoc)
	 * 
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PaymentDetailDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
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
		}else{
			if(payment.getPaymentType().getCode()==null || payment.getPaymentType().getCode().equals("")){
				validatorUtil.reject(errors, "invalid.payment", "Invalid Payment Type");
			}
		}
	
		if(payment.getTahdr()==null){
			validatorUtil.reject(errors, "invalid.payment", "Invalid Tender Selected ");
		}else{
			tahdr=tahdrService.findDto("getQueryForTAHDRById", AbstractContextServiceImpl.getParamMap("tahdrId", payment.getTahdr().getTahdrId()));
			td=tahdr.getTahdrDetail().iterator().next();
			map.put("tahdrDetailId", td.getTahdrDetailId());
			if(tahdr.getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
				map.put("partnerId", partner.getbPartnerId());
				if(payment.getPartnerOrg()!=null){
					if(payment.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
						map.put("apValue", AppBaseConstant.EXEMPTED_APPROVED);
					}
					else{
						map.put("apValue", "Y");
					}
					map.put("factoryId", payment.getPartnerOrg().getPartnerOrgId());
					td=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturerForPurchase", map);
					if(td==null){
						validatorUtil.reject(errors, "invalid.item", " Purchase is not allowed. No item in the tender exist in selected factory. ");
					}
				}else if(payment.getIsTrader().equals("Y")){
					td=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPTrader", map);
					String isTradingItem=sysConfiguratorService.getPropertyConfigurator("eat.material.trading_item_check");
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
		
		/*if(bidder.getTahdr().getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
			map.put("partnerId", partner.getbPartnerId());
			if(bidder.getFactory()!=null){
				map.put("factoryId", bidder.getFactory().getPartnerOrgId());
				tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPManufacturer", map);
			}else{
				tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTPTrader", map);
			}
		}else{
			tahdrDetailDto=tahdrDetailService.findDto("getQueryForTAHDRDetailMaterialWithGTP", map);
		}*/
	}

}
