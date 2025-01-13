package com.novelerp.core.dto;

import java.util.List;
import java.util.Map;

/**@author GOREAK-CONT*/

public class FileVerificationDto {
	
	private boolean verifySignedFile;
	private List<String> message;
	private Map<String, String> signatureattr;
	
	public FileVerificationDto(boolean verifySignedFile){
		this.verifySignedFile = verifySignedFile;
	}
	
	public FileVerificationDto() {
	}

	public boolean isVerifySignedFile() {
		return verifySignedFile;
	}
	public void setVerifySignedFile(boolean verifySignedFile) {
		this.verifySignedFile = verifySignedFile;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	public Map<String, String> getSignatureattr() {
		return signatureattr;
	}
	public void setSignatureattr(Map<String, String> signatureattr) {
		this.signatureattr = signatureattr;
	}
}
