package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.TenderTypeConverter;
import com.novelerp.appbase.master.dao.TenderTypeDao;
import com.novelerp.appbase.master.dto.TenderTypeDto;
import com.novelerp.appbase.master.entity.TenderType;
import com.novelerp.appbase.master.service.TenderTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class TenderTypeServiceImpl extends AbstractServiceImpl<TenderType, TenderTypeDto> implements TenderTypeService {

	@Autowired
	private TenderTypeDao tenderTypeDao;
	
	@Autowired
	private TenderTypeConverter tenderTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(TenderTypeServiceImpl.class, tenderTypeDao, TenderType.class, TenderTypeDto.class);
		setObjectConverter(tenderTypeConverter);
	}

	@Override
	public List<TenderTypeDto> getTenderTypeList()
	{
		List<TenderTypeDto> TenderTypeList=new ArrayList<TenderTypeDto>();
		List<TenderType> TenderTypeEntity= tenderTypeDao.findAll("", "updated desc");
		
		if(!TenderTypeEntity.isEmpty())
		{
			for(TenderType TenderType:TenderTypeEntity)
			{
				TenderTypeDto TenderTypeDto=tenderTypeConverter.getDtoFromEntity(TenderType, TenderTypeDto.class);
				TenderTypeList.add(TenderTypeDto);
			}
		}
		return TenderTypeList;
	}
	
	@Override
	public TenderTypeDto getTenderType(Long entityId)
	{
		TenderTypeDto TenderTypeDto=null;
				
		try
		{
			TenderType TenderType=tenderTypeDao.findOne(entityId);
			TenderTypeDto=tenderTypeConverter.getDtoFromEntity(TenderType, TenderTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching TenderType" + e.getMessage());
		}
		return TenderTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editTenderType(TenderTypeDto TenderTypeDto)
	{
		System.out.println("..TenderTypeServiceImpl-editTenderType()..");
		
		try {
				TenderType TenderType=TenderTypeConverter.getEntityFromDto(TenderTypeDto, TenderType.class);
				TenderTypeDao.update(TenderType);
				return new ResponseDto(AppBaseConstant.SUCCESS," TenderType- "+TenderTypeDto.getName()+" Updated Succesfully ",TenderTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated TenderType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TenderType- "+TenderTypeDto.getName()+"  Not Updated ",TenderTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteTenderType(Long id)
	{
		System.out.println("..TenderTypeServiceImpl-deleteTenderType()..");
		TenderTypeDto TenderTypeDto = new TenderTypeDto();
		try {
			
			TenderTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," TenderType- "+TenderTypeDto.getName()+" Deleted Succesfully ",TenderTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting TenderType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TenderType- "+TenderTypeDto.getName()+" Cannot be Deleted",TenderTypeDto.getId());
		}
		
	}*/

}


