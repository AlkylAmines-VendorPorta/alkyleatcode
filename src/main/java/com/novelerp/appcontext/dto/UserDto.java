 package com.novelerp.appcontext.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Vivek Birdi
 *
 */
public class UserDto extends CommonContextDto{

	public UserDto() {
	
	}
	
	public UserDto(Long userId, String name){
		this.userId = userId;
		this.name = name;
	}
	
	private static final long serialVersionUID = -1870101719277050893L;
	
	private Long userId;
	private String name;	
	private String password;
	private String confirmPassword;
	private String oldPassword;
	private String email;
	private UserDetailsDto userDetails;
	private Set<RoleDto> roles;
	private String isPasswordUpdated;
	private String isUserDeleted;
	private String isLoginCreated;
	private String authToken;
	private String isInvited;

	private String userName;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="userId")
	private UserDto approver;
	private List<RoleDto> rolesList;
	private String isEmailLogin;
	private String purchaseGroup;
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email==null?null:email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDetailsDto getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsDto userDetails) {
		this.userDetails = userDetails;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getIsPasswordUpdated() {
		return isPasswordUpdated;
	}

	public void setIsPasswordUpdated(String isPasswordUpdated) {
		this.isPasswordUpdated = isPasswordUpdated;
	}

	public String getIsUserDeleted() {
		return isUserDeleted;
	}

	public void setIsUserDeleted(String isUserDeleted) {
		this.isUserDeleted = isUserDeleted;
	}

	public String getIsLoginCreated() {
		if(null==isLoginCreated){
			return "N";
		}
		return isLoginCreated;
	}

	public void setIsLoginCreated(String isLoginCreated) {
		this.isLoginCreated = isLoginCreated;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getIsInvited() {
		return isInvited;
	}

	public void setIsInvited(String isInvited) {
		this.isInvited = isInvited;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDto getApprover() {
		return approver;
	}

	public void setApprover(UserDto approver) {
		this.approver = approver;
	}

	public List<RoleDto> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RoleDto> rolesList) {
		this.rolesList = rolesList;
	}

	public String getIsEmailLogin() {
		if(null==isEmailLogin){
			return "Y";
		}else{
		return isEmailLogin;
		}
	}

	public void setIsEmailLogin(String isEmailLogin) {
		this.isEmailLogin = isEmailLogin;
	}
	
	
	

	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", oldPassword=" + oldPassword + ", email=" + email + ", userDetails=" + userDetails
				+ ", roles=" + roles + ", isPasswordUpdated=" + isPasswordUpdated + ", isUserDeleted=" + isUserDeleted
				+ ", isLoginCreated=" + isLoginCreated + ", authToken=" + authToken + ", isInvited=" + isInvited
				+ ", userName=" + userName + ", approver=" + approver + ", rolesList=" + rolesList + ", isEmailLogin="
				+ isEmailLogin + ", purchaseGroup=" + purchaseGroup + "]";
	}


	
	
}
