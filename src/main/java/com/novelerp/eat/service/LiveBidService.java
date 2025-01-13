package com.novelerp.eat.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.OtpDto;
import com.novelerp.eat.dto.PriceBidDto;

public interface LiveBidService {
	
	public CustomResponseDto getMyAuctionForLiveBid(Long tahdrId);
	
	public CustomResponseDto getLiveAuctions(String typeCode);
	
	public CustomResponseDto getOverAllRank(Long tahdrId);
	
	public CustomResponseDto getTAHDRMaterialList(Long tahdrId);
	
	public CustomResponseDto getBidderForLiveBidByTahdrId(Long tahdrId,Long tahdrMaterialId);
	
	public CustomResponseDto getBidderListByTahdrId(Long tahdrId,Long tahdrMaterialId);
	
	public CustomResponseDto getBidListByTahdrId(Long tahdrId,Long tahdrMaterialId);
	
	public CustomResponseDto sendOtp(Long tahdrId,String tahdrName, HttpServletRequest request);
	
	public CustomResponseDto resendOtp(Long tahdrId,String tahdrName, HttpServletRequest request);
	
	public CustomResponseDto validateOtp(OtpDto otp, HttpServletRequest request);
	
	public CustomResponseDto saveNewBid(PriceBidDto newBid,HttpServletRequest request);
	
	public CustomResponseDto autoRefreshData(Long tahdrId,Long tahdrMaterialId);
	
	public CustomResponseDto getQuickTAHDRMaterialList(Long tahdrId);
	
	public String calculateRemainingTime(Date bidEndDate);
/**
	 * @param tahdrMaterialId
	 * @param bidderId
	 * @return
	 */
	public List<PriceBidDto> getBidListByBidderId(Long tahdrMaterialId, Long bidderId);

	/**
	 * @param tahdrMaterialId
	 * @return
	 */
	public List<PriceBidDto> getCompleteBidListTahdrMaterialId(Long tahdrMaterialId);
	
	public CustomResponseDto saveQuickAuctionWinner(Long tahdrId);
}
