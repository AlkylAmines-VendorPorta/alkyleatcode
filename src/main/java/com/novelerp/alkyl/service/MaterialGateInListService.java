package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateInList;

public interface MaterialGateInListService extends CommonService<MaterialGateInList, MaterialGateInListDto>{

	List<MaterialGateInListDto> getGateEntryMaterialByFilter(GateEntryReadDto dto);

}
