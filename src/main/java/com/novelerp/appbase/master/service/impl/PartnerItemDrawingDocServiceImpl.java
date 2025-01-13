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

import com.novelerp.appbase.master.dao.PartnerItemDrawingDocDao;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.dto.PartnerItemDrawingDocDto;
import com.novelerp.appbase.master.entity.PartnerItemDrawingDoc;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.master.service.PartnerItemDrawingDocService;
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
public class PartnerItemDrawingDocServiceImpl extends AbstractContextServiceImpl<PartnerItemDrawingDoc, PartnerItemDrawingDocDto> implements PartnerItemDrawingDocService{
	@Autowired
	private PartnerItemDrawingDocDao partnerItemDrawingDocDao;
	@Autowired
	private PartnerInfraItemService prtnerInfraItemService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	private void init() {
		super.init(PartnerItemDrawingDocServiceImpl.class, partnerItemDrawingDocDao, PartnerItemDrawingDoc.class, PartnerItemDrawingDocDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto insertDrawingDocByMaterial(PartnerItemDrawingDocDto dto) {
		HttpSession session=ContextServiceImpl.getCurrentSession();
		dto.setCreatedBy(contextService.getUser());
		dto.setCreatedSessionId(session.getId());
		dto.setPartner(contextService.getPartner());
		int count=partnerItemDrawingDocDao.insertDrawingDocByMaterial(getEntity(dto));
		if(count>0)
		{
			return new ResponseDto(false, "Record Inserted Successfully");
		}
		return new ResponseDto(true, "No Record Inserted");
	}
	@Override
	public CustomResponseDto getInfraItemDrawingDoc(Long partnerId, Long infraItemId) {
		RoleDto role=contextService.getDefaultRole();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("partnerId",partnerId);
		params.put("infraItemId",infraItemId);
		List<PartnerItemDrawingDocDto> drawingDocList=new ArrayList<>();
		drawingDocList=findDtos("getInfraDrawingDocsByPartnerAndInfraItem", params);
		if(role!=null && role.getValue()!=null && (AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()))){
		if(CommonUtil.isCollectionEmpty(drawingDocList)){
			Map<String,Object> map=getParamMap("infraItemId",infraItemId);
			PartnerInfraItemDto infraDto=prtnerInfraItemService.findDto("getInfraItemById",map);
			if(null!=infraDto){
				PartnerItemDrawingDocDto dto=new PartnerItemDrawingDocDto();
				dto.setPartnerInfraItem(infraDto);
				ResponseDto response=insertDrawingDocByMaterial(dto);
				if(!response.isHasError())
				{
					drawingDocList=findDtos("getInfraDrawingDocsByPartnerAndInfraItem", params);
				}
			}
		  }	
		}
		return new CustomResponseDto("drawingDocList", drawingDocList);
	}
	@Override
	public boolean resetInfraDrawingDocs(Long infraItemId, Long partnerId) {
		int resultCount=partnerItemDrawingDocDao.resetInfraDrawingDocs(infraItemId, partnerId);
		boolean result=resultCount>0?true:false;
		return result;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerItemDrawingDocDto saveInfraDrawingDocs(PartnerItemDrawingDocDto itemDoc,PartnerItemDrawingDocDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role!=null && role.getValue()!=null && itemDoc!=null && !AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue()) && !AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())){
				itemDoc.setIsEEApproved(dto.getStatus());
				itemDoc.setEeComment(dto.getRemark());
				return updateDto(itemDoc);
			}else if(ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue()))
			{
				itemDoc.setIsSEApproved(dto.getStatus());
				itemDoc.setSeComment(dto.getRemark());
				return updateDto(itemDoc);
			}else if(ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())){
				itemDoc.setIsCEApproved(dto.getStatus());
				itemDoc.setCeComment(dto.getRemark());
				return updateDto(itemDoc);
			}else if(ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())){
				itemDoc.setIsEDApproved(dto.getStatus());
				itemDoc.setEdComment(dto.getRemark());
				return updateDto(itemDoc);
			}else if(ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())){
				itemDoc.setIsDIRApproved(dto.getStatus());
				itemDoc.setDirComment(dto.getRemark());
				return updateDto(itemDoc);
			}
			/*itemDoc.setStatus(dto.getStatus());
			itemDoc.setRemark(dto.getRemark());*/
		}
		dto.setResponse(new ResponseDto(true,"Error In Updated"));
		return dto;
	}
}
