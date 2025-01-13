package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgProductConverter extends CustomContextDozerConverter<PartnerOrgProduct, PartnerOrgProductDto> {

	@Autowired
	private UomConverterPlain uomConverter;
	@Autowired
	private MaterialConverter materialConverter;
	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
	@Autowired
	private PartnerOrgBISConverterPlain partnerOrgBISConverter;
	@Autowired
	private PartnerOrgRegistrationConverterPlain partnerOrgRegistrationConverter;
	@Override
	public PartnerOrgProductDto convertEntityToDto(PartnerOrgProduct entity, Class<PartnerOrgProductDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgProductDto dto =  new PartnerOrgProductDto();
		dto.setPartnerOrgProductId(entity.getPartnerOrgProductId());
		dto.setLicenceNo(entity.getLicenceNo());
		dto.setQtyManufacturedPM(entity.getQtyManufacturedPM());
		dto.setTurnOver(entity.getTurnOver());
		dto.setRegistrationType(entity.getRegistrationType());
		MaterialDto material = materialConverter.getDtoFromEntity(entity.getMaterial(), MaterialDto.class);
		UOMDto uom = uomConverter.getDtoFromEntity(entity.getUom(), UOMDto.class);
		PartnerOrgDto partnerOrg =  partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		PartnerOrgRegistrationDto partnerOrgRegistration=partnerOrgRegistrationConverter.getDtoFromEntity(entity.getPartnerOrgRegistration(), PartnerOrgRegistrationDto.class);
		PartnerOrgBISDto partnerOrgBIS=partnerOrgBISConverter.getDtoFromEntity(entity.getPartnerOrgBIS(), PartnerOrgBISDto.class);
		dto.setPartnerOrgRegistration(partnerOrgRegistration);
		dto.setPartnerOrgBIS(partnerOrgBIS);
		dto.setPartnerOrg(partnerOrg);
		dto.setMaterial(material);
		dto.setUom(uom);
		dto.setLoadDefault(true);
		return dto;
	}

}
