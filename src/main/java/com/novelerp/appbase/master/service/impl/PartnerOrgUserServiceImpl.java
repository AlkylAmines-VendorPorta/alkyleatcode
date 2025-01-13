package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerOrgUserDao;
import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PartnerOrgUserService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgUserServiceImpl extends AbstractContextServiceImpl<PartnerOrgUser, PartnerOrgUserDto> implements PartnerOrgUserService{

	@Autowired
	private PartnerOrgUserDao  partnerOrgUserDao;
	@Autowired
	private LocationService locationService;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	/*Changed PartnerOrgUserConverter to PartnerOrgUserConverterPlain (Vivek Birdi)*/
	
	@PostConstruct
	public void init(){
		super.init(PartnerOrgUserServiceImpl.class, partnerOrgUserDao, PartnerOrgUser.class, PartnerOrgUserDto.class);
/*		setObjectConverter(partnerOrgUserConverter);*/
	}	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgUserDto save(PartnerOrgUserDto dto) {
		LocationDto location = dto.getUserDetail().getLocation();
		location = locationService.save(location);
		UserDetailsDto user =  dto.getUserDetail();
		user.setLocation(location);
		user = userDetailService.save(user);
		user.setLocation(location);
		dto.setUserDetail(user);
		dto = super.save(dto);
		dto.setUserDetail(user);
		return  dto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgUserDto updateDto(PartnerOrgUserDto dto) {
		LocationDto location = dto.getUserDetail().getLocation();
		location = locationService.updateDto(location);
		UserDetailsDto user =  dto.getUserDetail();
		user.setLocation(location);
		user = userDetailService.updateDto(user);
		user.setLocation(location);
		dto.setUserDetail(user);
		dto = super.updateDto(dto);
		dto.setUserDetail(user);
		return dto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgUserDto saveOrgUser(PartnerOrgUserDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true,"Session Timeout...Please Login"));
			return dto;
		}
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
		if(dto!=null && dto.getPartnerOrgUserId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgUserId", dto.getPartnerOrgUserId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgUserId() == null){
			return save(dto);
		}
		return updateDto(dto);
	}
	
}

