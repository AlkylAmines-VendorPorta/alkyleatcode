/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.PriceBid;

public interface PriceBidDao extends CommonDao<PriceBid, PriceBidDto> {

	/**
	 * @param tahdrId
	 * @return
	 */
	public int createRevisedBid(Long itemBidId, Long priceBidId);
	
	public int updateReqDocsForRevisedBid(Long priceBidId,Long value);

	/**
	 * @param itemBidId
	 * @return
	 */
	public int deleteOldBids(Long itemBidId);

	/**
	 * @param itemBidId
	 * @return
	 */
	public int updateItemPBByParts(Long itemBidId);
	
	public List<Object> getOverAllForwardRankBy(Long tahdrId,Long partnerId);
	
    public List<Object> getOverAllReverseRankBy(Long tahdrId,Long partnerId);
	
	public List<Object> getForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId);
	
	public List<Object> getReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId);
	
public List<Object> getForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId);
	
	public List<Object> getReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId);
	
	public int saveAnnexureC1DigitalSignedFile(Long priceBidId,Long bidderId,Long fileId);
	
	/*public int saveClauseData(Long priceBidId,Long itemBidId ,String clause,String clauseData);*/
	
	public int saveAnnexureC1FileResposne(Long priceBidId,Long fileId);

	public int resetAnnexureC1FileResposne(Long priceBidId);
	
	
	   public List<Object> getQuickReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId);
	
	
	   public List<Object> getQuickForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId,Long partnerId);
	   
	   public List<Object> getQuickReverseRankByMaterial(Long tahdrId,Long tahdrMaterialId);
		
		
	   public List<Object> getQuickForwardRankByMaterial(Long tahdrId,Long tahdrMaterialId);
	   
	   public List<Object> getOverAllQuickForwardRankBy(Long tahdrId,Long partnerId);
	   
	   public List<Object> getOverAllQuickReverseRankBy(Long tahdrId,Long partnerId);
	   
	   public List<Object> getDecendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId);
	   
	   public List<Object> getAscendingQuickBidderStatistic(Long tahdrId,Long tahdrMaterilId);
	   
	   public int createPreviousBid(Long itemBidId,Long priceBidId,String bidRemark);
	   
	   public int createEncryptedBid(Long tahdrId,String bidRemark);
	   
	   public List<Object> getCumulativeSheetOpenedBidderByTahdrId(Long tahdrId);
	   
	   public List<Object> getCumulativeSheetByTahdrId(Long tahdrId);
}
