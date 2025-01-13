package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.converter.PartnerSignatoryConverterPlain;
import com.novelerp.appbase.master.dao.PartnerSignatoryDao;
import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appbase.master.service.PartnerSignatoryService;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerSignatoryServiceImpl extends AbstractContextServiceImpl<PartnerSignatory, PartnerSignatoryDto> implements PartnerSignatoryService{

	@Autowired
	private PartnerSignatoryDao  partnerSignatoryDao;
	
	@Autowired
	private PartnerSignatoryConverterPlain partnerSignatoryConverter;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@PostConstruct
	public void init(){
		super.init(PartnerSignatoryServiceImpl.class, partnerSignatoryDao, PartnerSignatory.class, PartnerSignatoryDto.class);
		setObjectConverter(partnerSignatoryConverter);
		setByPassProxy(true);
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerSignatoryDto save(PartnerSignatoryDto dto) {
		LocationDto location = dto.getLocation();
		location = locationService.save(location);
		dto.setLocation(location);
		
		UserDetailsDto userDetail = dto.getUserDetail();
		userDetail.setLocation(location);
		userDetail = userDetailService.save(userDetail);
		dto.setUserDetail(userDetail);
		
		PartnerSignatoryDto signatoryDto = super.save(dto);
		signatoryDto.setUserDetail(userDetail);
		signatoryDto.setLocation(location);

		return signatoryDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerSignatoryDto updateDto(PartnerSignatoryDto dto) {

		LocationDto location = dto.getLocation();
		location = locationService.updateDto(location);
		dto.setLocation(location);
		
		UserDetailsDto userDetail = dto.getUserDetail();
		userDetail = userDetailService.updateDto(userDetail);
		dto.setUserDetail(userDetail);
		
		PartnerSignatoryDto signatoryDto = super.updateDto(dto);
		signatoryDto.setUserDetail(userDetail);
		signatoryDto.setLocation(location);

		return signatoryDto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerSignatoryDto saveSignatory(PartnerSignatoryDto dto,RoleDto role) {

		if(dto!=null && dto.getPartnerSignatoryId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerSignatoryId", dto.getPartnerSignatoryId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerSignatoryId() == null){
			return save(dto);
		}
		return updateDto(dto);
	}

}
