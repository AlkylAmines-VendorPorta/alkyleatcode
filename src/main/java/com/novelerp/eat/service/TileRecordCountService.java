package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appbase.master.dto.TilesRecordCountDto;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;

public interface TileRecordCountService {

	public TilesRecordCountDto getTilesRecordCounts(Map<Long, String> tileList);

	public TilesRecordCountDto getTenderTilesRecordCounts(List<String> tileList);

	public TilesRecordCountDto getTilesRecordCounts(List<RoleAccessMasterDto> tileList);
	
	public TilesRecordCountDto getTilesRecordCountsByTile(List<TileMasterDto> tileList);

	public TilesRecordCountDto getWorkflowTilesRecordCounts(List<RoleAccessMasterDto> list, Map<String, Object> param);

}
