package com.novelerp.appcontext.service;


import java.util.List;

import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.core.service.CommonService;


/**
 * 
 * @author vivek.birdi
 *
 */
public interface LocationService  extends CommonService<Location, LocationDto>{
	
	public List<LocationDto> getLocationList();
	
	
}
