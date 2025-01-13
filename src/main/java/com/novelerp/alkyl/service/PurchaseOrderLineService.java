package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PurchaseOrderLine;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.service.CommonService;

public interface PurchaseOrderLineService extends CommonService<PurchaseOrderLine, PurchaseOrderLineDto> {

	public List<PurchaseOrderLineDto> save(List<PurchaseOrderLineDto> poLineList, PurchaseOrderDto po, BPartnerDto partner);

	public List<PurchaseOrderLineDto> saveService(List<PurchaseOrderLineDto> poLineList, PurchaseOrderDto po, BPartnerDto partner, PurchaseOrderLineDto poLine);



	




}
