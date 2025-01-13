package com.novelerp.core.converter;

import com.novelerp.core.dto.CommonDto;
import com.novelerp.core.entity.PO;

/**
 * Custom dozer convert for entity to dto conversion. To provide custom conversion from entity to Dto, <br />
 * extend this class and override public 
 * 
 * abstract D convertEntityToDto(E entity, Class<D> dtoClass) 
 * 
 * @author Vivek Birdi
 * @param <E>
 * @param <D>
 */
public abstract class CustomDozerConverter<E extends PO, D extends CommonDto> extends DozerConverter<E,D> implements ObjectConverter<E, D>{
	
	public abstract D convertEntityToDto(E entity, Class<D> dtoClass);
	
	@Override
	public final D getDtoFromEntity(E entity, Class<D> dtoClass) {
		if(entity ==null){
			return null;
		}
		D dto = convertEntityToDto(entity, dtoClass);
		return dto;
	}
}
