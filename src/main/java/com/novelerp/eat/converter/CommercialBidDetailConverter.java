/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.TaxConverter;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.entity.CommercialBid;

@Component
public class CommercialBidDetailConverter extends CustomContextDozerConverter<CommercialBid, CommercialBidDto>
		implements ObjectConverter<CommercialBid, CommercialBidDto> {

	@Autowired
	private CommercialBidConverter commercialBidConverter; 
	
	@Autowired
	private TaxConverter taxConverter;
	
	@Autowired
	private ItemBidConverter itemBidConverter;
	
	@Override
	public CommercialBidDto convertEntityToDto(CommercialBid entity, Class<CommercialBidDto> dtoClass) {
		if(entity==null){
			return null;
		}
		CommercialBidDto dto=new CommercialBidDto();
		dto=commercialBidConverter.convertEntityToDto(entity, CommercialBidDto.class);
		dto.setTax(taxConverter.convertEntityToDto(entity.getTax(), TaxDto.class));
		/*dto.setItemBid(itemBidConverter.convertEntityToDto(entity.getItemBid(), ItemBidDto.class));*/
		return dto;
	}

}
