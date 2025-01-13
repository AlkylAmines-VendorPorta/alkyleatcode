package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerOrgBISConverter;
import com.novelerp.appbase.master.dao.PartnerOrgBISDao;
import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.appbase.master.service.PartnerOrgBISService;
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
public class PartnerOrgBISServiceImpl extends AbstractContextServiceImpl<PartnerOrgBIS, PartnerOrgBISDto> implements PartnerOrgBISService{

	
	@Autowired
	private PartnerOrgBISDao  partnerOrgBISDao;
	
	@Autowired
	private PartnerOrgBISConverter partnerOrgBISConverter;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@PostConstruct
	public void init(){
		super.init(PartnerOrgBISServiceImpl.class, partnerOrgBISDao, PartnerOrgBIS.class, PartnerOrgBISDto.class);
		/*setObjectConverter(partnerOrgBISConverter);*/
		setByPassProxy(true);
	}	
	
	@Override
	public PartnerOrgBISDto getPartnerOrgBIS(Long partnerId) {
		PartnerOrgBISDto dto=null;
		try
		{
			List<PartnerOrgBIS> list=partnerOrgBISDao.findAll(" where m_bpartner_id="+partnerId, null);
			PartnerOrgBIS entity=null;
			if(!list.isEmpty())
			{
				entity=list.get(0);
				dto=partnerOrgBISConverter.getDtoFromEntity(entity, PartnerOrgBISDto.class);
			}
		}catch(Exception ex)
		{
			System.err.println("Exception in getPartnerOrgBIS():"+ex);
		}
		return dto;
	}

	/*@Override
	@Transactional
	public ResponseDto updatePartnerOrgBIS(PartnerOrgBISDto dto) {
		
		try {
				PartnerOrgBIS entity=partnerOrgBISConverter.getEntityFromDto(dto, PartnerOrgBIS.class);
				partnerOrgBISDao.update(entity);
				return new ResponseDto(AppBaseConstant.SUCCESS, "BIS Details Updated Successfully", entity.getPartnerOrgBisId());
		} catch (Exception e) {
			
			System.err.println("Error in Update BIS Details " + e.getMessage());
			return new ResponseDto(AppBaseConstant.FAILURE, "BIS Details Not Updated");
		}
	}*/

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgBISDto saveOrgBIS(PartnerOrgBISDto dto,RoleDto role) {
		
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
		if(dto!=null && dto.getPartnerOrgBisId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgBisId", dto.getPartnerOrgBisId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgBisId() == null){
			return save(dto);
		}
		return updateDto(dto);
	
	}

}
