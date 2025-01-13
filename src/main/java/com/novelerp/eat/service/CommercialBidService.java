/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.Set;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.entity.CommercialBid;

public interface CommercialBidService extends CommonService<CommercialBid, com.novelerp.eat.dto.CommercialBidDto> {

	/**
	 * @param status
	 * @param id
	 * @param digiSignDocId
	 * @return
	 */
	public int updateStatus(String status, Long id, Long digiSignDocId);

	/**
	 * @param status
	 * @param bidderId
	 * @param digiSignDocId
	 * @return
	 */
	public int updateStatusByBidderId(String status, Long bidderId, Long digiSignDocId);

	/**
	 * @param tahdrId
	 * @param getbPartnerId
	 * @param commercialSection
	 * @return
	 */
	public CommercialBidDto getCommercialBid(Long tahdrId, Long getbPartnerId);

	public Set<BidderSectionDocDto> getCommercialBidDocs(Long tahdrId,String section, Long partnerId);

	/**
	 * @param newDto
	 * @param oldDto
	 * @return
	 */
	public CommercialBidDto copyNewDtoToOld(CommercialBidDto newDto, CommercialBidDto oldDto);

	/**
	 * @param cb
	 * @return
	 */
	public CommercialBidDto submitCommercailBid(CommercialBidDto cb);
	public long updateStatusForCommercialBid(String bidSection,Long bidderId);
	
	public CommercialBidDto getCommercialBidByBidderId(Long bidderId);
}
