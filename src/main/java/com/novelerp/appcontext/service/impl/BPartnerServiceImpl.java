package com.novelerp.appcontext.service.impl;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dao.BPartnerDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.appcontext.validator.PartnerValidator;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ErrorDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.msedcl.sap.ZVENDORCREATEResponse;
import com.novelerp.eat.service.SMSService;
import com.novelerp.eat.service.SapVendorDetailService;

@Service
public class BPartnerServiceImpl extends AbstractServiceImpl<Bpartner, BPartnerDto> implements BPartnerService {
	
	private static final Logger log=LoggerFactory.getLogger(BPartnerServiceImpl.class);
	
	@Autowired
	private BPartnerDao bpartnerDao;
	
	@Autowired
	private PartnerConverter partnerConverter;
	
	@Autowired
	 @Qualifier("jwtUserContext") 
	private ContextService contextService;
	
	@Autowired
	private AppContext appContext;
	@Autowired
	private PartnerValidator partnerValidator;
	
	@Autowired
	private UserRolesService userRolesService;
	@Autowired
	private SapVendorDetailService sapVendorDetailService;
	
	@Autowired
	private MailService mailService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	private SMSService smsService;
	public static final String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	@PostConstruct
	private void init() {
		log.debug("Initializing Partner service");
		super.init(BPartnerServiceImpl.class, bpartnerDao, Bpartner.class, BPartnerDto.class);
		/*setObjectConverter(partnerConverter);*/
		setByPassProxy(true);
	}
	
	public BPartnerDto getPartnerWithBPDetails (Long bPartnerId){
		return getDto(bpartnerDao.getPartnerWithBPDetails(bPartnerId));
	}

	@Override
	public boolean update(BPartnerDto dto) {
		dto.setUpdatedBy(contextService.getUser());
		dto.setCreatedBy(contextService.getPartner().getCreatedBy());
		return super.update(dto);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BPartnerDto updateDto(BPartnerDto dto) {
		dto.setUpdatedBy(contextService.getUser());
		dto.setCreatedBy(contextService.getPartner().getCreatedBy());
		return super.updateDto(dto);
	}

	@Override
	@Transactional
	public ResponseDto submitPartnerDetails(BPartnerDto dto) {
		
		    ResponseDto response=new ResponseDto();
		    Bpartner partner = bpartnerDao.getPartnerWithDetails(dto.getbPartnerId());
		    List<ErrorDto> errorList=new ArrayList<>();
			errorList=checkPartnerDetails(partner);
			response.setMessage("Following Tabs Are Not Filled: ");
			Errors errors =  new Errors();
			if(CommonUtil.isListEmpty(errorList))
			{
				partnerValidator.checkForDateValidation(partner,errors);
				errorList.addAll(errors.getErrorList());
				response.setMessage("Following Tabs Have Invalid Date: ");
			}
			if(CommonUtil.isListEmpty(errorList))
			{		
				partnerValidator.checkForPartnerRenewal(partner, errors);
				errorList.addAll(errors.getErrorList());
				response.setMessage("Following Type Payment Not Created");
			}
			if(CommonUtil.isListEmpty(errorList))
			{
				response.setHasError(false);
				partner.setStatus(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
				partner=checkPartnerForAutoApprove(partner);
     			/*Attachment attachment=new Attachment();
				attachment.setAttachmentId(dto.getPartnerSignCopy().getAttachmentId());
				partner.setPartnerSignCopy(attachment);*/
				updateEntity(partner);
				if((partner.getIsContractor().equalsIgnoreCase("Y") || partner.getIsCustomer().equalsIgnoreCase("Y"))&& !(partner.getIsManufacturer().equalsIgnoreCase("Y") ||partner.getIsTrader().equalsIgnoreCase("Y")))
				{
					response.setMessage("Your Registration Profile Has Been Submitted..Please Re-login to Continue!");
				}else{
					response.setMessage("Your Registration Profile Has Been Submitted!");
				}
			}else{
				response.setHasError(true);
				response.setErrors(errorList);
			}
			
		return response;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	private Bpartner checkPartnerForAutoApprove(Bpartner partner)
	{
		
		if(partner.getRegistrationType().equalsIgnoreCase(AppBaseConstant.PARTICIPANT))
		{
			   updatePartnerRole(partner.getbPartnerId(),partner.getRegistrationType());
			   partner.setIsApproved(AppBaseConstant.APPROVED_STATUS);
			   partner.setIsCEApproved(AppBaseConstant.APPROVED_STATUS);
		       partner.setStatus(AppBaseConstant.PARTNER_STATUS_COMPLETE);
		       partner.setValidDate(new Timestamp(System.currentTimeMillis()));
		       partner.setRemarks("Partner is Auto Approved");
				
		}
		return partner;
	}
	private List<ErrorDto> checkPartnerDetails(Bpartner partner)
	{
	    List<ErrorDto> errors=new ArrayList<ErrorDto>();
	    if(partner==null)
	    {
	    	ErrorDto err=new ErrorDto();
			err.setErrorMessage("Partner is Empty");
			errors.add(err);
			return errors;
	    }
	    if(partner.getIsProfileUpdated()==null)
	    {
	    	ErrorDto err=new ErrorDto();
			err.setErrorMessage("Company Details Not Updated");
			errors.add(err);
	    }
		if(CommonUtil.isCollectionEmpty(partner.getPartnerCompanyContact()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Company Contact");
			errors.add(err);
		}
		if(CommonUtil.isCollectionEmpty(partner.getPartnerCompanyAddress()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Company Address");
			errors.add(err);
			
		}
		if(CommonUtil.isCollectionEmpty(partner.getPartnerBankDetail()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Bank Details");
			errors.add(err);
			
		}
		if(AppBaseConstant.CREATER.equals(partner.getRegistrationType())){
			if(CommonUtil.isCollectionEmpty(partner.getPaymentDetails()))
			{
				ErrorDto err=new ErrorDto();
				err.setErrorMessage("Payment Details");
				errors.add(err);
				
			}
		}
	  return errors;
		/*if(CommonUtil.isCollectionEmpty(partner.getPartnerSignatory()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Digital Signatory");
			errors.add(err);
			
		}*/
		/*if(!CommonUtil.isCollectionEmpty(partner.getPartnerSignatory())){
			ErrorDto err=new ErrorDto();
		    boolean dsStatus=false;
		    boolean paStatus=false;
			Set<PartnerSignatory> partnerSignatory=partner.getPartnerSignatory();
			if(partner.getIsManufacturer().equalsIgnoreCase("Y") || partner.getIsTrader().equalsIgnoreCase("Y")){
				for(PartnerSignatory ps:partnerSignatory){
					if(ps.getHolderType().equalsIgnoreCase("POA")){
						paStatus=true;
					}if(ps.getHolderType().equalsIgnoreCase("DS")){
						dsStatus=true;
					}
				}
				if(!dsStatus){
					err.setErrorMessage("Digital Signatory");
					errors.add(err);
				}
				if(!paStatus){
					err.setErrorMessage("Power of Attorney");
					errors.add(err);
				}
		   }
		}*/
		/*if(CommonUtil.isCollectionEmpty(partner.getDirectoryDetails()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Management Details");
			errors.add(err);
		}*/
		/*if (partner.getIsManufacturer().equalsIgnoreCase("Y") || partner.getIsTrader().equalsIgnoreCase("Y")){
			if (CommonUtil.isCollectionEmpty(partner.getPaymentDetails())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage("Vendor Payment");
				errors.add(err);
			}else{
				if (partner.getIsTrader().equals("Y")) {
					int count = 0;
					for (PaymentDetail paymentDetail : partner.getPaymentDetails()) {
						if (paymentDetail.getVendorTypePayment()!=null && paymentDetail.getVendorTypePayment().equals(AppBaseConstant.TRADER_PAYMENT)
								&& partner.getIsTrader().equals("Y")) {
							count++;
							break;
						}
					}
					if (count == 0 && partner.getIsTrader().equals("Y")) {
						ErrorDto err = new ErrorDto();
						err.setErrorMessage("Vendor Payment For Trader");
						errors.add(err);
					}
				}
			}
		}
		if (partner.getIsManufacturer().equalsIgnoreCase("Y")) {
			if (CommonUtil.isCollectionEmpty(partner.getPartnerOrg())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage("Factory Details");
				errors.add(err);

			} else {

				errors.addAll(checkPartnerOrgDetails(partner.getPartnerOrg()));
			}
		}*/
		/*if (partner.getIsManufacturer().equalsIgnoreCase("Y") || partner.getIsTrader().equalsIgnoreCase("Y")){
			if (CommonUtil.isCollectionEmpty(partner.getPartnerOrgPBG())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage("PBG Details");
				errors.add(err);

			}
		}*/
		/*if (partner.getIsTrader().equalsIgnoreCase("Y")) {
			if (CommonUtil.isCollectionEmpty(partner.getPartnerItemManufacturer())) {
				ErrorDto err = new ErrorDto();
				err.setErrorMessage("Trading Items");
				errors.add(err);
				
			}else{
				 for(PartnerItemManufacturer manufacturer:partner.getPartnerItemManufacturer())
		    	 {
					  if (CommonUtil.isCollectionEmpty(manufacturer.getPartnerOrg())) {
					    ErrorDto err = new ErrorDto();
						err.setErrorMessage("Manufacturer Factory Details");
						errors.add(err);
					 }
		    	 }
			}
			
		}
		if(partner.getIsManufacturer().equalsIgnoreCase("Y") || partner.getIsTrader().equalsIgnoreCase("Y"))
		{
		if(CommonUtil.isCollectionEmpty(partner.getPartnerFinancialAttachment()))
		{
			ErrorDto err=new ErrorDto();
			err.setErrorMessage("Financial Details");
			errors.add(err);
			
		}
		else{
			for(PartnerFinancialAttachment attachment:partner.getPartnerFinancialAttachment())
			{
				if(attachment.getFinacialType().equals("TD"))
				{
					if((attachment.getAmount()*100000)>=AppBaseConstant.TURNOVER_AMOUNT && (partner.getIsGstApplicable()==null || partner.getIsGstApplicable().equals("N")))
					{
						ErrorDto err = new ErrorDto();
						err.setErrorMessage("GST Is Applicable For Given Turn Over Amount");
						errors.add(err);
					}
				}
			}
		}
		}*/
			
	}
    /* private List<ErrorDto> checkPartnerOrgDetails(Set<PartnerOrg> orgs)
     {
    	 List<ErrorDto> errors=new ArrayList<ErrorDto>();
    	 for(PartnerOrg partnerOrg:orgs)
    	 {
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgUser()))
    		 {
    		    ErrorDto err=new ErrorDto();
    			err.setErrorMessage("Factory Contact User");
    			errors.add(err);
       		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgExperience()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("Factory Experience Details");
     			 errors.add(err);
    			
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgCertification()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("ISO Certification");
     			 errors.add(err);
    			
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgBIS()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("BIS Details");
     			 errors.add(err);
    			 
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRegistration()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("Factory Registration");
     			 errors.add(err);
       		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgProduct()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("Item Manufacturer");
     			 errors.add(err);
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgPerformance()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("Past Performance");
     			 errors.add(err);
    			
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRDAEC()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("RDAEC");
     			 errors.add(err);
    			
    		 }
    		 if(CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgOE()))
    		 {
    			 ErrorDto err=new ErrorDto();
     			 err.setErrorMessage("Other Eligibility Details");
     			 errors.add(err);
    			 
    		 }
    			 
    	 }
    	
    	 return errors;
     }*/

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseDto partnerRegistrationApproval(BPartnerDto partner, BPartnerDto dto) {
		ResponseDto response=new ResponseDto();
		RoleDto role = contextService.getDefaultRole();
		List<ErrorDto> errorList=new ArrayList<ErrorDto>();
		/*Errors errors=new Errors();
		Bpartner dbPartner=new Bpartner();*/
		/*if(dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS))
		{   
	     Map<String, Object> params=AbstractServiceImpl.getParamMap("bPartnerId", dto.getbPartnerId());
		 dbPartner=findEntityByMethod("getPartnerWithDetailsForAproval", params);
		 partnerValidator.checkForDateValidation(dbPartner, errors);
		 errorList.addAll(errors.getErrorList());
		 response.setMessage("Please do clarification or rejection as following tabs have invalid validity date: ");
		 if(CommonUtil.isCollectionEmpty(errors.getErrorList()))
		 {
			errorList=checkForApprovedPartnerDetails(dbPartner);
			response.setMessage("Please do clarification or rejection as you have not approved following tabs:");
		 }
		}*/
		 if(CommonUtil.isCollectionEmpty(errorList)){
			 
			 return processPartnerForApproval(dto,role,partner.getRegistrationType());
		 }else{
			 response.setErrors(errorList);
			 response.setHasError(true);
			 return response;
		 }
		
	}
	
	
	private List<ErrorDto> checkForApprovedPartnerDetails(Bpartner partner)
	{       
		      RoleDto role=contextService.getDefaultRole();
		      List<ErrorDto> errors=new ArrayList<ErrorDto>();
		      
			  ErrorDto err=new ErrorDto();
			  if((partner.getIsEECompDetailApproved()==null || partner.getIsEECompDetailApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    {
			       err.setErrorMessage("Company Details");
			       errors.add(err);
			    }else if((partner.getIsCECompDetailApproved()==null || partner.getIsCECompDetailApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			       err.setErrorMessage("Company Details");
			       errors.add(err);
			    }
			  if(!CommonUtil.isCollectionEmpty(partner.getPartnerCompanyContact()))
				{
			       for(UserDetails compContact:partner.getPartnerCompanyContact())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   /*if((compContact.getIsEEApproved()==null || compContact.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage("Company Contact - "+compContact.getFirstName()+""+compContact.getLastName());
			    		   errors.add(error);
			    	   }else */
			    	   if((compContact.getIsCEApproved()==null || compContact.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_NOVEL_ADMIN)){
			    		   error.setErrorMessage("Company Contact -"+compContact.getFirstName()+""+compContact.getLastName());
			    		   errors.add(error);
			    	   }
			    	   
			       }
				}
		      if(!CommonUtil.isCollectionEmpty(partner.getPartnerCompanyAddress()))
				{
			       for(PartnerCompanyAddress compAddress:partner.getPartnerCompanyAddress())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   /*if((compAddress.getIsEEApproved()==null || compAddress.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage("Company Address - "+compAddress.getLocation().getAddress1());
			    		   errors.add(error);
			    	   }else*/
			    	   if((compAddress.getIsCEApproved()==null || compAddress.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_NOVEL_ADMIN)){
			    		   error.setErrorMessage("Company Address "+compAddress.getLocation().getAddress1());
			    		   errors.add(error);
			    	   }
			    	 
			       }
				}
		      if(!CommonUtil.isCollectionEmpty(partner.getPartnerBankDetail()))
				{
			       for(PartnerBankDetail partnerBankDetail:partner.getPartnerBankDetail())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   /*if((partnerBankDetail.getIsEEApproved()==null || partnerBankDetail.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Bank Details - "+partnerBankDetail.getBankName());
			    		   errors.add(error);
			    	   }else */
			    	   if((partnerBankDetail.getIsCEApproved()==null || partnerBankDetail.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_NOVEL_ADMIN)){
			    		   error.setErrorMessage(" Bank Details" );
			    		   errors.add(error);
			    	   }
			    	  
			       }
				}
		      return errors;
		     /* if(!CommonUtil.isCollectionEmpty(partner.getPartnerSignatory()))
				{
			       for(PartnerSignatory signatory:partner.getPartnerSignatory())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   if((signatory.getIsEEApproved()==null || signatory.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Digital Signatory "+signatory.getUserDetail().getFirstName());
			    		   errors.add(error);
			    	   }else if((signatory.getIsCEApproved()==null || signatory.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			    		   error.setErrorMessage(" Digital Signatory "+signatory.getUserDetail().getFirstName());
			    		   errors.add(error);
			    	   }
			    	   
			       }
				}
		      if(!CommonUtil.isCollectionEmpty(partner.getDirectoryDetails()))
				{
			       for(UserDetails directoryDetails:partner.getDirectoryDetails())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   if((directoryDetails.getIsEEApproved()==null || directoryDetails.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Management Details "+directoryDetails.getFirstName());
			    		   errors.add(error);
			    	   }else if((directoryDetails.getIsCEApproved()==null || directoryDetails.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			    		   error.setErrorMessage("  Management Details "+directoryDetails.getFirstName());
			    		   errors.add(error);
			    	   }
			    	   
			       }
				}
		      if(!CommonUtil.isCollectionEmpty(partner.getPartnerFinancialAttachment()))
				{
			       for(PartnerFinancialAttachment financialAttachment:partner.getPartnerFinancialAttachment())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   if((financialAttachment.getIsEEApproved()==null || financialAttachment.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Financial Details ");
			    		   errors.add(error);
			    	   }else if((financialAttachment.getIsCEApproved()==null || financialAttachment.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			    		   error.setErrorMessage(" Financial Details ");
			    		   errors.add(error);
			    	   }
			    	  
			       }
				}
		      */
		     
		    /*  if(partner.getIsTrader().equals("Y"))
		      {
		      if(!CommonUtil.isCollectionEmpty(partner.getPartnerItemManufacturer()))
				{
			       for(PartnerItemManufacturer partnerItemManufacturer:partner.getPartnerItemManufacturer())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   if((partnerItemManufacturer.getIsEEApproved()==null || partnerItemManufacturer.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Trading Items "+partnerItemManufacturer.getName());
			    		   errors.add(error);
			    	   }else if((partnerItemManufacturer.getIsCEApproved()==null || partnerItemManufacturer.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			    		   error.setErrorMessage(" "+partnerItemManufacturer.getName());
			    		   errors.add(error);
			    	   }
			    	   
			    	   if(!CommonUtil.isCollectionEmpty(partnerItemManufacturer.getPartnerOrg()))
			    	   {
			    		   for(PartnerOrg partnerOrg:partnerItemManufacturer.getPartnerOrg())
			    		   {
				    		   ErrorDto errorDto=new ErrorDto();
					    	   if((partnerOrg.getIsEEApproved()==null || partnerOrg.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
					    	   {
					    		   errorDto.setErrorMessage(" Trading Items "+partnerOrg.getName());
					    		   errors.add(errorDto);
					    	   }else if((partnerOrg.getIsCEApproved()==null || partnerOrg.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
					    		   errorDto.setErrorMessage(" "+partnerOrg.getName());
					    		   errors.add(errorDto);
					    	   }
			    		   }
			    	   }
			       }
				}
		      }
		      if(partner.getIsManufacturer().equals("Y"))
		      {
		      if(!CommonUtil.isCollectionEmpty(partner.getPartnerOrg()))
				{
			       for(PartnerOrg partnerOrg:partner.getPartnerOrg())   
			       {
			    	   ErrorDto error=new ErrorDto();
			    	   if(!CommonUtil.isStringEmpty(partnerOrg.getPaymentDetail().getIsFAApproved()) && partnerOrg.getPaymentDetail().getIsFAApproved().equals("Y")){
			    	   if((partnerOrg.getIsEEApproved()==null || partnerOrg.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
			    	   {
			    		   error.setErrorMessage(" Factory Details For Factory -"+partnerOrg.getName());
			    		   errors.add(error);
			    	   }else if((partnerOrg.getIsCEApproved()==null || partnerOrg.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
			    		   error.setErrorMessage(" Factory Details For Factory - "+partnerOrg.getName());
			    		   errors.add(error);
			    	   }
			    	   errors=checkPartnerOrgApproval(partnerOrg,errors);
			    	   }
			       }
			      
				}
					
				
		      }*/
		
	}
	/*private List<ErrorDto> checkPartnerOrgApproval(PartnerOrg partnerOrg,List<ErrorDto> errors)
	{
		RoleDto role=contextService.getDefaultRole();
	
		for(PartnerOrg partnerOrg:partner.getPartnerOrg())
		{
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgUser()))
			{
		       for(PartnerOrgUser partnerOrgUser:partnerOrg.getPartnerOrgUser())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgUser.getIsEEApproved()==null || partnerOrgUser.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Factory Contact Person For- "+partnerOrgUser.getUserDetail().getFirstName());
		    		   errors.add(error);
		    	   }else if((partnerOrgUser.getIsCEApproved()==null || partnerOrgUser.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Factory Contact Person For- "+partnerOrgUser.getUserDetail().getFirstName());
		    		   errors.add(error);
		    	   }
		    	   
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgExperience()))
			{
		       for(PartnerOrgExperience partnerOrgExp:partnerOrg.getPartnerOrgExperience())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgExp.getIsEEApproved()==null || partnerOrgExp.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Factory Experience "+partnerOrgExp.getExpDesignYear());
		    		   errors.add(error);
		    	   }else if((partnerOrgExp.getIsCEApproved()==null || partnerOrgExp.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Factory Experience "+partnerOrgExp.getExpDesignYear());
		    		   errors.add(error);
		    	   }
		    	  
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgCertification()))
			{
		       for(PartnerOrgCertification partnerOrgCertification:partnerOrg.getPartnerOrgCertification())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgCertification.getIsEEApproved()==null || partnerOrgCertification.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Factory ISO Certification  "+partnerOrgCertification.getIsoName());
		    		   errors.add(error);
		    	   }else if((partnerOrgCertification.getIsCEApproved()==null || partnerOrgCertification.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage("  Factory ISO Certification "+partnerOrgCertification.getIsoName());
		    		   errors.add(error);
		    	   }
		    	   
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgBIS()))
			{
		       for(PartnerOrgBIS partnerOrgBIS:partnerOrg.getPartnerOrgBIS())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgBIS.getIsEEApproved()==null || partnerOrgBIS.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Factory BIS Details "+partnerOrgBIS.getBisLicenceNo());
		    		   errors.add(error);
		    	   }else if((partnerOrgBIS.getIsCEApproved()==null || partnerOrgBIS.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Factory BIS Details "+partnerOrgBIS.getBisLicenceNo());
		    		   errors.add(error);
		    	   }
		    	   
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRegistration()))
			{
		       for(PartnerOrgRegistration partnerOrgRegistration:partnerOrg.getPartnerOrgRegistration())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgRegistration.getIsEEApproved()==null || partnerOrgRegistration.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Factory Registration "+partnerOrgRegistration.getRegistrationNo());
		    		   errors.add(error);
		    	   }else if((partnerOrgRegistration.getIsCEApproved()==null || partnerOrgRegistration.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Factory Registration "+partnerOrgRegistration.getRegistrationNo());
		    		   errors.add(error);
		    	   }
		    	   
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgProduct()))
			{
		       for(PartnerOrgProduct partnerOrgProduct:partnerOrg.getPartnerOrgProduct())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgProduct.getIsEEApproved()==null || partnerOrgProduct.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Item Manufacture ");
		    		   errors.add(error);
		    	   }else if((partnerOrgProduct.getIsCEApproved()==null || partnerOrgProduct.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Item Manufacture ");
		    		   errors.add(error);
		    	   }
		    	 
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgPerformance()))
			{
		       for(PartnerOrgPerformance partnerOrgPerformance:partnerOrg.getPartnerOrgPerformance())  
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgPerformance.getIsEEApproved()==null || partnerOrgPerformance.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" Past Performance "+partnerOrgPerformance.getFirmName());
		    		   errors.add(error);
		    	   }else if((partnerOrgPerformance.getIsCEApproved()==null || partnerOrgPerformance.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" Past Performance "+partnerOrgPerformance.getFirmName());
		    		   errors.add(error);
		    	   }
		    	   
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRDAEC()))
			{
		       for(PartnerOrgRDAEC partnerOrgRDAEC:partnerOrg.getPartnerOrgRDAEC())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgRDAEC.getIsEEApproved()==null || partnerOrgRDAEC.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" RDAEC "+partnerOrgRDAEC.getElegibilityType());
		    		   errors.add(error);
		    	   }else if((partnerOrgRDAEC.getIsCEApproved()==null || partnerOrgRDAEC.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" RDAEC "+partnerOrgRDAEC.getElegibilityType());
		    		   errors.add(error);
		    	   }
		    	  
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgOE()))
			{
		       for(PartnerOrgOE partnerOrgOE:partnerOrg.getPartnerOrgOE())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgOE.getIsEEApproved()==null || partnerOrgOE.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage(" OE "+partnerOrgOE.getOeType());
		    		   errors.add(error);
		    	   }else if((partnerOrgOE.getIsCEApproved()==null || partnerOrgOE.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage(" OE "+partnerOrgOE.getOeType());
		    		   errors.add(error);
		    	   }
		    	 
		       }
			
			}
          if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgInspection()))
			{
		       for(PartnerOrgInspection partnerOrgInspection:partnerOrg.getPartnerOrgInspection())   
		       {
		    	   ErrorDto error=new ErrorDto();
		    	   if((partnerOrgInspection.getIsEEApproved()==null || partnerOrgInspection.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
		    	   {
		    		   error.setErrorMessage("Org Inspection");
		    		   errors.add(error);
		    	   }else if((partnerOrgInspection.getIsCEApproved()==null || partnerOrgInspection.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		    		   error.setErrorMessage("Org Inspection");
		    		   errors.add(error);
		    	   }
		    	 
		       }
			
			}else{
				ErrorDto error=new ErrorDto();
				error.setErrorMessage("Org Inspection");
	    	    errors.add(error);
			}
          
         
		  }
         return errors;
	}*/
	@Transactional(propagation=Propagation.SUPPORTS)
	private ResponseDto processPartnerForApproval(BPartnerDto dto,RoleDto role,String regType)
    {
		/*if(dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS) && (role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_NOVEL_ADMIN))) {
				if (!updatePartnerRole(dto.getbPartnerId(),dto.getRegistrationType())) {
					return new ResponseDto(false, "Partner has Already Assigned Role");
				}
		}*/
		Map<String, Object> map=new HashMap<>();
		ResponseDto response=new ResponseDto();
    	if(dto.getIsApproved().equals(AppBaseConstant.REJECTED_STATUS) && role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_NOVEL_ADMIN)) {
				
				map.put("validDate", new Timestamp(System.currentTimeMillis()));
				map.put("remarks", dto.getRemarks());
				map.put("isApproved", dto.getIsApproved());
				map.put("status", AppBaseConstant.PARTNER_STATUS_REJECT);
				map.put("isCEApproved", dto.getIsApproved());
				map.put("ceComment", dto.getRemarks());
				response.setHasError(false);
				response.setMessage( "Partner Has Been Rejected !");
		  }else if((dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS) || dto.getIsApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)) && role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_NOVEL_ADMIN))
		  {
			      map.put("isCEApproved", dto.getIsApproved());
				  map.put("ceComment", dto.getRemarks());
				  map.put("validDate", new Timestamp(System.currentTimeMillis()));
				  map.put("remarks", dto.getRemarks());
				  map.put("isApproved", dto.getIsApproved());
				  if(dto.getIsApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
				  {
					  map.put("status", AppBaseConstant.CE_CLARIFICATION_STATUS);
				  }else{
					  updatePartnerRole(dto.getbPartnerId(),regType);
					  map.put("status", AppBaseConstant.PARTNER_STATUS_COMPLETE);
					  map.put("ceSignCopy.attachmentId", dto.getCeSignCopy().getAttachmentId());
				  }
				  
				  if(dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS))
				  {
                    Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, 1);
				    map.put("validTo", new Timestamp(cal.getTimeInMillis()));
				  } 
				response.setHasError(false);
				if(dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS))
				{
				  response.setMessage( "Partner Has Been Approved !");
				}
				if(dto.getIsApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
				{
					response.setMessage( "Partner Has Been Clarified !");
				}
		    }		
		    int updateValue=updateByJpql(map, "bPartnerId", dto.getbPartnerId());
			if (updateValue>0){
				return response;
            }else
              {
            	return new ResponseDto(true, "Error In Approval.....!");
              }
    }
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public boolean updatePartnerRole(Long bPartnerId,String registrationType) {
		RoleDto role = null;
		
		try {
		if (!CommonUtil.isStringEmpty(registrationType) && registrationType.equals(AppBaseConstant.CREATER)) {
			role = appContext.getRole(AppBaseConstant.ROLE_PARTNER_ADMIN);
			System.out.println("registrationType if role:" +role);
		} else if(!CommonUtil.isStringEmpty(registrationType) && registrationType.equals(AppBaseConstant.PARTICIPANT)){
			role = appContext.getRole(AppBaseConstant.ROLE_VENDOR_ADMIN);
			System.out.println("registrationType else role:" +role);
		}
		if (role != null) {
			List<UserRolesDto> userRolesDto = userRolesService.findDtos("getQueryForUserRoleByPartnerId",
					getParamMap("bpartnerId", bPartnerId));
			
			for (UserRolesDto userRole : userRolesDto) {
				if (userRole.getRole().getValue().equals(ContextConstant.USER_TYPE_PATNER_USER)) {
					userRole.setRole(role);
					System.out.println("userRole for if Loop:" +role);
					return userRolesService.update(userRole);
				} else if (userRole.getRole().getValue().equals(AppBaseConstant.ROLE_VENDOR_ADMIN)
						|| userRole.getRole().getValue().equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
					System.out.println("userRole for else Loop:" +role);
					return true;
				}
			}
		}
		System.out.println("FTP updatePartnerRole Try Block:" );
		}catch (Exception e) {
			System.out.println("FTP updatePartnerRole catch Block:" +e.getMessage());
			e.printStackTrace();
			throw e;
		}
		System.out.println("return updatePartnerRole false");
		return false;
	}

	@Override
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId);
		String query=bpartnerDao.getQueryForPartnerApproval(role);
		List<BPartnerDto> partners=findDtosByQuery(query, params);
		return partners;
	}
	
	@Override
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId, int pageNumber, int pageSize) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId);
		String query=bpartnerDao.getQueryForPartnerApproval(role);
		List<BPartnerDto> partners=findDtosByQuery(query, params, pageNumber, pageSize);
		return partners;
	}
	
	@Override
	public Long getCountForPrtnrAprvl(Long bpartnerId) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId);
		String query = bpartnerDao.getPrtnrAprlCountWhereClaus(role);
		long recordCount = getRecordCount(query, params);
		return recordCount;
	}
	@Override
	public List<BPartnerDto> getPartnerForApproval(Long bpartnerId, int pageNumber, int pageSize,String searchMode,String searchValue) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId,searchMode,searchValue);
		String query=bpartnerDao.getQueryForPartnerApproval(role,searchMode,searchValue);
		List<BPartnerDto> partners=findDtosByQuery(query, params, pageNumber, pageSize);
		return partners;
	}
	
	@Override
	public Long getCountForPrtnrAprvl(Long bpartnerId,String searchMode,String searchValue) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId,searchMode,searchValue);
		String query = bpartnerDao.getPrtnrAprlCountWhereClaus(role, searchMode, searchValue);
		long recordCount = getRecordCount(query, params);
		return recordCount;
	}
	private Map<String,Object> getParameterMap(RoleDto role,Long bpartnerId,String searchMode,String searchValue){
		Map<String, Object> params = new HashMap<String, Object>();
				
		if(!CommonUtil.isStringEmpty(role.getValue())){
		if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
			{
				String isEEApproved=AppBaseConstant.APPROVED_STATUS;
				params.put("isEEApproved", isEEApproved);
			}
			if(role.getValue().equals(AppBaseConstant.ROLE_SYS_ADMIN))
			{
				params.put("bpartnerId", bpartnerId);
			}
		}
		if(!"none".equalsIgnoreCase(searchValue)){
			params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		}
		
		return params;
	}
	private Map<String,Object> getParameterMap(RoleDto role,Long bpartnerId){
		Map<String, Object> params = new HashMap<String, Object>();
				
		if(!CommonUtil.isStringEmpty(role.getValue())){
		if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
			{
				String isEEApproved=AppBaseConstant.APPROVED_STATUS;
				params.put("isEEApproved", isEEApproved);
			}
			if(role.getValue().equals(AppBaseConstant.ROLE_SYS_ADMIN))
			{
				params.put("bpartnerId", bpartnerId);
			}
		}
		
		return params;
	}
	
	
	@Override
	public boolean sendEmailToPartner(String emailId,String sub,String message){
		List<String> emailList=new ArrayList<String>();
		if(!"".equals(emailId)){
			emailList.add(emailId);
		MailDto mailDto = new MailDto();
		mailDto.setSubject(sub);
		mailDto.setMailContent(message);
		mailDto.setRecipientList(emailList);
		mailService.sendEmail(mailDto,true);
		return true;
	  }
	return false;
	}
	
	@Override
	public boolean sendEmailToPartner(List<String> emailList,String sub,String message){
		if(!CommonUtil.isCollectionEmpty(emailList)){
		MailDto mailDto = new MailDto();
		mailDto.setSubject(sub);
		mailDto.setMailContent(message);
		mailDto.setRecipientList(emailList);
		mailService.sendEmail(mailDto,true);
		return true;
	  }
	return false;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public BPartnerDto updatePartner(BPartnerDto partner) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null)
		{   partner.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return partner;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || role.getValue().equals(AppBaseConstant.ROLE_VENDOR_ADMIN))
		{
			partner.setIsCECompDetailApproved(null);
			partner.setIsEECompDetailApproved(null);
			partner.setCeCompDetailComment(null);
			partner.setEeCompDetailComment(null);
			partner.setIsProfileUpdated(AppBaseConstant.APPROVED_STATUS);
		}
		if(partner!=null && partner.getbPartnerId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_NOVEL_ADMIN)))
		{
			Map<String, Object> map=new HashMap<>();
			/*if (partner.getIsEECompDetailApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeCompDetailComment", partner.getEeCompDetailComment());
			  map.put("isEECompDetailApproved", partner.getIsEECompDetailApproved());
			}else */
			if (partner.getIsCECompDetailApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_NOVEL_ADMIN)) {
				  map.put("ceCompDetailComment", partner.getCeCompDetailComment());
				  map.put("isCECompDetailApproved", partner.getIsCECompDetailApproved());
			}
			int result= updateByJpql(map, "bPartnerId", partner.getbPartnerId());
			if(result>0)
			{
				partner.setResponse(new ResponseDto(false, "Record Updated"));
				return partner;
			}else{
				partner.setResponse(new ResponseDto(true, "Error in Updated"));
				return partner;
			}
		}
		return updateDto(partner);

	}

	@Override
	public List<BPartnerDto> getVendors(String status) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status", status);
		String query=bpartnerDao.getQueryForPartnersByStatus(role,status);
		List<BPartnerDto> partners=findDtosByQuery (query, param);
		return partners;
	}
	
	@Override
	public List<BPartnerDto> getVendors(String status, int pageNumber, int pageSize) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status", status);
		String query=bpartnerDao.getQueryForPartnersByStatus(role,status);
		List<BPartnerDto> partners=findDtosByQuery(query, param, pageNumber, pageSize);
		return partners;
	}
	@Override
	public List<BPartnerDto> getVendors(String status, int pageNumber, int pageSize, String searchMode,
			String searchValue) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status", status);
		if(!"none".equalsIgnoreCase(searchValue)){
			param.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		}
		String query=bpartnerDao.getQueryForPartnersByStatus(role,status,searchMode,searchValue);
		List<BPartnerDto> partners=findDtosByQuery(query, param, pageNumber, pageSize);
		return partners;
	}
	
	@Override
	public long getRecordCount(String status) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return 0;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status", status);
		String query = bpartnerDao.getRecordCountWhereClaus(role, status);
		long recordCount = getRecordCount(query, param);
		return recordCount;
	}
	@Override
	public long getRecordCount(String status,String searchMode,String searchValue) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return 0;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("status", status);
		if(!"none".equalsIgnoreCase(searchValue)){
			param.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		}
		String query = bpartnerDao.getRecordCountWhereClaus(role, status, searchMode, searchValue);
		long recordCount = getRecordCount(query, param);
		return recordCount;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletePartners(){
		try{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date toDate = formatter.parse(formatter.format(cal.getTime()));
			bpartnerDao.deletePartners(toDate);
			userService.deleteUsers(toDate);
		}catch(Exception e){
			log.error(e.toString());
		}
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateBpartnerRating(BPartnerDto bPartnerDto, double newRating){
		double oldRating = bPartnerDto.getRating();
		long oldRateCount = bPartnerDto.getRateCount(); 
		double updatedRating;
		updatedRating = ((oldRating*oldRateCount)+newRating)/(oldRateCount+1);
		Map<String, Object> map = new HashMap<>();
		map.put("rating", updatedRating);
		map.put("rateCount", oldRateCount+1);
		return updateByJpql(map, "bPartnerId",bPartnerDto.getbPartnerId());
	}
    

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean partnersRenewal(BPartnerDto dto) {
		
		if(dto.getIsTrader().equals("Y"))
		{
			Map<String, Object> map=getParamMap("isRenewed","N");
			map.put("isTraderRenewed", "N");
			int count=updateByJpql(map,"bPartnerId",dto.getbPartnerId());
			if(count>0){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<Object[]> getPartnerBankAndLocDetail(Long bpartnerId){
		return bpartnerDao.getPartnerLocAndBankDetail(bpartnerId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto updateVendorSapCode(ZVENDORCREATEResponse zvendorcreateResponse, SapVendorDetailDto sapVendorDetailDto){
		if(!CommonUtil.isStringEmpty(zvendorcreateResponse.getLIFNR())){
			Map<String, Object> map = new HashMap<>();
			map.put("vendorSapCode", zvendorcreateResponse.getLIFNR());
			updateByJpql(map, "bPartnerId", sapVendorDetailDto.getPartner().getbPartnerId());
			sapVendorDetailService.save(sapVendorDetailDto);
			return new ResponseDto(false, zvendorcreateResponse.getLVALLMSGS());
		}else{
			return new ResponseDto(true, zvendorcreateResponse.getLVALLMSGS());
		}
	}

	@Override
	public ResponseDto sendMailOnSubmit(ResponseDto response, BPartnerDto dto) {
	  try{
		Map<String, Object> params = new HashMap<>();
		params.put("bPartnerId", dto.getbPartnerId());
		UserDto user = userService.findDto("getQueryForUserByPartnerId", params);
		String email = user.getEmail() == null ? "" : user.getEmail();
		BPartnerDto partner = findDto(dto.getbPartnerId());
		String partnerStatus = partner == null ? "" : partner.getStatus();
		Map<String, Object> codeMap = AbstractServiceImpl.getParamMap("role", ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser = userService.findDto("getUserByRoleCode", codeMap);
		MailTemplateDto mailTemplate = new MailTemplateDto();
		Map<String, Object> map = new HashMap<>();
		if (user != null && partnerStatus.equals(AppBaseConstant.PARTNER_STATUS_COMPLETE)) {
			mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_AUTO_APPROVE_TEMPLATE);
			response.setMessage("Your Registration has been Approved");
		} else if (user != null && partnerStatus.equals(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS)) {
			mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_SUBMIT_TEMPLATE);
			map.put("@officeAddressByPartnerType@", internalUser.getUserDetails().getLocation().getAddress1());
			response.setMessage("Your Profile has been submitted.Kindly visit the head office for physical verfication along with all the required documents");
		}
		if (user != null && mailTemplate != null && internalUser != null) {
			map.put("@sourceCompanyName@", internalUser.getPartner().getName());
			map.put("@honour@", "");
			map.put("@address@", internalUser.getUserDetails().getLocation().getAddress1());
			map.put("@emailCorrCode@", "");
			map.put("@today@", today);
			map.put("@companyName@", user.getPartner().getName());
			map.put("@factoryName@,", "");
			map.put("@factoryCity@", "");
			map.put("@vendorEmail@", user.getEmail());
			map.put("@sourcingcompanyshortname@", internalUser.getPartner().getValue());
			map.put("@respectiveDeptEmail@", internalUser.getUserDetails().getFirstName() + " "+ internalUser.getUserDetails().getLastName());
			map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail() + "," + internalUser.getUserDetails().getMobileNo());
			map.put("@companytel@", internalUser.getUserDetails().getTelephone1());
			map.put("@companyfax@", internalUser.getUserDetails().getFax1());
			mailService.sentMailByTemplate(mailTemplate, map, email);
		}
		}catch (Exception e) {
			log.error(e.toString());
		}
		return response;
	}

	@Override
	public ResponseDto sendMailOnApproval(ResponseDto response, BPartnerDto dto) {
	 try{
		RoleDto role = contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
		 	return response;
		}
		if (dto.getbPartnerId() != null && !response.isHasError()
				&& (role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_CHIEF_ENGINEER)
						|| role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))) {
			Map<String, Object> params = new HashMap<>();
			params.put("bPartnerId", dto.getbPartnerId());
			UserDto user = userService.findDto("getQueryForUserByPartnerId", params);
			if (user != null && user.getEmail() != null) {

				MailTemplateDto template = null;
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> codeMap = AbstractServiceImpl.getParamMap("role",
						ContextConstant.USER_TYPE_SYSUSER);
				UserDto internalUser = userService.findDto("getUserByRoleCode", codeMap);
				if (dto.getIsApproved().equals(AppBaseConstant.APPROVED_STATUS)
						&& role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
					template = mailTemplateService
							.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_APPROVE_TEMPLATE);
					if(user.getUserDetails() != null){
						if(user.getUserDetails().getMobileNo() != null)
							smsService.sendSMS(user.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_NEW_PASSWORD, null);
					}
					

				} else if (dto.getIsApproved().equals(AppBaseConstant.CLARIFICATION_STATUS)
						&& role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
					template = mailTemplateService
							.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_CLARIFY_TEMPLATE);

				} else if (dto.getIsApproved().equals(AppBaseConstant.REJECTED_STATUS)) {
					template = mailTemplateService
							.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_REJECT_TEMPLATE);
				}
				if (template != null && internalUser != null) {
					map.put("@sourceCompanyName@", internalUser.getPartner().getName());
					map.put("@honour@", "");
					map.put("@address@", internalUser.getUserDetails().getLocation().getAddress1());
					map.put("@emailCorrCode@", "");
					map.put("@today@", today);
					map.put("@companyName@", user.getPartner().getName());
					map.put("@factoryName@,", "");
					map.put("@factoryCity@", "");
					map.put("@vendorEmail@", user.getEmail());
					map.put("@sourcingcompanyshortname@", internalUser.getPartner().getValue());
					map.put("@respectiveDeptEmail@", internalUser.getUserDetails().getFirstName() + " "
							+ internalUser.getUserDetails().getLastName());
					map.put("@respectiveDeptContact@", internalUser.getUserDetails().getEmail() + ","
							+ internalUser.getUserDetails().getMobileNo());
					map.put("@companytel@", internalUser.getUserDetails().getTelephone1());
					map.put("@companyfax@", internalUser.getUserDetails().getFax1());
					mailService.sentMailByTemplate(template, map, user.getEmail());
				}
			}
		}
	 }catch (Exception e) {
		log.error(e.toString());
	}
		return response;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updatePartnerForEditProfile(BPartnerDto dto,RoleDto role) {
		int updatedValue=0;
		try{
		Map<String, Object> partnerMap = new HashMap<>();
		if (dto.getStatus()!=null && dto.getStatus().equals(AppBaseConstant.PARTNER_STATUS_COMPLETE)
				&& role.getValue().equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
			partnerMap.put("status", AppBaseConstant.PARTNER_STATUS_EDIT);
		} else {
			partnerMap.put("status", AppBaseConstant.PARTNER_STATUS_DRAFTED);
		}
		partnerMap.put("isRegCompleted", "N");
		partnerMap.put("isApproved", null);
		partnerMap.put("isEEApproved", null);
		partnerMap.put("isCEApproved", null);

		updatedValue = updateByJpql(partnerMap, "bPartnerId", dto.getbPartnerId());
		}catch (Exception e) {
			log.error(e.toString());
		}
		return updatedValue;
     }

	@Override
	public void sendMailOnEditProfile(BPartnerDto dto) {
		try{
		Map<String, Object> params = new HashMap<>();
		params.put("bPartnerId", dto.getbPartnerId());
		UserDto user = userService.findDto("getQueryForUserByPartnerId", params);
		if (user != null && user.getEmail() != null) {
			Map<String, Object> codeMap = AbstractServiceImpl.getParamMap("role",
					ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser = userService.findDto("getUserByRoleCode", codeMap);
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_EDIT_TEMPLATE);
			Map<String, Object> map = new HashMap<>();
			if (mailTemplate != null && internalUser != null) {
				map.put("@sourceCompanyName@", internalUser.getPartner().getName());
				map.put("@honour@", "");
				map.put("@address@", internalUser.getUserDetails().getLocation().getAddress1());
				map.put("@emailCorrCode@", "");
				map.put("@today@", today);
				map.put("@companyName@", user.getPartner().getName());
				map.put("@factoryName@,", "");
				map.put("@factoryCity@", "");
				map.put("@vendorEmail@", user.getEmail());
				map.put("@sourcingcompanyshortname@", internalUser.getPartner().getValue());
				map.put("@respectiveDeptEmail@", internalUser.getUserDetails().getFirstName() + " "
						+ internalUser.getUserDetails().getLastName());
				map.put("@respectiveDeptContact@", internalUser.getUserDetails().getEmail() + ","
						+ internalUser.getUserDetails().getMobileNo());
				map.put("@companytel@", internalUser.getUserDetails().getTelephone1());
				map.put("@companyfax@", internalUser.getUserDetails().getFax1());
				mailService.sentMailByTemplate(mailTemplate, map, user.getEmail());
			}
		}
		}catch (Exception e) {
			log.error(e.toString());
		}
	}

	@Override
	public void sendMailOnVedorExpired(BPartnerDto partner,UserDto internalUser) {
		Map<String,Object> params=AbstractServiceImpl.getParamMap("bPartnerId",partner.getbPartnerId());
    	UserDto user=userService.findDto("getQueryForUserByPartnerId", params);
    	if(user!=null && user.getEmail()!=null){
    		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        	MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_EXPIRED_TEMPLATE);
            if(mailTemplate!=null && internalUser!=null)
            {
            	Map<String,Object> map=new HashMap<>();
            	map.put("@sourceCompanyName@",internalUser.getPartner().getName());
    			map.put("@honour@","");
    			map.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());
    			map.put("@emailCorrCode@","");
    			map.put("@today@",today);
    			map.put("@companyName@",user.getPartner().getName());
    			map.put("@factoryName@,","");
    			map.put("@factoryCity@","");
    			map.put("@vendorEmail@",user.getEmail());
    			map.put("@expiryDate@",today);
    			map.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
    			map.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
    			map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
    			map.put("@companytel@",internalUser.getUserDetails().getTelephone1());
    			map.put("@companyfax@",internalUser.getUserDetails().getFax1());
    			mailService.sentMailByTemplate(mailTemplate,map,user.getEmail());
            }
    	}
	}

	@Override
	public void sendMailOnRenewalReminder(UserDto user, UserDto internalUser) {
		MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_RENEWAL_REMINDER_TEMPLATE);
		String validTo=new SimpleDateFormat("dd-MM-yyyy").format(user.getPartner().getValidTo());
		if(mailTemplate!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@today@",today);
			params.put("@companyName@",user.getPartner().getName());
			params.put("@factoryName@,","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",user.getEmail());
			params.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
			params.put("@expiryDate@",validTo);
			params.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
			params.put("@companytel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyfax@",internalUser.getUserDetails().getFax1());
			mailService.sentMailByTemplate(mailTemplate, params, user.getEmail());
			}
		
		
	}

//	@Override
//	public List<BPartnerDto> getVendorListReportbyFilter(VendorCredentialReadDto dto) {
//		Map<String, Object> params=getParameterMap(dto);
//		List<String> param = new ArrayList<>();
//		
//		if(dto.getStatus().equals("In Progress")) {
//			param.add(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
//			params.put("status", param);
//		}
//		/*if(dto.getStatus().equals("Approved")) {
//			param.add(AppBaseConstant.PARTNER_STATUS_IN_PROGRESS);
//			params.put("status", param);
//		}*/
//		if(dto.getStatus().equals("Rejected")) {
//			param.add(AppBaseConstant.PARTNER_STATUS_REJECT);
//			params.put("status", param);
//		}
//		if(dto.getStatus().equals("Drafted")) {
//			param.add(AppBaseConstant.PARTNER_STATUS_DRAFTED);
//			params.put("status", param);
//		}
//		if(dto.getStatus().equals("Complete")) {
//			param.add(AppBaseConstant.PARTNER_STATUS_COMPLETE);
//			params.put("status", param);
//		}
//		
//		String query=bpartnerDao.getVendorCredentailReportlist(dto);
//		List<BPartnerDto> UserCredentialList=findDtosByQuery(query, params);
//		
//		return UserCredentialList;
//	}
//
//	private Map<String,Object> getParameterMap(VendorCredentialReadDto dto){
//		Map<String, Object> params = new HashMap<String, Object>();
////		if(dto.getDistrict()!=null && !dto.getDistrict().equals("")){
////			params.put("district", dto.getDistrict());
////		
////			}
////		
////		if(dto.getState()!=null && !dto.getState().equals("")){
////			params.put("state", dto.getState());
////   		}
//		
//		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
//   			params.put("vendorCode",dto.getVendorCode());
//   		}
//		
////		if(dto.getMailID()!=null && !dto.getMailID().equals("")){
////   			params.put("mailID",dto.getMailID());
////   		}
////		
////		if(dto.getStatus()!=null && !dto.getStatus().equals("")) {
////   			params.put("status",dto.getStatus());
////   		}
//		return params;
//	}


	

}
