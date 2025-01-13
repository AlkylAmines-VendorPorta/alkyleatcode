package com.novelerp.alkyl.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.core.util.DateUtil;

//@Entity
@Embeddable
@Table(name="AuditLog")
public class AuditLog  extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long createdByID;
	private String createdByName;
	private Date createdDateTime;
	private Long updatedByID;
	private String updatedByName;
	private Date updatedDateTime;
	private String updatedColumn;
	private String oldValue;
	private String newValue;
	private String uniqueKey;
	private String tableName;
	
	
	@Column(name="CreatedByID")
	public Long getCreatedByID() {
		return createdByID;
	}
	public void setCreatedByID(Long createdByID) {
		this.createdByID = createdByID;
	}
	
	@Column(name="CreatedByName")
	public String getCreatedByName() {
		return createdByName;
	}
	
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	
	@Column(name="CreatedDateTime")
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	@Column(name="UpdatedByID")
	public Long getUpdatedByID() {
		return updatedByID;
	}

	public void setUpdatedByID(Long updatedByID) {
		this.updatedByID = updatedByID;
	}
	
	@Column(name="UpdatedByName")
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	
	@Column(name="UpdatedDateTime")
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	
	@Column(name="UpdatedColumn")
	public String getUpdatedColumn() {
		return updatedColumn;
	}
	public void setUpdatedColumn(String updatedColumn) {
		this.updatedColumn = updatedColumn;
	}
	
	@Column(name="OldValue")
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	
	@Column(name="NewValue")
	public String getNewValue() {
		return newValue;
	}	
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
	@Column(name="UniqueKey")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	
	@Column(name="TableName")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
	
	

	
	
	

	

	
	
	
	

	
	
	
	
	
	

	
	
}
