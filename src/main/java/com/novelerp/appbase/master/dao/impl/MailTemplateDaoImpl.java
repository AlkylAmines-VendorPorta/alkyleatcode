package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.MailTemplateDao;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.entity.MailTemplate;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * @author Ankita
 *
 */
@Repository
public class MailTemplateDaoImpl extends AbstractJpaDAO<MailTemplate, MailTemplateDto> implements MailTemplateDao {
private static final String ENTITY_NAME="M_MAIL_TEMPLATE";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(MailTemplate.class, MailTemplateDto.class);
	}
	
	@Override
	public String getMailTemplate()
	{
		StringBuilder jpql =  new StringBuilder(" Select mt from MailTemplate mt ");
		jpql.append(" WHERE mt.templateType =:templateType ");
		return jpql.toString();
	}

}
