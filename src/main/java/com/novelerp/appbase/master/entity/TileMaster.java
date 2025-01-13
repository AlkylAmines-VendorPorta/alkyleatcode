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

/** 
 * @author Aman
 *
 */
@Entity
@Table(name="m_tile")
public class TileMaster extends ContextPO{
	
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
	private TileMaster parentTile;
	private String isWorkflow;
	private Long viewSequence;
	private String path;

	@Id
	@SequenceGenerator(name="m_tile_seq",sequenceName="m_tile_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_tile_seq")	
	@Column(name = "m_tile_id", updatable=false)
	public Long getTileMasterId() {
		return tileMasterId;
	}
	public void setTileMasterId(Long tileMasterId) {
		this.tileMasterId = tileMasterId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="parent_id")
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	@Column(name="is_summary")
	public String getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}
	@Column(name="level_no")
	public Long getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Long levelNo) {
		this.levelNo = levelNo;
	}
	@Column(name="sequence_no")
	public Long getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	@Column(name = "entity")
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Column(name = "count_where_condition")
	public String getCountWhereCondition() {
		return countWhereCondition;
	}

	public void setCountWhereCondition(String countWhereCondition) {
		this.countWhereCondition = countWhereCondition;
	}
	
	@Column(name = "element_id")
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	@Column(name = "is_workflow")
	public String getIsWorkflow() {
		return isWorkflow;
	}
	public void setIsWorkflow(String isWorkflow) {
		this.isWorkflow = isWorkflow;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_parent_id")
	public TileMaster getParentTile() {
		return parentTile;
	}
	public void setParentTile(TileMaster parentTile) {
		this.parentTile = parentTile;
	}
	@Column(name = "view_sequence")
	public Long getViewSequence() {
		return viewSequence;
	}
	public void setViewSequence(Long viewSequence) {
		this.viewSequence = viewSequence;
	}
	
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
