package com.novelerp.appbase.master.dto;

import java.util.HashMap;
import java.util.Map.Entry;

public class TilesRecordCountDto {
	
	private Long partnersCount;
	private Long myTendersCount;
	private Long winnerSelectionCount;
	private Long tenderApprovalCount;
	private HashMap<String , Object> tileCountMap = new HashMap<>();

	public Long getPartnersCount() {
		return partnersCount;
	}

	public void setPartnersCount(Long partnersCount) {
		this.partnersCount = partnersCount;
	}

	public Long getMyTendersCount() {
		return myTendersCount;
	}

	public void setMyTendersCount(Long myTendersCount) {
		this.myTendersCount = myTendersCount;
	}

	public Long getWinnerSelectionCount() {
		return winnerSelectionCount;
	}

	public void setWinnerSelectionCount(Long winnerSelectionCount) {
		this.winnerSelectionCount = winnerSelectionCount;
	}

	public Long getTenderApprovalCount() {
		return tenderApprovalCount;
	}

	public void setTenderApprovalCount(Long tenderApprovalCount) {
		this.tenderApprovalCount = tenderApprovalCount;
	}
	
	public HashMap<String, Object> getTileCountMap() {
		return tileCountMap;
	}

	public void setTileCountMap(HashMap<String, Object> tileCountMap) {
		this.tileCountMap = tileCountMap;
	}
	
	public void addCount(String key, Object value){
		tileCountMap.put(key, value);
	}
	
	public void addCount(HashMap<String, Object> hm){
		for (Entry<String, Object> entry : hm.entrySet()) {
		    addCount(entry.getKey(), entry.getValue());
		}
	}
	

}
