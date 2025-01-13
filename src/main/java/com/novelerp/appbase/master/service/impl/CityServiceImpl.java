package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.CityDao;
import com.novelerp.appbase.master.dto.CityDto;
import com.novelerp.appbase.master.entity.City;
import com.novelerp.appbase.master.service.CityService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author vivek.birdi
 *
 */
@Service
public class CityServiceImpl  extends AbstractServiceImpl<City, CityDto> implements CityService{

	@Autowired
	private CityDao cityDao;
	
	@PostConstruct
	private void init() {
		super.init(CityServiceImpl.class, cityDao, City.class, CityDto.class);
	}


}
