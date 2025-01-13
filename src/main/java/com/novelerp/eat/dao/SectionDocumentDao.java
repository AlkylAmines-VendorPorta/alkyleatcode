/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.entity.SectionDocument;

public interface SectionDocumentDao extends CommonDao<SectionDocument, SectionDocumentDto> {

	/**
	 * @return
	 */
	public String getRequiredDocByDetailId();

	/**
	 * @return
	 */
	public String getRequiredDocBySection();

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int copySectionDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int updateTahdrMaterialIdOfCopiedRecords(Long newTahdrDetailId, Long oldTahdrDetailId);

}
