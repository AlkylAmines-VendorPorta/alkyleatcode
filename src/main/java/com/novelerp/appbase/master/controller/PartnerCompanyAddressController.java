package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * @author Aman
 *
 */
@Controller
@RequestMapping("/rest")
public class PartnerCompanyAddressController {

	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private VendorRegistrationValidator validator;

	@Autowired
	private VendorProfileHistoryComponent vendorProfileComponent;
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private BPartnerComponent bPartnerComponent;
	
	@RequestMapping(value = "/getCompanyAddressInfo/{partnerId}", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerCompanyAddress(@PathVariable("partnerId") Long partnerId) {

		Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerId", partnerId);
		/*
		 * List<PartnerCompanyAddressDto> orgs =
		 * partnerCompanyAddressService.findDtos("getAddressQuery", params);
		 */
		params.put("locationType", AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
		List<PartnerCompanyAddressDto> orgs = partnerCompanyAddressService.findDtos("getAddressQueryByLocation",
				params);
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> paramforregion = AbstractServiceImpl.getParamMap("countryId", 1l);
		List<CountryDto> countries = countryService.findDtos("getCountryQuery", param);
		List<RegionDto> regions = regionService.findDtos("getRegionBycountry", paramforregion);
		List<DistrictDto> districts = districtService.findDtos("getDistrictQuery", param);

		CustomResponseDto response = new CustomResponseDto("companyAddressList", orgs);
		response.addObject("countries", countries);
		response.addObject("regions", regions);
		response.addObject("districts", districts);
		return response;

	}

	@RequestMapping(value = "/saveCompanyAddress", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PartnerCompanyAddressDto save(@RequestBody PartnerCompanyAddressDto dto) {
		dto.setLocationType(AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
		Errors errors = new Errors();
		validator.validateCompanyAddress(dto, errors);
		if (errors.getErrorCount() > 0) {
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			return dto;
		}
		if(null!=dto.getPartnerCompanyAddressId()){
		try{
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", dto.getPartnerCompanyAddressId());
			PartnerCompanyAddressDto oldDto=partnerCompanyAddressService.findDto("getAddressQueryByID", param);
			vendorProfileComponent.createCompanyADDHistory(dto,oldDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// Update partial status of vendor
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
						AbstractContextServiceImpl.getParamMap("partnerId", dto.getPartner().getbPartnerId()));
		bPartnerComponent.setVendorPartialdetails(oldDto);
		partnerService.updateDto(oldDto);
		return partnerCompanyAddressService.savePartnerAddress(dto);
	}

	@RequestMapping(value = { "/saveAssociateCompanyDetails" }, method = RequestMethod.POST)
	public @ResponseBody PartnerCompanyAddressDto saveAssosiateContactDetails(
			@RequestBody PartnerCompanyAddressDto dto) {
		// System.out.println("Controller hit");
		dto.setLocationType(AppBaseConstant.LOCATION_TYPE_ASSOCIATE_COMP_ADDR);
		Errors errors = new Errors();
		validator.validateCompanyAddress(dto, errors);
		if (errors.getErrorCount() > 0) {
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			return dto;
		}
		return partnerCompanyAddressService.savePartnerAddress(dto);
	}

	@RequestMapping(value = "/getAssociateCompAddrInfo/{partnerId}", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getAssociateCompAddrInfo(@PathVariable("partnerId") Long partnerId) {

		Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerId", partnerId);
		/*
		 * List<PartnerCompanyAddressDto> orgs =
		 * partnerCompanyAddressService.findDtos("getAddressQuery", params);
		 */
		params.put("locationType", AppBaseConstant.LOCATION_TYPE_ASSOCIATE_COMP_ADDR);
		List<PartnerCompanyAddressDto> orgs = partnerCompanyAddressService.findDtos("getAddressQueryByLocation",
				params);
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> paramforregion = AbstractServiceImpl.getParamMap("countryId", 1l);
		List<CountryDto> countries = countryService.findDtos("getCountryQuery", param);
		List<RegionDto> regions = regionService.findDtos("getRegionBycountry", paramforregion);
		List<DistrictDto> districts = districtService.findDtos("getDistrictQuery", param);

		CustomResponseDto response = new CustomResponseDto();
		response.addObject("countries", countries);
		response.addObject("regions", regions);
		response.addObject("districts", districts);
		if (!CommonUtil.isCollectionEmpty(orgs)) {
			response.addObject("associateCompanyDetailsList", orgs);
		} else {
			response.addObject("associateCompanyDetailsList", null);
		}
		return response;

	}

	@RequestMapping(value = "/deleteCompanyAddress/{partnerCompanyAddressId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto delete(
			@PathVariable("partnerCompanyAddressId") Long partnerCompanyAddressId) {
		int result = partnerCompanyAddressService.updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", "N"),
				AbstractContextServiceImpl.getParamMap("partnerCompanyAddressId", partnerCompanyAddressId));

		PartnerCompanyAddressDto partnerAddress = new PartnerCompanyAddressDto();
		partnerAddress.setPartnerCompanyAddressId(partnerCompanyAddressId);
		if (result > 0) {
			partnerAddress.setResponse(new ResponseDto(false, "Record deleted"));
		} else {
			partnerAddress.setResponse(new ResponseDto(true, "Problem in deleting record"));
		}
		return new CustomResponseDto("partnerCompanyAddr", partnerAddress);
	}
	
	
	
	

}
