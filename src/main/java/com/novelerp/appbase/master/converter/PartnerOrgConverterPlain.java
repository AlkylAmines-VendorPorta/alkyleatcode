package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgConverterPlain extends CustomContextDozerConverter<PartnerOrg, PartnerOrgDto> implements ObjectConverter<PartnerOrg, PartnerOrgDto>{

	@Override
	public PartnerOrgDto convertEntityToDto(PartnerOrg entity,
			Class<PartnerOrgDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgDto dto  = new PartnerOrgDto();
		dto.setName(entity.getName());
		dto.setEstdDate(entity.getEstdDate());
		dto.setManPower(entity.getManPower());
		dto.setInspectionReportNo(entity.getInspectionReportNo());
		dto.setPartnerOrgId(entity.getPartnerOrgId());
		return dto;
	}
}
