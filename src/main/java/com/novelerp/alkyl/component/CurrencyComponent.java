package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.CurrencyDto;
import com.novelerp.appbase.master.service.CurrencyService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Component
public class CurrencyComponent {

	@Autowired
	private CurrencyService currencyService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public CurrencyDto getCurrencyByCode(String currencyCode){
		return currencyService.findDto("getCurrencyByCode", AbstractContextServiceImpl.getParamMap("currencyCode", currencyCode));
	}
	
}
