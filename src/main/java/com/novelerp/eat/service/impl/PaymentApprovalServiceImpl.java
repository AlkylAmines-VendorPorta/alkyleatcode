package com.novelerp.eat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.TAHDRPaymentApprovalDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;
import com.novelerp.eat.service.PaymentApprovalService;
import com.novelerp.eat.service.SMSService;

/**
 * 
 * @author varsha
 *
 */
@Service
public class PaymentApprovalServiceImpl extends AbstractContextServiceImpl<PaymentDetail, PaymentDetailDto>implements PaymentApprovalService{
    @Autowired
	private TAHDRPaymentApprovalDao tahdrPaymentApprovalDao;
    
    @Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
    @Autowired
    private BPartnerService partnerService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MailTemplateService mailTemplateService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private PartnerOrgService partnerOrgService;
    
    @PostConstruct
	private void init() {
		super.init(PaymentApprovalServiceImpl.class, tahdrPaymentApprovalDao, PaymentDetail.class, PaymentDetailDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<PaymentDetailDto> getPaymentApprovalList(UserDto userDto) {
		
		List<PaymentDetailDto> paymentApprovalList= new ArrayList<PaymentDetailDto>();
		try {
			String where = null;
			RoleDto role=contextService.getDefaultRole(); 
				if(role.getValue().equalsIgnoreCase("FO")){
					where = "";
				} else if (role.getValue().equalsIgnoreCase("FA")) {
					where = " where isFOApproved='Y' and isFAApproved=null ";
				}
			List<PaymentDetail> paymentList = tahdrPaymentApprovalDao.findAll(where, " id asc");
			for (PaymentDetail paymentDetail : paymentList) {
				PaymentDetailDto paymentDetailDto = convertToDto(paymentDetail);
				paymentApprovalList.add(paymentDetailDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentApprovalList;
	}
	@Override
	public PaymentDetail convertToEntity(PaymentDetailDto paymentDetailDto) {
		PaymentDetail paymentDetail=getEntity(paymentDetailDto);
		return paymentDetail;
	}
	@Override
	public PaymentDetailDto convertToDto(PaymentDetail paymentDetail) {
		PaymentDetailDto paymentDetailDto= getDto(paymentDetail);
		return paymentDetailDto;
	}
	@Override
    @Transactional
	public ResponseDto updatePaymentStatus(PaymentDetailDto paymentDetailDto, String role,UserDto sessionUser,PaymentDetailDto paymentDetail) {
		ResponseDto response=new ResponseDto();
		Map<String, Object> map = new HashMap<>();
		if (!(role == null) && !role.equals("")) {

			if (role.equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) {
				if (paymentDetailDto.getFoComment() != "" || paymentDetailDto.getFoComment() != null) {
					map.put("foComment", paymentDetailDto.getFoComment());
				}
				map.put("isFOApproved", paymentDetailDto.getIsFOApproved());
				map.put("foApprovedBy.userId", sessionUser.getUserId());
				map.put("foApprovedDate", new Date());
			} else if (role.equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)) {
				if (paymentDetailDto.getFaComment() != "" || paymentDetailDto.getFaComment() != null) {
					map.put("faComment", paymentDetailDto.getFaComment());
				}
				map.put("isFAApproved", paymentDetailDto.getIsFAApproved());
				map.put("faApprovedBy.userId", sessionUser.getUserId());
				map.put("faApprovedDate", new Date());
			}
			
			if(paymentDetailDto.getRealisationStatus()!=null){
				map.put("realisationStatus", paymentDetailDto.getRealisationStatus());
			}
			if(paymentDetailDto.getRealisationDate()!=null){
				map.put("realisationDate", paymentDetailDto.getRealisationDate());
			}
			if(paymentDetailDto.getMoneyReceiptDate()!=null){
				map.put("moneyReceiptDate", paymentDetailDto.getMoneyReceiptDate());
			}
			if(paymentDetailDto.getMoneyReceiptNumber()!=null && paymentDetailDto.getMoneyReceiptNumber()!=""){
				map.put("moneyReceiptNumber", paymentDetailDto.getMoneyReceiptNumber());
			}
			
			int count=updateByJpql(map, "paymentDetailId", paymentDetailDto.getPaymentDetailId());
			if(count>0)
			{
				response.setHasError(false);
				if(ContextConstant.USER_TYPE_FINANCE_ADMIN.equals(role)){
					if(paymentDetailDto.getIsFAApproved().equals(AppBaseConstant.APPROVED_STATUS)){
						if(null!=paymentDetail && AppBaseConstant.RENEWAL_FEE.equals(paymentDetail.getPaymentType().getCode())){
						  updateRenewalValidity(paymentDetail);
						}
						response.setMessage("Payment Approved !");
					}else if(paymentDetailDto.getIsFAApproved().equals(AppBaseConstant.REJECTED_STATUS)){
						response.setMessage("Payment Rejected !");
					}
				}
				if(ContextConstant.USER_TYPE_FINANCE_OPERATOR.equals(role)){
                    if(paymentDetailDto.getIsFOApproved().equals(AppBaseConstant.APPROVED_STATUS)){
                    	response.setMessage("Payment Approved !");
					}else if(paymentDetailDto.getIsFOApproved().equals(AppBaseConstant.REJECTED_STATUS)){
						response.setMessage("Payment Rejected !");
					}
				}
			}			
		}
		else{
			response.setHasError(true);
			response.setMessage("Invalid Action");	
		}
		return response;
	}
	private void updateRenewalValidity(PaymentDetailDto paymentDetail){
		if(paymentDetail.getPartner()!=null && paymentDetail.getIsCEApproved()==null && AppBaseConstant.PARTNER_STATUS_COMPLETE.equals(paymentDetail.getPartner().getStatus()) && AppBaseConstant.RENEWAL_FEE.equals(paymentDetail.getPaymentType().getCode())){
			if(AppBaseConstant.TRADER_PAYMENT.equals(paymentDetail.getVendorTypePayment()) && "Y".equals(paymentDetail.getPartner().getIsTrader())){
				Map<String,Object> validityMap=new HashMap<>();
				List<PaymentDetailDto> payments=new ArrayList<>();
				payments.add(paymentDetail);
				/*validityMap=partnerService.updateValidityForPartner(paymentDetail.getPartner(),validityMap,payments);*/
				partnerService.updateByJpql(validityMap,"bPartnerId",paymentDetail.getPartner().getbPartnerId());
			}else if(AppBaseConstant.MANUFACTURER_PAYMENT.equals(paymentDetail.getVendorTypePayment()) && "Y".equals(paymentDetail.getPartner().getIsManufacturer()) && null!=paymentDetail.getPartnerOrg()){
			    partnerOrgService.updatePartnerOrgValidity(paymentDetail.getPartnerOrg());
			}
		}		
	}
/*	private void sendPaymentApprovalMail(String isApproved,String email)
	{
		     RoleDto role=contextService.getDefaultRole();
		     String subject = "",message = "";
		     if(!CommonUtil.isStringEmpty(role.getValue()) && role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
		     {
		    	 if(isApproved.equals("Y"))
		    			 {
				    		subject="Payment Approval";
				    		message="<p>Hi</p><br><p>Your payment has been approved from finance operator.<p><p>Regards,</p><p>MSEDCL</p>";
						 }else if(isApproved.equals("N")){
							 subject="Payment Rejected";
							 message="<p>Hi</p><br><p>Your payment has been rejected from finance operator.<p><p>Regards,</p><p>MSEDCL</p>";
						 }
		     }
		     if(!CommonUtil.isStringEmpty(role.getValue()) && role.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN))
		     {
		    	 if(isApproved.equals("Y"))
		    			 {
				    		subject="Payment Approval";
						    message="<p>Hi</p><br><p>Your payment has been approved from finance admin.<p><p>Regards,</p><p>MSEDCL</p>";
						 }else if(isApproved.equals("N")){
							 subject="Payment Rejected";
							 message="<p>Hi</p><br><p>Your payment has been rejected from finance admin.<p><p>Regards,</p><p>MSEDCL</p>";
						 }
		     }
		    partnerService.sendEmailToPartner(email, subject, message);
        
	}
*/
	@Override
	public ResponseDto sendPaymentStatusMail(PaymentDetailDto paymentDetailDto,RoleDto roleDto,UserDto user) {
		String message="";
		try{
		MailTemplateDto mailTemplate=null;
		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	Map<String, Object> codeMap=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		UserDto internalUser=userService.findDto("getUserByRoleCode", codeMap);
		Map<String,Object> map=new HashMap<>();
		if(paymentDetailDto.getPaymentType()!=null && paymentDetailDto.getPaymentType().getCode()!=null && (paymentDetailDto.getPaymentType().getCode().equals(AppBaseConstant.REGISTRATION_FEE) || paymentDetailDto.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE))){
			if(paymentDetailDto.getVendorTypePayment()!=null && paymentDetailDto.getVendorTypePayment().equals(AppBaseConstant.MANUFACTURER_PAYMENT) && paymentDetailDto.getPartnerOrg()!=null){
			  map.put("@factoryName@,",paymentDetailDto.getPartnerOrg().getName());
			  map.put("@paymentFor@", "factory");
			  map.put("@paymentForName@", paymentDetailDto.getPartnerOrg().getName());
			}else{
			  map.put("@factoryName@,","");
			  map.put("@paymentFor@", "company");
			  map.put("@paymentForName@", paymentDetailDto.getPartner().getName());
			}
			map.put("@paymentType@", paymentDetailDto.getPaymentType().getName());
			if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) ||
					paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					message="Payment Approved";
						if(paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
						  mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PAYMENT_APPROVE_TEMPLATE);
						}
					}else if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("N") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) || 
				paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("N")&& roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					
					message="Payment Rejected";
					mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.PAYMENT_REJECT_TEMPLATE);
				}
		}else if(paymentDetailDto.getPaymentType()!=null && paymentDetailDto.getPaymentType().getCode()!=null && paymentDetailDto.getPaymentType().getCode().equals(AppBaseConstant.TENDER_PURCHASE_FEE)){
			 map.put("@factoryName@,","");
			if(paymentDetailDto.getTahdr()!=null && paymentDetailDto.getTahdr().getTahdrCode()!=null){
				map.put("@tenderCode@", paymentDetailDto.getTahdr().getTahdrCode());
			}
			if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) ||
					paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					message="Payment Approved";
						if(paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
						 mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TENDER_PAYMENT_APPROVE_TEMPLATE);
						}
					}else if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("N") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) || 
				paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("N")&& roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					
					message="Payment Rejected";
					mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.TENDER_PAYMENT_REJECT_TEMPLATE);
				}
		}else if(paymentDetailDto.getPaymentType()!=null && paymentDetailDto.getPaymentType().getCode()!=null && paymentDetailDto.getPaymentType().getCode().equals(AppBaseConstant.EMD)){
			 map.put("@factoryName@,","");
			if(paymentDetailDto.getTahdr()!=null && paymentDetailDto.getTahdr().getTahdrCode()!=null){
				map.put("@tenderCode@", paymentDetailDto.getTahdr().getTahdrCode());
			}
			map.put("@paymentType@", paymentDetailDto.getPaymentType().getName());
			if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) ||
					paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					message="Payment Approved";
						if(paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("Y") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
						 mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.EMD_PAYMENT_APPROVE_TEMPLATE);
						}
					}else if((paymentDetailDto.getIsFOApproved()!=null && paymentDetailDto.getIsFOApproved().equals("N") && roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR)) || 
				paymentDetailDto.getIsFAApproved()!=null && paymentDetailDto.getIsFAApproved().equals("N")&& roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
					
					message="Payment Rejected";
					mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.EMD_PAYMENT_REJECT_TEMPLATE);
				}
		}
		
		if(roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_OPERATOR))
		{
			 map.put("@approver@",roleDto.getName());
		}else if(roleDto.getValue().equals(ContextConstant.USER_TYPE_FINANCE_ADMIN)){
		     map.put("@approver@",roleDto.getName());
		}
		if(internalUser!=null && mailTemplate!=null){
			map.put("@subject@",mailTemplate.getSubject());
			map.put("@sourceCompanyName@",internalUser.getPartner().getName());
			map.put("@honour@","");
			map.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());
			map.put("@emailCorrCode@","");
			map.put("@today@",today);
			map.put("@companyName@",user.getPartner().getName());
			map.put("@factoryCity@","");
			map.put("@vendorEmail@",user.getEmail());
			map.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
			map.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
			map.put("@companytel@",internalUser.getUserDetails().getTelephone1());
			map.put("@companyfax@",internalUser.getUserDetails().getFax1());
			map.put("@tenderAuthority@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			mailService.sentMailByTemplate(mailTemplate,map,user.getEmail());
		}
		}catch(Exception e){
			log.error("Exception "+e);
		}
		return new ResponseDto(false, message);
	}
	
	@Override
	public void sendPaymentStatusSMS(PaymentDetailDto paymentDetailDto){
		try {
			if(paymentDetailDto != null){
				if(paymentDetailDto.getPaymentType() != null){
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(AppBaseConstant.SMS_PARAMETER_1, paymentDetailDto.getTotal().toString());
					
					if(paymentDetailDto.getPaymentType().getCode().endsWith(AppBaseConstant.REGISTRATION_FEE)){
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Registration%20Fee");
						smsService.sendSMS(paymentDetailDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PAYEMNT_RECEIVED, params);
						
					}else if(paymentDetailDto.getPaymentType().getCode().endsWith(AppBaseConstant.RENEWAL_FEE)){
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Renewal%20Fee");
						smsService.sendSMS(paymentDetailDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PAYEMNT_RECEIVED, params);
						
					}else if(paymentDetailDto.getPaymentType().getCode().endsWith(AppBaseConstant.TENDER_PURCHASE_FEE)){
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Tender%20Fee");
						params.put(AppBaseConstant.SMS_PARAMETER_3, paymentDetailDto.getTahdr().getTahdrCode());
						smsService.sendSMS(paymentDetailDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_TENDER_FEE, params);
						
					}else if(paymentDetailDto.getPaymentType().getCode().endsWith(AppBaseConstant.EMD)){
						params.put(AppBaseConstant.SMS_PARAMETER_2, AppBaseConstant.EMD.replace(" " , "%20"));
						params.put(AppBaseConstant.SMS_PARAMETER_3, paymentDetailDto.getTahdr().getTahdrCode());
						smsService.sendSMS(paymentDetailDto.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_TENDER_FEE, params);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
