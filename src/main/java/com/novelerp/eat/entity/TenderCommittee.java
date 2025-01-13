package com.novelerp.eat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

/**
 * 
 * @author varsha
 *
 */
@Entity
@Table(name="t_committee")
public class TenderCommittee extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long tenderCommitteeId;
    private TAHDR tahdr;
    private String isProcessed;
    private User chairPerson;
    private TAHDRDetail tenderVersion;
    private Set<TenderCommitteeParticipant> participant;
    private String sessionKey;
    private String bidOpeningType;
    private String isMailed;
    
    @Id
    @SequenceGenerator(name="t_committee_seq",sequenceName="t_committee_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_committee_seq")	
	@Column(name = "t_committee_id", updatable=false)
	public Long getTenderCommitteeId() {
		return tenderCommitteeId;
	}
	public void setTenderCommitteeId(Long tenderCommitteeId) {
		this.tenderCommitteeId = tenderCommitteeId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	@Column(name="isprocessed")
	public String getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_id")
	public User getChairPerson() {
		return chairPerson;
	}
	public void setChairPerson(User chairPerson) {
		this.chairPerson = chairPerson;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTenderVersion() {
		return tenderVersion;
	}
	public void setTenderVersion(TAHDRDetail tenderVersion) {
		this.tenderVersion = tenderVersion;
	}
	/*  */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tenderCommittee")
	public Set<TenderCommitteeParticipant> getParticipant() {
		return participant;
	}
	public void setParticipant(Set<TenderCommitteeParticipant> participant) {
		this.participant = participant;
	}
	@Column(name="session_key")
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	@Column(name="bid_opening_type")
	public String getBidOpeningType() {
		return bidOpeningType;
	}
	public void setBidOpeningType(String bidOpeningType) {
		this.bidOpeningType = bidOpeningType;
	}
	@Column(name="is_mailed")
	public String getIsMailed() {
		return isMailed;
	}
	public void setIsMailed(String isMailed) {
		this.isMailed = isMailed;
	}
	
	
}
