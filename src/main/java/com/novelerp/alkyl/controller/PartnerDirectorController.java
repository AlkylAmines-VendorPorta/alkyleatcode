package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerDirectorDetailsService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class PartnerDirectorController {

	@Autowired
	private PartnerDirectorDetailsService directorService;
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private VendorRegistrationValidator validator;
	
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private VendorProfileHistoryComponent vendorProfileComponent;
	@PostMapping(value="/saveDirectorDetails")
	public @ResponseBody CustomResponseDto saveDirectorDetails(@RequestBody UserDetailsDto dto){
		CustomResponseDto resp = new CustomResponseDto();
		dto.setUserDetailType(AppBaseConstant.MANAGEMENT_USER);
		Errors errors = new Errors();
		validator.validateDirectorDetails(dto, errors);
		if(errors.getErrorCount()>0){
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("directorDetails", dto);
			return resp;
		}
		if(null!=dto.getUserDetailsId()){
			try{
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("userDetailsId", dto.getUserDetailsId());
				UserDetailsDto oldDto=userDetailService.findDto("getUserDetailsWithID", param);
				vendorProfileComponent.createDirectorHistory(dto,oldDto);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		if(dto.getUserDetailsId()==null){
			dto=directorService.save(dto);
		}else{
			dto=directorService.updateDto(dto);
		}
		resp.addObject("directorDetails", dto);
		return resp;
	}
	
	@PostMapping(value="/getDirectorDetails/{partnerId}")
	public @ResponseBody CustomResponseDto getDirectorDetails(@PathVariable("partnerId") Long partnerId){
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> directorTypeList=refListService.getReferenceListMap(CoreReferenceConstants.DIRECTOR_TYPE);
		List<UserDetailsDto> dtos=directorService.findDtos("getDirectorDetails", AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		resp.addObject("directorDetailsList", dtos);
		resp.addObject("directorTypeList", directorTypeList);
		return resp;
	}
	
	@PostMapping(value="/deleteDirectorDetails/{directorId}")
	public @ResponseBody CustomResponseDto deleteDirectorDetails(@PathVariable("directorId") Long directorUserDetailsId){
		Map<String, Object> propertyMap = AbstractContextServiceImpl.getParamMap("isActive", "N");
		Map<String, Object> whereClauseMap = AbstractContextServiceImpl.getParamMap("userDetailsId", directorUserDetailsId);
		whereClauseMap.put("userDetailType", AppBaseConstant.MANAGEMENT_USER);
		int result = directorService.updateByJpql(propertyMap,whereClauseMap);
		UserDetailsDto director = new UserDetailsDto();
		director.setUserDetailsId(directorUserDetailsId);
		if (result>0) {
			director.setResponse(new ResponseDto(false, "Record deleted"));
		}else{
			director.setResponse(new ResponseDto(true, "Problem in deleting record"));
		}
		return new CustomResponseDto("directorDetails",director);
	}
	
}
