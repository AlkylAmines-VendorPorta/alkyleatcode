package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.RegistrationTypeConverter;
import com.novelerp.appbase.master.dao.RegistrationTypeDao;
import com.novelerp.appbase.master.dto.RegistrationTypeDto;
import com.novelerp.appbase.master.entity.RegistrationType;
import com.novelerp.appbase.master.service.RegistrationTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class RegistrationTypeServiceImpl extends AbstractServiceImpl<RegistrationType, RegistrationTypeDto> implements RegistrationTypeService {

	@Autowired
	private RegistrationTypeDao registrationTypeDao;
	
	@Autowired
	private RegistrationTypeConverter registrationTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(RegistrationTypeServiceImpl.class, registrationTypeDao, RegistrationType.class, RegistrationTypeDto.class);
		setObjectConverter(registrationTypeConverter);
	}

	@Override
	public List<RegistrationTypeDto> getRegistrationTypeList()
	{
		List<RegistrationTypeDto> RegistrationTypeList=new ArrayList<RegistrationTypeDto>();
		List<RegistrationType> RegistrationTypeEntity= registrationTypeDao.findAll("", "updated desc");
		
		if(!RegistrationTypeEntity.isEmpty())
		{
			for(RegistrationType RegistrationType:RegistrationTypeEntity)
			{
				RegistrationTypeDto RegistrationTypeDto=registrationTypeConverter.getDtoFromEntity(RegistrationType, RegistrationTypeDto.class);
				RegistrationTypeList.add(RegistrationTypeDto);
			}
		}
		return RegistrationTypeList;
	}
	
	@Override
	public RegistrationTypeDto getRegistrationType(Long entityId)
	{
		RegistrationTypeDto RegistrationTypeDto=null;
				
		try
		{
			RegistrationType RegistrationType=registrationTypeDao.findOne(entityId);
			RegistrationTypeDto=registrationTypeConverter.getDtoFromEntity(RegistrationType, RegistrationTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching RegistrationType" + e.getMessage());
		}
		return RegistrationTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editRegistrationType(RegistrationTypeDto RegistrationTypeDto)
	{
		System.out.println("..RegistrationTypeServiceImpl-editRegistrationType()..");
		
		try {
				RegistrationType RegistrationType=RegistrationTypeConverter.getEntityFromDto(RegistrationTypeDto, RegistrationType.class);
				RegistrationTypeDao.update(RegistrationType);
				return new ResponseDto(AppBaseConstant.SUCCESS," RegistrationType- "+RegistrationTypeDto.getName()+" Updated Succesfully ",RegistrationTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated RegistrationType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," RegistrationType- "+RegistrationTypeDto.getName()+"  Not Updated ",RegistrationTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteRegistrationType(Long id)
	{
		System.out.println("..RegistrationTypeServiceImpl-deleteRegistrationType()..");
		RegistrationTypeDto RegistrationTypeDto = new RegistrationTypeDto();
		try {
			
			RegistrationTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," RegistrationType- "+RegistrationTypeDto.getName()+" Deleted Succesfully ",RegistrationTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting RegistrationType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," RegistrationType- "+RegistrationTypeDto.getName()+" Cannot be Deleted",RegistrationTypeDto.getId());
		}
		
	}*/

}


