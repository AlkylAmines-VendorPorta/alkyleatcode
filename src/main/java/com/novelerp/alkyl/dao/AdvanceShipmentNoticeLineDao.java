package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.core.dao.CommonDao;

public interface AdvanceShipmentNoticeLineDao extends CommonDao<AdvanceShipmentNoticeLine, AdvanceShipmentNoticeLineDto> {

  public List<AdvanceShipmentNoticeLineDto> printASNByASNNOforSecurityNew(Long asnId);

public String getASNReportLinelist(ASNReadDto dto);

List<AdvanceShipmentNoticeLineDto> printASNByASNNOforSecurityNewWithoutPO(Long asnId);

public String getASNReportLinelistWithoutPO(ASNReadDto dto);

public String getSSNReportLinelist(SSNReadDto dto);

}
