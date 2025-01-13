package com.novelerp.appcontext.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.entity.RoleAccessMaster;
import com.novelerp.core.service.CommonService;

/** 
 * @author Aman
 *
 */
public interface RoleAccessMasterService extends CommonService<RoleAccessMaster, RoleAccessMasterDto>{
	
	public List<RoleAccessMasterDto> getRoleAccessMasterList();
	public List<RoleAccessMasterDto> getRoleAccessMasterByRoleId(Long roleId);
	public CustomResponseDto saveRoleAccessMaster(List<RoleAccessMasterDto> dto);
	public CustomResponseDto editRoleAccessMaster(List<RoleAccessMasterDto> dto);
	public CustomResponseDto deleteRoleAccessMaster(Long id);
	public Map<Long,String> getTilesByRoleId(Long roleId);
	public Map<String,Object> getAcessTilesByRoleId(Long roleId);
	public List<String> getSubTilesByRoleId(Long roleId,Long tileid);
	public List<String> getSubTilesByRoleId(List<RoleAccessMasterDto> accessTileList,Long tileId);
	public RoleAccessMasterDto getAccessByRoleId(Long roleId,Long tileid);
	public List<TileMasterDto> getTilesDetailListByRoleId(Long roleId);
	
	public Map<Long,String> getWorkFlowsTilesByRoleId(Long roleId);
}
