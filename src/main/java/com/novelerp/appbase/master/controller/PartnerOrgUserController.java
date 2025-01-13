package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.converter.PartnerOrgConverter;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PartnerOrgUserService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
/**
 * @author Vivek Birdi
 *
 */
@Controller
public class PartnerOrgUserController {
	
	@Autowired
	private PartnerOrgUserService partnerOrgUserService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@Autowired
	private PartnerOrgConverter partnerOrgConverter;
		
	@RequestMapping(value = "/getOrgUser/{orgId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrgUser(@PathVariable("orgId")Long orgId){

		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", orgId);
		Map<String, String> title = refListService.getReferenceListMap(CoreReferenceConstants.TITLE);
		List<PartnerOrgUserDto> orgUsers = partnerOrgUserService.findDtos("getOrgUserByOrgQuery", params);
		Map<String, Object> param= new HashMap<>();
 		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);

        /*List<DesignationDto> designations=designationService.findAll(designationConverter);*/
 		List<DesignationDto> designations = designationService.find(" isBPDesignation='Y' ", new HashMap<>(), " name asc ");
		CustomResponseDto response =  new CustomResponseDto("partnerOrgUsers", orgUsers);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);
 		response.addObject("designations",designations);
 		response.addObject("title",title);
 		return response;
	}
	
	@RequestMapping(value="/saveOrgUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgUserDto savePartnerOrgUser(@ModelAttribute("partnerOrgUser") PartnerOrgUserDto dto){
		dto.getUserDetail().setUserDetailType(AppBaseConstant.FACTORY_USER);
		return partnerOrgUserService.saveOrgUser(dto);
	}
	
	@RequestMapping(value="/deleteOrgUser/{orgUserId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("orgUserId") Long orgUserId){
		boolean deleted = partnerOrgUserService.deleteById(orgUserId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true,"Problem in deleting record");
	}

	@RequestMapping(value="/copyFactoryAddress/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto copyAddress (@PathVariable("partnerOrgId") Long partnerOrgId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgDto> orgAddress = partnerOrgService.findDtos("getDefaultFactoryAddress", params, partnerOrgConverter);
		CustomResponseDto response  = new CustomResponseDto();
		if(!CommonUtil.isCollectionEmpty(orgAddress)){
			response.addObject("orgAddress", orgAddress.get(0));
		}
		return response;
	}

}
