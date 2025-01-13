package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * 
 * @author varsha
 *
 */
public interface PaymentApprovalService extends CommonService<PaymentDetail, PaymentDetailDto>{
  public List<PaymentDetailDto> getPaymentApprovalList(UserDto userDto);
  public PaymentDetail convertToEntity(PaymentDetailDto paymentDetailDto);
  public PaymentDetailDto convertToDto(PaymentDetail paymentDetail);
  public ResponseDto updatePaymentStatus(PaymentDetailDto paymentDetailDto,String role,UserDto user,PaymentDetailDto dbPayment);
  public ResponseDto sendPaymentStatusMail(PaymentDetailDto dto,RoleDto role,UserDto user);
  public void sendPaymentStatusSMS(PaymentDetailDto paymentDetailDto);
}
