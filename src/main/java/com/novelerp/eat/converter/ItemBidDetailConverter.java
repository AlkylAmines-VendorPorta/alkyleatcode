/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.ItemBid;

@Component
public class ItemBidDetailConverter extends CustomContextDozerConverter<ItemBid, ItemBidDto>
		implements ObjectConverter<ItemBid, ItemBidDto> {
		
	@Autowired
	private TahdrMaterialWithUomConverter tahdrMaterialConverter;
	
	@Autowired
	private ItemBidConverter itemBidConverter;
	
	@Autowired
	private TechnicalBidDetailConverter technicalBidConverter;
	
	@Autowired
	private CommercialBidDetailConverter commercialBidConverter;
	
	@Override
	public ItemBidDto convertEntityToDto(ItemBid entity, Class<ItemBidDto> dtoClass) {

		if(entity==null){
			return null;
		}
		ItemBidDto dto=new ItemBidDto();
		
		dto=itemBidConverter.convertEntityToDto(entity, ItemBidDto.class);
		dto.setTahdrMaterial(tahdrMaterialConverter.convertEntityToDto(entity.getTahdrMaterial(), TAHDRMaterialDto.class));
		/*dto.setTechnicalBid(technicalBidConverter.convertEntityToDto(entity.getTechnicalBid(), TechnicalBidDto.class));*/
		/*dto.setCommercialBid(commercialBidConverter.convertEntityToDto(entity.getCommercialBid(), CommercialBidDto.class));*/
		return dto;
	}

}
