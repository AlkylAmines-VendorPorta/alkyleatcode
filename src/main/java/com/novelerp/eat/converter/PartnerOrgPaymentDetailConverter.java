package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.PartnerOrgConverter;
import com.novelerp.appbase.master.converter.PaymentTypeConverter;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * 
 * @author varsha
 *
 */
@Component
public class PartnerOrgPaymentDetailConverter extends CustomDozerConverter<PaymentDetail, PaymentDetailDto> implements  ObjectConverter<PaymentDetail, PaymentDetailDto> {
    @Autowired
	private PaymentTypeConverter paymentTypeConverter;
    @Autowired
    private PartnerOrgConverter partnerOrgConverter;
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
		dto.setPartnerOrg(partnerOrgConverter.convertEntityToDto(entity.getPartnerOrg(), PartnerOrgDto.class));
		dto.setPaymentType(paymentTypeConverter.convertEntityToDto(entity.getPaymentType(), PaymentTypeDto.class));
		return dto;
	}

}
