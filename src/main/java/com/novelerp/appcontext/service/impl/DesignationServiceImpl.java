package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.DesignationConverter;
import com.novelerp.appcontext.converter.DesignationConverterPlain;
import com.novelerp.appcontext.dao.DesignationDao;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Service
public class DesignationServiceImpl extends AbstractServiceImpl<Designation, DesignationDto> implements DesignationService {

	@Autowired
	private DesignationDao designationDao;
	
	@Autowired
	private DesignationConverterPlain designationConverterPlain;
	
	@Autowired
	private DesignationConverter designationConverter;
	
	@PostConstruct
	private void init() {
		super.init(DesignationServiceImpl.class, designationDao, Designation.class, DesignationDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<DesignationDto> getDesignationList()
	{
		List<DesignationDto> designationList=new ArrayList<DesignationDto>();
		List<Designation> designationEntity= designationDao.findAll("", "name");
		
		if(!designationEntity.isEmpty())
		{
			for(Designation designation:designationEntity)
			{
				/*DesignationDto designationDto=designationConverter.getDtoFromEntity(designation, DesignationDto.class);*/
				DesignationDto designationDto=designationConverterPlain.getDtoFromEntity(designation, DesignationDto.class);
				
				designationList.add(designationDto);
			}
		}
		return designationList;
	}
	
	
	public DesignationDto getDesignation(Long entityId)
	{
		DesignationDto designationDto=null;
				
		try
		{
			Designation designation=designationDao.findOne(entityId);
			designationDto=designationConverterPlain.getDtoFromEntity(designation, DesignationDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching Designation" + e.getMessage());
		}
		return designationDto;
	}

	@Override
	@Transactional
	public boolean deleteDesignation(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
	
	/*@Override
	@Transactional
	public ResponseDto addMaterialGroup(MaterialgroupDto materialGroupDto)
	{
		System.out.println("..MaterialGroupServiceImpl-addMaterialGroup()..");
		ResponseDto response=new ResponseDto();
		String flag="";
		String status="";
		
		try {
			    Materialgroup materialGroup=materialGroupConverter.getDtoFromEntity(materialgroup, materialGroupDto);
			    materialGroupDao.create(materialGroup);
				
			    response.setRecordId(materialGroup.getId());
				status="success";
				flag=" Material Group- "+materialGroupDto.getName()+" Added Succesfully ";
		} catch (Exception e) {
			
			System.err.println("Error in adding Material Group " + e.getMessage());
			status="error";
			flag=" Material Group- "+materialGroupDto.getName()+" Not Added ";
			
		}
		response.setResponseMsg(flag);
		response.setsetResponseStatus(status); 
		return response;
	}*/
	/*	
	@Override
	@Transactional
	public ResponseDto editDesignation(DesignationDto designationDto)
	{
		System.out.println("..DesignationServiceImpl-editDesignation()..");
		
		try {
				Designation designation=designationConverter.getEntityFromDto(designationDto, Designation.class);
				designationDao.update(designation);
				return new ResponseDto(AppBaseConstant.SUCCESS," Designation- "+designationDto.getName()+" Updated Succesfully ",designationDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated Designation " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Designation- "+designationDto.getName()+"  Not Updated ",designationDto.getId());
			
		}
		
	}*/
	
	/*@Override
	@Transactional
	public ResponseDto deleteDesignation(Long id)
	{
		System.out.println("..DesignationServiceImpl-deleteDesignation()..");
		DesignationDto designationDto = new DesignationDto();
		try {
			
			designationDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," Designation- "+designationDto.getName()+" Deleted Succesfully ",designationDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting Designation " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," Designation- "+designationDto.getName()+" Cannot be Deleted",designationDto.getId());
		}
		
	}*/

}
