package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.PaymentTermDao;
import com.novelerp.appbase.master.dto.PaymentTermDto;
import com.novelerp.appbase.master.entity.PaymentTerm;
import com.novelerp.appbase.master.service.PaymentTermService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class PaymentTermServiceImpl extends AbstractServiceImpl<PaymentTerm, PaymentTermDto> implements PaymentTermService{

	@Autowired
	private PaymentTermDao paymentTermDao;
	
	@PostConstruct
	private void init() {
		super.init(PaymentTermServiceImpl.class, paymentTermDao, PaymentTerm.class, PaymentTermDto.class);
	}
}
