package com.novelerp.eat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRSchedulingService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.validator.TAHDRDatesValidator;

@Service
public class TAHDRSchedulingServiceImpl implements TAHDRSchedulingService{

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TAHDRDatesValidator tahdrDatesValidation;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Override
	public CustomResponseDto getMyTenderDetailsForScheduling(Long tahdrId) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params=new HashMap<>();
		params.put("tahdrId", tahdrId);
		List<TAHDRDto> tahdrList=new ArrayList<TAHDRDto>();
		TAHDRDto tender=tahdrService.findDto("getTenderListsByTahdrId", params);
		tahdrList.add(tender);
		response.addObject("tahdrList",tahdrList);
		
		Map<String, String> scheduleList = refListService.getReferenceListMap(CoreReferenceConstants.SCHEDULING);
		response.addObject("scheduleList", scheduleList);
		
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("tenderStatusList", tenderStatusList);
		
		return response;
	}

	@Override
	public CustomResponseDto getTenderDetailsForScheduling(String tenderTypeCode) {
		CustomResponseDto response=new CustomResponseDto();
		Map<String, Object> params=new HashMap<>();
		params.put("tenderTypeCode", tenderTypeCode);
		/*List<TAHDRDto> tahdr=tahdrService.findDtos("getPublishedTenderListByTypeCode", params);*/
		List<TAHDRDto> tahdrList=null;
		if(tenderTypeCode.equals("RA") || tenderTypeCode.equals("FA")){
			if(tenderTypeCode.equals("RA")){
				params.put("tenderTypeCode", "PT"); //getSchedulingListForTenders
			}
			tahdrList=tahdrService.findDtos("getSchedulingAuctionListByTypeCode", params);
			Map<String, String> scheduleList = refListService.getReferenceListMap(CoreReferenceConstants.SCHEDULING);
			response.addObject("scheduleList", scheduleList);
		}else{
			tahdrList=tahdrService.findDtos("getSchedulingTenderListByTypeCode", params);
			List<ReferenceListDto> refernceList=refListService.findDtos("getSchedulingListForTenders", AbstractContextServiceImpl.getParamMap("code",CoreReferenceConstants.SCHEDULING));
			response.addObject("scheduleList", refernceList);
		}
		Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		response.addObject("tenderStatusList", tenderStatusList);
		
		response.addObject("tahdrList",tahdrList);
		return response;
	}

	@Override
	public CustomResponseDto getTenderList(int pageNumber, int pageSize, String searchColumn, String searchValue,
			String typeCode) {
		CustomResponseDto response=new CustomResponseDto();
		 Map<String, Object> newObjectMap= new HashMap<>();
		 Map<String, Object> objectMap = new HashMap<>();
		 long countResult = 0l;
		Map<String, Object> params=new HashMap<>();
		params.put("tenderTypeCode", typeCode);
		List<TAHDRDto> tahdrList=null;
		/*Map<String, String> scheduleList = refListService.getReferenceListMap(CoreReferenceConstants.SCHEDULING);*/
		if(typeCode.equals("RA") || typeCode.equals("FA")){
			if(typeCode.equals("RA")){
				params.put("tenderTypeCode", "PT");
			}
			tahdrList=tahdrService.getTenders("getSchedulingAuctionListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
			Map<String, String> scheduleList = refListService.getReferenceListMap(CoreReferenceConstants.SCHEDULING);
			newObjectMap.put("scheduleList", scheduleList);
		}else{
			
			tahdrList=tahdrService.getTenders("getSchedulingTenderListByTypeCode", params, pageNumber, pageSize, searchColumn, searchValue);
			List<ReferenceListDto> refernceList=refListService.findDtos("getSchedulingListForTenders", AbstractContextServiceImpl.getParamMap("code",CoreReferenceConstants.SCHEDULING));
			newObjectMap.put("scheduleList", refernceList);
		}
		countResult=tahdrService.getSchedulingTenderCount(typeCode,params,searchColumn,searchValue);
		newObjectMap.put("tahdrList", tahdrList);
		 int LastPage=(int) (countResult / pageSize);
		 LastPage = (int) ((LastPage)==1?LastPage:LastPage + 1);
		 Map<String, String> tenderStatusList = refListService.getReferenceListMap(AppBaseConstant.DOCUMENT_STATUS);
		
		 newObjectMap.put("tenderStatusList", tenderStatusList);
		 newObjectMap.put("tahdrList", tahdrList);
		 /*newObjectMap.put("scheduleList", scheduleList);*/
		 objectMap.put("LastPage", LastPage);
		 response.setData( newObjectMap);
		 response.setObjectMap(objectMap);
		return response;
	}

	@Override
	public CustomResponseDto updateTahdrScheduling(TAHDRDetailDto tahdrDetail) {
		CustomResponseDto response=new CustomResponseDto();
		Errors errors =  new Errors();
		
		Map<String,Object> map= new HashMap<>();
		Map<String,Object> params= new HashMap<>();
		/*boolean tenderUpdateCount=false;*/
		String schedulingType="";
		String openingDate="";
		String submissionDate="";
		String resultMessage="";
		Long tahdrDetailId=tahdrDetail.getTahdrDetailId();
		Long tahdrId= tahdrDetail.getTahdr()==null?null: tahdrDetail.getTahdr().getTahdrId();
		String tenderNo=tahdrDetail.getTahdr().getTahdrCode()==null?"Tender_NULL":tahdrDetail.getTahdr().getTahdrCode();
		
		tahdrDatesValidation.validateSchedulingDate(tahdrDetail, errors);
		
		if(errors.getErrorCount()>0){
			ResponseDto errResponse=new ResponseDto();
			errResponse.setHasError(true);
			errResponse.setErrors(errors.getErrorList());
			
			response.addObject("dateValidatioError", errResponse);
			response.addObject("result", false);
			response.addObject("dateValidationResult", false);
			response.addObject("resultMessage","Date Validation Error !");
			return response;
			
		}
		
		if(tahdrId!=null){
			/*TAHDRDto tahdr=tahdrService.findDto(tahdrId);*/
			TAHDRDto tahdr=tahdrService.findDto("getQueryForTAHDRById", AbstractContextServiceImpl.getParamMap("tahdrId",tahdrId));
			String bidType=tahdr.getBidTypeCode();
				if(tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING)){
				map.put("c1OpenningDate", tahdrDetail.getC1OpenningDate());
				map.put("c1ToDate", tahdrDetail.getC1ToDate());
				params.put("tahdrStatusCode", AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING);
				/*if(!AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING.equals(tahdr.getTahdrStatusCode())){*/
					Date todayDate=new Date();
					if(tahdr.getIsAuction().equals("Y") && tahdr.getTahdrDetail().iterator().next().getAuctionToDate()!=null && 
							(tahdr.getTahdrDetail().iterator().next().getAuctionToDate().compareTo(todayDate)==0 
							|| tahdr.getTahdrDetail().iterator().next().getAuctionToDate().compareTo(todayDate)==1)  ){
						resultMessage="Auction yet not Ended ";	
						response.addObject("result", false);
						response.addObject("resultMessage",resultMessage);
						
					}else if(tahdr.getIsAuction().equals("Y") && !AppBaseConstant.AUCTION_SCHEDULING.equals(tahdr.getTahdrStatusCode()) ){
						resultMessage="Auction is yet not scheduled ";
						response.addObject("result", false);
						response.addObject("resultMessage",resultMessage);
						
					}else if(tahdr.getIsAuction().equals("N") && !AppBaseConstant.Price_BID_OPENED.equals(tahdr.getTahdrStatusCode()) ){
						resultMessage="Price Bid not opened ";
						response.addObject("result", false);
						response.addObject("resultMessage",resultMessage);
						
					}else{
						response=tahdrService.scheduleTender(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING,tahdrId,bidType);
						schedulingType="ANNEXURE C1 SCHEDULED";
						
						if(tahdrDetail.getC1OpenningDate()!=null){
							openingDate=tahdrDetail.getC1OpenningDate().toString();	
						}
						if(tahdrDetail.getC1ToDate()!=null){
							submissionDate=tahdrDetail.getC1ToDate().toString();						
						}
					}
					
				/*}
				else{
					resultMessage="ANNEXURE C1 AlREADY SCHEDULED";
					response.addObject("result", false);
					response.addObject("resultMessage",resultMessage);
					return response;
				}*/
				}else if(tahdr.getTahdrStatusCode().equals(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING)){
					map.put("deviationOpenningDate", tahdrDetail.getDeviationOpenningDate());
					map.put("deviationToDate", tahdrDetail.getDeviationToDate());
					params.put("tahdrStatusCode", AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING);
						Date todayDate=new Date();
							response=tahdrService.scheduleTender(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING,tahdrId,bidType);
							schedulingType="DEVIATION BID RE-SCHEDULED";
							
							if(tahdrDetail.getDeviationOpenningDate()!=null){
								openingDate=tahdrDetail.getDeviationOpenningDate().toString();	
							}
							if(tahdrDetail.getDeviationToDate()!=null){
								submissionDate=tahdrDetail.getDeviationToDate().toString();						
							}
				}else if(tahdrDetail!=null && (tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING))){
				map.put("winnerSelectionDate", tahdrDetail.getWinnerSelectionDate());
				params.put("tahdrStatusCode", AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING);
				
				
				/*if(!AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING.equals(tahdr.getTahdrStatusCode())){*/
					response=tahdrService.scheduleTender(AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING,tahdrId,bidType);
					schedulingType="AWARD WINNER SCHEDULED";
					
					if(tahdrDetail.getWinnerSelectionDate()!=null){
						openingDate=tahdrDetail.getWinnerSelectionDate().toString();
					}
					resultMessage="Price Bid or Annexure C1 Bid or Revised bit yet not opened";
				/*}
				else{
					resultMessage="AWARD WINNER ALREAFDY SCHEDULED";
					response.addObject("result", false);
					response.addObject("resultMessage",resultMessage);
					return response;
				}*/
				}
				else if(tahdrDetail!=null && (tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING))){
				map.put("priceBidOpenningDate", tahdrDetail.getPriceBidOpenningDate());
				params.put("tahdrStatusCode", AppBaseConstant.TENDER_PRICE_BID_SCHEDULING);
				
				
				/*if(!AppBaseConstant.TENDER_PRICE_BID_SCHEDULING.equals(tahdr.getTahdrStatusCode())){*/
					response=tahdrService.scheduleTender(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING,tahdrId,bidType);
					schedulingType="PRICE BID SCHEDULED";
					
					if(tahdrDetail.getPriceBidOpenningDate()!=null){
						openingDate=tahdrDetail.getPriceBidOpenningDate().toString();
					}
					resultMessage="Scrutiny has not finished";
				/*}
				else{
					resultMessage="PRICE BID ALEADY SCHEDULED";
					response.addObject("result", false);
					response.addObject("resultMessage",resultMessage);
					return response;
				}*/
				
				}
				else if(tahdrDetail!=null && (tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.TENDER_REVISED_BID_SCHEDULING))){
				map.put("revisedBidOpenningDate", tahdrDetail.getRevisedBidOpenningDate());
				map.put("revisedBidToDate", tahdrDetail.getRevisedBidToDate());
				params.put("tahdrStatusCode", AppBaseConstant.TENDER_REVISED_BID_SCHEDULING);
				
				/*if(!AppBaseConstant.TENDER_REVISED_BID_SCHEDULING.equals(tahdr.getTahdrStatusCode())){*/
					response=tahdrService.scheduleTender(AppBaseConstant.TENDER_REVISED_BID_SCHEDULING,tahdrId,bidType);
					schedulingType="REVISED BID SCHEDULED";
					
					if(tahdrDetail.getRevisedBidOpenningDate()!=null){
						openingDate=tahdrDetail.getRevisedBidOpenningDate().toString();	
					}
					if(tahdrDetail.getRevisedBidToDate()!=null){
						submissionDate=tahdrDetail.getRevisedBidToDate().toString();						
					}
					resultMessage="Final Scrutiny yet not finished";
				/*}
				else{
					resultMessage="REVISED BID ALREADY SCHEDULED";
					response.addObject("result", false);
					response.addObject("resultMessage",resultMessage);
					return response;
				}*/
				}
				else if(tahdrDetail!=null && (tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.AUCTION_SCHEDULING))){ 
					map.put("auctionFromDate", tahdrDetail.getAuctionFromDate());
					map.put("auctionToDate", tahdrDetail.getAuctionToDate());
					params.put("tahdrStatusCode", AppBaseConstant.AUCTION_SCHEDULING);
					
/*					if(!AppBaseConstant.AUCTION_SCHEDULING.equals(tahdr.getTahdrStatusCode())){*/
						response=tahdrService.scheduleTender(AppBaseConstant.AUCTION_SCHEDULING,tahdrId,bidType);
						schedulingType="AUCTION SCHEDULED";
						
						if(tahdrDetail.getAuctionFromDate()!=null){
							openingDate=tahdrDetail.getAuctionFromDate().toString();	
						}
						if(tahdrDetail.getAuctionToDate()!=null){
							submissionDate=tahdrDetail.getAuctionToDate().toString();						
						}
						resultMessage="Price Bid yet not Opened";
					/*}
					else{
						resultMessage="AUCTION ALREADY SCHEDULED";
						response.addObject("result", false);
						response.addObject("resultMessage",resultMessage);
						return response;
					}*/
					}
					
					if((boolean) response.getObjectMap().get("result")){
						tahdrDetailService.updateByJpql(map, "tahdrDetailId", tahdrDetailId);
						response.addObject("result", true);
						/*List<String> emailList=bidderService.getBidderMailListByTahdrId(tahdrId);*/
						if((tahdrDetail.getTahdrScheduling().equals(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING)) &&!(boolean) response.getObjectMap().get("bidderResult")){
							response.addObject("resultMessage",schedulingType+" Successfully,But No bidder called for C1 Matching !");
						}else{
							List<String> C1CalledPartnerMailList=bidderService.getBidderMailListByTahdrId(tahdrId,AppBaseConstant.BIDDER_STATUS_ANNEXURE_C1_CALLED);
							String sub="Annexure Called";
							String message="<p>Hi</p><br><p>Your Bid For Tender: "+tenderNo+" has been called for Annexure C1 Matching .<p>";
							bidderService.sendMailToAllBidder(C1CalledPartnerMailList, sub, message);
							
							response.addObject("resultMessage",schedulingType+" Successfully");
						}
						/*String sub="Tender Sceduling";
						String message="<p>Hi</p><br><p>The Tender: "+tenderNo+" has been "+schedulingType+" on "+date+" .<p>";
						bidderService.sendMailToAllBidder(emailList, sub, message);
						return response;*/
						Map<String, Object> maps= new HashMap<>();
						maps.put("tahdrId", tahdrId);
						List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", maps);
						for(BidderDto bidder : bidderData){
							MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_SCHEDULE_OPENING);
							Map<String, Object> user=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
							UserDto internalUser=userService.findDto("getUserByRoleCode", user);
							if(mailData!=null  && internalUser!=null){
								String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
								Map<String, Object> paramData=new HashMap<>();
								String sourceCompanyName=internalUser.getPartner()==null?"":internalUser.getPartner().getName()==null?"":internalUser.getPartner().getName();
								String msebAddress=internalUser.getUserDetails()==null?"":internalUser.getUserDetails().getLocation()==null?"":internalUser.getUserDetails().getLocation().getAddress1()==null?"":internalUser.getUserDetails().getLocation().getAddress1();
								String vendorComapnyName=bidder.getPartner()==null?"":bidder.getPartner().getName()==null?"":bidder.getPartner().getName();
								String vendorEmail=bidder.getCreatedBy()==null?"":bidder.getCreatedBy().getEmail()==null?"":bidder.getCreatedBy().getEmail();
								String tenderCode=tahdrDetail==null?"":tahdrDetail.getTahdr()==null?"":tahdrDetail.getTahdr().getTahdrCode()==null?"":tahdrDetail.getTahdr().getTahdrCode();
								String companyTel=internalUser.getUserDetails()==null?"":internalUser.getUserDetails().getTelephone1()==null?"":internalUser.getUserDetails().getTelephone1();
								String companyFax=internalUser.getUserDetails()==null?"":internalUser.getUserDetails().getFax1()==null?"":internalUser.getUserDetails().getFax1();
								paramData.put("@sourceCompanyName@",sourceCompanyName);
								paramData.put("@honour@","");
								paramData.put("@msebAddress@",msebAddress);
								paramData.put("@emailCorrCode@","Renewal");
								paramData.put("@generalDetailDate@",today);
								paramData.put("@vendorComapnyName@",vendorComapnyName);
								paramData.put("@factoryName@","");
								paramData.put("@factoryCity@","");
								paramData.put("@vendorEmail@",vendorEmail);
								paramData.put("@tenderCode@", tenderCode);
								if(bidder.getCreatedBy().getName()!=null){
									paramData.put("@vendorName@", bidder.getCreatedBy().getName());
								}
								if(schedulingType!=null){
									paramData.put("@schedulingType@", schedulingType);	
								}
								if(openingDate!=null){
									paramData.put("@openingDate@", openingDate);
								}
								if(submissionDate!=null){
									paramData.put("@submissionDate@", submissionDate);
								}
								paramData.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
								paramData.put("@companyTel@",companyTel);
								paramData.put("@companyFax@",companyFax);
								if(bidder.getCreatedBy().getEmail()!=null){
									mailService.sentMailByTemplate(mailData, paramData, bidder.getCreatedBy().getEmail());
								}
							}
					}
						return response;
					}else{
						response.addObject("result", false);
						response.addObject("resultMessage",resultMessage);
						return response;
					}
					
				
				
			}
		else{
			response.addObject("result", false);
			response.addObject("resultMessage","Tender Scheduling Failed");
			return response;
		}
	}

}
