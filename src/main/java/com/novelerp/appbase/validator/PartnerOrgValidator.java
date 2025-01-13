package com.novelerp.appbase.validator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PartnerOrgValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerOrgDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgDto partnerOrg=(PartnerOrgDto) object;
		dbValidations(partnerOrg,errors);
		
	}
	private void dbValidations(PartnerOrgDto partnerOrg, Errors errors){
		if(partnerOrg.getPaymentDetail()==null || partnerOrg.getPaymentDetail().getPaymentDetailId()==null)
		{
			validator.reject(errors, "invalid.paymentDetail", "Payment Number Not Selected");
			return;
		}
		Map<String, Object> params = AbstractServiceImpl.getParamMap("paymentId", partnerOrg.getPaymentDetail().getPaymentDetailId());
		params.put("partnerId", partnerOrg.getPartner().getbPartnerId());
		PartnerOrgDto partnerOrgDto =  partnerOrgService.findDto("getPartnerOrgByPaymentId", params);
		if(null!=partnerOrgDto && !CommonUtil.isEqual(partnerOrg.getPartnerOrgId(), partnerOrgDto.getPartnerOrgId())){
			
			validator.reject(errors, "already.Used", "Factory with selected payment is already created");
		}
	}

}
