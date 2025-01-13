package com.novelerp.core.converter;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;

/**
 * Dozer converter for Dto to entity conversion
 * @author Vivek Birdi
 * @param <E>
 * @param <D>
 */
public abstract class DozerConverter<E extends Persistable,D extends BaseDto> implements ObjectConverter <E, D>{

	@Autowired
	protected Mapper mapper;
		
	@Override
	public E getEntityFromDto(D dto,Class<E> entityClass) {
		return mapper.map(dto,entityClass);
	}
	
	@Override
	public D getDtoFromTargetEntity(E entity, Class<D> dtoClass) {
		return mapper.map(entity,dtoClass);
	}
	
	
}
