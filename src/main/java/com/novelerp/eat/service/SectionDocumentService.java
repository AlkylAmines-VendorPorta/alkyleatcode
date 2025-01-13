/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.Set;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.SectionDocument;

public interface SectionDocumentService extends CommonService<SectionDocument, SectionDocumentDto> {

	/**
	 * @param secDocSet
	 * @return
	 */
	public Set<SectionDocumentDto> save(Set<SectionDocumentDto> secDocSet);


	/**
	 * @param tahdrDetailId
	 * @return
	 */
	public Set<SectionDocumentDto> getSectionDoc(Long tahdrDetailId);

	public int copySectionDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);
	
	/**
	 * @param id
	 * @return
	 */
	public boolean deleteSectionDoc(Long id);
}
