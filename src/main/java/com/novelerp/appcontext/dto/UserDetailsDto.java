package com.novelerp.appcontext.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.util.DateUtil;

public class UserDetailsDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long userDetailsId;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
			property="userId")
	private UserDto userId;
	private String name;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String fax1;
	private String fax2;
	private String telephone1;
	private String telephone2;
	private DesignationDto designation;
	private LocationDto location;
	private LocationTypeDto locationType;
	private String userDetailType;
	private String title;
	private String email;
	private String locationTypeRef;
	private OfficeLocationDto officeLocation;
	private String gender;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date dob;
	private String isApproved;
	private String remark;

	private String userCaptchaCode;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String otherDesignationDesc;
	private String isCreater;
	private String isBidder;
	private String creatorOrbidder;
	private String urlString;
	private AttachmentDto profileLogo;
	private String themeColor;
	private String directorUID;
	private String qualification;
	private String experience;
	private String userDesignation;
	private String userDept;
	private String isReceiveEnquiry;
	private String isReceivePO;
	private String isReceiveACInfo;
	private String directorType;
	private String department;
	private String plant;
	private Date activeFrom;
	private Date activeTill;
	private String approver;
	private String costCentre;
	private String personalMail;
	private String personalContactNo;
	private UserDto approverUser;
	private String approverId;
	private String sapId;
		
	public String getIsReceiveEnquiry() {
		return isReceiveEnquiry;
	}
	public void setIsReceiveEnquiry(String isReceiveEnquiry) {
		this.isReceiveEnquiry = isReceiveEnquiry;
	}
	public String getDirectorType() {
		return directorType;
	}
	public void setDirectorType(String directorType) {
		this.directorType = directorType;
	}
	public String getIsReceivePO() {
		return isReceivePO;
	}
	public void setIsReceivePO(String isReceivePO) {
		this.isReceivePO = isReceivePO;
	}
	public String getIsReceiveACInfo() {
		return isReceiveACInfo;
	}
	public void setIsReceiveACInfo(String isReceiveACInfo) {
		this.isReceiveACInfo = isReceiveACInfo;
	}
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
		
	public DesignationDto getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationDto designation) {
		this.designation = designation;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public LocationTypeDto getLocationType() {
		return locationType;
	}
	public void setLocationType(LocationTypeDto locationType) {
		this.locationType = locationType;
	}
	public UserDto getUserId() {
		return userId;
	}
	public void setUserId(UserDto userId) {
		this.userId = userId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getUserDetailType() {
		return userDetailType;
	}
	public void setUserDetailType(String userDetailType) {
		this.userDetailType = userDetailType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocationTypeRef() {
		return locationTypeRef;
	}
	public void setLocationTypeRef(String locationTypeRef) {
		this.locationTypeRef = locationTypeRef;
	}
	public OfficeLocationDto getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(OfficeLocationDto officeLocation) {
		this.officeLocation = officeLocation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getUserCaptchaCode() {
		return userCaptchaCode;
	}
	public void setUserCaptchaCode(String userCaptchaCode) {
		this.userCaptchaCode = userCaptchaCode;
	}
	public String getIsCreater() {
		return isCreater;
	}
	public void setIsCreater(String isCreater) {
		this.isCreater = isCreater;
	}
	public String getIsBidder() {
		return isBidder;
	}
	public void setIsBidder(String isBidder) {
		this.isBidder = isBidder;
	}
	public String getCreatorOrbidder() {
		return creatorOrbidder;
	}
	public void setCreatorOrbidder(String creatorOrbidder) {
		this.creatorOrbidder = creatorOrbidder;
	}
	public String getOtherDesignationDesc() {
		return otherDesignationDesc;
	}
	public void setOtherDesignationDesc(String otherDesignationDesc) {
		this.otherDesignationDesc = otherDesignationDesc;
	}
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public AttachmentDto getProfileLogo() {
		return profileLogo;
	}
	public void setProfileLogo(AttachmentDto profileLogo) {
		this.profileLogo = profileLogo;
	}
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirectorUID() {
		return directorUID;
	}
	public void setDirectorUID(String directorUID) {
		this.directorUID = directorUID;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Date getActiveTill() {
		return activeTill;
	}
	public void setActiveTill(Date activeTill) {
		this.activeTill = activeTill;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getCostCentre() {
		return costCentre;
	}
	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}
	public String getPersonalMail() {
		return personalMail;
	}
	public void setPersonalMail(String personalMail) {
		this.personalMail = personalMail;
	}
	public String getPersonalContactNo() {
		return personalContactNo;
	}
	public void setPersonalContactNo(String personalContactNo) {
		this.personalContactNo = personalContactNo;
	}
	public UserDto getApproverUser() {
		return approverUser;
	}
	public void setApproverUser(UserDto approverUser) {
		this.approverUser = approverUser;
	}
	
	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getSapId() {
		return sapId;
	}

	public void setSapId(String sapId) {
		this.sapId = sapId;
	}
	
	@Override
	public String toString() {
		return "UserDetailsDto [userDetailsId=" + userDetailsId + ", userId=" + userId + ", name=" + name
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", mobileNo="
				+ mobileNo + ", fax1=" + fax1 + ", fax2=" + fax2 + ", telephone1=" + telephone1 + ", telephone2="
				+ telephone2 + ", designation=" + designation + ", location=" + location + ", locationType="
				+ locationType + ", userDetailType=" + userDetailType + ", title=" + title + ", email=" + email
				+ ", locationTypeRef=" + locationTypeRef + ", officeLocation=" + officeLocation + ", gender=" + gender
				+ ", dob=" + dob + ", isApproved=" + isApproved + ", remark=" + remark + ", userCaptchaCode="
				+ userCaptchaCode + ", isEEApproved=" + isEEApproved + ", eeComment=" + eeComment + ", isCEApproved="
				+ isCEApproved + ", ceComment=" + ceComment + ", otherDesignationDesc=" + otherDesignationDesc
				+ ", isCreater=" + isCreater + ", isBidder=" + isBidder + ", creatorOrbidder=" + creatorOrbidder
				+ ", urlString=" + urlString + ", profileLogo=" + profileLogo + ", themeColor=" + themeColor
				+ ", directorUID=" + directorUID + ", qualification=" + qualification + ", experience=" + experience
				+ ", userDesignation=" + userDesignation + ", userDept=" + userDept + ", isReceiveEnquiry="
				+ isReceiveEnquiry + ", isReceivePO=" + isReceivePO + ", isReceiveACInfo=" + isReceiveACInfo
				+ ", directorType=" + directorType + ", department=" + department + ", plant=" + plant + ", activeFrom="
				+ activeFrom + ", activeTill=" + activeTill + ", approver=" + approver + ", costCentre=" + costCentre
				+ ", personalMail=" + personalMail + ", personalContactNo=" + personalContactNo + ", approverUser="
				+ approverUser + ", approverId=" + approverId + ", sapId=" + sapId + "]";
		}
	
}
