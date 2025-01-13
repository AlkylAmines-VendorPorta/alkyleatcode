package com.novelerp.core.dto;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Vivek Birdi
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DigitalSignatureInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2309805305089770460L;
	
	private DigitalRDNInfo issuer;
	private DigitalRDNInfo subject;
	private Date notBefore;
	private Date notAfter;
	private boolean validToday;
	private X509Certificate x509cert;
	public DigitalRDNInfo getIssuer() {
		return issuer;
	}
	public void setIssuer(DigitalRDNInfo issuer) {
		this.issuer = issuer;
	}
	public DigitalRDNInfo getSubject() {
		return subject;
	}
	public void setSubject(DigitalRDNInfo subject) {
		this.subject = subject;
	}
	public Date getNotBefore() {
		return notBefore;
	}
	public void setNotBefore(Date notBefore) {
		this.notBefore = notBefore;
	}
	public Date getNotAfter() {
		return notAfter;
	}
	public void setNotAfter(Date notAfter) {
		this.notAfter = notAfter;
	}
	public boolean isValidToday() {
		return validToday;
	}
	public void setValidToday(boolean validToday) {
		this.validToday = validToday;
	}
	public X509Certificate getX509cert() {
		return x509cert;
	}
	public void setX509cert(X509Certificate x509cert) {
		this.x509cert = x509cert;
	}
	
}
