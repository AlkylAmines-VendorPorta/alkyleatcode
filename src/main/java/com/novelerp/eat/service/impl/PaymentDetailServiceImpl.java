/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import java.math.BigDecimal;
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

import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.converter.PaymentTahdrDetailConverter;
import com.novelerp.eat.dao.PaymentDetailDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.PaymentDetail;
import com.novelerp.eat.msedcl.paymentposting.PaymentPostingClient;
import com.novelerp.eat.msedcl.paymentposting.ZFIETENDER;
import com.novelerp.eat.msedcl.paymentposting.ZFIETENDERResponse;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.TAHDRService;

@Service
public class PaymentDetailServiceImpl extends AbstractContextServiceImpl<PaymentDetail, PaymentDetailDto>
		implements PaymentDetailService {

	@Autowired
	private PaymentDetailDao paymentDao;

	@Autowired
	private PaymentTypeService paymentTypeService;

	@Autowired
	private TAHDRService tahdrService;

	@Autowired
	private PaymentTahdrDetailConverter paymentTahdrDetailConverter;

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@Autowired
	private PaymentPostingClient paymentPostingClient;
	
	@Autowired
	private FinancialYearService financialyear;
	
	@PostConstruct
	private void init() {
		super.init(PaymentDetailServiceImpl.class, paymentDao, PaymentDetail.class, PaymentDetailDto.class);
		setByPassProxy(true);
	}

	@Override
	public PaymentDetailDto purchaseTahdrDoc(PaymentDetailDto paymentDetail) {
		paymentDetail.setPaymentType(paymentTypeService.findDto("getPaymentTypeByCode",
				getParamMap("paymentTypeCode", AppBaseConstant.TENDER_PURCHASE_FEE)));
		TAHDRDto tender=tahdrService.findDto("getQueryForTAHDRByIdActiveTahdrDetail",
				AbstractContextServiceImpl.getParamMap("tahdrId", paymentDetail.getTahdr().getTahdrId()));
		paymentDetail.setTahdr(tender);
		paymentDetail.setTahdrDetail(paymentDetail.getTahdr().getTahdrDetail().iterator().next());
		if(paymentDetail.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setIsFAApproved("Y");
		}
		boolean isTenderFeesFree=tender!=null && tender.getTahdrDetail()!=null && tender.getTahdrDetail().size()!=0?tender.getTahdrDetail().iterator().next().getEstimatedCost().intValue()==0:false;
		if(isTenderFeesFree){
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setIsFAApproved("Y");
		}
		return paymentDetail;
	}
	
	public PaymentDetailDto purchaseTahdrDoc(PaymentDetailDto paymentDetail,String paymentType) {
		paymentDetail.setPaymentType(paymentTypeService.findDto("getPaymentTypeByCode",
				getParamMap("paymentTypeCode", paymentType)));
		TAHDRDto tender=tahdrService.findDto("getQueryForTAHDRByIdActiveTahdrDetail",
				AbstractContextServiceImpl.getParamMap("tahdrId", paymentDetail.getTahdr().getTahdrId()));
		paymentDetail.setTahdr(tender);
		paymentDetail.setTahdrDetail(paymentDetail.getTahdr().getTahdrDetail().iterator().next());
		if(paymentDetail.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setIsFAApproved("Y");
		}
		boolean isTenderFeesFree=tender!=null && tender.getTahdrDetail()!=null && tender.getTahdrDetail().size()!=0?tender.getTahdrDetail().iterator().next().getEstimatedCost().intValue()==0:false;
		if(isTenderFeesFree){
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setIsFAApproved("Y");
		}
		return paymentDetail;
	}

	@Override
	public PaymentDetailDto emdPayment(PaymentDetailDto paymentDetail) {
		paymentDetail.setPaymentType(paymentTypeService.findDto("getPaymentTypeByCode",
				getParamMap("paymentTypeCode", AppBaseConstant.EMD)));
		paymentDetail.setTahdr(tahdrService.findDto("getQueryForTAHDRById",
				AbstractContextServiceImpl.getParamMap("tahdrId", paymentDetail.getTahdr().getTahdrId())));
		paymentDetail.setTahdrDetail(paymentDetail.getTahdr().getTahdrDetail().iterator().next());
		if(paymentDetail.getPaymentMode().equalsIgnoreCase(AppBaseConstant.PAYMENT_MODE_ISEXEMP)){
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setIsFAApproved("Y");
		}
		return paymentDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.novelerp.appcontext.service.impl.AbstractContextServiceImpl#save(com.
	 * novelerp.appcontext.dto.CommonContextDto)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PaymentDetailDto save(PaymentDetailDto dto) {
		if (null == dto.getPaymentDetailId() || !(dto.getPaymentDetailId() > 0)) {
			return super.save(dto);
		} else {
			return super.updateDto(dto);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean beforeSave(PaymentDetailDto dto) {
		BPartnerDto partner = contextService.getPartner();
		if(!dto.isVendorPayment()){
		try {
			if (dto.getPaymentType().getCode().equals(AppBaseConstant.TENDER_PURCHASE_FEE)) {
				dto.setAmount(dto.getTahdrDetail().getTahdrFees());
			} else if (dto.getPaymentType().getCode().equals(AppBaseConstant.EMD)) {
				dto.setAmount(dto.getTahdrDetail().getEmdFee());
			} 
			dto.setGstAmount(dto.getPaymentType().getGst().multiply(dto.getAmount()).divide(new BigDecimal(100)));
			dto.setGst(dto.getPaymentType().getGst());

			if (partner.isIntraState()) {
				dto.setIgst(null);
				dto.setSgst(dto.getPaymentType().getGst().divide(new BigDecimal(2)));
				dto.setCgst(dto.getPaymentType().getGst().divide(new BigDecimal(2)));
			} else {
				dto.setIgst(dto.getPaymentType().getGst());
				dto.setSgst(null);
				dto.setCgst(null);

			}

			dto.setTotal(dto.getAmount().add(dto.getGstAmount()));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	   }
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novelerp.eat.service.PaymentDetailService#
	 * getTahdrPaymentDetailsByChargeCodeQuery(java.lang.Long)
	 */
	@Override
	public PaymentDetailDto getTahdrPaymentDetailsByChargeCodePartnerQuery(Long tahdrId, String chargeCode) {
		BPartnerDto partner = contextService.getPartner();
		Map<String, Object> map = new HashMap<>();
		map.put("tahdrId", tahdrId);
		map.put("paymentTypeCode", chargeCode);
		map.put("partnerId", partner.getbPartnerId());
		PaymentDetailDto payment = null;
		try {
			payment = findDto("getTahdrPaymentDetailsByChargeCodePartnerQuery", map);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return payment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.novelerp.eat.service.PaymentDetailService#
	 * getTahdrPaymentDetailsByChargeCodeQuery(java.lang.Long)
	 */
	@Override
	public PaymentDetailDto getTahdrPaymentDetailsByChargeCodeQuery(Long tahdrDetailId) {
		Map<String, Object> map = new HashMap<>();
		map.put("tahdrDetailId", tahdrDetailId);
		map.put("paymentTypeCode", AppBaseConstant.TENDER_PURCHASE_FEE);
		PaymentDetailDto payment = findDto("getTahdrPaymentDetailsByChargeCodeQuery", map, paymentTahdrDetailConverter);
		return payment;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaymentDetailDto savePartnerOrgPaymentDetail(PaymentDetailDto paymentDetail) {
		paymentDetail=getPaymentWithTax(paymentDetail);
		RoleDto role = contextService.getDefaultRole();
		if (role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER)
				|| role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())) {
			paymentDetail.setIsFAApproved(null);
			paymentDetail.setIsFOApproved(null);
			paymentDetail.setFaComment(null);
			paymentDetail.setFoComment(null);
			if(paymentDetail.getPaymentMode()!=null && paymentDetail.getPaymentMode().equals(AppBaseConstant.PAYMENT_MODE_DD))
			{
			  paymentDetail.setPaymentGatewayStatus(AppBaseConstant.PAYMENT_SUCCESS_STATUS);
			}			
		}
		if (paymentDetail.getPaymentDetailId() == null) {
			return save(paymentDetail);
		} else {
			return updateDto(paymentDetail);
		}
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaymentDetailDto savePartnerOrgOnlinePaymentDetail(PaymentDetailDto paymentDetail) {

		/*beforeSave(paymentDetail);*/
		paymentDetail=getPaymentWithTax(paymentDetail);
		paymentDetail = saveOnlineRegPaymentDetail(paymentDetail);
		long requestID = paymentDetail.getPaymentDetailId() + 2000000000;
		Map<String, Object> map = new HashMap<>();
		map.put("docNo", requestID);
		updateByJpql(map, "paymentDetailId", paymentDetail.getPaymentDetailId());
		paymentDetail.setDocNo(requestID);
		return paymentDetail;
	}

	protected PaymentDetailDto beforeUpdate(PaymentDetailDto paymentDetail) {
		paymentDetail.setAmount(paymentDetail.getPaymentType().getAmount());
		paymentDetail.setGst(paymentDetail.getPaymentType().getGst().multiply(paymentDetail.getAmount())
				.divide(new BigDecimal(100)));
		paymentDetail.setTotal(paymentDetail.getAmount().add(paymentDetail.getGst()));
		return paymentDetail;
	}

	/*@Override
	public List<PaymentDetailDto> getPaymentsForApproval() {
		RoleDto role = contextService.getDefaultRole();
		Map<String, Object> params = new HashMap<>();
		String query = paymentDao.getQueryForPaymentApproval(role);
		List<PaymentDetailDto> payments = findDtosByQuery(query, params);
		return payments;
	}*/

	@Override
	public List<PaymentDetailDto> getPaymenDetails(Long partnerId) {
		RoleDto role = contextService.getDefaultRole();
		Map<String, Object> params = getParameterMap(partnerId, role);
		String query = paymentDao.getQueryForPaymentDetail(role);
		List<PaymentDetailDto> payments = findDtosByQuery(query, params);
		return payments;
	}

	private Map<String, Object> getParameterMap(Long partnerId, RoleDto role) {
		Map<String, Object> params = new HashMap<>();
		params.put("partnerId", partnerId);
		if (role != null) {
			if (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)
					|| role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				params.put("isFAApproved", AppBaseConstant.APPROVED_STATUS);
			}
		}
		return params;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updatePaymentDetail(Long partnerOrgId, Long paymentDetailId) {
		int count = paymentDao.updatePaymentDetail(partnerOrgId, paymentDetailId);
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PaymentDetailDto saveOnlineEmdPaymentDetail(PaymentDetailDto paymentDetail) {
		long requestID; 
		paymentDetail.setIsApproved("Y");
		paymentDetail.setIsFAApproved("Y");
		paymentDetail.setIsFOApproved("Y");
		paymentDetail.setIsActive("Y");
		paymentDetail.setPaymentDate(new Date());
		paymentDetail = save(paymentDetail);
		requestID = paymentDetail.getPaymentDetailId() + 1000000000;
		paymentDetail.setDocNo(requestID);
		Map<String, Object> map = new HashMap<>();
		map.put("docNo", requestID);
		int result = updateByJpql(map, "paymentDetailId", paymentDetail.getPaymentDetailId());
		return paymentDetail;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PaymentDetailDto saveOnlineRegPaymentDetail(PaymentDetailDto paymentDetail) {
		/*RoleDto role = contextService.getDefaultRole();*/
		/*if (role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER)
				|| role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())) {
			paymentDetail.setIsFAApproved("Y");
			paymentDetail.setIsFOApproved("Y");
			paymentDetail.setFaComment(null);
			paymentDetail.setFoComment(null);
		}*/
		paymentDetail.setPaymentDate(new Date());
		paymentDetail.setIsActive("Y");
		paymentDetail = super.save(paymentDetail);
		return paymentDetail;
	}

	@Override
	public PaymentDetailDto getPaymentDetailsByDocNO(String docNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("docNo", Long.parseLong(docNo));
		String query = paymentDao.getQueryForDocNo();
		return findDtoByQuery(query, param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updatePaymentStatus(Long paymentDetaildID, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("paymentGatewayStatus", status);
		return updateByJpql(map, "paymentDetailId", paymentDetaildID);
	}

	/*@Override
	public List<PaymentDetailDto> getPaymentByStatus(String paymentStatus,pageNumber, pageSize,searchMode, searchValue) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		Map<String, Object> param=AbstractServiceImpl.getParamMap("paymentStatus", paymentStatus);
		String query=paymentDao.getPaymentsByStatus(role);
		List<PaymentDetailDto> payments=findDtosByQuery(query, param);
		return payments;
	}*/

	@Override
	public List<PaymentDetailDto> getPaymentByStatus(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue,String locationType,String officeLocation) {
		RoleDto role=contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		String query=paymentDao.getPaymentsByStatus(role,searchColumn, searchValue,locationType,officeLocation);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<PaymentDetailDto> payments = findDtosByQuery(query, map, pageNumber, pageSize);
		return payments;
	}
	
	@Override
	public List<PaymentDetailDto> getPaymentsForApproval(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue,String locationType,String officeLocation) {
		RoleDto role = contextService.getDefaultRole();
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return null;
		}
		String query = paymentDao.getQueryForPaymentApproval(role,searchColumn, searchValue,locationType,officeLocation);
		if (!"none".equalsIgnoreCase(searchValue)) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<PaymentDetailDto> payments = findDtosByQuery(query, map, pageNumber, pageSize);
		return payments;
	}
	
	@Override
	public List<PaymentDetailDto> getPaymentsForPosting(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue) {
		
		String query = paymentDao.getQueryForPaymentPosting(searchColumn, searchValue);
		if (!("none".equalsIgnoreCase(searchValue))) {
			map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
		} 
		List<PaymentDetailDto> payments = findDtosByQuery(query, map, pageNumber, pageSize);
		return payments;
	}
	@Override
	public long getPaymentsForPostingCount(String searchColumn, String searchValue, String showPushedData) {
		long totalCount;
		Map<String, Object> params = new HashMap<>();
		if (!("none".equalsIgnoreCase(searchValue))) {
			String queryString = paymentDao.getQueryForPaymentPostingCount(searchColumn, searchValue);
			params.put("showPushedData", showPushedData);
			params.put("searchValue", "%" + searchValue.toUpperCase() + "%");
			totalCount = getRecordCount(queryString, params);
		}else{
			String queryString = paymentDao.getQueryForPaymentPostingCount(searchColumn, searchValue);
			params.put("showPushedData", showPushedData);
			totalCount = getRecordCount(queryString, params);

		}
		return totalCount;
	}
	
	@Override
	public Object[] getPaymentANdMISDetail(Long paymentDetailId){
		return paymentDao.getUnPushedPayments(paymentDetailId);
	}
	
	@Override
	@Transactional
	public ResponseDto postPaymentDetails(Object[] result) {
		
		try{
			if (result.length > 0) {
				if ("0".equals(String.valueOf(result[1]))) {
					return new ResponseDto(true, "MIS details not received yet");
				}else{
					String date = DateUtil.getDateString(new Date(), "dd.MM.yyyy");
					String transactionId = String.valueOf(result[1]);
					ZFIETENDER zfietender = new ZFIETENDER();
					zfietender.setBKTXT(transactionId);
					zfietender.setBLDAT(date); // 23.08.2018
					zfietender.setBUDAT(date); // 23.08.2018
					zfietender.setCHARGES(new BigDecimal(String.valueOf(result[2]))); //(new BigDecimal("3.07"));
					zfietender.setGATEWYOWNR("100005346");
					zfietender.setPAYTYPE(String.valueOf(result[4]));
					String profitCenter=result[7]==null?"9950":(String) result[7];
					zfietender.setPRCTR(profitCenter);
					String tenderNo=result[6]==null?"NA":(String) result[6];
					zfietender.setSGTXT(tenderNo); //tendr no 
					if(result[5]!=null){
						zfietender.setVENDOR(String.valueOf(result[5]));
					}else{
						return new ResponseDto(true, "Venodr Code not generated, Please generate vendor first and then post payment.");
					}
					
					zfietender.setWRBTR(new BigDecimal(String.valueOf(result[3])));
					zfietender.setXBLNR(transactionId);
					ZFIETENDERResponse zfietenderResponse = paymentPostingClient.postPayment(zfietender);
					if (zfietenderResponse != null) {
						updatePaymentDetail(Long.parseLong(String.valueOf(result[0])), zfietenderResponse.getLVEXPORT());
						return new ResponseDto(false, "Post Payment Detail Successfully");
					}else{
						return new ResponseDto(true, "Error in posting details to SAP");
					}
				}
			}
			}catch (Exception e) {
				log.error(e.toString());
			}
			return new ResponseDto(true, "");
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	private void updatePaymentDetail(Long paymentDetailId, String lvexport){
		//paymentDao.upDatePushedPaymentDetails(paymentDetailIds);
		Map<String, Object> map = new HashMap<>();
		map.put("isPushed", "Y");
		map.put("lvexport", lvexport);
		updateByJpql(map, "paymentDetailId", paymentDetailId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaymentDetailDto updateOnlinePaymentStatus(PaymentDetailDto dto) {
		if(AppBaseConstant.PAYMENT_SUCCESS_STATUS.equals(dto.getPaymentGatewayStatus()))
		{
			dto.setIsFOApproved("Y");
			dto.setIsFAApproved("Y");
			dto.setFoComment("Online Payment");
			dto.setFaComment("Online Payment");
		}else{
			dto.setIsFOApproved("N");
			dto.setIsFAApproved("N");
			dto.setFoComment("Online Payment");
			dto.setFaComment("Online Payment");
		}
		return updateDto(dto);
	}
	private PaymentDetailDto getPaymentWithTax(PaymentDetailDto dto){
		BPartnerDto partner = contextService.getPartner();
		try { 
			 if (dto.getPaymentType().getCode().equals(AppBaseConstant.REGISTRATION_FEE)) {
			    dto.setAmount(dto.getPaymentType().getAmount());
		     }else if(dto.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE) && dto.getVendorTypePayment().equals(AppBaseConstant.MANUFACTURER_PAYMENT) 
				&& dto.getPartnerOrg()!=null && dto.getPartnerOrg().getPartnerOrgId()!=null && dto.getPartnerOrg().getValidTo()!=null){
				int year=DateUtil.getYearsDiff(new Date(),dto.getPartnerOrg().getValidTo());
				if(year>0){
				  dto.getPaymentType().setAmount(dto.getPaymentType().getAmount().multiply(new BigDecimal(year)));
				}	
			 }else if(dto.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE) && dto.getVendorTypePayment().equals(AppBaseConstant.TRADER_PAYMENT) 
					&& dto.getPartner()!=null && dto.getPartner().getbPartnerId()!=null && partner!=null && partner.getValidTo()!=null && CommonUtil.isEqual(dto.getPartner().getbPartnerId(),partner.getbPartnerId())){
				int year=DateUtil.getYearsDiff(new Date(),partner.getValidTo());
				if(year>0){
					  dto.getPaymentType().setAmount(dto.getPaymentType().getAmount().multiply(new BigDecimal(year)));
					}
			}
		    dto.setAmount(dto.getPaymentType().getAmount());
			dto.setGstAmount(dto.getPaymentType().getGst().multiply(dto.getAmount()).divide(new BigDecimal(100)));
			dto.setGst(dto.getPaymentType().getGst());

			if (partner!=null && partner.isIntraState()) {
				dto.setIgst(null);
				dto.setSgst(dto.getPaymentType().getGst().divide(new BigDecimal(2)));
				dto.setCgst(dto.getPaymentType().getGst().divide(new BigDecimal(2)));
				dto.setSgstAmount(dto.getGstAmount().divide(new BigDecimal(2)));
				dto.setCgstAmount(dto.getGstAmount().divide(new BigDecimal(2)));
			} else {
				dto.setIgst(dto.getPaymentType().getGst());
				dto.setIgstAmount(dto.getGstAmount());
				dto.setSgst(null);
				dto.setCgst(null);
				dto.setSgstAmount(null);
				dto.setCgstAmount(null);
			}
			dto.setTotal(dto.getAmount().add(dto.getGstAmount()));
			dto.setVendorPayment(true);
			return dto;
		}catch(Exception ex) {
			log.error(ex.toString());
			ex.printStackTrace();
			return dto;
		}
	}
	
	@Override
	public long getPaymentStatusCount(String paymentStatus,String searchColumn, String searchValue,String locationType,String officeLocation) {
		long totalCount;
		RoleDto role = contextService.getDefaultRole();
		String parameterFlag=null;
		if(role==null || CommonUtil.isStringEmpty(role.getValue()))
		{
			return 0;
		}
		
		if(!CommonUtil.isStringEmpty(paymentStatus)){
			parameterFlag="Y";
			totalCount =paymentDao.getPaymentApprovalCountQry(parameterFlag,role,searchColumn, searchValue,locationType,officeLocation,paymentStatus);
			/*if (!"none".equalsIgnoreCase(searchValue)) {
				map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				totalCount = getRecordCount(queryString, map);
			}else{
				totalCount = getRecordCount(queryString, map);
			}*/
		}
		else{
			parameterFlag=null;
			totalCount = paymentDao.getPaymentApprovalCountQry(parameterFlag,role,searchColumn, searchValue,locationType,officeLocation,paymentStatus);
			/*if (!"none".equalsIgnoreCase(searchValue)) {
				map.put("searchValue", "%" + searchValue.toUpperCase() + "%");
				totalCount = getRecordCount(queryString, map);
			}else{
				totalCount = getRecordCount(queryString, map);
			}*/
		}
		return totalCount;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaymentDetailDto savePaymentDetail(PaymentDetailDto dto) {
		PaymentDetailDto payment= savePartnerOrgPaymentDetail(dto);
		if(payment.getPartnerOrg()!=null && payment.getPaymentType()!=null && payment.getPaymentType().getCode().equals(AppBaseConstant.RENEWAL_FEE)){
		 payment.getPartnerOrg().setPaymentDetail(payment);
		 partnerOrgService.updateOrgForRenewalPayment(payment.getPartnerOrg());
		}
		return payment;
	}

	@Override
	public List<PaymentDetailDto> searchPaymentDetails(Long tahdrId, Long fromDate, Long toDate, String appStatus) {
		List<PaymentDetailDto> list=null;
		String query = invokeQueryMethod(paymentDao, "getTahdrPaymentDetailsByTahdrId");
		StringBuilder customeQuery=new StringBuilder(query);
		Map<String, Object> params= new HashMap<>();
		params.put("tahdrId", tahdrId);
		if(fromDate!=0 && toDate!=0){
			params.put("fromDate", new Date(fromDate));
			params.put("toDate",  new Date(toDate));
			query=customeQuery.append("  AND pd.created BETWEEN :fromDate AND :toDate ").toString();
		}else if(!appStatus.equals("")){
			params.put("appStatus",  appStatus);
			query=customeQuery.append("  AND pd.isFAApproved=:appStatus ").toString();
		}
		list=findDtosByQuery(query, params, 0, 0);
		return list;
	}
	
	@Override
	public List<PaymentDetailDto> getPaymentDetails(Long tahdrId,Long fiscalYearId,Long paymentTypeId,Long fromDate,Long toDate,String appStatus) {
		List<PaymentDetailDto> list=null;
		String query = "";
		Map<String, Object> params= new HashMap<>();
		if(tahdrId>0){
			params.put("tahdrId", tahdrId);
			query =invokeQueryMethod(paymentDao, "getTahdrPaymentDetailsByTahdrId");
		}else if(fiscalYearId>0){
			FinancialYearDto fiscalYear=financialyear.findDto(fiscalYearId);
			params.put("toDate", fiscalYear.getValidTo());
			params.put("fromDate", fiscalYear.getValidfrom());
			query =invokeQueryMethod(paymentDao, "getTahdrPaymentDetailsByfiscalyear");
		}else if(fromDate!=0 && toDate!=0){
			params.put("fromDate", new Date(fromDate));
			params.put("toDate",  new Date(toDate));
			query =invokeQueryMethod(paymentDao, "getTahdrPaymentDetails");
			StringBuilder customeQuery=new StringBuilder(query);
			query=customeQuery.append("  WHERE pd.created BETWEEN :fromDate AND :toDate ").toString();
		}else if(paymentTypeId>0){
			params.put("paymentTypeId", paymentTypeId);
			query =invokeQueryMethod(paymentDao, "getTahdrPaymentDetailsByPaymentType");
		}else{
			query =invokeQueryMethod(paymentDao, "getTahdrPaymentDetails");
		}
		list=findDtosByQuery(query, params, 0, 0);
		return list;
	}
}
