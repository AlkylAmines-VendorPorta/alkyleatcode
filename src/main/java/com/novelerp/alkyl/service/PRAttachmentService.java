package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.entity.PRAttachment;
import com.novelerp.core.service.CommonService;

public interface PRAttachmentService extends CommonService<PRAttachment, PRAttachmentDto> {

	public List<PRAttachmentDto> save(List<PRAttachmentDto> prAttSet);

	List<PRAttachmentDto> savePRDoc(List<PRAttachmentDto> prAttSet);

}
