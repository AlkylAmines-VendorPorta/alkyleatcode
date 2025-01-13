package com.novelerp.alkyl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.dto.OtherDocumentsDto;
import com.novelerp.alkyl.service.KYCService;
import com.novelerp.alkyl.service.OtherDocumentsService;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dao.PartnerCompanyAddressDao;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

@Controller
@RequestMapping("/rest")
public class KYCController {
	
	@Autowired
	private KYCService kycService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private BPartnerComponent partnerComponent;
	
	@Autowired
    private VendorRegistrationValidator validator;
	
	@Autowired
	private OtherDocumentsService odService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;
	
	@Autowired
	private PartnerCompanyAddressDao  partnerCompanyAddressDao;
	
	@PostMapping(value="/saveKYCDetails")
	public @ResponseBody CustomResponseDto saveKYCDetails(@RequestBody KYCDto dto){ 
		CustomResponseDto resp = new CustomResponseDto();
		if(dto==null || dto.getPartner()==null){
			return resp;
		}
		Errors errors = new Errors();
		validator.validateKYCDetails(dto, errors);
		if(errors.getErrorCount()>0){
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("kycDetails", dto);
			return resp;
		}
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",AbstractContextServiceImpl.getParamMap("partnerId", dto.getPartner().getbPartnerId()));
		partnerComponent.setKYCDetailsToPartner(oldDto, dto.getPartner());
		dto.setPartner(oldDto);
		dto = kycService.save(dto);
		String role =contextService.getDefaultRole().getValue();
//		if(role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)){
		partnerComponent.setVendorPartialdetails(oldDto);
//		}
		partnerService.updateDto(oldDto);
		resp.addObject("kycDetails",dto);
		return resp;
	}
	
	@PostMapping(value="/getKYCDetails/{partnerId}")
	public @ResponseBody CustomResponseDto getKYCDetails(@PathVariable("partnerId")Long partnerId){
		
		
		
		
		CustomResponseDto resp = new CustomResponseDto();
		String role =contextService.getDefaultRole().getValue();
		KYCDto dto = kycService.findDto("getKYCDetailsByPartnerId",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		List<OtherDocumentsDto> odList = odService.findDtos("getOtherDocumentsByKYCId", AbstractContextServiceImpl.getParamMap("kycId", dto.getKycId()));
		dto.setOtherDocuments(odList);
		
		/*added on 09-08-2024*/
//	
//		String locationType=AppBaseConstant.LOCATION_TYPE_COMP_ADDR;
//		
//		List<Object> orgs=partnerCompanyAddressDao.getregionByLocationandID(locationType,partnerId);
//		
//		resp.addObject("companyAddressRegionList", orgs);
		
		/*added on 09-08-2024*/
		
		resp.addObject("kycDetails", dto);
		resp.addObject("role",role);
		
		return resp;
	}
}
