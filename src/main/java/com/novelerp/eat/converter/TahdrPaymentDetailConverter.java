/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.PaymentTypeConverter;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.PaymentDetail;

@Component
public class TahdrPaymentDetailConverter extends CustomDozerConverter<PaymentDetail, PaymentDetailDto>
		implements ObjectConverter<PaymentDetail, PaymentDetailDto> {


	@Autowired
	private PaymentDetailConverter paymentDetailConverter;
	
	@Autowired
	private PaymentTypeConverter paymentTypeConverter;
	
	@Autowired
	private TAHDRConverter tahdrConverter;
	
	@Autowired
	private DetailConverter detailConverter;
	
	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public PaymentDetailDto convertEntityToDto(PaymentDetail entity, Class<PaymentDetailDto> dtoClass) {
		if(entity==null){
			return null;
		}
		PaymentDetailDto dto =new PaymentDetailDto();
		dto=paymentDetailConverter.convertEntityToDto(entity, PaymentDetailDto.class);
		dto.setTahdrDetail(detailConverter.convertEntityToDto(entity.getTahdrDetail(), TAHDRDetailDto.class));
		dto.setTahdr(tahdrConverter.convertEntityToDto(entity.getTahdr(), TAHDRDto.class));
		dto.setPartner(partnerConverter.convertEntityToDto(entity.getPartner(), BPartnerDto.class));
		dto.setPaymentType(paymentTypeConverter.convertEntityToDto(entity.getPaymentType(), PaymentTypeDto.class));
		return dto;
	}

}
