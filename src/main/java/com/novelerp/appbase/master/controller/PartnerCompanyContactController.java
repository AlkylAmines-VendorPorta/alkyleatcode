package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.converter.CountryConverterPlain;
import com.novelerp.appcontext.converter.DistrictConverterPlain;
import com.novelerp.appcontext.converter.RegionConverterPlain;
import com.novelerp.appcontext.converter.UserDetailConverterLocation;
import com.novelerp.appcontext.converter.UserDetailsConverter;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;

/**
 * @author Aman
 *
 */
@Controller
public class PartnerCompanyContactController {
	
	
	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private ReferenceListService referenceListService;
	@Autowired
	
	@Qualifier("jwtUserContext") 
	private ContextService contextService;

	@Autowired
	private UserDetailsConverter userDetailConverter;

	@Autowired
	private UserDetailConverterLocation userDetailConverterLocation;

	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryConverterPlain countryConverter;

	@Autowired
	private RegionService regionService;
	
	@Autowired
	private RegionConverterPlain regionConverter;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private DistrictConverterPlain districtConverter;

	@Autowired
	private ReferenceListService refListService;
    	
	@RequestMapping(value = "/getCompanyContact/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerCompanyContact(@PathVariable ("partnerId") Long partnerId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId);
		Map<String, String> title = refListService.getReferenceListMap(CoreReferenceConstants.TITLE);
		List<UserDetailsDto> companyContacts = userDetailService.findDtos("getUserDetailsQueryByBP", params, userDetailConverter);
		Map<String,Object> locationTypeParams = AbstractServiceImpl.getParamMap("referenceCode", CoreReferenceConstants.COMPANY_TYPE);
		List<ReferenceListDto> locationTypes =  referenceListService.find("c.referenceCode= :referenceCode", locationTypeParams, null);
		CustomResponseDto response = new CustomResponseDto("companyContacts",companyContacts);
		response.addObject("locationTypes", locationTypes);
		response.addObject("title", title);
		return response;
	}
	
	@RequestMapping(value="/saveCompanyContact", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody UserDetailsDto save(@RequestBody UserDetailsDto dto){
		UserDetailsDto userDetails=contextService.getUserDetails();
		dto.setLocationTypeRef(userDetails.getLocationTypeRef());
		return userDetailService.saveCompanyContact(dto);
	}

	/**
	 * Get list of directors of partner company 
	 * @param partnerId
	 * @return
	 */
	@RequestMapping(value = "/getCompanyDirectors/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerDirectors(@PathVariable ("partnerId") Long partnerId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId);
		 List<UserDetailsDto> directors = 
				 	userDetailService.findDtos("getDirectorDetailsQuery", params, userDetailConverterLocation);
		 
		Map<String,Object> locationTypeParams = AbstractServiceImpl.getParamMap("referenceCode", CoreReferenceConstants.COMPANY_TYPE);
		List<ReferenceListDto> locationTypes =  referenceListService.find("c.referenceCode= :referenceCode", locationTypeParams, null);
		
 		List<CountryDto> countries= countryService.findAll(countryConverter);
 		List<RegionDto> regions = regionService.findAll(regionConverter);
 		List<DistrictDto> districts =  districtService.findAll(districtConverter);		
		
		CustomResponseDto response = new CustomResponseDto("companyDirectors",directors);
		response.addObject("locationTypes", locationTypes);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);

		return response;
	}

	
	/**
	 * Save/update Company Directors details.
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/saveCompanyDirector", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDetailsDto saveDirector(@RequestBody UserDetailsDto dto){
		/*TO DO- Validation*/
		dto.setUpdatedBy(contextService.getUser());
		if(dto.getPartner() == null){
			dto.setPartner(contextService.getPartner());
		}
		dto.setUserDetailType("COMPDIR");
		if(dto != null && dto.getUserDetailsId()!=null){
			return userDetailService.updateWithLocation(dto);
		}else{
			dto.setCreatedBy(contextService.getUser());
			return userDetailService.saveWithLocation(dto);
		}
	}

	/**
	 * delete Company Directors details.
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/deleteCompanyDirector/{companyDirectorId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto deleteDirector(@ModelAttribute("companyDirector") Long companyDirectorId){
		/*TO DO- Validation*/
		boolean deleted =  userDetailService.deleteById(companyDirectorId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}

}
