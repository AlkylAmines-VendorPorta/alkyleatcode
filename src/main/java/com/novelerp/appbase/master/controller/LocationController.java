package com.novelerp.appbase.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Controller
public class LocationController {
	
	/*@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationConverterPlain locationConverterPlain;*/

	@RequestMapping(value="/locationView", method=RequestMethod.GET)
	public ModelAndView locationView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("locationForm");
		return view;
	}
	
	/*@RequestMapping(value = "/getOfficeLocation/{locationtype}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<LocationDto> getOfficeLocation(@PathVariable("locationtype") String locationtype) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("locationtype", locationtype);
		List<LocationDto> officeLocation = locationService.findDtos("getOfficeLocationByType", params, locationConverterPlain);
		return officeLocation;
	}*/
}
