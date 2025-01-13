package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.GateEntryLineDto;
import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.alkyl.entity.GateEntryLine;
import com.novelerp.core.dao.CommonDao;

public interface GateEntryLineDao extends CommonDao<GateEntryLine, GateEntryLineDto>{

	String getGateEntryLineDetails(GateEntryReadDto dto);

	String getRGPGateEntryLineDetails(GateEntryReadDto dto);

	//String getMaterialGateEntryLineDetails(GateEntryReadDto dto);

}
