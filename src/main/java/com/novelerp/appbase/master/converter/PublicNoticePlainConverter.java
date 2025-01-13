package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.appbase.master.entity.PublicNotice;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class PublicNoticePlainConverter extends CustomDozerConverter<PublicNotice, PublicNoticeDto> implements ObjectConverter<PublicNotice, PublicNoticeDto>{

	private AttachmentService attachmentService;
	
	@Override
	public PublicNoticeDto convertEntityToDto(PublicNotice entity, Class<PublicNoticeDto> dto) {
		if(entity == null){
			return null;
		}
		PublicNoticeDto publicNoticeDto = new PublicNoticeDto();
		publicNoticeDto.setCode(entity.getCode());
		publicNoticeDto.setName(entity.getName());
		publicNoticeDto.setPublicNoticeId(entity.getPublicNoticeId());
		publicNoticeDto.setCreated(entity.getCreated());
		publicNoticeDto.setUpdated(entity.getUpdated());
		publicNoticeDto.setIsActive(entity.getIsActive());
		publicNoticeDto.setDescription(entity.getDescription());
		publicNoticeDto.setTenderSaleClosingDate(entity.getTenderSaleClosingDate());
		publicNoticeDto.setTenderSaleOpeningDate(entity.getTenderSaleOpeningDate());
		publicNoticeDto.setTitle(entity.getTitle());
		publicNoticeDto.setMatter(entity.getMatter());
		publicNoticeDto.setPublishingDate(entity.getPublishingDate());
		
		if(entity.getAttachment()!=null){
			publicNoticeDto.setAttachment(attachmentService.getDto(entity.getAttachment(), null));
		}
		
		return publicNoticeDto;
	}
	

}

