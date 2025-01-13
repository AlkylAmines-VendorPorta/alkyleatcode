package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.CompanyTypeConverter;
import com.novelerp.appbase.master.dao.CompanyTypeDao;
import com.novelerp.appbase.master.dto.CompanyTypeDto;
import com.novelerp.appbase.master.entity.CompanyType;
import com.novelerp.appbase.master.service.CompanyTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Aman
 *
 */
@Service
public class CompanyTypeServiceImpl extends AbstractServiceImpl<CompanyType, CompanyTypeDto> implements CompanyTypeService {

	@Autowired
	private CompanyTypeDao companyTypeDao;
	
	@Autowired
	private CompanyTypeConverter companyTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(CompanyTypeServiceImpl.class, companyTypeDao, CompanyType.class, CompanyTypeDto.class);
		setObjectConverter(companyTypeConverter);
	}

	@Override
	public List<CompanyTypeDto> getCompanyTypeList()
	{
		List<CompanyTypeDto> CompanyTypeList=new ArrayList<CompanyTypeDto>();
		List<CompanyType> CompanyTypeEntity= companyTypeDao.findAll("", "updated desc");
		
		if(!CompanyTypeEntity.isEmpty())
		{
			for(CompanyType CompanyType:CompanyTypeEntity)
			{
				CompanyTypeDto CompanyTypeDto=companyTypeConverter.getDtoFromEntity(CompanyType, CompanyTypeDto.class);
				CompanyTypeList.add(CompanyTypeDto);
			}
		}
		return CompanyTypeList;
	}
	
	
	public CompanyTypeDto getCompanyType(Long entityId)
	{
		CompanyTypeDto CompanyTypeDto=null;
				
		try
		{
			CompanyType CompanyType=companyTypeDao.findOne(entityId);
			CompanyTypeDto=companyTypeConverter.getDtoFromEntity(CompanyType, CompanyTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching CompanyType" + e.getMessage());
		}
		return CompanyTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editCompanyType(CompanyTypeDto CompanyTypeDto)
	{
		System.out.println("..CompanyTypeServiceImpl-editCompanyType()..");
		
		try {
				CompanyType CompanyType=CompanyTypeConverter.getEntityFromDto(CompanyTypeDto, CompanyType.class);
				CompanyTypeDao.update(CompanyType);
				return new ResponseDto(AppBaseConstant.SUCCESS," CompanyType- "+CompanyTypeDto.getName()+" Updated Succesfully ",CompanyTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated CompanyType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," CompanyType- "+CompanyTypeDto.getName()+"  Not Updated ",CompanyTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteCompanyType(Long id)
	{
		System.out.println("..CompanyTypeServiceImpl-deleteCompanyType()..");
		CompanyTypeDto CompanyTypeDto = new CompanyTypeDto();
		try {
			
			CompanyTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," CompanyType- "+CompanyTypeDto.getName()+" Deleted Succesfully ",CompanyTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting CompanyType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," CompanyType- "+CompanyTypeDto.getName()+" Cannot be Deleted",CompanyTypeDto.getId());
		}
		
	}*/

}

