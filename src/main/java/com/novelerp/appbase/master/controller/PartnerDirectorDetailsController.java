package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.converter.PartnerCompanyAddressConverter;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.converter.UserDetailConverterLocation;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
/**
 * @author Aman
 * @author Vivek Birdi
 */
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
@Controller
public class PartnerDirectorDetailsController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private PartnerCompanyAddressConverter partnerCompanyAddressConverter;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddress;	
	
	@Autowired
	private DesignationService designationService;	

	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private UserDetailConverterLocation userDetailConverter;
	
	@Autowired
	private ReferenceListService refListService;
			
	@RequestMapping(value = "/getDirectorDetails/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerDirectorDetails(@PathVariable ("partnerId") Long partnerId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		Map<String, String> title = refListService.getReferenceListMap(CoreReferenceConstants.TITLE);
		List<UserDetailsDto> userDetails = userDetailService.findDtos("getDirectorDetailsQuery", params, userDetailConverter);
		Map<String, Object> param= new HashMap<>();
 		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);

 		/*List<DesignationDto> designations = designationService.findAll();*/
 		List<DesignationDto> designations = designationService.find(" isBPDesignation='Y' ", new HashMap<>(), " name asc ");
		CustomResponseDto response  = new CustomResponseDto("userDetails", userDetails);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);
 		response.addObject("designations",designations);
 		response.addObject("title",title);
 		return response;

		
	}
	
	@RequestMapping(value="/saveDirectorDetails", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDetailsDto savePartnerDirectorDetails(@RequestBody UserDetailsDto dto){
        dto.setUserDetailType(AppBaseConstant.MANAGEMENT_USER);
		return userDetailService.saveManagementDetails(dto);
	}
	
	@RequestMapping( value ="/deleteDirector/{directorId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseDto delete (@PathVariable("directorId") Long directorId){
		if(directorId!=null){
		Map<String, Object> param=AbstractServiceImpl.getParamMap("isActive","N");
		int count=userDetailService.updateByJpql(param,"userDetailsId",directorId);
		if(count>0){
			return new ResponseDto(false, "Record Deleted");
		}
		/*boolean deleted = userDetailService.deleteById(directorId);
			if(deleted){
				return new ResponseDto(false, "Record Deleted");
			}
		*/
		}
		return new ResponseDto(true, "Problem in deleting record;");
	}

	/*@RequestMapping(value="/copyDirectorAddress/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto copyAddress (@PathVariable("partnerId") Long partnerId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		LocationDto location = locationService.findDtos("getDefaultAddress", params, locationConverter).get(0);
		CustomResponseDto response  = new CustomResponseDto("location", location);
		return response;
	}*/
	@RequestMapping(value="/copyDirectorAddress/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto copyAddress (@PathVariable("partnerId") Long partnerId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		List<PartnerCompanyAddressDto> location = partnerCompanyAddress.findDtos("getDefaultAddress", params, partnerCompanyAddressConverter);
		CustomResponseDto response  = new CustomResponseDto();
		if(!CommonUtil.isCollectionEmpty(location)){
			response.addObject("location", location.get(0));
		}
		return response;
	}
}
