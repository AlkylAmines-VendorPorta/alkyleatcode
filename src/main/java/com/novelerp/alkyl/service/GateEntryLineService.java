package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.alkyl.entity.GateEntryLine;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;

public interface GateEntryLineService extends CommonService<GateEntryLine, GateEntryLineDto>{

	void saveLines(GateEntryDto dto, List<GateEntryLineDto> gateEntryLineDto);

	void updateGateIn(GateEntryDto gateEntryDto);

	List<GateEntryLineDto> getGateEntryByFilter(GateEntryReadDto dto);

	List<GateEntryLineDto> getGateEntryRGPByFilter(GateEntryReadDto dto);

	//List<GateEntryLineDto> getMaterialGateEntryByFilter(GateEntryReadDto dto);

}
