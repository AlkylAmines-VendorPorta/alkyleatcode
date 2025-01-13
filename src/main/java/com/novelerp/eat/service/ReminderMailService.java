package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.ReferenceListDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TenderCommitteeDto;

public interface ReminderMailService {

	public void mailReminderForTahdrOpening();
	
	public void mailReminderOfOpeningOfTahdr(TAHDRDto tahdrDto, List<BidderDto> bidderData, List<TenderCommitteeDto> committeeData, String status);
	
	public void mailReminderOfOpeningForUsers(UserDto user,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser, ReferenceListDto referenceData);
	
	public void mailReminderForTahdrSubmission();
	
	public void mailReminderOfSubmissionOfTahdr(TAHDRDto tahdrDto, List<BidderDto> bidderData,String status);
	
	public void mailReminderOfSubmissionForBidder(BidderDto bidder,TAHDRDto tahdr,MailTemplateDto mailData,UserDto internalUser, ReferenceListDto referenceData);
	
}
