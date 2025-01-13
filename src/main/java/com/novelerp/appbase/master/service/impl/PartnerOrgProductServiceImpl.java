package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerOrgProductDao;
import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.appbase.master.service.PartnerOrgProductService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class PartnerOrgProductServiceImpl extends AbstractContextServiceImpl<PartnerOrgProduct, PartnerOrgProductDto> implements PartnerOrgProductService{

	@Autowired
	private PartnerOrgProductDao partnerOrgProductDao;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
		
	@PostConstruct
	public void init(){
		super.init(PartnerOrgProductServiceImpl.class, partnerOrgProductDao, PartnerOrgProduct.class, PartnerOrgProductDto.class);
		/*setObjectConverter(orgProductConverter);*/
		setByPassProxy(true);
		
	}
	
	@Override
	public List<PartnerOrgProductDto> getExmptedItemListByTahdr(Long tahdrId,Long partnerId,Long partnerOrgId){
		Map<String, Object> map= new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("partnerId", partnerId);
		map.put("partnerOrgId", partnerOrgId);
		List<PartnerOrgProductDto> prodList=findDtos("getExmptedItemListByTahdr", map);
		return prodList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgProductDto saveOrgProduct(PartnerOrgProductDto dto,RoleDto role) {
				
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
		if(dto!=null && dto.getPartnerOrgProductId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  if(dto.getIsEEApproved()==null){
				  dto.setResponse(new ResponseDto(true, "Select atleast one status"));
				  return dto;
			  }
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				if(dto.getIsCEApproved()==null){
					dto.setResponse(new ResponseDto(true, "Select atleast one status"));
					return dto;
				}
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgProductId", dto.getPartnerOrgProductId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
	    if(dto.getPartnerOrgProductId() == null){
			return save(dto); 
		}
		return updateDto(dto);
	
	}

	
}
