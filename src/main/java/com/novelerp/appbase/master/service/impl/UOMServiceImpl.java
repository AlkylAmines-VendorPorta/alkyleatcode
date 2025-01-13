package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.UomConverter;
import com.novelerp.appbase.master.dao.UOMDao;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.appbase.master.service.UOMService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

@Service
public class UOMServiceImpl extends AbstractServiceImpl<UOM, UOMDto> implements UOMService{

	@Autowired
	private UOMDao  uomDao;
	@Autowired
	private UomConverter uomConverter;
	
	@PostConstruct
	public void init(){
		super.init(UOMServiceImpl.class, uomDao, UOM.class, UOMDto.class);
		/*setObjectConverter(uomConverter);*/
		setByPassProxy(true);
	}	
	
	@Override
	@Transactional
	public boolean deleteUOM(Long Id){
		return deleteById(Id);
	}
	
	@Override
	public List<UOMDto> getUomList()
	{
		List<UOMDto> uomList=new ArrayList<UOMDto>();
		List<UOM> uom= uomDao.findAll("", "updated desc");
		
		if(!uom.isEmpty())
		{
			for(UOM UOM:uom)
			{
				UOMDto uomDto=uomConverter.getDtoFromEntity(UOM, UOMDto.class);
				uomList.add(uomDto);
			}
		}
		return uomList;
	}
	public UOMDto getUom(Long entityId)
	{
		UOMDto uomDto=null;
		try
		{
			UOM uom=uomDao.findOne(entityId);
			uomDto=uomConverter.getDtoFromEntity(uom, UOMDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching UOM " + e.getMessage());
		}
		return uomDto;
	}
	@Override
	public List<UOMDto> getSearchedUomList(String word)
	{
		List<UOM> uomList=uomDao.getSearchedUomList(word);
		List<UOMDto>uomDtoList=new ArrayList<UOMDto>();
		if(!uomList.isEmpty())
		{
			for(UOM uom:uomList)
			{
				UOMDto uomDto=uomConverter.getDtoFromEntity(uom, UOMDto.class);
				uomDtoList.add(uomDto);
			}
		}
		return uomDtoList;
	}
}

