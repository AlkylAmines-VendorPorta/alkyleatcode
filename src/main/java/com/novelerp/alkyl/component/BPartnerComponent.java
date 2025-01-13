package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.service.VendorProfileHistoryService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;

@Component
public class BPartnerComponent {
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private VendorProfileHistoryService vendorProfileService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	public BPartnerDto saveCompanyDetails(BPartnerDto oldDto,BPartnerDto newDto){
		createCompanyDetailsHistory(oldDto,newDto);
		setCompannydetails(oldDto, newDto);
		setVendorPartialdetails(oldDto);
		return partnerService.updateDto(oldDto);
	}
	private void createCompanyDetailsHistory(BPartnerDto oldDto,BPartnerDto newDto){
		if(!newDto.getCompanyType().equals(oldDto.getCompanyType())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Company Details");
			dto.setOldValue(oldDto.getCompanyType());
			dto.setNewValue(newDto.getCompanyType());
			dto.setFieldName("Type of Organization");
			vendorProfileService.save(dto);
		}
		if(!newDto.getVendorType().equals(oldDto.getVendorType())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Company Details");
			dto.setOldValue(oldDto.getVendorType());
			dto.setNewValue(newDto.getVendorType());
			dto.setFieldName("Vendor Type");
			vendorProfileService.save(dto);
		}
		if(!newDto.getName().equals(oldDto.getName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Company Details");
			dto.setOldValue(oldDto.getName());
			dto.setNewValue(newDto.getName());
			dto.setFieldName("Vendor Name");
			vendorProfileService.save(dto);
		}
	}
	private void setCompannydetails(BPartnerDto oldDto,BPartnerDto newDto){
		oldDto.setCompanyType(newDto.getCompanyType());
		oldDto.setName(newDto.getName());
		oldDto.setVendorType(newDto.getVendorType());
	}
	
	public void setKYCDetailsToPartner(BPartnerDto oldDto,BPartnerDto newDto){
		oldDto.setPanCardCopy(newDto.getPanCardCopy());
		oldDto.setPanNumber(newDto.getPanNumber());
		oldDto.setGstinCopy(newDto.getGstinCopy());
		oldDto.setGstinNo(newDto.getGstinNo());
		oldDto.setIsGstApplicable(newDto.getIsGstApplicable());
	}
	
	public void setVendorPartialdetails(BPartnerDto oldDto){
		oldDto.setIsProfileUpdated(AppBaseConstant.PARTIAL_STATUS);
		oldDto.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED);
	}
	
	public UserDto getLoggedInUser(){
		return contextService.getUser();
	}

}
