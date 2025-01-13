package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgPerformanceConverter extends CustomDozerConverter<PartnerOrgPerformance, PartnerOrgPerformanceDto> implements ObjectConverter<PartnerOrgPerformance, PartnerOrgPerformanceDto>{

	@Autowired
	private PartnerOrgConverter partnerOrgConverter;
	
	@Autowired
	private MaterialConverter materialConverter;
	
	@Override
	public PartnerOrgPerformanceDto convertEntityToDto(PartnerOrgPerformance entity,
			Class<PartnerOrgPerformanceDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgPerformanceDto dto =  new PartnerOrgPerformanceDto();
		dto.setPartnerOrgPerformanceId(entity.getPartnerOrgPerformanceId());
		dto.setFirmName(entity.getFirmName());
		dto.setOrderStartDate(entity.getOrderStartDate());
		dto.setOrderEndDate(entity.getOrderEndDate());
		dto.setQuantitySupplied(entity.getQuantitySupplied());
		dto.setReferenc1(entity.getReferenc1());
		dto.setReferenc2(entity.getReferenc2());
		PartnerOrgDto partnerOrg = partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		MaterialDto material = materialConverter.getDtoFromEntity(entity.getMaterial(), MaterialDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setMaterial(material);
		dto.setLoadDefault(true);
		return dto;
	}

}

