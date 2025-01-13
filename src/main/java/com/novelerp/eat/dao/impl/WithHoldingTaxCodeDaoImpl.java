package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.WithHoldingTaxCodeDao;
import com.novelerp.eat.dto.WithHoldingTaxCodeDto;
import com.novelerp.eat.entity.WithHoldingTaxCode;

@Repository
public class WithHoldingTaxCodeDaoImpl extends AbstractJpaDAO<WithHoldingTaxCode, WithHoldingTaxCodeDto> implements WithHoldingTaxCodeDao{
	@PostConstruct
	public void postConstruct() {
		setClazz(WithHoldingTaxCode.class, WithHoldingTaxCodeDto.class);
	}

}
