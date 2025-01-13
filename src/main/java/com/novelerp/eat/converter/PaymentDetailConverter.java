package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

@Component
public class PaymentDetailConverter extends CustomContextDozerConverter<PaymentDetail, PaymentDetailDto> {
   
	@Override
	public PaymentDetailDto convertEntityToDto(PaymentDetail entity, Class<PaymentDetailDto> dtoClass) {
		
		if(entity == null){
			return null;
		}
		PaymentDetailDto dto = new PaymentDetailDto();
		dto.setPaymentDetailId(entity.getPaymentDetailId());
		dto.setBankName(entity.getBankName());
		dto.setPaymentMode(entity.getPaymentMode());
		dto.setAmount(entity.getAmount());
		dto.setGst(entity.getGst());
		dto.setBranchName(entity.getBranchName());
		dto.setFaComment(entity.getFaComment());
		dto.setFoComment(entity.getFoComment());
		dto.setIsFAApproved(entity.getIsFAApproved());
		dto.setIsFOApproved(entity.getIsFOApproved());
		dto.setMicrCode(entity.getMicrCode());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setPaymentMode(entity.getPaymentMode());
		dto.setTotal(entity.getTotal());
		dto.setReferenceNo(entity.getReferenceNo());
		return dto;
	}

}
