/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.StandardCustomDocDao;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.entity.StandardCustomDoc;
@Repository
public class StandardCustomDocDaoImpl extends AbstractJpaDAO<StandardCustomDoc, StandardCustomDocDto>
		implements StandardCustomDocDao {

	@PostConstruct
	void init(){
		setClazz(StandardCustomDoc.class, StandardCustomDocDto.class);
	}
	
	/**
	 * 
	 * @param tahdrId
	 * @return query for get attachment data 
	 */
	@Override
	public String getQueryForDocs(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT e FROM StandardCustomDoc e ")
		.append(" LEFT JOIN FETCH e.tahdrDetail td ")
		.append(" LEFT JOIN FETCH e.attachment a" )
		.append(" WHERE td.isActive='Y' AND td.tahdr.tahdrId = :tahdrId")
		.append(" ORDER BY e.index");
		
		return query.toString();		
	}
	
	private String copyStdCustDocsQuery(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Insert into T_STANDARD_CUSTOM_DOC (T_STANDARD_CUSTOM_DOC_ID,CREATED,ISACTIVE,"
				+ " UPDATED,VALUE,DESCRIPTION,RANK,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,"
				+ " M_ATTACHMENT_ID,T_TAHDR_DETAIL_ID) ");
		jpql.append( " SELECT T_STANDARD_CUSTOM_DOC_SEQ.NEXTVAL,CREATED,ISACTIVE,"
				+ " UPDATED,VALUE,DESCRIPTION,RANK,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,"
				+ " M_ATTACHMENT_ID,? from T_STANDARD_CUSTOM_DOC " );
		jpql.append(" WHERE t_tahdr_detail_id=? ");
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copyStdCustDocsToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=copyStdCustDocsQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, newTahdrDetailId);
		query.setParameter(2, oldTahdrDetailId);
		int count= query.executeUpdate();
		return count;
	}
}
