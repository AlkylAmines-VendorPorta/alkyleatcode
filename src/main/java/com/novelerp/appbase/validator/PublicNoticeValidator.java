package com.novelerp.appbase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PublicNoticeDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
/** 
 * @author Aman
 *
 */
@Component
public class PublicNoticeValidator implements Validator{

	@Autowired
	private ValidationUtil validator;

	public void validate(Object object, Errors errors) {
		
		if(!object.getClass().isAssignableFrom(PublicNoticeDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}

		PublicNoticeDto publicNoticeDto = (PublicNoticeDto) object;
		
		validator.rejectIfEmpty(publicNoticeDto.getName(), errors, "invalid.Name", "Name is Required");
		validator.rejectIfEmpty(publicNoticeDto.getCode(), errors, "invalid.Code", "Code is Required");
		validator.rejectIfEmpty(publicNoticeDto.getTitle(), errors, "invalid.Title", "Title is Required");
		validator.rejectIfEmpty(publicNoticeDto.getMatter(), errors, "invalid.Matter", "Matter is Required");
		if(publicNoticeDto.getTenderSaleClosingDate()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.TenderSaleClosingDate", "Tender Sale Closing Date Required");
		}
		if(publicNoticeDto.getTenderSaleOpeningDate()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.TenderSaleOpeningDate", "Tender Sale Opening Date Required");
		}
		if(publicNoticeDto.getPublishingDate()==null)
		{
			validator.rejectIfEmpty(null, errors, "invalid.PublishingDate", "Publishing Date Required");
		}
	}

}
