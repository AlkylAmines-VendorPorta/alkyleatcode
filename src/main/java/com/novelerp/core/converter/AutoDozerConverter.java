package com.novelerp.core.converter;

import org.springframework.stereotype.Component;

import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;
/**
 * Dozer converter for entity to dto converter. This converter will use automatic dozer mapping.
 * @author Vivek Birdi
 * @param <E>
 * @param <D>
 */
@Component("AUTO_DOZER_CONVERTER")
public class AutoDozerConverter <E extends Persistable, D extends BaseDto> extends DozerConverter<E,D> implements ObjectConverter<E, D>{

	@Override
	public  D getDtoFromEntity(E entity,Class<D> dtoClass) {
		return mapper.map(entity,dtoClass);
	}

	
}
