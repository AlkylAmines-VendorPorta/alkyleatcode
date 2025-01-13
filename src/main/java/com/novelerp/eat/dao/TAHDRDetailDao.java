package com.novelerp.eat.dao;

import java.util.Date;
import java.util.List;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRSearchDto;
import com.novelerp.eat.entity.TAHDRDetail;

public interface TAHDRDetailDao extends CommonDao<TAHDRDetail, TAHDRDetailDto> {

	public Long getVersionNumber(Long tahdrId);
	public int createVersion(Long tahdrId);
	public List<TAHDRDetail> getTAHDRList(TAHDRSearchDto tahdrSearchDto);
	/*public List<TAHDRDetail> getTAHDRTypeCode(String typeCode,BPartnerDto partnerDto);*/
	/*public List<TAHDRDetail> getAllTAHDRList(TAHDRSearchDto tahdrSearchDto);*/
	public List<TAHDRDetail> getPublishedTadhrDetail(String typeCode);
	
	public int updateDeviationScheduleInfo(TAHDRDetailDto dto);
	
	public List<TAHDRDetail> getTadhrDetailByStatus(String typeCode,String status);

	/**
	 * Check if active TAHDRDetail record has tender document attachment
	 * @author Vivek Birdi
	 * @param tahdrId
	 * @return Attachment, if active TAHDRDetail has an tender document attachment, else null.
	 */
	public Attachment getTenderDoc(Long tahdrId);
	
	/**
	 * Update TAHDRDetail with tenderDocId.
	 * @author Vivek Birdi
	 * @param attachmentId
	 * @param tahdrId
	 * @return number of updated Records.
	 */
	public int updateWithTenderDoc(Long attachmentId, Long tahdrId);
	
	public int setNewAuctionFromDate(Long tahdrId,Date newLastDate);
	public String getTAHDRPurchaseCount(String typeCode,BPartnerDto partnerDto,String searchColumn,String searchValue);
	public String getTAHDRTypeCode(String typeCode,BPartnerDto partnerDto,String searchColumn, String searchValue) ;
	public String getTahdrDetailFromTahdrDetailId();
	public int endQuicKAuction(Long tahdrId,Date extendedDate);
}
