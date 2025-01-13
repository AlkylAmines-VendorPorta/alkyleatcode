package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TenderCommitteeDto;
import com.novelerp.eat.dto.TenderCommitteeParticipantDto;
import com.novelerp.eat.entity.TenderCommittee;

public interface TenderCommitteeService extends CommonService<TenderCommittee, TenderCommitteeDto>{

	public TenderCommitteeDto saveTenderCommittee(TenderCommitteeDto tenderCommittee,TenderCommitteeDto dto);
	
	public List<TenderCommitteeDto> getAssociatedTenders(TenderCommitteeDto tenderCommitteeDto, UserDto user);
	
	public boolean generateSessionKey(Long tenderCommitteId,TenderCommitteeDto tenderCommittee,List<TenderCommitteeParticipantDto> participantList);
	
	public boolean isChairPerson(Long tenderCommitteId,Long userId);
	
	public List<TenderCommitteeDto> getTenderCommitteeByBidopeningType(String openingType);
	
	public long getCommitteeCount(String openingType,String typeCode,Map<String, Object> params,String searchColumn, String searchValue);
	
	public List<TenderCommitteeDto> getCommittee(String queryMethodName,Map<String, Object> params,int pageNumber, int pageSize,String searchColumn, String searchValue);
}
