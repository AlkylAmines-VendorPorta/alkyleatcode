package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.PaymentReceiptDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

@Repository
public class PaymentReceiptDaoImpl extends AbstractJpaDAO<PaymentDetail, PaymentDetailDto> implements PaymentReceiptDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(PaymentDetail.class, PaymentDetailDto.class);
	}
}
