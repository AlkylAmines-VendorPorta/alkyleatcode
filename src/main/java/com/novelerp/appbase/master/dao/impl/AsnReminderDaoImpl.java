package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.AsnReminderDao;
import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.AsnReminder;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AsnReminderDaoImpl extends AbstractJpaDAO<AsnReminder, AsnReminderDto> implements AsnReminderDao {

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		setClazz(AsnReminder.class, AsnReminderDto.class);
	}
	
	public String getAsnReminders(){		
		StringBuilder jpql = new StringBuilder()
			.append(" Select A from AsnReminder A ")
			.append(" INNER JOIN FETCH A.createdBy B ")	
			.append(" INNER JOIN FETCH B.userDetails E ")
			.append(" LEFT JOIN FETCH A.vendor D ")
			.append(" INNER JOIN FETCH A.updatedBy C " );
		
		return jpql.toString();
	}
	
	public String getQueryForReminderList() {
		StringBuilder query = new StringBuilder("select c from AsnReminder c ");
		query.append(" INNER JOIN FETCH c.partner bp");
		query.append(" Where c.poNo IN (:listOfPoNo)");
		return query.toString();
	}

}
