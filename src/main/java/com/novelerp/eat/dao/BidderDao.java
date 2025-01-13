/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.alkyl.dto.BidderFilterDto;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.Bidder;

public interface BidderDao extends CommonDao<Bidder, BidderDto> {

	public int updatBidderStatus(String status,Long bidderId,String clauseStatus);
	
	public int updateBidderStatusByOpeningType(String status,String oldStatus,Long tahdrId);
	
	public int updateBidderStatusByPBOpeningType(String status,String oldStatus,Long tahdrId);
	
	public String getBidderAssociatedTendersQuery(TenderCommitteeDto tenderCommitteeDto);
	
	public int updateBidderStatusForC1Called(String status,Long tahdrId);

	/**
	 * @param bidder
	 * @return
	 */
	public int submitBidder(BidderDto bidder);
	
	public int updateBidderStatusWithSelectedStatus(String status,String oldStatus,Long bidderId);
	
	public int updateBidderStatusForNoCommercialDeviation(String status,Long bidderId);
	
	public int updateStatusWithItemBidStatus(String status,String itemBidStatus,Long bidderId);
	public long getAwardWinnerCount(Long tahdrId);
	public String getTahdrListOfSubmissionForReminderMail(String status);
	public String getBidderDataFromBidderId();
	
	public int updateBidderStatusBySBPBOpeningType(String status,String oldStatus,Long tahdrId);
	
	public int updateQuickBidderForWinner(Long tahdrId);

	public String getBidderFilterQuery(BidderFilterDto dto);

	//public String getNewrfqNo();
	
}
