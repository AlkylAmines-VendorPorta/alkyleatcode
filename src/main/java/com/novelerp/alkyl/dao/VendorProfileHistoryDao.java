package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.entity.VendorProfileHistory;
import com.novelerp.core.dao.CommonDao;

public interface VendorProfileHistoryDao extends CommonDao<VendorProfileHistory, VendorProfileHistoryDto>{

	boolean updateIsActiveStatus(Long partnerId);
}
