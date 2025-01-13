package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerItemTypeTestDetailsDao;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.dto.PartnerItemTypeTestDetailsDto;
import com.novelerp.appbase.master.entity.PartnerItemTypeTestDetails;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.master.service.PartnerItemTypeTestDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.service.impl.ContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class PartnerItemTypeTestDetailsServiceImpl extends AbstractContextServiceImpl<PartnerItemTypeTestDetails, PartnerItemTypeTestDetailsDto> implements PartnerItemTypeTestDetailsService{
    @Autowired
	private PartnerItemTypeTestDetailsDao partnerItemTypeTestDetailsDao;
    @Autowired
    private PartnerInfraItemService partnerInfraItemService;
    @Autowired
     @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	public void init(){
		super.init(PartnerItemTypeTestDetailsServiceImpl.class, partnerItemTypeTestDetailsDao, PartnerItemTypeTestDetails.class, PartnerItemTypeTestDetailsDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto insertItemTypeTestByMaterial(PartnerItemTypeTestDetailsDto dto) {
		HttpSession session=ContextServiceImpl.getCurrentSession();
		dto.setCreatedBy(contextService.getUser());
		dto.setCreatedSessionId(session.getId());
		dto.setPartner(contextService.getPartner());
		int count=partnerItemTypeTestDetailsDao.insertDrawingDocByMaterial(getEntity(dto));
		if(count>0)
		{
			return new ResponseDto(false, "Record Inserted Successfully");
		}
		return new ResponseDto(true, "No Record Inserted");
	}
	@Override
	public CustomResponseDto getItemTypeTest(Long partnerId, Long infraItemId) {
		RoleDto role=contextService.getDefaultRole();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("partnerId",partnerId);
		params.put("infraItemId",infraItemId);
		List<PartnerItemTypeTestDetailsDto> typeTestList=new ArrayList<>();
		typeTestList=findDtos("getInfraTypeTestByPartnerAndInfraItem", params);
		if(role!=null && role.getValue()!=null && (AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())||AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()))){
			if(CommonUtil.isCollectionEmpty(typeTestList)){
				Map<String,Object> map=getParamMap("infraItemId",infraItemId);
				PartnerInfraItemDto typeTestDto=partnerInfraItemService.findDto("getInfraItemById",map);
				if(null!=typeTestDto){
					PartnerItemTypeTestDetailsDto dto=new PartnerItemTypeTestDetailsDto();
					dto.setPartnerInfraItem(typeTestDto);
					ResponseDto response=insertItemTypeTestByMaterial(dto);
					if(!response.isHasError())
					{
						typeTestList=findDtos("getInfraTypeTestByPartnerAndInfraItem", params);
					}
				}
			}	
		}
		return new CustomResponseDto("typeTestList", typeTestList);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerItemTypeTestDetailsDto saveTypeTest(PartnerItemTypeTestDetailsDto dto,PartnerItemTypeTestDetailsDto typeTest) {
		RoleDto role=contextService.getDefaultRole();
			
	    if(typeTest!=null && dto.getPartnerItemTypeTestId()!=null && role!=null && role.getValue()!=null){
	        if(AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
		    	typeTest.setIssueDate(dto.getIssueDate());
		    	typeTest.setLetterNo(dto.getLetterNo());
		    	 return updateDto(typeTest);
		    }else{
		    	if(role!=null && role.getValue()!=null && typeTest!=null && !AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) && !AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
					if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())){
						typeTest.setIsEEApproved(dto.getStatus());
						typeTest.setEeComment(dto.getRemark());
						return updateDto(typeTest);
					}else if(ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue()))
					{
						typeTest.setIsSEApproved(dto.getStatus());
						typeTest.setSeComment(dto.getRemark());
						return updateDto(typeTest);
					}else if(ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())){
						typeTest.setIsCEApproved(dto.getStatus());
						typeTest.setCeComment(dto.getRemark());
						return updateDto(typeTest);
					}else if(ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())){
						typeTest.setIsEDApproved(dto.getStatus());
						typeTest.setEdComment(dto.getRemark());
						return updateDto(typeTest);
					}else if(ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())){
						typeTest.setIsDIRApproved(dto.getStatus());
						typeTest.setDirComment(dto.getRemark());
						return updateDto(typeTest);
					}
					/*itemDoc.setStatus(dto.getStatus());
					itemDoc.setRemark(dto.getRemark());*/					
				}
		    }
		   
		}
	     dto.setResponse(new ResponseDto(true, "No Record Inserted"));
		 return dto;
	}
	@Override
	public boolean resetInfraTypeTest(Long infraItemId, Long partnerId) {
		int resultCount=partnerItemTypeTestDetailsDao.resetInfraTypeTest(infraItemId, partnerId);
		boolean result=resultCount>0?true:false;
		return result;
	}
}
