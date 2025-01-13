/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class StandardCustomDocDto extends CommonContextDto implements Comparable<StandardCustomDocDto> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long standardCustomDocId;
	private AttachmentDto attachment;
	private String code;
	private String name;
	private String description;
	private int index;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	
	public Long getStandardCustomDocId() {
		return standardCustomDocId;
	}
	public void setStandardCustomDocId(Long standarsCustomDocId) {
		this.standardCustomDocId = standarsCustomDocId;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StandardCustomDocDto std) {
		if(index==std.index){
			return 0;
		}else if(index>std.index){
			return +1;
		}else{
			return -1;
		}
		
	}

}
