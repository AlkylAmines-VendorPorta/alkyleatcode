package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.DocumentTypeDto;
import com.novelerp.appbase.master.entity.DocumentType;
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
public class DocumentTypeConverter extends CustomDozerConverter<DocumentType, DocumentTypeDto> implements ObjectConverter<DocumentType, DocumentTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public DocumentTypeDto convertEntityToDto(DocumentType entity, Class<DocumentTypeDto> dto) {
		if(entity == null){
			return null;
		}
		DocumentTypeDto DocumentTypeDto = new DocumentTypeDto();
		DocumentTypeDto.setCode(entity.getCode());
		DocumentTypeDto.setName(entity.getName());
		DocumentTypeDto.setDocumentTypeId(entity.getDocumentTypeId());
		DocumentTypeDto.setCreated(entity.getCreated());
		DocumentTypeDto.setUpdated(entity.getUpdated());
		DocumentTypeDto.setIsActive(entity.getIsActive());
		DocumentTypeDto.setDescription(entity.getDescription());
		/*DocumentTypeDto.setPartner(getParter(entity));*/
		
		return DocumentTypeDto;
	}
	
	private BPartnerDto getParter(DocumentType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
