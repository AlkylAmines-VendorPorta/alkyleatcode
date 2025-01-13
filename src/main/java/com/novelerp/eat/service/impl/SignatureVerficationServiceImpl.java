/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.PartnerSignatoryService;
import com.novelerp.appbase.master.service.SignatureService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.service.SignatureVerificationService;
@Service
public class SignatureVerficationServiceImpl implements SignatureVerificationService {

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PartnerSignatoryService partnerSignatoryService;
	
	@Autowired
	private SignatureService signatureService;
	
	/*@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)
	@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;*/
	
	@Override
	public ResponseDto verifyDigitalSignature(AttachmentDto signedAttachment) {
		BPartnerDto partner= contextService.getPartner();
		ResponseDto response=null;
		signedAttachment=attachmentService.findDto(signedAttachment.getAttachmentId());
		/*String filePath=signedAttachment.getPath();
		String filename= signedAttachment.getName();*/
		/*String extension=signedAttachment.getFileExtension();*/
		/*File file= new File(filePath+filename);*/
		/*byte[] buffer=mediaService.getBISFromAttachment(signedAttachment);*/
		List<PartnerSignatoryDto> signatoryList= partnerSignatoryService.findDtos("getPartnerSignatoryQuery", AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
		for(PartnerSignatoryDto signatory: signatoryList){
			response=signatureService.validate(signedAttachment, signatory.getUserDetail());
			if(!response.isHasError()){
				return response;
			}
		}
		return response;
	}

}
