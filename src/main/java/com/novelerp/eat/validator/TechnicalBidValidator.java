/**
 * @author Ankush
 */
package com.novelerp.eat.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.SignatureVerificationService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;

@Component
public class TechnicalBidValidator implements Validator{

	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private SignatureVerificationService signatureVerification;
	
	@Autowired
	private FileValidator fileValidator;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	/* (non-Javadoc)
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	
	public void validate(Object object, HttpServletRequest request,Errors errors) {
		if(!object.getClass().isAssignableFrom(TechnicalBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		TechnicalBidDto tb=(TechnicalBidDto) object;
		
		AttachmentDto digiSign=null;
		if(tb!=null){
			digiSign=tb.getDigiSignedDoc();
		}
		
		BPartnerDto partner=contextService.getPartner();
		Map<String, Object> map=new HashMap<>();
		map.put("partnerId", partner.getbPartnerId());
		map.put("itemBidId", tb.getItemBid().getItemBidId());
		tb =technicalBidService.findDto("verifyTechnicalBid", map);
		
		
		if(tb==null || tb.getTechnicalBidId()==null){
			validatorUtil.reject(errors, "invalid.technicalBid", "Invalid Bid submitted");
		}else{
			map=new HashMap<>();
			map.put("tahdrMaterialId", tb.getItemBid().getTahdrMaterial().getTahdrMaterialId());
			
			map.put("tahdrId", tb.getItemBid().getBidder().getTahdr().getTahdrId());
			/*map.put("sdCode", AppBaseConstant.TECHNICAL_SECTION);*/
			TAHDRDto tahdr=tahdrService.findDto("getTahdrWithAllDetailsForMaterial",map);
			TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
			TAHDRMaterialDto tm=td.getTahdrMaterial().iterator().next();
			
			int sdCount=0;
			if(!CommonUtil.isCollectionEmpty(td.getSectionDocument())){
				for(SectionDocumentDto sd:td.getSectionDocument()){
					if(sd.getCode().equals(AppBaseConstant.TECHNICAL_SECTION) 
							&& sd.getTahdrMaterial()!=null
							&& CommonUtil.isEqual(sd.getTahdrMaterial().getTahdrMaterialId(),(Long) map.get("tahdrMaterialId"))){
						sdCount++;
					}
				}
			}
			
			if(!CommonUtil.isCollectionEmpty(tm.getMaterialGtpList()) &&  ( CommonUtil.isCollectionEmpty(tb.getBidderGtp()) || tb.getBidderGtp().size()<tm.getMaterialGtpList().size())){
				validatorUtil.reject(errors, "invalid.technicalBid", "All GTP Are Mandatory.");
			}
			
			if(sdCount>0 &&  (CommonUtil.isCollectionEmpty(tb.getBidderSecDoc()) || tb.getBidderSecDoc().size()<sdCount)){
				validatorUtil.reject(errors, "invalid.technicalBid", "All Additional Required Documents are Mandatory.");
			}
			
			if(digiSign==null){
				validatorUtil.reject(errors, "invalid.technicalBid", "All Additional Required Documents are Mandatory.");
			}else {
				/*ResponseDto response=signatureVerification.verifyDigitalSignature(digiSign);
				if(response.isHasError()){
					validatorUtil.reject(errors, "invalid.technicalBid", response.getMessage());
				}
				
				fileValidator.validate(digiSign, request, errors);*/
			}
		
		}
	}

	public void saveValidate(Object object, Errors errors) {
		if(!object.getClass().isAssignableFrom(TechnicalBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		TechnicalBidDto tb=(TechnicalBidDto) object;
		Map<String, Object> map=new HashMap<>();
		map.put("itemBidId", tb.getItemBid().getItemBidId());
		ItemBidDto dto =itemBidService.findDto("getTahdrDataFromItemBidId", map);
		
		if(dto!=null){
			TAHDRDto tahdr=null;
			BidderDto bidder=dto.getBidder();
			if(bidder!=null){
				tahdr=bidder.getTahdr();
				if(tahdr!=null){
					PaymentDetailDto payment=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tahdr.getTahdrId(),AppBaseConstant.EMD);
					if(null==payment){
						validatorUtil.reject(errors, "invalid.payment","EMD not paid");
					}
				}
			}
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
