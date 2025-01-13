package com.novelerp.eat.validator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.master.service.PartnerItemDrawingDocService;
import com.novelerp.appbase.master.service.PartnerItemTypeTestDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

/**
 * 
 * @author Acer
 *
 */
@Component
public class InfraItemValidator implements Validator{
    @Autowired
	private ValidationUtil validator;
    @Autowired
     @Qualifier("jwtUserContext") private ContextService contextService;
    @Autowired
    private PartnerItemTypeTestDetailsService typeTestService;
    @Autowired
    private PartnerItemDrawingDocService partnerItemDrawingDocService;
    @Autowired
    private PartnerInfraItemService partnerInfraItemService;
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(PartnerInfraItemDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		PartnerInfraItemDto dto=(PartnerInfraItemDto) object;
	}
	public void checkForDuplicateInfraItem(PartnerInfraItemDto dto,Errors errors){
		Map<String,Object> param=AbstractServiceImpl.getParamMap("materialId",dto.getMaterial().getMaterialId());
		param.put("partnerId",dto.getPartner().getbPartnerId());
		PartnerInfraItemDto infraItem=partnerInfraItemService.findDto("getInfraQueryForDuplicateCheck",param);
		if(null!=infraItem && !CommonUtil.isEqual(infraItem.getPartnerInfraItemId(), dto.getPartnerInfraItemId()))
		{
			validator.reject(errors, "invalid.duplicateItem", "Selected Item is already added !");
		}
	}
	public void checkForApproveItem(PartnerInfraItemDto dto,Errors errors){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			validator.reject(errors, "invalid.role", "Role Not Found");
		}
		Map<String,Object> params=AbstractServiceImpl.getParamMap("infraItemId",dto.getPartnerInfraItemId());
		List<PartnerItemTypeTestDetailsDto> typeTests=typeTestService.findDtos("getInfraTypeTestForApproval", params);
		List<PartnerItemDrawingDocDto> itemDocs=partnerItemDrawingDocService.findDtos("getInfraDocsForApproval", params);
		if(AppBaseConstant.INFRA_APPROVE.equals(dto.getStatus())){
			checkForInfraApprove(itemDocs,typeTests,role,errors);
		}else if(AppBaseConstant.INFRA_CLARIFY.equals(dto.getStatus())){
			checkForInfraClarify(itemDocs,typeTests,role,errors);
		}else if(AppBaseConstant.INFRA_REJECT.equals(dto.getStatus())){
			checkForInfraReject(itemDocs,typeTests,role,errors);
		}
	}
	private void checkForInfraApprove(List<PartnerItemDrawingDocDto> itemDocs,List<PartnerItemTypeTestDetailsDto> typeTests,RoleDto role,Errors errors){
		if(!CommonUtil.isCollectionEmpty(typeTests))
	    {
	    	for(PartnerItemTypeTestDetailsDto typeTest:typeTests){
	    		if((!"Y".equals(typeTest.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
	    		(!"Y".equals(typeTest.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
	    		(!"Y".equals(typeTest.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
	    		(!"Y".equals(typeTest.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
	    		(!"Y".equals(typeTest.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
	    	     {
	    			validator.reject(errors, "invalid.NOT Approved", "Infra Type Test-"+typeTest.getMaterialTypeTestDetails().getDocName());
	    	     }
	    	}
	    }
	    if(!CommonUtil.isCollectionEmpty(itemDocs))
	    {
	    	for(PartnerItemDrawingDocDto itemDoc:itemDocs){
	    		if((!"Y".equals(itemDoc.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
	    		(!"Y".equals(itemDoc.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
	    		(!"Y".equals(itemDoc.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
	    		(!"Y".equals(itemDoc.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
	    		(!"Y".equals(itemDoc.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
	    	     {
	    			validator.reject(errors, "invalid.NOT Approved", "Infra Drawing Document -"+itemDoc.getMaterialDrawingDoc().getDocName());
	    	     }
	    	}
	    }
	}
	private void checkForInfraClarify(List<PartnerItemDrawingDocDto> itemDocs,List<PartnerItemTypeTestDetailsDto> typeTests,RoleDto role,Errors errors){
		
			boolean isClarified=false;
			if(!CommonUtil.isCollectionEmpty(itemDocs)){
				
				for(PartnerItemDrawingDocDto itemDoc:itemDocs){
					if((AppBaseConstant.INFRA_CLARIFY.equals(itemDoc.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
				    		(AppBaseConstant.INFRA_CLARIFY.equals(itemDoc.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(itemDoc.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(itemDoc.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(itemDoc.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
				    	     {
					         	isClarified=true;
					         	break;
				    	     }
					
				}
			}
			if(!CommonUtil.isCollectionEmpty(typeTests)){
				if(!isClarified){
					for(PartnerItemTypeTestDetailsDto typeTest:typeTests){
			    		if((AppBaseConstant.INFRA_CLARIFY.equals(typeTest.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
				    		(AppBaseConstant.INFRA_CLARIFY.equals(typeTest.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(typeTest.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(typeTest.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
				    		(AppBaseConstant.INFRA_CLARIFY.equals(typeTest.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
				    	 {
			    			isClarified=true;
				         	break;
			    	     }
			    	}
				}
			}
			if(!isClarified){
				validator.reject(errors, "invalid.NOT Clarified","Need atleast one clarificaion");
			}	
		}
	private void checkForInfraReject(List<PartnerItemDrawingDocDto> itemDocs,List<PartnerItemTypeTestDetailsDto> typeTests,RoleDto role,Errors errors){
		boolean isRejected=false;
		if(!CommonUtil.isCollectionEmpty(itemDocs)){
			
			for(PartnerItemDrawingDocDto itemDoc:itemDocs){
				if((AppBaseConstant.INFRA_REJECT.equals(itemDoc.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
			    		(AppBaseConstant.INFRA_REJECT.equals(itemDoc.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(itemDoc.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(itemDoc.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(itemDoc.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
			    	     {
					        isRejected=true;
				         	break;
			    	     }
				
			}
		}
		if(!CommonUtil.isCollectionEmpty(typeTests)){
			if(!isRejected){
				for(PartnerItemTypeTestDetailsDto typeTest:typeTests){
		    		if((AppBaseConstant.INFRA_REJECT.equals(typeTest.getIsEEApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())) || 
			    		(AppBaseConstant.INFRA_REJECT.equals(typeTest.getIsSEApproved()) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(typeTest.getIsCEApproved()) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(typeTest.getIsEDApproved()) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())) ||
			    		(AppBaseConstant.INFRA_REJECT.equals(typeTest.getIsDIRApproved()) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())) )
			    	 {
		    			isRejected=true;
			         	break;
		    	     }
		    	}
			}
		}
		if(!isRejected){
			validator.reject(errors, "invalid.NOT Rejected","Need atleast one rejection");
		}	
	}
}
