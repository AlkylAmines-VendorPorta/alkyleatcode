package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.entity.TileMaster;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface TileMasterService extends CommonService<TileMaster, TileMasterDto> {

	public List<TileMasterDto> getTileMasterList();
	public List<TileMasterDto> getSubTileMasterList();
}