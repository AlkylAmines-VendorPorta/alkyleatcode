package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.entity.ASNLineCostCenter;
import com.novelerp.core.dao.CommonDao;

public interface ASNLineCostCenterDao extends CommonDao<ASNLineCostCenter, ASNLineCostCenterDto>{

	
	int DeleteasnLineCostCenter(Long advanceShipmentNoticeLineId);

}
