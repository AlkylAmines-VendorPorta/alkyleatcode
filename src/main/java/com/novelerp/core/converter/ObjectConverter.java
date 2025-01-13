package com.novelerp.core.converter;

import com.novelerp.core.dto.BaseDto;
import com.novelerp.core.entity.Persistable;

public interface ObjectConverter <E extends Persistable,D extends BaseDto> {
	/**
	 * Convert entity class Object to Dto class Object <br/>
	 * @param entity - Class implementing com.novelerp.eauction.entity.Persistable (and annotated with javax.persistence.Entity) 
	 * @param dto - class extending com.novelerp.eauction.dto.BaseDto
	 * @return Entity class object if successfully converted, null otherwise
	 */
	public  D getDtoFromEntity(E entity,Class<D> dtoClass);
	
	/**
	 * Convert entity class object to Dto class Object <br/>
	 * @param entity - Class implementing com.novelerp.eauction.entity.Persistable (and annotated with javax.persistence.Entity) 
	 * @param dto - class extending com.novelerp.eauction.dto.BaseDto
	 * @return Entity class object if successfully converted, null otherwise
	 */
	public  D getDtoFromTargetEntity(E entity,Class<D> dtoClass);

	
	/**
	 * Convert Dto class to Entity class <br/>
	 * @param entity - Class implementing com.novelerp.eauction.entity.Persistable (and annotated with javax.persistence.Entity) 
	 * @param dto - class extending com.novelerp.eauction.dto.BaseDto
	 * @return Entity class object if successfully converted, null otherwise
	 */
	public  E getEntityFromDto(D dto,Class<E> entityClass);	
}
