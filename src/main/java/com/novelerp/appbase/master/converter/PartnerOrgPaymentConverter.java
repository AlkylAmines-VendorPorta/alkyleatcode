package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgPaymentDto;
import com.novelerp.appbase.master.entity.PartnerOrgPayment;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgPaymentConverter extends CustomDozerConverter<PartnerOrgPayment, PartnerOrgPaymentDto> implements ObjectConverter<PartnerOrgPayment, PartnerOrgPaymentDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	private BPartnerDto getPartner(PartnerSignatory entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}
	@Override
	public PartnerOrgPaymentDto convertEntityToDto(PartnerOrgPayment entity,
			Class<PartnerOrgPaymentDto> dtoClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
