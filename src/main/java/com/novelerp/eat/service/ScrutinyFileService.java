package com.novelerp.eat.service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.entity.ScrutinyFile;

public interface ScrutinyFileService extends CommonService<ScrutinyFile, ScrutinyFileDto>{
	
	public CustomResponseDto savePreliminaryScrutinyFile(ScrutinyFileDto scrutinyFile,ItemScrutinyDto itemScrutiny);
	
	public CustomResponseDto saveFinalScrutinyFile(ScrutinyFileDto scrutinyFile,ItemScrutinyDto itemScrutiny);
	
	public boolean isAuditingSubmitted(ScrutinyFileDto scrutinyFileDto,ItemScrutinyDto itemScrutinyDto);
	
	public boolean unhookPreviousScrutinyFile(Long itemScrtunityId,String scrutinyLevel,String scrutinyType);
	
	public AttachmentDto addScrutinyFile(ScrutinyFileDto scrutinyFile, String fileName);

}
