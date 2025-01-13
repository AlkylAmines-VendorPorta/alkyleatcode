package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.GateEntryReadDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.MaterialGateInDto;
import com.novelerp.eat.dto.MaterialGateInListDto;
import com.novelerp.eat.entity.MaterialGateIn;

public interface MaterialGateInService extends CommonService<MaterialGateIn, MaterialGateInDto>{

	void updateRejectQty(MaterialGateInDto materialGateInDto);

	void closeMaterialGateIn(MaterialGateInDto materialGateInDto);

	void UpdateMaterialGateIn(MaterialGateInDto materialGateInDto);

	void closedMaterialGateInUpdate(MaterialGateInDto materialGateInDto);

	void closedGateEntryUpdate(MaterialGateInListDto lineDto);



}
