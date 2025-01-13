package com.novelerp.appbase.master.dto;

import java.util.List;

import com.novelerp.appcontext.dto.BPartnerDto;

public class InvitationAuctionParticipantDto {
	
	private static final long serialVersionUID = 1L;
	private List<BPartnerDto> partners;
	private long auctionId;
	
	public List<BPartnerDto> getPartners() {
		return partners;
	}
	public void setPartners(List<BPartnerDto> partners) {
		this.partners = partners;
	}
	public long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}
	

}
