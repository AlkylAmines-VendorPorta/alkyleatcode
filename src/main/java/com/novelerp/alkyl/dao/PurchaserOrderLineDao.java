package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PurchaseOrderLine;
import com.novelerp.core.dao.CommonDao;

public interface PurchaserOrderLineDao extends CommonDao<PurchaseOrderLine, PurchaseOrderLineDto> {

	int DeletepoLine(Long advanceShipmentNoticeLineId);

}
