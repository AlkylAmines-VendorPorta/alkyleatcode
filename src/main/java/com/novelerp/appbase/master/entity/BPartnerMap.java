package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name = "m_bpartner_map")
public class BPartnerMap extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5114652250037675343L;
	
	private long mBPartnerMapId;
	private Bpartner requestPartnerId;
	private User targetPartnerId;
	private String isRequestSend;
	private String isRequestApproved;
	
	
	
	@Id
	@SequenceGenerator(name = "m_bpartner_map_seq", sequenceName = "m_bpartner_map_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bpartner_map_seq")
	@Column(name = "m_bpartner_map_id", updatable = false)
	public long getmBPartnerMapId() {
		return mBPartnerMapId;
	}
	public void setmBPartnerMapId(long mBPartnerMapId) {
		this.mBPartnerMapId = mBPartnerMapId;
	}
	
	
	
	
	
	

	
	@Column(name="is_request_send")
	public String getIsRequestSend() {
		return isRequestSend;
	}
	public void setIsRequestSend(String isRequestSend) {
		this.isRequestSend = isRequestSend;
	}
	
	@Column(name="is_request_approved")
	public String getIsRequestApproved() {
		return isRequestApproved;
	}
	public void setIsRequestApproved(String isRequestApproved) {
		this.isRequestApproved = isRequestApproved;
	}
	
	@OneToOne
	@JoinColumn(name="request_partner_id" ,referencedColumnName="m_bpartner_id")
	public Bpartner getRequestPartnerId() {
		return requestPartnerId;
	}
	public void setRequestPartnerId(Bpartner requestPartnerId) {
		this.requestPartnerId = requestPartnerId;
	}
	
	@OneToOne
	@JoinColumn(name="target_partner_id" ,referencedColumnName="ad_user_id")
	public User getTargetPartnerId() {
		return targetPartnerId;
	}
	public void setTargetPartnerId(User targetPartnerId) {
		this.targetPartnerId = targetPartnerId;
	}
	
	
	
	
	
}
