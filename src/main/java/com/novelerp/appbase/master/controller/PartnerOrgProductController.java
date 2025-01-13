package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * @author Vivek Birdi
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.service.PartnerOrgBISService;
import com.novelerp.appbase.master.service.PartnerOrgProductService;
import com.novelerp.appbase.master.service.PartnerOrgRegistrationService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.master.service.UOMService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgProductValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
/**
 * 
 * @author Vivek Birdi
 *
 */
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
@Controller
public class PartnerOrgProductController {

	@Autowired
	private PartnerOrgProductService partnerOrgProductService;
	
	@Autowired
	private UOMService uomService;
	@Autowired
	private ReferenceListService refListService;
	@Autowired
	private PartnerOrgBISService partnerOrgBISService;
	
	@Autowired
	private PartnerOrgRegistrationService partnerOrgRegistrationService;
	@Autowired
	private PartnerOrgProductValidator partnerOrgProductValidator;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@RequestMapping(value="/getPartnerOrgProducts/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getOrgProducts(@PathVariable("partnerOrgId") Long partnerOrgId){

		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgProductDto> partnerOrgProducts = partnerOrgProductService.findDtos("getOrgProductQuery", params);
		Map<String, String> registrationTypes = refListService.getReferenceListMap(CoreReferenceConstants.EXEMP_REG_TYPE);
		List<PartnerOrgBISDto> orgBIS=partnerOrgBISService.findDtos("getPartnerOrgBISQuery", params);
		
 		List<PartnerOrgRegistrationDto> partnerOrgRegistrations = partnerOrgRegistrationService.findDtos("getPartnerOrgRegQueryForProduct", params);
		CustomResponseDto response = new CustomResponseDto("partnerOrgProducts",partnerOrgProducts);
		List<UOMDto> uomlist=uomService.getUomList();
		response.addObject("orgBIS", orgBIS);
		response.addObject("uom",uomlist );
		response.addObject("registrationTypes", registrationTypes);
		response.addObject("partnerOrgRegistrations", partnerOrgRegistrations);
		return response;
	}
	
	@RequestMapping(value="/savePartnerOrgProduct", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerOrgProductDto save(@ModelAttribute("partnerOrgProduct") PartnerOrgProductDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
		  Errors errors=new Errors();
	      partnerOrgProductValidator.validate(dto, errors);
		  {
			if(errors.getErrorCount()>0){
				ResponseDto response=new ResponseDto();
				response.setHasError(true);
				response.setErrors(errors.getErrorList());
				dto.setResponse(response);
				return dto;
			}
		   }
		}
		return partnerOrgProductService.saveOrgProduct(dto,role);	
	}
	
	@RequestMapping(value="/deletePartnerOrgProduct/{partnerOrgProductId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable ("partnerOrgProductId") Long partnerOrgProductId){
	
		boolean deleted =  partnerOrgProductService.deleteById(partnerOrgProductId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted.");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}
}
