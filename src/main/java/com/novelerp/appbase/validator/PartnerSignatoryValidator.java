package com.novelerp.appbase.validator;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.SignatureService;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.validator.FileValidator;

@Component
public class PartnerSignatoryValidator implements Validator {

	@Autowired
	private ValidationUtil validator;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private SignatureService signatureService;
	@Autowired
	private FileValidator fileValidator;
	
	public void validate(Object object, Errors errors,HttpServletRequest request) {
		if(!object.getClass().isAssignableFrom(PartnerSignatoryDto.class)){
			validator.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		
		PartnerSignatoryDto signatory = (PartnerSignatoryDto) object;
		AttachmentDto attachment = signatory.getDigitallySignTestDoc();
		if(attachment == null || attachment.getAttachmentId() ==null){
			validator.reject(errors, "no.attachment", "No digitally signed document is attached");
		}
		if(signatory.getUserDetail()==null){
			validator.reject(errors, "invalid.signatory", "Invalid signatory details");
		}
		attachment = attachmentService.findDto(attachment.getAttachmentId());
		validateDigitalSignature(attachment, errors, signatory.getUserDetail(),request);
		validateDOB(signatory,errors);
	}
	
	public void validateDigitalSignature(AttachmentDto attachment, Errors errors, UserDetailsDto userDetail,HttpServletRequest request){
		/*String filePath =  attachment.getPath()+attachment.getName();*/
		if(attachment.getPath()==null)
		{
			validator.reject(errors, "invalid.signatoryInfo", "Sign File Path Not Found ");
			return;
		}
		fileValidator.validate(attachment, request, errors);
		if(CommonUtil.isListEmpty(errors.getErrorList())){
			/*File file = new File(filePath);*/
			ResponseDto response = signatureService.validate(attachment, userDetail);
			if(response.isHasError()){
				validator.reject(errors, "invalid.signatoryInfo", response.getMessage());
			}
	   }
	}
	public void validateDOB(PartnerSignatoryDto signatory,Errors errors){
		if(signatory!=null && signatory.getUserDetail()!=null && signatory.getUserDetail().getDob()!= null){
			      	
			      	 int minAge=18;
			    	 Calendar dob = Calendar.getInstance();
			      	 dob.setTime(signatory.getUserDetail().getDob());
			      	 Calendar today = Calendar.getInstance();
                            int curYear = today.get(Calendar.YEAR);
			      	        int dobYear = dob.get(Calendar.YEAR);
			      	        int age = curYear - dobYear;
			      	        int curMonth = today.get(Calendar.MONTH);
			      	        int dobMonth = dob.get(Calendar.MONTH);
			      	        if (dobMonth > curMonth) { // this year can't be counted!
			      	              age--;
			      	         } else if (dobMonth == curMonth) { 		      	
			      	            int curDay = today.get(Calendar.DAY_OF_MONTH);
			      	            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
			      	            if (dobDay > curDay) { // this year can't be counted!
			                         age--;
			      	            }
			      	}
                    if(age<minAge)
			        {
			        	 validator.reject(errors, "invalid.DOB", "Age should be 18 or greater");
			        }
			    }  
		}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
}

