package com.novelerp.appcontext.entity;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.core.util.DateUtil;

/**
 * @author  Aman Sahu
 *
 */
@Entity
@Table(name="ad_user_details")
public class UserDetails extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long userDetailsId;
	/*private User userId;*/
	private String name;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String fax1;
	private String fax2;
	private String telephone1;
	private String telephone2;
	private Designation designation;
	private Location location;
	private LocationType locationType;
	private String userDetailType;
	private String title;
	private String email;
	private String locationTypeRef;
	private OfficeLocation officeLocation;
	private String gender;
	private Date dob;
	private String remark;
	private String isApproved;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String creatorOrbidder;
	private String otherDesignationDesc;
	private String urlString;
	private String themeColor;
	private Attachment profileLogo;
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
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date activeFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date activeTill;
	private String approver;
	private String costCentre;
	private String personalMail;
	private String personalContactNo;
	private User approverUser;
	private String approverId;
	private String sapId;
	
	@Id
	@SequenceGenerator(name="ad_user_details_seq",sequenceName="ad_user_details_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_user_details_seq")	
	@Column(name = "ad_user_details_id", updatable=false)
	public Long getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "fax_1")
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	@Column(name = "fax_2")
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	@Column(name = "telephone_1")
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	@Column(name = "telephone_2")
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="m_designation_id")
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="m_location_type_id")
	public LocationType getLocationType() {
		return locationType;
	}
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	
	@Column(name="user_detail_type")
	public String getUserDetailType() {
		return userDetailType;
	}
	public void setUserDetailType(String userDetailType) {
		this.userDetailType = userDetailType;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="location_type")
	public String getLocationTypeRef() {
		return locationTypeRef;
	}
	public void setLocationTypeRef(String locationTypeRef) {
		this.locationTypeRef = locationTypeRef;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_office_location_id")
	public OfficeLocation getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(OfficeLocation officeLocation) {
		this.officeLocation = officeLocation;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="dob")
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Column(name="is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	@Column(name="creator_or_bidder")
	public String getCreatorOrbidder() {
		return creatorOrbidder;
	}
	public void setCreatorOrbidder(String creatorOrbidder) {
		this.creatorOrbidder = creatorOrbidder;
	}
	@Column(name="other_designation_desc")
	public String getOtherDesignationDesc() {
		return otherDesignationDesc;
	}
	public void setOtherDesignationDesc(String otherDesignationDesc) {
		this.otherDesignationDesc = otherDesignationDesc;
	}
	@Column(name="url_string")
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="profile_logo")
	public Attachment getProfileLogo() {
		return profileLogo;
	}
	public void setProfileLogo(Attachment profileLogo) {
		this.profileLogo = profileLogo;
	}
	@Column(name="theme_color")
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="director_uid")
	public String getDirectorUID() {
		return directorUID;
	}
	public void setDirectorUID(String directorUID) {
		this.directorUID = directorUID;
	}
	@Column(name="qualification")
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	@Column(name="experience")
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	@Column(name="user_designation")
	public String getUserDesignation() {
		return userDesignation;
	}
	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	@Column(name="user_dept")
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	@Column(name="is_receive_enquiry")
	public String getIsReceiveEnquiry() {
		return isReceiveEnquiry;
	}
	public void setIsReceiveEnquiry(String isReceiveEnquiry) {
		this.isReceiveEnquiry = isReceiveEnquiry;
	}
	@Column(name="is_receive_po")
	public String getIsReceivePO() {
		return isReceivePO;
	}
	public void setIsReceivePO(String isReceivePO) {
		this.isReceivePO = isReceivePO;
	}
	@Column(name="is_receive_ac_info")
	public String getIsReceiveACInfo() {
		return isReceiveACInfo;
	}
	public void setIsReceiveACInfo(String isReceiveACInfo) {
		this.isReceiveACInfo = isReceiveACInfo;
	}
	
	@Column(name="director_type")
	public String getDirectorType() {
		return directorType;
	}
	public void setDirectorType(String directorType) {
		this.directorType = directorType;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	@Column(name="active_from")
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	@Column(name="active_till")
	public Date getActiveTill() {
		return activeTill;
	}
	public void setActiveTill(Date activeTill) {
		this.activeTill = activeTill;
	}
	@Column(name="approver")
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	@Column(name="cost_centre")
	public String getCostCentre() {
		return costCentre;
	}
	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}
	@Column(name="personal_mail")
	public String getPersonalMail() {
		return personalMail;
	}
		
	@Column(name="approver_id")
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	
	@Column(name="sap_id")
	public String getSapId() {
		return sapId;
	}
	public void setSapId(String sapId) {
		this.sapId = sapId;
	}
	public void setPersonalMail(String personalMail) {
		this.personalMail = personalMail;
	}
	@Column(name="personal_contact")
	public String getPersonalContactNo() {
		return personalContactNo;
	}
	public void setPersonalContactNo(String personalContactNo) {
		this.personalContactNo = personalContactNo;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_id",referencedColumnName="ad_user_id")
	public User getApproverUser() {
		return approverUser;
	}
	public void setApproverUser(User approverUser) {
		this.approverUser = approverUser;
	}
	
	
	
}
