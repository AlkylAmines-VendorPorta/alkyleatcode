package com.novelerp.alkyl.service;

import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.entity.ASNLineCostCenter;
import com.novelerp.core.service.CommonService;

public interface ASNLIneCostCenterService extends CommonService<ASNLineCostCenter, ASNLineCostCenterDto> {

	ASNLineCostCenterDto saveCostCenter(ASNLineCostCenterDto dto);

	ASNLineCostCenterDto getNewDtoFromOldDto(ASNLineCostCenterDto oldDto, ASNLineCostCenterDto dto);

//	ASNLineCostCenterDto deleteCostCenter(ASNLineCostCenterDto dto);

	//ASNLineCostCenterDto getNewDtoFromOldDto(ASNLineCostCenterDto oldDto, ASNLineCostCenterDto dto);

}
