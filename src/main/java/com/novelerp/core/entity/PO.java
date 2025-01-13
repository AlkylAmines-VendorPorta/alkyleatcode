
package com.novelerp.core.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

/**
 * Persistence Object class. Classes annotated with @Entity annotation extends this class for required common columns (expect context columns).
 * @author Vivek Birdi
 *
 */
@MappedSuperclass
public class PO  implements Persistable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private User createdBy;
	private User updatedBy;
	
	*/
	private Timestamp created;
	private Timestamp updated;
	private String isActive="Y";
	
	@Transient
	private boolean loadDefault = false;
	
	@Column(name="created", updatable=false)
	public Timestamp getCreated() {
		return created;
	}
	
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Column(name="updated")
	public Timestamp getUpdated() {
		return updated;
	}
	
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	@PreUpdate
	public void update() {
		updated = new Timestamp(System.currentTimeMillis());
	}

	@PrePersist
	public void create() {
		created = updated = new Timestamp(System.currentTimeMillis());
	}

	@Column(name="isactive")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Transient
	public boolean isLoadDefault() {
		return loadDefault;
	}

	@Transient
	public void setLoadDefault(boolean loadDefault) {
		this.loadDefault = loadDefault;
	}

}

	
