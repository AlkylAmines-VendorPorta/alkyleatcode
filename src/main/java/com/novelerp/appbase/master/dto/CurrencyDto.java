package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class CurrencyDto extends CommonDto {

	private static final long serialVersionUID = -7356541952944146442L;

	private Long currecyId;
	private String currencyName;
	private String isoCode;

	public Long getCurrecyId() {
		return currecyId;
	}

	public void setCurrecyId(Long currecyId) {
		this.currecyId = currecyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}
