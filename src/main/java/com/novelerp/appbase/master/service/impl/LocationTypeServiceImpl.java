package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.LocationTypeConverter;
import com.novelerp.appbase.master.dao.LocationTypeDao;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class LocationTypeServiceImpl extends AbstractServiceImpl<LocationType, LocationTypeDto> implements LocationTypeService {

	@Autowired
	private LocationTypeDao locationTypeDao;
	
	@Autowired
	private LocationTypeConverter locationTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(LocationTypeServiceImpl.class, locationTypeDao, LocationType.class, LocationTypeDto.class);
		/*setObjectConverter(locationTypeConverter);*/
		setByPassProxy(true);
	}

	@Override
	public List<LocationTypeDto> getLocationTypeList()
	{
		List<LocationTypeDto> LocationTypeList=new ArrayList<LocationTypeDto>();
		List<LocationType> LocationTypeEntity= locationTypeDao.findAll("", "updated desc");
		
		if(!LocationTypeEntity.isEmpty())
		{
			for(LocationType LocationType:LocationTypeEntity)
			{
				LocationTypeDto LocationTypeDto=locationTypeConverter.convertEntityToDto(LocationType, LocationTypeDto.class);
				LocationTypeList.add(LocationTypeDto);
			}
		}
		return LocationTypeList;
	}
	
	/*@Override
	public LocationTypeDto getLocationType(Long entityId)
	{
		LocationTypeDto LocationTypeDto=null;
				
		try
		{
			LocationType LocationType=locationTypeDao.findOne(entityId);
			LocationTypeDto=locationTypeConverter.getDtoFromEntity(LocationType, LocationTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching LocationType" + e.getMessage());
		}
		return LocationTypeDto;
	}*/

	@Override
	@Transactional
	public boolean deleteLocationType(Long locationId) {
		// TODO Auto-generated method stub
		return deleteById(locationId);
	}

	@Override
	public List<LocationTypeDto> getLocationType(Long userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editLocationType(LocationTypeDto LocationTypeDto)
	{
		System.out.println("..LocationTypeServiceImpl-editLocationType()..");
		
		try {
				LocationType LocationType=LocationTypeConverter.getEntityFromDto(LocationTypeDto, LocationType.class);
				LocationTypeDao.update(LocationType);
				return new ResponseDto(AppBaseConstant.SUCCESS," LocationType- "+LocationTypeDto.getName()+" Updated Succesfully ",LocationTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated LocationType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," LocationType- "+LocationTypeDto.getName()+"  Not Updated ",LocationTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteLocationType(Long id)
	{
		System.out.println("..LocationTypeServiceImpl-deleteLocationType()..");
		LocationTypeDto LocationTypeDto = new LocationTypeDto();
		try {
			
			LocationTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," LocationType- "+LocationTypeDto.getName()+" Deleted Succesfully ",LocationTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting LocationType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," LocationType- "+LocationTypeDto.getName()+" Cannot be Deleted",LocationTypeDto.getId());
		}
		
	}*/

}


