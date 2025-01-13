package com.novelerp.appbase.master.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.MailTemplateDao;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.entity.MailTemplate;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/** 
 * @author Ankita
 *
 */
@Service
public class MailTemplateServiceImpl extends AbstractContextServiceImpl<MailTemplate, MailTemplateDto> implements MailTemplateService {
	
	@Autowired
	private MailTemplateDao mailTemplateDao;
	
	@PostConstruct
	private void init() {
		super.init(MailTemplateServiceImpl.class, mailTemplateDao, MailTemplate.class, MailTemplateDto.class);
		setByPassProxy(true);
	}
	
	@Override
	public MailTemplateDto getMailTemplateFromTemplateType(String templateType) {
		Map<String, Object> map = new HashMap<>();
		map.put("templateType", templateType);
		String queryString = mailTemplateDao.getMailTemplate();
		MailTemplateDto mailData = findDtoByQuery(queryString, map);
		return mailData;
	}
}
