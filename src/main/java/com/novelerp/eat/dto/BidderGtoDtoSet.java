/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BidderGtoDtoSet {
	
	private List<BidderGtpDto> bidderGtpDtoList;

	public Set<BidderGtpDto> getBidderGtpDtoSet() {
		Set<BidderGtpDto> bidderGtpDtoSet= new HashSet<>();
		bidderGtpDtoSet.addAll(bidderGtpDtoList);
		return bidderGtpDtoSet;
	}

	/*public void setBidderGtpDtoSet(Set<BidderGtpDto> bidderGtpDtoSet) {
		this.bidderGtpDtoSet = bidderGtpDtoSet;
	}*/

	public List<BidderGtpDto> getBidderGtpDtoList() {
		return bidderGtpDtoList;
	}

	public void setBidderGtpDtoList(List<BidderGtpDto> bidderGtpDtoList) {
		this.bidderGtpDtoList = bidderGtpDtoList;
	}

}
