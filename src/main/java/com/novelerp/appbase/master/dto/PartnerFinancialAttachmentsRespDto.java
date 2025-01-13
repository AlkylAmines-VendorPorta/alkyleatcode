package com.novelerp.appbase.master.dto;
/**
 * 
 * @author varsha
 * 
 */

import java.util.List;

public class PartnerFinancialAttachmentsRespDto {
	
   private List<PartnerFinancialAttachmentDto> financialAttachments;

	public List<PartnerFinancialAttachmentDto> getFinancialAttachments() {
		return financialAttachments;
	}
	
	public void setFinancialAttachments(List<PartnerFinancialAttachmentDto> financialAttachments) {
		this.financialAttachments = financialAttachments;
	}
   
}
