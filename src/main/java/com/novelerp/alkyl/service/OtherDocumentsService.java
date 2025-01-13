package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.dto.OtherDocumentsDto;
import com.novelerp.alkyl.entity.OtherDocuments;
import com.novelerp.core.service.CommonService;

public interface OtherDocumentsService extends CommonService<OtherDocuments, OtherDocumentsDto> {

	public List<OtherDocumentsDto> save(List<OtherDocumentsDto> otherDocuments, KYCDto kyc);

}
