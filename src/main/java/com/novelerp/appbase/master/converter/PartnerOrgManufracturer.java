package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerTradingItem;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgManufracturer extends CustomDozerConverter<PartnerTradingItem, PartnerTradingItemDto> implements ObjectConverter<PartnerTradingItem, PartnerTradingItemDto>{

	@Override
	public PartnerTradingItemDto convertEntityToDto(PartnerTradingItem entity,
			Class<PartnerTradingItemDto> dtoClass) {
		// TODO Auto-generated method stub
		return null;
	}

}