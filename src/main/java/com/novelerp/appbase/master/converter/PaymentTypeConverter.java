package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class PaymentTypeConverter extends CustomDozerConverter<PaymentType, PaymentTypeDto> implements ObjectConverter<PaymentType, PaymentTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public PaymentTypeDto convertEntityToDto(PaymentType entity, Class<PaymentTypeDto> dto) {
		if(entity == null){
			return null;
		}
		PaymentTypeDto paymentTypeDto = new PaymentTypeDto();
		paymentTypeDto.setCode(entity.getCode());
		paymentTypeDto.setName(entity.getName());
		paymentTypeDto.setAmount(entity.getAmount());
		paymentTypeDto.setGst(entity.getGst());
		paymentTypeDto.setPaymentTypeId(entity.getPaymentTypeId());
		paymentTypeDto.setCreated(entity.getCreated());
		paymentTypeDto.setUpdated(entity.getUpdated());
		paymentTypeDto.setIsActive(entity.getIsActive());
		paymentTypeDto.setIsRefundable(entity.getIsRefundable());
		paymentTypeDto.setPeriodType(entity.getPeriodType());
		paymentTypeDto.setDescription(entity.getDescription());
		/*paymentTypeDto.setPartner(getParter(entity));*/
		
		return paymentTypeDto;
	}
	
	private BPartnerDto getParter(PaymentType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

