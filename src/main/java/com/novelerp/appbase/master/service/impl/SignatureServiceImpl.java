package com.novelerp.appbase.master.service.impl;

import java.io.File;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.SignatureService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.DigitalRDNInfo;
import com.novelerp.core.dto.DigitalSignatureInfo;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.DigitialSignatureProcessor;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Service
public class SignatureServiceImpl implements SignatureService{

	@Autowired
	private DigitialSignatureProcessor sinatureProcessor;
	@Autowired
	/*@Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL)*/
    /*@Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)*/
    @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Override
	public ResponseDto validate(File file, UserDetailsDto userDetail) {
		DigitalSignatureInfo signatureInfo = sinatureProcessor.getDigitalSignatureInfo(file);
		if(signatureInfo == null){
			return new ResponseDto(true, "File is not digitally signed");
		}
		if(!signatureInfo.isValidToday()){
			return new ResponseDto(true, "Signature is not valid on " + new Date());
		}
		return validateSignature(signatureInfo, userDetail);
	}
	
	@Override
	public ResponseDto validate(File file, UserDetailsDto userDetail,String isEncrypted) {
		DigitalSignatureInfo signatureInfo = sinatureProcessor.getDigitalSignatureInfo(file,isEncrypted);
		if(signatureInfo == null){
			return new ResponseDto(true, "File is not digitally signed");
		}
		if(!signatureInfo.isValidToday()){
			return new ResponseDto(true, "Signature is not valid on " + new Date());
		}
		return validateSignature(signatureInfo, userDetail);
	}
	
	@Override
	public ResponseDto validate(AttachmentDto attachment, UserDetailsDto userDetail) {
		byte[] buffer=mediaService.getBISFromAttachment(attachment);
		DigitalSignatureInfo signatureInfo = sinatureProcessor.getDigitalSignatureInfo(buffer,attachment.getIsDocEncrypted());
		if(signatureInfo == null){
			return new ResponseDto(true, "File is not digitally signed");
		}
		if(!signatureInfo.isValidToday()){
			return new ResponseDto(true, "Signature is not valid on " + new Date());
		}
		return validateSignature(signatureInfo, userDetail);
	}
	
	@Override
	public ResponseDto validate(byte[] buffer, UserDetailsDto userDetail,String isEncrypted) {
		DigitalSignatureInfo signatureInfo = sinatureProcessor.getDigitalSignatureInfo(buffer,isEncrypted);
		if(signatureInfo == null){
			return new ResponseDto(true, "File is not digitally signed");
		}
		if(!signatureInfo.isValidToday()){
			return new ResponseDto(true, "Signature is not valid on " + new Date());
		}
		return validateSignature(signatureInfo, userDetail);
	}
	
	private ResponseDto validateSignature(DigitalSignatureInfo signatureInfo, UserDetailsDto userDetail){
		
		DigitalRDNInfo issuer =  signatureInfo.getIssuer();
		DigitalRDNInfo subject =  signatureInfo.getSubject();
		ResponseDto issuerResponse = validateIssuer(issuer);
		if(issuerResponse.isHasError()){
			return issuerResponse;
		}
		ResponseDto classResponse=validateClass(signatureInfo.getX509cert());
		if(classResponse.isHasError()){
			return classResponse;
		}
		ResponseDto subjectResponse =  validateSubject(subject, userDetail);
		if(subjectResponse.isHasError()){
			return subjectResponse;
		}
		return new ResponseDto(false, "Valid");
	}
	private ResponseDto validateClass(X509Certificate x509Cert){
		if(x509Cert==null){
			return new ResponseDto(true, "Invalid Class Certificate");
		}
		String certifi=x509Cert.toString();
		char class3 = certifi.charAt(certifi.indexOf("2.16.356.100.2.")+15);
		String companyName  = x509Cert.getIssuerDN().toString();
		boolean tcs_MSEDCL_Class2=companyName.toString().indexOf("TCS sub-CA for MSEDCL (WORKS)")!=-1;
		if(class3!='3')
		{	
			if(!tcs_MSEDCL_Class2){
			   return new ResponseDto(true,"Not valid class 3 Certificate");
			}   
		}
		return new ResponseDto(false, "Valid Certificate");
	}
	private ResponseDto validateIssuer(DigitalRDNInfo issuer){
		if(issuer==null){
			return new ResponseDto(true, "Invalid Issuer");
		}
		/*String signatory = issuer.getSignatory().toLowerCase();
		if(!signatory.contains("class3") && !signatory.contains("class 3")){
			return new ResponseDto(true, "Not valid class 3 Certificate");
		}*/
		return new ResponseDto(false, "Valid Issuer");
	}
	
	private ResponseDto validateSubject(DigitalRDNInfo subject, UserDetailsDto userDetail){
		if(subject == null){
			return new ResponseDto(true, "Invalid Subject");
		}
		String signatory =  subject.getSignatory();
		String organization= subject.getOrganization();
		String postal  = subject.getPostalCode();
		
		ResponseDto mandatoryValidationResponse = validateMandatoryFields(signatory, organization, postal);
		if(mandatoryValidationResponse.isHasError()){
			return mandatoryValidationResponse;
		}		
		String firstName  = userDetail.getFirstName();
		String middleName = userDetail.getMiddleName()==null?"":userDetail.getMiddleName() ;
		String lastName   = userDetail.getLastName();
		
		
		String userName =  firstName+middleName+lastName;
		signatory = signatory.replaceAll(" ", "");
		if(!userName.equalsIgnoreCase(signatory)){
			return new ResponseDto(true, "Signatory info does not match");
		}
		
		return new ResponseDto(false, "Valid Signatory");
	}
	
	private ResponseDto validateMandatoryFields(String signatory,  String organization, String postal){
	
		if(CommonUtil.isStringEmpty(signatory) 
				|| CommonUtil.isStringEmpty(organization) 
				|| CommonUtil.isStringEmpty(postal)){
			return new ResponseDto(true, "Invalid subject info");
		}
		return new ResponseDto(false, "Valid");
	}

	@Override
	public ResponseDto validateCoSigner(File file,UserDetailsDto userDetail){
		
		ResponseDto response=new ResponseDto();
		List<DigitalSignatureInfo> signaturesInfo = sinatureProcessor.getDigitalSignatureInfoList(file);
		if(signaturesInfo==null || signaturesInfo.isEmpty()){
			return new ResponseDto(true, "File is not digitally signed");
		}
		for(DigitalSignatureInfo info:signaturesInfo)
		{
			response=validateSignature(info,userDetail);
			if(!response.isHasError())
			{
				if(!info.isValidToday()){
					return new ResponseDto(true, "Co-Signature is not valid on " + new Date());
				}else{
					for(DigitalSignatureInfo signatures:signaturesInfo)
					{
						if(!signatures.isValidToday())
						{   
						  return new ResponseDto(true, "Signature is not valid on " + new Date());
						}
					}
					return response;
				}				
			}		
		}
		return response;
		/*if(!signatureInfo.isValidToday()){
			return new ResponseDto(true, "Sinature is not valid on " + new Date());
		}*/
		/*return response;*/
	}
	@Override
	public ResponseDto validateCoSigner(AttachmentDto attachment,UserDetailsDto userDetail){
		
		ResponseDto response=new ResponseDto();
		byte [] buffer=mediaService.getBISFromAttachment(attachment);
		List<DigitalSignatureInfo> signaturesInfo = sinatureProcessor.getDigitalSignatureInfoList(buffer, attachment.getIsDocEncrypted());
		if(signaturesInfo==null || signaturesInfo.isEmpty()){
			return new ResponseDto(true, "File is not digitally signed");
		}
		for(DigitalSignatureInfo info:signaturesInfo)
		{
			response=validateSignature(info,userDetail);
			if(!response.isHasError())
			{
				if(!info.isValidToday()){
					return new ResponseDto(true, "Co-Signature is not valid on " + new Date());
				}else{
					for(DigitalSignatureInfo signatures:signaturesInfo)
					{
						if(!signatures.isValidToday())
						{   
						  return new ResponseDto(true, "Signature is not valid on " + new Date());
						}
					}
					return response;
				}				
			}		
		}
		return response;
	}
}
