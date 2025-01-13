package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.BidderAttachmentDto;
import com.novelerp.alkyl.entity.BidderAttachment;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;

public interface BidderAttachmentService extends CommonService<BidderAttachment, BidderAttachmentDto>{

	List<BidderAttachmentDto> save(List<BidderAttachmentDto> bidderAttachment, BidderDto bidderDto);

}
