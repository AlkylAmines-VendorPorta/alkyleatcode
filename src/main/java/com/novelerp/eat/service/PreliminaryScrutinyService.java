package com.novelerp.eat.service;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface PreliminaryScrutinyService {
	
	public  CustomResponseDto getMyTender(Long tahdrId);
	
	public  CustomResponseDto getTenderList(int pageNumber, 
			int pageSize,String searchColumn , String searchValue,String typeCode);
	
	public  CustomResponseDto getBidderListByTahdrDetailId(Long tahdrDetailId);
	
	public  CustomResponseDto getItemListBybidderId(Long bidderId);
	
	public boolean sendPreliminaryStatusToBidder(Long tahdrId,String tenderNo);
	
	public CustomResponseDto savePreliminaryTechnicalStatus(ItemScrutinyDto newItemScrutinyDto);
	
	public CustomResponseDto savePreliminaryCommercialStatus(ItemScrutinyDto newItemScrutinyDto);
	
	public CustomResponseDto updateTechnicalItemScrutinyLine(ItemScrutinyLineDto dto);
	
	public CustomResponseDto updateCommercialItemScrutinyLine(ItemScrutinyLineDto dto);
	
	public ItemScrutinyLineDto saveGtpScrutiny(ItemScrutinyLineDto dto);
	
	public CustomResponseDto saveAuditorComment(ItemScrutinyLineDto dto);
	
	public CustomResponseDto confirmAuditorComment(ItemScrutinyDto dto);
	
	public CustomResponseDto checkAuditingStatus(Long tahdrId);
	
	public CustomResponseDto addAuditor(TAHDRDto tahdr);
	
	public CustomResponseDto saveDeviationSchedule(TAHDRDetailDto dto);
	
	public CustomResponseDto checkDeviationSchedule(Long tahdrDetailId);
	
	public CustomResponseDto intimidateAuditor(Long tahdrId,String tahdrName,Long auditorId);
	
	public CustomResponseDto intimidateAuditing(TAHDRDto tahdr);
	
}
