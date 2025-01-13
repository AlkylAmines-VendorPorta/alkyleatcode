package com.novelerp.alkyl.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class ASNValidator implements Validator{


	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private AdvanceShipmentNoticeService advanceShipmentNoticeService;
	
	
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	public void validateAsnDetails(AdvanceShipmentNoticeDto asn, Errors errors){
		
		if(asn==null){
			
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		int count = 0;
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){
			if(asnLine.getDeliveryQuantity()>0){
				count++;	
				}
		}
		if(count>0){
		if(asn.getInvoiceApplicable().equals("Y")){
			
			if(asn.getInvoiceNo()==null || ("").equals(asn.getInvoiceNo()) ){
				
				validationUtil.reject(errors, "InvoiceNoNull", "Invoice No cannot be Null");
			}if(asn.getInvoiceDate()==null){
				
				validationUtil.reject(errors, "InvoiceDateNull", "Invoice Date cannot be Null");
			}
			if(asn.getInvoice()==null){
				
				validationUtil.reject(errors, "InvoiceAttachmentNull", "Invoice Attachment cannot be Empty");
			}else if (asn.getInvoice().getAttachmentId()==null){
					validationUtil.reject(errors, "InvoiceAttachmentNull", "Invoice Attachment cannot be Empty");
			}
		}else{
			if(asn.getDeliveryNoteNo()==null){
				validationUtil.reject(errors, "DeliveryNoteNoNull", "Delivery Note No cannot be Null");
				
			}
			if(asn.getDeliveryNoteDate()==null){
				
				validationUtil.reject(errors, "deliveryNoteDateNull", "Delivery Note Date cannot be Empty");
			}
			if(asn.getInvoice()==null){
				
				validationUtil.reject(errors, "DeliveryNoteAttachmentNull", "Delivery Note Attachment cannot be Empty");
				
			}else if(asn.getInvoice().getAttachmentId()==null){
				
				validationUtil.reject(errors, "DeliveryNoteAttachmentNull", "Delivery Note Attachment cannot be Empty");
			}
		}
		
	}else{
		validationUtil.reject(errors, "DeliveryQunatityNull", "Delivery Quantity cannot be null");
		
	}
		if(asn.getInvoiceAmountByUser()==null){
			validationUtil.reject(errors, "Invoice Amount", "invoice amount cannot be null");
		}else{
			Double a=asn.getInvoiceAmount()-asn.getInvoiceAmountByUser();
			
		if(a<-1){
			validationUtil.reject(errors, "Invoice Amount Missmatch", "Provided invoicee amount not matched");
		}
		if(a>1){
			validationUtil.reject(errors, "Invoice Amount Missmatch", "Provided invoicee amount not matched");
		}
		}
}
	
	
	
public void validateWithoutpoAsnDetails(AdvanceShipmentNoticeDto asn, Errors errors){
		
		if(asn==null){
			
			validationUtil.reject(errors, "Empty Object", "Empty Form");
		}
		int count = 0;
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){
			if(asnLine.getDeliveryQuantity()>0){
				count++;	
				}
			
		}
		
		if(count>0){
			
			if("".equals(asn.getPlant())) {
				validationUtil.reject(errors, "PlantNull", "Please Select Plant");	
//			CustomResponseDto response = new CustomResponseDto(true,
//					"Please Select Plant");
//			
//			return response;
			
		}
			
		}else {
			validationUtil.reject(errors, "DeliveryQunatityNull", "Delivery Quantity cannot be null");	
		}
	
	
}
	
}
