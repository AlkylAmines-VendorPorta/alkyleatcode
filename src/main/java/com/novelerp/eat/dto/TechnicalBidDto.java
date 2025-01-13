/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;

public class TechnicalBidDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long technicalBidId;
	private Set<BidderGtpDto> bidderGtp;
	private String isSubmit;
	private AttachmentDto digiSignedDoc;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="itemBidId",scope=Long.class)
	private ItemBidDto itemBid;
	private List<BidderGtpDto> bidderGtpList;
	private Set<BidderSectionDocDto> bidderSecDoc;
	private List<BidderSectionDocDto> bidderSecDocList;
	private String status;
	
	public Long getTechnicalBidId() {
		return technicalBidId;
	}
	public void setTechnicalBidId(Long technicalBidId) {
		this.technicalBidId = technicalBidId;
	}
	public Set<BidderGtpDto> getBidderGtp() {
		if(CommonUtil.isCollectionEmpty(bidderGtp) && !CommonUtil.isCollectionEmpty(bidderGtpList)){
			bidderGtp=new HashSet<>();
			bidderGtp.addAll(getBidderGtpList());
		}
		return bidderGtp;
	}
	public void setBidderGtp(Set<BidderGtpDto> bidderGtp) {
		this.bidderGtp = bidderGtp;
	}
	public String getIsSubmit() {
		if(CommonUtil.isStringEmpty(isSubmit)){
			return "N";
		}
		return isSubmit;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public AttachmentDto getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(AttachmentDto digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public List<BidderGtpDto> getBidderGtpList() {
		return bidderGtpList;
	}
	public void setBidderGtpList(List<BidderGtpDto> bidderGtpList) {
		this.bidderGtpList = bidderGtpList;
	}
	public Set<BidderSectionDocDto> getBidderSecDoc() {
		if(CommonUtil.isCollectionEmpty(bidderSecDoc) && !CommonUtil.isCollectionEmpty(bidderSecDocList)){
			bidderSecDoc=new HashSet<>();
			bidderSecDoc.addAll(getBidderSecDocList());
		}
		return bidderSecDoc;
	}
	public void setBidderSecDoc(Set<BidderSectionDocDto> bidderSecDoc) {
		this.bidderSecDoc = bidderSecDoc;
	}
	public List<BidderSectionDocDto> getBidderSecDocList() {
		return bidderSecDocList;
	}
	public void setBidderSecDocList(List<BidderSectionDocDto> bidderSecDocList) {
		this.bidderSecDocList = bidderSecDocList;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
