package com.novelerp.eat.service;

import com.novelerp.appbase.master.dto.SessionKeyDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;

public interface TenderOpeningService {
	
	public  CustomResponseDto getOpeningTenderList();
	
	public  CustomResponseDto getOpeningMyTenderList(Long tahdrId);
	
	public  CustomResponseDto searchOpeningTenderList(Long tahdrId);
	
	public  CustomResponseDto searchOpeningTenderList(TenderCommitteeDto tenderCommitteeDto);
	
	public  CustomResponseDto checkSessionKey(SessionKeyDto sessionKeyDto);
	
	public  CustomResponseDto vendorSessionKey(Long tahdrId);
	
	public  CustomResponseDto tenderLogout(Long tahdrId);
	
	public  CustomResponseDto openingTenderByOpeningType(TAHDRDto tAHDRDto);
	
	public  CustomResponseDto viewTenderByOpeningType(Long tahdrId);
	
	public  CustomResponseDto openAllBid(Long tahdrId,Long tahdrMaterialId);
	
	public  CustomResponseDto openAllBid(Long tahdrId);
	
	public  CustomResponseDto getLoggedInAuditorList(Long tahdrId);
	
	public  CustomResponseDto getLoggedInBidderList(Long tahdrId);
	
	public  CustomResponseDto getTAHDRMaterialList(Long tahdrId);
	
	public  CustomResponseDto getBidderListByTahdrId(Long tahdrId);
	
	public  CustomResponseDto checkTenderOpened(Long tahdrId);

	/**
	 * @param bidderId
	 * @param tahdrMaterialId
	 * @param status
	 * @param bidType
	 * @return
	 */
	public CustomResponseDto getOpenedBids(Long bidderId, Long tahdrMaterialId, String status, String bidType);
	
	
	

}
