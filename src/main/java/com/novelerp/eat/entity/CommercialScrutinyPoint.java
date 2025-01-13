package com.novelerp.eat.entity;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.ScrutinyPoint;
import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_commercial_scrutiny_point")
public class CommercialScrutinyPoint extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long commercialScrutinyPointId;
	private ScrutinyPoint scrutinyPoint;
	private String deviationTypeCode;
	private String deviationStatus;
	private String comment;
	private String textResponse;
	private Attachment attachment;
	
	@Id
	@SequenceGenerator(name="t_commercial_scrutiny_point_seq",sequenceName="t_commercial_scrutiny_point_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_commercial_scrutiny_point_seq")	
	@Column(name = "t_commercial_scrutiny_point_id", updatable=false)
	public Long getCommercialScrutinyPointId() {
		return commercialScrutinyPointId;
	}
	public void setCommercialScrutinyPointId(Long commercialScrutinyPointId) {
		this.commercialScrutinyPointId = commercialScrutinyPointId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_scrutiny_point_id")
	public ScrutinyPoint getScrutinyPoint() {
		return scrutinyPoint;
	}
	public void setScrutinyPoint(ScrutinyPoint scrutinyPoint) {
		this.scrutinyPoint = scrutinyPoint;
	}
	@Column(name="deviation_type_code")
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="text_response")
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Column(name="deviation_status_code")
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}
	
}
