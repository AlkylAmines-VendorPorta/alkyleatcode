package com.novelerp.appbase.master.dao;

import java.util.List;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.entity.TileMaster;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface TileMasterDao extends CommonDao<TileMaster, TileMasterDto> {

	public List<TileMaster> getTileMasterList();

	public String getTilesByRoleIdQuery();
}