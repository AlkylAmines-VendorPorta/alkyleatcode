package com.novelerp.eat.validator;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.TAHDRDetailDto;

@Component
public class TAHDRDatesValidator implements Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	
	@Override
	public void validate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(TAHDRDetailDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		TAHDRDetailDto tahdrdetailDto =  (TAHDRDetailDto) object;
		
		
		if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
			checkForPublishDatesWithTodaysDate(tahdrdetailDto,errors);
			checkForPublishDates(tahdrdetailDto,errors);
		}
		else if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_APPROVED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
			checkForToDate(tahdrdetailDto,errors);
			checkForSaleDate(tahdrdetailDto,errors);
		}
		else if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_REJECTED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
			return;
		}
		else{
			if(tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N")){
				checkForToDate(tahdrdetailDto,errors);
				checkForBidStartDate(tahdrdetailDto,errors);
				}
				
				checkForSaleDate(tahdrdetailDto,errors);
				checkForBidEndDate(tahdrdetailDto,errors);
				checkForPreBidDate(tahdrdetailDto,errors);
				checkForTechBidOpeningDate(tahdrdetailDto,errors);
				if(tahdrdetailDto.getIsPBDSetLater().equalsIgnoreCase("N")){
					checkForPriceBidOpeningDate(tahdrdetailDto,errors);
				}
				if(tahdrdetailDto.getTahdr()!=null){
					if((tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT) && tahdrdetailDto.getTahdr().getIsAuction().equalsIgnoreCase("Y") 
							|| (tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)) )){
						if(tahdrdetailDto.getIsAuctionDateSetLater()!=null && tahdrdetailDto.getIsAuctionDateSetLater().equalsIgnoreCase("N"))
						{
						checkForAuctionStartDate(tahdrdetailDto,errors);
						checkForAuctionEndDate(tahdrdetailDto,errors);
						}
					}
					
					if(tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
						if(tahdrdetailDto.getIsAnnexureC1()!=null && tahdrdetailDto.getIsAnnexureC1().equalsIgnoreCase("Y")){
							if(tahdrdetailDto.getIsSetC1Later()!=null && tahdrdetailDto.getIsSetC1Later().equalsIgnoreCase("N")){
								checkForAnnexureEndDate(tahdrdetailDto,errors);
								checkForAnnexureBidOpeningDate(tahdrdetailDto,errors);
							}	
						}
						
					}
				}
				if(tahdrdetailDto.getIsWinnerSelectionDateSetLater()!=null && tahdrdetailDto.getIsWinnerSelectionDateSetLater().equalsIgnoreCase("N")){
					if(tahdrdetailDto.getTahdr()!=null){
					checkForWinnerDate(tahdrdetailDto,errors);
					}
				}
		}
		
	}

	public void checkForActiveCreateVersion(Object object,Errors errors) {
		if(!object.getClass().isAssignableFrom(TAHDRDetailDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		TAHDRDetailDto tahdrdetailDto =  (TAHDRDetailDto) object;
		
		if(tahdrdetailDto!=null && tahdrdetailDto.getVersion()>1 && tahdrdetailDto.getIsActive().equalsIgnoreCase("Y")){
			if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_PUBLISHED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
				checkForPublishDatesWithTodaysDate(tahdrdetailDto,errors);
				if(!compareDatewithTodaysDate(tahdrdetailDto.getPurchaseFromDate())){
				checkForPublishDates(tahdrdetailDto,errors);
				}
			}
			else if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_APPROVED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
				if(!compareDatewithTodaysDate(tahdrdetailDto.getPurchaseFromDate())){
				checkForToDate(tahdrdetailDto,errors);
				checkForSaleDate(tahdrdetailDto,errors);
				}
			}
			else if((tahdrdetailDto.getTahdr().getTahdrStatusCode()!=null && tahdrdetailDto.getTahdr().getTahdrStatusCode().equalsIgnoreCase(AppBaseConstant.DOCUMENT_STATUS_REJECTED)) && (tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N"))){
				return;
			}
			else{
				if(tahdrdetailDto.getEditedDates()==null || tahdrdetailDto.getEditedDates().equalsIgnoreCase("N")){
					if(!compareDatewithTodaysDate(tahdrdetailDto.getPurchaseFromDate())){
					checkForToDate(tahdrdetailDto,errors);
					}
					if(!compareDatewithTodaysDate(tahdrdetailDto.getTechnicalBidFromDate())){
					checkForBidStartDate(tahdrdetailDto,errors);
					}
					}
					
				if(!compareDatewithTodaysDate(tahdrdetailDto.getPurchaseToDate())){	
				checkForSaleDate(tahdrdetailDto,errors);
				}
				if(!compareDatewithTodaysDate(tahdrdetailDto.getTechnicalBidToDate())){
				checkForBidEndDate(tahdrdetailDto,errors);
				}
				if(!compareDatewithTodaysDate(tahdrdetailDto.getPreBidDate())){
					checkForPreBidDate(tahdrdetailDto,errors);
				}
				if(!compareDatewithTodaysDate(tahdrdetailDto.getTechBidOpenningDate())){
					checkForTechBidOpeningDate(tahdrdetailDto,errors);
				}
				if(tahdrdetailDto.getIsPBDSetLater().equalsIgnoreCase("N")){
					if(!compareDatewithTodaysDate(tahdrdetailDto.getPriceBidOpenningDate())){
						checkForPriceBidOpeningDate(tahdrdetailDto,errors);
					}
					}
				if(tahdrdetailDto.getTahdr()!=null){
					if((tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT) && tahdrdetailDto.getTahdr().getIsAuction().equalsIgnoreCase("Y") 
								|| (tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD)) )){
							if(tahdrdetailDto.getIsAuctionDateSetLater()!=null && tahdrdetailDto.getIsAuctionDateSetLater().equalsIgnoreCase("N"))
							{
							if(!compareDatewithTodaysDate(tahdrdetailDto.getAuctionFromDate())){
							checkForAuctionStartDate(tahdrdetailDto,errors);
							}
							if(!compareDatewithTodaysDate(tahdrdetailDto.getAuctionToDate())){
							checkForAuctionEndDate(tahdrdetailDto,errors);
							}
							}
						}
						
						if(tahdrdetailDto.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)){
							if(tahdrdetailDto.getIsAnnexureC1()!=null && tahdrdetailDto.getIsAnnexureC1().equalsIgnoreCase("Y")){
								if(tahdrdetailDto.getIsSetC1Later()!=null && tahdrdetailDto.getIsSetC1Later().equalsIgnoreCase("N")){
									if(!compareDatewithTodaysDate(tahdrdetailDto.getC1ToDate())){
									checkForAnnexureEndDate(tahdrdetailDto,errors);
									}
									if(!compareDatewithTodaysDate(tahdrdetailDto.getC1OpenningDate())){
									checkForAnnexureBidOpeningDate(tahdrdetailDto,errors);
									}
								}	
							}
							
						}
					}
					if(tahdrdetailDto.getIsWinnerSelectionDateSetLater()!=null && tahdrdetailDto.getIsWinnerSelectionDateSetLater().equalsIgnoreCase("N")){
						if(tahdrdetailDto.getTahdr()!=null){
							if(!compareDatewithTodaysDate(tahdrdetailDto.getWinnerSelectionDate())){
							checkForWinnerDate(tahdrdetailDto,errors);
							}
						}
					}
			}
		}
	}
	
	
	public static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }
	
	private boolean compareDatewithTodaysDate(Date comparingDate)
	{
		Date date=new Date();
		Date todaysDate= 	removeTime(date);
		boolean status=false;
		if(comparingDate!=null && date!=null)
		{
		 if(comparingDate.compareTo(date)==-1)
		{
			 status= true;	 
		}
		}
		else{
			status= false;
		}
		return status;
	}
	
	private void checkForToDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
	Date date=new Date();
	Date todaysDate= 	removeTime(date);
	if(tahdrDetail.getPurchaseFromDate()==null)
	{
		validatorUtil.reject(errors, "invalid.saleDate","Sale Start Date is Mandatory");
	}
	else if(tahdrDetail.getPurchaseFromDate().compareTo(date)==0){
		return;
	}
	else if(tahdrDetail.getPurchaseFromDate().compareTo(date)==1)
	{
		return;
	}
	else if(tahdrDetail.getPurchaseFromDate().compareTo(date)==-1)
	{
		validatorUtil.reject(errors, "invalid.saleDate","Sale Start Date should not be a Past Date");
	}
	}
	
	private void checkForSaleDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getPurchaseFromDate()==null || tahdrDetail.getPurchaseToDate()==null)
		{
			validatorUtil.reject(errors, "invalid.saleDate","Sale End Date is Mandatory");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==0)
		{
			validatorUtil.reject(errors, "invalid.saleDate", "Sale End Date should not be equal to Sale Start Date");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==1)
		{
			validatorUtil.reject(errors, "invalid.saleDate", "Sale End Date should be after Sale Start Date");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPurchaseToDate())==-1)
		{
			return;
		}
	}
	
	private void checkForBidStartDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getPurchaseFromDate()==null || tahdrDetail.getTechnicalBidFromDate()==null)
		{
			validatorUtil.reject(errors, "invalid.techBidStartDate","Bid Start Date is Mandatory");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getTechnicalBidFromDate())==0)
		{
			validatorUtil.reject(errors, "invalid.saleDate", "Bid Start Date should not be equal to Sale Start Date");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getTechnicalBidFromDate())==1)
		{
			validatorUtil.reject(errors, "invalid.techBidStartDate", "Bid Start Date should be after Sale Start Date");
		}
		else if(tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getTechnicalBidFromDate())==-1)
		{
			return;
		}
	}
	private void checkForBidEndDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getTechnicalBidFromDate()==null || tahdrDetail.getPurchaseToDate()==null || tahdrDetail.getTechnicalBidToDate()==null){
			validatorUtil.reject(errors, "invalid.techBidEndDate","Bid End Date is mandatory");
		}
		else if(tahdrDetail.getTechnicalBidFromDate().compareTo(tahdrDetail.getPurchaseToDate())==0){
			checkBidEndDates(tahdrDetail,tahdrDetail.getTechnicalBidFromDate(),errors,"Bid End Date should not be equal to Bid Start Date");
		}
		else if(tahdrDetail.getTechnicalBidFromDate().compareTo(tahdrDetail.getPurchaseToDate())==1){
			checkBidEndDates(tahdrDetail,tahdrDetail.getTechnicalBidFromDate(),errors,"Bid End Date should be After Bid Start Date");
		}
		else if(tahdrDetail.getTechnicalBidFromDate().compareTo(tahdrDetail.getPurchaseToDate())== -1){
			checkBidEndDates(tahdrDetail,tahdrDetail.getPurchaseToDate(),errors,"Bid End Date should be After Sale End Date");
		}
		
	}
		
	private void checkBidEndDates(TAHDRDetailDto tahdrDetail,Date dateForBidEndDate, Errors errors,String message){
	
		
		if(dateForBidEndDate==null || tahdrDetail.getTechnicalBidToDate()==null)
		{
			validatorUtil.reject(errors, "invalid.techBidEndDate",message);
		}
		else if(dateForBidEndDate.compareTo(tahdrDetail.getTechnicalBidToDate())==0)
		{
			validatorUtil.reject(errors, "invalid.saleDate", message);
		}
		else if(dateForBidEndDate.compareTo(tahdrDetail.getTechnicalBidToDate())==1)
		{
			validatorUtil.reject(errors, "invalid.techBidEndDate", message);
		}
		else if(dateForBidEndDate.compareTo(tahdrDetail.getTechnicalBidToDate())==-1)
		{
			return;
		}
	}

	private void checkForPreBidDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getPurchaseFromDate()==null || tahdrDetail.getPreBidDate()==null)
		{
			validatorUtil.reject(errors, "invalid.preBidDate","Pre Bid Date is Mandatory");
		}
		else if((tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPreBidDate())==0) && (tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getPreBidDate())==0))
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Pre Bid Date should not be equal to Sale Start Date and Bid End Date");
		}
		else if((tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPreBidDate())==1) && (tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getPreBidDate())==1))
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Pre Bid Date should be after Sale Start Date and before Bid End Date");
		}
		else if((tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPreBidDate())==-1) && (tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getPreBidDate())==-1))
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Pre Bid Date should be after Sale Start Date and before Bid End Date");
		}
		else if((tahdrDetail.getPurchaseFromDate().compareTo(tahdrDetail.getPreBidDate())==-1) && (tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getPreBidDate())==1))
		{
			return;
		}
	}

	private void checkForTechBidOpeningDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getTechnicalBidToDate()==null || tahdrDetail.getTechBidOpenningDate()==null)
		{
			validatorUtil.reject(errors, "invalid.techBidOpeningDate","Technical Bid Opening Date is mandatory");
		}
		else if(tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getTechBidOpenningDate())==0)
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Technical Bid Opening Date should not be equal to Bid End Date");
		}
		else if(tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getTechBidOpenningDate())==1)
		{
			validatorUtil.reject(errors, "invalid.techBidOpeningDate", "Technical Bid Opening Date should be After Bid End Date");
		}
		else if(tahdrDetail.getTechnicalBidToDate().compareTo(tahdrDetail.getTechBidOpenningDate())==-1)
		{
			return;
		}
	}
	
	private void checkForPriceBidOpeningDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getTechBidOpenningDate()==null || tahdrDetail.getPriceBidOpenningDate()==null)
		{
			validatorUtil.reject(errors, "invalid.priceBidDate","Price Bid Opening Date is mandatory");
		}
		else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getPriceBidOpenningDate())==0)
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Price Bid Opening Date should not be equal to Technical Bid Opening Date");
		}
		else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getPriceBidOpenningDate())==1)
		{
			validatorUtil.reject(errors, "invalid.priceBidDate", "Price Bid Opening Date Should be after Technical Bid Opening Date");
		}
		else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getPriceBidOpenningDate())==-1)
		{
			return;
		}
	}
	
	private void checkForAuctionStartDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getIsPBDSetLater()!=null && tahdrDetail.getIsPBDSetLater().equalsIgnoreCase("N")){
			if(tahdrDetail.getPriceBidOpenningDate()==null || tahdrDetail.getAuctionFromDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Auction Start Date is required");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Auction Start Date should not be equal to Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Auction Start Date should be after Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==-1)
			{
				return;
			}
		}
		else{
			if(tahdrDetail.getTechBidOpenningDate()==null || tahdrDetail.getAuctionFromDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Auction Start Date is required");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Auction Start Date should not be equal to Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Auction Start Date should be after Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getAuctionFromDate())==-1)
			{
				return;
			}
		}
		
	}
	private void checkForAuctionEndDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getAuctionFromDate()==null || tahdrDetail.getAuctionToDate()==null)
		{
			validatorUtil.reject(errors, "invalid.annexureBidEndDate","Auction End Date is required");
		}
		else if(tahdrDetail.getAuctionFromDate().compareTo(tahdrDetail.getAuctionToDate())==0)
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Auction End Date should not be equal to Auction Start Date");
		}
		else if(tahdrDetail.getAuctionFromDate().compareTo(tahdrDetail.getAuctionToDate())==1)
		{
			validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Auction End Date should be after Auction Start Date");
		}
		else if(tahdrDetail.getAuctionFromDate().compareTo(tahdrDetail.getAuctionToDate())==-1)
		{
			return;
		}
	}
	
	private void checkForAnnexureEndDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getIsAuctionDateSetLater()!=null && tahdrDetail.getIsAuctionDateSetLater().equalsIgnoreCase("N") && tahdrDetail.getAuctionToDate()!=null){
			if(tahdrDetail.getC1ToDate()==null || tahdrDetail.getAuctionToDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Annexure C1 End Date is required");
			}
			else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getC1ToDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Annexure C1 End Date should not be equal to Auction End Date");
			}
			else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getC1ToDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Annexure C1 End Date should be after Auction End Date");
			}
			else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getC1ToDate())==-1)
			{
				return;
			}
		}
		else if(tahdrDetail.getIsPBDSetLater()!=null && tahdrDetail.getIsPBDSetLater().equalsIgnoreCase("N")){
			if(tahdrDetail.getC1ToDate()==null || tahdrDetail.getPriceBidOpenningDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Annexure C1 End Date is required");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Annexure C1 End Date should not be equal to Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Annexure C1 End Date should be after Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==-1)
			{
				return;
			}
		}
		else{
			if(tahdrDetail.getC1ToDate()==null || tahdrDetail.getTechBidOpenningDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Annexure C1 End Date is required");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Annexure C1 End Date should not be equal to Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Annexure C1 End Date should be after Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getC1ToDate())==-1)
			{
				return;
			}			
		}
	}
	
	private void checkForAnnexureBidOpeningDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		if(tahdrDetail.getC1OpenningDate()==null || tahdrDetail.getC1ToDate()==null)
		{
			validatorUtil.reject(errors, "invalid.annexureBidOpeningDate","Annexure C1 Opening Date is mandatory");
		}
		else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getC1OpenningDate())==0)
		{
			validatorUtil.reject(errors, "invalid.preBidDate", "Annexure C1 opening Date should not be equal to Annexure C1 End Date");
		}
		else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getC1OpenningDate())==1)
		{
			validatorUtil.reject(errors, "invalid.annexureBidOpeningDate", "Annexure C1 opening Date should be after Annexure C1 End Date");
		}
		else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getC1OpenningDate())==-1)
		{
			return;
		}
	}
	
	private void checkForWinnerDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{

		if(tahdrDetail.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT)|| tahdrDetail.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD))
		{
			if(tahdrDetail.getIsAnnexureC1()!=null && tahdrDetail.getIsAnnexureC1().equalsIgnoreCase("Y")){
			if(tahdrDetail.getIsSetC1Later()!=null &&  tahdrDetail.getIsSetC1Later().equals("N")){
				if(tahdrDetail.getC1ToDate()==null || tahdrDetail.getWinnerSelectionDate()==null)
				{
					validatorUtil.reject(errors, "invalid.annexureBidEndDate","Winner Selection Date is required");
				}
				else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==0)
				{
					validatorUtil.reject(errors, "invalid.preBidDate", "Winner Selection Date should not be equal to Annexure C1 End Date");
				}
				else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==1)
				{
					validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Winner Selection Date should be after Annexure C1 End Date");
				}
				else if(tahdrDetail.getC1ToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==-1)
				{
					return;
				}
				}	
		}
		}
		else if((tahdrDetail.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.TENDER_TYPE_CODE_PROCUREMENT) && tahdrDetail.getTahdr().getIsAuction().equalsIgnoreCase("Y"))
					|| (tahdrDetail.getTahdr().getTahdrTypeCode().equalsIgnoreCase(AppBaseConstant.AUCTION_TYPE_CODE_FORWORD))){
			
			if(tahdrDetail.getIsAuctionDateSetLater()!=null && tahdrDetail.getIsAuctionDateSetLater().equalsIgnoreCase("N") && tahdrDetail.getAuctionToDate()!=null){
				if(tahdrDetail.getAuctionToDate()==null || tahdrDetail.getWinnerSelectionDate()==null)
				{
					validatorUtil.reject(errors, "invalid.annexureBidEndDate","Winner Selection Date is required");
				}
				else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==0)
				{
					validatorUtil.reject(errors, "invalid.preBidDate", "Winner Selection Date should not be equal to Auction End Date");
				}
				else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==1)
				{
					validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Winner Selection Date should be after Auction End Date");
				}
				else if(tahdrDetail.getAuctionToDate().compareTo(tahdrDetail.getWinnerSelectionDate())==-1)
				{
					return;
				}
				}
		}
		else if(tahdrDetail.getIsPBDSetLater()!=null && tahdrDetail.getIsPBDSetLater().equals("N")){
			if(tahdrDetail.getPriceBidOpenningDate()==null || tahdrDetail.getWinnerSelectionDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Winner Selection Date is required");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Winner Selection Date should not be equal to Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Winner Selection Date should be after Price Bid Opening Date");
			}
			else if(tahdrDetail.getPriceBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==-1)
			{
				return;
			}
			}
		else {
			if(tahdrDetail.getTechBidOpenningDate()==null || tahdrDetail.getWinnerSelectionDate()==null)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate","Winner Selection Date is required");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==0)
			{
				validatorUtil.reject(errors, "invalid.preBidDate", "Winner Selection Date should not be equal to Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==1)
			{
				validatorUtil.reject(errors, "invalid.annexureBidEndDate", "Winner Selection Date should be after Technical Bid Opening Date");
			}
			else if(tahdrDetail.getTechBidOpenningDate().compareTo(tahdrDetail.getWinnerSelectionDate())==-1)
			{
				return;
			}
		}
		
	}
	
	private void checkForPublishDatesWithTodaysDate(TAHDRDetailDto tahdrDetail, Errors errors)
	{
		Date date=new Date();
		Date todaysDate= removeTime(date);
		if(tahdrDetail.getPublishingDate().compareTo(todaysDate)==0){
			return;
		}
		else if(tahdrDetail.getPublishingDate().compareTo(todaysDate)==1)
		{
			return;
		}
		else if(tahdrDetail.getPublishingDate().compareTo(todaysDate)==-1)
		{
			validatorUtil.reject(errors, "invalid.saleDate","Publishing Date should not be a Past Date");
		}
	}
	
	private void checkForPublishDates(TAHDRDetailDto tahdrdetailDto, Errors errors)
	{
	if(tahdrdetailDto.getPublishingDate()==null)
	{
		validatorUtil.reject(errors, "invalid.saleDate","Publish Date is Mandatory");
	}
	else if(tahdrdetailDto.getPublishingDate().compareTo(tahdrdetailDto.getPurchaseFromDate())==0)
	{
		return;
	}
	else if(tahdrdetailDto.getPublishingDate().compareTo(tahdrdetailDto.getPurchaseFromDate())==1)
	{
		validatorUtil.reject(errors, "invalid.saleDate", "Sale From Date should be after Publishing Date");
	}
	else if(tahdrdetailDto.getPublishingDate().compareTo(tahdrdetailDto.getPurchaseFromDate())==-1)
	{
		return;
	}
	}
	
	public void validateSchedulingDate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(TAHDRDetailDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		TAHDRDetailDto tahdrdetailDto =  (TAHDRDetailDto) object;
			if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.TENDER_ANNEXURE_C1_SCHEDULING)){
				if(tahdrdetailDto.getC1ToDate()==null || tahdrdetailDto.getC1OpenningDate()==null){
					validatorUtil.reject(errors, "","Annexure Bid Opening Date And Annexure Bid To Date are mandatory");
				}
				else if(tahdrdetailDto.getC1ToDate().compareTo(tahdrdetailDto.getC1OpenningDate())==0){
					validatorUtil.reject(errors, "", "Annexure Bid Opening Date should not be same");
				}
				else if(tahdrdetailDto.getC1ToDate().compareTo(tahdrdetailDto.getC1OpenningDate())==1){
					validatorUtil.reject(errors, "", "Annexure Bid Opening Date should be After Bid End Date");
				}
				else if(tahdrdetailDto.getC1ToDate().compareTo(tahdrdetailDto.getC1OpenningDate())==-1){
					return;
				}
			}
			else if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.TENDER_AWARD_WINNER_SCHEDULING)){
				if(tahdrdetailDto.getWinnerSelectionDate()==null){
					validatorUtil.reject(errors, "","Winner Selcetion Date is mandatory");
				}else return;
			}
			else if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.TENDER_DEVIATION_BID_SCHEDULING)){
				if(tahdrdetailDto.getDeviationToDate()==null || tahdrdetailDto.getDeviationOpenningDate()==null){
					validatorUtil.reject(errors, "","Deviation Bid Opening Date And Deviation Bid To Date are mandatory");
				}
				else if(tahdrdetailDto.getDeviationToDate().compareTo(tahdrdetailDto.getDeviationOpenningDate())==0){
					validatorUtil.reject(errors, "", "Deviation Bid Opening Date should not be same");
				}
				else if(tahdrdetailDto.getDeviationToDate().compareTo(tahdrdetailDto.getDeviationOpenningDate())==1){
					validatorUtil.reject(errors, "", "Deviation Bid Opening Date should be After Deviation Bid End Date");
				}
				else if(tahdrdetailDto.getDeviationToDate().compareTo(tahdrdetailDto.getDeviationOpenningDate())==-1){
					return;
				}
	        }
			else if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.TENDER_REVISED_BID_SCHEDULING)){
				if(tahdrdetailDto.getRevisedBidToDate()==null || tahdrdetailDto.getRevisedBidOpenningDate()==null){
					validatorUtil.reject(errors, "","Revised Bid Opening Date And Revised Bid To Date are mandatory");
				}
				else if(tahdrdetailDto.getRevisedBidToDate().compareTo(tahdrdetailDto.getRevisedBidOpenningDate())==0){
					validatorUtil.reject(errors, "", "Revised Bid Opening Date should not be same");
				}
				else if(tahdrdetailDto.getRevisedBidToDate().compareTo(tahdrdetailDto.getRevisedBidOpenningDate())==1){
					validatorUtil.reject(errors, "", "Revised Bid Opening Date should be After Revised Bid End Date");
				}
				else if(tahdrdetailDto.getRevisedBidToDate().compareTo(tahdrdetailDto.getRevisedBidOpenningDate())==-1){
					return;
				}
	        }
			else if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.TENDER_PRICE_BID_SCHEDULING)){
				if(tahdrdetailDto.getPriceBidOpenningDate()==null){
					validatorUtil.reject(errors, "","Price Openning Date is mandatory");
				}else return;
	        }
			else if(tahdrdetailDto.getTahdrScheduling().equals(AppBaseConstant.AUCTION_SCHEDULING)){
				if(tahdrdetailDto.getAuctionFromDate()==null || tahdrdetailDto.getAuctionToDate()==null){
					validatorUtil.reject(errors, "","Auction End Date And Auction From Date are mandatory");
				}
				else if(tahdrdetailDto.getAuctionFromDate().compareTo(tahdrdetailDto.getAuctionToDate())==0){
					validatorUtil.reject(errors, "", "Auction Dates can be same but Time should not be same");
				}
				else if(tahdrdetailDto.getAuctionFromDate().compareTo(tahdrdetailDto.getAuctionToDate())==1){
					validatorUtil.reject(errors, "", "Auction End Date should be After Auction From Date");
				}
				else if(tahdrdetailDto.getAuctionFromDate().compareTo(tahdrdetailDto.getAuctionToDate())==-1){
					return;
				}
	        }
			
	}
}
