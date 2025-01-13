/*File Name :User.java
*created by :Lakshmi
*Description : This is entity file, used for creating respective database table's column's
*			   Used for details about users.
*Database Table dependency : eauction/ad_user			   	
*
*/
package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Vivek Birdi(changed by Aman Sahu)
 *
 */
@Entity
@Table(name="ad_user")
public class User extends ContextPO {

	private static final long serialVersionUID = 7991344774735272396L;
	public static final String CLASS_Name="User";
	
	private Long userId;
	private String name;
	
	private String password;
	private String email;
	private String userName;
	private String hasPlainPassword;
	private UserDetails userDetails;
	private String isPasswordUpdated;
	private String isUserDeleted;
	private String isLoginCreated;
	private String isInvited;
	private User approver;
	private String isEmailLogin;
	private String purchaseGroup;
	
	@Id
	@SequenceGenerator(name="ad_user_seq",sequenceName="ad_user_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_user_seq")	
	@Column(name = "ad_user_id", updatable=false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="email",unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ad_user_details_id")
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	@Column(name = "hasPlainPassword")
	public String getHasPlainPassword() {
		return hasPlainPassword;
	}

	public void setHasPlainPassword(String hasPlainPassword) {
		this.hasPlainPassword = hasPlainPassword;
	}
    @Column(name="is_password_updated")
	public String getIsPasswordUpdated() {
		return isPasswordUpdated;
	}

	public void setIsPasswordUpdated(String isPasswordUpdated) {
		this.isPasswordUpdated = isPasswordUpdated;
	}
    @Column(name="is_user_deleted")
	public String getIsUserDeleted() {
		return isUserDeleted;
	}

	public void setIsUserDeleted(String isUserDeleted) {
		this.isUserDeleted = isUserDeleted;
	}

	@Column(name="isLoginCreated")
	public String getIsLoginCreated() {
		if(null==isLoginCreated){
			return "N";
		}
		return isLoginCreated;
	}

	public void setIsLoginCreated(String isLoginCreated) {
		this.isLoginCreated = isLoginCreated;
	}

	@Column(name="is_invited")
	public String getIsInvited() {
		return isInvited;
	}

	public void setIsInvited(String isInvited) {
		this.isInvited = isInvited;
	}

	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="approver",referencedColumnName="ad_user_id")
	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	@Column(name="is_emai_login")
	public String getIsEmailLogin() {
		return isEmailLogin;
	}

	public void setIsEmailLogin(String isEmailLogin) {
		this.isEmailLogin = isEmailLogin;
	}

	@Column(name="purchase_group")
	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	
	

	
}
