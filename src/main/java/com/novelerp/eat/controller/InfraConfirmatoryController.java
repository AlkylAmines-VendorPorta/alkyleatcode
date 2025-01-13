package com.novelerp.eat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.service.InfraApprovalLevelService;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.master.service.PartnerItemDrawingDocService;
import com.novelerp.appbase.master.service.PartnerItemTypeTestDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.PartnerInfraItemApprovalDto;
import com.novelerp.eat.service.PartnerInfraItemApprovalService;
import com.novelerp.eat.validator.InfraItemValidator;
/**
 * 
 * @author Aman Sahu
 *
 */
@Controller
public class InfraConfirmatoryController {
	
	@Autowired
	private PartnerInfraItemApprovalService  partnerInfraItemApprovalService;
	
	@Autowired
	private InfraApprovalLevelService infraApprovalLevelService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
    @Autowired
	private PartnerItemTypeTestDetailsService partnerItemTypeTestDetailsService;
    @Autowired
    private PartnerItemDrawingDocService partnerItemDrawingDocService;
    @Autowired
    private PartnerInfraItemService partnerInfraItemService;
    @Autowired
    private InfraItemValidator infraItemValidator;
	@RequestMapping(value="/getInfraLine", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto getInfraLine(@RequestParam("partnerId") Long partnerId,
			                                                @RequestParam("infraItemId") Long infraItemId){
		CustomResponseDto response =new CustomResponseDto();
		RoleDto role=contextService.getDefaultRole();
		if(role!=null && role.getValue()!=null){
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("partnerId", partnerId);
			params.put("infraItemId", infraItemId);
			List<PartnerInfraItemApprovalDto> approvalLine=new ArrayList<>();
			if(role.getValue().equalsIgnoreCase(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
				approvalLine=partnerInfraItemApprovalService.findDtos("getInfraApprovalLineByApprovalLevel", params);
				response.addObject("infraLineList", approvalLine);
				response.addObject("result", true);
			}else{
				boolean isAuthorise=true;/*infraApprovalLevelService.isRoleAuthorise(role.getValue());*/
				if(isAuthorise){
					params.put("roleName", role.getValue());
					PartnerInfraItemApprovalDto approvalLineDto=partnerInfraItemApprovalService.findDto("getInfraApprovalLine", params);
					approvalLine.add(approvalLineDto);
					response.addObject("infraLineList", approvalLine);
					response.addObject("result", true);
				}else{
					response.addObject("message","Unauthorized User");
					response.addObject("result", false);
				}
			}
		}else{
			response.addObject("message","Role Not Found");
			response.addObject("result", false);
		}
		return response;
	}
	
	@RequestMapping(value="/createInfraLine", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto createInfraLine(@RequestParam("partnerId") Long partnerId,
                                                           @RequestParam("infraItemId") Long infraItemId){
		CustomResponseDto response=new CustomResponseDto();
		RoleDto role=contextService.getDefaultRole();
		response.addObject("result", false);
		if(role==null || role.getValue()==null){
			response.addObject("message", "Session Time Out");
		    return response;
		}
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("partnerId", partnerId);
			params.put("infraItemId", infraItemId);
			List<PartnerItemTypeTestDetailsDto> typeTestList=partnerItemTypeTestDetailsService.findDtos("getInfraTypeTestByPartnerAndInfraItem",params);
			List<PartnerItemDrawingDocDto> drawingDocList=partnerItemDrawingDocService.findDtos("getInfraDrawingDocsByPartnerAndInfraItem",params);
			if(CommonUtil.isCollectionEmpty(typeTestList) || CommonUtil.isCollectionEmpty(drawingDocList)){
				response.addObject("message", "Item Type Test Details Not Added OR Item Drawing Doc Details Not Added");
			    return response;
			}
			List<PartnerInfraItemApprovalDto> approvalLine=partnerInfraItemApprovalService.findDtos("getInfraApprovalLinePartnerAndInfraItem", params);
			PartnerInfraItemDto partnerInfraItem=new PartnerInfraItemDto();
			
			if(CommonUtil.isCollectionEmpty(approvalLine)){
				partnerInfraItem.setPartnerInfraItemId(infraItemId);
				if(role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
					List<InfraApprovalLevelDto> infraApprovalLevels=infraApprovalLevelService.findAll();
					if(!CommonUtil.isCollectionEmpty(infraApprovalLevels)){
						partnerInfraItemApprovalService.createInfraItemLine(infraApprovalLevels, partnerInfraItem);
						boolean result=partnerInfraItemService.updateInfraItemStatus(partnerInfraItem);
						if(result){
						   response.addObject("result", true);
						   response.addObject("message", "Infra Item Submited");
						   approvalLine=partnerInfraItemApprovalService.findDtos("getInfraApprovalLineByApprovalLevel", params);
						   response.addObject("infraLineList", approvalLine);
						}						
					}else{
						response.addObject("message", "Approval Level Not Found ");
					}
				}else{
					response.addObject("message", "error:Not Vendor");
				}
			}else{
				partnerInfraItem.setPartnerInfraItemId(infraItemId);
				boolean result=partnerInfraItemService.updateInfraItemStatus(partnerInfraItem);
				if(result){
				   response.addObject("result", true);
				   response.addObject("message", "Infra Item Submited");
				   approvalLine=partnerInfraItemApprovalService.findDtos("getInfraApprovalLineByApprovalLevel", params);
				   response.addObject("infraLineList", approvalLine);
				}
			}
		return response;
	}
	
	@RequestMapping(value="/saveClarificationFile", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody CustomResponseDto saveClarificationFile(@ModelAttribute("infraLine") PartnerInfraItemApprovalDto partnerInfraItemApprovalDto){
		return partnerInfraItemApprovalService.uploadClarification(partnerInfraItemApprovalDto);
	}
	
	@RequestMapping(value="/saveInfraLine", method=RequestMethod.POST,produces = "application/json")
	public @ResponseBody ResponseDto saveInfraLine(@ModelAttribute("infraLine") PartnerInfraItemApprovalDto dto){
		ResponseDto response=new ResponseDto();
		RoleDto role=contextService.getDefaultRole();
		if(role!=null && role.getValue()!=null && dto.getPartnerInfraItem()!=null && dto.getPartnerInfraItem().getPartnerInfraItemId()!=null && dto.getPartner()!=null && dto.getPartner().getbPartnerId()!=null){
			
				Errors errors=new Errors();
				dto.getPartnerInfraItem().setStatus(dto.getIsApproved());
				infraItemValidator.checkForApproveItem(dto.getPartnerInfraItem(), errors);
				if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
					response.setErrors(errors.getErrorList());
					response.setHasError(true);
					if(AppBaseConstant.INFRA_APPROVE.equals(dto.getIsApproved())){
					   response.setMessage("Following Tabs Not Approved: ");
					}else if(AppBaseConstant.INFRA_CLARIFY.equals(dto.getIsApproved())){
					   response.setMessage("Check For Following Issue: ");
					}else if(AppBaseConstant.INFRA_REJECT.equals(dto.getIsApproved())){
					   response.setMessage("Check For Following Issue: ");
					} 
					return response;
				}
			
			response=partnerInfraItemApprovalService.saveInfraApprovalLine(dto, role.getValue());
			if(!response.isHasError())
			{
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("partnerId", dto.getPartner().getbPartnerId());
				params.put("infraItemId", dto.getPartnerInfraItem().getPartnerInfraItemId());
				PartnerInfraItemApprovalDto latestApproval=partnerInfraItemApprovalService.findDto("getInfraApprovalLineByApprovalLevel", params);
				dto.getPartnerInfraItem().setPartner(dto.getPartner());
				/*dto.getPartnerInfraItem().setStatus(dto.getIsApproved());*/
				partnerInfraItemService.updateInfraItemLevel(dto.getPartnerInfraItem(), role,dto.getIsApproved());
				partnerInfraItemApprovalService.sendInfraApprovalMail(latestApproval);
				response.setHasError(false);
				if(AppBaseConstant.INFRA_APPROVE.equals(dto.getIsApproved())){
				     response.setMessage(" Infra Item Approved ");
				}else if(AppBaseConstant.INFRA_CLARIFY.equals(dto.getIsApproved())){
					 response.setMessage(" Infra Item Clarified ");
				}else if(AppBaseConstant.INFRA_REJECT.equals(dto.getIsApproved())){
					 response.setMessage(" Infra Item Rejected ");
				}
 			}else{				
			    response.setHasError(true);
				response.setMessage("Approval Failed !");
			}
		}else{			
			response.setHasError(true);
			response.setMessage("Role not found is session !");
		}
		return response;
	}
}
