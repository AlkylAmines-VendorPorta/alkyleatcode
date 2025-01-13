/**
,"getPurchasedTenderDetailByTypeCode" * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.alkyl.dto.BidderFilterDto;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.Bidder;

public interface BidderService extends CommonService<Bidder, BidderDto> {

	public BidderDto createBidder(PaymentDetailDto payment);

	/**
	 * @param id
	 * @return
	 */
	/*public BidderDto getBidderByTahdrDetailId(Long id);*/

	/**
	 * @param partnerId
	 * @param tahdrDetailId
	 * @return
	 */
	BidderDto getBidderTahdrDetail(Long tahdrDetailId);

	/**
	 * @param typeCode
	 * @return
	 */
	List<BidderDto> getBidderTahdrDetailList(String typeCode,String queryMethodName);

	/**
	 * @param tahdrDetailId
	 * @return
	 */
	BidderDto getBidsByBidderQuery(Long tahdrDetailId);
	
	
	public boolean updateStatus(String status,Long bidderId);

	/**
	 * @param bidder
	 * @return
	 *//*
	public BidderDto saveCommercialBid(BidderDto bidder);*/

	/**
	 * @param tahdrDetailId
	 * @param partnerId
	 * @param section
	 * @return
	 *//*
	public BidderDto getCommercialBid(Long tahdrDetailId, Long partnerId, String section);*/

	/**
	 * @param dto
	 * @return
	 */
	/*public BidderDto saveCommercialSectionDocument(BidderDto dto);*/

	public int updateBidderStatusByOpeningType(String status,String oldStatus,Long tahdrId);
	
	public int updateBidderStatusByPBOpeningType(String status,String oldStatus,Long tahdrId);
	
	public List<BidderDto> getBidderAssociatedTenders(TenderCommitteeDto tenderCommitteeDto,BPartnerDto partner);
	
	public boolean updateBidderPreliminaryScrutinyStatus(String newStatus,BidderDto bidderDto);
	
	/*public boolean updateBidderFinalScrutinyStatus(String newStatus,BidderDto bidderDto);*/
	
	public List<String> getBidderMailListByTahdrId(Long tahdrId);
	
	public List<String> getBidderMailListByTahdrId(Long tahdrId,String status);
	
	public boolean sendMailToAllBidder(List<String> emailList,String sub,String message);
	
	public boolean sendMailBidder(String email,String sub,String message);

	/**
	 * @param bidder
	 * @return
	 *//*
	public BidderDto submitBidder(BidderDto bidder);*/
	
	public CustomResponseDto checkDeviationSchedule(Long tahdrDetailId);
	
	public List<BidderDto> getBidderDeviationTahdrDetailList(String typeCode);
	
	public boolean updateBidderStatusForC1Called(String status,Long tahdrId);

	int updateBidderRating(BidderDto bidderDto, double newRating);

	/**
	 * @param payment
	 * @return
	 */
	public BidderDto purchaseTender(PaymentDetailDto payment);
	
	public boolean updateBidderStatusWithSelectedStatus(String status,String oldStatus,Long bidderId);
	
	boolean updateBidderStatusForNoCommercialDeviation(String status,Long bidderId);
	
	public boolean updateStatusWithItemBidStatus(String status,String itemBidStatus,Long bidderId);
	
	public boolean sendCustomMailToAllBidder(Map<String,StringBuilder> mails,String sub);
	
	public long getAwardWinnerCount(Long  tahdrId);
	
	public String getBidderUpdatedScrutinyStatus(Long bidderId,String scrutinyLevel);
	
	public Map<Long, String> getBidderFinalStatus(Long tahdrId,String scrutinyLevel);
	
	public boolean setBidderStatus(Map<Long, String> bidderStatus);
	
	public List<BidderDto> getBidderListForSubmissionReminderMail(Long tahdrId, String status);
	
	public void mailNotificationForReSubmission(Long tahdrDetailId);
	
	public void mailNotificationForReSubmissionOfBid(UserDto bidder,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser);

	
	/*public void mailNotificationForBidSubmitted(BidderDto bidder,TAHDRDto tahdrDto);*/


	/**
	 * @param payment
	 * @return
	 */
	public BidderDto createBidderForOP(PaymentDetailDto payment);
	
	public int updateBidderStatusBySBPBOpeningType(String status,String oldStatus,Long tahdrId);
	
	public boolean updateQuickBidderForWinner(Long tahdrId);

	public BidderDto updateBidderTotalBid(BidderDto bidder);

	public List<BidderDto> updateTotalSplitValue(List<BidderDto> bidderList);

	public List<BidderDto> getBidderByFilter(BidderFilterDto dto);;
}
