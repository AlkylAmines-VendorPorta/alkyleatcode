/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.entity.StandardCustomDoc;

public interface StandardCustomDocDao extends CommonDao<StandardCustomDoc, StandardCustomDocDto> {

	/**
	 * 
	 * @author Vivek Birdi
	 * @return query for get attachment data 
	 */
	public String getQueryForDocs();

	/**
	 * @param newTahdrDetailId
	 * @param oldTahdrDetailId
	 * @return
	 */
	public int copyStdCustDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);
}
