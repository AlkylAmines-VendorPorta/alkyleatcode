/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class AttachmentConverter extends CustomDozerConverter<Attachment, AttachmentDto>
		implements ObjectConverter<Attachment, AttachmentDto> {

	/* (non-Javadoc)
	 * @see com.novelerp.core.converter.CustomDozerConverter#convertEntityToDto(com.novelerp.core.entity.PO, java.lang.Class)
	 */
	@Override
	public AttachmentDto convertEntityToDto(Attachment entity, Class<AttachmentDto> dtoClass) {
		if(entity==null){
			return null;
		}
		AttachmentDto attachmentDto=new AttachmentDto();
		attachmentDto.setAttachmentId(entity.getAttachmentId());
		attachmentDto.setFileName(entity.getFileName());
		attachmentDto.setMedia(entity.getMedia());
		
		return attachmentDto;
	}

}
