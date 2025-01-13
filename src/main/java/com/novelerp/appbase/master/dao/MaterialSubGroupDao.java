package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface MaterialSubGroupDao extends CommonDao<MaterialSubGroup, MaterialSubGroupDto> {

	public List<MaterialSubGroup> getMaterialSubGroupList();
	public List<MaterialSubGroup> getMaterialSubGroupById(Long id);
	public List<MaterialSubGroup> getSubGroupByGroupId(Long id);
}
