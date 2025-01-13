package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.entity.PartnerItemDrawingDoc;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerItemDrawingDocService extends CommonService<PartnerItemDrawingDoc, PartnerItemDrawingDocDto>{
     public ResponseDto insertDrawingDocByMaterial(PartnerItemDrawingDocDto dto);
     public CustomResponseDto getInfraItemDrawingDoc(Long partnerId,Long infraItemId);
     public boolean resetInfraDrawingDocs(Long infraItemId,Long partnerId);
     public PartnerItemDrawingDocDto saveInfraDrawingDocs(PartnerItemDrawingDocDto itemDoc,PartnerItemDrawingDocDto dto);
}
