package com.novelerp.alkyl.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;

import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface AdvanceShipmentNoticeService extends CommonService<AdvanceShipmentNotice, AdvanceShipmentNoticeDto> {

	public Integer getNewASNNumber(String plant);

	public boolean approveServiceEntrySheet(AdvanceShipmentNoticeDto asn) throws JsonProcessingException;

	public String getNewServiceSheetNumber();

	boolean billBooking(AdvanceShipmentNoticeDto asn) throws JsonProcessingException;
	
	boolean updateNewASNClosedStatus(Long asnId);
	
	public boolean updateNewASNGateOutStatus(Long asnId);

	public boolean processGateIn(Long asnId, List<AdvanceShipmentNoticeLineDto> lineDtos, Map<String, Object> param ) throws Exception;
	
	//public boolean processGateIn(Long asnId, List<AdvanceShipmentNoticeLineDto> lineDtos, Map<String, Object> param) throws Exception;
	
	public Long getOpenAsnCountbyPartnerId(Long bPartnerId);

	public Long getPendingPoBillBookingCountbyPartnerId(Long bPartnerId);

	public List<AdvanceShipmentNoticeDto> getASNReportbyFilter(ASNReadDto dto);

	List<AdvanceShipmentNoticeDto> getSSNReportbyFilter(SSNReadDto dto);

	public AdvanceShipmentNoticeDto saveasn(AdvanceShipmentNoticeDto advanceshipmentnotice);

	//int updateasn(AdvanceShipmentNoticeDto asn);

	//boolean updateasn(Long asnId, List<AdvanceShipmentNoticeDto> lineDtos, Map<String, Object> param) throws Exception;

	//boolean updateasn(Long asnId, List<AdvanceShipmentNoticeDto> lineDtos) throws Exception;

	//boolean updateasn(Long asnId, AdvanceShipmentNoticeDto lineDtos) throws Exception;


	//AdvanceShipmentNoticeDto updateasn(AdvanceShipmentNoticeDto asnDto) throws Exception;


	//boolean updateasn(Long asnId, List<AdvanceShipmentNoticeDto> lineDtos, Map<String, Object> param) throws Exception;


	AdvanceShipmentNoticeDto saveasnwithoutpo(AdvanceShipmentNoticeDto asn);

	List<AdvanceShipmentNoticeDto> getASNbyFilter(ASNReadDto dto);

	AdvanceShipmentNoticeDto saveasnDirectFor103(AdvanceShipmentNoticeDto asn);
			

//	public ResponseDto insertinheaderdto(AdvanceShipmentNoticeDto dto);

//	public CommercialPOHeaderDto save(CommercialPOHeaderDto commercialPOHeaderDto);
}
