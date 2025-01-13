package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.FinancialDocumentsConverter;
import com.novelerp.appbase.master.dao.FinancialDocumentsDao;
import com.novelerp.appbase.master.dto.FinancialDocumentsDto;
import com.novelerp.appbase.master.entity.FinancialDocuments;
import com.novelerp.appbase.master.service.FinancialDocumentsService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class FinancialDocumentsServiceImpl extends AbstractServiceImpl<FinancialDocuments, FinancialDocumentsDto> implements FinancialDocumentsService {

	@Autowired
	private FinancialDocumentsDao financialDocumentsDao;
	
	@Autowired
	private FinancialDocumentsConverter financialDocumentsConverter;
	
	@PostConstruct
	private void init() {
		super.init(FinancialDocumentsServiceImpl.class, financialDocumentsDao, FinancialDocuments.class, FinancialDocumentsDto.class);
		setObjectConverter(financialDocumentsConverter);
	}

	@Override
	public List<FinancialDocumentsDto> getFinancialDocumentsList()
	{
		List<FinancialDocumentsDto> FinancialDocumentsList=new ArrayList<FinancialDocumentsDto>();
		List<FinancialDocuments> FinancialDocumentsEntity= financialDocumentsDao.findAll("", "updated desc");
		if(!FinancialDocumentsEntity.isEmpty())
		{
			for(FinancialDocuments FinancialDocuments:FinancialDocumentsEntity)
			{
				FinancialDocumentsDto FinancialDocumentsDto=financialDocumentsConverter.getDtoFromEntity(FinancialDocuments, FinancialDocumentsDto.class);
				FinancialDocumentsList.add(FinancialDocumentsDto);
			}
		}
		return FinancialDocumentsList;
	}
	
	@Override
	public FinancialDocumentsDto getFinancialDocuments(Long entityId)
	{
		FinancialDocumentsDto FinancialDocumentsDto=new FinancialDocumentsDto();
		try
		{
			FinancialDocuments FinancialDocuments=financialDocumentsDao.findOne(entityId);
			FinancialDocumentsDto=financialDocumentsConverter.getDtoFromEntity(FinancialDocuments, FinancialDocumentsDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching FinancialDocuments" + e.getMessage());
		}
		return FinancialDocumentsDto;
	}

}



