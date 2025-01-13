package com.novelerp.appcontext.dto;

public class QueryMasterDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long adQueryMasterId;
    private String name;
	private String value;
	public Long getAdQueryMasterId() {
		return adQueryMasterId;
	}
	public void setAdQueryMasterId(Long adQueryMasterId) {
		this.adQueryMasterId = adQueryMasterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
