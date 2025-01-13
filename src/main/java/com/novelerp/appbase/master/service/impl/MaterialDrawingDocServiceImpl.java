package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.MaterialDrawingDocDao;
import com.novelerp.appbase.master.dto.MaterialDrawingDocumentDto;
import com.novelerp.appbase.master.entity.MaterialDrawingDocument;
import com.novelerp.appbase.master.service.MaterialDrawingDocService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/**
 * 
 * @author Varsha Patil
 *
 */
@Service
public class MaterialDrawingDocServiceImpl extends AbstractContextServiceImpl<MaterialDrawingDocument, MaterialDrawingDocumentDto>implements MaterialDrawingDocService{
    @Autowired
	private MaterialDrawingDocDao materialDrawingDocDao;
	@PostConstruct
	private void init() {
		super.init(MaterialDrawingDocServiceImpl.class, materialDrawingDocDao, MaterialDrawingDocument.class, MaterialDrawingDocumentDto.class);
		setByPassProxy(true);
	}
}
