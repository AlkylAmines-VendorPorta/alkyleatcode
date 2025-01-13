package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.converter.PartnerCompanyAddressConverter;
import com.novelerp.appbase.master.converter.PartnerSignatoryConverter;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.master.service.PartnerSignatoryService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerSignatoryValidator;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerSignatoryController {
	
	@Autowired
	private PartnerSignatoryService partnerSignatoryService;
	
	@Autowired
	private PartnerSignatoryConverter partnerSignatoryConverter;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private PartnerCompanyAddressConverter partnerCompanyAddressConverter;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private DesignationService designationService;	
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddress;	
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private PartnerSignatoryValidator signatoryValidator;
	@Autowired
	private AttachmentService attachmentService;
	@RequestMapping(value = "/getSignatoryDetails/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerSignatory(@PathVariable ("partnerId") Long partnerId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		Map<String, String> title = refListService.getReferenceListMap(CoreReferenceConstants.TITLE);
		Map<String, String> holderType = refListService.getReferenceListMap(CoreReferenceConstants.HOLDERTYPE);
		List<PartnerSignatoryDto> partnerSignatories = partnerSignatoryService.findDtos("getPartnerSignatoryQuery", params, partnerSignatoryConverter);
		Map<String, Object> param= new HashMap<>();
		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);
 		List<DesignationDto> designations = designationService.find(" isBPDesignation='Y' ", new HashMap<>(), " name");
		CustomResponseDto response  = new CustomResponseDto("partnerSignatories", partnerSignatories);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);
 		response.addObject("designations",designations);
 		response.addObject("title",title);
 		response.addObject("holderType",holderType);
		return response;
	}
	
	@RequestMapping(value="/saveSignatoryDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerSignatoryDto save(@ModelAttribute ("partnerSignatory") PartnerSignatoryDto dto,HttpServletRequest request){
		RoleDto role=contextService.getDefaultRole();
		dto.getUserDetail().setUserDetailType(AppBaseConstant.SIGNATORY_USER);
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			Errors errors = new Errors();
			signatoryValidator.validate(dto, errors,request);
			if(errors.getErrorCount()>0){
				ResponseDto response =  new ResponseDto(true, "Request has validation errors, look for error List",
						errors.getErrorList());
				dto.setResponse(response);
				return dto;
			}
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
	  return partnerSignatoryService.saveSignatory(dto,role);
	}
	
	@RequestMapping(value="/deletePartnerSignatory/{partnerSignatoryId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete (@PathVariable("partnerSignatoryId") Long partnerSignatoryId){
	
		if(partnerSignatoryId!=null){
			Map<String,Object> map=AbstractServiceImpl.getParamMap("isActive","N");
			int count=partnerSignatoryService.updateByJpql(map,"partnerSignatoryId",partnerSignatoryId);
			if(count>0)
			{
				return new ResponseDto(false, "Record Deleted");	
			}
		}
		/*boolean deleted  = partnerSignatoryService.deleteById(partnerSignatoryId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");	
		}else*/
			return new ResponseDto(true, "Problem in deleting record");
	}

	@RequestMapping(value="/copyAddress/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto copyAddress (@PathVariable("partnerId") Long partnerId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		List<PartnerCompanyAddressDto> location = partnerCompanyAddress.findDtos("getDefaultAddress", params, partnerCompanyAddressConverter);
		CustomResponseDto response  = new CustomResponseDto();
		if(!CommonUtil.isCollectionEmpty(location)){
		  response.addObject("location", location.get(0));
		}
		return response;
	}
	@RequestMapping(value="/downloadTestFile", method=RequestMethod.POST)
	public void downloadTestFile(HttpServletRequest request,HttpServletResponse response){
		attachmentService.downloadTestFile(request,response);
	}
}
