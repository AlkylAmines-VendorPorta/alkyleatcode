package com.novelerp.appbase.master.service.impl;
/**
 * 
 * @author Varsha Patil
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerInfraItemDao;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appbase.master.entity.PartnerInfraItem;
import com.novelerp.appbase.master.service.PartnerInfraItemService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
@Service
public class PartnerInfraItemServiceImpl extends AbstractContextServiceImpl<PartnerInfraItem, PartnerInfraItemDto> implements PartnerInfraItemService{
	@Autowired
	private PartnerInfraItemDao partnerInfraItemDao;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@PostConstruct
	private void init() {
		super.init(PartnerInfraItemServiceImpl.class, partnerInfraItemDao, PartnerInfraItem.class, PartnerInfraItemDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerInfraItemDto saveInfraItems(PartnerInfraItemDto dto,PartnerInfraItemDto infraItem) {
		
		if(dto.getPartnerInfraItemId()==null)
	    {
			dto.setLevelNo(1l);
			dto.setStatus(AppBaseConstant.PARTNER_STATUS_DRAFTED);
			return save(dto);
	    }else{
	    	infraItem.setMaterial(dto.getMaterial());
	    	/*infraItem.setIsActive(dto.getIsActive());*/
		    return updateDto(infraItem);
	    }
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	 public boolean uploadClarification(Long infraItemId,Long partnerId,Long attachmentId){
		int resultCount=partnerInfraItemDao.uploadClarification(infraItemId, partnerId, attachmentId);
		return resultCount>0?true:false;
	 }
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateInfraItemStatus(PartnerInfraItemDto dto) {
		
		Map<String,Object> map=getParamMap("status",AppBaseConstant.INFRA_IN_PROGRESS);
		map.put("levelNo",1l);
		int count =updateByJpql(map, "partnerInfraItemId", dto.getPartnerInfraItemId());
		return count>0?true:false;
	}
	@Override
	public List<PartnerInfraItemDto> getInfraItems(Long partnerId) {
		RoleDto role=contextService.getDefaultRole();
		List<PartnerInfraItemDto> infraItems=new ArrayList<>();
		if(role==null || role.getValue()==null){
			return infraItems;
		}
		Map<String, Object> param=AbstractContextServiceImpl.getParamMap("partnerId",partnerId);
		if(AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())||AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
		   infraItems=findDtos("getInfraItemQuery",param);
		}else{
			if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())){
				param.put("level", 1l);
			}else if(ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())){
				param.put("level", 2l);
			}else if(ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())){
				param.put("level", 3l);
			}else if(ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())){
				param.put("level", 4l);
			}else if(ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())){
				param.put("level", 5l);
			}else{
				param.put("level", 0l);
			}
			param.put("status",AppBaseConstant.INFRA_IN_PROGRESS);
			infraItems=findDtos("getInfraItemForApproval",param);
		}
		return infraItems;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateInfraItemLevel(PartnerInfraItemDto dto, RoleDto role,String isApproved) {
	
	    	dto=setInfraStatus(dto,role,isApproved);
		 int result=0;
		 if(AppBaseConstant.INFRA_APPROVE.equals(isApproved) && !ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())){
			 
			 result=partnerInfraItemDao.updateInfraItemLevel(dto, role);
			
		 }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) || AppBaseConstant.INFRA_REJECT.equals(isApproved) || (AppBaseConstant.INFRA_APPROVE.equals(isApproved) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue()))){
			
			 result=partnerInfraItemDao.updateInfraItemStaus(dto, role);
		 }
		return result>0?true:false;
	}
	private PartnerInfraItemDto setInfraStatus(PartnerInfraItemDto dto,RoleDto role,String isApproved){
		
			   if(AppBaseConstant.INFRA_APPROVE.equals(isApproved) && (ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue()) 
			    || ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())
			    || ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue()) || ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())))
				{
			    	 dto.setStatus(AppBaseConstant.INFRA_IN_PROGRESS);
			    	 
				}else if(AppBaseConstant.INFRA_APPROVE.equals(isApproved) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue()))
				{
		    	     dto.setStatus(AppBaseConstant.INFRA_COMPLETE);
		    
				}else if(AppBaseConstant.INFRA_REJECT.equals(isApproved) && (ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue()) 
					   || ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())|| ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue()) 
					   || ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue()) || ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue()))){
		    	    
					dto.setStatus(AppBaseConstant.INFRA_STATUS_REJECTED);
			
		        }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved))
		        {
		        	dto.setStatus(AppBaseConstant.INFRA_STATUS_CLARIFY);
		        }
			   /*else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) && ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(role.getValue())){
		    	    
		        	dto.setStatus(AppBaseConstant.INFRA_EE_CLARIFIED);
		        }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) && ContextConstant.USER_TYPE_SCRUTINY_ENGINEER.equals(role.getValue())){
		    	    
		        	dto.setStatus(AppBaseConstant.INFRA_SE_CLARIFIED);
		        }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) && ContextConstant.USER_TYPE_CHIEF_ENGINEER.equals(role.getValue())){
		    	    
		        	dto.setStatus(AppBaseConstant.INFRA_CE_CLARIFIED);
		        }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) && ContextConstant.USER_TYPE_EXECUTIVE_DIRECTOR.equals(role.getValue())){
		    	    
		        	dto.setStatus(AppBaseConstant.INFRA_ED_CLARIFIED);
		        }else if(AppBaseConstant.INFRA_CLARIFY.equals(isApproved) && ContextConstant.USER_TYPE_DIRECTOR.equals(role.getValue())){
		    	    
		        	dto.setStatus(AppBaseConstant.INFRA_DIR_CLARIFIED);
		        } */
	 
		return dto;
	}
	@Override
	public boolean updateInfraItem(PartnerInfraItemDto dto) {
		
		return false;
	}
}

