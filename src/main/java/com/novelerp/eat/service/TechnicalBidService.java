/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;

public interface TechnicalBidService extends CommonService<TechnicalBid, TechnicalBidDto> {

	/**
	 * @param dto
	 * @return
	 */
	public TechnicalBidDto submit(TechnicalBidDto dto);
	
	public boolean updateTechnicalBidStatus(String status,Long itemBidId);

	/**
	 * @param tahdrMaterialId
	 * @param partnerId
	 * @return
	 */
	public List<BidderGtpDto> getTechnicalBidBidderGtp(Long tahdrMaterialId, Long partnerId);

	/**
	 * @param tahdrMaterialId
	 * @param partnerId
	 * @return
	 */
	public List<BidderSectionDocDto> getTechnicalBidSectionDoc(Long tahdrMaterialId, Long partnerId);

	/**
	 * @param tahdrMaterialId
	 * @param getbPartnerId
	 * @return
	 */
	public TechnicalBidDto getTechnicalBid(Long tahdrMaterialId, Long partnerId);

	/**
	 * @param status
	 * @param id
	 * @param digiSignDocId
	 * @return
	 */
	public int updateStatus(String status, Long id, Long digiSignDocId);

	/**
	 * @param status
	 * @param itemBidId
	 * @param digiSignDocId
	 * @return
	 */
	public int updateStatusByItemBidId(String status, Long itemBidId, Long digiSignDocId);
	public long updateStatusForTechnicalBid(String bidSection,Long bidderId,Long itemBidId);

}
