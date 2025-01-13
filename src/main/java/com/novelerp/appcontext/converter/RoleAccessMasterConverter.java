package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.RoleAccessMaster;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class RoleAccessMasterConverter extends CustomContextDozerConverter<RoleAccessMaster, RoleAccessMasterDto> implements ObjectConverter<RoleAccessMaster, RoleAccessMasterDto>{

	@Override
	public RoleAccessMasterDto convertEntityToDto(RoleAccessMaster entity, Class<RoleAccessMasterDto> dto) {
		if(entity==null){
			return null;
		}
		RoleAccessMasterDto roleAccessMasterDto=new RoleAccessMasterDto();
		
		roleAccessMasterDto.setRoleAccessMasterId(entity.getRoleAccessMasterId());
		if(entity.getRole()!=null)
		{
			RoleDto role=new RoleDto();
			role.setRoleId(entity.getRole().getRoleId());
			role.setName(entity.getRole().getName());
			roleAccessMasterDto.setRole(role);
		}	
		if(entity.getTile()!=null)
		{
			TileMasterDto tileMasterDto=new TileMasterDto();
			tileMasterDto.setTileMasterId(entity.getTile().getTileMasterId());
			tileMasterDto.setCode(entity.getTile().getCode());
			tileMasterDto.setName(entity.getTile().getName());
			tileMasterDto.setParentId(entity.getTile().getParentId());
//			tileMasterDto.setPath(entity.getTile().getPath());
			roleAccessMasterDto.setTile(tileMasterDto);
			
		}
		roleAccessMasterDto.setViewOnly(entity.getViewOnly());
		roleAccessMasterDto.setModifyAccess(entity.getModifyAccess());
		roleAccessMasterDto.setDeleteAccess(entity.getDeleteAccess());

		
		return roleAccessMasterDto;
	}
}
