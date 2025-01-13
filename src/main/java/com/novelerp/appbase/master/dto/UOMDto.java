package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * @author Vivek Birdi
 *
 */
public class UOMDto extends CommonContextDto {
	
	private static final long serialVersionUID = 8582062180475860801L;
	private Long uomId;
	private String name;
	private String code;
	private String description;
	
	public Long getUomId() {
		return uomId;
	}
	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
