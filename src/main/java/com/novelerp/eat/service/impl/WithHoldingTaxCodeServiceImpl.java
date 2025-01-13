package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.WithHoldingTaxCodeDao;
import com.novelerp.eat.dto.WithHoldingTaxCodeDto;
import com.novelerp.eat.entity.WithHoldingTaxCode;
import com.novelerp.eat.service.WithHoldingTaxCodeService;
@Service
public class WithHoldingTaxCodeServiceImpl extends AbstractContextServiceImpl<WithHoldingTaxCode, WithHoldingTaxCodeDto> implements WithHoldingTaxCodeService {

	@Autowired
	private WithHoldingTaxCodeDao withHoldingTaxCodeDao;
	
	@PostConstruct
	void init(){
		super.init(WithHoldingTaxCodeServiceImpl.class, withHoldingTaxCodeDao, WithHoldingTaxCode.class, WithHoldingTaxCodeDto.class);
		setByPassProxy(true);
	}
}
