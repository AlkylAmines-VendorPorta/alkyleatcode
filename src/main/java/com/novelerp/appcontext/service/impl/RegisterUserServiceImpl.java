package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RegisterUser;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.AuthGenerator;
import com.novelerp.core.util.AuthManager;

@Service(ContextConstant.REGISTER_USER_SERVICE)
public class RegisterUserServiceImpl extends AbstractServiceImpl<User, UserDto> implements RegisterUser {

	@Autowired
	protected UserDao userDao;
	
	@Autowired
	@Qualifier("jwtUserContext") 
	private ContextService contextService;  
	
	@Autowired
	private UserDetailsService userDetailsService;
 
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private AppContext appContext;

	@Autowired
	private UserRolesService userRoleService;
	
	@Autowired
	AuthManager authManager;
	
	@PostConstruct
	public void init(){
		super.init(UserServiceImpl.class, userDao,User.class, UserDto.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDto register(UserDto dto) {	
		
//		UserDto sysAdmin =  appContext.getSysAdmin();
//		dto.setCreatedBy(sysAdmin);
//		dto.setUpdatedBy(sysAdmin);		
		ContextDto context = contextService.getContext();
		if(null!=context) {
		dto.setCreatedBy(context.getUserDto());
		dto.setUpdatedBy(context.getUserDto());
		}else {
			UserDto adminUser = new UserDto();
			adminUser.setUserId(1L);
			dto.setCreatedBy(adminUser);
			dto.setUpdatedBy(adminUser);
		}
		/*Partner Handling*/
		BPartnerDto partner = addPartner(dto);
		dto.setPartner(partner);
		
		/*User Detail Handling*/
		UserDetailsDto userDetail = addUserDetail(dto);
		dto.setUserDetails(userDetail);
		String defaultPassword=dto.getPassword();
		String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
		if(defaultPassword==null) {
			dto.setPassword(authManager.getSaltedHash(password, dto.getEmail()));
			
		}
		else {
			dto.setPassword("dmFyc2hhY0B5b3BtYWlsLmNvbQ==$X1X/CjDG07/9qblHxqhwSOysf3SaY68Cd844iMaZctE=");
		/*String password = "Pass,123";*/
		
		}
		dto.setIsInvited("Y");
		dto.setIsLoginCreated("Y");
		dto.setIsEmailLogin("Y");
		dto = save(dto);
		boolean roleSaved = addUserRole(dto);
		if(!dto.getResponse().isHasError() && roleSaved){
			/*MailDto mailDto = new MailDto();
			mailDto.setSubject("Auto Generated Password For MSEDCL Login");
			mailDto.setMailContent("<p>Hi</p><br><p>Password for login is - <p>"+ password);
			mailDto.getRecipientList().add(user.getEmail());
			mailService.sendEmail(mailDto);*/
			dto.setPassword(password);
			return dto;
		}		
		return dto;
	}
	/**
	 * add Partner into database
	 * @param dto
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public BPartnerDto addPartner(UserDto dto){
	
		BPartnerDto partner =  dto.getPartner();
		partner.setCreatedBy(dto.getCreatedBy());
		partner.setUpdatedBy(dto.getUpdatedBy());
		partner.setRegistrationType(dto.getPartner().getRegistrationType());
		partner.setStatus(AppBaseConstant.DOCUMENT_STATUS_DRAFTED); 
		partner.setIsRegCompleted("Y");
		partner.setIsCEApproved("Y");
		partner.setIsCustomer("Y");//already approved,no approval
		partner.setIsActive("Y");
		partner.setIsGstApplicable("Y");
		partner.setRegistrationType("PARTICIPANT");
		partner = partnerService.save(partner);
		return partner;
	}
	/**
	 * Add User Details into database
	 * @param dto
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	private UserDetailsDto addUserDetail(UserDto dto){
		UserDetailsDto userDetail = dto.getUserDetails();
		userDetail.setUserDetailType(AppBaseConstant.INIT_USER);
		userDetail.setEmail(dto.getEmail());
		userDetail.setCreatedBy(dto.getCreatedBy());
		userDetail.setUpdatedBy(dto.getUpdatedBy());
		userDetail.setPartner(dto.getPartner());
		userDetail.setCreatorOrbidder(dto.getUserDetails().getCreatorOrbidder());
		if(dto.getPartner()!=null && AppBaseConstant.CREATER.equals(dto.getPartner().getRegistrationType()))
		{
			userDetail.setLocationTypeRef(AppBaseConstant.OFFICE_TYPE_OT);
		}
		userDetail = userDetailsService.save(userDetail);
		return userDetail;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addUserRole(UserDto dto){
		RoleDto role = appContext.getRole(ContextConstant.USER_TYPE_PATNER_USER);
		return userRoleService.addUserRole(dto, role);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean addUserRole(UserDto dto,RoleDto role){
		return userRoleService.addUserRole(dto, role);
	}
	

}
