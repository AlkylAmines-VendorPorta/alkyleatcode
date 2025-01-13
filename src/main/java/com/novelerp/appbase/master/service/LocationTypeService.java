package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface LocationTypeService extends CommonService<LocationType, LocationTypeDto> {

	public List<LocationTypeDto> getLocationTypeList();
	/*public LocationTypeDto getLocationType(Long partnerId);*/
	/*public ResponseDto addLocationType(LocationTypeDto dto);
	public ResponseDto editLocationType(LocationTypeDto dto);
	public ResponseDto deleteLocationType(Long id);*/
	public boolean deleteLocationType(Long locationId);
	public List<LocationTypeDto> getLocationType(Long userID);
}
