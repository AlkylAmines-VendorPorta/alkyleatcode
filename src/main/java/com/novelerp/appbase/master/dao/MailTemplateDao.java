package com.novelerp.appbase.master.dao;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.entity.MailTemplate;
import com.novelerp.core.dao.CommonDao;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
public interface MailTemplateDao extends CommonDao<MailTemplate, MailTemplateDto> {

	public String getMailTemplate();
}
