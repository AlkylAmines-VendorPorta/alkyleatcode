package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.entity.MaterialGateIn;

public interface MaterialGateInDao extends CommonDao<MaterialGateIn, MaterialGateInDto>{

	String getNewReqNo();

	//String getmaterialGateEntryDetails(GateEntryReadDto dto);

}
