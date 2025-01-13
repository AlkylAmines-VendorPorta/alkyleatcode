package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class TaxMappingDto  extends CommonDto{

/**
 * 
 * @author Aman
 *
 */
	private static final long serialVersionUID = 7800140825058079898L;
	private int taxMappingid;
	private TaxSetDto taxset;
	private TaxAttributesDto taxattributesid;
    private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getTaxMappingid() {
		return taxMappingid;
	}
	public void setTaxMappingid(int taxMappingid) {
		this.taxMappingid = taxMappingid;
	}
	
	
	
	
	public TaxSetDto getTaxset() {
		return taxset;
	}
	
	public void setTaxset(TaxSetDto taxset) {
		this.taxset = taxset;
	}
	
	public TaxAttributesDto gettaxattributes() {
		return taxattributesid;
	}
	public void settaxattributes(TaxAttributesDto taxattributes) {
		this.taxattributesid = taxattributes;
	}
		

}
