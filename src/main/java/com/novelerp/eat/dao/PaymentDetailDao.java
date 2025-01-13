/**
 * @author Ankush
 */
package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

public interface PaymentDetailDao extends CommonDao<PaymentDetail, PaymentDetailDto> {

	/*public String getQueryForPaymentApproval(RoleDto role);*/
	public String getQueryForPaymentDetail(RoleDto role);
	public int updatePaymentDetail(Long partnerOrgId,Long paymentDetailId);
	public String getQueryForDocNo();
	/*public String getPaymentsByStatus(RoleDto role);*/
	public String getQueryForPaymentReceipt(String paymentType,String role);
	public long getPaymentApprovalCountQry(String parameterFlag,RoleDto role,String searchColumn,String searchValue,String locationType,String officeLocation,String paymentStatus);
	public String getQueryForPaymentApproval(RoleDto role,String searchColumn, String searchValue,String locationType,String officeLocation);
	public String getPaymentsByStatus(RoleDto role,String searchColumn, String searchValue,String locationType,String officeLocation);
	public String getQueryForPaymentPosting(String searchColumn, String searchValue);
	public String getQueryForPaymentPostingCount(String searchColumn, String searchValue);
	public Object[] getUnPushedPayments(Long paymentDetailId);
	public int upDatePushedPaymentDetails(List<Long> ids);
	
	}
