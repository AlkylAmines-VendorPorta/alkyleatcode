/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.SectionDocumentDao;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.SectionDocument;
import com.novelerp.eat.service.SectionDocumentService;

@Service
public class SectionDocumentServiceImpl extends AbstractContextServiceImpl<SectionDocument, SectionDocumentDto>
		implements SectionDocumentService {

	@Autowired
	private SectionDocumentDao sectionDocumentDao;
	
	@PostConstruct
	void init(){
		super.init(SectionDocumentServiceImpl.class, sectionDocumentDao, SectionDocument.class, SectionDocumentDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Set<SectionDocumentDto> save(Set<SectionDocumentDto> secDocSet){
		Set<SectionDocumentDto> secDocDtoSet= new HashSet<>();
		for(SectionDocumentDto secDoc: secDocSet){
			if(null==secDoc.getSectionDocumentId() || !(secDoc.getSectionDocumentId()>0)){
				secDoc=super.save(secDoc);
			}else{
				secDoc=super.updateDto(secDoc);
			}
			secDocDtoSet.add(secDoc);
		}
		return secDocDtoSet;
	}
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public SectionDocumentDto save(SectionDocumentDto dto) {
		if(null!=dto){
			if(null==dto.getSectionDocumentId() || !(dto.getSectionDocumentId()>0))
				dto=super.save(dto);
			else
				dto=super.updateDto(dto);
		}
		return dto;
	}
	
	@Override
	public Set<SectionDocumentDto> getSectionDoc(Long tahdrDetailId){
		Set<SectionDocumentDto> secDocSet= new HashSet<>();
		Map<String, Object> map= new HashMap<>();
		map.put("tahdrDetailId", tahdrDetailId);
		secDocSet.addAll(findDtos("getRequiredDocByDetailId", map));
		return secDocSet;
	}

	/* (non-Javadoc)
	 * @see com.novelerp.eat.service.SectionDocumentService#copySectionDocsToNewVersion(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int copySectionDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId) {
		
		return sectionDocumentDao.copySectionDocsToNewVersion(newTahdrDetailId, oldTahdrDetailId);
	}
	
	@Override
	@Transactional
	public boolean deleteSectionDoc(Long id) {
		// TODO Auto-generated method stub
		return deleteById(id);
	}
}
