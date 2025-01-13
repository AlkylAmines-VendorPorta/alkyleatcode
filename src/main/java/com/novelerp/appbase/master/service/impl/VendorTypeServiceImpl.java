package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.VendorTypeConverter;
import com.novelerp.appbase.master.dao.VendorTypeDao;
import com.novelerp.appbase.master.dto.VendorTypeDto;
import com.novelerp.appbase.master.entity.VendorType;
import com.novelerp.appbase.master.service.VendorTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class VendorTypeServiceImpl extends AbstractServiceImpl<VendorType, VendorTypeDto> implements VendorTypeService {

	@Autowired
	private VendorTypeDao vendorTypeDao;
	
	@Autowired
	private VendorTypeConverter vendorTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(VendorTypeServiceImpl.class, vendorTypeDao, VendorType.class, VendorTypeDto.class);
		setObjectConverter(vendorTypeConverter);
	}

	@Override
	public List<VendorTypeDto> getVendorTypeList()
	{
		List<VendorTypeDto> VendorTypeList=new ArrayList<VendorTypeDto>();
		List<VendorType> VendorTypeEntity= vendorTypeDao.findAll("", "updated desc");
		System.err.println(VendorTypeList.size());
		if(!VendorTypeEntity.isEmpty())
		{
			for(VendorType VendorType:VendorTypeEntity)
			{
				VendorTypeDto VendorTypeDto=vendorTypeConverter.getDtoFromEntity(VendorType, VendorTypeDto.class);
				VendorTypeList.add(VendorTypeDto);
			}
		}
		return VendorTypeList;
	}
	
	@Override
	public VendorTypeDto getVendorType(Long entityId)
	{
		VendorTypeDto VendorTypeDto=new VendorTypeDto();
		try
		{
			VendorType VendorType=vendorTypeDao.findOne(entityId);
			VendorTypeDto=vendorTypeConverter.getDtoFromEntity(VendorType, VendorTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching VendorType" + e.getMessage());
		}
		return VendorTypeDto;
	}

}



