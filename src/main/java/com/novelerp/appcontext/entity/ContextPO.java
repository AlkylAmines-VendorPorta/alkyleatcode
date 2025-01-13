
package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.novelerp.core.entity.PO;
/**
 * Context Persistence Object class. Classes annotated with @Entity annotation extends this class for required common columns and context columns.
 * @author Vivek Birdi
 *
 */

@MappedSuperclass
public class ContextPO  extends PO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User createdBy;
	private User updatedBy;
	private Bpartner partner;
	private String createdSessionId;
	private String updatedSessionId;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdBy", updatable=false)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updatedBy")
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="m_bpartner_id")
	public Bpartner getPartner() {
		return partner;
	}

	public void setPartner(Bpartner partner) {
		this.partner = partner;
	}
	@Column(name="created_session_id")
	public String getCreatedSessionId() {
		return createdSessionId;
	}

	public void setCreatedSessionId(String createdSessionId) {
		this.createdSessionId = createdSessionId;
	}

	@Column(name="updated_session_id")
	public String getUpdatedSessionId() {
		return updatedSessionId;
	}

	public void setUpdatedSessionId(String updatedSessionId) {
		this.updatedSessionId = updatedSessionId;
	}
}

	
