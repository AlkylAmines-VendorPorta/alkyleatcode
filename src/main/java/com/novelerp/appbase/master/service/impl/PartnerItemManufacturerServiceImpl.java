package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerItemManufacturerConverterPlain;
import com.novelerp.appbase.master.dao.PartnerItemManufacturerDao;
import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appbase.master.service.PartnerItemManufacturerService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Aman Sahu
 * 
 */
@Service
public class PartnerItemManufacturerServiceImpl extends AbstractContextServiceImpl<PartnerItemManufacturer, PartnerItemManufacturerDto> implements PartnerItemManufacturerService{


	@Autowired
	private PartnerItemManufacturerDao  partnerItemManufacturerDao;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private PartnerItemManufacturerConverterPlain partnerItemManufacturerConverterPlain;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	public void init(){
		super.init(PartnerItemManufacturerServiceImpl.class, partnerItemManufacturerDao, PartnerItemManufacturer.class, PartnerItemManufacturerDto.class);
		setObjectConverter(partnerItemManufacturerConverterPlain);
		setByPassProxy(true);
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerItemManufacturerDto save(PartnerItemManufacturerDto dto) {
		LocationDto location = dto.getLocation();
		location = locationService.save(location);
		dto.setLocation(location);
		
		PartnerItemManufacturerDto partnerItemManufacturerDto = super.save(dto);
		partnerItemManufacturerDto.setLocation(location);

		return partnerItemManufacturerDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerItemManufacturerDto updateDto(PartnerItemManufacturerDto dto) {

		LocationDto location = dto.getLocation();
		location = locationService.updateDto(location);
		dto.setLocation(location);
		
		PartnerItemManufacturerDto partnerItemManufacturerDto = super.updateDto(dto);
		partnerItemManufacturerDto.setLocation(location);

		return partnerItemManufacturerDto;
	}

	@Override
	public List<PartnerItemManufacturerDto> getPartnerItemManufacturer(Long bpartnerId) {
		
 		RoleDto role=contextService.getDefaultRole();	
		if(role==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId);
		String query=partnerItemManufacturerDao.getQueryForPartnerItemManufacturerQuery(role);
		List<PartnerItemManufacturerDto> partnerItemManufacturer=findDtosByQuery(query, params);
		return partnerItemManufacturer;
	}
	private Map<String,Object> getParameterMap(RoleDto role,Long bpartnerId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("partnerId", bpartnerId);
		if(role!=null && role.getValue()!=null){
			if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER) || role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
			 {
				params.put("isFAApproved", AppBaseConstant.APPROVED_STATUS);
			 }
		}
		return params;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerItemManufacturerDto saveItemManufacture(PartnerItemManufacturerDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true,"Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getPartnerItemManufacturerId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerItemManufacturerId", dto.getPartnerItemManufacturerId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerItemManufacturerId() == null){
			return save(dto);
		}
		return  updateDto(dto);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto updateItemsApproval(PartnerTradingItemDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			return new ResponseDto(true,"Role not found");
		}
		Map<String, Object> map=new HashMap<>();
		if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
		  map.put("itemsEEComment", dto.getEeComment());
		  map.put("isItemsEEApproved", dto.getIsEEApproved());
		}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
			  map.put("itemsCEComment", dto.getCeComment());
			  map.put("isItemsCEApproved", dto.getIsCEApproved());
		}
		int result=updateByJpql(map,"partnerItemManufacturerId",dto.getPartnerItemManufacutrer().getPartnerItemManufacturerId());
		if(result>0)
		{
			return new ResponseDto(false, "Record Updated");			
		}else{
			return new ResponseDto(true, "Error in Updated");
		}
	}
}