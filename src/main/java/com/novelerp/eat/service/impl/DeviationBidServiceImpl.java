package com.novelerp.eat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.BidderStatusService;
import com.novelerp.eat.service.DeviationBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.ItemScrutinyLineService;
import com.novelerp.eat.service.ItemScrutinyService;

@Service
public class DeviationBidServiceImpl implements DeviationBidService {

	@Autowired
	private ItemScrutinyService itemScrutinyService;
	
	@Autowired
	private ItemScrutinyLineService itemScrutinyLineService;
	
	@Autowired
	private BidderStatusService bidderStatusService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private BidderService bidderService;
	
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto confirmCommercialDeviationByBidder(ItemScrutinyDto itemScrutiny){
		CustomResponseDto response=new CustomResponseDto();
		boolean result=false;
		result=itemScrutinyService.saveBidderDevitionSatus(itemScrutiny);
		if(result){
			String status=AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED;
			result= bidderService.updateStatus(status,itemScrutiny.getBidder().getBidderId());
			if(result){
				result=bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(),status,"");
				if(result){
					response.addObject("Status", "Commercial Deviation Updated Successfully");
					response.addObject("statusResult", true);
				}else{
					response.addObject("Status", "Commercial Deviation not updated");
					response.addObject("statusResult", false);
					throw new CustomException("Commercial Deviation Not Updated");
				}
			}else{
				response.addObject("Status", "Commercial Deviation not updated");
				response.addObject("statusResult", false);
				throw new CustomException("Commercial Deviation Not Updated");
			}
		}else{
			response.addObject("Status", "Commercial Deviation Submission not Confirmed/All deviation is has not submitted ");
			response.addObject("statusResult", false);
			throw new CustomException("Commercial Deviation Not Updated");
		}	
		return response;
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
	public CustomResponseDto confirmTechicalDeviationByBidder(ItemScrutinyDto itemScrutiny){
		CustomResponseDto response=new CustomResponseDto();
		boolean result=false;
		result=itemScrutinyService.saveBidderDevitionSatus(itemScrutiny);
		if(result){
			String status=AppBaseConstant.BIDDER_STATUS_DEVIATION_SUBMITTED;
			result=itemBidService.updateItemBidScrutinyStatus(status,itemScrutiny.getItemBid());
			if(itemScrutiny.getBidder().getBidderId()!=null && result){
					bidderService.updateBidderStatusForNoCommercialDeviation(status,itemScrutiny.getBidder().getBidderId());
					bidderStatusService.saveBidderStatus(itemScrutiny.getBidder(),status,"");
						if(result){
							response.addObject("Status", "Technical Deviation Updated Successfully");
							response.addObject("statusResult", true);
						}else{
							response.addObject("Status", "Technical Deviation Not Updated");
							response.addObject("statusResult", false);
							throw new CustomException("Technical Deviation Not Updated");
						}
			}else{
				response.addObject("Status", "Technical Deviation Not Updated");
				response.addObject("statusResult", false);
				throw new CustomException("Technical Deviation Not Updated");
			}
		}else{
			response.addObject("Status", "Technical Deviation Submission not Confirmed/All deviation is has not submitted");
			response.addObject("statusResult", false);
			throw new CustomException("Technical Deviation Not Updated");
		}
		return response;
		
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public CustomResponseDto saveCommercialDeviationByBidder(ItemScrutinyLineDto itemScrutinyLine){
    	CustomResponseDto response=new CustomResponseDto();
    	boolean result=false;
    	response.addObject("resultStatus", result);
    	result=itemScrutinyLineService.updateCommercialDeviationResponseInfo(itemScrutinyLine);
    	Long itemScrutinyId=itemScrutinyLine.getItemScrutiny().getItemScrutinyId();
    	if(result && itemScrutinyId!=null){
    		result=revokeDeviationConfirmation(itemScrutinyId);
    			response.addObject("Status", "Commercial Deviation Submitted <BR>Please re-Submit Final Technical Deviation,if Already Submitted !");
    			response.addObject("resultStatus", true);
    	}else{
    		response.addObject("Status", "Commercial Deviation Submission not Submitted");
			response.addObject("resultStatus", false);
			throw new CustomException("Commercial Deviation Not Updated");
    	}
    	return response;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
   	public CustomResponseDto saveTechicalDeviationByBidder(ItemScrutinyLineDto itemScrutinyLine){
    	CustomResponseDto response=new CustomResponseDto();
    	boolean result=false;
    	response.addObject("resultStatus", result);
    	result=itemScrutinyLineService.updateTechnicalDeviationResponseInfo(itemScrutinyLine);
    	Long itemScrutinyId=itemScrutinyLine.getItemScrutiny().getItemScrutinyId();
    	if(result && itemScrutinyId!=null){
    		result=revokeDeviationConfirmation(itemScrutinyId);
    			response.addObject("Status", "Technical Deviation Submitted <BR>Please re-Submit Final Technical Deviation,if Already Submitted !");
    			response.addObject("resultStatus", true);
    	}else{
    		response.addObject("Status", "Technical Deviation Submission not Submitted");
			response.addObject("resultStatus", false);
			throw new CustomException("Technical Deviation Not Submitted");
    	}
    	return response;
   	}
   	
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean revokeDeviationConfirmation(Long itemScrutinyId){
    	return itemScrutinyService.revokeDeviationConfirmation(itemScrutinyId);
    }
    
}
