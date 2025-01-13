package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class PartnerConverterPlain extends CustomDozerConverter<Bpartner, BPartnerDto> 
implements ObjectConverter<Bpartner, BPartnerDto>{

	@Override
	public BPartnerDto convertEntityToDto(Bpartner entity, Class<BPartnerDto> dto) {
		if(entity==null){
			return null;
		}

		BPartnerDto partner = new BPartnerDto();
		partner.setValue(entity.getValue());
		partner.setName(entity.getName());
		partner.setbPartnerId(entity.getbPartnerId());
		partner.setDescription(entity.getDescription());
		partner.setCrnNumber(entity.getCrnNumber());
		partner.setPanNumber(entity.getPanNumber());
		partner.setCompanyType(entity.getCompanyType());
		partner.setContractorType(entity.getContractorType());
		partner.setIsGstApplicable(entity.getIsGstApplicable());
		partner.setGstinNo(entity.getGstinNo());
		partner.setIsActive(entity.getIsActive());
		partner.setIsCustomer(entity.getIsCustomer());
		partner.setIsVendor(entity.getIsVendor());
		partner.setIsEmployee(entity.getIsEmployee());
		partner.setIsContractor(entity.getIsContractor());
		partner.setIsManufacturer(entity.getIsManufacturer());
		partner.setIsTrader(entity.getIsTrader());
		return partner;
	}

}
