package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.InternalUserService;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.config.AppContext;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RegisterUser;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserRolesService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.util.AuthManager;
@Service
public class InternalUserServiceImpl implements InternalUserService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRolesService userRolesService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private AuthManager authManager;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AppContext appContext;
	
	@Autowired
	private RegisterUser registerUser;
	@Autowired
	private MailTemplateService mailTemplateService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto createInternalUser(UserRolesDto userRolesDto) {
		CustomResponseDto response=new CustomResponseDto();
		boolean checkEmail=userService.checkValue( userRolesDto.getUser().getEmail(),null);
		if(!checkEmail){
			BPartnerDto partner=contextService.getPartner();
			//String panno=partner==null?"":partner.getPanNumber();
			try{
					UserDetailsDto userDetailsDto=userDetailsService.save(userRolesDto.getUser().getUserDetails());
					userRolesDto.getUser().setUserDetails(userDetailsDto);
					
					/*String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);*/
					String password = "Pass,123";
					String encodedPassword=authManager.getSaltedHash(password, userRolesDto.getUser().getEmail());
					userRolesDto.getUser().setPassword(encodedPassword);
					
					userRolesDto.getUser().setName(userDetailsDto.getFirstName());
					UserDto userDto=userService.save(userRolesDto.getUser());
					userRolesDto.setUser(userDto);
					
					userRolesDto=userRolesService.addUserRole(userRolesDto);
					response.setData(userRolesService.getUserRolesByUserId(userDto.getUserId()));
					response.setResponseMsg("User Added Successfully");
					response.setResponseStatus(true);
					MailDto mailDto = new MailDto();
					
					mailDto.setSubject("Auto Generated Password For Login");
					mailDto.setMailContent("<p>Hi</p><br><p>Password for login is - <p>"+ password);
					mailDto.getRecipientList().add(userDto.getEmail());
					mailService.sendEmail(mailDto,true);
				}catch(Exception ex){
					System.out.println("Exception in createChildUser(): "+ex);
					response.setResponseMsg("User not Added");
					response.setResponseStatus(false);
				}
		}else{
				response.setResponseMsg("Email Id already Registered");
				response.setResponseStatus(false);
		}
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public com.novelerp.core.dto.CustomResponseDto createNewInvitedUser(UserDto userDto) {
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		boolean checkEmail=userService.checkValue( userDto.getEmail(),null);
		if(!checkEmail){
			UserDto invitedBy =  contextService.getUser();
			userDto.setCreatedBy(invitedBy);
			userDto.setUpdatedBy(invitedBy);		
			
			/*Partner Handling*/
			BPartnerDto partner = registerUser.addPartner(userDto);
			String panno=partner==null?"":partner.getPanNumber();
			userDto.setPartner(partner);
			try{
				    UserRolesDto newUserRole=new UserRolesDto();
					UserDetailsDto userDetailsDto=userDetailsService.save(userDto.getUserDetails());
					userDto.setUserDetails(userDetailsDto);
					
					/*String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);*/
					String password = "Pass,123";
					String encodedPassword=authManager.getSaltedHash(password, userDto.getEmail());
					userDto.setPassword(encodedPassword);
					userDto.setIsPasswordUpdated("Y");
					userDto.setName(userDetailsDto.getFirstName());
					
					userDto=userService.save(userDto);
					
					newUserRole.setUser(userDto);
					/*RoleDto role = appContext.getRole(AppBaseConstant.ROLE_QUICK_VENDOR_ADMIN);*/
					RoleDto role = appContext.getRole(AppBaseConstant.ROLE_VENDOR_ADMIN);
					newUserRole.setRole(role);
					registerUser.addUserRole(userDto,role);
					
					response.addObject("GeneratedPassword", password);
					response.addObject("GeneratedPartner", partner);
					response.addObject("RESULT_MESSAGE", "User Added Successfully");
					response.addObject("RESULT_STATUS",true);
					
				}catch(Exception ex){
					System.out.println("Exception in createNewInvitedUser(): "+ex);
					response.addObject("RESULT_MESSAGE", "User not Added");
					response.addObject("RESULT_STATUS",false);
				}
		}else{
			response.addObject("RESULT_MESSAGE", "Email Id already Registered with other user");
			response.addObject("RESULT_STATUS",false);
		}
		return response;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto updateInternalUser(UserRolesDto userRolesDto) {
		CustomResponseDto response=new CustomResponseDto();
		boolean checkEmail=userService.checkValue( userRolesDto.getUser().getEmail(),userRolesDto.getUser().getUserId());
		if(!checkEmail)
		{
					try
					{
						UserDetailsDto userDetailsDto=userDetailsService.updateDto(userRolesDto.getUser().getUserDetails());
						userRolesDto.getUser().setUserDetails(userDetailsDto);
						/*userRolesDto.getUser().setPassword("password"); */
						/*userRolesDto.getUser().setPassword(userRolesDto.getUser().getPassword());*/
						userRolesDto.getUser().setName(userDetailsDto.getFirstName());
						
						UserDto userDto=userService.updateDto(userRolesDto.getUser());
						userRolesDto.setUser(userDto);
			
						userRolesDto=userRolesService.updateUserRole(userRolesDto);
						
						response.setData(userRolesService.getUserRolesByUserId(userDto.getUserId()));
						response.setResponseMsg("User Updated Successfully");
						response.setResponseStatus(true);
					}catch(Exception ex)
					{
						System.out.println("Exception in updateChildUser(): "+ex);
						response.setResponseMsg("User not Updated");
						response.setResponseStatus(false);
					}
		}
		else
			{
				response.setResponseMsg("Email Id already Registered");
				response.setResponseStatus(false);
			}
		return response;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public com.novelerp.core.dto.CustomResponseDto saveInteralUserCore(UserRolesDto userRolesDto){
		
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		/*boolean checkEmail=userService.checkValue( userRolesDto.getUser().getEmail(),null,userRolesDto.getUser().getUserName());*/
		boolean checkEmail=userService.checkUserNameValue(userRolesDto.getUser().getUserName(),null);
		if(!checkEmail){
			BPartnerDto partner=contextService.getPartner();
			
			try{
					UserDetailsDto userDetailsDto=userDetailsService.save(userRolesDto.getUser().getUserDetails());
					userRolesDto.getUser().setUserDetails(userDetailsDto);
					
					/*String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);*/
					String password = "Pass,123";
					String encodedPassword=authManager.getSaltedHash(password, userRolesDto.getUser().getEmail());
					userRolesDto.getUser().setPassword(encodedPassword);
					
					userRolesDto.getUser().setName(userDetailsDto.getName());
					userRolesDto.setPartner(partner);
					userRolesDto.getUser().setIsEmailLogin("N");
					UserDto userDto=userService.save(userRolesDto.getUser());
					userRolesDto.setUser(userDto);
					
					/*userRolesDto=userRolesService.addUserRole(userRolesDto);*/
					userRolesDto=userRolesService.addMultipleUserRole(userRolesDto);
					response.setData(userRolesService.getUserRolesByUserId(userDto.getUserId()));
					response.setMessage("User Added Successfully");
					response.setSuccess(false);
					MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_FORGOT_PASSWORD);
					userDto.setPassword(password);
					userService.sendMailOnForgotPassword(userDto,mailTemplate);
					/*MailDto mailDto = new MailDto();
					
					mailDto.setSubject("Auto Generated Password For Login");
					mailDto.setMailContent("<p>Hi</p><br><p>Password for login is - <p>"+ password);
					mailDto.getRecipientList().add(userDto.getEmail());
					mailService.sendEmail(mailDto,true);*/
				}catch(Exception ex){
					System.out.println("Exception in createChildUser(): "+ex);
					response.setMessage("User not Added");
					response.setSuccess(true);
				}
		}else{
				/*response.setMessage("Email Id already Registered");*/
			response.setMessage("Employee Code already Registered");
				response.setSuccess(true);
		}
		return response;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public com.novelerp.core.dto.CustomResponseDto updateInternalUserCore(UserRolesDto userRolesDto){
		
		com.novelerp.core.dto.CustomResponseDto response=new com.novelerp.core.dto.CustomResponseDto();
		/*boolean checkEmail=userService.checkValue( userRolesDto.getUser().getEmail(),userRolesDto.getUser().getUserId(),userRolesDto.getUser().getUserName());*/
		boolean checkEmail=userService.checkUserNameValue(userRolesDto.getUser().getUserName(),userRolesDto.getUser().getUserId());
		if(!checkEmail)
		{
			BPartnerDto partner=contextService.getPartner();
					try
					{
						UserDetailsDto userDetailsDto=userDetailsService.updateDto(userRolesDto.getUser().getUserDetails());
						userRolesDto.getUser().setUserDetails(userDetailsDto);
						userRolesDto.setPartner(partner);
						Map<String, Object> propertyValueMap = new HashMap<>();
						propertyValueMap.put("name", userRolesDto.getUser().getUserDetails().getName());
						propertyValueMap.put("email", userRolesDto.getUser().getUserDetails().getEmail());
						propertyValueMap.put("userName", userRolesDto.getUser().getUserName());
						int result = userService.updateByJpql(propertyValueMap, 
								AbstractContextServiceImpl.getParamMap("userId",  userRolesDto.getUser().getUserId()));
						
						Map<String, Object> propertyValueMapDetails = new HashMap<>();
						propertyValueMapDetails.put("personalMail", userRolesDto.getUser().getUserDetails().getPersonalMail());
						propertyValueMapDetails.put("personalContactNo", userRolesDto.getUser().getUserDetails().getPersonalContactNo());
						
						int updateADUserDetailsResult = userDetailsService.updateByJpql(propertyValueMapDetails, 
								AbstractContextServiceImpl.getParamMap("userDetailsId",  userRolesDto.getUser().getUserId()));
					/*	i
						UserDto userDto=userService.updateDto(userRolesDto.getUser());
						userRolesDto.setUser(userDto);*/
			
						/*userRolesDto=userRolesService.updateUserRole(userRolesDto);*/
						
						response.setData(userRolesService.getUserRolesByUserId( userRolesDto.getUser().getUserId()));
						response.setMessage("User Updated Successfully");
						response.setSuccess(false);
					}catch(Exception ex)
					{
						System.out.println("Exception in updateChildUser(): "+ex);
						response.setMessage("User not Updated");
						response.setSuccess(true);
					}
		}
		else
			{
				response.setMessage("Email Id already Registered");
				response.setSuccess(true);
			}
		return response;

	
	}

}