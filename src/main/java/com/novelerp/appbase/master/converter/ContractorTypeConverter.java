package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.ContractorTypeDto;
import com.novelerp.appbase.master.entity.ContractorType;
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
public class ContractorTypeConverter extends CustomDozerConverter<ContractorType, ContractorTypeDto> implements ObjectConverter<ContractorType, ContractorTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public ContractorTypeDto convertEntityToDto(ContractorType entity, Class<ContractorTypeDto> dto) {
		if(entity == null){
			return null;
		}
		ContractorTypeDto ContractorTypeDto = new ContractorTypeDto();
		ContractorTypeDto.setCode(entity.getCode());
		ContractorTypeDto.setName(entity.getName());
		ContractorTypeDto.setContractorTypeId(entity.getContractorTypeId());
		ContractorTypeDto.setCreated(entity.getCreated());
		ContractorTypeDto.setUpdated(entity.getUpdated());
		ContractorTypeDto.setIsActive(entity.getIsActive());
		ContractorTypeDto.setDescription(entity.getDescription());
		/*ContractorTypeDto.setPartner(getParter(entity));*/
		
		return ContractorTypeDto;
	}
	
	private BPartnerDto getParter(ContractorType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}