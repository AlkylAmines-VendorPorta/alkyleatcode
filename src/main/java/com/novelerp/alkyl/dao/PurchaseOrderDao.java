package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.entity.PurchaseOrder;
import com.novelerp.core.dao.CommonDao;

public interface PurchaseOrderDao extends CommonDao<PurchaseOrder, PurchaseOrderDto> {

	public Long getPobyPartnerId(Long partnerId);
	public Long getReleasedPobyPartnerId(Long partnerId);
	public Long getRejectedPobyPartnerId(Long partnerId);
	public Long getAcceptedPobyPartnerId(Long partnerId);
	public String getPOByFilter(POReadDto dto);
}
