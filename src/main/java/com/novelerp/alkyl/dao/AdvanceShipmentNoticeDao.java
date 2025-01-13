package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;
import com.novelerp.core.dao.CommonDao;

public interface AdvanceShipmentNoticeDao extends CommonDao<AdvanceShipmentNotice, AdvanceShipmentNoticeDto> {

	public Integer getASNNumber(String plant);

	public String getServiceSheetNumber();

	boolean updateASNClosedStatus(Long asnId);

	public boolean updateASNGateOutStatus(Long asnId);

	public String getASNReportlist(ASNReadDto dto);

	public String getSSNReportlist(SSNReadDto dto);

	public String getASNListBYStatus(ASNReadDto dto);

}
