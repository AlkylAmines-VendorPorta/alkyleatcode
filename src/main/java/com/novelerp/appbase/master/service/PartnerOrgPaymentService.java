package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerOrgPaymentDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.entity.PartnerOrgPayment;
import com.novelerp.core.service.CommonService;
/**
 * 
 * @author Aman
 */
public interface PartnerOrgPaymentService extends CommonService<PartnerOrgPayment, PartnerOrgPaymentDto>{

	public PartnerOrgPaymentDto getPartnerOrgPayment(Long entityId);
	public ResponseDto updatePartnerOrgPayment(PartnerOrgPaymentDto dto);
	
}