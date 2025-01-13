package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.HolderTypeConverter;
import com.novelerp.appbase.master.dao.HolderTypeDao;
import com.novelerp.appbase.master.dto.HolderTypeDto;
import com.novelerp.appbase.master.entity.HolderType;
import com.novelerp.appbase.master.service.HolderTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class HolderTypeServiceImpl extends AbstractServiceImpl<HolderType, HolderTypeDto> implements HolderTypeService {

	@Autowired
	private HolderTypeDao holderTypeDao;
	
	@Autowired
	private HolderTypeConverter holderTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(HolderTypeServiceImpl.class, holderTypeDao, HolderType.class, HolderTypeDto.class);
		setObjectConverter(holderTypeConverter);
	}

	@Override
	public List<HolderTypeDto> getHolderTypeList()
	{
		List<HolderTypeDto> HolderTypeList=new ArrayList<HolderTypeDto>();
		List<HolderType> HolderTypeEntity= holderTypeDao.findAll("", "updated desc");
		
		if(!HolderTypeEntity.isEmpty())
		{
			for(HolderType HolderType:HolderTypeEntity)
			{
				HolderTypeDto HolderTypeDto=holderTypeConverter.getDtoFromEntity(HolderType, HolderTypeDto.class);
				HolderTypeList.add(HolderTypeDto);
			}
		}
		return HolderTypeList;
	}
	
	@Override
	public HolderTypeDto getHolderType(Long entityId)
	{
		HolderTypeDto HolderTypeDto=null;
				
		try
		{
			HolderType HolderType=holderTypeDao.findOne(entityId);
			HolderTypeDto=holderTypeConverter.getDtoFromEntity(HolderType, HolderTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching HolderType" + e.getMessage());
		}
		return HolderTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editHolderType(HolderTypeDto HolderTypeDto)
	{
		System.out.println("..HolderTypeServiceImpl-editHolderType()..");
		
		try {
				HolderType HolderType=HolderTypeConverter.getEntityFromDto(HolderTypeDto, HolderType.class);
				HolderTypeDao.update(HolderType);
				return new ResponseDto(AppBaseConstant.SUCCESS," HolderType- "+HolderTypeDto.getName()+" Updated Succesfully ",HolderTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated HolderType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," HolderType- "+HolderTypeDto.getName()+"  Not Updated ",HolderTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteHolderType(Long id)
	{
		System.out.println("..HolderTypeServiceImpl-deleteHolderType()..");
		HolderTypeDto HolderTypeDto = new HolderTypeDto();
		try {
			
			HolderTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," HolderType- "+HolderTypeDto.getName()+" Deleted Succesfully ",HolderTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting HolderType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," HolderType- "+HolderTypeDto.getName()+" Cannot be Deleted",HolderTypeDto.getId());
		}
		
	}*/

}


