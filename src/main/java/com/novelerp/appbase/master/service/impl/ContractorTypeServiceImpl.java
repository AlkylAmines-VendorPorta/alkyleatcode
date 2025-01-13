package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.ContractorTypeConverter;
import com.novelerp.appbase.master.dao.ContractorTypeDao;
import com.novelerp.appbase.master.dto.ContractorTypeDto;
import com.novelerp.appbase.master.entity.ContractorType;
import com.novelerp.appbase.master.service.ContractorTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class ContractorTypeServiceImpl extends AbstractServiceImpl<ContractorType, ContractorTypeDto> implements ContractorTypeService {

	@Autowired
	private ContractorTypeDao contractorTypeDao;
	
	@Autowired
	private ContractorTypeConverter contractorTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(ContractorTypeServiceImpl.class, contractorTypeDao, ContractorType.class, ContractorTypeDto.class);
		setObjectConverter(contractorTypeConverter);
	}

	@Override
	public List<ContractorTypeDto> getContractorTypeList()
	{
		List<ContractorTypeDto> ContractorTypeList=new ArrayList<ContractorTypeDto>();
		List<ContractorType> ContractorTypeEntity= contractorTypeDao.findAll("", "updated desc");
		
		if(!ContractorTypeEntity.isEmpty())
		{
			for(ContractorType ContractorType:ContractorTypeEntity)
			{
				ContractorTypeDto ContractorTypeDto=contractorTypeConverter.convertEntityToDto(ContractorType, ContractorTypeDto.class);
				ContractorTypeList.add(ContractorTypeDto);
			}
		}
		return ContractorTypeList;
	}
	
	@Override
	public ContractorTypeDto getContractorType(Long entityId)
	{
		ContractorTypeDto ContractorTypeDto=null;
				
		try
		{
			ContractorType ContractorType=contractorTypeDao.findOne(entityId);
			ContractorTypeDto=contractorTypeConverter.getDtoFromEntity(ContractorType, ContractorTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching ContractorType" + e.getMessage());
		}
		return ContractorTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editContractorType(ContractorTypeDto ContractorTypeDto)
	{
		System.out.println("..ContractorTypeServiceImpl-editContractorType()..");
		
		try {
				ContractorType ContractorType=ContractorTypeConverter.getEntityFromDto(ContractorTypeDto, ContractorType.class);
				ContractorTypeDao.update(ContractorType);
				return new ResponseDto(AppBaseConstant.SUCCESS," ContractorType- "+ContractorTypeDto.getName()+" Updated Succesfully ",ContractorTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated ContractorType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," ContractorType- "+ContractorTypeDto.getName()+"  Not Updated ",ContractorTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteContractorType(Long id)
	{
		System.out.println("..ContractorTypeServiceImpl-deleteContractorType()..");
		ContractorTypeDto ContractorTypeDto = new ContractorTypeDto();
		try {
			
			ContractorTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," ContractorType- "+ContractorTypeDto.getName()+" Deleted Succesfully ",ContractorTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting ContractorType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," ContractorType- "+ContractorTypeDto.getName()+" Cannot be Deleted",ContractorTypeDto.getId());
		}
		
	}*/

}


