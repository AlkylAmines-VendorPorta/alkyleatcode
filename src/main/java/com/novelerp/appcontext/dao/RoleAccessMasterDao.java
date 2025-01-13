package com.novelerp.appcontext.dao;

import java.util.List;

import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.entity.RoleAccessMaster;
import com.novelerp.core.dao.CommonDao;

/** 
 * @author Aman
 *
 */
public interface RoleAccessMasterDao extends CommonDao<RoleAccessMaster,RoleAccessMasterDto>{

	public List<RoleAccessMaster> getUserRoleAccessMastersByRoleId(Long roleId);
	public List<RoleAccessMaster> getSubTilesByRoleId(Long roleId,Long tileId);
	public List<RoleAccessMaster> getAccessByRoleId(Long roleId,Long tileId) ;
	public List<RoleAccessMaster> getTilesByRoleId(Long roleId);
	public String getSubTilesByRoleIdAndTileIDQuery();
	public String getTilesByRoleIdQuery();
	public String getSubTilesForWorkflowQuery();
}
