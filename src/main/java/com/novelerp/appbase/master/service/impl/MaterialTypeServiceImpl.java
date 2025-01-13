package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.MaterialTypeConverter;
import com.novelerp.appbase.master.dao.MaterialTypeDao;
import com.novelerp.appbase.master.dto.MaterialTypeDto;
import com.novelerp.appbase.master.entity.MaterialType;
import com.novelerp.appbase.master.service.MaterialTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

@Service
public class MaterialTypeServiceImpl extends AbstractServiceImpl<MaterialType, MaterialTypeDto> implements MaterialTypeService {

	@Autowired
	private MaterialTypeDao materialTypeDao;
	
	@Autowired
	private MaterialTypeConverter materialTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(MaterialTypeServiceImpl.class, materialTypeDao, MaterialType.class, MaterialTypeDto.class);
		setByPassProxy(true);
		
	}

	@Override
	public List<MaterialTypeDto> getMaterialTypeList()
	{
		List<MaterialTypeDto> materialTypeList=new ArrayList<MaterialTypeDto>();
		List<MaterialType> materialTypeEntity= materialTypeDao.findAll("", "updated desc");
		
		if(!materialTypeEntity.isEmpty())
		{
			for(MaterialType materialType:materialTypeEntity)
			{
				MaterialTypeDto materialTypeDto=materialTypeConverter.getDtoFromEntity(materialType, MaterialTypeDto.class);
				materialTypeList.add(materialTypeDto);
			}
		}
		return materialTypeList;
	}
	
	
	public MaterialTypeDto getMaterialType(Long entityId)
	{
		MaterialTypeDto materialTypeDto=null;
				
		try
		{
			MaterialType materialType=materialTypeDao.findOne(entityId);
			materialTypeDto=materialTypeConverter.getDtoFromEntity(materialType, MaterialTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching MaterialType" + e.getMessage());
		}
		return materialTypeDto;
	}

	@Override
	@Transactional
	public boolean deleteMaterialType(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	

}

