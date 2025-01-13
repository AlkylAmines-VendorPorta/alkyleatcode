package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.entity.PartnerFinancialAttachment;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author varsha
 *
 */
public interface PartnerFinancialDetailsService extends CommonService<PartnerFinancialAttachment, PartnerFinancialAttachmentDto>{
    public List<PartnerFinancialAttachmentDto> processFinancialAttachmentRequest(List<PartnerFinancialAttachmentDto> dtos);
    public PartnerFinancialAttachmentDto saveFinancialAttachment(PartnerFinancialAttachmentDto dto);
}
