package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.TaxCategoryConverter;
import com.novelerp.appbase.master.dao.TaxCategoryDao;
import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.entity.TaxCategory;
import com.novelerp.appbase.master.service.TaxCategoryService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Aman
 *
 */
@Service
public class TaxCategoryServiceImpl extends AbstractServiceImpl<TaxCategory, TaxCategoryDto> implements TaxCategoryService {

	@Autowired
	private TaxCategoryDao taxCategoryDao;
	
	@Autowired
	private TaxCategoryConverter taxCategoryConverter;
	
	@PostConstruct
	private void init() {
		super.init(TaxCategoryServiceImpl.class, taxCategoryDao, TaxCategory.class, TaxCategoryDto.class);
		setObjectConverter(taxCategoryConverter);
	}

	@Override
	public List<TaxCategoryDto> getTaxCategoryList(Long entityId)
	{
		
		List<TaxCategoryDto> TaxCategoryList=new ArrayList<TaxCategoryDto>();
		List<TaxCategory> TaxCategoryEntity= taxCategoryDao.findAll("where m_bpartner_id="+entityId, "updated desc");
		
		if(!TaxCategoryEntity.isEmpty())
		{
			for(TaxCategory TaxCategory:TaxCategoryEntity)
			{
				TaxCategoryDto TaxCategoryDto=taxCategoryConverter.getDtoFromEntity(TaxCategory, TaxCategoryDto.class);
				TaxCategoryList.add(TaxCategoryDto);
			}
		}
		return TaxCategoryList;
	}
	
	@Override
	public TaxCategoryDto getTaxCategory(Long entityId)
	{
		TaxCategoryDto TaxCategoryDto=null;
				
		try
		{
			TaxCategory TaxCategory=taxCategoryDao.findOne(entityId);
			TaxCategoryDto=taxCategoryConverter.getDtoFromEntity(TaxCategory, TaxCategoryDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching TaxCategory" + e.getMessage());
		}
		return TaxCategoryDto;
	}

	@Override
	@Transactional
	public boolean deleteTaxCat(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	@Transactional
	public void removeTax(Long id)
	{
		taxCategoryDao.deleteById(id);
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editTaxCategory(TaxCategoryDto TaxCategoryDto)
	{
		System.out.println("..TaxCategoryServiceImpl-editTaxCategory()..");
		
		try {
				TaxCategory TaxCategory=TaxCategoryConverter.getEntityFromDto(TaxCategoryDto, TaxCategory.class);
				TaxCategoryDao.update(TaxCategory);
				return new ResponseDto(AppBaseConstant.SUCCESS," TaxCategory- "+TaxCategoryDto.getName()+" Updated Succesfully ",TaxCategoryDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated TaxCategory " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TaxCategory- "+TaxCategoryDto.getName()+"  Not Updated ",TaxCategoryDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteTaxCategory(Long id)
	{
		System.out.println("..TaxCategoryServiceImpl-deleteTaxCategory()..");
		TaxCategoryDto TaxCategoryDto = new TaxCategoryDto();
		try {
			
			TaxCategoryDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," TaxCategory- "+TaxCategoryDto.getName()+" Deleted Succesfully ",TaxCategoryDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting TaxCategory " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," TaxCategory- "+TaxCategoryDto.getName()+" Cannot be Deleted",TaxCategoryDto.getId());
		}
		
	}*/

}

