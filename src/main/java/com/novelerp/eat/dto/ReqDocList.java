/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.novelerp.commons.util.CommonUtil;

public class ReqDocList {

	private List<SectionDocumentDto> secDocList;
	
	private Set<SectionDocumentDto> secDocSet;

	public Set<SectionDocumentDto> getSecDocSet() {
		Set<SectionDocumentDto> secDocSet=new HashSet<>();
		if(CommonUtil.isCollectionEmpty(secDocSet) && !CommonUtil.isCollectionEmpty(secDocList)){
			secDocSet.addAll(secDocList);
		}
		return secDocSet;
	}	

	public List<SectionDocumentDto> getSecDocList() {
		return secDocList;
	}

	public void setSecDocList(List<SectionDocumentDto> secDocList) {
		this.secDocList = secDocList;
	}

}
