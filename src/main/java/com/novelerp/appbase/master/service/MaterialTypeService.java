package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialTypeDto;
import com.novelerp.appbase.master.entity.MaterialType;
import com.novelerp.core.service.CommonService;

public interface MaterialTypeService extends CommonService<MaterialType, MaterialTypeDto> {

	public List<MaterialTypeDto> getMaterialTypeList();
	public MaterialTypeDto getMaterialType(Long entityId);
	public boolean deleteMaterialType(Long id);
}