package com.novelerp.appbase.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerFinancialDetailsDao;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.entity.PartnerFinancialAttachment;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
/**
 * 
 * @author varsha
 *
 */
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
@Service
public class PartnerFinancialDetailsServiceImpl extends AbstractContextServiceImpl<PartnerFinancialAttachment, PartnerFinancialAttachmentDto>implements PartnerFinancialDetailsService{
	@Autowired
	private PartnerFinancialDetailsDao partnerFinancialDetailsDao;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	public void init(){
		super.init(PartnerFinancialDetailsServiceImpl.class, partnerFinancialDetailsDao, PartnerFinancialAttachment.class, PartnerFinancialAttachmentDto.class);
		setByPassProxy(true);
	}

	@Override
	@Transactional
	public List<PartnerFinancialAttachmentDto> processFinancialAttachmentRequest(
			List<PartnerFinancialAttachmentDto> dtos) {
		ArrayList<PartnerFinancialAttachmentDto> financialAttachments = new ArrayList<>();
		if(CommonUtil.isListEmpty(dtos)){
			return financialAttachments;
		}
		
		for(PartnerFinancialAttachmentDto dto : dtos){
			PartnerFinancialAttachmentDto savedDto=new PartnerFinancialAttachmentDto();
		   
			savedDto=saveFinancialAttachment(dto);
          
			financialAttachments.add(savedDto);
		}
		return financialAttachments;
	}

	@Override
	@Transactional
	public PartnerFinancialAttachmentDto saveFinancialAttachment(PartnerFinancialAttachmentDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getFinancialAttachmentId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "financialAttachmentId", dto.getFinancialAttachmentId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getFinancialAttachmentId() == null){
			return super.save(dto);
		}
		return super.updateDto(dto);
	}
}
