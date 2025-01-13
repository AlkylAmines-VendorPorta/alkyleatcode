package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.service.VendorProfileHistoryService;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appcontext.dto.UserDetailsDto;

@Component
public class VendorProfileHistoryComponent {
	
	
	@Autowired
	private VendorProfileHistoryService vendorProfileService;
	
	public void createCompanyADDHistory(PartnerCompanyAddressDto newDto,PartnerCompanyAddressDto oldDto) {
		if(!newDto.getIsPrimaryAccount().equals(oldDto.getIsPrimaryAccount())){
		VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
		dto.setModule("Address Details");
		dto.setOldValue(oldDto.getIsPrimaryAccount());
		dto.setNewValue(newDto.getIsPrimaryAccount());
		dto.setFieldName("Is Primary Address");
		vendorProfileService.saveDto(dto);
		}
		if(!newDto.getLocation().getAddress1().equals(oldDto.getLocation().getAddress1())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getAddress1());
			dto.setNewValue(newDto.getLocation().getAddress1());
			dto.setFieldName("Address Line 1");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getAddress2().equals(oldDto.getLocation().getAddress2())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getAddress2());
			dto.setNewValue(newDto.getLocation().getAddress2());
			dto.setFieldName("Address Line 2");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getAddress3().equals(oldDto.getLocation().getAddress3())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getAddress3());
			dto.setNewValue(newDto.getLocation().getAddress3());
			dto.setFieldName("Address Line 3");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getCountry().getCountryId().equals(oldDto.getLocation().getCountry().getCountryId())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getCountry().getName());
			dto.setNewValue(newDto.getLocation().getCountry().getName());
			dto.setFieldName("Country");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getRegion().getRegionId().equals(oldDto.getLocation().getRegion().getRegionId())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getRegion().getName());
			dto.setNewValue(newDto.getLocation().getRegion().getName());
			dto.setFieldName("State");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getDistrict().getDistrictId().equals(oldDto.getLocation().getDistrict().getDistrictId())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getDistrict().getName());
			dto.setNewValue(newDto.getLocation().getDistrict().getName());
			dto.setFieldName("District");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getLocation().getPostal().equals(oldDto.getLocation().getPostal())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Address Details");
			dto.setOldValue(oldDto.getLocation().getPostal());
			dto.setNewValue(newDto.getLocation().getPostal());
			dto.setFieldName("Postal Code");
			vendorProfileService.saveDto(dto);
			}
	}

	public void createCompanyConHistory(UserDetailsDto newDto, UserDetailsDto oldDto) {
		if(!newDto.getTitle().equals(oldDto.getTitle())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getTitle());
			dto.setNewValue(newDto.getTitle());
			dto.setFieldName("Salutation");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getName().equals(oldDto.getName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getName());
			dto.setNewValue(newDto.getName());
			dto.setFieldName("Person Name");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getUserDesignation().equals(oldDto.getUserDesignation())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getUserDesignation());
			dto.setNewValue(newDto.getUserDesignation());
			dto.setFieldName("Designation");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getUserDept().equals(oldDto.getUserDept())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getUserDept());
			dto.setNewValue(newDto.getUserDept());
			dto.setFieldName("Department");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getMobileNo().equals(oldDto.getMobileNo())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getMobileNo());
			dto.setNewValue(newDto.getMobileNo());
			dto.setFieldName("Mobile No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getMobileNo().equals(oldDto.getMobileNo())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getMobileNo());
			dto.setNewValue(newDto.getMobileNo());
			dto.setFieldName("Mobile No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getTelephone1().equals(oldDto.getTelephone1())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getTelephone1());
			dto.setNewValue(newDto.getTelephone1());
			dto.setFieldName("Telephone No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getEmail().equals(oldDto.getEmail())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getEmail());
			dto.setNewValue(newDto.getEmail());
			dto.setFieldName("Mail ID");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getIsReceiveEnquiry().equals(oldDto.getIsReceiveEnquiry())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getIsReceiveEnquiry());
			dto.setNewValue(newDto.getIsReceiveEnquiry());
			dto.setFieldName("Enquiry");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getIsReceivePO().equals(oldDto.getIsReceivePO())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getIsReceivePO());
			dto.setNewValue(newDto.getIsReceivePO());
			dto.setFieldName("PO");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getIsReceiveACInfo().equals(oldDto.getIsReceiveACInfo())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Contact Details");
			dto.setOldValue(oldDto.getIsReceiveACInfo());
			dto.setNewValue(newDto.getIsReceiveACInfo());
			dto.setFieldName("AC Info");
			vendorProfileService.saveDto(dto);
			}
		
	}

	public void createBankHistory(PartnerBankDetailDto newDto, PartnerBankDetailDto oldDto) {
		if(!newDto.getIfscCode().equals(oldDto.getIfscCode())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getIfscCode());
			dto.setNewValue(newDto.getIfscCode());
			dto.setFieldName("IFSC Code");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getAccountNumber().equals(oldDto.getAccountNumber())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getAccountNumber());
			dto.setNewValue(newDto.getAccountNumber());
			dto.setFieldName("Account No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getBenificaryName().equals(oldDto.getBenificaryName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getBenificaryName());
			dto.setNewValue(newDto.getBenificaryName());
			dto.setFieldName("Name As Per Cheque ");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getBankNameDetails().getName().equals(oldDto.getBankNameDetails().getName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getBankNameDetails().getName());
			dto.setNewValue(newDto.getBankNameDetails().getName());
			dto.setFieldName("Bank Name");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getBranchName().getBranchName().equals(oldDto.getBranchName().getBranchName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getBranchName().getBranchName());
			dto.setNewValue(newDto.getBranchName().getBranchName());
			dto.setFieldName("Branch Name ");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getBranchName().getBranchState().getRegionId().equals(oldDto.getBranchName().getBranchState().getRegionId())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Bank Details");
			dto.setOldValue(oldDto.getBranchName().getBranchState().getName());
			dto.setNewValue(newDto.getBranchName().getBranchState().getName());
			dto.setFieldName("Branch State");
			vendorProfileService.saveDto(dto);
			}
		
	}

	public void createDirectorHistory(UserDetailsDto newDto, UserDetailsDto oldDto) {
		if(!newDto.getDirectorType().equals(oldDto.getDirectorType())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getDirectorType());
			dto.setNewValue(newDto.getDirectorType());
			dto.setFieldName("Type");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getName().equals(oldDto.getName())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getName());
			dto.setNewValue(newDto.getName());
			dto.setFieldName("Name");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getDirectorUID().equals(oldDto.getDirectorUID())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getDirectorUID());
			dto.setNewValue(newDto.getDirectorUID());
			dto.setFieldName("Directors Identification No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getMobileNo().equals(oldDto.getMobileNo())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getMobileNo());
			dto.setNewValue(newDto.getMobileNo());
			dto.setFieldName("Mobile No");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getQualification().equals(oldDto.getQualification())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getQualification());
			dto.setNewValue(newDto.getQualification());
			dto.setFieldName("Qualification");
			vendorProfileService.saveDto(dto);
			}
		if(!newDto.getExperience().equals(oldDto.getExperience())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Directors Details");
			dto.setOldValue(oldDto.getExperience());
			dto.setNewValue(newDto.getExperience());
			dto.setFieldName("Experience");
			vendorProfileService.saveDto(dto);
			}
	}

	public void createOtherhistory(PartnerOrgDto newDto, PartnerOrgDto oldDto) {
		if(!newDto.getManPower().equals(oldDto.getManPower())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Other details");
			dto.setOldValue(oldDto.getManPower());
			dto.setNewValue(newDto.getManPower());
			dto.setFieldName("No of Employees");
			vendorProfileService.saveDto(dto);
			}
	/*	if(!newDto.getEstdDate().equals(oldDto.getEstdDate())){
			VendorProfileHistoryDto dto= new VendorProfileHistoryDto();
			dto.setModule("Other details");
			dto.setOldValue(oldDto.getEstdDate());
			dto.setNewValue(newDto.getEstdDate());
			dto.setFieldName("No of Employees");
			vendorProfileService.saveDto(dto);
			}*/
		
	}

}
