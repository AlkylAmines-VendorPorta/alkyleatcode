package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TAHDRPaymentApprovalDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * 
 * @author varsha
 *
 */
@Repository
public class TAHDRPaymentApprovalDaoImpl extends AbstractJpaDAO<PaymentDetail, PaymentDetailDto>implements TAHDRPaymentApprovalDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(PaymentDetail.class, PaymentDetailDto.class);
	}

	@Override
	public List<PaymentDetailDto> getPaymentApprovalList() {
		
		return null;
	}
	
	

	
}
