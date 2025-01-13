package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.DocumentTypeDto;
import com.novelerp.appbase.master.entity.DocumentType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface DocumentTypeService extends CommonService<DocumentType, DocumentTypeDto> {

	public List<DocumentTypeDto> getDocumentTypeList();
	public DocumentTypeDto getDocumentType(Long partnerId);
	/*public ResponseDto addDocumentType(DocumentTypeDto dto);
	public ResponseDto editDocumentType(DocumentTypeDto dto);
	public ResponseDto deleteDocumentType(Long id);*/
}
