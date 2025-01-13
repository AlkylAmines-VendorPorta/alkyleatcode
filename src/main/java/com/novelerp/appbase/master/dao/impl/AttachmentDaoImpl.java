package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.AttachmentDao;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AttachmentDaoImpl extends AbstractJpaDAO<Attachment, AttachmentDto> implements AttachmentDao{

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		setClazz(Attachment.class,AttachmentDto.class);
		}
	
	public String getAttachmentQuery(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select a from Attachment a ")
		.append(" LEFT JOIN FETCH a.partner p");
		return jpql.toString();
	}
	public String getAttachmentByIdQuery(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select att from Attachment att ")
		.append(" LEFT JOIN FETCH att.partner p")
		.append(" WHERE att.attachmentId = :AttachmentId");
		return jpql.toString();
	}

	@Override
	public String getAttachmentDateWiseQuery() {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select att from Attachment att ")
		.append(" LEFT JOIN FETCH att.partner p")
		.append(" WHERE att.created BETWEEN TO_DATE(:StartDate,'dd-mm-yyyy HH24:MI:SS') AND TO_DATE(:EndADte,'dd-mm-yyyy HH24:MI:SS')");
		return jpql.toString();
	}
	
	public String getAttachmentByAttId(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select att from Attachment att ")
		.append(" LEFT JOIN FETCH att.partner p")
		.append(" WHERE att.attachmentId = :attachmentId");
		return jpql.toString();
	}
	
	public String getAttachmentByName(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select att from Attachment att ")
		.append(" LEFT JOIN FETCH att.partner p")
		.append(" WHERE att.fileName = :fileName");
		return jpql.toString();
	}
	
}
