package com.novelerp.appbase.master.controller;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.BankNameDetailsDto;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.BankNameDetailsService;
import com.novelerp.appbase.master.service.PartnerBankDetailService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;

@RequestMapping("/rest")
@Controller
public class PartnerBankDetailController {

	@Autowired
	private PartnerBankDetailService partnerBankDetailService;
	@Autowired
	private BankNameDetailsService bankNameDetailsService;
	@Autowired
	private VendorRegistrationValidator validator;
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private VendorProfileHistoryComponent vendorProfileComponent;
	@Autowired
	private BPartnerComponent bPartnerComponent;
	
	@RequestMapping(value="/getPartnerBankDetails/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerBankDetails (@PathVariable ("partnerId") Long bPartnerId ){
		 
		BPartnerDto partnerDto=partnerService.findDto("getQueryForPartnerDetail",AbstractContextServiceImpl.getParamMap("partnerId", bPartnerId));
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("bPartnerId", bPartnerId);
		List<PartnerBankDetailDto> bankDetails =  partnerBankDetailService.findDtos("getBankDetailsQuery", params);
		Map<String, Object> param= new HashMap<>();
		List<BankNameDetailsDto> bankName= bankNameDetailsService.findDtos("getAllBankName", param);
		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
		CustomResponseDto response =  new CustomResponseDto();
		if(!CommonUtil.isCollectionEmpty(bankDetails)){
			response.addObject("partnerBankDetails", bankDetails.get(0));
		}
		if(!CommonUtil.isCollectionEmpty(bankName)){
			response.addObject("bankName", bankName);
		}
		response.addObject("regions", regions);
		response.addObject("vendorName", partnerDto.getName());
		return response;
	}
	@RequestMapping(value="/savePartnerBankDetail", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerBankDetailDto save(@RequestBody PartnerBankDetailDto dto){
		Errors errors = new Errors();
		validator.validateBankDetails(dto, errors);
		if(errors.getErrorCount()>0){
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			return dto;
		}
		if(null!=dto.getPartnerBankDetailId()){
			try{
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("id", dto.getPartnerBankDetailId());
				PartnerBankDetailDto oldDto=partnerBankDetailService.findDto("getBankDetailsById", param);
				vendorProfileComponent.createBankHistory(dto,oldDto);
				}catch(Exception e){
					e.printStackTrace();
				}
			}

        // Update partial status of vendor
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
						AbstractContextServiceImpl.getParamMap("partnerId", dto.getPartner().getbPartnerId()));
		bPartnerComponent.setVendorPartialdetails(oldDto);
		partnerService.updateDto(oldDto);
        return partnerBankDetailService.saveBankDetails(dto);
     }
	
	@RequestMapping(value="/deletePartnerBankDetail/{partnerBankDetailId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("partnerBankDetailId") Long partnerBankDetailId){

		boolean deleted = partnerBankDetailService.deleteById(partnerBankDetailId);
		if(deleted){
			return new ResponseDto(false, "Reccord Deleted");
		}
		return new ResponseDto(false, "Problem in deleting Record");
	}
	
	@PostMapping(value="/getIFSCDetails/{ifsc}")
	public @ResponseBody String getIFSCDetails(@PathVariable("ifsc")String ifscCode){
		System.out.println("success");
		try {
			
			if (CommonUtil.isStringEmpty(ifscCode)) {
				throw new InvalidParameterException();
			}
			StringBuilder url = new StringBuilder("https://ifsc.datayuge.com/api/v1/");
			url.append( ifscCode);
			RestTemplate restTemplate = new RestTemplate();
			String responseBody = null;
			ResponseEntity<String> response;
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("DY-X-Authorization", "08154fb2143dbf958126f0ba9cfe0f85ce73fd90");
				HttpEntity<String> entity = new HttpEntity<>("body", headers);
				response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);
				responseBody = response.getBody();
				if (!CommonUtil.isStringEmpty(responseBody)) {
					System.out.println(responseBody);
					return responseBody;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("response- " + responseBody);
			return "";
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
