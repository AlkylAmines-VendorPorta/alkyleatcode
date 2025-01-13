package com.novelerp.appcontext.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.UserDetailsDao;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.utility.MapperUtil;

@Service
public class UserDetailsServiceImpl extends AbstractContextServiceImpl<UserDetails, UserDetailsDto> implements UserDetailsService{ 
	
	@Autowired
	protected UserDetailsDao userDetailsDao;

	
	@Autowired
	private MapperUtil mapper;
	

	@Autowired
	private LocationService locationService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	public void init(){
		super.init(UserDetailsServiceImpl.class, userDetailsDao,UserDetails.class, UserDetailsDto.class);
/*		setObjectConverter(userDetailConverter);*/
		setByPassProxy(true);
	}

	@Override
	public UserDetailsDto getUserDetailsByUserId(Long userId) {
		List<UserDetails> userDetailsList=userDetailsDao.findAll(" where ad_user_id="+userId, null);
		UserDetailsDto userDetails=null; 
		if(!userDetailsList.isEmpty())
		{
			userDetails=mapper.convertEntityToDto(userDetailsList.get(0), UserDetailsDto.class);
		}
		return userDetails;
	}

	@Override
	@Transactional
	public UserDetailsDto createUserDetails(UserDetailsDto userDetailsDto) {
		UserDetails entity=mapper.convertDtoToEntity(userDetailsDto, UserDetails.class);
		userDetailsDao.create(entity);
		userDetailsDto=mapper.convertEntityToDto(entity, UserDetailsDto.class);
		return userDetailsDto;
	}

	@Override
	@Transactional
	public UserDetailsDto updateUserDetails(UserDetailsDto userDetailsDto) {
		UserDetails entity=mapper.convertDtoToEntity(userDetailsDto, UserDetails.class);
		userDetailsDao.update(entity);
		userDetailsDto=mapper.convertEntityToDto(entity, UserDetailsDto.class);
		return userDetailsDto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDetailsDto saveWithLocation(UserDetailsDto userDetailDto) {
		
		LocationDto location = userDetailDto.getLocation();
		location = locationService.save(location);
		userDetailDto.setLocation(location);	
		UserDetailsDto userDetail = save(userDetailDto);
		userDetail.setLocation(location);
		return userDetail;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDetailsDto updateWithLocation(UserDetailsDto userDetailDto) {

		LocationDto location = userDetailDto.getLocation();
		location = locationService.updateDto(location);
		userDetailDto.setLocation(location);	
		UserDetailsDto userDetail = updateDto(userDetailDto);
		userDetail.setLocation(location);
		return userDetail;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDetailsDto saveCompanyContact(UserDetailsDto dto) {
		dto.setUpdatedBy(contextService.getUser());
		if(dto.getPartner() == null){
			dto.setPartner(contextService.getPartner());
		}
		RoleDto role=contextService.getDefaultRole();
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
		if(dto!=null && dto.getUserDetailsId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "userDetailsId", dto.getUserDetailsId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto != null && dto.getUserDetailsId()!=null){
			return updateDto(dto);
		}else{
			dto.setCreatedBy(contextService.getUser());
			return save(dto);
		}

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDetailsDto saveManagementDetails(UserDetailsDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getUserDetailsId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "userDetailsId", dto.getUserDetailsId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getUserDetailsId() == null){
			dto.setUserDetailType("COMPDIR");
			return saveWithLocation(dto);
		}
		return  updateWithLocation(dto);	
	}

	
}

