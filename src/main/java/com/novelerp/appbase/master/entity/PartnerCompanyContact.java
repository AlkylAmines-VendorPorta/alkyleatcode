package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.entity.UserDetails;

/**
 * 
 * @author Aman
 *
 */
@Entity
@Table(name="m_bp_company_contact")
public class PartnerCompanyContact extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerCompanyContactId;
	private UserDetails userDetail;
	private String locationType;
	private Location location;
	
	
	@Id
	@SequenceGenerator(name="m_bp_company_contact_seq",sequenceName="m_bp_company_contact_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_company_contact_seq")	
	@Column(name = "m_bp_company_contact_id", updatable=false)
	public Long getPartnerCompanyContactId() {
		return partnerCompanyContactId;
	}
	public void setPartnerCompanyContactId(Long partnerCompanyContactId) {
		this.partnerCompanyContactId = partnerCompanyContactId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_details_id")
	public UserDetails getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetails userDetail) {
		this.userDetail = userDetail;
	}
	@Column(name="location_type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
