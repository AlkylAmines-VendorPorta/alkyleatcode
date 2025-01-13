package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * 
 * @author varsha
 *
 */
public interface TAHDRPaymentApprovalDao extends CommonDao<PaymentDetail, PaymentDetailDto>{
	
	public List<PaymentDetailDto> getPaymentApprovalList();
  
}
