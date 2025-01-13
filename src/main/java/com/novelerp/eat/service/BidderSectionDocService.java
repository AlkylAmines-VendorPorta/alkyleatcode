/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;
import java.util.Set;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.BidderSectionDoc;

public interface BidderSectionDocService extends CommonService<BidderSectionDoc, BidderSectionDocDto> {

	/**
	 * @param technicalBid
	 * @return
	 */
	public TechnicalBidDto save(TechnicalBidDto technicalBid);

	/**
	 * @param commercialBid
	 * @return
	 */
	public CommercialBidDto save(CommercialBidDto commercialBid);

	/**
	 * @param dto
	 * @return
	 */
	public PriceBidDto save(PriceBidDto dto);

	/**
	 * @param secDocList
	 * @param bidderSecDocSet
	 * @return
	 */
	public Set<BidderSectionDocDto> getBidderSectionDocSet(List<SectionDocumentDto> secDocList,
			Set<BidderSectionDocDto> bidderSecDocSet);

}
