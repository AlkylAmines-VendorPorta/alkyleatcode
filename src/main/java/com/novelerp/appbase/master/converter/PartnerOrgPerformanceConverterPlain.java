package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgPerformanceConverterPlain extends CustomDozerConverter<PartnerOrgPerformance, PartnerOrgPerformanceDto> implements ObjectConverter<PartnerOrgPerformance, PartnerOrgPerformanceDto>{
	
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
		return dto;
	}

}

