/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface MyTenderService {

	public List<ReferenceListDto> getMyTenderLinks(String role);

	/**
	 * @param action
	 * @return
	 */
	public boolean validateAccessAction(String action);

	/**
	 * @param tenderTypeCode
	 * @return
	 */
	public List<TAHDRDto> getTahdrByTypeCode(String tenderTypeCode,Long partnerId,int pageNumber,int pageSize,String searchMode,String searchValue);
	
	public List<TAHDRDto> getAuctionByTypeCode(String auctionTypeCode,Long partnerId,int pageNumber,int pageSize,String searchMode,String searchValue);
	public long getAuctionByTypeCount(String tenderTypeCode,Long partnerId,String searchColumn, String searchValue);
	public long getTahdrByTypeCodeCount(String tenderTypeCode,Long partnerId,String searchColumn, String searchValue);
}
