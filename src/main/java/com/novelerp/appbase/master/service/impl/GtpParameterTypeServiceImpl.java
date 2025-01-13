package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.GtpParameterTypeConverter;
import com.novelerp.appbase.master.dao.GtpParameterTypeDao;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.entity.GtpParameterType;
import com.novelerp.appbase.master.service.GtpParameterTypeService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class GtpParameterTypeServiceImpl extends AbstractContextServiceImpl<GtpParameterType, GtpParameterTypeDto> implements GtpParameterTypeService {

	@Autowired
	private GtpParameterTypeDao gtpParameterTypeDao;
	
	@Autowired
	private GtpParameterTypeConverter gtpParameterTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(GtpParameterTypeServiceImpl.class, gtpParameterTypeDao, GtpParameterType.class, GtpParameterTypeDto.class);
		setObjectConverter(gtpParameterTypeConverter);
	}

	@Override
	public List<GtpParameterTypeDto> getGtpParameterTypeList()
	{
		List<GtpParameterTypeDto> GtpParameterTypeList=new ArrayList<GtpParameterTypeDto>();
		List<GtpParameterType> GtpParameterTypeEntity= gtpParameterTypeDao.findAll("", "updated desc");
		
		if(!GtpParameterTypeEntity.isEmpty())
		{
			for(GtpParameterType GtpParameterType:GtpParameterTypeEntity)
			{
				GtpParameterTypeDto GtpParameterTypeDto=gtpParameterTypeConverter.getDtoFromEntity(GtpParameterType, GtpParameterTypeDto.class);
				GtpParameterTypeList.add(GtpParameterTypeDto);
			}
		}
		return GtpParameterTypeList;
	}
	
	@Override
	public GtpParameterTypeDto getGtpParameterType(Long entityId)
	{
		GtpParameterTypeDto GtpParameterTypeDto=null;
				
		try
		{
			GtpParameterType GtpParameterType=gtpParameterTypeDao.findOne(entityId);
			GtpParameterTypeDto=gtpParameterTypeConverter.getDtoFromEntity(GtpParameterType, GtpParameterTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching GtpParameterType" + e.getMessage());
		}
		return GtpParameterTypeDto;
	}
	
	@Override
	@Transactional
	public CustomResponseDto saveGtpParameterType(GtpParameterTypeDto gtpParameterTypeDto)
	{
		System.out.println("..GtpParameterTypeServiceImpl-saveGtpParameterType()..");
		
		try {
				GtpParameterType gtpParameterType=gtpParameterTypeConverter.getEntityFromDto(gtpParameterTypeDto, GtpParameterType.class);
				/*gtpParameterType.setIsActive(gtpParameterTypeDto.getIsActive().equalsIgnoreCase("on")?"Y":"N");*/
				gtpParameterTypeDao.create(gtpParameterType);
				gtpParameterTypeDto=gtpParameterTypeConverter.getDtoFromEntity(gtpParameterType, GtpParameterTypeDto.class);
				return new CustomResponseDto(gtpParameterTypeDto,"GTP PARAMETER TYPE ADDED SUCCESSFULLY",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated GtpParameterType " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER TYPE NOT ADDED");
			
		}
		
	}
	
	
	@Override
	@Transactional
	public CustomResponseDto editGtpParameterType(GtpParameterTypeDto gtpParameterTypeDto)
	{
		System.out.println("..GtpParameterTypeServiceImpl-editGtpParameterType()..");
		
		try {
			GtpParameterType gtpParameterType=gtpParameterTypeConverter.getEntityFromDto(gtpParameterTypeDto, GtpParameterType.class);
			/*gtpParameterType.setIsActive(gtpParameterTypeDto.getIsActive().equalsIgnoreCase("on")?"Y":"N");*/
			gtpParameterTypeDao.update(gtpParameterType);
			gtpParameterTypeDto=gtpParameterTypeConverter.getDtoFromEntity(gtpParameterType, GtpParameterTypeDto.class);
			return new CustomResponseDto(gtpParameterTypeDto,"GTP PARAMETER TYPE UPDATED SUCCESSFULLY",true);
	} catch (Exception e) {
		System.err.println("Error in Updated GtpParameterType " + e.getMessage());
		return new CustomResponseDto(false,"GTP PARAMETER TYPE NOT UPDATED");
		
	}
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto deleteGtpParameterType(Long id)
	{
		System.out.println("..GtpParameterTypeServiceImpl-deleteGtpParameterType()..");
		try {
			CustomResponseDto responseDto= removeGtpParameterType(id);
			return responseDto;
		} catch (Exception e) {
			
			System.err.println("Error in Deleting GtpParameterType " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER TYPE  IS IN USE");
		}
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto removeGtpParameterType(Long id)
	{
		try {
			
			deleteById(id);
			return new CustomResponseDto(null,"GTP PARAMETER TYPE DELETED SUCCESSFULLY",true);
		} catch (Exception e) {
			
			System.err.println("Error in Deleting GtpParameterType " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER TYPE  NOT DELETED");
		}
	}

}


