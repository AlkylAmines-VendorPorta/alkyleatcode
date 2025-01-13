package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.TenderBudgetTypeConverter;
import com.novelerp.appbase.master.dao.TenderBudgetTypeDao;
import com.novelerp.appbase.master.dto.TenderBudgetTypeDto;
import com.novelerp.appbase.master.entity.TenderBudgetType;
import com.novelerp.appbase.master.service.TenderBudgetTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class TenderBudgetTypeServiceImpl extends AbstractServiceImpl<TenderBudgetType, TenderBudgetTypeDto> implements TenderBudgetTypeService {

	@Autowired
	private TenderBudgetTypeDao tenderBudgetTypeDao;
	
	@Autowired
	private TenderBudgetTypeConverter tenderBudgetTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(TenderBudgetTypeServiceImpl.class, tenderBudgetTypeDao, TenderBudgetType.class, TenderBudgetTypeDto.class);
		setObjectConverter(tenderBudgetTypeConverter);
	}

	@Override
	public List<TenderBudgetTypeDto> getTenderBudgetTypeList()
	{
		List<TenderBudgetTypeDto> TenderBudgetTypeList=new ArrayList<TenderBudgetTypeDto>();
		List<TenderBudgetType> TenderBudgetTypeEntity= tenderBudgetTypeDao.findAll("", "updated desc");
		
		if(!TenderBudgetTypeEntity.isEmpty())
		{
			for(TenderBudgetType TenderBudgetType:TenderBudgetTypeEntity)
			{
				TenderBudgetTypeDto TenderBudgetTypeDto=tenderBudgetTypeConverter.convertEntityToDto(TenderBudgetType, TenderBudgetTypeDto.class);
				TenderBudgetTypeList.add(TenderBudgetTypeDto);
			}
		}
		return TenderBudgetTypeList;
	}
	
	@Override
	public TenderBudgetTypeDto getTenderBudgetType(Long entityId)
	{
		TenderBudgetTypeDto TenderBudgetTypeDto=null;
				
		try
		{
			TenderBudgetType TenderBudgetType=tenderBudgetTypeDao.findOne(entityId);
			TenderBudgetTypeDto=tenderBudgetTypeConverter.convertEntityToDto(TenderBudgetType, TenderBudgetTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching TenderBudgetType" + e.getMessage());
		}
		return TenderBudgetTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editTenderBudgetType(TenderBudgetTypeDto TenderBudgetTypeDto)
	{
		System.out.println("..TenderBudgetTypeServiceImpl-editTenderBudgetType()..");
		
		try {
				TenderBudgetType TenderBudgetType=TenderBudgetTypeConverter.getEntityFromDto(TenderBudgetTypeDto, TenderBudgetType.class);
				TenderBudgetTypeDao.update(TenderBudgetType);
				return new ResponseDto(AppBaseConstant.SUCCESS," TenderBudgetType- "+TenderBudgetTypeDto.getName()+" Updated Succesfully ",TenderBudgetTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated TenderBudgetType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TenderBudgetType- "+TenderBudgetTypeDto.getName()+"  Not Updated ",TenderBudgetTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteTenderBudgetType(Long id)
	{
		System.out.println("..TenderBudgetTypeServiceImpl-deleteTenderBudgetType()..");
		TenderBudgetTypeDto TenderBudgetTypeDto = new TenderBudgetTypeDto();
		try {
			
			TenderBudgetTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," TenderBudgetType- "+TenderBudgetTypeDto.getName()+" Deleted Succesfully ",TenderBudgetTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting TenderBudgetType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TenderBudgetType- "+TenderBudgetTypeDto.getName()+" Cannot be Deleted",TenderBudgetTypeDto.getId());
		}
		
	}*/

}


