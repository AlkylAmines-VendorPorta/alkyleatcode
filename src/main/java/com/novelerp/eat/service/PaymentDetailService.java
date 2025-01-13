/**
 * @author Ankush
 */
package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

public interface PaymentDetailService extends CommonService<PaymentDetail, PaymentDetailDto> {

	/**
	 * @param tahdrDetailId
	 * @return
	 */
	public PaymentDetailDto getTahdrPaymentDetailsByChargeCodeQuery(Long tahdrDetailId);
	
	/**
	 * @param paymentDetail
	 * @return
	 */
    public PaymentDetailDto savePartnerOrgPaymentDetail(PaymentDetailDto paymentDetail);
	
	/**
	 * @param paymentDetail
	 * @return
	 */
	public PaymentDetailDto purchaseTahdrDoc(PaymentDetailDto paymentDetail);
	
	public PaymentDetailDto purchaseTahdrDoc(PaymentDetailDto paymentDetail,String paymentType);

	/**
	 * @param paymentDetail
	 * @return
	 */
	public PaymentDetailDto emdPayment(PaymentDetailDto paymentDetail);
	/**
	 * @param paymentDetail
	 * @return
	 */
	public PaymentDetailDto saveOnlineEmdPaymentDetail(PaymentDetailDto paymentDetail);


	public PaymentDetailDto savePartnerOrgOnlinePaymentDetail(PaymentDetailDto paymentDetail);
	/**
	 * @param tahdrDetailId
	 * @param chargeCode
	 * @return
	 */
	public PaymentDetailDto getTahdrPaymentDetailsByChargeCodePartnerQuery(Long tahdrDetailId, String chargeCode);
	/**
	 * 
	 */
	/*public List<PaymentDetailDto> getPaymentsForApproval();*/
	
	public List<PaymentDetailDto> getPaymenDetails(Long partnerId);
	
	public boolean updatePaymentDetail(Long partnerOrgId,Long paymentDetailId);

	/**
	 * @param docNo
	 * @return PaymentDetailDto
	 */
	
	public PaymentDetailDto getPaymentDetailsByDocNO(String docNo);

	/**
	 * @param paymentDetaildID
	 * @param status
	 * @return
	 */
	public int updatePaymentStatus(Long paymentDetaildID, String status);
	/*public List<PaymentDetailDto> getPaymentByStatus(String paymentStatus);*/
    public PaymentDetailDto updateOnlinePaymentStatus(PaymentDetailDto dto);
    public long getPaymentStatusCount(String paymentStatus,String searchColumn, String searchValue,String locationType,String officeLocation);
    public List<PaymentDetailDto> getPaymentByStatus(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue,String locationType,String officeLocation);
    public List<PaymentDetailDto> getPaymentsForApproval(Map<String, Object> map, int pageNumber, int pageSize,String searchColumn, String searchValue,String locationType,String officeLocation);
    public List<PaymentDetailDto> getPaymentsForPosting(Map<String, Object> map, int pageNumber, int pageSize, String searchColumn, String searchValue);
	public long getPaymentsForPostingCount(String searchColumn, String searchValue, String showPushedData);
	public ResponseDto postPaymentDetails(Object[] result);
	public Object[] getPaymentANdMISDetail(Long paymentDetailId);
	public PaymentDetailDto savePaymentDetail(PaymentDetailDto dto);
	
	public List<PaymentDetailDto> searchPaymentDetails(Long tahdrId,Long fromDate,Long toDate,String appStatus);
	
	public List<PaymentDetailDto> getPaymentDetails(Long tahdrId,Long fiscalYearId,Long paymentTypeId,Long fromDate,Long toDate,String appStatus);

}
