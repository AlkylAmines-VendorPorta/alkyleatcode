package com.novelerp.alkyl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.ApprovalMatrixDao;
import com.novelerp.alkyl.dto.ApprovalMatrixDto;
import com.novelerp.alkyl.entity.ApprovalMatrix;
import com.novelerp.alkyl.service.ApprovalMatrixService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.sap.document.sap.rfc.functions.ZVENDORPORTAL_Type;
import com.sap.document.sap.rfc.functions.ZvendorPortal_Zvendorportal_Client;


@Service
public class ApprovalMatrixServiceImpl extends AbstractContextServiceImpl<ApprovalMatrix, ApprovalMatrixDto> implements ApprovalMatrixService {

	@Autowired
	private ApprovalMatrixDao approvalDao;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
		
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ZvendorPortal_Zvendorportal_Client webClient;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@PostConstruct
	public void init(){
		super.init(ApprovalMatrixServiceImpl.class, approvalDao, ApprovalMatrix.class, ApprovalMatrixDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ApprovalMatrixDto approveVendor(ApprovalMatrixDto dto) {
		if(dto.getApprovalMatrixId()==null){
			dto = super.save(dto);
		}else{
			dto = super.updateDto(dto);
		}		
		int responsestatus=partnerService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.DOCUMENT_STATUS_COMPLETE)
				, AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));
		if(responsestatus>0 ){
			dto.setResponse(new ResponseDto(false,"Vendor Approved Sucessfully"));
			partnerService.updatePartnerRole(dto.getPartner().getbPartnerId(), AppBaseConstant.PARTICIPANT);
		}else{
			dto.setResponse(new ResponseDto(true,"Vendor Not Approved "));
		}
		try{
			if(webClient.postVendorData(dto)){
				dto.setResponse(new ResponseDto(false,"Vendor Approved."));
			}else{
				dto.setResponse(new ResponseDto(true,"Vendor Approval Failed."));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		return dto;
	}
	
	@Override
	public Errors fetchVendorData() throws Exception{
		String[] listOfFileNames = mediaService.getFilesListAt(AppBaseConstant.FTP_VENDOR_PROCESSED_PATH);
		Errors errors = new Errors();
		if(null==listOfFileNames || listOfFileNames.length==0){
			errors.addError("Empty", "No records found to process");
			return errors;
		}
		for(String fileName : listOfFileNames){
			try{
				updateVendorData(fileName);
			}catch (Exception e) {
				errors.addError(fileName, e.getMessage());
			}
			
		}
		return errors;
	}
	
	public boolean updateVendorData(String fileName){
		ZVENDORPORTAL_Type out;
		BPartnerDto partner = null;
		boolean flag = false;
		try {
			out = webClient.fetchVendorData(fileName);
			if(!CommonUtil.isStringEmpty(out.getLIFNR())){
				partner = partnerService.findDto("getPartnerByPanNo", AbstractContextServiceImpl.getParamMap("panCardNumber", out.getJ1IPANNO()));
				int result = partnerService.updateByJpql(AbstractContextServiceImpl.getParamMap("vendorSapCode", out.getLIFNR()), 
						AbstractContextServiceImpl.getParamMap("bPartnerId", partner.getbPartnerId()));
				int result1 = userService.updateByJpql(AbstractContextServiceImpl.getParamMap("userName", out.getLIFNR()), 
						AbstractContextServiceImpl.getParamMap("partner.bPartnerId", partner.getbPartnerId()));
				if(result>0 && result1>0){
					mediaService.deleteFile(fileName);
					flag = true;
				}else{
					throw new RuntimeException("Vendor Creation failed");
				}
			}else{
				throw new RuntimeException("Vendor Creation failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Vendor Creation failed - "+e.getMessage());
		}
		
		if(flag){
			UserDto user=userService.findDto("getQueryForUserByPartnerId", AbstractContextServiceImpl.getParamMap("bPartnerId", partner.getbPartnerId()));
			MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.Vendor_CODE_CREATION_SAP_TEMPLATE);
			userService.sendMailOnVendorApproval(user,mailTemplate,out.getLIFNR());
			Map<String, Object> map=new HashMap<>();
			map.put("isPasswordUpdated","N");
			userService.updateByJpql(map, "userId", user.getUserId());
		}
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ApprovalMatrixDto approveVendorWithUserDto(ApprovalMatrixDto dto, UserDto user) {
		if(dto.getApprovalMatrixId()==null){
			dto = super.save(dto);
			System.out.println("Matrix Saved");
		}else{
			dto = super.updateDto(dto);
			System.out.println("Matrix Updated");
		}
		
//		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
//				AppBaseConstant.DOCUMENT_STATUS_COMPLETE);
//		
//		if(null!=dto.getPurchaseGroup()) {
//		param.put("value", dto.getPurchaseGroup());
//		}
//		
//		
//		int responsestatus=partnerService.updateByJpql(param
//				, AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));
//		System.out.println("PartnerID:" +dto.getPartner().getbPartnerId());
//		if(responsestatus>0 ){
//			dto.setResponse(new ResponseDto(false,"Vendor Approved Sucessfully"));
//			partnerService.updatePartnerRole(dto.getPartner().getbPartnerId(), AppBaseConstant.PARTICIPANT);
//			System.out.println("Vendor Approved Sucessfully:" +dto.getPartner().getbPartnerId());
//		}else{
//			dto.setResponse(new ResponseDto(true,"Vendor Not Approved "));
//			System.out.println("Vendor Not Approved:" +responsestatus);
//		}
		
		
//		Map<String, Object> where = new HashMap<>();
//		where.put("bPartnerId", dto.getPartner().getbPartnerId());
//		int resultnew = partnerService.updateByJpql(param, where);
		
		try{
			if(webClient.postVendorDataUserDto(dto, user)){
				partnerService.updatePartnerRole(dto.getPartner().getbPartnerId(), AppBaseConstant.PARTICIPANT);
				System.out.println("FTP Success");
				dto.setResponse(new ResponseDto(false,"Vendor Approved."));
			}else{
				System.out.println("FTP Failed");
				dto.setResponse(new ResponseDto(true,"Vendor Approval Failed."));
			}
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("FTP Failed"+ e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
		return dto;
	}	
	
	
@Override
@Transactional(propagation=Propagation.REQUIRED)	
public void updateStatusAfterVendorApproval(ApprovalMatrixDto dto) {
		
Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.DOCUMENT_STATUS_COMPLETE);

if(null!=dto.getPurchaseGroup()) {
param.put("value", dto.getPurchaseGroup());
}

partnerService.updateByJpql(param, AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));

	}

}

