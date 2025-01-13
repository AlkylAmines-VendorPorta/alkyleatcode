package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PaymentModeDto;
import com.novelerp.appbase.master.entity.PaymentMode;
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
public class PaymentModeConverter extends CustomDozerConverter<PaymentMode, PaymentModeDto> implements ObjectConverter<PaymentMode, PaymentModeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public PaymentModeDto convertEntityToDto(PaymentMode entity, Class<PaymentModeDto> dto) {
		if(entity == null){
			return null;
		}
		PaymentModeDto PaymentModeDto = new PaymentModeDto();
		PaymentModeDto.setCode(entity.getCode());
		PaymentModeDto.setName(entity.getName());
		PaymentModeDto.setPaymentModeId(entity.getPaymentModeId());
		PaymentModeDto.setCreated(entity.getCreated());
		PaymentModeDto.setUpdated(entity.getUpdated());
		PaymentModeDto.setIsActive(entity.getIsActive());
		PaymentModeDto.setDescription(entity.getDescription());
		PaymentModeDto.setPartner(getParter(entity));
		
		return PaymentModeDto;
	}
	
	private BPartnerDto getParter(PaymentMode entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

