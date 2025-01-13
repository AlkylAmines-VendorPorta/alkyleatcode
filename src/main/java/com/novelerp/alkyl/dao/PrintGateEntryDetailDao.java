package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.PrintGateEntryDetailDto;
import com.novelerp.alkyl.entity.PrintGateEntryDetail;
import com.novelerp.core.dao.CommonDao;

public interface PrintGateEntryDetailDao extends CommonDao<PrintGateEntryDetail, PrintGateEntryDetailDto>{

	String getNewDocType();

}
