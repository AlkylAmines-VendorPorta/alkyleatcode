package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateInList;

public interface MaterialGateInListDao extends CommonDao<MaterialGateInList, MaterialGateInListDto>{

	String getmaterialGateEntryDetails(GateEntryReadDto dto);

}
