package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Designation;
/**
 * 
 * @author varsha
 *
 */
import com.novelerp.appcontext.entity.User;
@Entity
@Table(name="t_committee_participant")
public class TenderCommitteeParticipant extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long committeeParticipantId;
	private TenderCommittee tenderCommittee;
	private Designation designation;
	private User user;
	private String sessionKey;
	private String isMailed;
	
	@Id
	@SequenceGenerator(name="t_committee_participant_seq",sequenceName="t_committee_participant_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_committee_participant_seq")	
	@Column(name = "t_committee_participant_id", updatable=false)
	public Long getCommitteeParticipantId() {
		return committeeParticipantId;
	}
	public void setCommitteeParticipantId(Long committeeParticipantId) {
		this.committeeParticipantId = committeeParticipantId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_committee_id")
	public TenderCommittee getTenderCommittee() {
		return tenderCommittee;
	}
	public void setTenderCommittee(TenderCommittee tenderCommittee) {
		this.tenderCommittee = tenderCommittee;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_designation_id")
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="session_key")
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	@Column(name="is_mailed")
	public String getIsMailed() {
		return isMailed;
	}
	public void setIsMailed(String isMailed) {
		this.isMailed = isMailed;
	}
	

}
