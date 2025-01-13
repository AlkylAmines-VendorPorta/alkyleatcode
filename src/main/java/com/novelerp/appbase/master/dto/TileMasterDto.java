package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/** 
 * @author Aman
 *
 */
public class TileMasterDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long tileMasterId;
	private String name;
	private String code;
	private String description;
	private Long parentId;
	private String isSummary;
	private Long levelNo;
	private Long sequenceNo;
	private String entity;
	private String countWhereCondition;
	private String elementId;
	private TileMasterDto parentTile;
	private String isWorkflow;
	private Long viewSequence;
	private String path;
	
	public Long getTileMasterId() {
		return tileMasterId;
	}
	public String getIsWorkflow() {
		return isWorkflow;
	}
	public void setIsWorkflow(String isWorkflow) {
		this.isWorkflow = isWorkflow;
	}
	public void setTileMasterId(Long tileMasterId) {
		this.tileMasterId = tileMasterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}
	public Long getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Long levelNo) {
		this.levelNo = levelNo;
	}
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getCountWhereCondition() {
		return countWhereCondition;
	}
	public void setCountWhereCondition(String countWhereCondition) {
		this.countWhereCondition = countWhereCondition;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public TileMasterDto getParentTile() {
		return parentTile;
	}
	public void setParentTile(TileMasterDto parentTile) {
		this.parentTile = parentTile;
	}
	public Long getViewSequence() {
		return viewSequence;
	}
	public void setViewSequence(Long viewSequence) {
		this.viewSequence = viewSequence;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	

}
