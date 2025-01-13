package com.novelerp.alkyl.component;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;

@Component
public class POComponent {

	@Autowired
	private PurchaseOrderService poService;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private MailService mailService;
	@Autowired
	private MailTemplateService mailTemplateService;
	
	@Autowired
	private UserDetailsService userDetailsService;
//	@Autowired
//	private PurchaseOrderLineService poLineService;
	
	public Errors fetchPOFromFTP(){
		String[] listOfFileNames = mediaService.getFilesListAt(AppBaseConstant.FTP_PO_ITEM_TO_PROCESS_PATH);
		Errors errors = new Errors();
		if(null==listOfFileNames || listOfFileNames.length==0){
			errors.addError("Empty", "No records found to process");
			return errors;
		}
		for(String fileName : listOfFileNames){
			
			try {
			/*	if(fileName.contains("_2")){
					byte[] byteOut = mediaService.getBISFromAttachment(fileName);
					ObjectMapper objJson = new ObjectMapper();
					
					PurchaseOrderDto poDto = objJson.readValue(byteOut, PurchaseOrderDto.class);
					PurchaseOrderDto oldDto = poService.findDto("getPObyNumber", AbstractContextServiceImpl.getParamMap("poNumber", poDto.getPurchaseOrderNumber()));
					poLineService.save(poDto.getPoLineList(), oldDto, oldDto.getPartner());
					
				}else{*/
				byte[] byteOut = mediaService.getBISFromAttachment(fileName);
				ObjectMapper objJson = new ObjectMapper();
				
				PurchaseOrderDto poDto = objJson.readValue(byteOut, PurchaseOrderDto.class);
				
				if(poDto == null){
					errors.addError(fileName, "Empty file");
					continue;
				}
//				if(CommonUtil.isStringEmpty(poDto.getVendorCode())){
//					errors.addError(fileName, "PO Number - "+poDto.getPurchaseOrderNumber()+", "+"Vendor Code cannot be empty null.");
//					continue;
//				}
				BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCodeForPO",AbstractContextServiceImpl.getParamMap("vendorCode", poDto.getVendorCode()));
				
//				if(partner==null){
//					errors.addError(fileName, "Vendor with SAP vendor code "+poDto.getVendorCode()+" not found.");
//					continue;
//				}
				poDto.setPartner(partner);
				/*UserDto vendorUser = userService.findDto("getUserByPartnerId", AbstractContextServiceImpl.getParamMap("bPartnerId", partner.getbPartnerId()));*/
				List<UserDetailsDto> userDetail=userDetailsService.findDtos("getVendorUserForPOActivity", AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
				if(userDetail==null){
					errors.addError(fileName, "No User Found With PO relatice activity.");
					continue;
				}
				UserDto requestedBy = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", poDto.getRequestedBy()));
				
				if(requestedBy==null){
					errors.addError(fileName, "Employee with employee code "+poDto.getRequestedBy()+" not found.");
					continue;
				}
				
//				poDto.setCreatedBy(requestedBy);
//				poDto.setUpdatedBy(requestedBy);
				poDto.setReqby(requestedBy);
				
				poDto = poService.save(poDto);
				
				mediaService.deleteFile(fileName);
				MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PO_RELEASED);
				if(poDto!=null || mailTemplate!=null || userDetail!=null )
				userService.sendMailOnPOReleased(poDto,mailTemplate,userDetail);
			/*	}*/
			} catch (Exception e) {
				errors.addError(fileName, e.getMessage());
			} 
		}
		return errors;
	}
	
//	private void sendMailOnPoPushedToPortal(UserDto userDto, MailTemplateDto mailTemplate, UserDto requestedBy,
//			PurchaseOrderDto poDto) {
//		try {
//			if (userDto != null && userDto.getEmail() != null) {
//
//				/*
//				 * Map<String, Object>
//				 * codeMap=AbstractServiceImpl.getParamMap("role",
//				 * ContextConstant.USER_TYPE_SYSUSER); UserDto
//				 * internalUser=findDto("getUserByRoleCode", codeMap);
//				 */
//
//				if (mailTemplate != null && poDto != null) {
//					Map<String, Object> map = new HashMap<>();
//					String subject = mailTemplate.getSubject();
//					mailTemplate.setSubject(subject);
//					map.put("@PO_Number@", poDto.getPurchaseOrderNumber());
//
//					List<String> toEmailIds = new ArrayList<>();
//					toEmailIds.add(userDto.getEmail());
//
//					List<String> ccEmailIds = new ArrayList<>();
//					ccEmailIds.add(requestedBy.getEmail());
//					ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
//
//					mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
//				}
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	public void sendMailOnAcceptPo(PurchaseOrderDto po) {
		try {
			/*UserDto user = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", po.getPartner().getVendorSapCode()));
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.PO_ACCEPTED_TEMPLATE);
			UserDto requestedBy = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", po.getRequestedBy()));
			sendMailOnPoPushedToPortal(user, mailTemplate, requestedBy, po);*/
			/*UserDto user = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", po.getPartner().getVendorSapCode()));*/
			List<UserDetailsDto> userDetail=userDetailsService.findDtos("getVendorUserForPOActivity", AbstractContextServiceImpl.getParamMap("partnerId", po.getPartner().getbPartnerId()));
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.PO_ACCEPTED_TEMPLATE);
			userService.sendMailOnPOReleased(po,mailTemplate,userDetail);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Boolean createPOFromSAP(PurchaseOrderDto poDto) {
		
		if(poDto == null){
			return false;
		}
		if(CommonUtil.isStringEmpty(poDto.getVendorCode())){
			return false;
		}
		BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCodeForPO",AbstractContextServiceImpl.getParamMap("vendorCode", poDto.getVendorCode()));
		
		if(partner!=null){
			poDto.setPartner(partner);
			List<UserDetailsDto> userDetail=userDetailsService.findDtos("getVendorUserForPOActivity", AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
			if(userDetail==null){
				return false;
			}
		}
		
		/*UserDto vendorUser = userService.findDto("getUserByPartnerId", AbstractContextServiceImpl.getParamMap("bPartnerId", partner.getbPartnerId()));*/
		
		UserDto requestedBy = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.getParamMap("username", poDto.getRequestedBy()));
		
		/*if(requestedBy==null){
			return false;
		}*/
		
//		poDto.setCreatedBy(requestedBy);
//		poDto.setUpdatedBy(requestedBy);
		poDto.setReqby(requestedBy);
		try {
		//poDto = poService.save(poDto);
		poDto = poService.save(poDto);
	//	poLineService.save(poDto.getPoLineList(), poDto, poDto.getPartner());
		}catch (Exception e) {
		return false;
		}
		return true;
		
	}
	
}
