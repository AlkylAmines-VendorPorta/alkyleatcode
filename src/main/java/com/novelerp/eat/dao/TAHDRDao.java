package com.novelerp.eat.dao;

import java.util.List;
import java.util.Map;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.TAHDR;

public interface TAHDRDao extends CommonDao<TAHDR, TAHDRDto> {
public TAHDR getAllTahdrDetails(Long tahdrId);
	
	public int scheduleTender(String schedulingType,Long tahdrId,String bidType,TAHDRDetailDto tahdrDetail);
	
	public int setTenderStatus(String status,Long tahdrId);
	/*public String getOpeningTenderByUserId(TAHDRDetailDto tahdrDetailDto);*/
	
	public String getOpeningTenderByUserId(TenderCommitteeDto tenderCommitteeDto);
	
	public String getOpeningTenderByPartnerId(TenderCommitteeDto tenderCommitteeDto);
	
	public String getOpeningTenderByTahdrId();
	
	public int openTender(Long tahdrId,String status,String remark);
	
	public String getTahdrListByRoleANDLoc(String searchColumn, String searchValue);
	
	public String getTahdrListQueryCountQry(String searchColumn, String searchValue);
	
	public String getTahdrAndTahdrDetailListByRoleANDLoc(String searchColumn, String searchValue);
	
	public String getTenderListsAuditorId(String searchColumn, String searchValue);
	
	public String getMyTenderForBidder(String searchColumn, String searchValue);
	
	public String getTenderListsByRole(String searchColumn, String searchValue);
	
	public String getTahdrApprovalListQueryCountQry(String searchColumn,String searchValue);
	
	public String getTahdrListFromApprovalMatrix(String searchColumn, String searchValue);

	public String getTahdrWithDetailsForAwardWinner();

	public String getTahdrWithDetailsForAwardWinner(String searchColumn, String searchValue);
	
	public String getQuickTahdrWithDetailsForAwardWinner(String searchColumn, String searchValue);

	public String getTahdrWithDetailsForAwardWinnerCount(String searchColumn, String searchValue);
	
	public String getAuctionListByRoleANDLoc(String searchColumn, String searchValue);
	
	public String getMyAuctionForBidder(String searchColumn, String searchValue);
		
	public String getQuickTahdrWithDetailsForAwardWinnerCount(String searchColumn, String searchValue);
	
	public String getAuctionListByRoleANDLoc();
	
	public String getMyAuctionForBidder();
	
	public String getAuctionListsByRole(String searchColumn, String searchValue);

	public String getMytenderCountQuery();

	public String getTahdrWithDetailsForAwardWinnerCount();

	public String getTahdrListForApprovalQuery();
	
	public int uploadMOM(Long tahdrId,Long momAttachmentId);

	/**
	 * @return
	 */
	String getTahdrWithActiveDetail();
	public long getAuctionListByRoleANDLocCountQry(String tenderTypeCode,Long userId,Long levels,String searchColumn,String searchValue);
	public long getMyAuctionForBidderCountQry(String typeCode,Long partnerId,String searchColumn,String searchValue);
	public String getAuctionListsByRoleCountQry(String searchColumn,String searchValue);
	public long getTahdrAndTahdrDetailListByRoleANDLocCountQry(String tenderTypeCode,Long userId,Long levels,String searchColumn,String searchValue);
	public long getMyTenderForBidderCountQry(String tenderTypeCode,Long partnerId,String searchColumn, String searchValue);
	public String getTenderListsAuditorIdCountQry(String searchColumn, String searchValue);
	public String getTenderListsByRoleCountQry(String searchColumn, String searchValue);

	public String getTahdrApprovalListQueryForWorkflow(Map<String, Object> param);

	public String getAuctionApprovalListQueryForWorkflow(Map<String, Object> param);
	public String getTahdrListByCodeAndUser(String searchColumn, String searchValue);

	public String getQuickAuctionListQueryCountQry(String searchColumn, String searchValue);

	public String getTahdrListOfOpeningForReminderMail(String status);
	
	public String getTahdrListOfSubmissionForReminderMail(String status);
	
	public String getRfqDetailsForAwardWinnerCount(String searchColumn, String searchValue);
	
	public String getRfqWithDetailsForAwardWinner(String searchColumn, String searchValue);
	
	public List<TAHDR> getTenderByTypeCode(Long partnerId,String status, String typeCode);
}

