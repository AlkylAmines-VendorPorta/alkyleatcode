package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.MaterialDrawingDocDao;
import com.novelerp.appbase.master.dto.MaterialDrawingDocumentDto;
import com.novelerp.appbase.master.entity.MaterialDrawingDocument;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
public class MaterialDrawingDocDaoImpl extends AbstractJpaDAO<MaterialDrawingDocument, MaterialDrawingDocumentDto> implements MaterialDrawingDocDao{
	@PostConstruct
	private void init() {
		setClazz(MaterialDrawingDocument.class,MaterialDrawingDocumentDto.class);
	}
	
	public String getMaterialDocumentByMaterialId(){
		StringBuilder jpql=new StringBuilder("select md from MaterialDrawingDocument md ")
		.append(" INNER JOIN FETCH md.material m ")
	    .append(" where md.material.materialId=:materialId ");
		return jpql.toString();
	}
}
