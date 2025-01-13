package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.PaymentDetailDao;
import com.novelerp.eat.dao.PaymentReceiptDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;
import com.novelerp.eat.service.PaymentReceiptService;

@Service
public class PaymentReceiptServiceImpl extends AbstractContextServiceImpl<PaymentDetail, PaymentDetailDto>
implements PaymentReceiptService {
	
	@Autowired
	private PaymentReceiptDao paymentReceiptDao;
	
	@Autowired
	private PaymentDetailDao paymentDetailDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	private void init() {
		super.init(PaymentReceiptServiceImpl.class, paymentReceiptDao, PaymentDetail.class, PaymentDetailDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public PaymentDetailDto getPaymentDetailsByPaymentType(Long id, String paymentType) {
		PaymentDetailDto payment = new PaymentDetailDto();
		Map<String, Object> map = new HashMap<>();
		BPartnerDto partner=contextService.getPartner();
		RoleDto roleDto= contextService.getDefaultRole();
		try {
			if(roleDto.getValue().equalsIgnoreCase(AppBaseConstant.ROLE_FINANCE_OPERATOR) || roleDto.getValue().equalsIgnoreCase(AppBaseConstant.ROLE_FINANCE_ADMIN)){
				if(paymentType.equalsIgnoreCase(AppBaseConstant.TENDER_PURCHASE_FEE)
						|| paymentType.equalsIgnoreCase(AppBaseConstant.EMD)){
					map.put("paymentDetailId", id);
					map.put("paymentType", paymentType);
					String queryString = paymentDetailDao.getQueryForPaymentReceipt(paymentType,roleDto.getValue());
					payment=findDtoByQuery(queryString, map);
				 }
				else if(paymentType.equalsIgnoreCase(AppBaseConstant.REGISTRATION_FEE)
						|| paymentType.equalsIgnoreCase(AppBaseConstant.RENEWAL_FEE)){
					map.put("paymentDetailId", id);
					map.put("paymentType", paymentType);
					String queryString = paymentDetailDao.getQueryForPaymentReceipt(paymentType,roleDto.getValue());
					payment=findDtoByQuery(queryString, map);
				 }	
			}
			else{
				if(paymentType.equalsIgnoreCase(AppBaseConstant.TENDER_PURCHASE_FEE)
						|| paymentType.equalsIgnoreCase(AppBaseConstant.EMD)){
					map.put("tahdrId", id);
					map.put("partnerId", partner.getbPartnerId());
					map.put("paymentType", paymentType);
					String queryString = paymentDetailDao.getQueryForPaymentReceipt(paymentType,roleDto.getValue());
					payment=findDtoByQuery(queryString, map);
				 }
				else if(paymentType.equalsIgnoreCase(AppBaseConstant.REGISTRATION_FEE)
						|| paymentType.equalsIgnoreCase(AppBaseConstant.RENEWAL_FEE)){
					map.put("paymentDetailId", id);
					map.put("paymentType", paymentType);
					String queryString = paymentDetailDao.getQueryForPaymentReceipt(paymentType,roleDto.getValue());
					payment=findDtoByQuery(queryString, map);
				 }				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return payment;
	}
}
