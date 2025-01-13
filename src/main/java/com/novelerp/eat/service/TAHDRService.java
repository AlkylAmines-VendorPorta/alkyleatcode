/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ErrorDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.entity.TAHDR;

public interface TAHDRService extends CommonService<TAHDR, TAHDRDto> {
	public ResponseDto submitTahdrDetails(TAHDRDto tahdr,UserDto current, UserDto next,TAHDRDetailDto tahdrDetailDto);
	public ResponseDto changeStatusFromRJtoDR(Long tahdrId);
	public boolean isTahdrExists(String code);
	
	public List<String> getAccessClause(String role);
	public List<ReferenceListDto> getAccessAction(String role);
	public long getTahdrListQueryCount(Map<String,Object> map,String searchColumn, String searchValue);
	public List<TAHDRDto> getTahdrList(Map<String,Object> map,int pageNumber, int pageSize,String searchColumn, String searchValue);
	public CustomResponseDto openingTender(TAHDRDto tahdr,List<ItemBidDto> itemBidList,List<PriceBidDto> pblist);
	
	public CustomResponseDto getBidderByOpeningType(Long tahdrId,Long tahdrMaterialId);
	
	public boolean updateTahdrMom(TAHDRDto tahdr);
	
	public CustomResponseDto scheduleTender(String schedulingType,Long tahdrId,String bidType);
	
	public boolean setTenderStatus(String status,Long tahdrId);
	
	
	public String getOpeningTenderByUserId(TenderCommitteeDto tenderCommitteeDto);
	
	public String getOpeningTenderByPartnerId(TenderCommitteeDto tenderCommitteeDto);
	
	public List<TAHDRDto> getAssociatedTenders(TenderCommitteeDto tenderCommitteeDto,UserDto user);
	
	public List<TAHDRDto> getAssociatedTenders(TAHDRDetailDto tahdrDetailDto,UserDto user);
	
	public List<TAHDRDto> getBidderAssociatedTenders(TenderCommitteeDto tenderCommitteeDto,BPartnerDto user);
	/**
	 * @param tahdr
	 * @return
	 */
	public List<ErrorDto> checkTahdrDetails(TAHDR tahdr);
	
	public boolean openTender(Long tahdrId,String status,String remark);
	
	public List<TAHDRDto> getTenderForCommitteeFormation(Map<String, Object> params);
	
	public long getTahdrApprovalListQueryCount(Map<String, Object> map,String searchColumn, String searchValue);
	public List<TAHDRDto> getTahdrApprovalList(Map<String,Object> map,int pageNumber, int pageSize,String searchColumn, String searchValue);
	public List<TAHDRDto> getTahdrWithDetailsForAwardWinner(Map<String, Object> params, int pageNumber, int pageSize,
			String searchColumn, String searchValue);
	public Long getTahdrWithDetailsForAwardWinnerCount(Map<String, Object> params, String searchMode, String searchValue);
	public TAHDRDto getTahderAllDetails(Long tahdrID);
	public Long getMyTendersCount();
	public Long getTahdrWithDetailsForAwardWinnerCount();
	public List<TAHDRDto> getTenders(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue);
	public long getSchedulingTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public long getBidSheetTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public long getPreBidTenderCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	public CustomResponseDto getBidderCommercialBid(Long tahdrId);
	public long getTahdrApprovalListQueryCount(Map<String, Object> param);
	public long getAuctionApprovalListQueryCount(Map<String, Object> param);
	public void mailNotificationStatusWise(TAHDRDto tahdrDto,List<BidderDto> bidderData,List<TAHDRApprovalMatrixDto> approvalMatrixuser,String tahdrStatusCode);
	public void mailNotificationForBidderVOStatus(BidderDto bidder,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForAppMatrixUserVOStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForCretorVOStatus(UserDto creator,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForFirstLevelAppMatrixUser(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser);
	public void mailNotificationForAppMatrixUserPUStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForCretorPUStatus(UserDto creator,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForCretorRJStatus(UserDto creator,TAHDRDto tahdrDetail,MailTemplateDto mailData, UserDto internalUser);
	public void mailNotificationForCretorAPStatus(UserDto creator,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser,UserDto user);
	public void mailNotificationForNextLevelAPStatus(TAHDRApprovalMatrixDto approvalUser,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser);
	public List<TAHDRDto> getTahdrToBeOpenedForReminderMail(String status);
	public List<TAHDRDto> getTahdrOfSubmissionForReminderMail(String status);
	
	public long getTenderBidsCount(String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	
	public List<TAHDRDto> getTenderByTypeCode(Long partnerId,String status ,String typeCode);
}
