package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.UserDto;

/**
 * 
 * @author varsha
 *
 */
public class TenderCommitteeParticipantDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long committeeParticipantId;

	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tenderCommitteeId",scope=Long.class)
	private TenderCommitteeDto tenderCommittee;
	private DesignationDto designation;
	private UserDto user;
	private String sessionKey;
	private String isMailed;
	
	
	public Long getCommitteeParticipantId() {
		return committeeParticipantId;
	}
	public void setCommitteeParticipantId(Long committeeParticipantId) {
		this.committeeParticipantId = committeeParticipantId;
	}
	public TenderCommitteeDto getTenderCommittee() {
		return tenderCommittee;
	}
	public void setTenderCommittee(TenderCommitteeDto tenderCommittee) {
		this.tenderCommittee = tenderCommittee;
	}
	public DesignationDto getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationDto designation) {
		this.designation = designation;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getIsMailed() {
		return isMailed;
	}
	public void setIsMailed(String isMailed) {
		this.isMailed = isMailed;
	}
	
	
}
