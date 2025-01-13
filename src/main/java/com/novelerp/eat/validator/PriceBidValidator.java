package com.novelerp.eat.validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.core.validator.Validator;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.SectionDocumentDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.service.CommercialBidService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PriceBidService;
import com.novelerp.eat.service.SignatureVerificationService;
import com.novelerp.eat.service.TAHDRService;
import com.novelerp.eat.service.TechnicalBidService;

@Component
public class PriceBidValidator implements Validator{
	@Autowired
	private ValidationUtil validatorUtil;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private TechnicalBidService technicalBidService;
	
	@Autowired
	private SignatureVerificationService signatureVerification;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private FileValidator fileValidator;
	
	@Autowired
	private TAHDRService tahdrService;
	
	@Autowired
	private PaymentDetailService paymentDetailService;
	
	@Autowired
	private CommercialBidService commercialBidService;
	
	public void validate(Object object, HttpServletRequest request,Errors errors) {
		if(!object.getClass().isAssignableFrom(PriceBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		PriceBidDto priceBidDto =  (PriceBidDto) object;
		Map<String, Object> map=new HashMap<>();
		map.put("itemBidId", priceBidDto.getItemBid().getItemBidId());
		ItemBidDto dto =itemBidService.findDto("getTahdrDataFromItemBidId", map);
		
		if(dto!=null){
			TAHDRDto tahdr=null;
			BidderDto bidder=dto.getBidder();
			if(bidder!=null){
				tahdr=bidder.getTahdr();
				if(tahdr!=null){
					TAHDRMaterialDto tm=dto.getTahdrMaterial();
					BPartnerDto partner=contextService.getPartner();
					if(partner!=null){
						PriceBidDto pb=getPriceBid(partner.getbPartnerId(), dto.getItemBidId());
						if(pb!=null){
							pb.setDigiSignedDoc(priceBidDto.getDigiSignedDoc());
							priceBidDto.getItemBid().setPriceBid(pb);
							tahdr=getTahdr(tm.getTahdrMaterialId(),tahdr.getTahdrId());
							TechnicalBidDto tb =getTechnicalBid(partner.getbPartnerId(), dto.getItemBidId());
							checkForErrors(tb, pb, tahdr, errors, request, dto.getTahdrMaterial().getMaterialGtpList());
						}else{
							validatorUtil.reject(errors, "invalidBid", "Invalid Price Bid");
						}

					}
				}else {
					validatorUtil.reject(errors, "invalidBid", "Invalid Tender");
				}
			}else{
				validatorUtil.reject(errors, "invalidBid", "Invalid Bider");
			}		
		}else{
			validatorUtil.reject(errors, "invaliBid", "No Saved Bid To Submit");
		}
		
}
	
	private Errors checkForErrors(TechnicalBidDto tb,PriceBidDto pb,TAHDRDto tahdr, Errors errors,HttpServletRequest request,Set<TAHDRMaterialGTPDto> gtpList){
		boolean technicalBidNeeded=tahdr==null?false:tahdr.getTahdrDetailList()==null?false:tahdr.getTahdrDetailList().iterator().next().getIsTechnicalBid().equals("Y")?true:false;
		if(technicalBidNeeded){
			if(tb!=null){
				if((tahdr.getTahdrTypeCode().equalsIgnoreCase("FA") 
						&& !CommonUtil.isCollectionEmpty(gtpList)) 
						|| tahdr.getTahdrTypeCode().equalsIgnoreCase("PT")
						|| tahdr.getTahdrTypeCode().equalsIgnoreCase("WT")
						|| tahdr.getTahdrTypeCode().equalsIgnoreCase("RA")){
					checkForTechnicalBid(tb,tahdr,errors);
					checkForPriceBid(pb,tahdr,errors);
					fileValidator.validate(pb.getDigiSignedDoc(), request, errors);
				}else{
					checkForPriceBid(pb,tahdr,errors);
					fileValidator.validate(pb.getDigiSignedDoc(), request, errors);
				}
			}else{
				validatorUtil.reject(errors, "invalidBid", "Invalid Technical Bid");
			}
		}
		return errors;
	}
	
	private void checkForTechnicalBid(TechnicalBidDto tb, TAHDRDto tahdr ,Errors errors)
	{
		if(tb==null){
			validatorUtil.reject(errors, "invalid.technicalBid","No Saved Technical Bid");
		}else if(!AppBaseConstant.BIDDER_STATUS_BID_SUBMITED.equals(tb.getStatus())){
			validatorUtil.reject(errors, "invalid.technicalBid","Technical Bid not Submitted");
		}
	}
	
	private void checkForPriceBid(PriceBidDto pb, TAHDRDto tahdr, Errors errors){
		AttachmentDto digiSign= null;
		if(pb!=null ){
			digiSign=pb.getDigiSignedDoc();
		}
		
		TAHDRDetailDto td=tahdr.getTahdrDetail().iterator().next();
		TAHDRMaterialDto tm=td.getTahdrMaterial().iterator().next();
		PaymentDetailDto payment=paymentDetailService.getTahdrPaymentDetailsByChargeCodePartnerQuery(tahdr.getTahdrId(),AppBaseConstant.EMD);
		if(pb==null){
			validatorUtil.reject(errors, "invalid.priceBid","No Saved Price Bid");
		}else if(!tahdr.getTahdrTypeCode().equals(AppBaseConstant.TENDER_TYPE_CODE_WORKS) 
				&& (pb.getExGroupPriceRate()==null || pb.getFreightChargeRate()==0
				|| pb.getTicRate()==null || pb.getOfferedQuantity()==null)){
			validatorUtil.reject(errors, "invalid.priceBid","Price Bid is not Complete");
		}else if(!CommonUtil.isCollectionEmpty(td.getSectionDocument())){
				int sdCount=0;
				for(SectionDocumentDto sd:td.getSectionDocument()){
					if(sd.getCode().equals(AppBaseConstant.PRICE_BID) 
							&& sd.getTahdrMaterial()!=null
							&& CommonUtil.isEqual(sd.getTahdrMaterial().getTahdrMaterialId(),tm.getTahdrMaterialId())){
						sdCount++;
					}
				}
				if(sdCount>0 &&  (CommonUtil.isCollectionEmpty(pb.getBidderSecDoc()) || pb.getBidderSecDoc().size()<sdCount)){
					validatorUtil.reject(errors, "invalid.technicalBid", "All Additional Required Documents are Mandatory.");
				}			
		}
		
		if(null==payment){
			validatorUtil.reject(errors, "invalid.payment","EMD not paid");
		}/*else if(!"Y".equals(payment.getIsFAApproved())){
			validatorUtil.reject(errors, "invalid.payment","EMD not Approved");
		}*/
		
		if(digiSign==null){
			validatorUtil.reject(errors, "invalid.signature","Please Attach Digitally Signed Document");
		}else{
			ResponseDto response=new ResponseDto();
			/*response.setHasError(true);
			response=signatureVerification.verifyDigitalSignature(digiSign);
			if(response.isHasError()){
				validatorUtil.reject(errors, "invalid.Signature",response.getMessage());
			}*/
		}
	}
	/* (non-Javadoc)
	 * @see com.novelerp.core.validator.Validator#validate(java.lang.Object, com.novelerp.core.dto.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	
	private TAHDRDto getTahdr(Long tahdrMaterialId , Long tahdrId){
		Map<String , Object>map=new HashMap<>();
		map.put("tahdrMaterialId", tahdrMaterialId);
		map.put("tahdrId", tahdrId);
		return tahdrService.findDto("getTahdrWithAllDetailsForMaterial",map);
	}
	
	private PriceBidDto getPriceBid(Long partnerId , Long itemBidId){
		Map<String, Object> param=new HashMap<>();
		param.put("itemBidId", itemBidId);
		param.put("partnerId", partnerId);
		return priceBidService.findDto("verifyPriceBid", param);
	}
	
	private TechnicalBidDto getTechnicalBid(Long partnerId , Long itemBidId){
		Map<String, Object> param=new HashMap<>();
		param.put("itemBidId", itemBidId);
		param.put("partnerId", partnerId);
		return technicalBidService.findDto("verifyTechnicalBid", param);
	}
	
	public void saveValidator(Object object,Errors errors){
		if(!object.getClass().isAssignableFrom(PriceBidDto.class)){
			validatorUtil.classNotSupported(errors, "invalid.classObject", "Invalid class Obect");
			return;
		}
		
		PriceBidDto priceBidDto =  (PriceBidDto) object;
		Map<String, Object> map=new HashMap<>();
		map.put("itemBidId", priceBidDto.getItemBid().getItemBidId());
		ItemBidDto dto =itemBidService.findDto("getTahdrDataFromItemBidId", map);
		
		if(dto!=null && priceBidDto.getMaterialSpecification()==null){
			TAHDRDto tahdr=null;
			BidderDto bidder=dto.getBidder();
			if(bidder!=null){
				tahdr=bidder.getTahdr();
				if(tahdr!=null){
					BPartnerDto partner=contextService.getPartner();
					CommercialBidDto cb=commercialBidService.getCommercialBid(tahdr.getTahdrId(), partner.getbPartnerId());
					BigDecimal cbGst=cb.getGst();
					if(priceBidDto.getTaxRate()!=null && cbGst!=null ){
					BigDecimal pbGst=new BigDecimal(priceBidDto.getTaxRate());
					if(cbGst.compareTo(pbGst)<0){
						validatorUtil.reject(errors, "invalid.GST", "Entered Gst should be less than or equal to Gst entered in Commercial Details.");
					}
					}
				}
			}
		}
	}
}
