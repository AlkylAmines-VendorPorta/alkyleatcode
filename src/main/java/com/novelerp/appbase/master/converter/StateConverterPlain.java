package com.novelerp.appbase.master.converter;

import com.novelerp.appbase.master.dto.StateDto;
import com.novelerp.appbase.master.entity.State;
import com.novelerp.core.converter.CustomDozerConverter;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class StateConverterPlain extends CustomDozerConverter<State, StateDto> {

	@Override
	public StateDto convertEntityToDto(State entity, Class<StateDto> dtoClass) {
		StateDto dto =  new StateDto();
		dto.setStateId(entity.getStateId());
		dto.setName(entity.getName());
		return dto;
	}

}
