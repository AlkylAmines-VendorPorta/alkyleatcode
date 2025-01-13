package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.MaterialSubgroupWithGroupConverter;
import com.novelerp.appbase.master.dao.MaterialSubGroupDao;
import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.appbase.master.service.MaterialSubGroupService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class MaterialSubGroupServiceImpl extends AbstractServiceImpl<MaterialSubGroup, MaterialSubGroupDto> implements MaterialSubGroupService {

	@Autowired
	private MaterialSubGroupDao materialSubGroupDao;
	
	@Autowired
	private MaterialSubgroupWithGroupConverter materialSubGroupConverter;
	
	@PostConstruct
	private void init() {
		super.init(MaterialSubGroupServiceImpl.class, materialSubGroupDao, MaterialSubGroup.class, MaterialSubGroupDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<MaterialSubGroupDto> getMaterialSubGroupList()
	{
		List<MaterialSubGroupDto> materialSubGroupList=new ArrayList<MaterialSubGroupDto>();
		List<MaterialSubGroup> materialSubGroupEntity= materialSubGroupDao.getMaterialSubGroupList();
		materialSubGroupList=getDtoList(materialSubGroupEntity);
		return materialSubGroupList;
	}
	
	@Override
	public MaterialSubGroupDto getMaterialSubGroup(Long entityId)
	{
		MaterialSubGroupDto materialSubGroupDto=null;
				
		try
		{
			List<MaterialSubGroup> materialSubGroupList=materialSubGroupDao.getMaterialSubGroupById(entityId);
			if(!materialSubGroupList.isEmpty())
			{
			
				materialSubGroupDto=materialSubGroupConverter.getDtoFromEntity(materialSubGroupList.get(0), MaterialSubGroupDto.class);
			}
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching MaterialSubGroup" + e.getMessage());
		}
		return materialSubGroupDto;
	}
	
	@Override
	public List<MaterialSubGroupDto> getSubGroupListByGroupId(Long groupId)
	{
		List<MaterialSubGroupDto> materialSubGroupList=new ArrayList<MaterialSubGroupDto>();
		List<MaterialSubGroup> materialSubGroupEntity= materialSubGroupDao.getSubGroupByGroupId(groupId);
		materialSubGroupList=getDtoList(materialSubGroupEntity);
		return materialSubGroupList;
	}

	@Override
	@Transactional
	public boolean deleteMaterialSubGroup(Long id) {
		
		return deleteById(id);
	}
	
	/*@Override
	@Transactional
	public ResponseDto editMaterialSubGroup(MaterialSubGroupDto MaterialSubGroupDto)
	{
		System.out.println("..MaterialSubGroupServiceImpl-editMaterialSubGroup()..");
		
		try {
				MaterialSubGroup MaterialSubGroup=MaterialSubGroupConverter.getEntityFromDto(MaterialSubGroupDto, MaterialSubGroup.class);
				MaterialSubGroupDao.update(MaterialSubGroup);
				return new ResponseDto(AppBaseConstant.SUCCESS," MaterialSubGroup- "+MaterialSubGroupDto.getName()+" Updated Succesfully ",MaterialSubGroupDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated MaterialSubGroup " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialSubGroup- "+MaterialSubGroupDto.getName()+"  Not Updated ",MaterialSubGroupDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteMaterialSubGroup(Long id)
	{
		System.out.println("..MaterialSubGroupServiceImpl-deleteMaterialSubGroup()..");
		MaterialSubGroupDto MaterialSubGroupDto = new MaterialSubGroupDto();
		try {
			
			MaterialSubGroupDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," MaterialSubGroup- "+MaterialSubGroupDto.getName()+" Deleted Succesfully ",MaterialSubGroupDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting MaterialSubGroup " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialSubGroup- "+MaterialSubGroupDto.getName()+" Cannot be Deleted",MaterialSubGroupDto.getId());
		}
		
	}*/

}

