package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.PoLineConditionDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PoLineCondition;
import com.novelerp.core.service.CommonService;

public interface PoLineConditionService extends CommonService<PoLineCondition, PoLineConditionDto> {

	public List<PoLineConditionDto> save(List<PoLineConditionDto> poLineConditionList, PurchaseOrderLineDto poLine);

}
