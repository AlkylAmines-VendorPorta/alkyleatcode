package com.novelerp.eat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRSearchDto;
import com.novelerp.eat.entity.TAHDRDetail;

public interface TAHDRDetailService extends CommonService<TAHDRDetail, TAHDRDetailDto> {

	public TAHDRDetailDto saveTAHDRDetail(TAHDRDetailDto dto);
	public List<TAHDRDetailDto> getTAHDRList(TAHDRSearchDto tahdrSearchDto);
	/*public List<TAHDRDetailDto> getTenderList(String typeCode, BPartnerDto partnerDto);*/
	/**
	 * @param dto
	 * @param tahdrDetailDto
	 * @return
	 */
	public TAHDRDetailDto saveTAHDRDates(TAHDRDetailDto dto, TAHDRDetailDto tahdrDetailDto);
	public TAHDRDetailDto saveTAHDRDates(TAHDRDetailDto tahdrDetailDto);
	/**
	 * @param tahdrDetail
	 * @param oldDto
	 * @return
	 */
	public TAHDRDetailDto updateDto(TAHDRDetailDto tahdrDetail, TAHDRDetailDto oldDto);
	public List<TAHDRDetailDto> getPublishedTadhrDetail(String typeCode);
	
	public boolean updateDeviationScheduleInfo(TAHDRDetailDto dto);
	
	public List<TAHDRDetailDto> getTadhrDetailByStatus(String typeCode,String status);
	
	/**
	 * Check if active TAHDRDetail record has tender document attachment.
	 * @author Vivek Birdi
	 * @param tahdrId
	 * @return AttachmentDto, if active TAHDRDetail has an tender document attachment, else null.
	 */
	public AttachmentDto getTenderDoc(Long tahdrId);
	
	/**
	 * Update TAHDRDetail with tenderDocId.
	 * @author Vivek Birdi
	 * @param attachmentId
	 * @param tahdrId
	 * @return number of updated Records.
	 */
	public int updateWithTenderDoc(Long attachmentId, Long tahdrId);
	
	public boolean setNewAuctionFromDate(Long tahdrId,Date newAuctionFromDate);
	public StringBuilder findDifferentDate(TAHDRDetailDto newDto,TAHDRDetailDto oldDto);
	public Long getPreliminaryScrutinyCount();
	public long getPreliminaryScrutinyTenderCount(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public List<TAHDRDetailDto> getPreliminaryScrutinyTender(String query,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue);
	/**
	 * @param bidder
	 * @param tahdrDetail
	 */
	public void mailNotificationToBidders(BidderDto bidder, TAHDRDetailDto tahdrDetail);
	/**
	 * @param dto
	 * @return
	 */
	public TAHDRDetailDto createVersion(TAHDRDetailDto dto);
	public long getTahdrPurchaseQueryCount(String typeCode, BPartnerDto partnerDto,String searchColumn,String searchValue);
	public List<TAHDRDetailDto> getTenderList(String typeCode, BPartnerDto partnerDto, int pageNumber, int pageSize,String searchColumn, String searchValue);
	public TAHDRDetailDto copyNewDatesToOld(TAHDRDetailDto newDto,TAHDRDetailDto oldDto);

	public TAHDRDetailDto updateQuickAuction(TAHDRDetailDto newDto, TAHDRDetailDto oldDto);
	public TAHDRDetailDto updateQuickRfq(TAHDRDto tahdr,TAHDRDetailDto newDto, TAHDRDetailDto oldDto);
	public void mailNotificationOfDateToBidders(BidderDto bidder, TAHDRDetailDto tahdrDetail,StringBuilder html);
	public int updateTenderDocOnDrafted(TAHDRDetailDto dto) ;
	public long getPreliminaryScrutinyTenderCountForWorkflow(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public long getAuctionPreliminaryScrutinyTenderCountForWorkflow(String roleName,String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public boolean endQuicKAuction(Long tahdrId,Date extenedDate);
	public void validate(TAHDRDetailDto tahdrDetail, Errors errors);
}
