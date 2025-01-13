package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.eat.entity.TAHDR;

/**
 * @author Sanjeev Mahto
 * 
 *
 */
@Entity
@Table(name = "m_actionparticipant_map")
public class MAuctionParticipantMap  extends ContextPO{
	private static final long serialVersionUID = 1976840623817707666L;
	private long MActionParticipantMapId;
	
	private TAHDR tahdr;
	private Bpartner bPartner;
	private String status;
	
	@Id
	@SequenceGenerator(name = "m_actionparticipant_map_seq", sequenceName = "m_actionparticipant_map_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_actionparticipant_map_seq")
	@Column(name = "m_actionparticipant_map_id", updatable = false)
	public long getMActionParticipantMapId() {
		return MActionParticipantMapId;
	}
	public void setMActionParticipantMapId(long mActionParticipantMapId) {
		MActionParticipantMapId = mActionParticipantMapId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="b_partner_id")
	public Bpartner getbPartner() {
		return bPartner;
	}
	public void setbPartner(Bpartner bPartner) {
		this.bPartner = bPartner;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
