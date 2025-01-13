package com.novelerp.eat.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
@Component
public class TenderContext {
	
	private Map<Long,Map<Long,UserDto>> tenderMap=new HashMap<>();
	private Map<Long,Map<Long,UserDto>> tenderBidderMap=new HashMap<>();
	
	public void addTenderForCommittee(Long tahdrId,UserDto user){
		Map<Long, UserDto> userMap = tenderMap.get(tahdrId);
		if(userMap!=null){
			addCommitteeJoinee(tahdrId, user);
		}else{
			userMap=new HashMap<>();
			userMap.put(user.getUserId(), user);
			tenderMap.put(tahdrId, userMap);
		}
	}
	
	public void addTenderForBidder(Long tahdrId,UserDto user){
		Map<Long, UserDto> userMap = tenderBidderMap.get(tahdrId);
		if(userMap!=null){
			addBidderJoinee(tahdrId, user);
		}
		else{
			userMap=new HashMap<>();
			userMap.put(user.getUserId(), user);
			tenderBidderMap.put(tahdrId, userMap);
		}
	}

	
	public void addCommitteeJoinee(Long tahdrId,UserDto user){
		Map<Long,UserDto> userMap=tenderMap.get(tahdrId);
		userMap.put(user.getUserId(), user);
		tenderMap.put(tahdrId, userMap);
	}
	
	public void addBidderJoinee(Long tahdrId,UserDto user){
		Map<Long,UserDto> userMap=tenderBidderMap.get(tahdrId);
		userMap.put(user.getUserId(), user);
		tenderBidderMap.put(tahdrId, userMap);
	}
	
	public Map<Long,UserDto> getCommitteeJoinee(Long tahdrId){
		return tenderMap.get(tahdrId);
	}
	
	public Map<Long,UserDto> getBidderJoinee(Long tahdrId){
		return tenderBidderMap.get(tahdrId);
	}
	
	public void removeCommitteeJoinee(Long tahdrId,Long userId){
		Map<Long,UserDto> userMap= tenderMap.get(tahdrId);
		if(userMap!=null && userMap.containsKey(userId)){
			userMap.remove(userId);
		}
	}
	
	public void removeBidderJoinee(Long tahdrId,Long userId){
		Map<Long,UserDto> userMap= tenderBidderMap.get(tahdrId);
		if(userMap!=null && userMap.containsKey(userId)){
			userMap.remove(userId);
		}
	}
	
	public void removeTender(Long tahdrId){
		if(tenderMap.containsKey(tahdrId)){
			tenderMap.remove(tahdrId);
		}
		if(tenderBidderMap.containsKey(tahdrId)){
			tenderBidderMap.remove(tahdrId);
		}
	}
	
	public void removeBidderFromAll(Long userId){
		for(Map<Long,UserDto> users:tenderBidderMap.values()){
			if(users!=null && users.containsKey(userId)){
				users.remove(userId);
			}
		}
	}
	
	public void removeUserFromAll(Long userId){
		for(Map<Long,UserDto> users:tenderMap.values()){
			if(users!=null && users.containsKey(userId)){
				users.remove(userId);
			}
		}
	}
	
	public boolean checkLoggedUser(Long tahdrId,List<Long> userIdList){
		Map<Long, UserDto> userMap = tenderMap.get(tahdrId);
		for(Long userId:userIdList){
			UserDto user=userMap.get(userId);
			if(user!=null){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkLoggedChairPerson(Long tahdrId,Long chairPersonId){
		Map<Long, UserDto> userMap = tenderMap.get(tahdrId);
		UserDto user=userMap.get(chairPersonId);
			if(user!=null){
				return true;
			}
		return false;
	}
	
}
