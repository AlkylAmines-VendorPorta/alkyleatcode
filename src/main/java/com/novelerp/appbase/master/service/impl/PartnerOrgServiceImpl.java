package com.novelerp.appbase.master.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerOrgDao;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.PartnerItemManufacturerService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.LocationService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.MailService;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.service.PaymentDetailService;
/**
 * 
 * @author Aman
 */
@Service
public class PartnerOrgServiceImpl extends AbstractContextServiceImpl<PartnerOrg, PartnerOrgDto> implements PartnerOrgService{

	@Autowired
	private PartnerOrgDao  partnerOrgDao;
	
		
	@Autowired
	private LocationService locationService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	private MailService mailService;
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	private PartnerItemManufacturerService partnerItemManufacturerService;
	@PostConstruct
	public void init(){
		super.init(PartnerOrgServiceImpl.class, partnerOrgDao, PartnerOrg.class, PartnerOrgDto.class);
		/*setObjectConverter(partnerOrgConverter);*/
		setByPassProxy(true);
	}	
		
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgDto save(PartnerOrgDto dto) {
		LocationDto location = dto.getLocation();
		if(location!=null){
			location = locationService.save(location);
			dto.setLocation(location);
		}
		return super.save(dto);
	}

	@Override
	@Transactional
	public PartnerOrgDto updateDto(PartnerOrgDto dto) {
		LocationDto location = dto.getLocation();
		if(location!=null){
			location = locationService.updateDto(location);
			dto.setLocation(location);
		}
		return super.updateDto(dto);
	}

	@Override
	public List<PartnerOrgDto> getPartnerOrgs(Long bpartnerId) {
		RoleDto role=contextService.getDefaultRole();	
		if(role==null || role.getValue()==null)
		{
			return null;
		}
		Map<String, Object> params=getParameterMap(role,bpartnerId);
		String query=partnerOrgDao.getPartnerOrgsQuery(role);
		List<PartnerOrgDto> orgs=findDtosByQuery(query, params);
		return orgs;
	}
	private Map<String,Object> getParameterMap(RoleDto role,Long bpartnerId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("partnerId", bpartnerId);
		if(role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER) || role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER))
		 {
			params.put("isFAApproved", AppBaseConstant.APPROVED_STATUS);
		 }
		return params;
	}

	@Override
	public PartnerOrg getPartnerOrg(Long partnerOrgId) {
		Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		return findEntityByMethod("getQueryForOrgApprovalCheck", param);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgDto savePartnerOrg(PartnerOrgDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true,"Session Timeout...Please Login"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
		}
		if(dto!=null && dto.getPartnerOrgId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			  map.put("validTo", null);
			}else if(dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
				  if(dto.getIsCEApproved().equals(AppBaseConstant.APPROVED_STATUS))
					{
					    Calendar cal = Calendar.getInstance();
	                    if(((dto.getIsRenewed()!=null && dto.getIsRenewed().equals("Y"))||dto.getIsRenewed()==null) && dto.getValidTo()==null)
					    {   
					    	cal.add(Calendar.YEAR, 1);
					    	map.put("validTo", new Timestamp(cal.getTimeInMillis()));
					    }else if(dto.getPaymentDetail()!=null && dto.getPaymentDetail().getPaymentDetailId()!=null  && dto.getPaymentDetail().getPaymentType()!=null && dto.getPaymentDetail().getPaymentType().getCode()!=null 
					    		&& dto.getPaymentDetail().getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE) 
					    		&& (dto.getPaymentDetail().getIsCEApproved()==null || dto.getPaymentDetail().getIsCEApproved().equals("N"))){
					    	/* dto.getIsRenewed()!=null && dto.getIsRenewed().equals("N") && */
					    	cal.setTime(dto.getValidTo());
					    	int year=DateUtil.getYearsDiff(new Date(),dto.getValidTo());
					    	if(year==0){
					    		year=1;
					    	}
					    	cal.add(Calendar.YEAR, year);
					    	map.put("validTo", new Timestamp(cal.getTimeInMillis()));
					    	map.put("isRenewed","Y");
					    	Map<String,Object> param=AbstractServiceImpl.getParamMap("isCEApproved","Y");
							paymentDetailService.updateColumns(param,"paymentDetailId", dto.getPaymentDetail().getPaymentDetailId());
					    }
					}
			}
			
			int result= updateByJpql(map, "partnerOrgId", dto.getPartnerOrgId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		
		if(dto.getPartnerOrgId()==null){
			return save(dto);	 
		}
		return updateDto(dto);
	}
   @Transactional(propagation=Propagation.REQUIRED)
   private boolean updatePaymentDetail(PartnerOrgDto partnerOrg)
   {
	   if(partnerOrg!=null && partnerOrg.getPartnerOrgId()!=null && partnerOrg.getPaymentDetail().getPaymentDetailId()!=null)
	   {
		 return paymentDetailService.updatePaymentDetail(partnerOrg.getPartnerOrgId(), partnerOrg.getPaymentDetail().getPaymentDetailId());
	   }
	   return false;
   }

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto deleteOrg(Long partnerOrgId, Long paymentId) {
		Map<String, Object> map=AbstractServiceImpl.getParamMap("partnerOrg", null);
		int count=paymentDetailService.updateByJpql(map, "paymentDetailId", paymentId);
		if(count>0)
		{
		    boolean deleted = deleteById(partnerOrgId);
			if(deleted){
				return new ResponseDto(false, "Record Deleted");
			}
		}
		return new ResponseDto(true,"Problem in deleting record");
	}
    
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateOrgForRenewal(PartnerOrgDto dto){
		Map<String, Object> param=AbstractServiceImpl.getParamMap("isRenewed","N");
		if(dto!=null && dto.getPartnerOrgId()!=null){
		int count=updateByJpql(param,"partnerOrgId",dto.getPartnerOrgId());
		if(count>0)
		{
			return true;
			/*String sub="Factory Expired";
			String email=dto.getCreatedBy().getEmail();
			String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String content="<p>Hi</p><br><p>Your "+dto.getName()+" factory has been expired on "+date+".</p><p>Regards,</p><p>MSEDCL</p>";
			partnerService.sendEmailToPartner(email, sub, content);*/
		}
	   }
	   return false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOrgForRenewalPayment(PartnerOrgDto dto) {
		if(dto!=null && dto.getPartnerOrgId()!=null && dto.getPaymentDetail()!=null && dto.getPaymentDetail().getPaymentGatewayStatus()!=null 
				&& dto.getPaymentDetail().getPaymentGatewayStatus().equals(AppBaseConstant.PAYMENT_SUCCESS_STATUS)
				&& dto.getPaymentDetail().getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE))
				{
					Map<String, Object> map=AbstractServiceImpl.getParamMap("paymentDetail.paymentDetailId",dto.getPaymentDetail().getPaymentDetailId());
					updateByJpql(map, "partnerOrgId", dto.getPartnerOrgId());
				}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerOrgDto saveManufactureOrg(PartnerOrgDto dto) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
			if(dto.getPartnerItemManufacturer()!=null && dto.getPartnerItemManufacturer().getPartnerItemManufacturerId()!=null)
			{
			  Map<String, Object> map=AbstractServiceImpl.getParamMap("isEEApproved", null);
			  map.put("isCEApproved", null);
			  map.put("eeComment", null);
			  map.put("ceComment", null);
			  partnerItemManufacturerService.updateByJpql(map, "partnerItemManufacturerId", dto.getPartnerItemManufacturer().getPartnerItemManufacturerId());
			}
		}
		if(dto!=null && dto.getPartnerOrgId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= updateByJpql(map, "partnerOrgId", dto.getPartnerOrgId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgId()==null){
			return save(dto);
		}
		return updateDto(dto);
	
	}

	@Override
	public void sendMailOnFactoryExpired(PartnerOrgDto dto,UserDto internalUser) {
		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.FACTORY_EXPIRED_TEMPLATE);
        if(mailTemplate!=null && internalUser!=null)
        {
        	Map<String,Object> map=new HashMap<>();
        	map.put("@sourceCompanyName@",internalUser.getPartner().getName());
			map.put("@honour@","");
			map.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());
			map.put("@emailCorrCode@","");
			map.put("@today@",today);
			map.put("@companyName@",dto.getPartner().getName());
			map.put("@factoryName@,",dto.getName());
			map.put("@factoryCity@",dto.getLocation().getCity());
			map.put("@vendorEmail@",dto.getCreatedBy().getEmail());
			map.put("@expiryDate@", today);
			map.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
			map.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
			map.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
			map.put("@companytel@",internalUser.getUserDetails().getTelephone1());
			map.put("@companyfax@",internalUser.getUserDetails().getFax1());
			mailService.sentMailByTemplate(mailTemplate,map,dto.getCreatedBy().getEmail());
        }

		
	}

	@Override
	public void sendMailOnFactoryRenewalReminder(PartnerOrgDto partnerOrg, UserDto internalUser) {
		MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.FACTORY_RENEWAL_REMINDER_TEMPLATE);
		String validTo=new SimpleDateFormat("dd-MM-yyyy").format(partnerOrg.getValidTo());
		if(mailTemplate!=null  && internalUser!=null){
		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Map<String, Object> params=new HashMap<>();
		params.put("@sourceCompanyName@",internalUser.getPartner().getName());
		params.put("@honour@","");
		params.put("@address@",internalUser.getUserDetails().getLocation().getAddress1());
		params.put("@emailCorrCode@","Renewal");
		params.put("@today@",today);
		params.put("@companyName@",partnerOrg.getPartner().getName());
		params.put("@factoryName@",partnerOrg.getName());
		params.put("@factoryCity@",partnerOrg.getLocation().getCity());
		params.put("@vendorEmail@",partnerOrg.getCreatedBy().getEmail());
		params.put("@sourcingcompanyshortname@",internalUser.getPartner().getValue());
		params.put("@expiryDate@",validTo);
		params.put("@respectiveDeptEmail@",internalUser.getUserDetails().getFirstName()+" "+internalUser.getUserDetails().getLastName());
		params.put("@respectiveDeptContact@",internalUser.getUserDetails().getEmail()+","+internalUser.getUserDetails().getMobileNo());
		params.put("@companytel@",internalUser.getUserDetails().getTelephone1());
		params.put("@companyfax@",internalUser.getUserDetails().getFax1());
		mailService.sentMailByTemplate(mailTemplate, params, partnerOrg.getCreatedBy().getEmail());
	   }

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto updatePartnerOrgValidity(PartnerOrgDto dto) {
		Map<String,Object> map=new HashMap<>();
		Calendar cal = Calendar.getInstance();
        if(((dto.getIsRenewed()!=null && dto.getIsRenewed().equals("Y"))||dto.getIsRenewed()==null) && dto.getValidTo()==null)
	    {   
	    	cal.add(Calendar.YEAR, 1);
	    	map.put("validTo", new Timestamp(cal.getTimeInMillis()));
	    	map.put("isRenewed","Y");
	    }else if(dto.getPaymentDetail()!=null && dto.getPaymentDetail().getPaymentDetailId()!=null  && dto.getPaymentDetail().getPaymentType()!=null && dto.getPaymentDetail().getPaymentType().getCode()!=null 
	    		&& dto.getPaymentDetail().getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE) 
	    		&& (dto.getPaymentDetail().getIsCEApproved()==null)){
	    	/* dto.getIsRenewed()!=null && dto.getIsRenewed().equals("N") && */
	    	cal.setTime(dto.getValidTo());
	    	int year=DateUtil.getYearsDiff(new Date(),dto.getValidTo());
	    	if(year<=0){
	    		year=1;
	    	}
	    	cal.add(Calendar.YEAR, year);
	    	map.put("validTo", new Timestamp(cal.getTimeInMillis()));
	    	map.put("isRenewed","Y");
	    	Map<String,Object> param=AbstractServiceImpl.getParamMap("isCEApproved","Y");
			paymentDetailService.updateByJpql(param,"paymentDetailId", dto.getPaymentDetail().getPaymentDetailId());
	    }
        if(map.isEmpty()){
        	return new ResponseDto(false,"Factory Renewal Data Not Found");
        }
        int result= updateByJpql(map,"partnerOrgId",dto.getPartnerOrgId());
		if(result>0)
		{
			return new ResponseDto(false, "Record Updated");
		}else{
			return new ResponseDto(true, "Error in Updated");
		}
        
	}
	
}