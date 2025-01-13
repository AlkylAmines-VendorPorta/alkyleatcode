package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.converter.DozerConverter;
import com.novelerp.core.converter.ObjectConverter;

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
public abstract class CustomContextDozerConverter<E extends ContextPO, D extends CommonContextDto> extends DozerConverter<E,D> implements ObjectConverter<E, D>{

	@Autowired
	@Qualifier("USER_CONVERTER")
	private UserConverter userConverter;
	
	@Autowired
	private PartnerConverterPlain partnerConverter;
	
	protected abstract D convertEntityToDto(E entity, Class<D> dtoClass);
	
	@Override
	public final D getDtoFromEntity(E entity, Class<D> dtoClass) {
		if(entity ==null){
			return null;
		}
		D dto = convertEntityToDto(entity, dtoClass);
		loadDefault(entity, dto);
		return dto;
	}
	/**
	 * Load default values of entity into Dto (createdBy, updatedBy, isActive,created, updated)
	 * @param entity
	 * @param dto
	 */
	public final void loadDefault(E entity, D dto){
		
		if(entity == null || dto == null){
			return;
		}	
		if(!(entity.isLoadDefault() || dto.isLoadDefault())){
			return;
		}
		if(!(entity instanceof User)){
			UserDto createdBy =	userConverter.getDtoFromEntity(entity.getCreatedBy(), UserDto.class);
			UserDto updatedBy = userConverter.getDtoFromEntity(entity.getUpdatedBy(), UserDto.class);
			dto.setCreatedBy(createdBy);
			dto.setUpdatedBy(updatedBy);
		}
		
		BPartnerDto partner = partnerConverter.getDtoFromEntity(entity.getPartner(), BPartnerDto.class);
		dto.setPartner(partner);
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setIsActive(entity.getIsActive());
	}

}
