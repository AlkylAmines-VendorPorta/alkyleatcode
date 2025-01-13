package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.entity.PublicNotice;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface PublicNoticeService extends CommonService<PublicNotice, PublicNoticeDto> {

	public List<PublicNoticeDto> getPublicNoticeList();
	public PublicNoticeDto getPublicNotice(Long partnerId);
	public CustomResponseDto savePublicNotice(PublicNoticeDto dto);
	public CustomResponseDto editPublicNotice(PublicNoticeDto dto);
	public CustomResponseDto deletePublicNotice(Long id);
}
