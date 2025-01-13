package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.entity.ReferenceList;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * 
 * @author varsha
 *
 */
@Component
public class ReferenceListConverter extends CustomDozerConverter<ReferenceList, ReferenceListDto> implements ObjectConverter<ReferenceList, ReferenceListDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Autowired
	private ReferenceConverter referenceConverter;
	
	@Override
	public ReferenceListDto convertEntityToDto(ReferenceList entity, Class<ReferenceListDto> dtoClass) {
		if(entity == null){
			return null;
		}
		ReferenceListDto referenceListDto=new ReferenceListDto();
		referenceListDto.setReferenceListId(entity.getReferenceListId());
		referenceListDto.setCode(entity.getCode());
		referenceListDto.setDescription(entity.getDescription());
		referenceListDto.setName(entity.getName());
		referenceListDto.setReferenceCode(entity.getReferenceCode());
		referenceListDto.setActionName(entity.getActionName());
		referenceListDto.setReference(referenceConverter.convertEntityToDto(entity.getReference(), ReferenceDto.class));
		/*referenceListDto.setPartner(getPartner(entity));*/
		return referenceListDto;
	}
	private BPartnerDto getPartner(ReferenceList entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}

}