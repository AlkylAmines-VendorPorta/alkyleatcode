package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.PurchaseOrder;
import com.novelerp.core.service.CommonService;

public interface PurchaseOrderService extends CommonService<PurchaseOrder, PurchaseOrderDto> {

	public Long getPobyPartnerIdNew(Long partnerId);
	public Long getReleasedPobyPartnerIdNew(Long partnerId);
	public Long getRejectedPobyPartnerIdNew(Long partnerId);
	public Long getAcceptedPobyPartnerIdNew(Long partnerId);
	public Long getOpenPoCountbyPartnerId(Long bPartnerId);
	public List<PurchaseOrderDto> getPoByFilter(POReadDto dto);
	//List<PurchaseOrderDto> getPoGateEntryByFilter(POReadDto dto);
	
}
