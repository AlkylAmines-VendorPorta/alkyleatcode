package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.RegionConverterPlain;
import com.novelerp.appcontext.dao.RegionDao;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.entity.Region;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author vivek.birdi
 *
 */
@Service
public class RegionServiceImpl extends AbstractServiceImpl<Region, RegionDto> implements RegionService{

	@Autowired
	private RegionDao regionDao;
	
	@Autowired
	private RegionConverterPlain regionConverter;
	
	@PostConstruct
	 private void init() {
		init(RegionServiceImpl.class, regionDao, Region.class, RegionDto.class);
		setByPassProxy(true);
	}
}
