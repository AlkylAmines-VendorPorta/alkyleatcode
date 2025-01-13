package com.novelerp.appcontext.validator;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.appbase.master.entity.PartnerOrgRDAEC;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.SignatureService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.DateUtil;
import com.novelerp.core.util.ServiceConstant;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.entity.PaymentDetail;
@Component
public class PartnerValidator implements  Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private BPartnerService partnerServic;
    @Autowired
	private AttachmentService attachmentService;
    @Autowired
    private SignatureService signatureService;
    @Autowired
    private PartnerOrgService partnerOrgService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(BPartnerDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		BPartnerDto partner =  (BPartnerDto) object;
		validatorUtil.rejectIfEmpty(partner.getCompanyType(), errors, "invalid.companyType", "Company Type Not Selected");
		validatorUtil.rejectIfEmpty(partner.getPanNumber(), errors, "invalid.panNumber", "Pan Number is Required");
		checkForPartnerRole(partner,errors);
		checkForGSTNApplicable(partner,errors);
		checkForTermsAccepted(partner, errors);
		checkForManipulation(partner, errors);
		checkForContractorType(partner, errors);
		/*checkForMandatoryFile(partner, errors);*/
		checkForUniqueNumbers(partner, errors);
		checkForGSTN(partner, errors);
	}
	
	/**
	 * Check for client side manipulations
	 * @param partner
	 * @param errors
	 */
	private void checkForManipulation(BPartnerDto partner, Errors errors){
	
		BPartnerDto contextPartner = contextService.getPartner();
		if(contextPartner == null){
			return;
		}
		if(partner.getbPartnerId().compareTo(contextPartner.getbPartnerId())!=0){
			validatorUtil.reject(errors, "invalid.partnerId", ServiceConstant.INVALID_CHANGE_MSG);
		}
	}
	
	private void checkForPartnerRole(BPartnerDto partner, Errors errors)
	{
		if(partner.getIsContractor()==null || partner.getIsContractor()=="N")
		{
			if(partner.getIsCustomer()==null || partner.getIsCustomer()=="N")
			{
				if( partner.getIsManufacturer()==null || partner.getIsManufacturer()=="N")
				{
					if(partner.getIsTrader()==null || partner.getIsTrader()=="N")
					{
						validatorUtil.rejectIfEmpty(null, errors, "invalid.partnerType","Partner Type Not Selected");
					}
				}
			}
		}
	}
	private void checkForGSTNApplicable(BPartnerDto partner, Errors errors)
	{
		if(partner.getIsGstApplicable()!=null && partner.getIsGstApplicable()!="N")
		{
			validatorUtil.rejectIfEmpty(partner.getGstinNo(), errors, "invalid.gstinNo","GST Number Required");
		}
	}
	private void checkForTermsAccepted(BPartnerDto partner, Errors errors)
	{
		if(partner.getHasAccepted()==null || partner.getHasAccepted().equals("N"))
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.hasAccepted","Accept Terms and Conditions");
		}
	}
	private void checkForContractorType(BPartnerDto partner, Errors errors){
		if(partner.getRegistrationType().equals(AppBaseConstant.PARTICIPANT)){
			if(partner.getIsContractor().equals("Y")){
				validatorUtil.rejectIfEmpty(partner.getContractorType(), errors, "invalid.contractorType", "Supplier Type Not Selected");
		    }
		}
	}
	
	
	
	/*private void checkForMandatoryFile(BPartnerDto partner, Errors errors)
	{
		if(partner.getCompanyRegCertificate().getAttachmentId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.companyRegCertificate", "Company Registration Certificate is Required");
		}
		if(partner.getPanCardCopy().getAttachmentId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.panCardCopy", "Pan Card Copy is Required");
		}
		if(partner.getIsGstApplicable()=="Y" && partner.getGstinCopy().getAttachmentId()==null)
		{
			validatorUtil.rejectIfEmpty(null, errors, "invalid.gstinCopy", "GSTIN Copy is Required");
		}
	}*/
	public void checkForGSTN(BPartnerDto partner, Errors errors)
	{
	   if(partner!=null && !CommonUtil.isStringEmpty(partner.getGstinNo()))
	   {
		   String gstNumber=partner.getGstinNo().substring(2, 12);
		   if(!gstNumber.equals(partner.getPanNumber()))
		   {
		     validatorUtil.reject(errors, "invalid.gstino", "Invalid GST Number");
		   }
	   }
	}
	public void checkForUniqueNumbers(BPartnerDto partner, Errors errors)
	{
	   Map<String, Object> params=AbstractServiceImpl.getParamMap("panCardNumber", partner.getPanNumber());
	   params.put("crnNumber", partner.getCrnNumber());
	   BPartnerDto partnerDto=partnerServic.findDto("getPartnerForValidation",params);
	   if(null!=partnerDto && !CommonUtil.isEqual(partner.getbPartnerId(), partnerDto.getbPartnerId()))
	   {
		   if((CommonUtil.isStringEmpty(partner.getGstinNo()) && CommonUtil.isStringEmpty(partnerDto.getGstinNo())) || (partner.getGstinNo().equals(partnerDto.getGstinNo())))
		   {
		     validatorUtil.reject(errors, "already.registered", "User with given CRN,GSTN and Pan Card Number already registered.");
		   }
	   }
	}	
	public void checkSignCopy(BPartnerDto partner,Errors errors)
	{
		 if(partner.getCeSignCopy()!=null && partner.getCeSignCopy().getAttachmentId()!=null)
		 {
            AttachmentDto attachment=attachmentService.findDto(partner.getCeSignCopy().getAttachmentId());
		    String filePath =  attachment.getPath()+attachment.getName();
		    if(attachment.getPath()==null)
		    {
		    	validatorUtil.reject(errors, "invalid.signatoryInfo", "Sign File Path Not Found");
		    	return;
		    }
		    File file = new File(filePath);
		    UserDetailsDto userDetail=contextService.getUserDetails();
			ResponseDto resp = signatureService.validate(file, userDetail);
			if(resp.isHasError()){
				validatorUtil.reject(errors, "invalid.signatoryInfo", resp.getMessage());
			}
		 }
	}
	public void checkCoSignCopy(BPartnerDto partner,Errors errors)
	{
		 if(partner.getPartnerCoSignCopy()!=null && partner.getPartnerCoSignCopy().getAttachmentId()!=null)
		 {
            AttachmentDto attachment=attachmentService.findDto(partner.getPartnerCoSignCopy().getAttachmentId());
		    String filePath =  attachment.getPath()+attachment.getName();
		    if(attachment.getPath()==null)
		    {
		    	validatorUtil.reject(errors, "invalid.signatoryInfo", "Co-Sign File Path Not Found");
		    	return;
		    }
		    File file = new File(filePath);
		    UserDetailsDto userDetail=contextService.getUserDetails();
			ResponseDto resp = signatureService.validateCoSigner(file, userDetail);
			if(resp.isHasError()){
				validatorUtil.reject(errors, "invalid.signatoryInfo", resp.getMessage());
			}
		 }else{
			 validatorUtil.reject(errors, "invalid.file", "Co Signed Copy is Required");
		 }
	}
		public void checkForUniquePanNo(BPartnerDto partner, Errors errors)
	{
		   Map<String, Object> params=AbstractServiceImpl.getParamMap("panCardNumber", partner.getPanNumber());
		   BPartnerDto partnerDto=partnerServic.findDto("getQueryForBPDetailsForSendInvitation",params);
		   if(null!=partnerDto)
		   {
			   validatorUtil.reject(errors, "already.registered", "User with given Pan Card Number already registered.");
			  
		   }
		
	}

	public void checkForDateValidation(Bpartner partner,Errors errors)
	{
		Date date=DateUtil.getDateWithoutTime(new Date());
		if(partner==null)
		{
			validatorUtil.reject(errors, "invalid.partner", "Partner is null");
			return;
		}
		if (!CommonUtil.isCollectionEmpty(partner.getPartnerSignatory())) {
			for (PartnerSignatory signatory : partner.getPartnerSignatory()) {

				if (signatory.getValidFrom() != null && (signatory.getValidFrom().compareTo(date) < 0)) {
					validatorUtil.reject(errors, "invalid.validFrom", "Invalid Signatory Validity Date");

				}
			}
		}
		if (partner.getIsTrader().equalsIgnoreCase("Y")) {
			if (!CommonUtil.isCollectionEmpty(partner.getPartnerItemManufacturer())) {
				for (PartnerItemManufacturer manufacturer : partner.getPartnerItemManufacturer()) {
					if (!CommonUtil.isCollectionEmpty(manufacturer.getPartnerOrg())) {

						for (PartnerOrg org : manufacturer.getPartnerOrg()) {
							if (org.getLicenceValidityDate() != null && (org.getLicenceValidityDate().compareTo(date) < 0)) {
								validatorUtil.reject(errors, "invalid.validityDate","Invalid License Validity Date of Manufacturer Factory " + org.getName());
							}
						}
					}
				}
			}
		}
		if (partner.getIsManufacturer().equalsIgnoreCase("Y")) {
			if (!CommonUtil.isCollectionEmpty(partner.getPartnerOrg())) {
				for (PartnerOrg partnerOrg : partner.getPartnerOrg()) {
					if (partnerOrg.getLicenceValidityDate() != null && (partnerOrg.getLicenceValidityDate().compareTo(date) < 0)) {
						validatorUtil.reject(errors,"invalid.licenseValidityDate","Invalid " + partnerOrg.getName() + " Factory License Validity Date");
					}
					
					if (!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgCertification())) {
						for (PartnerOrgCertification cert : partnerOrg.getPartnerOrgCertification()) {
							if (cert.getIsoValidityDate() != null && (cert.getIsoValidityDate().compareTo(date)<=0)) {
								validatorUtil.reject(errors, "invalid.isoValidityDate","Invalid Validity Date Of ISO Certification");
							}
						}
					}
					if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRegistration())){
						for(PartnerOrgRegistration reg:partnerOrg.getPartnerOrgRegistration())
						{
							if(reg.getIssueDate()!=null && (reg.getIssueDate().compareTo(partnerOrg.getEstdDate())<0))
							{
								validatorUtil.reject(errors, "invalid.validFrom","Issue date of factory registraion "+reg.getValidityType()+" should be equal or greater than factory established date");
							}
						}
					}
					if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgBIS())){
		    			 for(PartnerOrgBIS bis:partnerOrg.getPartnerOrgBIS())
		       			 {
		       				if(bis.getValidTo()!=null && (bis.getValidTo().compareTo(date)<0))
		       				{
		       					validatorUtil.reject(errors, "invalid.validTo","Invalid Validity Date Of BIS License "+bis.getBisLicenceNo());
		           			}
		       				if(bis.getValidFrom()!=null && (bis.getValidFrom().compareTo(partnerOrg.getEstdDate())<0))
		       				{
		       					validatorUtil.reject(errors, "invalid.validFrom","Issue date of BIS license "+bis.getBisLicenceNo()+" should be equal or greater than factory established date");
		           			}
		       			 }
		    		 }
					if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRDAEC())){
		    			 for(PartnerOrgRDAEC rdac:partnerOrg.getPartnerOrgRDAEC())
		       			 {
		       				if(rdac.getValidTo()!=null && (rdac.getValidTo().compareTo(date)<0))
		       				{
		       					validatorUtil.reject(errors, "invalid.validTo","Invalid Validity Date of RDAEC ");
		           			}
		       			 }
		    		 }
					if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgOE())){
		    			 for(PartnerOrgOE oe:partnerOrg.getPartnerOrgOE())
		       			 {
		       				if(oe.getValidTo()!=null && (oe.getValidTo().compareTo(date)<0))
		       				{
		       					validatorUtil.reject(errors, "invalid.validTo","Invalid Validity Date of Other Eligibility Details");
		           			}
		       				
		       			 }
		    		 }
				}
			}
   			
   		 }
	}
	public void checkForPartnerRenewal(Bpartner partner,Errors errors)
	{
		if((partner.getIsRenewed() != null && partner.getIsRenewed().equals("N")) || 
		(partner.getValidTo()!=null && DateUtil.getDateWithoutTime(partner.getValidTo()).compareTo(DateUtil.getDateWithoutTime(new Date()))<=0)
		|| (partner.getIsTraderRenewed()!=null && partner.getIsTraderRenewed().equals("N"))) {
			if(partner.getPaymentDetails() != null) {
				/*int count = 0;*/
				int result=0;
				for (PaymentDetail pd : partner.getPaymentDetails()) {
					/*if (partner.getIsManufacturer().equals("Y") && pd.getPaymentType() != null
							&& !pd.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE)
							&& !pd.getVendorTypePayment().equals("MP")) 
					{
						count++;
					}*/
					if (partner.getIsTrader().equals("Y") && pd.getPaymentType() != null
							&& !pd.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE)
							&& !pd.getVendorTypePayment().equals("TP")) 
					{
						result++;
					}
				}
				/*if (count > 0) {
					validatorUtil.reject(errors,"invalid.type","Manufacturer Renewal Payment Not Added");
				}*/
				if (result > 0) {
					validatorUtil.reject(errors,"invalid.type","Trader Renewal Payment Not Added");
				}
			
			}
		}
		
		if(partner.getIsManufacturer().equals("Y"))
		{
			if(!CommonUtil.isCollectionEmpty(partner.getPartnerOrg()))
			{
				for(PartnerOrg org:partner.getPartnerOrg())
				{
					if((org.getValidTo()!=null && DateUtil.getDateWithoutTime(org.getValidTo()).compareTo(DateUtil.getDateWithoutTime(new Date()))<=0)
					  ||(org.getIsRenewed()!=null && org.getIsRenewed().equals("N")))
					{
						validatorUtil.reject(errors,"invalid.type","Renewal Payment Not Added For Factory "+org.getName());
					}
				}
			}
		}
	}
	public void checkVendorValidity(BPartnerDto partner,Errors errors){
		if((partner.getValue() == null || !partner.getValue().equals(ContextConstant.USER_TYPE_INTERNAL))
		&& ((partner.getValidTo()!=null && (DateUtil.getDateWithoutTime(partner.getValidTo()).compareTo(DateUtil.getDateWithoutTime(new Date()))<0))
		|| (partner.getIsRenewed() != null && partner.getIsRenewed().equals("N")))){
			validatorUtil.reject(errors,"invalid","Invalid Vendor Validitiy Date");
			return;
		}
		if(partner.getIsManufacturer().equals("Y")){
			Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerId", partner.getbPartnerId());
			param.put("date",DateUtil.getDateWithoutTime(new Date()));
			List<PartnerOrgDto> orgs=partnerOrgService.findDtos("getQueryForOrgValidity",param);
			if(!CommonUtil.isCollectionEmpty(orgs))
			{
				validatorUtil.reject(errors,"invalid","Invalid Factory Validitiy Date");
			}
		}
		
	}
}
