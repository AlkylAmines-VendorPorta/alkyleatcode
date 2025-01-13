package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.RoleDao;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Role;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Vivek Birdi
 *
 */

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleDto> implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@PostConstruct
	private void init() {
		super.init(RoleServiceImpl.class, roleDao, Role.class, RoleDto.class);
		setByPassProxy(true);
	}
	
	
	public Map<String, RoleDto> getRoleMap(){
		List<RoleDto> roles = findAll();
		return loadMap(roles);
	}
	
	/**
	 * load map from list of roles
	 * @param roles
	 * @return Map<String,RoleDto> -  HashMap of roles
	 */
	public Map<String, RoleDto> loadMap(List<RoleDto> roles){
		Map<String, RoleDto> roleMap = new HashMap<>();
		for(RoleDto role: roles){
			roleMap.put(role.getValue(),role);
		}
		return roleMap;
	}

	@Override
	public RoleDto getRoleById(Long roleId) {
		RoleDto role  = new RoleDto();
		try{
			Role roles =  roleDao.findOne(roleId);
			role = getDto(roles);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return role;
	}
	@Override
	public List<RoleDto> getRoles(Long userID) {
		List<RoleDto> roleList  = new ArrayList<>();
		try{
			List<Role> roles =  roleDao.getUserRoles(userID);
			roleList = getDtoList(roles);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return roleList;
	}
	@Override
	public List<RoleDto> getAllRoles(String role)
	{
		List<RoleDto> roleList  = new ArrayList<>();
		List<Role> roles =null;
		try{
			if(role.equals("SYSADM"))
			{
				roles =  roleDao.findAll(" where value<>'SYSADM' ","name");
			}else
				roles =  roleDao.findAll(" where isadmin='N' or value='FINADM' ","name");
			roleList = getDtoList(roles);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return roleList;
		
	}
	
	@Override
	public List<RoleDto> getUserRoles(Long userID) {
		List<RoleDto> roleList  = new ArrayList<>();
		try{
			List<Role> roles =  roleDao.getUserRoles(userID);
			roleList = getDtoList(roles);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return roleList;
	}


	@Override
	@Transactional
	public boolean deleteRole(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}


	@Override
	public List<RoleDto> getAllUserRoles(Long userId) {
		List<RoleDto> roleList  = new ArrayList<>();
		try{
			List<Role> roles =  roleDao.getUserAllRoles(userId);
			roleList = getDtoList(roles);
		}catch (Exception e) {
			log.error("Exception", e);
		}
		return roleList;
	}





	
}
