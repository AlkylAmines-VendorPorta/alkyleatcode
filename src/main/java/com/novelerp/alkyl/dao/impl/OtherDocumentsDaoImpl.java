package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.OtherDocumentsDao;
import com.novelerp.alkyl.dto.OtherDocumentsDto;
import com.novelerp.alkyl.entity.OtherDocuments;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class OtherDocumentsDaoImpl  extends AbstractJpaDAO<OtherDocuments, OtherDocumentsDto> implements OtherDocumentsDao{

	@PostConstruct
	public void init(){
		
		setClazz(OtherDocuments.class, OtherDocumentsDto.class);
	}
	
	public String getOtherDocumentsByKYCId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM OtherDocuments A ")
				.append(" INNER JOIN A.kycDetails B ")
				.append(" INNER JOIN FETCH A.attachmentdetails C ")
				.append(" WHERE B.kycId=:kycId and A.isActive='Y' ");
		return jpql.toString();
	}
}
