package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.ExemptedCategoriesConverter;
import com.novelerp.appbase.master.dao.ExemptedCategoriesDao;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.appbase.master.entity.ExemptedCategories;
import com.novelerp.appbase.master.service.ExemptedCategoriesService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class ExemptedCategoriesServiceImpl extends AbstractContextServiceImpl<ExemptedCategories, ExemptedCategoriesDto> implements ExemptedCategoriesService {

	@Autowired
	private ExemptedCategoriesDao exemptedCategoriesDao;
	
	@Autowired
	private ExemptedCategoriesConverter exemptedCategoriesConverter;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contexService;
	@PostConstruct
	private void init() {
		super.init(ExemptedCategoriesServiceImpl.class, exemptedCategoriesDao, ExemptedCategories.class, ExemptedCategoriesDto.class);
		setObjectConverter(exemptedCategoriesConverter);
	}

	@Override
	public List<ExemptedCategoriesDto> getExemptedCategoriesList()
	{
		List<ExemptedCategoriesDto> ExemptedCategoriesList=new ArrayList<ExemptedCategoriesDto>();
		List<ExemptedCategories> ExemptedCategoriesEntity= exemptedCategoriesDao.findAll("", "updated desc");
		System.err.println(ExemptedCategoriesList.size());
		if(!ExemptedCategoriesEntity.isEmpty())
		{
			for(ExemptedCategories ExemptedCategories:ExemptedCategoriesEntity)
			{
				ExemptedCategoriesDto ExemptedCategoriesDto=exemptedCategoriesConverter.getDtoFromEntity(ExemptedCategories, ExemptedCategoriesDto.class);
				ExemptedCategoriesList.add(ExemptedCategoriesDto);
			}
		}
		return ExemptedCategoriesList;
	}
	
	@Override
	public ExemptedCategoriesDto getExemptedCategories(Long entityId)
	{
		ExemptedCategoriesDto ExemptedCategoriesDto=new ExemptedCategoriesDto();
		try
		{
			ExemptedCategories ExemptedCategories=exemptedCategoriesDao.findOne(entityId);
			ExemptedCategoriesDto=exemptedCategoriesConverter.getDtoFromEntity(ExemptedCategories, ExemptedCategoriesDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching ExemptedCategories" + e.getMessage());
		}
		return ExemptedCategoriesDto;
	}
	
	@Override
	@Transactional
	public CustomResponseDto saveExemptedCategories(ExemptedCategoriesDto exemptedCategoriesDto)
	{
		System.out.println("..ExemptedCategoriesServiceImpl-saveExemptedCategories()..");
		
		try {
				BPartnerDto partner=contexService.getPartner();
				exemptedCategoriesDto.setPartner(partner);
				ExemptedCategories exemptedCategories=exemptedCategoriesConverter.getEntityFromDto(exemptedCategoriesDto, ExemptedCategories.class);
				exemptedCategoriesDao.create(exemptedCategories);
				exemptedCategoriesDto=exemptedCategoriesConverter.getDtoFromEntity(exemptedCategories, ExemptedCategoriesDto.class);
				return new CustomResponseDto(exemptedCategoriesDto,"Exempted Categories ADDED SUCCESSFULLY",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated ExemptedCategories " + e.getMessage());
			return new CustomResponseDto(false,"Exempted Categories NOT ADDED");
			
		}
		
	}
	
	
	@Override
	@Transactional
	public CustomResponseDto editExemptedCategories(ExemptedCategoriesDto exemptedCategoriesDto)
	{
		System.out.println("..ExemptedCategoriesServiceImpl-editExemptedCategories()..");
		try {
			BPartnerDto partner=contexService.getPartner();
			exemptedCategoriesDto.setPartner(partner);
			ExemptedCategories exemptedCategories=exemptedCategoriesConverter.getEntityFromDto(exemptedCategoriesDto, ExemptedCategories.class);
			exemptedCategoriesDao.update(exemptedCategories);
			exemptedCategoriesDto=exemptedCategoriesConverter.getDtoFromEntity(exemptedCategories, ExemptedCategoriesDto.class);
			return new CustomResponseDto(exemptedCategoriesDto,"Exempted Categories UPDATED SUCCESSFULLY",true);
	} catch (Exception e) {
		
		System.err.println("Error in Updated ExemptedCategories " + e.getMessage());
		return new CustomResponseDto(false,"Exempted Categories NOT UPDATED");
		
	}
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto deleteExemptedCategories(Long id)
	{
		System.out.println("..ExemptedCategoriesServiceImpl-deleteExemptedCategories()..");
		try {
			
			removeExemptedCategories(id);
			return new CustomResponseDto(null,"Exempted Categories DELETED SUCCESSFULLY",true);
		} catch (Exception e) {
			
			System.err.println("Error in Deleting ExemptedCategories " + e.getMessage());
			return new CustomResponseDto(false,"Exempted Categories  NOT DELETED");
		}
		
	}

	@Transactional
	public void removeExemptedCategories(Long id)
	{
		exemptedCategoriesDao.deleteById(id);
	}
}



