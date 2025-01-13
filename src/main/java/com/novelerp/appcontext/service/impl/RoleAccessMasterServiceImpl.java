package com.novelerp.appcontext.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.appcontext.converter.RoleAccessMasterConverter;
import com.novelerp.appcontext.dao.RoleAccessMasterDao;
import com.novelerp.appcontext.dto.RoleAccessMasterDto;
import com.novelerp.appcontext.entity.RoleAccessMaster;
import com.novelerp.appcontext.service.RoleAccessMasterService;

/** 
 * @author Aman
 *
 */
@Service
public class RoleAccessMasterServiceImpl extends AbstractContextServiceImpl<RoleAccessMaster, RoleAccessMasterDto> 
implements RoleAccessMasterService{
	
	@Autowired
	private RoleAccessMasterDao roleAccessMasterDao;
	
	@Autowired
	private RoleAccessMasterConverter roleAccessMasterConverter;
	
	
	@PostConstruct
	private void init() {		
		super.init(RoleAccessMasterServiceImpl.class, roleAccessMasterDao, RoleAccessMaster.class, RoleAccessMasterDto.class);
		setByPassProxy(true);
	}


		@Override
		public List<RoleAccessMasterDto> getRoleAccessMasterByRoleId(Long roleId)
		{
			List<RoleAccessMaster> roleAccessMasterList=roleAccessMasterDao.getUserRoleAccessMastersByRoleId(roleId);
			List<RoleAccessMasterDto> dtoList=new ArrayList<RoleAccessMasterDto>();
			if(!roleAccessMasterList.isEmpty())
			{
				for(RoleAccessMaster RoleAccessMaster:roleAccessMasterList)
				{
					RoleAccessMasterDto roleAccessMasterDto=roleAccessMasterConverter.convertEntityToDto(RoleAccessMaster, RoleAccessMasterDto.class);
					dtoList.add(roleAccessMasterDto);
				}
			}
			return dtoList;
		}
		
		
		@Override
		public List<RoleAccessMasterDto> getRoleAccessMasterList() {
			List<RoleAccessMasterDto> roleAccessMasterList=new ArrayList<RoleAccessMasterDto>();
			List<RoleAccessMaster> roleAccessMasterEntity= roleAccessMasterDao.findAll("", "updated desc");
			
			if(!roleAccessMasterEntity.isEmpty())
			{
				for(RoleAccessMaster roleAccessMaster:roleAccessMasterEntity)
				{
					RoleAccessMasterDto roleAccessMasterDto=roleAccessMasterConverter.convertEntityToDto(roleAccessMaster, RoleAccessMasterDto.class);
					roleAccessMasterList.add(roleAccessMasterDto);
				}
			}
			return roleAccessMasterList;
		}
		
		
		@Override
		@Transactional
		public CustomResponseDto saveRoleAccessMaster(List<RoleAccessMasterDto> roleAccessMasterDto)
		{
			List<RoleAccessMasterDto> resultListDto=new ArrayList<RoleAccessMasterDto>();
			CustomResponseDto customResponse=null;
			if(roleAccessMasterDto!=null)
			{	
					for(RoleAccessMasterDto dto:roleAccessMasterDto)
					{
						try {
								RoleAccessMasterDto resultDto=new RoleAccessMasterDto();
								RoleAccessMaster roleAccessMaster=roleAccessMasterConverter.getEntityFromDto(dto, RoleAccessMaster.class);
								roleAccessMaster= updateEntity(roleAccessMaster);
								resultDto=roleAccessMasterConverter.convertEntityToDto(roleAccessMaster, RoleAccessMasterDto.class);
								resultListDto.add(resultDto);
								customResponse= new CustomResponseDto(resultListDto,"Role Access is saved Successfully",true);
						} catch (Exception e) {
						
							return new CustomResponseDto(false,"Role Access not Saved");
							
						}
					}
					return customResponse;
			}
			else 
				return new CustomResponseDto(false,"Role Access not Saved");
			
		}
		@Override
		@Transactional
		public CustomResponseDto editRoleAccessMaster(List<RoleAccessMasterDto> roleAccessMasterDto)
		{
			List<RoleAccessMasterDto> resultListDto=new ArrayList<RoleAccessMasterDto>();
			CustomResponseDto customResponse=null;
			if(!roleAccessMasterDto.isEmpty())
			{	
					for(RoleAccessMasterDto dto:roleAccessMasterDto)
					{
						try {
								RoleAccessMasterDto responseDto=new RoleAccessMasterDto();
								RoleAccessMaster roleAccessMaster=roleAccessMasterConverter.getEntityFromDto(dto, RoleAccessMaster.class);
								roleAccessMaster= updateEntity(roleAccessMaster);
								responseDto=roleAccessMasterConverter.convertEntityToDto(roleAccessMaster, RoleAccessMasterDto.class);
								resultListDto.add(responseDto);
								customResponse= new CustomResponseDto(resultListDto,"Role Access is updated Successfully",true);
						} catch (Exception e) {
							return new CustomResponseDto(false,"Role Access not Updated");
							
						}
					}
					return customResponse;
			}
			else 
				return new CustomResponseDto(false,"Role Access not Update");
			
		}
		
		@Override
		@Transactional
		public CustomResponseDto deleteRoleAccessMaster(Long id)
		{
			try {
				roleAccessMasterDao.deleteById(id);
					return new CustomResponseDto(true,"Role Access deleted Successfully");
			} catch (Exception e) {
				return new CustomResponseDto(false,"Role Access not deleted");
			}
			
		}
		
		@Override
		public Map<Long,String> getTilesByRoleId(Long roleId){
			List<RoleAccessMasterDto> tileNameList=findDtos("getTilesByRoleId", AbstractContextServiceImpl.getParamMap("roleId", roleId));
			Map<Long,String> tileList=new HashMap<Long,String>();
			if(!tileNameList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:tileNameList){
					if(roleAccessMaster.getTile()!=null && !tileList.containsKey(roleAccessMaster.getTile().getParentTile())
							&& roleAccessMaster.getTile().getParentTile()!=null){
						TileMasterDto tileMaster=roleAccessMaster.getTile().getParentTile();
						String tileName=tileMaster.getName();
						Long tileId=tileMaster.getTileMasterId();
						tileList.put(tileId,tileName);
					}
				}
			}
			return tileList;
		}
		@Override
		public Map<Long,String> getWorkFlowsTilesByRoleId(Long roleId){
			List<RoleAccessMasterDto> tileNameList=findDtos("getWorkFlowsTilesByRoleId", AbstractContextServiceImpl.getParamMap("roleId", roleId));
			Map<Long,String> tileList=new HashMap<Long,String>();
			if(!tileNameList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:tileNameList){
					if(roleAccessMaster.getTile()!=null && !tileList.containsKey(roleAccessMaster.getTile())){
						TileMasterDto tileMaster=roleAccessMaster.getTile();
						String tileName=tileMaster.getName();
						Long tileId=tileMaster.getTileMasterId();
					    tileList.put(tileId,tileName);
					}
				}
			}
			return tileList;
		}
		
		@Override
		public Map<String,Object> getAcessTilesByRoleId(Long roleId){
			Map<String,Object> response=new HashMap<String,Object>();
			List<RoleAccessMasterDto> tileNameList=findDtos("getTilesByRoleId", AbstractContextServiceImpl.getParamMap("roleId", roleId));
			Map<Long,String> tileList=new HashMap<Long,String>();
			if(!tileNameList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:tileNameList){
					if(roleAccessMaster.getTile()!=null && !tileList.containsKey(roleAccessMaster.getTile().getParentTile())
							&& roleAccessMaster.getTile().getParentTile()!=null){
						TileMasterDto tileMaster=roleAccessMaster.getTile().getParentTile();
						String tileName=tileMaster.getName();
						Long tileId=tileMaster.getTileMasterId();
						tileList.put(tileId,tileName);
					}
				}
			}
			response.put("TileNameMap", tileList);
			response.put("AccessTileMap", tileNameList);
			return response;
		}
		
		
		/*public Map<String,Object> getAuthorisedTilesByRoleId(Long roleId)
		{
			List<RoleAccessMaster> tileList=roleAccessMasterDao.getTilesByRoleId(roleId);
			Map<String,Object> roleTileMap=new HashMap<String,Object>();
			if(!tileList.isEmpty())
			{
				for(RoleAccessMaster roleAccessMaster:tileList)
				{
						RoleAccessMasterDto dto=roleAccessMasterConverter.getDtoFromEntity(roleAccessMaster, RoleAccessMasterDto.class);
						roleTileMap.put(dto.getRole().getRoleId()+"_",roleAccessMaster);
				}
			}
			return roleTileMap;
		}*/
		@Override
		public List<String> getSubTilesByRoleId(Long roleId,Long tileId){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("roleId", roleId);
			params.put("tileId", tileId);
			List<RoleAccessMasterDto> tileNameList=findDtos("getSubTilesByRoleId",params);
			List<String> tileList=new ArrayList<String>();
			if(!tileNameList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:tileNameList){
					if(roleAccessMaster.getTile().getParentTile()!=null && !tileList.contains(roleAccessMaster.getTile().getParentTile().getName())){
						String tileName=roleAccessMaster.getTile().getName();
					    tileList.add(tileName);
					}
				}
			}
			return tileList;
		}
		
		@Override
		public List<String> getSubTilesByRoleId(List<RoleAccessMasterDto> accessTileList,Long tileId){
			List<String> tileList=new ArrayList<String>();
			if(!accessTileList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:accessTileList){
					if(roleAccessMaster.getTile().getParentId().compareTo(tileId)==0){
						if(roleAccessMaster.getTile().getParentTile()!=null && !tileList.contains(roleAccessMaster.getTile().getParentTile().getName())){
							String tileName=roleAccessMaster.getTile().getName();
						    tileList.add(tileName);
						}	
					}
				}
			}
			return tileList;
		}
		@Override
		public RoleAccessMasterDto getAccessByRoleId(Long roleId,Long tileId)
		{
			List<RoleAccessMaster> tileNameList=roleAccessMasterDao.getAccessByRoleId(roleId, tileId);
			RoleAccessMasterDto accessDto=null;
			if(!tileNameList.isEmpty())
			{
				for(RoleAccessMaster roleAccessMaster:tileNameList)
				{
					accessDto=roleAccessMasterConverter.convertEntityToDto(roleAccessMaster, RoleAccessMasterDto.class);
				}
			}
			return accessDto;
		}
		@Override
		public List<TileMasterDto> getTilesDetailListByRoleId(Long roleId){
			List<TileMasterDto> dtos = new ArrayList<>();
			List<RoleAccessMasterDto> tileNameList=findDtos("getTilesByRoleId", AbstractContextServiceImpl.getParamMap("roleId", roleId));
			Map<Long,String> tileList=new HashMap<Long,String>();
			if(!tileNameList.isEmpty()){
				for(RoleAccessMasterDto roleAccessMaster:tileNameList){
					if(roleAccessMaster.getTile()!=null && !tileList.containsKey(roleAccessMaster.getTile().getParentTile())
							&& roleAccessMaster.getTile().getParentTile()!=null){
						TileMasterDto tileMaster=roleAccessMaster.getTile().getParentTile();
						dtos.add(tileMaster);
					}
				}
			}
			/*List<RoleAccessMaster> tileNameList=roleAccessMasterDao.getTilesByRoleId(roleId);
			Map<Long,String> tileList=new HashMap<Long,String>();
			if(!tileNameList.isEmpty())
			{
				for(RoleAccessMaster roleAccessMaster:tileNameList)
				{
					if(!tileList.containsKey(roleAccessMaster.getTile().getParentId()))
					{
						TileMasterDto tileMaster=tileMasterService.findDto(roleAccessMaster.getTile().getParentId());
						String tileName=tileMaster.getName();
						Long tileId=tileMaster.getTileMasterId();
					    tileList.put(tileId,tileName);
					    
					    dtos.add(tileMaster);
					}
				}
			}*/
			return dtos;
		}
		
		
		

}

