package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.CountryConverterPlain;
import com.novelerp.appcontext.dao.CountryDao;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.entity.Country;
import com.novelerp.appcontext.service.CountryService;
/**
 * 
 * @author vivek.birdi
 *
 */
@Service
public class CountryServiceImpl extends AbstractContextServiceImpl<Country, CountryDto>  implements CountryService{

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private CountryConverterPlain countryConverter;
	
	@PostConstruct
	private void init() {
		super.init(CountryServiceImpl.class, countryDao,Country.class, CountryDto.class);
		setByPassProxy(true);
	}
}
