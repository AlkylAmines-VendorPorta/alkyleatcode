package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgInspectionDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appbase.master.entity.PartnerOrgExperience;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Varsha Patil
 *
 */
@Component
public class PartnerOrgInspectionValidator implements Validator{

	@Autowired
	private ValidationUtil validator;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerOrgInspectionDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerOrgInspectionDto partnerOrgInspection=(PartnerOrgInspectionDto) object;
		checkForApproval(partnerOrgInspection,errors);
		checkForOrgAllDetailsApproval(partnerOrgInspection, errors);
	}
   private void checkForApproval(PartnerOrgInspectionDto partnerOrgInspection,Errors errors)
   {
	   
	    if(partnerOrgInspection.getPartnerOrg()!=null || partnerOrgInspection.getPartnerOrg().getPartnerOrgId()!=null)
	    {
	       PartnerOrgDto partnerOrg=partnerOrgService.findDto(partnerOrgInspection.getPartnerOrg().getPartnerOrgId());
	       if(partnerOrg.getIsFactoryInspected()!=null && partnerOrg.getIsFactoryInspected().equals("N"))
	       {
	    	  return; 
	       }else{
	    	  checkForOrgInspection(partnerOrgInspection,errors); 
	       }
	    }
   }
   private void checkForOrgInspection(PartnerOrgInspectionDto partnerOrgInspection,Errors errors)
   {
	   RoleDto role=contextService.getDefaultRole();
	   if(role==null || role.getValue()==null){
		   validator.reject(errors, "no.session", "Session Timeout");
		   return;
		}   
	    if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER))
	    {
	    if(!CommonUtil.isStringEmpty(partnerOrgInspection.getIsEEApproved()) && partnerOrgInspection.getIsEEApproved().equals("Y"))
	    {
		   if(!partnerOrgInspection.getIsNoteReceived().equals("Y") || !partnerOrgInspection.getIsCapableForManufacturing().equals("Y")
		 || !partnerOrgInspection.getIsCalibrationCertified().equals("Y")|| !partnerOrgInspection.getIsAllInspectionReportFilled().equals("Y") ||
		 !partnerOrgInspection.getIsAdequetTesting().equals("Y") ||  !partnerOrgInspection.getIsMachineWorking().equals("Y")||
		 !partnerOrgInspection.getIsFinanciallyCapable().equals("Y") ||  !partnerOrgInspection.getIsOtherItemsManufactured().equals("Y"))
		   {
			   validator.reject(errors, "not.selected", "Please do clarification as you have not selected all point");
		   }
	     }
	    }
	   if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
	   {
		   if(!CommonUtil.isStringEmpty(partnerOrgInspection.getIsCEApproved()) && partnerOrgInspection.getIsCEApproved().equals("Y"))
		   {
		   if(!partnerOrgInspection.getIsNoteReceived().equals("Y") || !partnerOrgInspection.getIsCapableForManufacturing().equals("Y")
		 || !partnerOrgInspection.getIsCalibrationCertified().equals("Y")|| !partnerOrgInspection.getIsAllInspectionReportFilled().equals("Y") ||
		 !partnerOrgInspection.getIsAdequetTesting().equals("Y") ||  !partnerOrgInspection.getIsMachineWorking().equals("Y")||
		 !partnerOrgInspection.getIsFinanciallyCapable().equals("Y") ||  !partnerOrgInspection.getIsOtherItemsManufactured().equals("Y"))
		   {
			   validator.reject(errors, "not.selected", "Please do clarification as you have not selected all point");
		   }
		   }
	   }
   }
   private void checkForOrgAllDetailsApproval(PartnerOrgInspectionDto partnerOrgInspection,Errors errors)
   {
	   
	  
	  PartnerOrg partnerOrg=partnerOrgService.getPartnerOrg(partnerOrgInspection.getPartnerOrg().getPartnerOrgId());
	  RoleDto role=contextService.getDefaultRole();
	  if(null==role || CommonUtil.isStringEmpty(role.getValue()))
	  {
		  validator.reject(errors, "session", "Session Timeout...Please Login");
		  return;
	  }
	  checkForOrgEEApproval(partnerOrgInspection,errors,role,partnerOrg);
	  checkForOrgCEApproval(partnerOrgInspection,errors,role,partnerOrg);
   }
   private void checkForOrgEEApproval(PartnerOrgInspectionDto partnerOrgInspection,Errors errors,RoleDto role,PartnerOrg partnerOrg)
   {
	   if(partnerOrg!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER) )
	   {
		  if(!CommonUtil.isStringEmpty(partnerOrgInspection.getIsEEApproved()) && partnerOrgInspection.getIsEEApproved().equals("Y"))
		  {
		   if(CommonUtil.isStringEmpty(partnerOrg.getIsEEApproved()) || partnerOrg.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
		   {
			   validator.reject(errors, "partnerOrg.isEEApproved", "Factory Details is Not Approved");
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgUser()))
		   {
			 for(PartnerOrgUser orgUser:partnerOrg.getPartnerOrgUser())
			 {
			   if(CommonUtil.isStringEmpty(orgUser.getIsEEApproved())|| orgUser.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgUser.isEEApproved", "Factory Contact  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgExperience()))
		   {
			 for(PartnerOrgExperience orgExperience:partnerOrg.getPartnerOrgExperience())
			 {
			   if(CommonUtil.isStringEmpty(orgExperience.getIsEEApproved())|| orgExperience.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgExperience.isEEApproved", "Factory Experience  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRegistration()))
		   {
			 for(PartnerOrgRegistration orgRegistration:partnerOrg.getPartnerOrgRegistration())
			 {
			   if(CommonUtil.isStringEmpty(orgRegistration.getIsEEApproved())|| orgRegistration.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgRegistration.isEEApproved", "Factory Registration  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgBIS()))
		   {
			 for(PartnerOrgBIS orgBIS:partnerOrg.getPartnerOrgBIS())
			 {
			   if(CommonUtil.isStringEmpty(orgBIS.getIsEEApproved()) || orgBIS.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgBIS.isEEApproved", "Factory BIS  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgProduct()))
		   {
			 for(PartnerOrgProduct orgProduct:partnerOrg.getPartnerOrgProduct())
			 {
			   if(CommonUtil.isStringEmpty(orgProduct.getIsEEApproved()) || orgProduct.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgProduct.isEEApproved", "Factory Item Manufacture  is Not Approved");
			   }
			 }
		   }if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgCertification()))
		   {
			 for(PartnerOrgCertification orgCertification:partnerOrg.getPartnerOrgCertification())
			 {
			   if(CommonUtil.isStringEmpty(orgCertification.getIsEEApproved())|| orgCertification.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgCertification.isEEApproved", "Factory ISO Certification  is Not Approved");
			   }
			 }
		   }if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgPerformance()))
		   {
			 for(PartnerOrgPerformance orgPerformance:partnerOrg.getPartnerOrgPerformance())
			 {
			   if(CommonUtil.isStringEmpty(orgPerformance.getIsEEApproved())|| orgPerformance.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgPerformance.isEEApproved", "Factory Past Performance  is Not Approved");
			   }
			 }
		   }/*if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRDAEC()))
		   {
			 for(PartnerOrgRDAEC orgRDAEC:partnerOrg.getPartnerOrgRDAEC())
			 {
			   if(CommonUtil.isStringEmpty(orgRDAEC.getIsEEApproved()) || orgRDAEC.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgRDAEC.isEEApproved", "Factory RDAEC  is Not Approved");
			   }
			 }
		   }*/
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgOE()))
		   {
			 for(PartnerOrgOE orgOE:partnerOrg.getPartnerOrgOE())
			 {
			   if(CommonUtil.isStringEmpty(orgOE.getIsEEApproved()) || orgOE.getIsEEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgOE.isEEApproved", "Factory Other Eligibility Detail is Not Approved");
			   }
			 }
		   }
		  }
	   }
   }
   private void checkForOrgCEApproval(PartnerOrgInspectionDto partnerOrgInspection,Errors errors,RoleDto role,PartnerOrg partnerOrg)
   {
	   if(partnerOrg!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER) )
	   {
		  if(!CommonUtil.isStringEmpty(partnerOrgInspection.getIsCEApproved()) && partnerOrgInspection.getIsCEApproved().equals("Y"))
		  {
		   if(CommonUtil.isStringEmpty(partnerOrg.getIsCEApproved()) || partnerOrg.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
		   {
			   validator.reject(errors, "partnerOrg.isCEApproved", "Factory Details is Not Approved");
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgUser()))
		   {
			 for(PartnerOrgUser orgUser:partnerOrg.getPartnerOrgUser())
			 {
			   if(CommonUtil.isStringEmpty(orgUser.getIsCEApproved())|| orgUser.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgUser.isCEApproved", "Factory Contact  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgExperience()))
		   {
			 for(PartnerOrgExperience orgExperience:partnerOrg.getPartnerOrgExperience())
			 {
			   if(CommonUtil.isStringEmpty(orgExperience.getIsCEApproved())|| orgExperience.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgExperience.isCEApproved", "Factory Experience  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRegistration()))
		   {
			 for(PartnerOrgRegistration orgRegistration:partnerOrg.getPartnerOrgRegistration())
			 {
			   if(CommonUtil.isStringEmpty(orgRegistration.getIsCEApproved()) || orgRegistration.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgRegistration.isCEApproved", "Factory Registration  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgBIS()))
		   {
			 for(PartnerOrgBIS orgBIS:partnerOrg.getPartnerOrgBIS())
			 {
			   if(CommonUtil.isStringEmpty(orgBIS.getIsCEApproved())|| orgBIS.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgBIS.isCEApproved", "Factory BIS  is Not Approved");
			   }
			 }
		   }
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgProduct()))
		   {
			 for(PartnerOrgProduct orgProduct:partnerOrg.getPartnerOrgProduct())
			 {
			   if(CommonUtil.isStringEmpty(orgProduct.getIsCEApproved())|| orgProduct.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgProduct.isCEApproved", "Factory Item Manufacture  is Not Approved");
			   }
			 }
		   }if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgCertification()))
		   {
			 for(PartnerOrgCertification orgCertification:partnerOrg.getPartnerOrgCertification())
			 {
			   if(CommonUtil.isStringEmpty(orgCertification.getIsCEApproved())|| orgCertification.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgCertification.isCEApproved", "Factory ISO Certification  is Not Approved");
			   }
			 }
		   }if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgPerformance()))
		   {
			 for(PartnerOrgPerformance orgPerformance:partnerOrg.getPartnerOrgPerformance())
			 {
			   if(CommonUtil.isStringEmpty(orgPerformance.getIsCEApproved())|| orgPerformance.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgPerformance.isCEApproved", "Factory Past Performance  is Not Approved");
			   }
			 }
		   }
		   /*if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgRDAEC()))
		   {
			 for(PartnerOrgRDAEC orgRDAEC:partnerOrg.getPartnerOrgRDAEC())
			 {
			   if(CommonUtil.isStringEmpty(orgRDAEC.getIsCEApproved())|| orgRDAEC.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgRDAEC.isCEApproved", "Factory RDAEC  is Not Approved");
			   }
			 }
		   }*/
		   if(!CommonUtil.isCollectionEmpty(partnerOrg.getPartnerOrgOE()))
		   {
			 for(PartnerOrgOE orgOE:partnerOrg.getPartnerOrgOE())
			 {
			   if(CommonUtil.isStringEmpty(orgOE.getIsCEApproved()) || orgOE.getIsCEApproved().equals(AppBaseConstant.CLARIFICATION_STATUS))
			   {
				   validator.reject(errors, "orgOE.isCEApproved", "Factory Other Eligibility Detail is Not Approved");
			   }
			 }
		   }
		  }
	   }
   }

}
