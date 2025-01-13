package com.novelerp.alkyl.dto;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

public class AuditLogDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8862829487655726913L;
	
	private Long createdByID;
	private String createdByName;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date createdDateTime;
	private Long updatedByID;
	private String updatedByName;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date updatedDateTime;
	private String updatedColumn;
	private String oldValue;
	private String newValue;
	private String uniqueKey;
	private String tableName;
	public Long getCreatedByID() {
		return createdByID;
	}
	public String getCreatedByName() {
		return createdByName;
	}

	public Long getUpdatedByID() {
		return updatedByID;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}
	public String getUpdatedColumn() {
		return updatedColumn;
	}
	public String getOldValue() {
		return oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public String getTableName() {
		return tableName;
	}
	public void setCreatedByID(Long createdByID) {
		this.createdByID = createdByID;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public void setUpdatedByID(Long updatedByID) {
		this.updatedByID = updatedByID;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public void setUpdatedColumn(String updatedColumn) {
		this.updatedColumn = updatedColumn;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	@Override
	public String toString() {
		return "AuditLogDto [createdByID=" + createdByID + ", createdByName=" + createdByName + ", createdDateTime="
				+ createdDateTime + ", updatedByID=" + updatedByID + ", updatedByName=" + updatedByName
				+ ", updatedDateTime=" + updatedDateTime + ", updatedColumn=" + updatedColumn + ", oldValue=" + oldValue
				+ ", newValue=" + newValue + ", uniqueKey=" + uniqueKey + ", tableName=" + tableName + "]";
	}




	
	

}
