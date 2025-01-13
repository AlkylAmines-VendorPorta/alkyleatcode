package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.MaterialSpecificationConverter;
import com.novelerp.appbase.master.dao.MaterialSpecificationDao;
import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.appbase.master.service.MaterialSpecificationService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class MaterialSpecificationServiceImpl extends AbstractContextServiceImpl<MaterialSpecification, MaterialSpecificationDto> implements MaterialSpecificationService {

	@Autowired
	private MaterialSpecificationDao materialSpecificationDao;
	
	@Autowired
	private MaterialSpecificationConverter materialSpecificationConverter;
	
	@PostConstruct
	private void init() {
		super.init(MaterialSpecificationServiceImpl.class, materialSpecificationDao, MaterialSpecification.class, MaterialSpecificationDto.class);
		setObjectConverter(materialSpecificationConverter);
		setByPassProxy(true);
	}

	@Override
	public List<MaterialSpecificationDto> getMaterialSpecificationList()
	{
		List<MaterialSpecificationDto> MaterialSpecificationList=new ArrayList<MaterialSpecificationDto>();
		List<MaterialSpecification> MaterialSpecificationEntity= materialSpecificationDao.getMaterialSpecificationList();
		
		if(!MaterialSpecificationEntity.isEmpty())
		{
			for(MaterialSpecification MaterialSpecification:MaterialSpecificationEntity)
			{
				MaterialSpecificationDto MaterialSpecificationDto=materialSpecificationConverter.convertEntityToDto(MaterialSpecification, MaterialSpecificationDto.class);
				MaterialSpecificationList.add(MaterialSpecificationDto);
			}
		}
		return MaterialSpecificationList;
	}
	
	@Override
	public MaterialSpecificationDto getMaterialSpecification(Long entityId)
	{
		MaterialSpecificationDto MaterialSpecificationDto=null;
				
		try
		{
			List<MaterialSpecification> materialSpecificationList=materialSpecificationDao.getMaterialSpecificationListById(entityId);
			if(!materialSpecificationList.isEmpty())
			{
				MaterialSpecificationDto=materialSpecificationConverter.convertEntityToDto(materialSpecificationList.get(0), MaterialSpecificationDto.class);
			}
			
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching MaterialSpecification" + e.getMessage());
		}
		return MaterialSpecificationDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editMaterialSpecification(MaterialSpecificationDto MaterialSpecificationDto)
	{
		System.out.println("..MaterialSpecificationServiceImpl-editMaterialSpecification()..");
		
		try {
				MaterialSpecification MaterialSpecification=MaterialSpecificationConverter.getEntityFromDto(MaterialSpecificationDto, MaterialSpecification.class);
				MaterialSpecificationDao.update(MaterialSpecification);
				return new ResponseDto(AppBaseConstant.SUCCESS," MaterialSpecification- "+MaterialSpecificationDto.getName()+" Updated Succesfully ",MaterialSpecificationDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated MaterialSpecification " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialSpecification- "+MaterialSpecificationDto.getName()+"  Not Updated ",MaterialSpecificationDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteMaterialSpecification(Long id)
	{
		System.out.println("..MaterialSpecificationServiceImpl-deleteMaterialSpecification()..");
		MaterialSpecificationDto MaterialSpecificationDto = new MaterialSpecificationDto();
		try {
			
			MaterialSpecificationDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," MaterialSpecification- "+MaterialSpecificationDto.getName()+" Deleted Succesfully ",MaterialSpecificationDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting MaterialSpecification " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," MaterialSpecification- "+MaterialSpecificationDto.getName()+" Cannot be Deleted",MaterialSpecificationDto.getId());
		}
		
	}*/

}


