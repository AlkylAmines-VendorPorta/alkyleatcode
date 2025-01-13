package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * Ankita
 */

@Entity
@Table(name="m_mail_template")
public class MailTemplate extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mailId;
	private String mailTemplate;
	private String templateType;
	private String subject;
	
	@Id
	@SequenceGenerator(name="m_mail_template_seq",sequenceName="m_mail_template_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_mail_template_seq")	
	@Column(name = "m_mail_template_id", updatable=false)
	public Long getMailId() {
		return mailId;
	}
	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}
	@Column(name = "mail_template")
	public String getMailTemplate() {
		return mailTemplate;
	}
	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
	@Column(name = "template_type")
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
