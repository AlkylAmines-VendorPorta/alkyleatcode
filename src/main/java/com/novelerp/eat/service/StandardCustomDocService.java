/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.StandardCustomDocDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.StandardCustomDoc;

public interface StandardCustomDocService extends CommonService<StandardCustomDoc, StandardCustomDocDto> {

	/**
	 * @param tahdrDetail
	 * @return
	 */
	public TAHDRDetailDto save(TAHDRDetailDto tahdrDetail,TAHDRDetailDto tahdrDetailDto);

	/**
	 * @author Vivek Birdi
	 * @param tahdrId
	 * @return
	 */
	public List<StandardCustomDocDto> getDocs(Long tahdrId);
	
	public int copyStdCustDocsToNewVersion(Long newTahdrDetailId, Long oldTahdrDetailId);
}
