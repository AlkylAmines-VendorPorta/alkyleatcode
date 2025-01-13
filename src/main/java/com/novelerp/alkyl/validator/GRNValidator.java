package com.novelerp.alkyl.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;

@Component
public class GRNValidator implements Validator{

	@Autowired
	private ValidationUtil validationUtil;
	
	@Autowired
	private AdvanceShipmentNoticeService advanceShipmentNoticeService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	public void validateGrnDetails(AdvanceShipmentNoticeDto advanceShipmentNotice, Errors errors) throws ParseException{
		AdvanceShipmentNoticeDto asn=advanceShipmentNoticeService.findDto("getASNByASNId", 
				AbstractContextServiceImpl.getParamMap("asnId", advanceShipmentNotice.getAdvanceShipmentNoticeId()));
		
		
		 Date poDate=asn.getPo().getDate();
			
		 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

		 Date newpoDate = formatter.parse(formatter.format(poDate));
	 
		 Date postingDate=formatter1.parse(advanceShipmentNotice.getPostingDate());  
		 
         Date yesterdayDate=DateUtils.addDays(new Date(), -1);
		 
		 Date newyesterdayDate = formatter.parse(formatter.format(yesterdayDate));
		 
		 String lastdateoflastMonth= LocalDate.now().withDayOfMonth(1).minusDays(1).toString();
		 Date lastDate=formatter1.parse(lastdateoflastMonth);
		 String firstdateofcurrMonth=LocalDate.now().withDayOfMonth( 1 ).toString();
		 Date firstDate=formatter1.parse(firstdateofcurrMonth);
		 String seconddateofcurrMonth=LocalDate.now().withDayOfMonth( 2 ).toString();
		 Date secondDate=formatter1.parse(seconddateofcurrMonth);
		 String thirddateofcurrMonth=LocalDate.now().withDayOfMonth( 3 ).toString();
		 Date thirdDate=formatter1.parse(thirddateofcurrMonth);
		 Date today=new Date();
		 Date todayDate=formatter.parse(formatter.format(today));
		 
//		 if(postingDate.before(newyesterdayDate)) {
//		
//			 validationUtil.reject(errors, "Posting Date Error", "GRN is not allowed for more than yesterday date");
//		 }
		 
		 if(todayDate.equals(secondDate) || todayDate.equals(firstDate) || todayDate.equals(thirdDate)) {
			 if(postingDate.before(newyesterdayDate) && (!postingDate.equals(lastDate) && !postingDate.equals(firstDate) && !postingDate.equals(secondDate))) {
				
				 validationUtil.reject(errors, "Posting Date Error", "Posting Date is not allowed before 2 days");
			 }
			 }
			 else if(postingDate.before(newyesterdayDate)) {
				
				 validationUtil.reject(errors, "Posting Date Error", "Posting Date is not allowed before 2 days");
			 }
		
		 if(postingDate.before(newpoDate)) {
			 validationUtil.reject(errors, "Posting PO Date Error", "Posting Date Should be Greater than PO Date");
			
		 }
		
		for(AdvanceShipmentNoticeLineDto asnLine : advanceShipmentNotice.getAsnLineList()){
			double totalQTY=asnLine.getDeliveryQuantity();
			double storageQTY=0;
			/*if(CommonUtil.isStringEmpty(asnLine.getStorageLocation())){
				validationUtil.reject(errors, "EmptyStorageLocation", "ADD Stroage Location");
			}*/
			if(null==asnLine.getAsnLineCostCenter()){
				validationUtil.reject(errors, "EmptyStorageLocation", "ADD Stroage Location");
			}
			for(ASNLineCostCenterDto dto:asnLine.getAsnLineCostCenter()){
				storageQTY =storageQTY+Double.parseDouble(dto.getQuantity());
			}
//			if(totalQTY!=storageQTY){
//				validationUtil.reject(errors, "MissMatch", "Storage LOcation Quantity Should be equal to Deliver Quantity");
//			}
			
		}
		{/*if(asn.getIsQCPassed().equals("N")){
			validationUtil.reject(errors, "QCNotPassed", "QC Not Passed");
		}
		
		if(asn.getIsUnload().equals("N")){
			
			validationUtil.reject(errors, "UnoladNotDone", "Unload Not Done");
		}*/}
		
		
		
	}
	
	
	public void validateSSNDetails(AdvanceShipmentNoticeDto asn, Errors errors) throws ParseException{
//		AdvanceShipmentNoticeDto asn=advanceShipmentNoticeService.findDto("getASNByASNId", 
//				AbstractContextServiceImpl.getParamMap("asnId", advanceShipmentNotice.getAdvanceShipmentNoticeId()));
		
		User loggedinUser = new User();
		loggedinUser.setUserId(contextService.getUser().getUserId());
		loggedinUser.setUserName(contextService.getUser().getUserName());

		String sapUserID = asn.getPo().getUserID();
		List<String> list = Arrays.asList(sapUserID.split(","));
		
		if (!list.contains(loggedinUser.getUserName())) {
			if (asn.getInvoiceDate() == null || asn.getInvoiceNo().equals("")) {
				validationUtil.reject(errors, "Invoice Error", "Please fill Invoice No and Date");
				//return new CustomResponseDto(false, "Please fill Invoice No and Date");
			} else if ((asn.getInvoiceDate().before(asn.getPo().getDate()))) {
				validationUtil.reject(errors, "Invoice PO Error", "Invoice Date Should be greater than PO Date");
	             //return new CustomResponseDto(false, "Invoice Date Should be greater than PO Date");
			}
		}

		 
		 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		 Date yesterdayDate=DateUtils.addDays(new Date(), -1);
		 
		 Date postingDate=formatter.parse(formatter.format(asn.getServicePostingDate()));  
		 
		 Date newyesterdayDate = formatter.parse(formatter.format(yesterdayDate));
		 
		 String lastdateoflastMonth= LocalDate.now().withDayOfMonth(1).minusDays(1).toString();
		 Date lastDate=formatter1.parse(lastdateoflastMonth);
		 String firstdateofcurrMonth=LocalDate.now().withDayOfMonth( 1 ).toString();
		 Date firstDate=formatter1.parse(firstdateofcurrMonth);
		 String seconddateofcurrMonth=LocalDate.now().withDayOfMonth( 2 ).toString();
		 Date secondDate=formatter1.parse(seconddateofcurrMonth);
		 String thirddateofcurrMonth=LocalDate.now().withDayOfMonth( 3 ).toString();
		 Date thirdDate=formatter1.parse(thirddateofcurrMonth);
		 Date today=new Date();
		 Date todayDate=formatter.parse(formatter.format(today));
		
		
		if (asn.getServicePostingDate() == null) {
			validationUtil.reject(errors, "Posting Error", "Please Select Posting Date");
			//return new CustomResponseDto(false, "Please Select Posting Date");
		}
		else if(asn.getServicePostingDate().before(asn.getPo().getDate())) {
			validationUtil.reject(errors, "Posting PO Error", "Posting date is less than PO date please check");
			//return new CustomResponseDto(false, "Posting date is less than PO date please check");
		}
		
		 if(todayDate.equals(secondDate) || todayDate.equals(firstDate) || todayDate.equals(thirdDate)) {
			 if(postingDate.before(newyesterdayDate) && (!postingDate.equals(lastDate) && !postingDate.equals(firstDate) && !postingDate.equals(secondDate))) {
				 validationUtil.reject(errors, "Posting Date Error", "Posting Date is not allowed before 2 days");
				// return new CustomResponseDto(false,"Posting Date is not allowed before 2 days");
			 }
			 }
			 else if(postingDate.before(newyesterdayDate)) {
				 validationUtil.reject(errors, "Posting Date Error", "Posting Date is not allowed before 2 days");
				// return new CustomResponseDto(false, "Posting Date is not allowed before 2 days");
			 }
		 

	
	}

}
