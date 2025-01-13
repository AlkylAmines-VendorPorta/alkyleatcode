package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TenderBudgetTypeDto;
import com.novelerp.appbase.master.entity.TenderBudgetType;
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
public class TenderBudgetTypeConverter extends CustomDozerConverter<TenderBudgetType, TenderBudgetTypeDto> implements ObjectConverter<TenderBudgetType, TenderBudgetTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public TenderBudgetTypeDto convertEntityToDto(TenderBudgetType entity, Class<TenderBudgetTypeDto> dto) {
		if(entity == null){
			return null;
		}
		TenderBudgetTypeDto TenderBudgetTypeDto = new TenderBudgetTypeDto();
		TenderBudgetTypeDto.setCode(entity.getCode());
		TenderBudgetTypeDto.setName(entity.getName());
		TenderBudgetTypeDto.setTenderBudgetTypeId(entity.getTenderBudgetTypeId());
		TenderBudgetTypeDto.setCreated(entity.getCreated());
		TenderBudgetTypeDto.setUpdated(entity.getUpdated());
		TenderBudgetTypeDto.setIsActive(entity.getIsActive());
		TenderBudgetTypeDto.setDescription(entity.getDescription());
		/*TenderBudgetTypeDto.setPartner(getParter(entity));*/
		
		return TenderBudgetTypeDto;
	}
	
	private BPartnerDto getParter(TenderBudgetType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

