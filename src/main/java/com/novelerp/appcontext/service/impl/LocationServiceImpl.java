package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.converter.LocationConverterPlain;
import com.novelerp.appcontext.dao.LocationDao;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.service.LocationService;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class LocationServiceImpl extends AbstractContextServiceImpl<Location, LocationDto> implements LocationService {

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private LocationConverterPlain locationConverterPlain;
	
	@PostConstruct
	private void init() {
		super.init(LocationServiceImpl.class, locationDao, Location.class, LocationDto.class);
/*		setObjectConverter(locationConverterPlain);*/
		setByPassProxy(true);
	}
	@Override
	public List<LocationDto> getLocationList()
	{
		List<LocationDto> locationDtoList  = new ArrayList<LocationDto>();
		try{
			List<Location> locationList =  locationDao.findAll("", "updated desc");
			if(!locationList.isEmpty())
			{
				for(Location location:locationList)
				{
					LocationDto locationDto = locationConverterPlain.convertEntityToDto(location, LocationDto.class);
					locationDtoList.add(locationDto);
				}
				
			}
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return locationDtoList;
	}
}
