package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.DistrictConverterPlain;
import com.novelerp.appcontext.dao.DistrictDao;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.entity.District;
import com.novelerp.appcontext.service.DistrictService;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class DistrictServiceImpl extends AbstractContextServiceImpl<District, DistrictDto> implements DistrictService {

	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private DistrictConverterPlain districtConverter;

	@PostConstruct
	public void init() {
		super.init(DistrictServiceImpl.class, districtDao, District.class, DistrictDto.class);
		setByPassProxy(true);
	}
}
