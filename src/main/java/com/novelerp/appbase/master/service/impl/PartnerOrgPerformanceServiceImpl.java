package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerOrgPerformanceDao;
import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.appbase.master.service.PartnerOrgPerformanceService;
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
 * @author Vivek Birdi
 */
@Service
public class PartnerOrgPerformanceServiceImpl extends AbstractContextServiceImpl<PartnerOrgPerformance, PartnerOrgPerformanceDto> implements PartnerOrgPerformanceService{

	
	@Autowired
	private PartnerOrgPerformanceDao  partnerOrgPerformanceDao;
	@Autowired
	private PartnerOrgService partnerOrgService;

	@PostConstruct
	public void init(){
		super.init(PartnerOrgPerformanceServiceImpl.class, partnerOrgPerformanceDao, PartnerOrgPerformance.class, PartnerOrgPerformanceDto.class);
		/*setObjectConverter(partnerOrgPerformanceConverter);*/
		setByPassProxy(true);
	}


	@Override
	public PartnerOrgPerformanceDto checkForDuplicatePerformance(PartnerOrgPerformanceDto dto) {
		Map<String,Object> param=AbstractServiceImpl.getParamMap("material", dto.getMaterial().getMaterialId());
		param.put("poNumber", dto.getPoNumber());
		/*param.put("firmName", dto.getFirmName());
		param.put("startDate",dto.getOrderStartDate());
		param.put("quantity",dto.getQuantitySupplied());*/
		if(dto.getPartnerOrg()!=null && dto.getPartnerOrg().getPartnerOrgId()!=null){
			param.put("partnerOrgId",dto.getPartnerOrg().getPartnerOrgId());
		}else if(dto.getPartner()!=null && dto.getPartner().getbPartnerId()!=null){
			param.put("partnerId",dto.getPartner().getbPartnerId());
		}
		String query=partnerOrgPerformanceDao.checkForDuplicatePerformance(dto);
		PartnerOrgPerformanceDto dtos=findDtoByQuery(query, param);
		return dtos;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgPerformanceDto saveOrgPerformance(PartnerOrgPerformanceDto dto,RoleDto role) {
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
		if(dto!=null && dto.getPartnerOrgPerformanceId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgPerformanceId", dto.getPartnerOrgPerformanceId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgPerformanceId() == null){
			return save(dto);
		}
		return updateDto(dto);
	
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgPerformanceDto saveTraderPerformance(PartnerOrgPerformanceDto dto, RoleDto role) {
 	
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getPartnerOrgPerformanceId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgPerformanceId", dto.getPartnerOrgPerformanceId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgPerformanceId() == null){
			return save(dto);
		}
		return updateDto(dto);
	}	
	
}
