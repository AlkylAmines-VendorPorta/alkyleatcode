package com.novelerp.eat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ReminderMailService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TenderCommitteeParticipantService;
import com.novelerp.eat.service.TenderCommitteeService;

@Service
public class ReminderMailServiceImpl implements ReminderMailService{

	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	private TenderCommitteeService tenderCommitteeService;
	
	@Autowired
	private TenderCommitteeParticipantService tenderCommitteeParticipantService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReferenceListService referenceListService;
	
	private Logger Log =  LoggerFactory.getLogger(ReminderMailServiceImpl.class);
	
	/*Reminder Opening Mail*/
	
	@Override
	public void mailReminderForTahdrOpening(){
		try{
			List<TAHDRDto> tcopTahdr=tahdrService.getTahdrToBeOpenedForReminderMail(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
			for(TAHDRDto tahdrTCOP : tcopTahdr){
				List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrTCOP.getTahdrId()));
				Map<String, Object> listParam=new HashMap<>();
				listParam.put("bidOpeningType", AppBaseConstant.TENDER_COMMITTEE_TechnoCommercial_Opening);
				listParam.put("tahdrId", tahdrTCOP.getTahdrId());
				List<TenderCommitteeDto> committeeData=tenderCommitteeService.findDtos("getQueryForMailListOfCommitteeByTahdrId", listParam);
				mailReminderOfOpeningOfTahdr(tahdrTCOP,bidderData,committeeData,AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED);
			}
			
			List<TAHDRDto> pbopTahdr =tahdrService.getTahdrToBeOpenedForReminderMail(AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED);
			for(TAHDRDto tahdrPBOP : pbopTahdr){
				List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrPBOP.getTahdrId()));
				Map<String, Object> listParams=new HashMap<>();
				listParams.put("bidOpeningType", AppBaseConstant.TENDER_COMMITTEE_PriceBid_Opening);
				listParams.put("tahdrId", tahdrPBOP.getTahdrId());
				List<TenderCommitteeDto> committeeData=tenderCommitteeService.findDtos("getQueryForMailListOfCommitteeByTahdrId", listParams);
				mailReminderOfOpeningOfTahdr(tahdrPBOP,bidderData,committeeData,AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED);
			}
			
			List<TAHDRDto> dbopTahdr =tahdrService.getTahdrToBeOpenedForReminderMail(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED);
			for(TAHDRDto tahdrDBOP : dbopTahdr){
				List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrDBOP.getTahdrId()));
				Map<String, Object> list=new HashMap<>();
				list.put("bidOpeningType", AppBaseConstant.TENDER_COMMITTEE_Deviation_Opening);
				list.put("tahdrId", tahdrDBOP.getTahdrId());
				List<TenderCommitteeDto> committeeData=tenderCommitteeService.findDtos("getQueryForMailListOfCommitteeByTahdrId", list );
				mailReminderOfOpeningOfTahdr(tahdrDBOP,bidderData,committeeData,AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED);
			}
			
			List<TAHDRDto> c1opTahdr =tahdrService.getTahdrToBeOpenedForReminderMail(AppBaseConstant.DOCUMENT_STATUS_C1_BID_OPENED);
			for(TAHDRDto tahdrC1OP : c1opTahdr){
				List<BidderDto> bidderData=bidderService.findDtos("getQueryForMailListByTahdrId", AbstractContextServiceImpl.getParamMap("tahdrId", tahdrC1OP.getTahdrId()));
				Map<String, Object> lists=new HashMap<>();
				lists.put("bidOpeningType", AppBaseConstant.TENDER_COMMITTEE_Annexure_Opening);
				lists.put("tahdrId", tahdrC1OP.getTahdrId());
				List<TenderCommitteeDto> committeeData=tenderCommitteeService.findDtos("getQueryForMailListOfCommitteeByTahdrId", lists);
				mailReminderOfOpeningOfTahdr(tahdrC1OP,bidderData,committeeData,AppBaseConstant.DOCUMENT_STATUS_C1_BID_OPENED);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailReminderForTahdrOpening/ReminderMailServiceIMPL" + e);
		}
	}
	
	@Override
	public void mailReminderOfOpeningOfTahdr(TAHDRDto tahdrDto, List<BidderDto> bidderData, List<TenderCommitteeDto> committeeData, String status){
			MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_OPENING_REMINDER_MAIL);
			Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", map);
			Map<String, Object> param=AbstractServiceImpl.getParamMap("code",status);
			param.put("referenceCode", AppBaseConstant.TAHDR_OPENING_TYPES);
			ReferenceListDto referenceDto=referenceListService.findDto("getRefernceListData", param);
			List<UserDto>  users = new ArrayList<UserDto>();
			if(mailData!=null  && internalUser!=null){
				for(BidderDto bidder : bidderData){
					users.add(bidder.getCreatedBy());
					/*mailReminderOfOpeningForBidder(bidder,tahdrDto,mailData,internalUser,referenceDto);*/
				}
				for(TenderCommitteeDto committee : committeeData){
					List<TenderCommitteeParticipantDto> participantDto =tenderCommitteeParticipantService.findDtos("getQueryForMailListOfCommitteeParticipantByCommitteeId",  AbstractContextServiceImpl.getParamMap("committeeId", committee.getTenderCommitteeId()));
					for(TenderCommitteeParticipantDto dto: participantDto){
						users.add(dto.getUser());
					}
					users.add(committee.getChairPerson());
					/*mailReminderOfOpeningForCommittee(committee,tahdrDto,mailData,internalUser,referenceDto);*/
					
				}
				if(tahdrDto.getCreatedBy()!=null){
					users.add(tahdrDto.getCreatedBy());
				/*mailReminderOfOpeningForCretor(tahdrDto.getCreatedBy(),tahdrDto,mailData,internalUser,referenceDto);*/
				 }
				
				for(UserDto user : users){
				mailReminderOfOpeningForUsers(user,tahdrDto,mailData,internalUser,referenceDto);
				}
			}
		}
	
	@Override
	public void mailReminderOfOpeningForUsers(UserDto user,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser, ReferenceListDto referenceData){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",user.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",user.getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@openingType@", referenceData.getName());
			if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_TC_BID_OPENED)){
			params.put("@openingDate@", tahdr.getTahdrDetail().iterator().next().getTechBidOpenningDate().toString());
			}
			else if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_PRICE_BID_OPENED)){
			params.put("@openingDate@", tahdr.getTahdrDetail().iterator().next().getPriceBidOpenningDate().toString());
			}
			else if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_DEVIATION_BID_OPENED)){
			params.put("@openingDate@", tahdr.getTahdrDetail().iterator().next().getDeviationOpenningDate().toString());
			}
			else if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_C1_BID_OPENED)){
			params.put("@openingDate@", tahdr.getTahdrDetail().iterator().next().getC1OpenningDate().toString());
			}
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(user.getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, user.getEmail());
			}
		}
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
			Log.info("mailReminderOfOpeningForCretor/ReminderMailServiceIMPL" + ex);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailReminderOfOpeningForCretor/ReminderMailServiceIMPL" + e);
		}
	}
	
	/*Reminder Opening Mail*/
	
	/*Reminder Submission Mail*/
	@Override
	public void mailReminderForTahdrSubmission(){
		try{
			List<TAHDRDto> bidSubTahdr=tahdrService.getTahdrOfSubmissionForReminderMail(AppBaseConstant.Bid_Submission);
			for(TAHDRDto bidSubmission : bidSubTahdr){
				List<BidderDto> bidderData=bidderService.getBidderListForSubmissionReminderMail(bidSubmission.getTahdrId(),AppBaseConstant.Bid_Submission);
				mailReminderOfSubmissionOfTahdr(bidSubmission,bidderData,AppBaseConstant.Bid_Submission);
			}
			
			List<TAHDRDto> deviationSubTahdr=tahdrService.getTahdrOfSubmissionForReminderMail(AppBaseConstant.Deviation_Bid);
			for(TAHDRDto tahdrDeviationBid : deviationSubTahdr){
				List<BidderDto> bidderData=bidderService.getBidderListForSubmissionReminderMail(tahdrDeviationBid.getTahdrId(),AppBaseConstant.Deviation_Bid);
				mailReminderOfSubmissionOfTahdr(tahdrDeviationBid,bidderData,AppBaseConstant.Deviation_Bid);
			}
			
			List<TAHDRDto> AnnexureSubTahdr=tahdrService.getTahdrOfSubmissionForReminderMail(AppBaseConstant.Annexture_C1);
			for(TAHDRDto tahdrC1Sub : AnnexureSubTahdr){
				List<BidderDto> bidderData=bidderService.getBidderListForSubmissionReminderMail(tahdrC1Sub.getTahdrId(),AppBaseConstant.Annexture_C1);
				mailReminderOfSubmissionOfTahdr(tahdrC1Sub,bidderData,AppBaseConstant.Annexture_C1);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.info("mailReminderForTahdrSubmission/ReminderMailServiceIMPL" + e);
		}
	}
	
	@Override
	public void mailReminderOfSubmissionOfTahdr(TAHDRDto tahdrDto, List<BidderDto> bidderData,String status){
			MailTemplateDto mailData=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TEMPLATE_SUBMISSION_REMINDER_MAIL);
			Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
			UserDto internalUser=userService.findDto("getUserByRoleCode", map);
			Map<String, Object> param=AbstractServiceImpl.getParamMap("code",status);
			param.put("referenceCode", AppBaseConstant.submission);
			ReferenceListDto referenceDto=referenceListService.findDto("getRefernceListData", param);
			if(mailData!=null  && internalUser!=null){
				for(BidderDto bidder : bidderData){
					mailReminderOfSubmissionForBidder(bidder,tahdrDto,mailData,internalUser,referenceDto);
				}
		}
	}
	
	@Override
	public void mailReminderOfSubmissionForBidder(BidderDto bidder,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser, ReferenceListDto referenceData){
		try{
		if(mailData!=null  && internalUser!=null){
			String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Map<String, Object> params=new HashMap<>();
			params.put("@sourceCompanyName@",internalUser.getPartner().getName());
			params.put("@honour@","");
			params.put("@msebAddress@",internalUser.getUserDetails().getLocation().getAddress1());
			params.put("@emailCorrCode@","Renewal");
			params.put("@generalDetailDate@",today);
			params.put("@vendorComapnyName@",bidder.getPartner().getName());
			params.put("@factoryName@","");
			params.put("@factoryCity@","");
			params.put("@vendorEmail@",bidder.getCreatedBy().getEmail());
			params.put("@tenderCode@", tahdr.getTahdrCode());
			params.put("@bidSubmission@", referenceData.getName());
			if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.Bid_Submission)){
			params.put("@bidDate@", tahdr.getTahdrDetail().iterator().next().getTechnicalBidToDate().toString());
			}
			else if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.Deviation_Bid)){
			params.put("@bidDate@", tahdr.getTahdrDetail().iterator().next().getDeviationToDate().toString());
			}
			else if(referenceData.getCode().equalsIgnoreCase(AppBaseConstant.Annexture_C1)){
			params.put("@bidDate@", tahdr.getTahdrDetail().iterator().next().getC1ToDate().toString());
			}
			params.put("@authoriyNameAndDesignation@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			params.put("@companyTel@",internalUser.getUserDetails().getTelephone1());
			params.put("@companyFax@",internalUser.getUserDetails().getFax1());
			if(bidder.getCreatedBy().getEmail()!=null){
				mailService.sentMailByTemplate(mailData, params, bidder.getCreatedBy().getEmail());
			}
		}
	}
	catch (NullPointerException ex) {
		ex.printStackTrace();
		Log.info("mailReminderOfSubmissionForBidder/ReminderMailServiceIMPL" + ex);
	}
	catch (Exception e) {
		e.printStackTrace();
		Log.info("mailReminderOfSubmissionForBidder/ReminderMailServiceIMPL" + e);
	}
	}
}
