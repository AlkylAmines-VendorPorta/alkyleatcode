package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.core.dao.CommonDao;

/**
 *  @author Aman
 */
public interface PaymentTypeDao extends CommonDao<PaymentType, PaymentTypeDto> {

	/**
	 * @return
	 */
	String getPaymentTypeByCode();
	public String getQueryForVenderPayment();
}

