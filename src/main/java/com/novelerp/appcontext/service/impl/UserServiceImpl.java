package com.novelerp.appcontext.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.master.service.ReferenceService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dao.UserDao;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.AuthGenerator;
import com.novelerp.core.util.AuthManager;
import com.novelerp.core.util.DateUtil;
import com.novelerp.core.utility.MapperUtil;
import com.novelerp.eat.service.SMSService;
import com.novelerp.report.dto.PartnerDto;


/**
 * Simple Service Implementation
 * @author Vivek Birdi
 *
 */

@Service(ContextConstant.SIMPLE_USER_SERVICE)
public class UserServiceImpl extends AbstractContextServiceImpl<User, UserDto> implements UserService{ 
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	protected UserDao userDao;

	@Autowired
	private MapperUtil mapper;
	
	/*@Autowired
	private UserConverterWithDetail userConverter;
	
	@Autowired
	private UserDetailsConverter userDetailsConverter;*/
	
	@Autowired
	private AuthManager authManager;	
	@Autowired
	private MailService mailService;
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	private SMSService smsService;
	@Autowired
	private ReferenceListService refrenceListService;
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	
	@PostConstruct
	public void init(){
		super.init(UserServiceImpl.class, userDao,User.class, UserDto.class);
		/*setObjectConverter(userConverter);*/
		setByPassProxy(true);
	}
	
	@Override
	public List<UserDto> fetchUsers(Long bpartnerId)
	{
		List<UserDto> userList=new ArrayList<>();
		
		for(User user:userDao.findAll(" where c_bpartner_id="+bpartnerId,null))
		{
			UserDto userDto=mapper.convertEntityToDto(user,UserDto.class);
			userList.add(userDto);
		}
		return userList;
	}
	
	@Override
	public UserDto fetchUser(Long bpartnerId)
	{
		UserDto user=findDto("getQueryForUserByPartnerId", AbstractContextServiceImpl.getParamMap("bPartnerId", bpartnerId));
		
	
		return user;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	
	public UserDto createUser(UserDto userDto)
	{
		try
		{
			User entity=mapper.convertDtoToEntity(userDto, User.class);
			userDao.create(entity);
			userDto=mapper.convertEntityToDto(entity, UserDto.class);
		}catch(Exception ex)
		{
			System.err.println(ex);
		}
		
		return userDto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public UserDto updateUser(UserDto userDto)
	{
		try
		{
			User entity=mapper.convertDtoToEntity(userDto, User.class);
			userDao.update(entity);
			userDto=mapper.convertEntityToDto(entity, UserDto.class);
		}catch(Exception ex)
		{
			System.err.println(ex);
		}
		
		return userDto;
	}
	@Override
	public UserDto getUser(Long userId)
	{
		User user=userDao.findOne(userId);
		UserDto userDto=mapper.convertEntityToDto(user, UserDto.class);
		return userDto;
	}
	@Override
	public boolean checkValue(String value,Long userId)
	{
		return userDao.checkValue( value,userId);
	}
	@Override
	public boolean checkValue(String value,Long userId,String code)
	{
		return userDao.checkValue( value,userId,code);
	}
	@Override
	public UserDetailsDto getDetailedUserById(Long userId)
	{
		User user=userDao.getDetailedUserById(userId);
		UserDetailsDto userDetailDto=new UserDetailsDto();
		if(user.getUserDetails().getOfficeLocation()!=null && user.getUserDetails().getLocationType()!=null)
		{
			/*LocationDto location=new LocationDto();
			location.setLocationId(user.getUserDetails().getLocation().getLocationId());
			location.setAddress1(user.getUserDetails().getLocation().getAddress1());
			userDetailDto.setLocation(location);*/
			
			OfficeLocationDto officeLocation=new OfficeLocationDto();
			officeLocation.setOfficeLocationId(user.getUserDetails().getOfficeLocation().getOfficeLocationId());
			officeLocation.setName(user.getUserDetails().getOfficeLocation().getName());
			userDetailDto.setOfficeLocation(officeLocation);
			
			LocationTypeDto locationType=new LocationTypeDto();
			locationType.setLocationTypeId(user.getUserDetails().getLocationType().getLocationTypeId());
			locationType.setName(user.getUserDetails().getLocationType().getName());
			userDetailDto.setLocationType(locationType);
		}
		
		return userDetailDto;
	}
	
	@Override
	public List<UserDto> getUsersWithPlainPassword(){
		
		return find("c.hasPlainPassword= 'Y'", null, null);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void hashPlainPassword(List<UserDto> users){
		for(UserDto user :users){
			hashPlainPassword(user);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void hashPlainPassword(UserDto user){
		String password =  user.getPassword();
		String salt= user.getEmail();
		String hash = authManager.getSaltedHash(password,salt);
		user.setPassword(hash);
		updateDto(user);
		
	}
	@Override
	public UserDto getUserByPartnerId(Long bpartnerId){
			Map<String, Object> params= new HashMap<>();		
			params.put("bPartnerId", bpartnerId);
			UserDto user=findDto("getQueryForUserByPartnerId", params);
			return user;
		}
			@Override
	public List<UserDto> findFilteredPartner(Map<String, Object> params, String email, String name) {
		
		 List<User> user = null;
		 List<UserDto> userdto = null;
		if(!email.equals("NULL")){
			params.put("email", "%"+email+"%");
		}
		if(!name.equals("NULL")){
			params.put("name","%"+name+"%");
		}
		String whereCondition = getWhereCondition(email,name);
		String query=invokeQueryMethod(userDao, "getQueryForAllPartnerRA");
		if(whereCondition!=null){
		query=query+whereCondition;
		}
		user =findEntities(query, params);
		userdto =getDtoList(user);
		return userdto;
	}

	public String getWhereCondition(String email,String name){
		String condition = null;
		if(!email.equals("NULL") && !name.equals("NULL") ){
			condition =(" AND upper(ad.email) like upper (:email) AND upper(ad.name) like upper (:name)");
		}else if(!email.equals("NULL") && name.equals("NULL") ){
			condition =(" AND upper(ad.email) like upper (:email)  ");
		}else if(email.equals("NULL") && !name.equals("NULL")){
			condition =(" AND upper(ad.name) like upper (:name) ");
		}
		return condition;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUsers(Date toDate){
		try{
		   userDao.deleteUser(toDate);
		}catch (Exception e) {
			log.error(e.toString());
		}
	}

	@Override
	public void sendReminderForProfileUpdate(){
		try{
		Map<String, Object> param=AbstractServiceImpl.getParamMap("code",ContextConstant.USER_TYPE_INTERNAL);
		List<UserDto> users=findDtos("getUserForProfileUpdateReminder", param);
		if(!CommonUtil.isCollectionEmpty(users)){
		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
      /*  Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", codeMap);*/
		MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_PROFILE_REMINDER_TEMPLATE);
		for(UserDto user:users)
		{
			String validTo=getDateForProfileUpdate(user.getCreated());
			if(mailTemplate!=null){
				Map<String,Object> map=new HashMap<>();
				/*map.put("@sourceCompanyName@",internalUser.getPartner().getName());*/
				map.put("@honour@","");
				/*map.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());*/
				map.put("@emailCorrCode@","");
				map.put("@today@",today);
				map.put("@companyName@",user.getPartner().getName());
				map.put("@factoryName@,","");
				map.put("@factoryCity@","");
				map.put("@expiryDate@",validTo);
				map.put("@vendorEmail@",user.getEmail());
				/*map.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
				map.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
				map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
				map.put("@companytel@",internalUser.getUserDetails().getTelephone1());
				map.put("@companyfax@",internalUser.getUserDetails().getFax1());*/
			    mailService.sentMailByTemplate(mailTemplate,map,user.getEmail());
			}
			if(user.getUserDetails() != null ){
            	Map<String, String> smsParams = new HashMap<String, String>();
            	smsParams.put(AppBaseConstant.SMS_PARAMETER_1, "Registration");
            	smsParams.put(AppBaseConstant.SMS_PARAMETER_1, validTo.replaceAll(" ", "%20"));
            	if(user.getUserDetails().getMobileNo() != null)
            		smsService.sendSMS(user.getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_EXPIRE, smsParams);
            }
			/*List<String> emails=new ArrayList<>();
			String date=getDateForProfileUpdate(user.getCreated());
			mailDto.setMailContent("<p>Hi</p><br><p>A gentle reminder to submit your profile along with the required documents till "+date+". <p><p>Regards,</p><p>MSEDCL</p>");
			emails.add(user.getEmail());
			mailDto.setRecipientList(emails);
			mailService.sendEmail(mailDto);*/
		  }
		}
		}catch (Exception e) {
			log.error(e.toString());
		}
		
	}
	private String getDateForProfileUpdate(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +7);
		Date toDate = cal.getTime();
		return new SimpleDateFormat("dd-MM-yyyy").format(toDate);
	}
	/*@Override
	public List<UrlPatternsDto> getUrlPatterns(){
		List<UrlPatternsDto> list = new ArrayList<>();
		list = urlPatternService.findAll();
		return list;
	}
	@Override
	public List<Object[]> getUrlPatternsByRole(){
		return userDao.getUrlPatternsByRole();
	}*/

		
	@Override
	public void sendMailForPassword(UserDto userDto,MailTemplateDto mailTemplate) {
		if(userDto!=null && userDto.getEmail()!=null && userDto.getPassword()!=null)
		{
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
    		UserDto internalUser=findDto("getUserByRoleCode", codeMap);*/
    		
            if(mailTemplate!=null)
            {
            	Map<String,Object> map=new HashMap<>();
            	String subject=mailTemplate.getSubject();
            	/*subject=subject.replace("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
            	mailTemplate.setSubject(subject);
            	map.put("@User@",("Y").equals(userDto.getIsEmailLogin())?"Business Partner":"User");
            	map.put("@password@",userDto.getPassword());
            	map.put("@supportEmailId@","");
            	map.put("@contactEmail","");
            	/*map.put("@sourceCompanyName@",internalUser.getPartner().getName());*/
    			map.put("@honour@","");
    			/*map.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());*/
    			map.put("@emailCorrCode@","");
    			map.put("@today@",today);
    			map.put("@companyName@",userDto.getPartner().getName());
    			map.put("@factoryName@,","");
    			map.put("@factoryCity@","");
    			map.put("@vendorEmail@",userDto.getEmail());
    			/*map.put("@expiryDate@",today);*/
    			map.put("@subject@",mailTemplate.getSubject());
    			/*map.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
    			/*map.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());*/
    			/*map.put("@respectiveDeptEmail@","eAuctionApp ADMIN");
    			map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
    			map.put("@companytel@",internalUser.getUserDetails().getTelephone1());
    			map.put("@companyfax@",internalUser.getUserDetails().getFax1());*/
    			mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getEmail(),true);
            }
		}
			
	}
	
	public void sendMailOnVendorInvitation(UserDto userDto,MailTemplateDto mailTemplate){
		
		if(userDto!=null && userDto.getEmail()!=null && userDto.getPassword()!=null)
		{
			
        	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
    		UserDto internalUser=findDto("getUserByRoleCode", codeMap);
    		*/
           // if(mailTemplate!=null && internalUser!=null)
    		 if(mailTemplate!=null)
            {
            	Map<String,Object> map=new HashMap<>();
            	String subject="Registration Request -"+userDto.getPartner().getName();
            	/*subject=subject.replace("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
            	mailTemplate.setSubject(subject);
            	map.put("@vendorPassword@",userDto.getPassword().trim());
    			map.put("@vendorUserId@",userDto.getEmail().trim());
    			map.put("@registrationLink@",AppBaseConstant.REGISTATION_LINK);
    			map.put("@subject@",mailTemplate.getSubject());
    			List<String> to=new ArrayList<>();
    			List<String> Cc=new ArrayList<>();
    			to.add(userDto.getEmail());
    			/*Cc.add(AppBaseConstant.PURCHASE_TEAM);
    			Cc.add(userDto.getCreatedBy().getEmail());*/
    			Cc.add(AppBaseConstant.PURCHASE_TEAM);
    			Cc.add(AppBaseConstant.PURCHASE_TEAM_1);
    		//	Cc.add(userDto.getCreatedBy().getEmail());
    			mailService.sentMailByTemplateAsync(mailTemplate, map, to, Cc, true);
    			/*mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getEmail(),true);*/
            }
		}
	}

public void sendMailOnVendorRegistrationSubmit(UserDto userDto,MailTemplateDto mailTemplate){
		
		if(userDto!=null && userDto.getEmail()!=null)
		{
			
        	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
    		UserDto internalUser=findDto("getUserByRoleCode", codeMap);*/
    		
            if(mailTemplate!=null)
            {
            	Map<String,Object> map=new HashMap<>();
            	String subject=mailTemplate.getSubject();
            	subject=subject.replace("@VendorName@",userDto.getPartner().getName());
            	mailTemplate.setSubject(subject);
    			map.put("@subject@",mailTemplate.getSubject());
    			List<String> to=new ArrayList<>();
    			List<String> Cc=new ArrayList<>();
    			to.add(userDto.getEmail());
    			Cc.add(AppBaseConstant.PURCHASE_TEAM);
    			Cc.add(userDto.getCreatedBy().getEmail());
    			mailService.sentMailByTemplateAsync(mailTemplate, map, to, Cc, true);
    			/*mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getEmail(),true);*/
            }
		}
	}

public void sendMailOnVendorApproval(UserDto userDto,MailTemplateDto mailTemplate, String vendorCode){
	
	if(userDto!=null && userDto.getEmail()!=null)
	{
		
    	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", codeMap);*/
		
        if(mailTemplate!=null)
        {
        	Map<String,Object> map=new HashMap<>();
        	String subject=mailTemplate.getSubject();
        	subject=subject.replace("@VendorName@",userDto.getPartner().getName());
        	mailTemplate.setSubject(subject);
        	/*OLD CODE map.put("@subject@",mailTemplate.getSubject());
			 map.put("@vendorCode@",vendorCode);*/
			map.put("@Vednor_code@",vendorCode);
			
			List<String> toEmailIds=new ArrayList<>();
			toEmailIds.add(userDto.getEmail());
			
			List<String> ccEmailIds=new ArrayList<>();
			ccEmailIds.add(userDto.getCreatedBy().getEmail());
			ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
			ccEmailIds.add(AppBaseConstant.TAX_TEAM);
			mailService.sentMailByTemplateAsync(mailTemplate,map,toEmailIds,ccEmailIds,true);
        }
	}
}
	@Override
	public void sendMailOnForgotPassword(UserDto userDto) {
		MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_FORGOT_PASSWORD);
		/*Map<String, Object> mapList=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", mapList);*/
		//if(mailData!=null  && internalUser!=null){
		if(mailData!=null ){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> paramData=new HashMap<>();
			/*paramData.put("@sourceCompanyName@",internalUser.getPartner().getName());*/
			paramData.put("@honour@","");
			/*paramData.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());*/
			paramData.put("@generalDetailDate@",today);
			/*paramData.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
			paramData.put("@vendorPassword@", userDto.getPassword());
			/*paramData.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			paramData.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			paramData.put("@companyFax@",internalUser.getUserDetails().getFax1());*/
			if(userDto.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, paramData,userDto.getEmail());
			}
		
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int loginGenerator(List<UserDto> userList,MailTemplateDto mailTemplate, UserDto internalUser){
		Map<String, Object> map=null;
		int countFail = 0;
		int countSuccess = 0;
		MailTemplateDto mailTemplate1=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.ALKYL_MAIL_TO_INVITER);
		ContextDto context = contextService.getContext();
		for(UserDto usr: userList){
			try{
				usr.setCreatedBy(context.getUserDto());
				map=new HashMap<>();
				String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
				usr.setPassword(password);
				map.put("isPasswordUpdated", "N");
				map.put("password", authManager.getSaltedHash(password, usr.getEmail()));
				User user= new User();
				user.setUserId(contextService.getUser().getUserId());
				map.put("createdBy", user);
				int result=updateByJpql(map, "userId", usr.getUserId());
				if(result>0){
					sendMailLoginGenerator(usr, mailTemplate, internalUser);
					userService.sendInvitationMailToInviter(usr,mailTemplate1);
					map.clear();
					map.put("isLoginCreated", "Y");
					map.put("isInvited", "Y");
					updateByJpql(map, "userId", usr.getUserId());
					countSuccess++;
					log.info("Login created for : "+usr.getEmail());
				}else{
					countFail++;
				}
			}catch(Exception e){
				countFail++;
				log.error(e.getMessage());
			}
		}
		
		/*UserDto usr= userList.get(0);
		usr.setEmail("support_etender@mahadiscom.in");
			map=new HashMap<>();
			String password = AuthGenerator.generateToken(8, 8, 2, 2, 1);
			usr.setPassword(password);
			map.put("isPasswordUpdated", "N");
			map.put("password", authManager.getSaltedHash(password, usr.getPartner().getPanNumber()));
			int result=updateByJpql(map, "userId", usr.getUserId());
			if(result>0){
				sendMailLoginGenerator(usr, mailTemplate, internalUser);
				map.clear();
				map.put("isLoginCreated", "Y");
				updateByJpql(map, "userId", usr.getUserId());
				countSuccess++;
			}else{
				countFail++;
			}*/
		
		System.out.println(countSuccess+" Success/ "+countFail+" Failed");
		return countFail;
	}
	
	@Override
	public void sendMailLoginGenerator(UserDto userDto,MailTemplateDto mailData,UserDto internalUser) {
		/*Map<String, Object> mapList=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", mapList);*/
		if(mailData!=null ){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> paramData=new HashMap<>();
			/*paramData.put("@sourceCompanyName@",internalUser.getPartner().getName());*/
			paramData.put("@destinationCompanyName@",userDto.getPartner().getName());
			/*paramData.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());*/
			paramData.put("@today@",today);
			/*paramData.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
			paramData.put("@vendorPassword@",userDto.getPassword().trim());
			paramData.put("@vendorUserId@",userDto.getEmail().trim());
			paramData.put("@registrationLink@",AppBaseConstant.REGISTATION_LINK);
			/*paramData.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());*/
			/*paramData.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			paramData.put("@companyFax@",internalUser.getUserDetails().getFax1());*/
			if(userDto.getEmail()!=null){
				mailService.sentMailByTemplateAsync(mailData, paramData,userDto.getEmail(),true);
			}
		
		}
	}
	
	
	
	
	
	@Override
	public List<UserDto> getUserByEmailOrUserName(String usernameOrEmail,String isInvitedVendor)
	{
		List<UserDto> userList=new ArrayList<>();
		List<User> userlist=userDao.getUserByEmailOrUserName(usernameOrEmail,isInvitedVendor);
//		for(User user:userlist)
//		{
//			UserDto userDto=mapper.convertEntityToDto(user,UserDto.class);
//			userList.add(userDto);
//		}
		
		ObjectConverter<User,UserDto> currentConverter = null;
		userList = getDtoList(userlist, currentConverter);
		return userList;
	}


	@Override
	public List<UserDto> getUserDetailsByEmailOrUserName(String usernameOrEmail,Long userId){
		Map<String, Object> params=new HashMap<>();
		params.put("username", usernameOrEmail);
		params.put("userId", userId);
		return findDtos("getUserDetailsbyEmailOrUserName", params);
	}

	@Override
	public List<Object> getTilesUrl(String value) {
		
		return userDao.getTilesUrl(value);
	}

	@Override
	public RoleDto getDefaultRole(Set<RoleDto> roles) {
		
		if(!CommonUtil.isCollectionEmpty(roles)){
			return roles.iterator().next();
		}
		return null;
	}

	@Override
	public boolean checkUserNameValue(String userName,Long userId) {
		// TODO Auto-generated method stub
		
		return userDao.checkUserNameValue(userName,userId);
	}
public void sendMailOnForgotPassword(UserDto userDto,MailTemplateDto mailTemplate){
		
		if(userDto!=null && userDto.getEmail()!=null && userDto.getPassword()!=null)
		{
			
        	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
    		UserDto internalUser=findDto("getUserByRoleCode", codeMap);
    		*/
           // if(mailTemplate!=null && internalUser!=null)
    		 if(mailTemplate!=null)
            {
            	Map<String,Object> map=new HashMap<>();
            	String subject=mailTemplate.getSubject();
            	/*subject=subject.replace("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
            	mailTemplate.setSubject(" Forgot/Reset Password Request - "+(("Y").equals(userDto.getIsEmailLogin())?userDto.getEmail().trim():userDto.getUserName()));
            	map.put("@vendorPassword@",userDto.getPassword().trim());
    			/*map.put("@vendorUserId@",("Y").equals(userDto.getIsEmailLogin())?userDto.getEmail().trim():userDto.getUserName());*/
    			/*map.put("@registrationLink@",AppBaseConstant.REGISTATION_LINK);*/
    			map.put("@subject@",mailTemplate.getSubject());
    			/*map.put("@User@",("Y").equals(userDto.getIsEmailLogin())?"Business Partner":"User");*/
    			map.put("@UserName@",("Y").equals(userDto.getIsEmailLogin())?"Partner":userDto.getName());
    			mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getEmail(),true);
            }
		}
	}

@Override
public void sendMailOnVendorApprovalAlkyl(UserDto user, MailTemplateDto mailTemplate, String name) {
	if(user!=null && user.getEmail()!=null)
	{
		
    	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", codeMap);
		*/
       // if(mailTemplate!=null && internalUser!=null)
		 if(mailTemplate!=null)
        {
        	Map<String,Object> map=new HashMap<>();
        	String subject=mailTemplate.getSubject();
        	/*subject=subject.replace("@sourcingcompanyshortname@",internalUser.getPartner().getValue());*/
        	mailTemplate.setSubject(mailTemplate.getSubject());
        	map.put("@vendor_name",name);
			map.put("@Vendor_email",user.getEmail());
			List<String> to=new ArrayList<>();
			List<String> Cc=new ArrayList<>();
			to.add(user.getCreatedBy().getEmail());
			Cc.add(AppBaseConstant.PURCHASE_TEAM);
			mailService.sentMailByTemplateAsync(mailTemplate, map, to, Cc, true);
        }
	}
}
public void sendInvitationMailToInviter(UserDto userDto,MailTemplateDto mailTemplate){
	
	if(userDto!=null && userDto.getEmail()!=null && userDto.getPassword()!=null)
	{
		
    	/*Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=findDto("getUserByRoleCode", codeMap);
		*/
       // if(mailTemplate!=null && internalUser!=null)
		 if(mailTemplate!=null)
        {
        	Map<String,Object> map=new HashMap<>();
        	String subject=mailTemplate.getSubject();
        	/*String subject="Registration Request -"+userDto.getPartner().getName();*/
        	subject=subject.replace("@VendorName@",userDto.getPartner().getName());
        	mailTemplate.setSubject(subject);
        	map.put("@Creator@",userDto.getCreatedBy().getName());
			map.put("@UserName@",userDto.getUserDetails().getName());
			map.put("@CompanyName@",userDto.getPartner().getName());
			map.put("@EmailID@",userDto.getEmail());
			map.put("@MobileNo@",userDto.getUserDetails().getMobileNo());
			/*List<String> to=new ArrayList<>();
			List<String> Cc=new ArrayList<>();
			to.add(userDto.getCreatedBy().getEmail());*/
			/*Cc.add(AppBaseConstant.PURCHASE_TEAM);
			Cc.add(userDto.getCreatedBy().getEmail());*/
			/*mailService.sentMailByTemplateAsync(mailTemplate, map, to, Cc, true);*/
			mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getCreatedBy().getEmail(),true);
        }
	}
}

@Override
public void sendMailOnPOReleased(PurchaseOrderDto poDto, MailTemplateDto mailTemplate,List<UserDetailsDto> userDetail) {
	 if(mailTemplate!=null && poDto!=null)
     {
		 Map<String,Object> param=new HashMap<>();
		 param.put("referenceCode", 45l);
		 param.put("code", poDto.getPartner().getValue());
		ReferenceListDto dto=refrenceListService.findDto("getRefernceListByValue", param);
		UserDto purGrp =findDto("getUserBYUserName",
				AbstractContextServiceImpl.getParamMap("username", dto.getDescription()));
     	Map<String,Object> map=new HashMap<>();
     	String subject=mailTemplate.getSubject();
     	/*String subject="Registration Request -"+userDto.getPartner().getName();*/
     	subject=subject.replace("@VendorName@",poDto.getPartner().getName());
     	subject=subject.replace("@PO_Number@",poDto.getPurchaseOrderNumber());
     	subject=subject.replace("@PODate@",DateUtil.getDateString(poDto.getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
     	mailTemplate.setSubject(subject);
     	map.put("@VendorName@",poDto.getPartner().getName());
			map.put("@PO_Number@",poDto.getPurchaseOrderNumber());
			map.put("@PODate@",DateUtil.getDateString(poDto.getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
			List<String> to=new ArrayList<>();
			List<String> Cc=new ArrayList<>();
			for(UserDetailsDto userDetails:userDetail){
			to.add(userDetails.getEmail());
			}
			Cc.add(purGrp.getEmail());
			Cc.add(poDto.getReqby().getEmail());
			mailService.sentMailByTemplateAsync(mailTemplate, map, to, Cc, true);
			/*mailService.sentMailByTemplateAsync(mailTemplate,map,userDto.getEmail(),true);*/
     }
	
}






}
