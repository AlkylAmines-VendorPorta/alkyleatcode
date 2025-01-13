package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.core.service.CommonService;

public interface AdvanceShipmentNoticeLineService extends CommonService<AdvanceShipmentNoticeLine, AdvanceShipmentNoticeLineDto> {

	public List<AdvanceShipmentNoticeLineDto> save(List<AdvanceShipmentNoticeLineDto> asnLines, AdvanceShipmentNoticeDto asn);

	public boolean unloadASN(List<AdvanceShipmentNoticeLineDto> asnLines,
			AdvanceShipmentNoticeDto asn);

	public AdvanceShipmentNoticeDto submitASN(AdvanceShipmentNoticeDto asn);

	public AdvanceShipmentNoticeDto submitServiceSheet(AdvanceShipmentNoticeDto asn);

	public AdvanceShipmentNoticeDto submitServiceEntry(AdvanceShipmentNoticeDto asn);

	public boolean unloadASN105(List<AdvanceShipmentNoticeLineDto> asnLines, AdvanceShipmentNoticeDto asn) throws Exception;

	public boolean qcPassASN(List<AdvanceShipmentNoticeLineDto> asnLineList, AdvanceShipmentNoticeDto asn);

	public boolean qcFailASN(List<AdvanceShipmentNoticeLineDto> lineDtos, AdvanceShipmentNoticeDto asn, String remark) throws Exception;

	public boolean updateCostCenter(List<AdvanceShipmentNoticeLineDto> asnLineList);

	public List<AdvanceShipmentNoticeLineDto> savelist(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn);

	List<AdvanceShipmentNoticeLineDto> getASNLineReportbyFilter(ASNReadDto dto);

	boolean unloadASN101(List<AdvanceShipmentNoticeLineDto> asnLines, AdvanceShipmentNoticeDto asn) throws Exception;

	public List<AdvanceShipmentNoticeLineDto> getASNLineReportwithoutPObyFilter(ASNReadDto dto);

	public List<AdvanceShipmentNoticeLineDto> getSSNLineReportbyFilter(SSNReadDto dto);

}
