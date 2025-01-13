package com.novelerp.alkyl.validator;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.alkyl.component.QuotationComponent;
import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;

@Component
public class QuotationValidator implements Validator{
	
	@Autowired
	private QuotationComponent quotComponent;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private BidderService bidderService;

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	@Autowired
	private ValidationUtil validationUtil;
	
	public void validate(QuotationDto quotation, Errors errors){
		
		if(null==quotation){
			
			validationUtil.reject(errors, "Empty Quotation ", "Please try again later.");
		}else{
			if(quotation!=null && quotation.getBidder()!=null && quotation.getBidder().getBidderId()!=null){
				
				 
				if("header_level".equals(quotation.getBidder().getOtherChargeType())){
					for(PriceBidDto priceBid : quotation.getQuotations()){
						priceBid.setFreightChargeRate(0);
						priceBid.setPackingFwdCharge(0);
						priceBid.setOtherChargesAmt(0);
						priceBid.setOtherChargesRate(0);
						priceBid.setTotalFreightCharge(0);
						priceBid.setTotalPackingFwdChargeAmt(0);
						priceBid.setCompleteOther(0);
						priceBid.setPerQtyDiscount(0);
						//priceBid.setNetRate(0);
					}
					
					Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", quotation.getBidder().getBidderId());
					Map<String, Object> params = new HashMap<String, Object>();
					if((quotation.getBidder().getHeaderFreightType().equals("")) && ((!quotation.getBidder().getTotalFreight().equals("0")) )) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please Select freightChargesType");
						params.put("headerFreightType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					
		
					
					if(quotation.getBidder().getHeaderPKFWDType().equals("") && !quotation.getBidder().getTotalPKFWD().equals("0")) {
						validationUtil.reject(errors, "Empty PackingChargesType ", "Please Select PackingChargesType");
						params.put("headerPKFWDType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(quotation.getBidder().getHeaderOtherType().equals("") && !quotation.getBidder().getOtherRates().equals("0")) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please Select OtherChargesType");
						params.put("headerOtherType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderFreightType().equals("") && quotation.getBidder().getTotalFreight().equals("0")) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please fill freightCharges");
						params.put("headerFreightType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderPKFWDType().equals("") && quotation.getBidder().getTotalPKFWD().equals("0")) {
						validationUtil.reject(errors, "Empty PackingChargesType ", "Please fill PackingCharges");
						params.put("headerPKFWDType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderOtherType().equals("") && quotation.getBidder().getOtherRates().equals("0")) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please fill OtherCharges");
						params.put("headerOtherType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
				}
				
					for(PriceBidDto newpricebid : quotation.getQuotations()){
						
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("itemBidId", newpricebid.getItemBid().getItemBidId());
						 
						 List<ItemBidDto> itemBiddto = itemBidService.findDtos("getprLineByitemId", param);
						
						if(newpricebid.getDeliveryDate().before(new Date())) {
							
							validationUtil.reject(errors, "Empty Delivery Date ", "Delivery Date Must be today's or later for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
						}
						
						Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("priceBidId", newpricebid.getPriceBidId());
						Map<String, Object> params = new HashMap<String, Object>(); 
						
						if("item_level".equals(quotation.getBidder().getOtherChargeType())){
							if(newpricebid.getFreightChargesType().equals("") && newpricebid.getFreightChargeRate()!=0.0) {
								validationUtil.reject(errors, "Empty freightChargesType ", "Please Select freightChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
								params.put("freightChargesType", null);
								priceBidService.updateByJpql(params, whereCls);

							}
							
							if(newpricebid.getPackingFwdChargeType().equals("") && newpricebid.getPackingFwdCharge()!=0.0) {
								validationUtil.reject(errors, "Empty PackingFwdChargesType ", "Please Select PackingFwdChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
								params.put("packingFwdChargeType", null);
								priceBidService.updateByJpql(params, whereCls);
							}
							
							if(newpricebid.getOtherChargesType().equals("") && newpricebid.getOtherChargesRate()!=0.0) {
								validationUtil.reject(errors, "Empty OtherChargesType ", "Please Select OtherChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
								params.put("otherChargesType", null);
								priceBidService.updateByJpql(params, whereCls);
							}
							
//							if(newpricebid.getDiscountType().equals("") && !"0".equals(newpricebid.getDiscountCharge())) {
//								validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
//								params.put("discountType", null);
//								int result3 = priceBidService.updateByJpql(params, whereCls);
//							}
							
							}
						
					
						
					if(newpricebid.getDiscountType().equals("") && !"0".equals(newpricebid.getDiscountCharge())) {
						validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
						params.put("discountType", null);
						 priceBidService.updateByJpql(params, whereCls);
					}
					
					if(newpricebid.getExGroupPriceRate().equals("") || "0".equals(newpricebid.getExGroupPriceRate())) {
						validationUtil.reject(errors, "Empty Rate ", "Rate Null for:"+itemBiddto.get(0).getPrLine().getMaterialCode());

					}
					
					if(newpricebid.getTaxRate().equals("") || "0".equals(newpricebid.getTaxRate())) {
						validationUtil.reject(errors, "Empty taxRate ", "Tax Rate Null for:"+itemBiddto.get(0).getPrLine().getMaterialCode());

					}
						
						
					}
					
					
//				}
			/*	boolean result=quotComponent.validateBidEndDateById(quotation.getBidder().getBidderId());
				if(!result){
					validationUtil.reject(errors, "Bid Date Exceeds! ", "Please try again later.");
				}*/
				
			}else{
				validationUtil.reject(errors, "Empty Bidder Details ", "Please try again later.");
			}
		}
		
		
		
	}
	
	
	public void validateQuotation(QuotationDto quotation, Errors errors){
		
		if(null==quotation){
			
			validationUtil.reject(errors, "Empty Quotation ", "Please try again later.");
		}else{
			if(quotation!=null && quotation.getBidder()!=null && quotation.getBidder().getBidderId()!=null){
				if(quotation.getBidder().getNegotiatorPaymentTerms()==null || quotation.getBidder().getNegotiatorPaymentTerms().equals("")) {
					validationUtil.reject(errors, "Empty Negotiator Payment Terms ", "Please Select Negotiator Payment Terms.");
				}
				
				if(quotation.getBidder().getValidityDateFrom()==null || quotation.getBidder().getValidityDateFrom().equals("")) {
					validationUtil.reject(errors, "Empty Validity Date From", "Please Select Validity Date From");
				}
				
				if(quotation.getBidder().getValidityDateTo()==null || quotation.getBidder().getValidityDateTo().equals("")) {
					validationUtil.reject(errors, "Empty Validity Date To", "Please Select Validity Date To");
				}
				
				if("header_level".equals(quotation.getBidder().getOtherChargeType())){
					
					Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", quotation.getBidder().getBidderId());
					Map<String, Object> params = new HashMap<String, Object>();
					if(quotation.getBidder().getHeaderFreightType().equals("") && !quotation.getBidder().getTotalFreight().equals("0")) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please Select freightChargesType");
						params.put("headerFreightType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(quotation.getBidder().getHeaderPKFWDType().equals("") && !quotation.getBidder().getTotalPKFWD().equals("0")) {
						validationUtil.reject(errors, "Empty PackingChargesType ", "Please Select PackingChargesType");
						params.put("headerPKFWDType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(quotation.getBidder().getHeaderOtherType().equals("") && !quotation.getBidder().getOtherRates().equals("0")) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please Select OtherChargesType");
						params.put("headerOtherType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderFreightType().equals("") && quotation.getBidder().getTotalFreight().equals("0")) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please fill freightCharges");
						params.put("headerFreightType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderPKFWDType().equals("") && quotation.getBidder().getTotalPKFWD().equals("0")) {
						validationUtil.reject(errors, "Empty PackingChargesType ", "Please fill PackingCharges");
						params.put("headerPKFWDType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
					if(!quotation.getBidder().getHeaderOtherType().equals("") && quotation.getBidder().getOtherRates().equals("0")) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please fill OtherCharges");
						params.put("headerOtherType", null);
						bidderService.updateByJpql(params, whereCls);

					}
					
				}
				
				
				for(PriceBidDto Newquotation : quotation.getQuotations()) {
					Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("priceBidId", Newquotation.getPriceBidId());
					Map<String, Object> params = new HashMap<String, Object>(); 
					
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("itemBidId", Newquotation.getItemBid().getItemBidId());
					 
					List<ItemBidDto> itemBiddto = itemBidService.findDtos("getprLineByitemId", param);
					
					if("item_level".equals(quotation.getBidder().getOtherChargeType())){
					if(Newquotation.getFreightChargesType().equals("") && Newquotation.getFreightChargeRate()!=0.0) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please Select freightChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
						params.put("freightChargesType", null);
						 priceBidService.updateByJpql(params, whereCls);

					}
					
					if(Newquotation.getPackingFwdChargeType().equals("") && Newquotation.getPackingFwdCharge()!=0.0) {
						validationUtil.reject(errors, "Empty PackingFwdChargesType ", "Please Select PackingFwdChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
						params.put("packingFwdChargeType", null);
						priceBidService.updateByJpql(params, whereCls);
					}
					
					if(Newquotation.getOtherChargesType().equals("") && Newquotation.getOtherChargesRate()!=0.0) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please Select OtherChargesType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
						params.put("otherChargesType", null);
					priceBidService.updateByJpql(params, whereCls);
					}
					
//					if(Newquotation.getDiscountType().equals("") && !"0".equals(Newquotation.getDiscountCharge())) {
//						validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
//						params.put("discountType", null);
//						int result3 = priceBidService.updateByJpql(params, whereCls);
//					}
					
					}
					
					if(Newquotation.getDiscountType().equals("") && !"0".equals(Newquotation.getDiscountCharge())) {
					validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
					params.put("discountType", null);
					priceBidService.updateByJpql(params, whereCls);
				}
					if(Newquotation.getExGroupPriceRate().equals("") || "0".equals(Newquotation.getExGroupPriceRate())) {
						validationUtil.reject(errors, "Empty Rate ", "Rate Null for:"+itemBiddto.get(0).getPrLine().getMaterialCode());

					}
					
					if(Newquotation.getTaxRate().equals("") || "0".equals(Newquotation.getTaxRate())) {
						validationUtil.reject(errors, "Empty taxRate ", "Tax Rate Null for:"+itemBiddto.get(0).getPrLine().getMaterialCode());

					}
//					
//					if("header_level".equals(quotation.getBidder().getOtherChargeType())){
//					
//					if(Newquotation.getDiscountType().equals("") && !"0".equals(Newquotation.getDiscountCharge())) {
//						validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBiddto.get(0).getPrLine().getMaterialCode());
//						params.put("discountType", null);
//						int result4 = priceBidService.updateByJpql(params, whereCls);
//					}
//					}
				}
//				if("header_level".equals(quotation.getBidder().getOtherChargeType())){
//					for(PriceBidDto priceBid : quotation.getQuotations()){
//						priceBid.setFreightChargeRate(0);
//						priceBid.setPackingFwdCharge(0);
//						priceBid.setOtherChargesAmt(0);
//						priceBid.setOtherChargesRate(0);
//						priceBid.setTotalFreightCharge(0);
//						priceBid.setTotalPackingFwdChargeAmt(0);
//						priceBid.setCompleteOther(0);
//						priceBid.setPerQtyDiscount(0);
//						//priceBid.setNetRate(0);
//					}
//				}
		
				
			}else{
				validationUtil.reject(errors, "Empty Bidder Details ", "Please try again later.");
			}
		}
		
		
		
	}

}
