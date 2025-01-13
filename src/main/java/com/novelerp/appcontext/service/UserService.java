package com.novelerp.appcontext.service; 

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.VendorCredentialReadDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.service.CommonService;

public interface UserService extends CommonService<User, UserDto> {
		public  List<UserDto> fetchUsers(Long bpartnerId);
		
		public UserDto createUser(UserDto user);
		public UserDto updateUser(UserDto user);
		public UserDto getUser(Long userId);
		public boolean checkValue(String value,Long id);
		public boolean checkValue(String value,Long id,String code);
		public UserDetailsDto getDetailedUserById(Long userId);
		public List<UserDto> getUsersWithPlainPassword();
		public void hashPlainPassword(List<UserDto> users);
		public List<UserDto> findFilteredPartner(Map<String, Object> params,String CompanyType,String StateType);
		public UserDto getUserByPartnerId(Long bpartnerId);
		public void deleteUsers(Date toDate);
	    public void sendReminderForProfileUpdate();
	    public void sendMailForPassword(UserDto dto,MailTemplateDto template);
	    public void sendMailOnForgotPassword(UserDto dto);
	   /* public List<UrlPatternsDto> getUrlPatterns();
	    public List<Object[]> getUrlPatternsByRole();*/

		/**
		 * @param userList
		 * @param mailTemplate
		 * @return
		 */
		/*public int loginGenerator(List<UserDto> userList, MailTemplateDto mailTemplate);*/

		/**
		 * @param userDto
		 * @param mailData
		 */
		/*public void sendMailLoginGenerator(UserDto userDto, MailTemplateDto mailData);*/

		/**
		 * @param userDto
		 * @param mailData
		 * @param internalUser
		 */
		public void sendMailLoginGenerator(UserDto userDto, MailTemplateDto mailData, UserDto internalUser);

		/**
		 * @param userList
		 * @param mailTemplate
		 * @param internalUser
		 * @return
		 */
		public int loginGenerator(List<UserDto> userList, MailTemplateDto mailTemplate, UserDto internalUser);
		
		public UserDto fetchUser(Long bpartnerId);


		 public List<UserDto> getUserByEmailOrUserName(String usernameOrEmail, String isInvitedVendor);

		public void sendMailOnVendorInvitation(UserDto user, MailTemplateDto mailTemplate);

		public void sendMailOnVendorRegistrationSubmit(UserDto user, MailTemplateDto mailTemplate);

		public void sendMailOnVendorApproval(UserDto user, MailTemplateDto mailTemplate, String vendorCode);

		public List<UserDto> getUserDetailsByEmailOrUserName(String userNameorEmail,Long userId);

		public List<Object> getTilesUrl(String value);

		public RoleDto getDefaultRole(Set<RoleDto> roles);

		public boolean checkUserNameValue(String userName,Long userId);

		public void sendMailOnForgotPassword(UserDto userDto, MailTemplateDto mailTemplate);

		public void sendMailOnVendorApprovalAlkyl(UserDto user, MailTemplateDto mailTemplate, String name);

		public void sendInvitationMailToInviter(UserDto user, MailTemplateDto mailTemplate);

		public void sendMailOnPOReleased(PurchaseOrderDto poDto, MailTemplateDto mailTemplate, List<UserDetailsDto> userDetail);

	//	public List<UserDto> getVendorListReportbyFilter(VendorCredentialReadDto dto);

		


		
	  
}
