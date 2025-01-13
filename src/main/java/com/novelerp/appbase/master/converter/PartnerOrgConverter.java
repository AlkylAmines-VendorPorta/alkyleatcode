package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *	{Changed By Vivek Birdi}
 *
 */
@Component
public class PartnerOrgConverter extends CustomContextDozerConverter<PartnerOrg, PartnerOrgDto> implements ObjectConverter<PartnerOrg, PartnerOrgDto>{

	@Autowired
	private LocationConverter locationConverter;
	@Autowired
	private AttachmentConverter attachmentConverter;

	@Override
	public PartnerOrgDto convertEntityToDto(PartnerOrg entity,
			Class<PartnerOrgDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgDto dto  = new PartnerOrgDto();
		dto.setName(entity.getName());
		LocationDto locationDto =   locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		AttachmentDto licenceCopy=attachmentConverter.getDtoFromEntity(entity.getLicenceCopy(), AttachmentDto.class);
		AttachmentDto machinaryListCopy=attachmentConverter.getDtoFromEntity(entity.getMachinaryListCopy(), AttachmentDto.class);
		AttachmentDto inspectionReportCopy=attachmentConverter.getDtoFromEntity(entity.getInspectionReportCopy(), AttachmentDto.class);
		dto.setLicenceCopy(licenceCopy);
        dto.setMachinaryListCopy(machinaryListCopy);
		dto.setLocation(locationDto);
		dto.setEstdDate(entity.getEstdDate());
		dto.setInspectionReportNo(entity.getInspectionReportNo());
		dto.setPartnerOrgId(entity.getPartnerOrgId());
		dto.setManPower(entity.getManPower());
		dto.setLicenceNo(entity.getLicenceNo());
		dto.setLicenceValidityDate(entity.getLicenceValidityDate());
		dto.setLicenseType(entity.getLicenseType());
		dto.setInspectionReportCopy(inspectionReportCopy);
		dto.setInspectionDate(entity.getInspectionDate());
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsEEApproved(entity.getIsEEApproved());
		dto.setIsCEApproved(entity.getIsCEApproved());
		entity.setLoadDefault(true);
		return dto;
	}
}
