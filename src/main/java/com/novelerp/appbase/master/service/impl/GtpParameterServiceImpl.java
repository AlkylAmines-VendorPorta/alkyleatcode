package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.GtpParameterConverter;
import com.novelerp.appbase.master.dao.GtpParameterDao;
import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.appbase.master.service.GtpParameterService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

/** 
 * @author Aman
 *
 */
@Service
public class GtpParameterServiceImpl extends AbstractContextServiceImpl<GtpParameter, GtpParameterDto> implements GtpParameterService {

	@Autowired
	private GtpParameterDao gtpParameterDao;
	
	@Autowired
	private GtpParameterConverter gtpParameterConverter;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	private void init() {
		super.init(GtpParameterServiceImpl.class, gtpParameterDao, GtpParameter.class, GtpParameterDto.class);
		setObjectConverter(gtpParameterConverter);
	}

	@Override
	public List<GtpParameterDto> getGtpParameterList()
	{
		List<GtpParameterDto> gtpParameterList=new ArrayList<GtpParameterDto>();
		List<GtpParameter> gtpParameterEntity= gtpParameterDao.getGtpParameterList();
		
		if(!gtpParameterEntity.isEmpty())
		{
			for(GtpParameter gtpParameter:gtpParameterEntity)
			{
				GtpParameterDto gtpParameterDto=gtpParameterConverter.getDtoFromEntity(gtpParameter, GtpParameterDto.class);
				gtpParameterList.add(gtpParameterDto);
			}
		}
		return gtpParameterList;
	}
	
	@Override
	public List<GtpParameterDto> getGtpParameterList(int pageNumber, int pageSize, String searchColumn, String searchValue,Long id)
	{
		Map<String, Object> param = null;
		String query = gtpParameterDao.getAllGtpParameterQuery(searchColumn, searchValue);
		if(id!=null){
			param= AbstractContextServiceImpl.getParamMap("bPartnerId", id);}
		if (!"none".equalsIgnoreCase(searchValue)) {
			param = AbstractContextServiceImpl.getParamMap("searchValue",
					"%" + searchValue.toUpperCase() + "%");
		} 
		List<GtpParameterDto> gtpParameterList = findDtosByQuery(query, param, pageNumber, pageSize);
		return gtpParameterList;
	}
	
	@Override
	public Long getGtpParameterCount(String searchColumn, String searchValue,Long id){

		long totalCount;
		
			Map<String, Object> materialParams = null;
			if(id!=null){
				materialParams= AbstractContextServiceImpl.getParamMap("bPartnerId", id);}
			String queryString = gtpParameterDao.getAllGtpParameterCountQuery(searchColumn, searchValue);
			if (!"none".equalsIgnoreCase(searchValue)) {
			materialParams = AbstractContextServiceImpl.getParamMap("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, materialParams);
		}else{
			totalCount = getRecordCount();
		}
		return totalCount;
	
	}
	
	@Override
	public GtpParameterDto getGtpParameter(Long entityId)
	{
		GtpParameterDto gtpParameterDto=null;
				
		try
		{
			List<GtpParameter> gtpParameter=gtpParameterDao.getGtpParameterById(entityId);
			if(!gtpParameter.isEmpty())
			{
				gtpParameterDto=gtpParameterConverter.getDtoFromEntity(gtpParameter.get(0), GtpParameterDto.class);
			}
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching GtpParameter" + e.getMessage());
		}
		return gtpParameterDto;
	}
	/*
	@Override
	@Transactional
	public CustomResponseDto saveGtpParameter(GtpParameterDto gtpParameterDto)
	{
		GtpParameterDto dto=null;
		try {
			BPartnerDto partner=contextService.getPartner();	
			gtpParameterDto.setPartner(partner);	
			GtpParameter gtpParameter=gtpParameterConverter.getEntityFromDto(gtpParameterDto, GtpParameter.class);
				gtpParameterDao.create(gtpParameter);
				dto=gtpParameterConverter.getDtoFromEntity(gtpParameter, GtpParameterDto.class);
				return new CustomResponseDto(dto,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Saved Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated GtpParameter " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Not Saved");
			
		}
		
	}
	
	@Override
	@Transactional
	public CustomResponseDto editGtpParameter(GtpParameterDto gtpParameterDto)
	{
		GtpParameterDto dto=null;
		try {
			BPartnerDto partner=contextService.getPartner();	
			gtpParameterDto.setPartner(partner);
				GtpParameter gtpParameter=gtpParameterConverter.getEntityFromDto(gtpParameterDto, GtpParameter.class);
				gtpParameterDao.update(gtpParameter);
				dto=gtpParameterConverter.convertEntityToDto(gtpParameter, GtpParameterDto.class);
				return new CustomResponseDto(dto,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Updated Successfully",true);
		} catch (Exception e) {
			
			System.err.println("Error in Updated GtpParameter " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER-"+gtpParameterDto.getName()+" is Not Updated");
			
		}
	}
	*/
	/*@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto deleteGtpParameter(Long id)
	{
		try {
				removeGtpParameter(id);
				return new CustomResponseDto(true,"GTP PARAMETER is Deleted Successfully");
		} catch (Exception e) {
			
			System.err.println("Error in Deleting GtpParameter " + e.getMessage());
			return new CustomResponseDto(false,"GTP PARAMETER is Not Deleted");
		}
	}*/
	@Transactional
	public void removeGtpParameter(Long id)
	{
		gtpParameterDao.deleteById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public com.novelerp.core.dto.CustomResponseDto copyGTP(List<GtpParameterDto> gtpList,Long materialId){
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		
		MaterialDto material=new MaterialDto();
		material.setMaterialId(materialId);
		if(!CommonUtil.isCollectionEmpty(gtpList)){
			for(GtpParameterDto gtp:gtpList){
				gtp=copyGtpParameter(gtp,material);
			}
			response.addObject("result", true);
			response.addObject("resultMessage", "Gtp Copied Successfully");
			
		}else{
			response.addObject("result", false);
			response.addObject("resultMessage", "Not Gtp Found to copy");
		}
		
		return response;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private GtpParameterDto copyGtpParameter(GtpParameterDto gtp,MaterialDto material){
		gtp.setGtpParameterId(null);
		gtp.setCreatedBy(null);
		gtp.setUpdatedBy(null);
		gtp.setMaterial(material);
		gtp.setIsCopied("Y");
		try{
			gtp=super.save(gtp);
		}catch(Exception ex){
			log.error("Exception while fetching records", ex);
		}
		return gtp;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public com.novelerp.core.dto.CustomResponseDto deleteGTP(Long materialId){
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		int count =gtpParameterDao.deleteCopiedGTP(materialId);
		boolean  result=count>0?true:false;
		response.addObject("result",result);
		return response;
	}

	@Override
	@Transactional
	public boolean deleteGtpParameter(Long id) {
		
			return deleteById(id);
		}
		@Transactional
		public void removeTax(Long id)
		{
			gtpParameterDao.deleteById(id);
		}
}


