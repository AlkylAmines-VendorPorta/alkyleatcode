package com.novelerp.eat.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

/**
 * 
 * @author varsha
 *
 */
public class TenderCommitteeDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    private Long tenderCommitteeId;
	    @JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	    private TAHDRDto tahdr;
	    private String isProcessed;
	    private UserDto chairPerson;
	    @JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	    private TAHDRDetailDto tenderVersion;
	    private Set<TenderCommitteeParticipantDto> participant;
	    private String sessionKey;
	    private String bidOpeningType;
	    private String isMailed;
	    
		public Long getTenderCommitteeId() {
			return tenderCommitteeId;
		}
		public void setTenderCommitteeId(Long tenderCommitteeId) {
			this.tenderCommitteeId = tenderCommitteeId;
		}
		public TAHDRDto getTahdr() {
			return tahdr;
		}
		public void setTahdr(TAHDRDto tahdr) {
			this.tahdr = tahdr;
		}
		public String getIsProcessed() {
			return isProcessed;
		}
		public void setIsProcessed(String isProcessed) {
			this.isProcessed = isProcessed;
		}
		public UserDto getChairPerson() {
			return chairPerson;
		}
		public void setChairPerson(UserDto chairPerson) {
			this.chairPerson = chairPerson;
		}
		public TAHDRDetailDto getTenderVersion() {
			return tenderVersion;
		}
		public void setTenderVersion(TAHDRDetailDto tenderVersion) {
			this.tenderVersion = tenderVersion;
		}
		
		public String getSessionKey() {
			return sessionKey;
		}
		public void setSessionKey(String sessionKey) {
			this.sessionKey = sessionKey;
		}
		public String getBidOpeningType() {
			return bidOpeningType;
		}
		public void setBidOpeningType(String bidOpeningType) {
			this.bidOpeningType = bidOpeningType;
		}
		public String getIsMailed() {
			return isMailed;
		}
		public void setIsMailed(String isMailed) {
			this.isMailed = isMailed;
		}
		public Set<TenderCommitteeParticipantDto> getParticipant() {
			return participant;
		}
		public void setParticipant(Set<TenderCommitteeParticipantDto> participant) {
			this.participant = participant;
		}
	    
	    
	  
}
