package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.entity.MaterialGroup;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

public interface MaterialGroupService extends CommonService<MaterialGroup, MaterialGroupDto>{
	
	public List<MaterialGroupDto> getMaterialGroupList();
	public MaterialGroupDto getMaterialGroup(Long entityId);
	public boolean deleteMaterialGroup(Long id);
	/*public ResponseDto addMaterialGroup(MaterialGroupDto materialGroupDto);
	public ResponseDto editMaterialGroup(MaterialGroupDto materialGroupDto);
	public ResponseDto deleteMaterialGroup(Long id);*/
	
}
