package com.sap.document.sap.rfc.grn;

import java.util.List;

import com.sap.document.sap.rfc.ses.YSERVICECOSTCENTER;

public class GrnFtpObject {
	private List<BAPI2017GMITEMCREATE> lineItem;
	private ZMMGRN poDetails;
	public ZMMGRN getPoDetails() {
		return poDetails;
	}
	public void setPoDetails(ZMMGRN poDetails) {
		this.poDetails = poDetails;
	}
	public List<BAPI2017GMITEMCREATE> getLineItem() {
		return lineItem;
	}
	public void setLineItem(List<BAPI2017GMITEMCREATE> lineItem) {
		this.lineItem = lineItem;
	}

}
