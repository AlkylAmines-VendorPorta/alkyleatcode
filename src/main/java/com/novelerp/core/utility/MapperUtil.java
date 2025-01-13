package com.novelerp.core.utility;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;

/**
 * @author Vivek Birdi
 * */

@Component
public class MapperUtil {

	
	@Autowired
	private Mapper mapper;
	
	public  <E extends Persistable,D extends BaseDto> D convertEntityToDto(E entity,Class<D> dto){		
		return mapper.map(entity,dto);
	}
	
	
	public  <D extends BaseDto,E extends Persistable> E convertDtoToEntity(D dto,Class<E> entity)	{	
		return mapper.map(dto,entity);
	}
	
}
