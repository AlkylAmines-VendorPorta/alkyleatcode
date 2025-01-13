package com.novelerp.appcontext.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;

@Component
public class AppContext {
	
	private Logger log =  LoggerFactory.getLogger(AppContext.class);
	private UserDto adminUser;
	
	@Autowired
	@Qualifier(ContextConstant.SIMPLE_USER_SERVICE)
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	private Map<String, RoleDto> roleMap =  new HashMap<>();
	
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	private boolean isSFTP=false;
	
	@PostConstruct
	public void init(){
		loadContext();
	}
	
	public synchronized void loadContext(){
		String whereClause = "c.name = :name";
		List<UserDto> users = userService.find(whereClause, getAdminCondition(), null);
		loadSuperUser(users);
		loadRoles();
		this.setSFTP("Y".equals(sysConfigService.getSystemPropertyConfigurator(AppBaseConstant.EAT_SFTP_ENABLED)));
	}
	
	
	private void loadSuperUser(List<UserDto> users){
		if(CommonUtil.isListEmpty(users)){	
			log.debug("No user from DB");
			return;					
		}
		adminUser  = users.get(0);
		log.debug("Loaded ..");
		
	}
	
	/**
	 * Load roles
	 */
	private void loadRoles(){
		roleMap = roleService.getRoleMap();
	}
	
	/**
	 * Get RoleDto object
	 * @param roleName
	 * @return RoleDto
	 */
	public RoleDto getRole(String roleName){
		return roleMap.get(roleName);
	}
	
	private Map<String, Object> getAdminCondition(){
		Map<String, Object> params= new HashMap<>();		
		params.put("name", "SysUser");
		return params;
	}
	
	public UserDto getSysAdmin(){
		return adminUser;
	}

	public boolean isSFTP() {
		return isSFTP;
	}

	public void setSFTP(boolean isSFTP) {
		this.isSFTP = isSFTP;
	}

}
