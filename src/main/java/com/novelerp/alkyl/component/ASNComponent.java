package com.novelerp.alkyl.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.util.DateUtil;

@Component
public class ASNComponent {

	@Autowired
	private AdvanceShipmentNoticeService asnService;

	@Autowired
	private UserService userService;

	@Autowired
	private MailTemplateService mailTemplateService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	public AdvanceShipmentNoticeDto updateGateEntry(AdvanceShipmentNoticeDto asnDto) {
		AdvanceShipmentNoticeDto oldDto = asnService.findDto("getASNByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnDto.getAdvanceShipmentNoticeId()));
		setGateEntryFieldsToOldDto(oldDto, asnDto);
		return asnService.updateDto(oldDto);
	}

	private void setGateEntryFieldsToOldDto(AdvanceShipmentNoticeDto oldDto, AdvanceShipmentNoticeDto newDto) {
		oldDto.setNameOfDriver(newDto.getNameOfDriver());
		oldDto.setMobileNumber(newDto.getMobileNumber());
		oldDto.setPhotoIdProof(newDto.getPhotoIdProof());
		oldDto.setPhotoIdProofAtt(newDto.getPhotoIdProofAtt());
		oldDto.setIsReported("Y");
		oldDto.setReportedDate(new Date());
		oldDto.setStatus(AppBaseConstant.ASN_STATUS_REPORTED);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public Integer getNewASNNumber(String plant) {
		return asnService.getNewASNNumber(plant);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public String getNewServiceSheetNumber() {
		return asnService.getNewServiceSheetNumber();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public AdvanceShipmentNoticeDto getASN(Long asnId) {
		return asnService.findDto("getASNByASNId", AbstractContextServiceImpl.getParamMap("asnId", asnId));
	}

	public void sendMainOnAsnCreation(AdvanceShipmentNoticeDto asn) {
		try {

			AdvanceShipmentNoticeDto asnDto = asnService.findDto("getAsnAndPartnerByASNId",
					AbstractContextServiceImpl.getParamMap("asnId", asn.getAdvanceShipmentNoticeId()));
			/*UserDto user = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", asnDto.getPo().getPartner().getVendorSapCode()));*/
			List<UserDetailsDto> userDetail=userDetailsService.findDtos("getVendorUserForPOActivity", AbstractContextServiceImpl.getParamMap("partnerId", asnDto.getPo().getPartner().getbPartnerId()));
		//	UserDto reqBy = asnDto.getPo().getReqby();
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.ASN_CREATED_TEMPLATE);
			if (mailTemplate != null && asnDto != null && asnDto.getPo() != null) {
				// Map<String, Object> params =
				// AbstractContextServiceImpl.getParamMap("plant",
				// asnDto.getPo().getPoLineList().get(0).getPlant());
				// params.put("roleValue",
				// AppBaseConstant.ROLE_COMMERCIAL_ADMIN);
				// UserDto commercial =
				// userService.findDto("getUserByRoleAndPlant", params);
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();
				subject = subject.replace("@ASN_Number@", asn.getAdvanceShipmentNoticeNo().toString());
				subject = subject.replace("@PO_Number@", asn.getPo().getPurchaseOrderNumber().toString());
				mailTemplate.setSubject(subject);
				map.put("@PO_Number@", asn.getPo().getPurchaseOrderNumber().toString());
				map.put("@ASN_Number@", asn.getAdvanceShipmentNoticeNo().toString());
				map.put("@PO_Date@",  DateUtil.getDateString(asnDto.getPo().getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
				map.put("@VENDOR_NAME@",asnDto.getPo().getPartner().getName());

				List<String> toEmailIds = new ArrayList<>();
				for(UserDetailsDto userDetails:userDetail){
				toEmailIds.add(userDetails.getEmail());
				}

				List<String> ccEmailIds = new ArrayList<>();
			//	ccEmailIds.add(reqBy.getEmail());
				
				
				// ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
				// if (commercial != null) {
				// ccEmailIds.add(commercial.getEmail());
				// }

			//	mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
				mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void commonSSNMail(AdvanceShipmentNoticeDto asn, String ssnCreated1Mail, boolean isVendor,
			boolean isRequestedBy, boolean isInvoice) {
		AdvanceShipmentNoticeDto asnDto = asnService.findDto("getAsnAndPartnerByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asn.getAdvanceShipmentNoticeId()));
		Map<String, Object> map = new HashMap<>();
		UserDto user = null;
		List<UserDetailsDto> userDetail=null;
		List<UserDetailsDto> accUserDetails=null;
		if (isVendor) {
			/*user = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", asnDto.getPo().getPartner().getVendorSapCode()));*/
			 userDetail=userDetailsService.findDtos("getVendorUserForPOActivity", AbstractContextServiceImpl.getParamMap("partnerId", asnDto.getPo().getPartner().getbPartnerId()));
			 accUserDetails=userDetailsService.findDtos("getVendorUserForAccActivity", AbstractContextServiceImpl.getParamMap("partnerId", asnDto.getPo().getPartner().getbPartnerId()));
		} else {
			user = asnDto.getPo().getReqby();
		}
		MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(ssnCreated1Mail);
		String subject = mailTemplate.getSubject();
		subject = subject.replace("@SSN_Number@", asnDto.getServiceSheetNo());
		subject = subject.replace("@PO_Number@", asnDto.getPo().getPurchaseOrderNumber());
		mailTemplate.setSubject(subject);
		UserDto reqBy = null;
		if (isVendor) {
			reqBy = asnDto.getPo().getReqby();
			map.put("@SSN_Number@", asnDto.getServiceSheetNo());
			map.put("@PO_Number@", asnDto.getPo().getPurchaseOrderNumber());
			map.put("@VENDOR_NAME@", asnDto.getPo().getPartner().getName());
			/*map.put("@PO_Date@", asnDto.getPo().getDate() == null ? "" : asnDto.getPo().getDate().toString());*/
			map.put("@PO_Date@", DateUtil.getDateString(asnDto.getPo().getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
			map.put("@REQUESTED_BY@", reqBy.getName() == null ? "" : reqBy.getName());
		} else if (isRequestedBy) {
			reqBy = userService.findDto("getUserBYUserName",
					AbstractContextServiceImpl.getParamMap("username", asnDto.getPo().getPartner().getVendorSapCode()));
			map.put("@SSN_Number@", asnDto.getServiceSheetNo());
			/*map.put("@VENDOR_NAME@", CommonUtil.isStringEmpty(reqBy.getName()) ? "" : reqBy.getName());*/
			map.put("@VENDOR_NAME@", asnDto.getPo().getPartner().getName());
			map.put("@PO_Number@", asnDto.getPo().getPurchaseOrderNumber());
			map.put("@REQUESTED_BY@", CommonUtil.isStringEmpty(user.getName()) ? "" : user.getName());
			map.put("@PO_Date@",  DateUtil.getDateString(asnDto.getPo().getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
		} else if (isVendor && isRequestedBy) {
			map.put("@SSN_Number@", asnDto.getServiceSheetNo());
			map.put("@PO_Number@", asnDto.getPo().getPurchaseOrderNumber());
			map.put("@VENDOR_NAME@", asnDto.getPo().getPartner().getName());
			map.put("@PO_Date@",  DateUtil.getDateString(asnDto.getPo().getDate(),DateUtil.DEFAUT_MAIL_FORMAT));
			reqBy = asnDto.getPo().getReqby();
		}

		if (mailTemplate != null && asnDto != null && asnDto.getPo() != null) {
			/*
			 * Map<String, Object> params =
			 * AbstractContextServiceImpl.getParamMap("plant",
			 * asnDto.getPo().getPoLineList().get(0).getPlant());
			 * params.put("roleValue", AppBaseConstant.ROLE_COMMERCIAL_ADMIN);
			 */
			/*
			 * UserDto commercial = userService.findDto("getUserByRoleAndPlant",
			 * params);
			 */

			List<String> toEmailIds = new ArrayList<>();
			if (isInvoice && isRequestedBy) {
				toEmailIds.add(asnDto.getPo().getReqby().getEmail());
			}
			if(!isInvoice && isVendor ){
					for(UserDetailsDto userDetails:userDetail){
						toEmailIds.add(userDetails.getEmail());
					}
				}
			if(!isInvoice && isRequestedBy ){
						toEmailIds.add(asnDto.getPo().getReqby().getEmail());
					}
				
			if(isInvoice && isVendor ){
					for(UserDetailsDto userDetails:accUserDetails){
						toEmailIds.add(userDetails.getEmail());
					}
				}
				
		
			List<String> ccEmailIds = new ArrayList<>();
			if (isVendor && isRequestedBy) {
				ccEmailIds.add(reqBy.getEmail());
			}
			/*
			 * if (commercial != null) { ccEmailIds.add(commercial.getEmail());
			 * }
			 */

			mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
		}
	}

	public void sendMainOnGateEntry(AdvanceShipmentNoticeDto asn) {
		try {
			sendMainOnAsnCreation(asn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMainOnSubmitServiceSheet(AdvanceShipmentNoticeDto asn) {
		commonSSNMail(asn, AppBaseConstant.SSN_CREATED_1_MAIL_TEMPLATE, true, false, false);
		commonSSNMail(asn, AppBaseConstant.SSN_CREATED_2_MAIL_TEMPLATE, false, true, false);
	}

	public void sendMailOnApproveServiceSheet(AdvanceShipmentNoticeDto asn) {
		commonSSNMail(asn, AppBaseConstant.SSN_APPROVED_TEMPLATE, true, true, false);
	}

	public void sendMailOnProceedBillBooking(AdvanceShipmentNoticeDto asn) {
		commonSSNMail(asn, AppBaseConstant.INVOICE_SUBMITTED_TEMPLATE1, true, false, true);
		commonSSNMail(asn, AppBaseConstant.INVOICE_SUBMITTED_TEMPLATE2, false, true, true);
	}
	
	//public void sendASNCreationReminderMail(String PoNo,String Invoice,String IDate,String toEmail,String poreqby,String poemail) {
		
	public void sendASNCreationReminderMail(String PoNo,String Invoice,String IDate,String toEmail,String poemail) {
		try {
			MailTemplateDto mailTemplate = mailTemplateService
					.getMailTemplateFromTemplateType(AppBaseConstant.ASN_CREATE_REMINDER);
			if (mailTemplate != null && PoNo != null && Invoice != null) {
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();
				subject = subject.replace("@pono@", PoNo);
				subject = subject.replace("@invoice@", Invoice);
				mailTemplate.setSubject(subject);
				map.put("@pono@",PoNo);
				map.put("@invoice@", Invoice);
				map.put("@date@",  IDate);
				
				List<String> toEmailIds = new ArrayList<>();
				toEmailIds.add(toEmail);
				
				

				List<String> ccEmailIds = new ArrayList<>();
			//	ccEmailIds.add(poreqby);
				//ccEmailIds.add(pocreatedby);
				ccEmailIds.add(poemail);
				// ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM);
				// if (commercial != null) {
				// ccEmailIds.add(commercial.getEmail());
				

				mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CustomResponseDto sendMailOnServiceSheetApproval(Long asnId) {
		CustomResponseDto resp=new CustomResponseDto();
		try {
			
			
			MailTemplateDto mailTemplate = mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.SSN_APPROVED_TEMPLATE);
			
			AdvanceShipmentNoticeDto asnDetails = asnService.findDto("getASNDetailsByASNId",AbstractContextServiceImpl.getParamMap("asnId", asnId));
			
			if (mailTemplate != null) {
				Map<String, Object> map = new HashMap<>();
				String subject = mailTemplate.getSubject();

				subject = subject.replace("@SSN_Number@", asnDetails.getServiceSheetNo());
				subject=  subject.replace("@PO_Number@", asnDetails.getPo().getPurchaseOrderNumber());
				mailTemplate.setSubject(subject);                                                                                                                                                                                                                                             
				map.put("@VENDOR_NAME@",asnDetails.getCreatedBy().getName());
				map.put("@SSN_Number@", asnDetails.getServiceSheetNo());
				map.put("@PO_Number@", asnDetails.getPo().getPurchaseOrderNumber());
				map.put("@approverName@", asnDetails.getSsnApprovedBy().getName());
				
				
				List<String> toEmailIds = new ArrayList<>();
				toEmailIds.add(asnDetails.getCreatedBy().getEmail());
				
                List<String> ccEmailIds = new ArrayList<>();	
                
//                for(String ccMailIdList:ccList) {
//                	ccEmailIds.add(ccMailIdList);
//                }
			//	ccEmailIds.addAll(ccList);

//				List<String> ccEmailIds = new ArrayList<>();
//				
//				ccEmailIds.add(AppBaseConstant.PURCHASE_TEAM_1);

				

              mailService.sentMailByTemplateAsync(mailTemplate, map, toEmailIds, ccEmailIds, true);
              resp=new CustomResponseDto(true,"Mail Sucessfully Sent");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resp=new CustomResponseDto(false,"Mail Sending Failed");
		}
		//return null;
		return resp;
		
	}

	
}
