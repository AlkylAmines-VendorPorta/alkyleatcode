/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;


@Entity
@Table(name="t_standard_custom_doc")
public class StandardCustomDoc extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long standardCustomDocId;
	private Attachment attachment;
	private String code;
	private String name;
	private String description;
	private Long index;
	private TAHDRDetail tahdrDetail;
	
	@Id
	@SequenceGenerator(name="t_standard_custom_doc_seq",sequenceName="t_standard_custom_doc_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_standard_custom_doc_seq")
	@Column(name="t_standard_custom_doc_id",updatable=false)
	public Long getStandardCustomDocId() {
		return standardCustomDocId;
	}
	public void setStandardCustomDocId(Long standardCustomDocId) {
		this.standardCustomDocId = standardCustomDocId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="rank")
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
}
