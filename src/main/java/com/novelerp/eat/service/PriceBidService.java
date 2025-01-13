/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.core.dto.BidStatisticDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.GraphDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderSectionDocDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.PriceBid;

public interface PriceBidService extends CommonService<PriceBid, PriceBidDto> {

	/**
	 * @param dto
	 * @return
	 */
	public PriceBidDto submit(PriceBidDto dto,boolean doEcrypt);
	
	public int createRevisedBid(Long itemBidId, Long priceBidId);
	
	public int updateReqDocsForRevisedBid(Long priceBidId,Long value);

	/**
	 * @param itemBidId
	 * @return
	 */
	public int deleteOldBids(Long itemBidId);

	/**
	 * @param tahdrMaterialId
	 * @param getbPartnerId
	 * @return
	 */
	public PriceBidDto getPriceBid(Long tahdrMaterialId, Long getbPartnerId);

	/**
	 * @param tahdrMaterialId
	 * @param partnerId
	 * @return
	 */
	public List<BidderSectionDocDto> getPriceBidSectionDoc(Long tahdrMaterialId, Long partnerId);

	/**
	 * @param tahdrMaterialId
	 * @param getbPartnerId
	 * @return
	 */
	public List<PriceBidDto> getPriceBidForParts(Long tahdrMaterialId, Long getbPartnerId);

	/**
	 * @param priceBid
	 * @return
	 */
	public PriceBidDto decryptPB(PriceBidDto priceBid);

	/**
	 * @param dto
	 * @return
	 */
	public PriceBidDto encryptPB(PriceBidDto dto);

	/**
	 * @param pbList
	 * @return
	 */
	public List<PriceBidDto> encryptPBList(List<PriceBidDto> pbList);

	/**
	 * @param pbList
	 * @return
	 */
	public List<PriceBidDto> decryptPBList(List<PriceBidDto> pbList);

	/**
	 * @param pbNew
	 * @param part
	 * @return
	 */
	public PriceBidDto mergePart(PriceBidDto pbNew, PriceBidDto part);

	/**
	 * @param tahdrMaterialId
	 * @param partId
	 * @return
	 */
	public PriceBidDto mergeUpdatedParts(Long tahdrMaterialId, Long partId);

	/**
	 * @param pbList
	 * @return
	 */
	public boolean decryptAllPb(List<PriceBidDto> pbList);

	public PriceBidDto calculate(PriceBidDto dto);
	
	public PriceBidDto saveLiveBid(PriceBidDto dto) ;
	
	public int getRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId,String typeCode);
	
	public int getOverAllRankBy(Long tahdrId,Long partnerId,String typeCode);

	/**
	 * @param pbList
	 * @return
	 */
	public boolean encryptAllPb(List<PriceBidDto> pbList);

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

/*	public boolean saveClauseData(Long priceBidId,Long itemBidId ,String clause,String clauseData);*/
	
	public boolean saveAnnexureC1FileResposne(PriceBidDto dto);
	
	public boolean resetAnnexureC1FileResposne(PriceBidDto dto);

	/**
	 * @param pb
	 * @return
	 * @throws IOException 
	 */
	public boolean decryptPriceBidDoc(PriceBidDto pb) throws IOException;

	/**
	 * @param pbList
	 * @return
	 * @throws IOException
	 */
	public boolean decryptPriceBidDoc(List<PriceBidDto> pbList) throws IOException;

	public long updateStatusForPriceBid(String bidSection,Long bidderId,Long itemBidId);

	public List<GraphDto> setGraphDtoDetails(List<PriceBidDto> priceHistoryList);
	
	public List<Object> getDecendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId);
	   
	public List<Object> getAscendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId);
	
	public List<BidStatisticDto> setBidStatisticGraphDtoDetails(List<PriceBidDto> priceHistoryList) ;
	
	public List<Object> getRankOfAllBidderByMaterial(Long tahdrId,Long tahdrMaterialId,String typeCode);
	
	public List<PriceBidDto> getBidListBySessionId(String userSessionId);
	
	public int createPreviousBid(Long itemBidId,Long priceBidId,String bidRemark);
	
	public boolean saveEncryptedBidBeforeOpening(Long tahdrId,String bidRemark);
	
	public Object getCumulativeSheetData(Long tahdrId,boolean opened);

	public QuotationDto saveQuaotation(QuotationDto quotation);

	public CustomResponseDto updateDeliverDate(Long itembidId, Date date1, Errors errors);

	/*public List<PriceBidDto> submitQuotation(List<PriceBidDto> quotations, Long bidderId);*/
}
