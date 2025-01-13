package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.GateEntryDto;
import com.novelerp.alkyl.entity.GateEntry;
import com.novelerp.core.dao.CommonDao;

public interface GateEntryDao extends CommonDao<GateEntry, GateEntryDto>{

	String getNewReqNo();
//	public String getGateEntryByFilter(GateEntryReadDto dto);

}
