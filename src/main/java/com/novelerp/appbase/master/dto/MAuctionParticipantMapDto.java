package com.novelerp.appbase.master.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.eat.dto.TAHDRDto;

public class MAuctionParticipantMapDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;
	private long MActionParticipantMapId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	private BPartnerDto bPartner;
	private String status;
	public long getMActionParticipantMapId() {
		return MActionParticipantMapId;
	}
	public void setMActionParticipantMapId(long mActionParticipantMapId) {
		MActionParticipantMapId = mActionParticipantMapId;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public BPartnerDto getbPartner() {
		return bPartner;
	}
	public void setbPartner(BPartnerDto bPartner) {
		this.bPartner = bPartner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
