package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface MaterialSubGroupService extends CommonService<MaterialSubGroup, MaterialSubGroupDto> {

	public List<MaterialSubGroupDto> getMaterialSubGroupList();
	public MaterialSubGroupDto getMaterialSubGroup(Long partnerId);
	/*public ResponseDto addMaterialSubGroup(MaterialSubGroupDto dto);
	public ResponseDto editMaterialSubGroup(MaterialSubGroupDto dto);
	public ResponseDto deleteMaterialSubGroup(Long id);*/
	public List<MaterialSubGroupDto> getSubGroupListByGroupId(Long groupId);
	public boolean deleteMaterialSubGroup(Long id);
}
