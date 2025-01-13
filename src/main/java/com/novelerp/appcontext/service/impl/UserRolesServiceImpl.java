package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.converter.UserRolesConverter;
import com.novelerp.appcontext.dao.UserRolesDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.core.utility.MapperUtil;

import net.bytebuddy.implementation.bind.annotation.Super;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class UserRolesServiceImpl extends AbstractContextServiceImpl<UserRoles, UserRolesDto> 
	implements UserRolesService{
	
	@Autowired
	private UserRolesDao userRolesDao;
	
	@Autowired
	private MapperUtil mapper;
	
	@Autowired
	private UserRolesConverter userRolesConverter;
	@PostConstruct
	private void init() {		
		super.init(UserRolesServiceImpl.class, userRolesDao, UserRoles.class, UserRolesDto.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addUserRole(UserDto user, RoleDto role) {
		UserRolesDto userRole =  new UserRolesDto();
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setCreatedBy(user.getCreatedBy());
		userRole.setUpdatedBy(user.getCreatedBy());
		userRole.setPartner(user.getPartner());
		userRole.setIsDefault("Y");
		return create(userRole);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserRolesDto updateUserRole(UserRolesDto userRoleDto) {
		UserRoles userRole =mapper.convertDtoToEntity(userRoleDto, UserRoles.class);
		userRolesDao.update(userRole);
		userRoleDto=userRolesConverter.convertEntityToDto(userRole, UserRolesDto.class);
		return userRoleDto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserRolesDto addUserRole(UserRolesDto userRolesDto) {
		UserRoles userRole =mapper.convertDtoToEntity(userRolesDto, UserRoles.class);
		userRolesDao.create(userRole);
		userRolesDto=userRolesConverter.convertEntityToDto(userRole, UserRolesDto.class);
		return userRolesDto;
	}
	@Override
	public UserRolesDto getUserRolesByUserId(Long userId)
	{
		List<UserRoles> userRolesList=userRolesDao.getUserRolesByUserId(userId);
		UserRolesDto dto=null;
		if(!userRolesList.isEmpty())
		{
			dto=userRolesConverter.convertEntityToDto(userRolesList.get(0), UserRolesDto.class);
		}
		return dto;
	}
	
	/*@Override
	public List<UserRolesDto> getInternalUserRolesList(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue
			  ,String locationType,String officeLocation){
		List<UserRolesDto> internalUserRolesList=new ArrayList<>();
		String query = invokeQueryMethod(userRolesDao, queryMethodName);
		StringBuilder customeQuery=new StringBuilder(query);
		if(locationType!=null && officeLocation!=null){
			params.put("locationType",locationType);
			params.put("officeLocation",officeLocation);
			query=customeQuery.append(" AND lt.name=:locationType AND ofl.name=:officeLocation AND r.isAdmin='N'").toString();
		}
		if (!"none".equalsIgnoreCase(searchValue)) {
			query=customeQuery.append(" AND  ur.user."+searchColumn+" LIKE :searchValue ORDER BY ur.updated DESC").toString();
			params.put("searchValue",
					"'%" + searchValue.toUpperCase() + "%'");
		}else{
			query=customeQuery.append("  ORDER BY ur.updated DESC").toString();
		}
		internalUserRolesList = findDtosByQuery(query, params, pageNumber, pageSize);
		return internalUserRolesList;
	}
	
	@Override
	public long getInternaluserCount(String roleName,Map<String, Object> params,String searchColumn, String searchValue
			,String locationType,String officeLocation){
		long resultCount=0l;
		String searchParam="";
		if(roleName !=null){
			String whereCondition=" c.role.value not in "
					+ "('"+AppBaseConstant.ROLE_SYS_ADMIN+"','"+AppBaseConstant.ROLE_VENDOR_ADMIN+"','"+AppBaseConstant.ROLE_PARTNER_USER+"') ";
			if (!"none".equalsIgnoreCase(searchValue)) {
				params.put("searchValue", "'%" + searchValue.toUpperCase() + "%'");
				searchParam=" AND  c.user."+searchColumn+" LIKE :searchValue";
			}if(roleName.equals(AppBaseConstant.ROLE_LOCATION_ADMIN)){
				params.put("locationType",locationType);
				params.put("officeLocation",officeLocation);
				whereCondition=whereCondition+" AND c.user.userDetails.locationType.name=:locationType AND c.user.userDetails.officeLocation.name=:officeLocation AND c.role.isAdmin='N'" +searchParam;
				resultCount= getRecordCount(whereCondition, params);
			}else if(roleName.equals(AppBaseConstant.ROLE_SYS_ADMIN)){
					if(!searchParam.trim().equals("")){
						whereCondition= whereCondition+" "+searchParam;
					}
					resultCount= getRecordCount(whereCondition, params);
			}
		}else{
			return resultCount;
		}
		return resultCount;
	}*/
	
	@Override
	public List<UserRolesDto> getUserRoles(String locationType,String officeLocation,BPartnerDto Bdto){
		List<UserRoles> userRolesList=userRolesDao.getUserRolesList(locationType, officeLocation,Bdto);
		List<UserRolesDto> userRolesDtoList=new ArrayList<UserRolesDto>();
		if(!userRolesList.isEmpty()){
			for(UserRoles userRoles:userRolesList){
				UserRolesDto dto=userRolesConverter.convertEntityToDto(userRoles, UserRolesDto.class);
				userRolesDtoList.add(dto);
			}
		}
		return userRolesDtoList;
	}
	
	@Override
	public List<UserRolesDto> getSearchedUserList(String litral)
	{
		List<UserRoles> userRolesList=userRolesDao.searchUserRoles(litral);
		List<UserRolesDto> userRolesDtoList=new ArrayList<UserRolesDto>();
		if(!userRolesList.isEmpty())
		{
			for(UserRoles userRoles:userRolesList)
			{
				UserRolesDto dto=userRolesConverter.convertEntityToDto(userRoles, UserRolesDto.class);
				userRolesDtoList.add(dto);
			}
		}
		return userRolesDtoList;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserRolesDto addMultipleUserRole(UserRolesDto userRolesDto) {
		for(RoleDto dto:userRolesDto.getRoleList()){
			userRolesDto.setRole(dto);
			if("Y".equals(dto.getIsDefault())){
				userRolesDto.setIsDefault("Y");
			}else{
				userRolesDto.setIsDefault("N");
			}
				super.save(userRolesDto);
			}
		return userRolesDto;
	}

	
	

}
