/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

@Component
public class PaymentTahdrDetailConverter extends CustomDozerConverter<PaymentDetail, PaymentDetailDto>
		implements ObjectConverter<PaymentDetail, PaymentDetailDto> {

	@Autowired
	private PaymentDetailConverter paymentDetailConverter;
	
	@Autowired
	private TAHDRWithDetailConverter tahdrDetailConverter;
	
	/* (non-Javadoc)
	 * @see com.novelerp.core.converter.CustomDozerConverter#convertEntityToDto(com.novelerp.core.entity.PO, java.lang.Class)
	 */	
	@Override
	public PaymentDetailDto convertEntityToDto(PaymentDetail entity, Class<PaymentDetailDto> dtoClass) {
		if(entity==null){
			return null;
		}
		PaymentDetailDto dto= new PaymentDetailDto();
		dto=paymentDetailConverter.convertEntityToDto(entity, PaymentDetailDto.class);
		dto.setTahdrDetail(tahdrDetailConverter.convertEntityToDto(entity.getTahdrDetail(), TAHDRDetailDto.class));
		dto.setTahdr(dto.getTahdrDetail().getTahdr());// TODO Auto-generated method stub
		return dto;
	}

}
