package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.PartnerItemTypeTestDetails;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Varsha Patil
 *
 */
public interface PartnerItemTypeTestDetailsService extends CommonService<PartnerItemTypeTestDetails, PartnerItemTypeTestDetailsDto>{
  public ResponseDto insertItemTypeTestByMaterial(PartnerItemTypeTestDetailsDto dto);
  public CustomResponseDto getItemTypeTest(Long partnerId,Long infraItemId);
  public PartnerItemTypeTestDetailsDto saveTypeTest(PartnerItemTypeTestDetailsDto dto,PartnerItemTypeTestDetailsDto typeTest);
  public boolean resetInfraTypeTest(Long infraItemId,Long partnerId);
  
}
