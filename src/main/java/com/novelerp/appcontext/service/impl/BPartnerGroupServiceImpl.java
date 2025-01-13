package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.BPartnerGroupConverter;
import com.novelerp.appcontext.dao.BPartnerGroupDao;
import com.novelerp.appcontext.dto.BPartnerGroupDto;
import com.novelerp.appcontext.entity.BPartnerGroup;
import com.novelerp.appcontext.service.BPartnerGroupService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/** 
 * @author Aman
 *
 */
@Service
public class BPartnerGroupServiceImpl extends AbstractServiceImpl<BPartnerGroup, BPartnerGroupDto> implements BPartnerGroupService {

	@Autowired
	private BPartnerGroupDao BPartnerGroupDao;
	
	@Autowired
	private BPartnerGroupConverter businessPartnerGroupConverter;
	
	@PostConstruct
	private void init() {
		super.init(BPartnerGroupServiceImpl.class, BPartnerGroupDao, BPartnerGroup.class, BPartnerGroupDto.class);
		setObjectConverter(businessPartnerGroupConverter);
	}

	@Override
	public List<BPartnerGroupDto> getBusinessPartnerGroupList()
	{
		List<BPartnerGroupDto> businessPartnerGroupList=new ArrayList<BPartnerGroupDto>();
		List<BPartnerGroup> businessPartnerGroupEntity= BPartnerGroupDao.findAll("", "updated desc");

		if(!businessPartnerGroupEntity.isEmpty())
		{
			for(BPartnerGroup businessPartnerGroup:businessPartnerGroupEntity)
			{
				BPartnerGroupDto BusinessPartnerGroupDto=businessPartnerGroupConverter.getDtoFromEntity(businessPartnerGroup, BPartnerGroupDto.class);
				businessPartnerGroupList.add(BusinessPartnerGroupDto);
			}
		}
		return businessPartnerGroupList;
	}
	
	@Override
	public BPartnerGroupDto getBusinessPartnerGroup(Long entityId)
	{
		BPartnerGroupDto businessPartnerGroupDto=new BPartnerGroupDto();
		try
		{
			BPartnerGroup businessPartnerGroup=BPartnerGroupDao.findOne(entityId);
			businessPartnerGroupDto=businessPartnerGroupConverter.getDtoFromEntity(businessPartnerGroup, BPartnerGroupDto.class);
		}
		catch(Exception e)
		{
			System.err.println("Error in Fetching BusinessPartnerGroup" + e.getMessage());
		}
		return businessPartnerGroupDto;
	}

}


