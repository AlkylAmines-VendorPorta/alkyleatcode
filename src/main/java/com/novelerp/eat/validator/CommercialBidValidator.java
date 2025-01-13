/**
 * @author Ankush
 */
package com.novelerp.eat.validator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.service.TAHDRMaterialService;
import com.novelerp.eat.service.TAHDRService;

@Component
public class CommercialBidValidator implements Validator {

	/* (non-Javadoc)
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	
	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private FileValidator fileValidator;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	@Autowired 
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired 
	private TAHDRService tahdrService; 
	
	public void validate(Object object,HttpServletRequest request, Errors errors) {
		if(!object.getClass().isAssignableFrom(CommercialBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		BPartnerDto partner=contextService.getPartner();
		
		CommercialBidDto cb=(CommercialBidDto) object;
		
		Map<String, Object> map= new HashMap<>();
		map.put("tahdrDetailId", cb.getTahdrDetail().getTahdrDetailId());
		map.put("partnerId", partner.getbPartnerId());
	
		BidderDto bidder=bidderService.findDto("getBidderSubmitStatus",map);
		TAHDRDto tahdr=tahdrService.findDto("getTahdrWithAllDetailsForCS",
				AbstractContextServiceImpl.getParamMap("tahdrId",bidder.getTahdr().getTahdrId()));
		TAHDRDetailDto td=null;
		if(tahdr!=null){
			td=tahdr.getTahdrDetail().iterator().next();
		}
		
		if(cb.getBidder()==null){
			validatorUtil.reject(errors, "invalidBid", "Invalid Commercial Bid");
		}else if(cb.getDigiSignedDoc()==null){
			validatorUtil.reject(errors, "invalidSignatureFile", "Please Attach Digitally Signed File");
		}else if(td!=null && !CommonUtil.isCollectionEmpty(td.getSectionDocument())){
			int sdCount=0;
			for(SectionDocumentDto sd:td.getSectionDocument()){
				if(sd.getCode().equals(AppBaseConstant.COMMERCIAL_SECTION) 
						&& sd.getTahdrMaterial()==null){
					sdCount++;
				}
			}
			if(sdCount>0 &&  (CommonUtil.isCollectionEmpty(cb.getBidderSecDoc()) || cb.getBidderSecDoc().size()<sdCount)){
				validatorUtil.reject(errors, "invalid.technicalBid", "All Additional Required Documents are Mandatory.");
			}			
		}else{
			fileValidator.validate(cb.getDigiSignedDoc(), request, errors);
		}
		if(bidder==null || CommonUtil.isCollectionEmpty(bidder.getItemBidList())){
			validatorUtil.reject(errors, "emptyItemBid", "Atleast One Price Bid Must be Submited ");
		}
		
	}

	public void saveValidate(Object object,Errors errors) {
		if(!object.getClass().isAssignableFrom(CommercialBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Object");
			return;
		}
		
			CommercialBidDto cb=(CommercialBidDto) object;
			
			if(cb!=null){
				checkValidationForDeliverDate(cb,errors);
			}
		}	
			
		private void checkValidationForDeliverDate(CommercialBidDto dto, Errors error){
			BigDecimal minimunRequiredQty = null ;
			MathContext mc = new MathContext(4);
			BigDecimal firstLot = dto.getFirstLot();
			BigDecimal deliveryPeriod = new BigDecimal(dto.getDeliveringMonth());
			BigDecimal actualPeriod =null;
			BigDecimal ratePerMonth = dto.getRatePerMonth();
			try{
				if(dto!=null){
					BigDecimal totalReqMatCount = null;
					if(dto.getTahdrDetail().getTahdrDetailId()!=null)
					{
						TAHDRDetailDto tahdrDetailDto = tahdrDetailService.findDto(dto.getTahdrDetail().getTahdrDetailId());
						Long totalRequiredMaterialCount = tahdrMaterialService.getTotalMaterialItemsCount(dto.getTahdrDetail().getTahdrDetailId());
						totalReqMatCount =new BigDecimal(totalRequiredMaterialCount);// total quantity required
						Long minRequiredQty = tahdrDetailDto.getMinQuantity();//in %
						
				
							if(totalRequiredMaterialCount!=null && minRequiredQty!=null )
							{
								Long minReqQtyItem= (totalRequiredMaterialCount*minRequiredQty)/100 ;
								minimunRequiredQty = new BigDecimal(minReqQtyItem);//minimum quantity required
							}
						

							actualPeriod= new BigDecimal(tahdrDetailDto.getDeliveryDuration());
					}
					BigDecimal bidderQty =  firstLot.add(((actualPeriod.subtract(deliveryPeriod)).multiply(ratePerMonth, mc)));
					if((bidderQty.compareTo(minimunRequiredQty)==0) && (bidderQty.compareTo(totalReqMatCount)==0)){
						return;
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==0) && (bidderQty.compareTo(totalReqMatCount)==1))
					{
						validatorUtil.reject(error, "invalid.bidderQty","Bidder Quantity cannot be greater than Total Required Quantity");
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==0) && (bidderQty.compareTo(totalReqMatCount)==-1))
					{
						return;
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==1) && (bidderQty.compareTo(totalReqMatCount)==0))
					{
						return;
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==1) && (bidderQty.compareTo(totalReqMatCount)==1))
					{
						validatorUtil.reject(error, "invalid.bidderQty","Bidder Quantity cannot be greater than Total Required Quantity");
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==1) && (bidderQty.compareTo(totalReqMatCount)==-1))
					{
						return;
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==-1) && (bidderQty.compareTo(totalReqMatCount)==0))
					{
						validatorUtil.reject(error, "invalid.bidderQty","Bidder Quantity cannot be less than Minimum Required Item Quantity");
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==-1) && (bidderQty.compareTo(totalReqMatCount)==1))
					{
						validatorUtil.reject(error, "invalid.bidderQty","Bidder Quantity cannot be less than Minimum Required Item Quantity and cannot be greater than Total required quantity");
					}
					else if((bidderQty.compareTo(minimunRequiredQty)==-1) && (bidderQty.compareTo(totalReqMatCount)==-1))
					{
						validatorUtil.reject(error, "invalid.bidderQty","Bidder Quantity cannot be less than Minimum Required Item Quantity");
					}
				}
				}
				catch (ArithmeticException e) {
					e.printStackTrace();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}

	/* (non-Javadoc)
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
