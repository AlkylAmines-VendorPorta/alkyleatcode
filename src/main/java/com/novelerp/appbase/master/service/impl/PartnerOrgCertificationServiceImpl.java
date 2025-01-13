package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerOrgCertificationDao;
import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appbase.master.service.PartnerOrgCertificationService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgCertificationServiceImpl extends AbstractContextServiceImpl<PartnerOrgCertification, PartnerOrgCertificationDto> implements PartnerOrgCertificationService{


	@Autowired
	private PartnerOrgCertificationDao  partnerOrgCertificationDao;
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgCertificationServiceImpl.class, partnerOrgCertificationDao, PartnerOrgCertification.class, PartnerOrgCertificationDto.class);
		/*setObjectConverter(partnerOrgCertificationConverter);*/
		setByPassProxy(true);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgCertificationDto saveOrgCert(PartnerOrgCertificationDto dto,RoleDto role) {
		
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
			if(dto.getPartnerOrg()!=null && dto.getPartnerOrg().getPartnerOrgId()!=null)
			{
			  Map<String, Object> map=AbstractServiceImpl.getParamMap("isEEApproved", null);
			  map.put("isCEApproved", null);
			  map.put("eeComment", null);
			  map.put("ceComment", null);
			  partnerOrgService.updateByJpql(map, "partnerOrgId", dto.getPartnerOrg().getPartnerOrgId());
			}
		}
		if(dto!=null && dto.getPartnerOrgCertificateId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgCertificateId", dto.getPartnerOrgCertificateId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgCertificateId() == null){
			return save(dto);
		}
		return updateDto(dto);

	}	

}

