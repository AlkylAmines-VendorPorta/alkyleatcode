package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.DocumentTypeConverter;
import com.novelerp.appbase.master.dao.DocumentTypeDao;
import com.novelerp.appbase.master.dto.DocumentTypeDto;
import com.novelerp.appbase.master.entity.DocumentType;
import com.novelerp.appbase.master.service.DocumentTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class DocumentTypeServiceImpl extends AbstractServiceImpl<DocumentType, DocumentTypeDto> implements DocumentTypeService {

	@Autowired
	private DocumentTypeDao documentTypeDao;
	
	@Autowired
	private DocumentTypeConverter documentTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(DocumentTypeServiceImpl.class,documentTypeDao, DocumentType.class, DocumentTypeDto.class);
		setObjectConverter(documentTypeConverter);
	}

	@Override
	public List<DocumentTypeDto> getDocumentTypeList()
	{
		List<DocumentTypeDto> DocumentTypeList=new ArrayList<DocumentTypeDto>();
		List<DocumentType> DocumentTypeEntity= documentTypeDao.findAll("", "updated desc");
		
		if(!DocumentTypeEntity.isEmpty())
		{
			for(DocumentType DocumentType:DocumentTypeEntity)
			{
				DocumentTypeDto DocumentTypeDto=documentTypeConverter.getDtoFromEntity(DocumentType, DocumentTypeDto.class);
				DocumentTypeList.add(DocumentTypeDto);
			}
		}
		return DocumentTypeList;
	}
	
	@Override
	public DocumentTypeDto getDocumentType(Long entityId)
	{
		DocumentTypeDto DocumentTypeDto=null;
				
		try
		{
			DocumentType DocumentType=documentTypeDao.findOne(entityId);
			DocumentTypeDto=documentTypeConverter.getDtoFromEntity(DocumentType, DocumentTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching DocumentType" + e.getMessage());
		}
		return DocumentTypeDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editDocumentType(DocumentTypeDto DocumentTypeDto)
	{
		System.out.println("..DocumentTypeServiceImpl-editDocumentType()..");
		
		try {
				DocumentType DocumentType=DocumentTypeConverter.getEntityFromDto(DocumentTypeDto, DocumentType.class);
				DocumentTypeDao.update(DocumentType);
				return new ResponseDto(AppBaseConstant.SUCCESS," DocumentType- "+DocumentTypeDto.getName()+" Updated Succesfully ",DocumentTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated DocumentType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," DocumentType- "+DocumentTypeDto.getName()+"  Not Updated ",DocumentTypeDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteDocumentType(Long id)
	{
		System.out.println("..DocumentTypeServiceImpl-deleteDocumentType()..");
		DocumentTypeDto DocumentTypeDto = new DocumentTypeDto();
		try {
			
			DocumentTypeDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," DocumentType- "+DocumentTypeDto.getName()+" Deleted Succesfully ",DocumentTypeDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting DocumentType " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," DocumentType- "+DocumentTypeDto.getName()+" Cannot be Deleted",DocumentTypeDto.getId());
		}
		
	}*/

}



