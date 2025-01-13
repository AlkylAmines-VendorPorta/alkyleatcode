package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.core.service.CommonService;

public interface GateEntryService extends CommonService<GateEntry, GateEntryDto>{

//	public List<GateEntryDto> getGateEntryByFilter(GateEntryReadDto dto);

	GateEntryDto saveGateEntry(GateEntryDto dto);


}
