package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.MaterialGroupConverter;
import com.novelerp.appbase.master.dao.MaterialGroupDao;
import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.entity.MaterialGroup;
import com.novelerp.appbase.master.service.MaterialGroupService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Service
public class MaterialGroupServiceImpl extends AbstractServiceImpl<MaterialGroup, MaterialGroupDto> implements MaterialGroupService{

	@Autowired
	private MaterialGroupDao materialGroupDao;
	
	@Autowired
	private MaterialGroupConverter materialGroupConverter;
	
	@PostConstruct
	private void init() {
		super.init(MaterialGroupServiceImpl.class, materialGroupDao, MaterialGroup.class, MaterialGroupDto.class);
		setByPassProxy(true);
		
		}
	

	@Override
	public List<MaterialGroupDto> getMaterialGroupList()
	{
		List<MaterialGroupDto> materialGroupList=new ArrayList<MaterialGroupDto>();
		List<MaterialGroup> materialGroup= materialGroupDao.findAll("", "updated desc");
		
		if(!materialGroup.isEmpty())
		{
			for(MaterialGroup materialGrp:materialGroup)
			{
				MaterialGroupDto materialGroupDto=materialGroupConverter.getDtoFromEntity(materialGrp, MaterialGroupDto.class);
				materialGroupList.add(materialGroupDto);
			}
		}
		return materialGroupList;
	}
	
	
	public MaterialGroupDto getMaterialGroup(Long entityId)
	{
		MaterialGroupDto materialgroupDto=null;
				
		try
		{
			MaterialGroup materialGroup=materialGroupDao.findOne(entityId);
			materialgroupDto=materialGroupConverter.getDtoFromEntity(materialGroup, MaterialGroupDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching Material Group " + e.getMessage());
		}
		return materialgroupDto;
	}


	@Override
	@Transactional
	public boolean deleteMaterialGroup(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	@Transactional
	public void removeMaterialGroup(Long id)
	{
		materialGroupDao.deleteById(id);
	}
	/*@Override
	@Transactional
	public ResponseDto addMaterialGroup(MaterialGroupDto materialGroupDto)
	{
		System.out.println("..MaterialGroupServiceImpl-addMaterialGroup()..");
		
		try {
			System.out.println("..MaterialGroupServiceImpl-in try..");
			    MaterialGroup materialGroup=materialGroupConverter.getEntityFromDto(materialGroupDto, MaterialGroup.class);
			    System.out.println("..MaterialGroupServiceImpl-addMaterialGroup().."+materialGroup);
			    materialGroupDao.create(materialGroup);
				
			    return new ResponseDto(AppBaseConstant.SUCCESS," Material Group- "+materialGroupDto.getName()+" Added Succesfully ",materialGroupDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated Material Group " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Material Group- "+materialGroupDto.getName()+"  Not Added ",materialGroupDto.getId());
		}
	}
		
	@Override
	@Transactional
	public ResponseDto editMaterialGroup(MaterialGroupDto materialGroupDto)
	{
		System.out.println("..MaterialGroupServiceImpl-editMaterialGroup()..");
		
		try {
				MaterialGroup materialGroup=materialGroupConverter.getEntityFromDto(materialGroupDto, MaterialGroup.class);
				materialGroupDao.update(materialGroup);
				return new ResponseDto(AppBaseConstant.SUCCESS," Material Group- "+materialGroupDto.getName()+" Updated Succesfully ",materialGroupDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated Material Group " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Material Group- "+materialGroupDto.getName()+"  Not Updated ",materialGroupDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteMaterialGroup(Long id)
	{
		System.out.println("..MaterialGroupServiceImpl-deleteMaterialGroup()..");
		MaterialGroupDto materialGroupDto = new MaterialGroupDto();
		try {
			
			materialGroupDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," Material Group- "+materialGroupDto.getName()+" Deleted Succesfully ",materialGroupDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting Material Group " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Material Group- "+materialGroupDto.getName()+" Cannot be Deleted",materialGroupDto.getId());
		}
		
	}*/
}
