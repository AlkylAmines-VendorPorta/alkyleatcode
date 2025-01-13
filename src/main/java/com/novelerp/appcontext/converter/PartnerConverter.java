package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.AttachmentConverter;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class PartnerConverter extends CustomDozerConverter<Bpartner, BPartnerDto> 
	implements ObjectConverter<Bpartner, BPartnerDto>{

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private AttachmentConverter attachmentConverter;
	
	@Override
	public BPartnerDto convertEntityToDto(Bpartner entity, Class<BPartnerDto> dto) {
		if(entity==null){
			return null;
		}

		BPartnerDto partner = new BPartnerDto();
		AttachmentDto panCardCopy=attachmentConverter.getDtoFromEntity(entity.getPanCardCopy(), AttachmentDto.class);
		AttachmentDto GSTINCopy=attachmentConverter.getDtoFromEntity(entity.getGstinCopy(), AttachmentDto.class);
		AttachmentDto compRegCertificate=attachmentConverter.getDtoFromEntity(entity.getCompanyRegCertificate(), AttachmentDto.class);
		partner.setPanCardCopy(panCardCopy);
		partner.setGstinCopy(GSTINCopy);
		partner.setCompanyRegCertificate(compRegCertificate);
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
		loadDefault(entity, partner);
		return partner;
	}
	
	private void loadDefault(Bpartner entity, BPartnerDto dto){
		
		UserDto createdBy =	userConverter.getDtoFromEntity(entity.getCreatedBy(), UserDto.class);
		UserDto updatedBy = userConverter.getDtoFromEntity(entity.getUpdatedBy(), UserDto.class);
		dto.setCreatedBy(createdBy);
		dto.setUpdatedBy(updatedBy);		
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setIsActive(entity.getIsActive());
	}

}

