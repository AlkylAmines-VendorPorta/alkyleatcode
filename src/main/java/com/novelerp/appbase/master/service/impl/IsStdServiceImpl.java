package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.IsStdConverter;
import com.novelerp.appbase.master.dao.IsStdDao;
import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.entity.IsStd;
import com.novelerp.appbase.master.service.IsStdService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class IsStdServiceImpl extends AbstractServiceImpl<IsStd, IsStdDto> implements IsStdService {

	@Autowired
	private IsStdDao isStdDao;
	
	@Autowired
	private IsStdConverter isStdConverter;
	
	@PostConstruct
	private void init() {
		super.init(IsStdServiceImpl.class, isStdDao, IsStd.class, IsStdDto.class);
		setObjectConverter(isStdConverter);
	}

	@Override
	public List<IsStdDto> getIsStdList()
	{
		List<IsStdDto> isStdList=new ArrayList<IsStdDto>();
		/*List<IsStd> isStdEntity= isStdDao.getIsStdList();*/
		List<IsStd> isStdEntity= isStdDao.findAll(""," updated desc");
		
		if(!isStdEntity.isEmpty())
		{
			for(IsStd isStd:isStdEntity)
			{
				IsStdDto isStdDto=isStdConverter.convertEntityToDto(isStd, IsStdDto.class);
				isStdList.add(isStdDto);
			}
		}
		return isStdList;
	}
	
	@Override
	public IsStdDto getIsStd(Long entityId)
	{
		IsStdDto isStdDto=null;
				
		try
		{
			/*List<IsStd> IsStd=isStdDao.getIsStdById(entityId);*/
			IsStd IsStd=isStdDao.findOne(entityId);
			/*if(!IsStd.isEmpty())
			{
				isStdDto=isStdConverter.convertEntityToDto(IsStd.get(0), IsStdDto.class);
			}*/
			isStdDto=isStdConverter.convertEntityToDto(IsStd, IsStdDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching IsStd" + e.getMessage());
		}
		return isStdDto;
	}
	
	
	/*@Override
	@Transactional
	public ResponseDto editIsStd(IsStdDto IsStdDto)
	{
		System.out.println("..IsStdServiceImpl-editIsStd()..");
		
		try {
				IsStd IsStd=IsStdConverter.getEntityFromDto(IsStdDto, IsStd.class);
				IsStdDao.update(IsStd);
				return new ResponseDto(AppBaseConstant.SUCCESS," IsStd- "+IsStdDto.getName()+" Updated Succesfully ",IsStdDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Updated IsStd " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," IsStd- "+IsStdDto.getName()+"  Not Updated ",IsStdDto.getId());
			
		}
		
	}
	
	@Override
	@Transactional
	public ResponseDto deleteIsStd(Long id)
	{
		System.out.println("..IsStdServiceImpl-deleteIsStd()..");
		IsStdDto IsStdDto = new IsStdDto();
		try {
			
			IsStdDao.deleteById(id);
			return new ResponseDto(AppBaseConstant.SUCCESS," IsStd- "+IsStdDto.getName()+" Deleted Succesfully ",IsStdDto.getId());
		} catch (Exception e) {
			
			System.err.println("Error in Deleting IsStd " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE," IsStd- "+IsStdDto.getName()+" Cannot be Deleted",IsStdDto.getId());
		}
		
	}*/

}


