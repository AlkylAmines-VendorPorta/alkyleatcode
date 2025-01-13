package com.novelerp.eat.entity;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
/*@Entity
@Table(name="t_gtp_scrutiny")*/
public class GtpScrutiny extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long gtpScrutinyId;
	private TechnicalScrutiny technicalScrutiny;
	private BidderGtp bidderGtp;
	private String isDeviation;
	private String comment;
	private String deviationTypeCode;
	private String textResponse;
	private Attachment attachment;
	
	
	/*@Id
	@SequenceGenerator(name="t_gtp_scrutiny_seq",sequenceName="t_gtp_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_gtp_scrutiny_seq")	
	@Column(name = "t_gtp_scrutiny_id", updatable=false)
	public Long getGtpScrutinyId() {
		return gtpScrutinyId;
	}
	public void setGtpScrutinyId(Long gtpScrutinyId) {
		this.gtpScrutinyId = gtpScrutinyId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tech_scrutiny_id")
	public TechnicalScrutiny getTechnicalScrutiny() {
		return technicalScrutiny;
	}
	public void setTechnicalScrutiny(TechnicalScrutiny technicalScrutiny) {
		this.technicalScrutiny = technicalScrutiny;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_gtp_id")
	public BidderGtp getBidderGtp() {
		return bidderGtp;
	}
	public void setBidderGtp(BidderGtp bidderGtp) {
		this.bidderGtp = bidderGtp;
	}
	@Column(name="isdeviation")
	public String getIsDeviation() {
		return isDeviation;
	}
	public void setIsDeviation(String isDeviation) {
		this.isDeviation = isDeviation;
	}
	@Column(name="deviation_comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="deviaton_type_code")
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
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
	
	*/
	
}
