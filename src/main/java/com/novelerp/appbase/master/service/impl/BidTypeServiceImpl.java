package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.converter.BidTypeConverter;
import com.novelerp.appbase.master.dao.BidTypeDao;
import com.novelerp.appbase.master.dto.BidTypeDto;
import com.novelerp.appbase.master.entity.BidType;
import com.novelerp.appbase.master.service.BidTypeService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/** 
 * @author Aman
 *
 */
@Service
public class BidTypeServiceImpl extends AbstractServiceImpl<BidType, BidTypeDto> implements BidTypeService {

	@Autowired
	private BidTypeDao bidTypeDao;
	
	@Autowired
	private BidTypeConverter bidTypeConverter;
	
	@PostConstruct
	private void init() {
		super.init(BidTypeServiceImpl.class, bidTypeDao, BidType.class, BidTypeDto.class);
		setObjectConverter(bidTypeConverter);
	}

	@Override
	public List<BidTypeDto> getBidTypeList()
	{
		List<BidTypeDto> BidTypeList=new ArrayList<BidTypeDto>();
		List<BidType> BidTypeEntity= bidTypeDao.findAll("", "updated desc");
		if(!BidTypeEntity.isEmpty())
		{
			for(BidType BidType:BidTypeEntity)
			{
				BidTypeDto BidTypeDto=bidTypeConverter.getDtoFromEntity(BidType, BidTypeDto.class);
				BidTypeList.add(BidTypeDto);
			}
		}
		return BidTypeList;
	}
	
	@Override
	public BidTypeDto getBidType(Long entityId)
	{
		BidTypeDto BidTypeDto=new BidTypeDto();
		try
		{
			BidType BidType=bidTypeDao.findOne(entityId);
			BidTypeDto=bidTypeConverter.getDtoFromEntity(BidType, BidTypeDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching BidType" + e.getMessage());
		}
		return BidTypeDto;
	}

}


