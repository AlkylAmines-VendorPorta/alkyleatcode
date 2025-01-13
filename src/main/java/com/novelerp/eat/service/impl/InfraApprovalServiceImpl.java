package com.novelerp.eat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.service.InfraApprovalService;

@Service
public class InfraApprovalServiceImpl implements InfraApprovalService{
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Override
	public CustomResponseDto levelwiseApproval(String roleName){
		CustomResponseDto response=new CustomResponseDto();
		response.addObject("result", false);
		if(!roleName.trim().equals("")){
			List<InfraApprovalLevelDto> levels=new ArrayList<>();
			if(!CommonUtil.isCollectionEmpty(levels)){
				for(InfraApprovalLevelDto approvalLevel:levels){
					RoleDto levelRole=approvalLevel.getRole();
					if(levelRole.getName().equals(roleName)){
						response.addObject("vendorlist", null);
						response.addObject("result", true);
						response.addObject("message", "work in process");
						break;
					}
				}
			}else{
				response.addObject("message", "work in process");
			}
		}else{
			response.addObject("message", "work in process");
		}
		return response;
	}

	@Override
	public CustomResponseDto loadVendor() {
		CustomResponseDto response=new CustomResponseDto();
		RoleDto loggedInRole=contextService.getDefaultRole();
		if(loggedInRole!=null){
			if(loggedInRole.getName().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(loggedInRole.getValue())){
				response.addObject("vendorlist", null);
				response.addObject("result", true);
				response.addObject("message", "work in process");
			}else{
				response=levelwiseApproval(loggedInRole.getName());
			}
		}else{
			response.addObject("vendorlist", null);
			response.addObject("result", false);
			response.addObject("message", "work in process");
		}
		return response;
	}
  }

