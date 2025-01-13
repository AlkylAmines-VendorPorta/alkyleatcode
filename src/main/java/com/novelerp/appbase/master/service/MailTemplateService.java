package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.entity.MailTemplate;
import com.novelerp.core.service.CommonService;

/**
 *  @author Ankita
  */
public interface MailTemplateService extends CommonService<MailTemplate, MailTemplateDto>{

	public MailTemplateDto getMailTemplateFromTemplateType(String templateType);
}
