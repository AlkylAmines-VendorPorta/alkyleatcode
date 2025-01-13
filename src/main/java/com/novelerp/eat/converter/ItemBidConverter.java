package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.entity.ItemBid;

/**
 * @author Aman Sahu
 *
 */
@Component
public class ItemBidConverter extends CustomDozerConverter<ItemBid, ItemBidDto> implements ObjectConverter<ItemBid, ItemBidDto> {

@Override
public ItemBidDto convertEntityToDto(ItemBid entity, Class<ItemBidDto> dtoClass) {
		if(entity==null){
			return null;
		}
		ItemBidDto dto=new ItemBidDto();
		dto.setItemBidId(entity.getItemBidId());
		return dto;
		}

}

