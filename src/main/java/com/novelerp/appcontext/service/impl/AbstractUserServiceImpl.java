package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * @author sravan 
 * AbstractUserServiceImpl
 *
 */

public abstract class AbstractUserServiceImpl extends AbstractServiceImpl<User, UserDto> implements UserService {
	
/*	@Autowired
	protected BPartnerDao bPartnerDao;
	
	@Autowired
	protected BPartnerService partnerService;
*/	
	@Autowired
	protected UserRolesService userRolesService;	

	@Autowired
	protected UserDao userDao;
	
	/*@Autowired
	private MapperUtil mapper;*/


	/**
	 * @author sravan 
	 * method createBPartner
	 * @param UserDto
	 * return ResponseDto
	 */
/*	public void createBPartner(BPartnerDto bPartnerDto) {
		log.info("== in createBPartner");
		
		Bpartner bPartner=new Bpartner();
		bPartner.setName(bPartnerDto.getName());
		bPartner.setValue(bPartnerDto.getValue());
		bPartner.setPanNo(bPartnerDto.getPanNo());
		bPartner.setUrl(bPartnerDto.getUrl());
		
		bPartner.setImage(12);
		bPartnerDao.create(bPartner);
		if(bPartner.getbPartnerId()>0){
			log.debug(bPartner.getbPartnerId()+"=====");
			bPartnerDto.setPartnerId(bPartner.getbPartnerId());
		}
	}
*/
	/*private UserDto saveUser(UserDto userDto){
		String pwd = PassWordGenerator.generatePswd(8, 12, 2, 4, 2);
		userDto.setPassword(pwd);
		userDto.setIsActive("Y");
		userDto.setCreated(new Timestamp(new Date().getTime()));
		userDto.setUpdated(new Timestamp(new Date().getTime()));
		// Changed Vivek
//		userDto.setCreatedBy(1000);
//		userDto.setUpdatedBy(1000);
		User user = new User();
		user.setName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmailID());
		user.setPhone(userDto.getTelephoneNo());
		user.setPhone2(userDto.getMobileNo());
		user.setIsActive(userDto.getIsActive());
		user.setCreated(userDto.getCreated());
		user.setUpdated(new Timestamp(new Date().getTime()));
		// Changed Vivek
//		user.setCreatedBy(userDto.getCreatedBy());
//		user.setUpdatedBy(userDto.getUpdatedBy());
//		user.setUserDepartmentId(0);
		user.setbPartner(bPartnerDao.findOne(userDto.getbPartnerId()));
		user.setRole(roleDao.findOne(DBConstants.CUSTOMER_ADMIN));
		userDao.create(user);
		if(user.getUserId()>0){
			userDto.setUserId(user.getUserId());
			userDto.setRoleId(user.getRole().getRoleId());
		}
		return userDto;
	}*/
/*	private UserRoles userRolesMapping(UserDto userDto){
		UserRoleKey adUserRoleKey=new UserRoleKey();
		adUserRoleKey.setAdRoleId(userDto.getRoleId());
		// Changed Vivek
//		adUserRoleKey.setAdUserId(userDto.getUserId());
		UserRoles userRoles=new UserRoles();
		userRoles.setCreated(userDto.getCreated());
		userRoles.setUpdated(userDto.getUpdated());
		// Changed Vivek
//		userRoles.setCreatedby(userDto.getCreatedBy());
//		userRoles.setUpdatedby(userDto.getUpdatedBy());
//		userRoles.setUserRoleKey(adUserRoleKey);
		
		return userRoles;
	}
*/	
	protected abstract void setRole(UserDto dto);
// Changed Vivek
//	@Override
	public List<UserDto> fetchUsers(Long bpartnerId) {

		List<UserDto> userList=new ArrayList<>();
		
		return userList;
	}

}
