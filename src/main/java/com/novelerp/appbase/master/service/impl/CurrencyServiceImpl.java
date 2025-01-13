package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.CurrencyDao;
import com.novelerp.appbase.master.dto.CurrencyDto;
import com.novelerp.appbase.master.entity.Currency;
import com.novelerp.appbase.master.service.CurrencyService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class CurrencyServiceImpl extends AbstractServiceImpl<Currency, CurrencyDto> implements CurrencyService {
	
	@Autowired
	private CurrencyDao currencyDao;
	
	@PostConstruct
	private void init() {
		super.init(CurrencyServiceImpl.class, currencyDao, Currency.class,CurrencyDto.class);

	}

}
