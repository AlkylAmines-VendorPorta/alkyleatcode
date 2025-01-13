package com.novelerp.alkyl.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.IMSDetailsDto;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.service.IMSDetailsService;
import com.novelerp.alkyl.service.KYCService;
import com.novelerp.appbase.master.dao.PartnerCompanyAddressDao;
import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.service.PartnerBankDetailService;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appbase.master.service.PartnerDirectorDetailsService;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.commons.util.CommonValidationsUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class VendorRegistrationValidator implements Validator {

	@Autowired
	private PartnerDirectorDetailsService directorService;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private PartnerCompanyAddressService addressService;
	
	@Autowired
	private PartnerFinancialDetailsService partnerFinancialDetailsService;
	
	@Autowired
	private KYCService kycService; 
	
	@Autowired
	private IMSDetailsService imsDetailservice;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private PartnerBankDetailService partnerBankDetailService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;
	
	@Override
	public void validate(Object object, Errors errors) {

	}

	public void validateGeneralInformation(BPartnerDto partner, Errors errors) {
		// TODO Auto-generated method stub
		if(partner==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if(CommonUtil.isStringEmpty(partner.getCompanyType())){
			validationUtil.reject(errors, "EmptyCompanyType", "Company Type is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(partner.getVendorType())){
			validationUtil.reject(errors, "EmptyVendorType", "Vendor Type is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(partner.getName())){
			validationUtil.reject(errors, "EmptyVendorName", "Vendor Name is Mandatory");
		}
	}
	
	public void validateCompanyAddress(PartnerCompanyAddressDto companyAddressDetails, Errors errors) {
		// TODO Auto-generated method stub
		
		if(companyAddressDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerId", companyAddressDetails.getPartner().getbPartnerId());
		params.put("locationType", AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
		List<PartnerCompanyAddressDto> companyAddressList = addressService.findDtos("getAddressQueryByLocation", params);
		
		
		if(companyAddressDetails!=null && companyAddressDetails.getPartnerCompanyAddressId()==null){
			int count=0;
			for(PartnerCompanyAddressDto addr : companyAddressList){
				if("Y".equals(addr.getIsPrimaryAccount())){
					count++;
				}
			}
			if(count>=1 && ("Y".equals(companyAddressDetails.getIsPrimaryAccount()))){
				validationUtil.reject(errors, "EmptyCompanyAddress", "You can not Add more than one Company Primary Address");
			}
		}
		
		
		if(companyAddressDetails.getLocation()==null){
			validationUtil.reject(errors, "EmptyLocation", "Location details (Country,State,District,Pincode) are Mandatory");
			return;
		}
		
		if(companyAddressDetails.getLocation().getCountry()==null
			|| companyAddressDetails.getLocation().getCountry().getCountryId()==null){
			validationUtil.reject(errors, "EmptyCountry", "Country is Mandatory");
		}
	if(companyAddressDetails.getLocation().getCountry().getCountryId().equals(1)){
			
			if(companyAddressDetails.getLocation().getRegion()==null
					|| companyAddressDetails.getLocation().getRegion().getRegionId()==null){
				validationUtil.reject(errors, "EmptyState", "State is Mandatory");
			}
		
			if(companyAddressDetails.getLocation().getDistrict()==null 
					|| companyAddressDetails.getLocation().getDistrict().getDistrictId()==null){
				validationUtil.reject(errors, "EmptyDistrict", "District is Mandatory");
			}
		}else{
		if(CommonUtil.isStringEmpty(companyAddressDetails.getLocation().getPostal())){
			validationUtil.reject(errors, "EmptyPostalCode", "Postal Code is Mandatory");
			}
		
		}
		
	}
	
	public void validateCompanyContact(UserDetailsDto companyContactDetails, Errors errors) {

		if(companyContactDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getTitle())){
			validationUtil.reject(errors, "EmptyTitle", "Salutation is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getName())){
			validationUtil.reject(errors, "EmptyName", "Person Name is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getUserDesignation())){
			validationUtil.reject(errors, "EmptyDesignation", "Designation is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getUserDept())){
			validationUtil.reject(errors, "EmptyDept", "Department is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getMobileNo())){
			validationUtil.reject(errors, "EmptyMobile", "Mobile Number is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getEmail())){
			validationUtil.reject(errors, "EmptyEmail", "Email is Mandatory");
		}else{
			if(!CommonValidationsUtil.isValidEmail(companyContactDetails.getEmail())){
				validationUtil.reject(errors, "EmptyEmail", "Enter Valid Email-Id");
			}
		}
	}
	
	public void validateBankDetails(PartnerBankDetailDto bankDetail,Errors errors){
		
		if(bankDetail == null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
//		BPartnerDto partnerDto =  null;
//		
//		if(null!=bankDetail.getPartner()){
//			partnerDto = partnerService.findDto("getQueryForPartnerDetail",AbstractContextServiceImpl.getParamMap("partnerId", bankDetail.getPartner().getbPartnerId()));
//			if(null==partnerDto){
//				validationUtil.reject(errors, "EmptyObject", "Empty Form");
//				return;
//			}
//		}else{
//			validationUtil.reject(errors, "EmptyObject", "Empty Form");
//			return;
//		}
		
		
		if(CommonUtil.isStringEmpty(bankDetail.getAccountNumber())){
			validationUtil.reject(errors, "EmptyAccNo", "Account Number is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(bankDetail.getBenificaryName())){
			validationUtil.reject(errors, "EmptyBeneficiary", "Beneficiary Name is Mandatory");
		}
			
//	if(	!(partnerDto.getName()).equals(bankDetail.getBenificaryName())){
//			validationUtil.reject(errors, "VendorNameCheckNameDiffrent", "Name As Per check is Different than Vendor Name");
//		}
		
		if(CommonUtil.isStringEmpty(bankDetail.getIfscCode())){
			validationUtil.reject(errors, "EmptyIFSC", "IFSC Code is Mandatory");
		}
		
		if(bankDetail.getBankNameDetails()==null){
			validationUtil.reject(errors, "EmptyBankName", "Bank Details are Mandatory");
			return;
		}
		
		if(bankDetail.getBranchName()==null){
			validationUtil.reject(errors, "EmptyBankBranch", "Bank Branch Details are Mandatory");
			return;
		}
		
		if(CommonUtil.isStringEmpty(bankDetail.getBankNameDetails().getName())){
			validationUtil.reject(errors, "EmptyBankName", "Bank Name is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(bankDetail.getBranchName().getBranchName())){
			validationUtil.reject(errors, "EmptyBranchName", "Bank Branch Name is Mandatory");
		}
		
		if(null == bankDetail.getBranchName().getBranchState()){
			validationUtil.reject(errors, "EmptyBranchState", "Bank Branch State is Mandatory");
		}
	}
	
	public void validateKYCDetails(KYCDto kycDetails,Errors errors){
		
		if(kycDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if(kycDetails.getCancelledCheque()==null || kycDetails.getCancelledCheque().getAttachmentId()==null){
			validationUtil.reject(errors, "EmptyCanceledCheque", "Upload Cancelled Cheque");
		}
		
		if(kycDetails.getPartner() == null ){
			validationUtil.reject(errors, "EmptyKYC", "Empty Partner Details");
			return;
		}
		
		if( CommonUtil.isStringEmpty(kycDetails.getPartner().getPanNumber())
				|| !CommonValidationsUtil.isValidPanNO(kycDetails.getPartner().getPanNumber())){
			validationUtil.reject(errors, "InvalidPAN", "Enter Valid PAN Number");
		}
		
		/*BPartnerDto partner = partnerService.findDto("getPartnerByPanNo", AbstractContextServiceImpl.getParamMap("panCardNumber", kycDetails.getPartner().getPanNumber()));
		
		if(null != partner && !partner.getbPartnerId().equals(contextService.getPartner().getbPartnerId())){
			validationUtil.reject(errors, "InvalidPAN", "PAN already registered");
		}*/
		
		if("Y".equals(kycDetails.getPartner().getIsGstApplicable())
				&& CommonUtil.isStringEmpty(kycDetails.getPartner().getGstinNo())){
			validationUtil.reject(errors, "EmptyGST", "Enter GST");
		}
		
		if(kycDetails.getPartner().getGstinNo()!=null){
			
			String GstNo=kycDetails.getPartner().getGstinNo();
			String StateCode=GstNo.substring(0, 2);
			String PanNo=GstNo.substring(2, 12);
			String partnerStateCode="";
			String partnerPanNo=kycDetails.getPartner().getPanNumber();
			
			Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerId", kycDetails.getPartner().getbPartnerId());
			params.put("locationType", AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
			List<PartnerCompanyAddressDto> partnerAddress = partnerCompanyAddressService.findDtos("getPrimaryAddressQueryByLocation", params);
			
			if(partnerAddress.size()>0) {
				 partnerStateCode=partnerAddress.get(0).getLocation().getRegion().getCode();
				}
			
			
				int count=0;
				for(PartnerCompanyAddressDto addr : partnerAddress){
					if("Y".equals(addr.getIsPrimaryAccount())){
						count++;
					}
				}
				
				if(count<=0){
					validationUtil.reject(errors, "EmptyCompanyAddress", "ADD Atleast One Primary Company Address");
				}else {
					
					if(!StateCode.equals(partnerStateCode)) {
						validationUtil.reject(errors, "Wrong State Code", "Enter valid state code in GST No");
					}					
				}
			
			if(!PanNo.equals(partnerPanNo)) {
				validationUtil.reject(errors, "Wrong Pan No", "Enter valid Pan in GST No");
			}
			
			
		}
		
		if("Y".equals(kycDetails.getIsMSME()) && CommonUtil.isStringEmpty(kycDetails.getMsmeNumber())){
			
			validationUtil.reject(errors, "EmptyMSME", "Enter MSME Number");
		}
		if("Y".equals(kycDetails.getIsMSME())){
			if(kycDetails.getMsmeCertificate()==null || kycDetails.getMsmeCertificate().getAttachmentId()==null){
				validationUtil.reject(errors, "EmptyMSME", "Upload MSME Certificate");
			}
		}
		
		if("Y".equals(kycDetails.getIsLowerDeduction())){
			if(kycDetails.getLowerDeductionCert()==null || kycDetails.getLowerDeductionCert().getAttachmentId()==null){
				validationUtil.reject(errors, "EmptyMSME", "Upload Lower Deduction Certificate");
			}
			
			if(CommonUtil.isStringEmpty(kycDetails.getLdValue())){
				validationUtil.reject(errors, "EmptyLDValue", "Enter Lower Deduction Value");
			}
			
			if(kycDetails.getValidFrom()==null){
				validationUtil.reject(errors, "EmptyValidFrom", "Enter Lower Deduction Valid From Date");
			}
			
			if(kycDetails.getValidTo()==null){
				validationUtil.reject(errors, "EmptyValidTo", "Enter Lower Deduction Valid To Date");
			}
		}
	}
	
	public void validateDirectorDetails(UserDetailsDto directorDetails, Errors errors){
		
		if(directorDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if(CommonUtil.isStringEmpty(directorDetails.getName())){
			validationUtil.reject(errors, "EmptyDirectorName", "Name is Mandatory");
		}
		
		if(CommonUtil.isStringEmpty(directorDetails.getDirectorType())){
			validationUtil.reject(errors, "EmptyDirectorType", "Type is Mandatory");
		}/*else if(AppBaseConstant.DIRECTOR_TYPE_DIRECTOR.equals(directorDetails.getDirectorType()) 
				&& CommonUtil.isStringEmpty(directorDetails.getDirectorUID()) ){
			validationUtil.reject(errors, "EmptyDirectorUID", "Director UID is Mandatory");
		}*/
		
	}
	
//	public void validateFinancialDetails(FinancialDetailsDto financilDetails, Errors errors){
//		
//		if(financilDetails==null){
//			validationUtil.reject(errors, "EmptyObject", "Empty Form");
//			return;
//		}
		
//		List<PartnerFinancialDetailDto> partnerFDList = financilDetails.getFinancialDetailsList();
//		for(PartnerFinancialDetailDto partnerFD: partnerFDList){
//			if(partnerFD.getFinancialYear()==null || partnerFD.getBalanceSheet()==null || partnerFD.getProfitAndLoss()==null
//				|| partnerFD.getTurnOver()==null || partnerFD.getFinancialYear().getFinancialYearId()==null 
//				|| partnerFD.getBalanceSheet().getAttachment()==null 
//				|| partnerFD.getProfitAndLoss().getAttachment()==null
//				|| partnerFD.getBalanceSheet().getAttachment().getAttachmentId()==null 
//				|| partnerFD.getProfitAndLoss().getAttachment().getAttachmentId()==null
//				|| partnerFD.getTurnOver().getAmount()<=0 )
//			{
//				validationUtil.reject(errors, "EmptyFinancialDetails", "All Financial Detail Fields are Mandatory");
//				return;
//			}
//		}
//	}
	
	public void validateIMSDetails(IMSDetailsDto imsDetails,Errors errors){
		
		if(imsDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
		
		if("Y".equals(imsDetails.getIsISO14001()) 
			&& (imsDetails.getIso14001Attachment()==null 
				|| imsDetails.getIso14001Attachment().getAttachmentId()==null
				|| imsDetails.getIso14001ValidUpto()==null)){
			validationUtil.reject(errors, "EmptyISO", "Fill ISO 14001 Details");
		}
		
		if("Y".equals(imsDetails.getIsISO50001()) 
				&& (imsDetails.getIso50001Attachment()==null 
					|| imsDetails.getIso50001Attachment().getAttachmentId()==null
					|| imsDetails.getIso50001ValidUpto()==null)){
				validationUtil.reject(errors, "EmptyISO", "Fill ISO 50001 Details");
		}
		
		if("Y".equals(imsDetails.getIsISO9001()) 
				&& (imsDetails.getIso9001Attachment()==null 
					|| imsDetails.getIso9001Attachment().getAttachmentId()==null
					|| imsDetails.getIso9001ValidUpto()==null)){
				validationUtil.reject(errors, "EmptyISO", "Fill ISO 9001 Details");
		}
		
		if("Y".equals(imsDetails.getIsRCLogo()) 
				&& (imsDetails.getIsRCLogoAttachment()==null 
					|| imsDetails.getIsRCLogoAttachment().getAttachmentId()==null
					|| imsDetails.getIsRCLogoValidUpto()==null)){
				validationUtil.reject(errors, "EmptyISO", "Fill RC LOGO Details");
		}
	}
	
	public void validateRegistrationSubmit(Long partnerId, Errors errors){
		
		BPartnerDto partnerDto =  partnerService.findDto("getQueryForPartnerDetail",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		
		Map<String, Object> params = AbstractServiceImpl.getParamMap("partnerId", partnerId);
		//params.put("locationType", AppBaseConstant.LOCATION_TYPE_ASSOCIATE_COMP_ADDR);
		//List<PartnerCompanyAddressDto> associateAddressList = addressService.findDtos("getAddressQueryByLocation", params);
		
		//params.clear();
		//params = AbstractServiceImpl.getParamMap("partnerId", partnerId);
		params.put("locationType", AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
		List<PartnerCompanyAddressDto> companyAddressList = addressService.findDtos("getAddressQueryByLocation", params);
		
		/*UserDetailsDto directorDto=directorService.findDto("getDirectorDetails", AbstractContextServiceImpl.getParamMap("partnerId", partnerId));*/
		
		params.clear();
		params =  AbstractServiceImpl.getParamMap("partnerId", partnerId);
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.YEAR, -1);
//		Date lastYear=cal.getTime();
//		cal.add(Calendar.YEAR,-1);
//		Date lastTwoYear=cal.getTime();
//		params.put("lastYear", lastYear);
//		params.put("lastTwoYear", lastTwoYear);
//		params.put("finacialType", AppBaseConstant.TURNOVER_DETAILS);
		List<PartnerFinancialAttachmentDto> financialAttachments=partnerFinancialDetailsService.findDtos("getFinancialDetailsQuery", params);
		
		KYCDto kycDto = kycService.findDto("getKYCDetailsByPartnerId",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		IMSDetailsDto imsDto=imsDetailservice.findDto("getIMSDetails", AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		
		List<UserDetailsDto> contactDetailsList = userDetailService.findDtos("getUserDetailsQueryByBP", AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId));
				
		params.clear();
		params = AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId);
		List<PartnerBankDetailDto> bankDetails =  partnerBankDetailService.findDtos("getBankDetailsQuery", params);
		
		
		if(partnerDto == null){
			validationUtil.reject(errors, "EmptyPartner", "Fill General Information");
		}
		if(partnerDto.getPanCardCopy()==null){
			validationUtil.reject(errors, "PAN CARD", "PAN CARD Copy Cannot be null");
		}
		if("Y".equals(partnerDto.getIsGstApplicable())){
		if(partnerDto.getGstinCopy()==null){
			validationUtil.reject(errors, "GSTN", "GSTN Copy Cannot be null");
		}
		
		
//          if(partnerDto.getGstinNo()!=null){
//			
//			String GstNo=partnerDto.getGstinNo();
//			String StateCode=GstNo.substring(0, 2);
//			String PanNo=GstNo.substring(2, 12);
//			String partnerStateCode="";
//			Map<String, Object> params1 = AbstractServiceImpl.getParamMap("partnerId", partnerDto.getbPartnerId());
//			params1.put("locationType", AppBaseConstant.LOCATION_TYPE_COMP_ADDR);
//			List<PartnerCompanyAddressDto> partnerAddress = partnerCompanyAddressService.findDtos("getPrimaryAddressQueryByLocation", params1);
//			
//			if(partnerAddress.size()>0) {
//			 partnerStateCode=partnerAddress.get(0).getLocation().getRegion().getCode();
//			}
//			String partnerPanNo=partnerDto.getPanNumber();
//			
//			if(!StateCode.equals(partnerStateCode)) {
//				validationUtil.reject(errors, "Wrong State Code", "Enter valid state code in GST No");
//			}
//			
//			if(!PanNo.equals(partnerPanNo)) {
//				validationUtil.reject(errors, "Wrong Pan No", "Enter valid Pan in GST No");
//			}
//			
//			
//		}
		}
          
		
		
		
		if("".equals(partnerDto.getCompanyType() )|| partnerDto.getCompanyType()==null){
			validationUtil.reject(errors, "Organization Type", "Organization type cannot be null");
		}
		if("".equals(partnerDto.getVendorType()) || partnerDto.getVendorType()==null){
			validationUtil.reject(errors, "Vendor Type", "Vendor type cannot be null");
		}
		if(CommonUtil.isCollectionEmpty(companyAddressList)){
			validationUtil.reject(errors, "EmptyCompanyAddress", "ADD Atleast One Company Address");
		}else{
			int count=0;
			for(PartnerCompanyAddressDto addr : companyAddressList){
				if("Y".equals(addr.getIsPrimaryAccount())){
					count++;
				}
			}
			if(count<=0){
				validationUtil.reject(errors, "EmptyCompanyAddress", "ADD Atleast One Primary Company Address");
			}
		}
		
		if(CommonUtil.isCollectionEmpty(contactDetailsList)){
			validationUtil.reject(errors, "EmptyCompanyContact", "ADD Atleast One Company Contact");
		}else{
			int poCount = 0;
			int enqCount = 0;
			int accountInfo = 0;
			for(UserDetailsDto contact:contactDetailsList){
				validateCompanyContactOnSubmit(contact, errors);
				if("Y".equals(contact.getIsReceiveEnquiry())){
					enqCount++;
				}
				
				if("Y".equals(contact.getIsReceiveACInfo())){
					accountInfo++;
				}
				
				if("Y".equals(contact.getIsReceivePO())){
					poCount++;
				}
			}
			
			if(enqCount<=0){
				validationUtil.reject(errors, "enquiryCount", "ADD Atleast One Company Contact to Receive Enquiry");
			}
			
			if(poCount<=0){
				validationUtil.reject(errors, "enquiryCount", "ADD Atleast One Company Contact to Receive PO");
			}
			
			if(accountInfo<=0){
				validationUtil.reject(errors, "enquiryCount", "ADD Atleast One Company Contact to Receive Account Info");
			}
		}
		
		if(bankDetails.isEmpty()){
			validationUtil.reject(errors, "EmptyBankDetails", "Fill Bank Details");
		}
		
		/*if(directorDto==null){
			validationUtil.reject(errors, "EmptyDirector", "Fill Director Details");
		}*/
		
//		if(CommonUtil.isCollectionEmpty(financialAttachments)){
//			validationUtil.reject(errors, "EmptyFinancialDetails", "Fill Financial Details");
//		}
		
		/*if(CommonUtil.isCollectionEmpty(associateAddressList)){
			validationUtil.reject(errors, "EmptyAssociateAddress", "Fill Associate Company Details");
		}*/
		
		if(kycDto==null){
			validationUtil.reject(errors, "EmptyKYC", "Fill KYC Details");
		}
		if(kycDto.getCancelledCheque()==null){
			validationUtil.reject(errors, "Cancelled Cheque", "Cancelled Cheque connot be null");
		}
		
		if(partnerDto!=null && "MANUFACTURER".equals(partnerDto.getVendorType()) && 
				imsDto==null){
			validationUtil.reject(errors, "EmptyIMSDetails", "Fill IMS Details");
		}
	}
	public void validateCompanyContactOnSubmit(UserDetailsDto companyContactDetails, Errors errors) {
		
		if(companyContactDetails==null){
			validationUtil.reject(errors, "EmptyObject", "Empty Form");
			return;
		}
			
		final String name=companyContactDetails.getName();
			
		if(CommonUtil.isStringEmpty(companyContactDetails.getTitle())){
			validationUtil.reject(errors, "EmptyTitle", "Salutation is Mandatory for" + name);
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getName())){
			validationUtil.reject(errors, "EmptyName", "Person Name is Mandatory for" + name);
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getUserDesignation())){
			validationUtil.reject(errors, "EmptyDesignation", "Designation is Mandatory for" + name);
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getUserDept())){
			validationUtil.reject(errors, "EmptyDept", "Department is Mandatory for" + name);
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getMobileNo())){
			validationUtil.reject(errors, "EmptyMobile", "Mobile Number is Mandatory for" + name);
		}
		
		if(CommonUtil.isStringEmpty(companyContactDetails.getEmail())){
			validationUtil.reject(errors, "EmptyEmail", "Email is Mandatory for" + name);
		}else{
			if(!CommonValidationsUtil.isValidEmail(companyContactDetails.getEmail())){
				validationUtil.reject(errors, "EmptyEmail", "Enter Valid Email-Id for" + name);
			}
		}
		
	}
		
}
