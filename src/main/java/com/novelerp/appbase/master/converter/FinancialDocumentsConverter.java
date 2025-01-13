package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.FinancialDocumentsDto;
import com.novelerp.appbase.master.entity.FinancialDocuments;
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
public class FinancialDocumentsConverter extends CustomDozerConverter<FinancialDocuments, FinancialDocumentsDto> implements ObjectConverter<FinancialDocuments, FinancialDocumentsDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public FinancialDocumentsDto convertEntityToDto(FinancialDocuments entity, Class<FinancialDocumentsDto> dto) {
		if(entity == null){
			return null;
		}
		FinancialDocumentsDto bidTypeDto = new FinancialDocumentsDto();
		bidTypeDto.setCode(entity.getCode());
		bidTypeDto.setName(entity.getName());
		bidTypeDto.setFinancialDocumentsId(entity.getFinancialDocId());
		bidTypeDto.setCreated(entity.getCreated());
		bidTypeDto.setUpdated(entity.getUpdated());
		bidTypeDto.setIsActive(entity.getIsActive());
		bidTypeDto.setDescription(entity.getDescription());
		/*bidTypeDto.setPartner(getParter(entity));*/
		
		return bidTypeDto;
	}
	
	private BPartnerDto getParter(FinancialDocuments entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}


