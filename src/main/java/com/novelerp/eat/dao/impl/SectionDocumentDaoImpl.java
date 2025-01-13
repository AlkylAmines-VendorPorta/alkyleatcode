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
import com.novelerp.eat.dao.SectionDocumentDao;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.SectionDocument;

@Repository
public class SectionDocumentDaoImpl extends AbstractJpaDAO<SectionDocument, SectionDocumentDto>
		implements SectionDocumentDao {

	@PostConstruct
	void init(){
		setClazz(SectionDocument.class, SectionDocumentDto.class);
	}
	
	@Override
	public String getRequiredDocByDetailId(){
		StringBuilder sb= new StringBuilder();
		sb.append(" Select e from SectionDocument e ");
		sb.append(" LEFT JOIN FETCH e.tahdrMaterial tm ");
		sb.append(" LEFT JOIN FETCH tm.material m ");
		sb.append(" Where e.tahdrDetail.tahdrDetailId=:tahdrDetailId ORDER BY e.code ASC,m.name ASC ");
		return sb.toString();
	}
	
	@Override
	public String getRequiredDocBySection(){
		StringBuilder sb= new StringBuilder();
		sb.append(" Select e from SectionDocument e ")
		.append(" INNER JOIN e.tahdrDetail td ")
		.append(" INNER JOIN td.tahdr t ")
		.append(" Where t.tahdrId=:tahdrId and td.isActive='Y' and e.code=:sectionCode ");
		return sb.toString();
	}
	
	private String copySectionDocsQuery(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" Insert into T_SECTION_DOCUMENT (T_SECTION_DOCUMENT_ID,CREATED,ISACTIVE,UPDATED, "
				+ " VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,T_TAHDR_DETAIL_ID, "
				+ " T_TAHDR_MATERIAL_ID) ");
		jpql.append( " SELECT T_SECTION_DOCUMENT_SEQ.NEXTVAL,CREATED,ISACTIVE, "
				+ " UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,?, "
				+ " T_TAHDR_MATERIAL_ID from T_SECTION_DOCUMENT " );
		jpql.append(" WHERE t_tahdr_detail_id=? ");
		return jpql.toString();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copySectionDocsToNewVersion(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=copySectionDocsQuery();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, newTahdrDetailId);
		query.setParameter(2, oldTahdrDetailId);
		int count= query.executeUpdate();
		updateTahdrMaterialIdOfCopiedRecords(newTahdrDetailId,oldTahdrDetailId);
		return count;
	}
	

	public String getSectionDocByCodeAndTahdrMaterialId(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select sd from SectionDocument sd")
		.append(" INNER JOIN FETCH sd.tahdrMaterial tm ")
		.append(" WHERE tm.tahdrMaterialId=:tahdrMaterialId ")
		.append(" AND sd.code=:sdCode ");
		return jpql.toString();
	}
	
	private String queryToUpdateNewRecords(){
		StringBuilder jpql=new StringBuilder();
		jpql.append(" UPDATE T_SECTION_DOCUMENT TSD ")
		.append(" SET TSD.T_TAHDR_MATERIAL_ID=")
		.append(" ( ")
		.append(" SELECT T_TAHDR_MATERIAL_ID FROM T_TAHDR_MATERIAL TTM ")
        .append(" WHERE TTM.T_MATERIAL_ID= ")
        .append(" ( ")
        .append(" SELECT T_MATERIAL_ID FROM T_TAHDR_MATERIAL TTM2  ")
        .append(" WHERE TTM2.T_TAHDR_MATERIAL_ID=TSD.T_TAHDR_MATERIAL_ID AND TTM2.T_TAHDR_DETAIL_ID=? ")
        .append(" ) ")
        .append(" AND TTM.T_TAHDR_DETAIL_ID=? ")
        .append(" ) ")
        .append(" WHERE TSD.T_TAHDR_DETAIL_ID=? ") ;
		return jpql.toString();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateTahdrMaterialIdOfCopiedRecords(Long newTahdrDetailId,Long oldTahdrDetailId){
		String jpql=queryToUpdateNewRecords();
		Query query = getEntityManager().createNativeQuery(jpql);
		query.setParameter(1, oldTahdrDetailId);
		query.setParameter(2, newTahdrDetailId);
		query.setParameter(3, newTahdrDetailId);
		int count= query.executeUpdate();
		return count;
	}
	
}
